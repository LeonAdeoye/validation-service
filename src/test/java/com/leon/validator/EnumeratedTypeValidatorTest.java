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
public class EnumeratedTypeValidatorTest
{
    @Autowired
    EnumeratedTypeValidator enumeratedTypeValidator;

    @Test
    public void validate_whenPassedValueMatchingEnumeratedTypeValues_shouldReturnEmptyString()
    {
        // Arrange
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setEnumeratedTypeValues(new String[] {"ABC","DEF"});
        String input = "ABC";
        // Act
        String actualResult = enumeratedTypeValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("When passed a value that matches one of the enumerated type values, validate should return an empty string.", "", actualResult);
    }

    @Test
    public void validate_whenPassedValueNotMatchingEnumeratedTypeValues_shouldReturnErrorString()
    {
        // Arrange
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setEnumeratedTypeValues(new String[] {"ABC","DEF"});
        String input = "XYZ";
        // Act
        String actualResult = enumeratedTypeValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("When passed a value that does not match one of the enumerated type values, validate should return an error string.", "The field value [XYZ] is not one of the enumerated type values.", actualResult);
    }
}
