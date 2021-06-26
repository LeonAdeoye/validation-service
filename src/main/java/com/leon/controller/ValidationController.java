package com.leon.controller;

import com.leon.model.ValidationResult;
import com.leon.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController
{
    @Autowired
    ValidationService validationService;

    @RequestMapping("/heartbeat")
    String heartbeat()
    {
        return "Hello World!";
    }

    @RequestMapping("/validate")
    ValidationResult validate()
    {
        return validationService.validate();
    }
}
