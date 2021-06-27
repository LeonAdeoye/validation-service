package com.leon.validator;

import com.leon.model.FieldValidation;

public class BooleanValidator implements Validator
{
    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        return fieldValidation.getType().equalsIgnoreCase(("BOOLEAN")) &&
                (input.equalsIgnoreCase("TRUE") || input.equalsIgnoreCase("FALSE"));
    }
}