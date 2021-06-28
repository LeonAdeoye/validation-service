package com.leon.controller;

import com.leon.model.ValidationRequest;
import com.leon.model.ValidationResult;
import com.leon.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidationController
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationController.class);
    @Autowired
    ValidationService validationService;

    @RequestMapping("/heartbeat")
    String heartbeat()
    {
        return "Here I am";
    }

    @RequestMapping("/validate")
    ValidationResult validate(@RequestBody ValidationRequest request)
    {
        if(request == null)
        {
            logger.error("Validation request cannot be null");
            throw new IllegalArgumentException("Validation request cannot be null.");
        }

        return validationService.validate(request.getFilePath(), request.getValidationConfiguration());
    }
}
