package com.leon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leon.model.FieldValidation;
import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationRequest;
import com.leon.service.ValidationService;
import com.leon.validator.Validator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ValidationControllerTest
{
    private MockMvc mockMVC;
    @Mock
    private ValidationService validationService;
    @InjectMocks
    private ValidationController validationController;
    private ValidationRequest validationRequest = new ValidationRequest();

    private static String asJsonString(final Object obj)
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
        field.setType(Validator.INTEGER);

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
    public void testHeartbeat() throws Exception
    {
    }

    @Test
    public void validate_whenPassedEmptyFilePath_ShouldNeverCallValidationService() throws Exception
    {
        // Arrange
        validationRequest.setFilePath("");
        // Act
        String response = mockMVC.perform(post("/validate")
                .content(asJsonString(validationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Assert
        verify(validationService, never()).validate(any(), any());
        //assertEquals("{\"status\" : \"ERROR\", \"errors\" : [\"File path cannot be null or empty.\"]}", response);
    }

    @Test
    public void validate_whenPassedNullFilePath_ShouldNeverCallValidationService() throws Exception
    {
        // Arrange
        validationRequest.setFilePath(null);
        // Act
        String response = mockMVC.perform(post("/validate")
                .content(asJsonString(validationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Assert
        verify(validationService, never()).validate(any(), any());
        //assertEquals("{\"status\" : \"ERROR\", \"errors\" : [\"File path cannot be null or empty.\"]}", response);
    }

    @Test
    public void validate_whenPassedNullValidationConfiguration_ShouldNeverCallValidationService() throws Exception
    {
        // Arrange
        validationRequest.setValidationConfiguration(null);
        // Act
        String response = mockMVC.perform(post("/validate")
                .content(asJsonString(validationRequest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        // Assert
        verify(validationService, never()).validate(any(), any());
        //assertEquals("{\"status\" : \"ERROR\", \"errors\" : [\" Validation configuration cannot be null.\"]}", response);
    }
}