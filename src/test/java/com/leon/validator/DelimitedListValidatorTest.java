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
public class DelimitedListValidatorTest
{
    @Autowired
    private DelimitedListValidator delimitedListValidator;

    @Test
    public void validate_whenPassedValidDelimitedList_shouldReturnEmptyString()
    {
        // Arrange
        String input = "10|11|12";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.DELIMITED.toString());
        fieldValidation.setListDelimiterRegex("\\|");
        // Act
        String actualResult = delimitedListValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed valid list of delimited values, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedSingleValue_shouldReturnEmptyString()
    {
        // Arrange
        String input = "Horatio";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.DELIMITED.toString());
        fieldValidation.setListDelimiterRegex("\\|");
        // Act
        String actualResult = delimitedListValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed a single delimited value, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedDelimitedListWithDifferentDelimiter_shouldReturnErrorString()
    {
        // Arrange
        String input = "10~10";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.DELIMITED.toString());
        fieldValidation.setListDelimiterRegex("\\|");
        fieldValidation.setMinimumDelimitedValues(2);
        // Act
        String actualResult = delimitedListValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed delimited list value with a different delimiter, validate should return an error string",  "The field value [10~10] is not a valid delimited list value.", actualResult);
    }

    @Test
    public void validate_whenPassedDelimitedListWithCountOfElementsMoreThanMinimum_shouldReturnEmptyString()
    {
        // Arrange
        String input = "10~20~30";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.DELIMITED.toString());
        fieldValidation.setListDelimiterRegex("~");
        fieldValidation.setMinimumDelimitedValues(2);
        // Act
        String actualResult = delimitedListValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed delimited list value with the count of elements matching the minimum, validate should return an empty string",  "", actualResult);
    }

    @Test
    public void validate_whenPassedDelimitedListWithCountOfElementsLessThanMinimum_shouldReturnErrorString()
    {
        // Arrange
        String input = "10~20~30";
        FieldValidation fieldValidation = new FieldValidation();
        fieldValidation.setType(ValidatorType.DELIMITED.toString());
        fieldValidation.setListDelimiterRegex("~");
        fieldValidation.setMinimumDelimitedValues(5);
        // Act
        String actualResult = delimitedListValidator.validate(input, fieldValidation);
        // Assert
        assertEquals("when passed delimited list value with the count of elements less than the minimum, validate should return an error string",  "The field value [10~20~30] is not a valid delimited list value.", actualResult);
    }
}
