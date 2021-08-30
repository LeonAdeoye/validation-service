package com.leon.validator;

public class ValidatorFactory
{
    public Validator create(ValidatorType validatorType)
    {
        switch(validatorType)
        {
            case INTEGER:
                return new IntegerValidator();
            case DOUBLE:
                return new DoubleValidator();
            case STRING:
                return new StringValidator();
            case CURRENCY:
                return new CurrencyValidator();
            case BOOLEAN:
                return new BooleanValidator();
            case REGEX:
                return new RegexValidator();
            case DELIMITED:
                return new DelimitedListValidator();
            case TIMESTAMP:
                return new TimestampValidator();
            case RANGE:
                return new RangeValidator();
            case ENUMERATED:
                return new EnumeratedTypeValidator();
            default:
                return null;
        }
    }
}
