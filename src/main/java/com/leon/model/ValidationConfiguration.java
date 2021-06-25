package com.leon.model;

import java.util.List;

public class ValidationConfiguration
{
    private char delimiter;
    private int rowStart;
    private List<FieldValidation> listOfFieldValidations;

    public char getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(char delimiter)
    {
        this.delimiter = delimiter;
    }

    public int getRowStart()
    {
        return rowStart;
    }

    public void setRowStart(int rowStart)
    {
        this.rowStart = rowStart;
    }

    public List<FieldValidation> getListOfFieldValidations()
    {
        return listOfFieldValidations;
    }

    public void setListOfFieldValidations(List<FieldValidation> listOfFieldValidations)
    {
        this.listOfFieldValidations = listOfFieldValidations;
    }
}
