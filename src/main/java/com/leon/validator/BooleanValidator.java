package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;

@Component
public class BooleanValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        if(input.equalsIgnoreCase("TRUE") || input.equalsIgnoreCase("FALSE"))
            return "";
        else
            return String.format("The field value [%s] is not a valid boolean.", input);
    }
}
