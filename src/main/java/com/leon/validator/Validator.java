package com.leon.validator;

import com.leon.model.FieldValidation;

public interface Validator
{
    String validate(String input, FieldValidation fieldValidation);
}
