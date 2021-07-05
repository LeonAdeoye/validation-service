package com.leon.validator;

import com.leon.model.FieldValidation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TimestampValidatorTest
{
    @Autowired
    private TimestampValidator timestampValidator;

    @Test
    public void validate_whenPassedValueWithinTheRange_shouldReturnEmptyString()
    {
        // Arrange
        String input = "23-DEC-2012 19:14:00.500";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(Validator.TIMESTAMP);
        //fieldValidation.setTimestampFormat("dd-MMM-yyyy HH:mm:ss.SSS");
        // Act
        //String actualResult = timestampValidator.validate(input, fieldValidation);
        // Assert
        //assertEquals("when passed value timestamp value, validate should return an empty string",  "", actualResult);
    }
}
