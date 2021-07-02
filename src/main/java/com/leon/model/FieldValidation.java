package com.leon.model;

public class FieldValidation
{
    private int id;
    private String description;
    private String type;
    private String dataFormat;
    private boolean canBeEmpty;
    private String[] validValues;
    private String validRegex;
    private String stringFormat;
    private int maxDecimals;
    private String listDelimiter;
    private String validRange;

    public String getValidRange()
    {
        return validRange;
    }

    public void setValidRange(String validRange)
    {
        this.validRange = validRange;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getDataFormat()
    {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat)
    {
        this.dataFormat = dataFormat;
    }

    public boolean canBeEmpty()
    {
        return canBeEmpty;
    }

    public boolean cannotBeEmpty()
    {
        return !canBeEmpty;
    }

    public void setCanBeEmpty(boolean canBeEmpty)
    {
        this.canBeEmpty = canBeEmpty;
    }

    public String[] getValidValues()
    {
        return validValues;
    }

    public void setValidValues(String[] validValues)
    {
        this.validValues = validValues;
    }

    public String getValidRegex()
    {
        return validRegex;
    }

    public void setValidRegex(String validRegex)
    {
        this.validRegex = validRegex;
    }

    public String getStringFormat()
    {
        return stringFormat;
    }

    public void setStringFormat(String stringFormat)
    {
        this.stringFormat = stringFormat;
    }

    public int getMaxDecimals()
    {
        return maxDecimals;
    }

    public void setMaxDecimals(int maxDecimals)
    {
        this.maxDecimals = maxDecimals;
    }

    public String getListDelimiter()
    {
        return listDelimiter;
    }

    public void setListDelimiter(String listDelimiter)
    {
        this.listDelimiter = listDelimiter;
    }
}
