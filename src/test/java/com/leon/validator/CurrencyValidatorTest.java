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
public class CurrencyValidatorTest
{

    @Autowired
    private CurrencyValidator currencyValidator;

    @Test
    public void validate_whenPassedValidCurrencyValue_shouldReturnEmptyString()
    {
        // Arrange
        String input = "JPY";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.CURRENCY.toString());
        // Act
        String actualResult = currencyValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid currency value, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidLowercaseCurrencyValue_shouldReturnErrorString()
    {
        // Arrange
        String input = "jpy";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.CURRENCY.toString());
        // Act
        String actualResult = currencyValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid lowercase boolean value, validate should return an error string","The field value [jpy] is not a valid currency.", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidMixedCaseCurrencyValue_shouldReturnErrorString()
    {
        // Arrange
        String input = "UsD";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.CURRENCY.toString());
        // Act
        String actualResult = currencyValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid mixed case currency value, validate should return an error string", "The field value [UsD] is not a valid currency.", actualResult);
    }

    @Test
    public void validate_whenPassedInvalidLongerValue_shouldReturnErrorString()
    {
        // Arrange
        String input = "GBPS";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.CURRENCY.toString());
        // Act
        String actualResult = currencyValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed invalid four character currency value, validate should return an error string", "The field value [GBPS] is not a valid currency.", actualResult);
    }
}
