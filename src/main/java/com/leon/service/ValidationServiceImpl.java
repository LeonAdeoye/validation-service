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

        rows.parallel()
            .runOn(Schedulers.parallel())
            .doOnNext(row  -> result.concatenateErrors(validateRow(row, validationConfiguration).getErrors()))
            .subscribe();

        return new ValidationResult();
    }

    private ValidationResult validateRow(String[] row, ValidationConfiguration validationConfiguration)
    {
        ValidationResult result = new ValidationResult();
        List<FieldValidation> listOfValidations = validationConfiguration.getListOfFieldValidations();

        for(int columnIndex = 0; columnIndex < row.length; ++columnIndex)
        {
            switch(listOfValidations.get(columnIndex).getType())
            {
                case "INTEGER":
                    result.addError(integerValidator.validate(row[columnIndex], listOfValidations.get(columnIndex)));
                case "DOUBLE":
                    result.addError(doubleValidator.validate(row[columnIndex], listOfValidations.get(columnIndex)));
                case "STRING":
                    result.addError(stringValidator.validate(row[columnIndex], listOfValidations.get(columnIndex)));
                case "CURRENCY":
                    result.addError(currencyValidator.validate(row[columnIndex], listOfValidations.get(columnIndex)));
                case "BOOLEAN":
                    result.addError(booleanValidator.validate(row[columnIndex], listOfValidations.get(columnIndex)));
                case "REGEX":
                    result.addError(regexValidator.validate(row[columnIndex], listOfValidations.get(columnIndex)));
                case "DELIMITED":
                    result.addError(delimitedValidator.validate(row[columnIndex], listOfValidations.get(columnIndex)));
                case "RANGE":
                    result.addError(rangeValidator.validate(row[columnIndex], listOfValidations.get(columnIndex)));
            }
        }
        return result;
    }
}
