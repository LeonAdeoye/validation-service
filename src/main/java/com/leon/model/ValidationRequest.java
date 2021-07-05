package com.leon.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ValidationRequest
{
    @JsonProperty("filePath")
    private String filePath;

    @JsonProperty("configuration")
    private ValidationConfiguration validationConfiguration;

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public ValidationConfiguration getValidationConfiguration()
    {
        return validationConfiguration;
    }

    public void setValidationConfiguration(ValidationConfiguration validationConfiguration)
    {
        this.validationConfiguration = validationConfiguration;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ValidationRequest that = (ValidationRequest) o;
        return Objects.equals(getFilePath(), that.getFilePath()) &&
                Objects.equals(getValidationConfiguration(), that.getValidationConfiguration());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getFilePath(), getValidationConfiguration());
    }
}
