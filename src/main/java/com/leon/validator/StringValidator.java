package com.leon.validator;

import com.leon.model.FieldValidation;

import java.util.regex.Pattern;

public class StringValidator implements Validator
{
    private Pattern uppercasePattern = Pattern.compile("^[0-9A-Z\\W]*$");
    private Pattern lowercasePattern = Pattern.compile("^[0-9a-z\\W]*$");

    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        String format = fieldValidation.getStringFormat();
        if(format == null || format.isEmpty())
            return "";

        switch(format.toUpperCase())
        {
            case "UPPERCASE":
                if(uppercasePattern.matcher(input).matches())
                    return "";
            case "LOWERCASE":
                if(lowercasePattern.matcher(input).matches())
                    return "";
        }
        return String.format("The field value [%s] is not a valid string.", input);
    }
}
