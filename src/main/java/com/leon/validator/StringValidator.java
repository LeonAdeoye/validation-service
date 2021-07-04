package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
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
                else
                    return String.format("The field value [%s] is not a valid uppercase string.", input);
            case "LOWERCASE":
                if(lowercasePattern.matcher(input).matches())
                    return "";
                else
                    return String.format("The field value [%s] is not a valid lowercase string.", input);
        }
        return String.format("The field value [%s] is not a valid string.", input);
    }
}
