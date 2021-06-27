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
    public void validate_when_shouldReturn()
    {
        // Arrange
        String input = "";
        // Act
        Boolean result = booleanValidator.validate(input, new FieldValidation());
        // Assert
        assertTrue(result);
    }
}
