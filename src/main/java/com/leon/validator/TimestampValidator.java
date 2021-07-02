package com.leon.validator;

import com.leon.model.FieldValidation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TimestampValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        if(validateTimestamp(input, fieldValidation.getDateFormat()))
            return "";
        else
            return String.format("The field value [%s] does not match date timestamp value format.", input);
    }

    private boolean validateTimestamp(String input, String format)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime formattedDate = LocalDateTime.parse(input, formatter);
        String formattedDateString = formattedDate.format(formatter);
        return formattedDateString.equals(input);
    }
}
