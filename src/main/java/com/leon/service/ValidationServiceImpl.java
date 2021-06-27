package com.leon.service;

import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationServiceImpl implements ValidationService
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Override
    public ValidationResult validate(String filePath, ValidationConfiguration validationConfiguration)
    {
        return new ValidationResult();
    }
}
