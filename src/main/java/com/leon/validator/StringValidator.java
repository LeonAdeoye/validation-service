package com.leon.validator;

import com.leon.model.FieldValidation;

import java.util.regex.Pattern;

public class StringValidator implements Validator
{
    private Pattern uppercasePattern = Pattern.compile("^[0-9A-Z\\W]*$");
    private Pattern lowercasePattern = Pattern.compile("^[0-9a-z\\W]*$");

    @Override
    public boolean validate(String input, FieldValidation fieldValidation)
    {
        String format = fieldValidation.getStringFormat();
        if(format == null || format.isEmpty())
            return true;

        switch(format.toUpperCase())
        {
            case "UPPERCASE":
                return uppercasePattern.matcher(input).matches();
            case "LOWERCASE":
                return lowercasePattern.matcher(input).matches();
            default:
                return false;
        }
    }
}
