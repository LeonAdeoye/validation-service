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
public class RegexValidatorTest
{
    @Autowired
    RegexValidator regexValidator;

    @Test
    public void validate_whenPassedValueMatchingRegex_shouldReturnEmptyString()
    {
        // Arrange
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setValidRegex("^[A-Z]{3}$");
        String input = "ABC";
        // Act
        String actualResult = regexValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("When passed a valid regex value, validate should return an empty string.", "", actualResult);
    }

    @Test
    public void validate_whenPassedValueNotMatchingRegex_shouldReturnErrorString()
    {
        // Arrange
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setValidRegex("^[A-Z]{3}$");
        String input = "1234";
        // Act
        String actualResult = regexValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("When passed a valid regex value, validate should return an error string.","The field value [1234] does not match the regex expression: ^[A-Z]{3}$.", actualResult);
    }
}
