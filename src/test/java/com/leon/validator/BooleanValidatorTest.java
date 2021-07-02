package com.leon.validator;

import com.leon.model.FieldValidation;
import org.junit.runner.RunWith;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BooleanValidatorTest
{

    @Autowired
    private BooleanValidator booleanValidator;

    @Test
    public void validate_whenPassedValidLowercaseTrueBoolean_shouldReturnEmptyString()
    {
        // Arrange
        String input = "true";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String actualResult = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid lowercase boolean value, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedValidUppercaseTrueBoolean_shouldReturnEmptyString()
    {
        // Arrange
        String input = "TRUE";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String actualResult = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid uppercase boolean value, validate should return an empty string","", actualResult);
    }

    @Test
    public void validate_whenPassedValidMixedCaseTrueBoolean_shouldReturnEmptyString()
    {
        // Arrange
        String input = "True";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String actualResult = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid mixed case boolean value, validate should return an empty string", "", actualResult);
    }

    @Test
    public void validate_whenPassedValidLowercaseFalseBoolean_shouldReturnEmptyString()
    {
        // Arrange
        String input = "false";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String actualResult = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid lowercase boolean value, validate should return an empty string", "", actualResult);
    }

    @Test
    public void validate_whenPassedValidUppercaseFalseBoolean_shouldReturnEmptyString()
    {
        // Arrange
        String input = "FALSE";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String actualResult = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid uppercase booleanb value, validate should return an empty string", "", actualResult);
    }

    @Test
    public void validate_whenPassedValidMixedCaseFalseBoolean_shouldReturnEmptyString()
    {
        // Arrange
        String input = "False";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String actualResult = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid mixed case boolean value, validate should return an empty string", "", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidBoolean_shouldReturnErrorString()
    {
        // Arrange
        String input = "TRUTHY";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String actualResult = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed invalid boolean value, validate should return an error string", "The field value [TRUTHY] is not a valid boolean.", actualResult);
    }
}
