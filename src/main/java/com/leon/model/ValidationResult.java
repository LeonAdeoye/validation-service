package com.leon.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum ResultType
{
    SUCCESS,
    ERROR
}

public class ValidationResult
{
    private List<String> errors;
    private ResultType result;

    public ValidationResult()
    {
        errors = new ArrayList<>();
    }
    public ValidationResult(String error)
    {
        errors = new ArrayList<>();
        errors.add(error);
    }
    public ValidationResult(List<String> errors)
    {
        this.errors = new ArrayList<>(errors);
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

    public String toJSON()
    {
        result = errors.isEmpty() ? ResultType.SUCCESS : ResultType.ERROR;
        StringBuilder sb = new StringBuilder("{\"status\" : \"");
        sb.append(result);
        sb.append("\", \"errors\" : [");
        for(int index = 0; index < errors.size(); ++index)
        {
            sb.append("\"");
            sb.append(errors.get(index));
            sb.append("\"");
            if(index < errors.size() - 1)
                sb.append(",");
        }
        sb.append("]}");
        return sb.toString();
    }
}
