package com.leon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties
public class ValidationConfiguration
{
    @JsonProperty("delimiter")
    private String delimiter = ",";
    @JsonProperty("rowStart")
    private int rowStart = 0;
    @JsonProperty("listOfFieldValidations")
    private List<FieldValidation> listOfFieldValidations;

    public ValidationConfiguration() {}

    public String getDelimiter()
    {
        return delimiter;
    }

    public void setDelimiter(String delimiter)
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

    @Override
    public String toString()
    {
        return "ValidationConfiguration{" +
                "delimiter=" + delimiter +
                ", rowStart=" + rowStart +
                ", listOfFieldValidations=" + listOfFieldValidations +
                '}';
    }
}
