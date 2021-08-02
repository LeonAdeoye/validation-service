package com.leon.validator;

import com.leon.model.FieldValidation;
import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationResult;
import com.leon.service.ValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ValidationServiceTest
{
    @Autowired
    private ValidationService validationService;

    @Test
    public void validate_duplicates_not_allowed() throws Exception
    {
        FieldValidation firstFieldValidation = new FieldValidation();
        firstFieldValidation.setId(1);
        firstFieldValidation.setDescription("Name");
        firstFieldValidation.setCanBeEmpty(false);
        firstFieldValidation.setType(Validator.STRING);

        FieldValidation secondFieldValidation = new FieldValidation();
        secondFieldValidation.setId(2);
        secondFieldValidation.setDescription("wealth");
        secondFieldValidation.setCanBeEmpty(false);
        secondFieldValidation.setType(Validator.STRING);

        // Arrange
        ValidationConfiguration validationConfiguration = new ValidationConfiguration();
        validationConfiguration.setDelimiter(",");
        validationConfiguration.setRowStart(1);
        validationConfiguration.setDuplicatesAllowed(false);
        validationConfiguration.setListOfFieldValidations(Arrays.asList(firstFieldValidation, secondFieldValidation));
        URL resource = getClass().getClassLoader().getResource("test-data.csv");
        if (resource == null)
            throw new IllegalArgumentException("test-data.csv file not found.");
        // Act
        ValidationResult actualResult = validationService.validate(resource.toURI().getRawPath(), validationConfiguration);
        // Assert
        assertEquals("Validation that duplicates are not allowed",  Arrays.asList("The row already exists: DataRow{rowNumber=4, rowValues=[Harper, 1]}"), actualResult.getErrors());
    }
    @Test
    public void validate_duplicates_allowed() throws Exception
    {
        FieldValidation firstFieldValidation = new FieldValidation();
        firstFieldValidation.setId(1);
        firstFieldValidation.setDescription("Name");
        firstFieldValidation.setCanBeEmpty(false);
        firstFieldValidation.setType(Validator.STRING);

        FieldValidation secondFieldValidation = new FieldValidation();
        secondFieldValidation.setId(2);
        secondFieldValidation.setDescription("Wealth");
        secondFieldValidation.setCanBeEmpty(false);
        secondFieldValidation.setType(Validator.STRING);

        // Arrange
        ValidationConfiguration validationConfiguration = new ValidationConfiguration();
        validationConfiguration.setDelimiter(",");
        validationConfiguration.setRowStart(1);
        validationConfiguration.setDuplicatesAllowed(true);
        validationConfiguration.setListOfFieldValidations(Arrays.asList(firstFieldValidation, secondFieldValidation));
        URL resource = getClass().getClassLoader().getResource("test-data.csv");
        if (resource == null)
            throw new IllegalArgumentException("test-data.csv file not found.");
        // Act
        ValidationResult actualResult = validationService.validate(resource.toURI().getRawPath(), validationConfiguration);
        // Assert
        assertEquals("Validation that duplicates are allowed",  Arrays.asList(), actualResult.getErrors());
    }
}
