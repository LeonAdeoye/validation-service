package com.leon.controller;

import com.leon.model.ValidationRequest;
import com.leon.model.ValidationResult;
import com.leon.service.ValidationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class ValidationController
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationController.class);
    @Autowired
    ValidationService validationService;

    @CrossOrigin
    @RequestMapping("/heartbeat")
    String heartbeat()
    {
        return "Here I am";
    }

    @CrossOrigin
    @RequestMapping(value = "/validate", method={POST}, consumes=MediaType.APPLICATION_JSON_VALUE, produces=MediaType.APPLICATION_JSON_VALUE )
    String validate(@RequestBody ValidationRequest request)
    {
        if(request.getFilePath() == null || request.getFilePath().isEmpty())
        {
            logger.error("Validation request's file path cannot be null or an empty string.");
            return new ValidationResult("Validation request's file path cannot be null or an empty string.").toJSON();
        }

        if(request.getValidationConfiguration() == null)
        {
            logger.error("Validation request's configuration cannot be null.");
            return new ValidationResult("Validation request's configuration cannot be null.").toJSON();
        }

        logger.info(String.format("Received request to validate file: %s with configuration: %s", request.getFilePath(), request.getValidationConfiguration()));
        return validationService.validate(request.getFilePath(), request.getValidationConfiguration()).toJSON();
    }
}
