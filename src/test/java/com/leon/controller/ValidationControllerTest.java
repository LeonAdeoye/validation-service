package com.leon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leon.model.FieldValidation;
import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationRequest;
import com.leon.model.ValidationResult;
import com.leon.service.ValidationService;
import com.leon.validator.Validator;
import com.leon.validator.ValidatorType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ValidationControllerTest
{
    private MockMvc mockMVC;
    private ValidationRequest validationRequest = new ValidationRequest();

    @Mock
    private ValidationService validationService;

    @InjectMocks
    private ValidationController validationController;

    private static String convertToJSON(final Object obj)
    {
        try
        {
            return new ObjectMapper().writeValueAsString(obj);
        }
        catch(JsonProcessingException jpe)
        {
            throw new RuntimeException(jpe);
        }
    }

    @Before
    public void before()
    {
        mockMVC = MockMvcBuilders.standaloneSetup(validationController).build();

        FieldValidation field = new FieldValidation();
        field.setId(0);
        field.setDescription("integer field");
        field.setType(ValidatorType.INTEGER.toString());

        List<FieldValidation> fields = new ArrayList<>();
        fields.add(field);

        ValidationConfiguration validationConfiguration = new ValidationConfiguration();
        validationConfiguration.setDelimiter(",");
        validationConfiguration.setRowStart(0);
        validationConfiguration.setListOfFieldValidations(fields);

        validationRequest.setFilePath("test-only.csv");
        validationRequest.setValidationConfiguration(validationConfiguration);
    }

    @Test
    public void validate_whenPassedEmptyFilePath_ShouldNeverCallValidationService() throws Exception
    {
        // Arrange
        validationRequest.setFilePath("");
        // Act
        String response = mockMVC.perform(post("/validate")
                .content(convertToJSON(validationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Assert
        verify(validationService, never()).validate(any(), any());
        assertEquals("{\"status\" : \"ERROR\", \"errors\" : [\"Validation request's file path cannot be null or an empty string.\"]}", response);
    }

    @Test
    public void validate_whenPassedNullFilePath_ShouldNeverCallValidationService() throws Exception
    {
        // Arrange
        validationRequest.setFilePath(null);
        // Act
        String response = mockMVC.perform(post("/validate")
                .content(convertToJSON(validationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Assert
        verify(validationService, never()).validate(any(), any());
        assertEquals("{\"status\" : \"ERROR\", \"errors\" : [\"Validation request's file path cannot be null or an empty string.\"]}", response);
    }

    @Test
    public void validate_whenPassedNullValidationConfiguration_ShouldNeverCallValidationService() throws Exception
    {
        // Arrange
        validationRequest.setValidationConfiguration(null);
        // Act
        String response = mockMVC.perform(post("/validate")
                .content(convertToJSON(validationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Assert
        verify(validationService, never()).validate(any(), any());
        assertEquals("{\"status\" : \"ERROR\", \"errors\" : [\"Validation request's configuration cannot be null.\"]}", response);
    }

    @Test
    public void validate_whenPassedValidValidationConfiguration_ShouldCallValidationService() throws Exception
    {
        // Arrange
        Mockito.when(validationService.validate(anyString(), any(ValidationConfiguration.class))).thenReturn(new ValidationResult());
        // Act
        String response = mockMVC.perform(post("/validate")
                .content(convertToJSON(validationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        // Assert
        verify(validationService, times(1)).validate(eq(validationRequest.getFilePath()), eq(validationRequest.getValidationConfiguration()));
        assertEquals("{\"status\" : \"SUCCESS\", \"errors\" : []}", response);
    }
}