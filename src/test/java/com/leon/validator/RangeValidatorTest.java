package com.leon.validator;

import com.leon.model.FieldValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RangeValidatorTest
{
    @Autowired
    private RangeValidator rangeValidator;

    @Test
    public void validate_whenPassedValueWithinTheRange_shouldReturnEmptyString()
    {
        // Arrange
        String input = "5";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.RANGE);
        fieldValidation.setValidRange("1,10");
        // Act
        String actualResult = rangeValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed value within the two ranges, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedValueAboveTheRange_shouldReturnErrorString()
    {
        // Arrange
        String input = "15";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.RANGE);
        fieldValidation.setValidRange("1,10");
        // Act
        String actualResult = rangeValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed value outside the range, validate should return an error string",  "The field value [15] is not within the ranges: 1 and 10.", actualResult);
    }

    @Test
    public void validate_whenPassedMinimumValueOfTheRange_shouldReturnEmptyString()
    {
        // Arrange
        String input = "1";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.RANGE);
        fieldValidation.setValidRange("1,10");
        // Act
        String actualResult = rangeValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed value outside the range, validate should return an error string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedMaximumValueOfTheRange_shouldReturnEmptyString()
    {
        // Arrange
        String input = "10";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.RANGE);
        fieldValidation.setValidRange("1,10");
        // Act
        String actualResult = rangeValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed value outside the range, validate should return an error string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedValueBelowTheRange_shouldReturnErrorString()
    {
        // Arrange
        String input = "0";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.RANGE);
        fieldValidation.setValidRange("1,10");
        // Act
        String actualResult = rangeValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed value outside the range, validate should return an error string",  "The field value [0] is not within the ranges: 1 and 10.", actualResult);
    }
}
