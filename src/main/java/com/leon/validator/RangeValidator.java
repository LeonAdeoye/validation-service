package com.leon.validator;

import com.leon.model.FieldValidation;

public class RangeValidator implements Validator
{
    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        String range = fieldValidation.getValidRange();

        return false;
    }
}
