package com.leon.validator;

import com.leon.model.FieldValidation;

public interface Validator
{
    boolean validate(String input, FieldValidation fieldValidation);
}
