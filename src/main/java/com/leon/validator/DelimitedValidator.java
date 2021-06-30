package com.leon.validator;

import com.leon.model.FieldValidation;

public class DelimitedValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        if(input.split(fieldValidation.getListDelimiter()).length > 0)
            return "";
        else
            return String.format("the field value [%s] is not a valid delimited value.", input);
    }
}
