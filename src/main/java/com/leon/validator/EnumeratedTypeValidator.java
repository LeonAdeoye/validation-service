package com.leon.validator;

import com.leon.model.FieldValidation;

public class EnumeratedTypeValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        if(true)
            return "";
        else
            return String.format("The field value [%s] is not one of the enumerated values", input);
    }
}
