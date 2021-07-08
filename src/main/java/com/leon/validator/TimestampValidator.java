package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimestampValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        if(validateTimestamp(input, fieldValidation.getTimestampFormat()))
            return "";
        else
            return String.format("The field value [%s] does not match date timestamp value format.", input);
    }

    private boolean validateTimestamp(String input, String format)
    {
        try
        {
            SimpleDateFormat simpleDateFormatter = new SimpleDateFormat(format);
            Date formattedDate = simpleDateFormatter.parse(input);
            String formattedDateString = simpleDateFormatter.format(formattedDate);
            return formattedDateString.equals(input);
        }
        catch(ParseException pe)
        {
            return false;
        }
    }
}
