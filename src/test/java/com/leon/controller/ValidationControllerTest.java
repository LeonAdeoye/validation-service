package com.leon.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationRequest;
import com.leon.service.ValidationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

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
        ValidationConfiguration validationConfiguration = new ValidationConfiguration();
        validationConfiguration.setDelimiter(",");
        validationConfiguration.setRowStart(0);
    }

    @Test
    public void testHeartbeat() throws Exception
    {
    }

    @Test
    public void validate_whenPassedEmptyFilePath_ShouldThrowException() throws Exception
    {
        validationService.validate("", new ValidationConfiguration());
    }
}