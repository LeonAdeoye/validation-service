package com.leon.validator;

import com.leon.model.FieldValidation;

public class IntegerValidator implements Validator
{
    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        return input.matches("/^[+-]?[0-9]+$");
    }
}
