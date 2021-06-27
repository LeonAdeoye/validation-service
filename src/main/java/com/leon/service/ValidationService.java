package com.leon.service;

import com.leon.model.ValidationConfiguration;
import com.leon.model.ValidationResult;

public interface ValidationService
{
    ValidationResult validate(String filePath, ValidationConfiguration validationConfiguration);
}
