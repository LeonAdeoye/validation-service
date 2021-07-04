package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;

@Component
public class DoubleValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        if(input.matches("^[0-9]+(\\.[0-9]+)?$"))
            return "";
        else
            return String.format("The field value [%s] is not a valid double value.", input);
    }
}
