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
public class StringValidatorTest
{
    @Autowired
    private StringValidator stringValidator;

    @Test
    public void validate_whenPassedValidUppercaseStringValue_shouldReturnEmptyString()
    {
        // Arrange
        String input = "JPY";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.STRING);
        fieldValidation.setStringFormat("UPPERCASE");
        // Act
        String actualResult = stringValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid uppercase string value, validate method should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidLowercaseStringValue_shouldReturnErrorString()
    {
        // Arrange
        String input = "jpy";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.STRING);
        fieldValidation.setStringFormat("UPPERCASE");
        // Act
        String actualResult = stringValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid lowercase string value, validate method should return an error string",  "The field value [jpy] is not a valid uppercase string.", actualResult);
    }

    @Test
    public void validate_whenPassedValidLowercaseStringValue_shouldReturnEmptyString()
    {
        // Arrange
        String input = "jpy";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.STRING);
        fieldValidation.setStringFormat("LOWERCASE");
        // Act
        String actualResult = stringValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid uppercase string value, validate method should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidUppercaseStringValue_shouldReturnErrorString()
    {
        // Arrange
        String input = "JPY";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.STRING);
        fieldValidation.setStringFormat("LOWERCASE");
        // Act
        String actualResult = stringValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid uppercase string value, validate method should return an error string",  "The field value [JPY] is not a valid lowercase string.", actualResult);
    }

    @Test
    public void validate_whenPassedNonAlphanumericValue_shouldReturnEmptyString()
    {
        // Arrange
        String input = "@#$%%";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.STRING);
        fieldValidation.setStringFormat("LOWERCASE");
        // Act
        String actualResult = stringValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid uppercase string value, validate method should return an error string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedNumericValue_shouldReturnEmptyString()
    {
        // Arrange
        String input = "12345.678910";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.STRING);
        fieldValidation.setStringFormat("LOWERCASE");
        // Act
        String actualResult = stringValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid uppercase string value, validate method should return an error string",  "", actualResult);
    }
}
