package com.leon.controller;

import com.leon.model.ValidationConfiguration;
import com.leon.service.ValidationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
public class ValidationControllerTest
{
    @Autowired
    ValidationService validationService;

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