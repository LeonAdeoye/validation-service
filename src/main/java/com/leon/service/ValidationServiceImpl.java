package com.leon.service;

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
    RegexValidator regexValidator;

    @Autowired
    RangeValidator rangeValidator;

    @Autowired
    DelimitedValidator delimitedValidator;

    @Override
    public ValidationResult validate(String filePath, ValidationConfiguration validationConfiguration)
    {
        ValidationResult result = new ValidationResult();
        Flux<String[]> rows = fileReaderService.readFile(filePath, validationConfiguration.getDelimiter());
        AtomicInteger count = new AtomicInteger(0);

        rows.parallel()
            .runOn(Schedulers.parallel())
            .doOnNext(row  ->
            {
                result.concatenateErrors(validateRow(count.getAndIncrement(), row, validationConfiguration).getErrors());
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

            switch(listOfValidations.get(columnIndex).getType())
            {
                case "INTEGER":
                    result.addError(errorRowDetails + integerValidator.validate(fieldValue, fieldValidation));
                case "DOUBLE":
                    result.addError(errorRowDetails + doubleValidator.validate(fieldValue, fieldValidation));
                case "STRING":
                    result.addError(errorRowDetails + stringValidator.validate(fieldValue, fieldValidation));
                case "CURRENCY":
                    result.addError(errorRowDetails + currencyValidator.validate(fieldValue, fieldValidation));
                case "BOOLEAN":
                    result.addError(errorRowDetails + booleanValidator.validate(fieldValue, fieldValidation));
                case "REGEX":
                    result.addError(errorRowDetails + regexValidator.validate(fieldValue, fieldValidation));
                case "DELIMITED":
                    result.addError(errorRowDetails+ delimitedValidator.validate(fieldValue, fieldValidation));
                case "RANGE":
                    result.addError(errorRowDetails+ rangeValidator.validate(fieldValue, fieldValidation));
            }
        }
        return result;
    }
}
