package com.leon.validator;

import com.leon.model.FieldValidation;

public class DoubleValidator implements Validator
{
    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        return input.matches("/^[0-9]+(\\.[0-9]+)?$");
    }
}
