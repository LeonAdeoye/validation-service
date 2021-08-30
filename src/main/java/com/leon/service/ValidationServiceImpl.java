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
import java.util.*;

@Service
public class ValidationServiceImpl implements ValidationService
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);
    private Map<Integer, Integer> rowsHashMap = new HashMap<>();
    private Validator validator;

    @Autowired
    FileReaderService fileReaderService;

    @Override
    public ValidationResult validate(String filePath, ValidationConfiguration validationConfiguration)
    {
        ValidationResult result = new ValidationResult();
        rowsHashMap.clear();
        try
        {
            Flux<DataRow> rows = fileReaderService.readFile(filePath, validationConfiguration.getDelimiter());
            rows.parallel()
                .runOn(Schedulers.parallel())
                .doOnNext(dataRow ->
                {
                    result.concatenateErrors(isDuplicate(dataRow, validationConfiguration).getErrors());
                    result.concatenateErrors(isValidRow(dataRow, validationConfiguration).getErrors());
                })
                .sequential()
                .blockLast();
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

    private ValidationResult isDuplicate(DataRow dataRow, ValidationConfiguration validationConfiguration)
    {
        ValidationResult result = new ValidationResult();

        if(validationConfiguration.areDuplicatesAllowed())
            return result;

        int hash = Arrays.hashCode(dataRow.getRowValues());

        if(rowsHashMap.containsKey(hash))
            result.addError("The row: " + dataRow.getRowNumber() + " with values: " + Arrays.toString(dataRow.getRowValues()) + " is a duplicate of row: " + rowsHashMap.get(hash));
        else
            rowsHashMap.put(hash, dataRow.getRowNumber());

        return result;
    }

    private ValidationResult isValidRow(DataRow dataRow, ValidationConfiguration validationConfiguration)
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

            ValidatorFactory validatorFactory = new ValidatorFactory();
            validator = validatorFactory.create(ValidatorType.valueOf(listOfValidations.get(columnIndex).getType().toUpperCase()));
            if(validator != null)
                validationResult = validator.validate(fieldValue, fieldValidation);
            else
                validationResult = String.format("field validation type %s is unsupported.", listOfValidations.get(columnIndex).getType());

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
