package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public class EnumeratedTypeValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        if(Arrays.stream(fieldValidation.getEnumeratedTypeValues()).anyMatch(input::equals))
            return "";
        else
            return String.format("The field value [%s] is not one of the enumerated type values.", input);
    }
}
