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
    public void validate_() throws Exception
    {
        // Arrange
        ValidationConfiguration validationConfiguration = new ValidationConfiguration();
        validationConfiguration.setDelimiter(",");
        validationConfiguration.setRowStart(1);
        validationConfiguration.setDuplicatesAllowed(false);
        validationConfiguration.setListOfFieldValidations(Arrays.asList());

        URL resource = getClass().getClassLoader().getResource("test-data.csv");
        if (resource == null)
            throw new IllegalArgumentException("test-data.csv file not found.");
        // Act
        ValidationResult actualResult = validationService.validate(resource.toURI().toString(), validationConfiguration);
        // Assert
        assertEquals("when passed ",  Arrays.asList(), actualResult.getErrors());
    }
}
