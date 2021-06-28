package com.leon.validator;

import com.leon.model.FieldValidation;

public class DelimitedValidator implements Validator
{
    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        return input.split(fieldValidation.getListDelimiter()).length > 0;
    }
}
