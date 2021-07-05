package com.leon.validator;

import com.leon.model.FieldValidation;

public interface Validator
{
    String BOOLEAN = "BOOLEAN";
    String DOUBLE = "DOUBLE";
    String STRING = "STRING";
    String INTEGER = "INTEGER";
    String CURRENCY = "CURRENCY";
    String DELIMITED = "DELIMITED";
    String ENUMERATED = "ENUMERATED";
    String RANGE = "RANGE";
    String REGEX = "REGEX";
    String TIMESTAMP = "TIMESTAMP";

    String validate(String input, FieldValidation fieldValidation);
}
