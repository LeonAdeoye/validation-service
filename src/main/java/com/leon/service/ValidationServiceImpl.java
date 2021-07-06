package com.leon.service;

import com.leon.model.DataRow;
import com.leon.model.FieldValidation;
import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationResult;
import com.leon.validator.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Autowired
    FileReaderService fileReaderService;

    @Autowired
    IntegerValidator integerValidator;

    @Autowired
    DoubleValidator doubleValidator;

    @Autowired
    StringValidator stringValidator;

    @Autowired
    CurrencyValidator currencyValidator;

    @Autowired
    BooleanValidator booleanValidator;

    @Autowired
    TimestampValidator timestampValidator;

    @Autowired
    RegexValidator regexValidator;

    @Autowired
    RangeValidator rangeValidator;

    @Autowired
    DelimitedListValidator delimitedListValidator;

    @Autowired
    EnumeratedTypeValidator enumeratedTypeValidator;

    @Override
    public ValidationResult validate(String filePath, ValidationConfiguration validationConfiguration)
    {
        ValidationResult result = new ValidationResult();
        try
        {
            Flux<DataRow> rows = fileReaderService.readFile(filePath, validationConfiguration.getDelimiter());
            rows.parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(dataRow -> result.concatenateErrors(validateRow(dataRow, validationConfiguration).getErrors()))
                .subscribe();
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
        finally
        {
            return result;
        }
    }

    private String addErrorRowDetails(int row, int column)
    {
        return String.format("Validation error found at row %d and column %d. ", row, column);
    }

    private ValidationResult validateRow(DataRow dataRow, ValidationConfiguration validationConfiguration)
    {
        ValidationResult result = new ValidationResult();
        List<FieldValidation> listOfValidations = validationConfiguration.getListOfFieldValidations();

        if(dataRow.getRowValues().length != listOfValidations.size())
        {
            result.addError(String.format("Row length of %d is not equal to validation list size of %d", dataRow.getRowValues().length, listOfValidations.size()));
            return result;
        }

        for(int columnIndex = 0; columnIndex < listOfValidations.size(); ++columnIndex)
        {
            FieldValidation fieldValidation = listOfValidations.get(columnIndex);
            String errorRowDetails = addErrorRowDetails(dataRow.getRowNumber(), columnIndex);
            String fieldValue = dataRow.getRowValues()[columnIndex];

            if(fieldValue.isEmpty() && fieldValidation.cannotBeEmpty())
            {
                result.addError(errorRowDetails + "The field value is empty.");
                continue;
            }

            String validationResult;

            switch(listOfValidations.get(columnIndex).getType().toUpperCase())
            {
                case Validator.INTEGER:
                    validationResult = integerValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.DOUBLE:
                    validationResult = doubleValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.STRING:
                    validationResult = stringValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.CURRENCY:
                    validationResult = currencyValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.BOOLEAN:
                    validationResult =  booleanValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.REGEX:
                    validationResult = regexValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.DELIMITED:
                    validationResult = delimitedListValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.TIMESTAMP:
                    validationResult = timestampValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.RANGE:
                    validationResult = rangeValidator.validate(fieldValue, fieldValidation);
                    break;
                case Validator.ENUMERATED:
                    validationResult = enumeratedTypeValidator.validate(fieldValue, fieldValidation);
                    break;
                default:
                    validationResult = String.format("field validation type %s is unsupported.", listOfValidations.get(columnIndex).getType());
            }

            if(!validationResult.isEmpty())
            {
                String error = errorRowDetails + validationResult;
                logger.error(error);
                result.addError(error);
            }
        }
        return result;
    }
}
