package com.leon.validator;

import com.leon.model.FieldValidation;

public class CurrencyValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        if(input.matches("[A-Z]{3}$"))
            return "";
        else
            return String.format("The field value [%s] is not a valid currency.", input);
    }
}
