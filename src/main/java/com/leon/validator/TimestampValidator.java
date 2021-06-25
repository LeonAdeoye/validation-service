package com.leon.validator;

import com.leon.model.FieldValidation;

public class TimestampValidator implements Validator
{
    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        return fieldValidation.getType().equalsIgnoreCase(("TIMESTAMP"));
    }
}
