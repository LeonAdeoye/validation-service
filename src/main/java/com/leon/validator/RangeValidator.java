package com.leon.validator;

import com.leon.model.FieldValidation;

public class RangeValidator implements Validator
{
    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        return fieldValidation.getType().equalsIgnoreCase(("RANGE"));
    }
}
