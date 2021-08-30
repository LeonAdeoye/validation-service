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
public class DoubleValidatorTest
{

    @Autowired
    private DoubleValidator doubleValidator;

    @Test
    public void validate_whenPassedValidDouble_shouldReturnEmptyString()
    {
        // Arrange
        String input = "10.0";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.DOUBLE.toString());
        // Act
        String actualResult = doubleValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid double value, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidDouble_shouldReturnErrorString()
    {
        // Arrange
        String input = "Horatio";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.DOUBLE.toString());
        // Act
        String actualResult = doubleValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed non double value, validate should return an error string",  "The field value [Horatio] is not a valid double value.", actualResult);
    }

    @Test
    public void validate_whenPassedValidInteger_shouldReturnEmptyString()
    {
        // Arrange
        String input = "10";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.DOUBLE.toString());
        // Act
        String actualResult = doubleValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed non double value, validate should return an error string",  "", actualResult);
    }
}
