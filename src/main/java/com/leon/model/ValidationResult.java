package com.leon.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum ResultType
{
    SUCCESS,
    FAILURE
}

public class ValidationResult
{
    private List<String> errors;
    private ResultType result;

    public ValidationResult()
    {
        errors = new ArrayList<>();
    }

    public void addError(String error)
    {
        errors.add(error);
    }

    public List<String> getErrors()
    {
        return errors;
    }

    public void concatenateErrors(List<String> errors)
    {
        this.errors.addAll(errors);
    }

    @Override
    public String toString()
    {
        if(errors.size() != 0)
            result = ResultType.FAILURE;
        else
            result = ResultType.SUCCESS;

        StringBuilder sb = new StringBuilder("{\"status\" : \"");
        sb.append(result);
        sb.append("\", \"errors\" : ");
        sb.append(Arrays.toString(errors.toArray()));
        sb.append("}");
        System.out.println("sb: " + sb.toString());
        return sb.toString();
    }
}
