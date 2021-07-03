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
public class IntegerValidatorTest
{

    @Autowired
    private IntegerValidator integerValidator;

    @Test
    public void validate_whenPassedValidInteger_shouldReturnEmptyString()
    {
        // Arrange
        String input = "10";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.INTEGER);
        // Act
        String actualResult = integerValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid integer value, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedValidNegativeInteger_shouldReturnEmptyString()
    {
        // Arrange
        String input = "-10";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.INTEGER);
        // Act
        String actualResult = integerValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid negative integer value, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedValidPositiveInteger_shouldReturnEmptyString()
    {
        // Arrange
        String input = "+10";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.INTEGER);
        // Act
        String actualResult = integerValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid positive integer value, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedDouble_shouldReturnErrorString()
    {
        // Arrange
        String input = "10.0";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.INTEGER);
        // Act
        String actualResult = integerValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid double value, validate should return an error string",  "The field value [10.0] is not a valid integer value.", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidInteger_shouldReturnErrorString()
    {
        // Arrange
        String input = "Horatio";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.INTEGER);
        // Act
        String actualResult = integerValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed non integer value, validate should return an error string",  "The field value [Horatio] is not a valid integer value.", actualResult);
    }
}
