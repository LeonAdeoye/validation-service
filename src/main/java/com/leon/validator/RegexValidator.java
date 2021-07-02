package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class RegexValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        Pattern regexPattern = Pattern.compile(fieldValidation.getValidRegex());

        if(regexPattern.matcher(input).matches())
            return "";
        else
            return String.format("The field value [%s] does not match the regex expression: %s.", input, fieldValidation.getValidRegex());
    }
}
