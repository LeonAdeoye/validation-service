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
import java.util.concurrent.atomic.AtomicInteger;

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
        Flux<DataRow> rows = fileReaderService.readFile(filePath, validationConfiguration.getDelimiter());
        AtomicInteger rowIndex = new AtomicInteger(0);

        rows.parallel()
            .runOn(Schedulers.parallel())
            .doOnNext(dataRow  ->
            {
                result.concatenateErrors(validateRow(dataRow.getRowNumber(), dataRow.getRowValues(), validationConfiguration).getErrors());
            })
            .subscribe();

        return new ValidationResult();
    }

    private String addErrorRowDetails(int row, int column)
    {
        return String.format("Validation error found at row %d and column %d. ", row, column);
    }

    private ValidationResult validateRow(int rowIndex, String[] row, ValidationConfiguration validationConfiguration)
    {
        ValidationResult result = new ValidationResult();
        List<FieldValidation> listOfValidations = validationConfiguration.getListOfFieldValidations();

        if(row.length != listOfValidations.size())
        {
            result.addError(String.format("Row length of %d is not equal to validation list size of %d", row.length, listOfValidations.size()));
            return result;
        }

        for(int columnIndex = 0; columnIndex < row.length; ++columnIndex)
        {
            FieldValidation fieldValidation = listOfValidations.get(columnIndex);
            String errorRowDetails = addErrorRowDetails(rowIndex, columnIndex);
            String fieldValue = row[columnIndex];

            if(fieldValue.isEmpty() && fieldValidation.cannotBeEmpty())
            {
                result.addError(errorRowDetails + "The field value is empty.");
                continue;
            }

            switch(listOfValidations.get(columnIndex).getType().toUpperCase())
            {
                case Validator.INTEGER:
                    result.addError(errorRowDetails + integerValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.DOUBLE:
                    result.addError(errorRowDetails + doubleValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.STRING:
                    result.addError(errorRowDetails + stringValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.CURRENCY:
                    result.addError(errorRowDetails + currencyValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.BOOLEAN:
                    result.addError(errorRowDetails + booleanValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.REGEX:
                    result.addError(errorRowDetails + regexValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.DELIMITED:
                    result.addError(errorRowDetails + delimitedListValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.TIMESTAMP:
                    result.addError(errorRowDetails + timestampValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.RANGE:
                    result.addError(errorRowDetails + rangeValidator.validate(fieldValue, fieldValidation));
                    break;
                case Validator.ENUMERATED:
                    result.addError(errorRowDetails + enumeratedTypeValidator.validate(fieldValue, fieldValidation));
                    break;
                default:
                    result.addError(errorRowDetails + String.format("field validation type %s is unsupported.", listOfValidations.get(columnIndex).getType()));
            }
        }
        return result;
    }
}
