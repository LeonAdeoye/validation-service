package com.leon.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Objects;

@JsonIgnoreProperties
public class FieldValidation
{
    @JsonProperty("id")
    private int id;
    @JsonProperty("description")
    private String description;
    @JsonProperty("type")
    private String type;
    @JsonProperty("timestampFormat")
    private String timestampFormat;
    @JsonProperty("canBeEmpty")
    private boolean canBeEmpty = true;
    @JsonProperty("enumeratedTypeValues")
    private String[] enumeratedTypeValues;
    @JsonProperty("validRegex")
    private String validRegex;
    @JsonProperty("stringFormat")
    private String stringFormat;
    @JsonProperty("maxDecimals")
    private int maxDecimals;
    @JsonProperty("listDelimiterRegex")
    private String listDelimiterRegex;
    @JsonProperty("minimumDelimitedValues")
    private int minimumDelimitedValues;
    @JsonProperty("validRange")
    private String validRange;

    public String getValidRange()
    {
        return validRange;
    }

    public void setValidRange(String validRange)
    {
        this.validRange = validRange;
    }

    public int getMinimumDelimitedValues()
    {
        return minimumDelimitedValues;
    }

    public void setMinimumDelimitedValues(int minimumDelimitedValues)
    {
        this.minimumDelimitedValues = minimumDelimitedValues;
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

    public String getTimestampFormat()
    {
        return timestampFormat;
    }

    public void setTimestampFormat(String timestampFormat)
    {
        this.timestampFormat = timestampFormat;
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

    public String[] getEnumeratedTypeValues()
    {
        return enumeratedTypeValues;
    }

    public void setEnumeratedTypeValues(String[] enumeratedTypeValues)
    {
        this.enumeratedTypeValues = enumeratedTypeValues;
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

    public String getListDelimiterRegex()
    {
        return listDelimiterRegex;
    }

    public void setListDelimiterRegex(String listDelimiterRegex)
    {
        this.listDelimiterRegex = listDelimiterRegex;
    }

    @Override
    public boolean equals(Object o)
    {
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        FieldValidation that = (FieldValidation) o;
        return getId() == that.getId() &&
                canBeEmpty == that.canBeEmpty &&
                getMaxDecimals() == that.getMaxDecimals() &&
                getMinimumDelimitedValues() == that.getMinimumDelimitedValues() &&
                Objects.equals(getDescription(), that.getDescription()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getTimestampFormat(), that.getTimestampFormat()) &&
                Arrays.equals(getEnumeratedTypeValues(), that.getEnumeratedTypeValues()) &&
                Objects.equals(getValidRegex(), that.getValidRegex()) &&
                Objects.equals(getStringFormat(), that.getStringFormat()) &&
                Objects.equals(getListDelimiterRegex(), that.getListDelimiterRegex()) &&
                Objects.equals(getValidRange(), that.getValidRange());
    }

    @Override
    public int hashCode()
    {
        int result = Objects.hash(getId(), getDescription(), getType(), getTimestampFormat(), canBeEmpty, getValidRegex(), getStringFormat(), getMaxDecimals(), getListDelimiterRegex(), getMinimumDelimitedValues(), getValidRange());
        result = 31 * result + Arrays.hashCode(getEnumeratedTypeValues());
        return result;
    }

    @Override
    public String toString()
    {
        return "FieldValidation{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", timestampFormat='" + timestampFormat + '\'' +
                ", canBeEmpty=" + canBeEmpty +
                ", enumeratedTypeValues=" + Arrays.toString(enumeratedTypeValues) +
                ", validRegex='" + validRegex + '\'' +
                ", stringFormat='" + stringFormat + '\'' +
                ", maxDecimals=" + maxDecimals +
                ", listDelimiterRegex='" + listDelimiterRegex + '\'' +
                ", minimumDelimitedValues=" + minimumDelimitedValues +
                ", validRange='" + validRange + '\'' +
                '}';
    }
}
