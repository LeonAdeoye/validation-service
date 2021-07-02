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
    private Validator booleanValidator;

    @Test
    public void validate_whenPassedValidLowercaseTrueBoolean_shouldReturnTrue()
    {
        // Arrange
        String input = "true";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String result = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid lowercase boolean value, validate should return empty string", result, "");
    }

    @Test
    public void validate_whenPassedValidUppercaseTrueBoolean_shouldReturnTrue()
    {
        // Arrange
        String input = "TRUE";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String result = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid uppercase booleanb value, validate should return empty string", result, "");
    }

    @Test
    public void validate_whenPassedValidMixedCaseTrueBoolean_shouldReturnTrue()
    {
        // Arrange
        String input = "True";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String result = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid mixed case boolean value, validate should return empty string", result, "");
    }

    @Test
    public void validate_whenPassedValidLowercaseFalseBoolean_shouldReturnTrue()
    {
        // Arrange
        String input = "false";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String result = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid lowercase boolean value, validate should return empty string", result, "");
    }

    @Test
    public void validate_whenPassedValidUppercaseFalseBoolean_shouldReturnTrue()
    {
        // Arrange
        String input = "FALSE";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String result = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid uppercase booleanb value, validate should return empty string", result, "");
    }

    @Test
    public void validate_whenPassedValidMixedCaseFalseBoolean_shouldReturnTrue()
    {
        // Arrange
        String input = "False";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String result = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed valid mixed case boolean value, validate should return empty string", result, "");
    }

    @Test
    public void validate_whenPassedInvalidBoolean_shouldReturnFalse()
    {
        // Arrange
        String input = "TRUTHY";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.BOOLEAN);
        // Act
        String result = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertEquals("when passed invalid boolean value, validate should return empty string", result, "The field value [TRUTHY] is not a valid boolean.");
    }
}
