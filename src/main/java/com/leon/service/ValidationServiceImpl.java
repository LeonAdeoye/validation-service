package com.leon.service;

import com.leon.model.ValidationResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ValidationServiceImpl implements ValidationService
{
    private static final Logger logger = LoggerFactory.getLogger(ValidationServiceImpl.class);

    @Override
    public ValidationResult validate()
    {
        return new ValidationResult();
    }
}
