package com.leon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties
public class ValidationConfiguration
{
    @JsonProperty("delimiter")
    private String delimiter = ",";
    @JsonProperty("rowStart")
    private int rowStart = 0;
    @JsonProperty("duplicatesAllowed")
    private boolean duplicatesAllowed = false;
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

    public boolean areDuplicatesAllowed()
    {
        return duplicatesAllowed;
    }

    public void setDuplicatesAllowed(boolean duplicatesAllowed)
    {
        this.duplicatesAllowed = duplicatesAllowed;
    }

    @Override
    public String toString()
    {
        return "ValidationConfiguration{" +
                "delimiter='" + delimiter + '\'' +
                ", rowStart=" + rowStart +
                ", duplicatesAllowed=" + duplicatesAllowed +
                ", listOfFieldValidations=" + listOfFieldValidations +
                '}';
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ValidationConfiguration that = (ValidationConfiguration) o;
        return getRowStart() == that.getRowStart() &&
                areDuplicatesAllowed() == that.areDuplicatesAllowed() &&
                getDelimiter().equals(that.getDelimiter()) &&
                getListOfFieldValidations().equals(that.getListOfFieldValidations());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getDelimiter(), getRowStart(), areDuplicatesAllowed(), getListOfFieldValidations());
    }
}
