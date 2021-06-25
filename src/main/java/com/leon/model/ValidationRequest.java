package com.leon.model;

public class ValidationRequest
{
    private String filePath;
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
}
