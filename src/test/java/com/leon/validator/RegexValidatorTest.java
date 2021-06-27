package com.leon.validator;

import com.leon.model.FieldValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
public class RegexValidatorTest
{

    @Autowired
    Validator regexValidator;

    @Test
    public void validate_when_shouldReturn()
    {
        // Arrange
        String input = "";
        // Act
        Boolean result = regexValidator.validate(input, new FieldValidation());
        // Assert
        assertTrue(result);
    }
}
