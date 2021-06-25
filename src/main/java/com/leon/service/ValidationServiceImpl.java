package com.leon.service;

import com.leon.model.ValidationResult;

public class ValidationServiceImpl implements ValidationService
{
    @Override
    public ValidationResult validate()
    {
        return new ValidationResult();
    }
}
