package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;

@Component
public class RangeValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        Double valueToCompare = Double.parseDouble(input);
        String[] limits = fieldValidation.getValidRange().split(",");
        double minimum = Double.parseDouble(limits[0]);
        double maximum = Double.parseDouble(limits[1]);
        if(valueToCompare >= minimum && valueToCompare <= maximum)
            return "";
        else
            return String.format("The field value [%s] is not within the ranges: %s and %s.", input, limits[0], limits[1]);
    }
}
