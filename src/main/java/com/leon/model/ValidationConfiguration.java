package com.leon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties
public class ValidationConfiguration
{
    @JsonProperty("delimiter")
    private char delimiter = ',';
    @JsonProperty("rowStart")
    private int rowStart = 0;
    @JsonProperty("listOfFieldValidations")
    private List<FieldValidation> listOfFieldValidations;

    public ValidationConfiguration() {}

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
