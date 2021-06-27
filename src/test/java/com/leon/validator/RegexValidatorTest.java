package com.leon.validator;

import com.leon.model.FieldValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
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
