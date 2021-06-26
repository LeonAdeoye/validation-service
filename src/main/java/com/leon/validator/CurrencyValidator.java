package com.leon.validator;

import com.leon.model.FieldValidation;

public class CurrencyValidator implements Validator
{
    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        return fieldValidation.getType().equalsIgnoreCase(("CURRENCY")) && input.matches("[A-Z]{3}$");
    }
}
