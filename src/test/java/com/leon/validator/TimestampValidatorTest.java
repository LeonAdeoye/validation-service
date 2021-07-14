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
public class TimestampValidatorTest
{
    @Autowired
    private TimestampValidator timestampValidator;

    @Test
    public void validate_whenPassedValidTimestampValue_shouldReturnEmptyString()
    {
        // Arrange
        String input = "23-12-2012";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.TIMESTAMP);
        fieldValidation.setTimestampFormat("dd-mm-yyyy");
        // Act
        String actualResult = timestampValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid timestamp value, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidTimestampValue_shouldReturnErrorString()
    {
        // Arrange
        String input = "23-XX-2012";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.TIMESTAMP);
        fieldValidation.setTimestampFormat("dd-mm-yyyy");
        // Act
        String actualResult = timestampValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid timestamp value, validate should return an empty string",  "The field value [23-XX-2012] does not match date timestamp value format.", actualResult);
    }

    @Test
    public void validate_whenPassedValidTimestampValue_shouldReturnErrorString()
    {
        // Arrange
        String input = "23-12-2012 10:10:30";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.TIMESTAMP);
        fieldValidation.setTimestampFormat("dd-MM-yyyy HH:mm:ss");
        // Act
        String actualResult = timestampValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid timestamp value, validate should return an empty string",  "", actualResult);
    }
}
