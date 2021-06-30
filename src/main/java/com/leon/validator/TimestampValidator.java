package com.leon.validator;

import com.leon.model.FieldValidation;

import java.text.SimpleDateFormat;

public class TimestampValidator implements Validator
{
    @Override
    public String validate(String input, FieldValidation fieldValidation)
    {
        SimpleDateFormat format = new SimpleDateFormat(fieldValidation.getDataFormat());
        return "";
    }
}
