package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class DelimitedListValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        String[] delimitedValues = input.split(Pattern.quote(fieldValidation.getListDelimiterRegex()));
        if(delimitedValues.length >= fieldValidation.getMinimumDelimitedValues())
            return "";
        else
            return String.format("The field value [%s] is not a valid delimited list value.", input);
    }
}
