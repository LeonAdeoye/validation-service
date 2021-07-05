package com.leon.model;

import java.util.Arrays;

public class DataRow
{
    private int rowNumber;
    private String[] rowValues;

    public int getRowNumber()
    {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber)
    {
        this.rowNumber = rowNumber;
    }

    public String[] getRowValues()
    {
        return rowValues;
    }

    public void setRowValues(String[] rowValues)
    {
        this.rowValues = rowValues;
    }

    public DataRow(int rowNumber, String[] rowValues)
    {
        this.rowNumber = rowNumber;
        this.rowValues = rowValues;
    }

    @Override
    public String toString()
    {
        return "DataRow{" +
                "rowNumber=" + rowNumber +
                ", rowValues=" + Arrays.toString(rowValues) +
                '}';
    }
}
