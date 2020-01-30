package com.epam.lemon.statement;

import com.epam.lemon.exception.InvalidDefaultValueException;

import java.util.Formatter;
import java.util.Objects;

/**
 * Model represents the cobol integer numeric statements.
 *
 * Description can be found here - https://www.mainframestechhelp.com/tutorials/cobol/cobol-numeric-data-type.htm
 *
 * NOTICE:
 *
 * No decimal fields are supported for now!
 */
public class IntegerDeclarationCobolStatement implements RegularDataDeclarationCobolStatement {

    private final Integer level;
    private final Integer length;
    private final String name;
    private final String defaultValue;

    /**
     * Main statement constructor.
     * @param level is a statement level
     * @param length is a field length
     * @param name is a field length
     * @param defaultValue is a field default value
     */
    public IntegerDeclarationCobolStatement(Integer level, Integer length, String name, Integer defaultValue) {
        this.level = level;
        this.length = length;
        this.name = name;
        validateTheDefaultValueSize(length, defaultValue);
        this.defaultValue = initializeDefaultValue(defaultValue);
    }

    private void validateTheDefaultValueSize(Integer fieldLength, Integer defaultValue) {
        if (defaultValue.toString().length() > fieldLength) {
            throw new InvalidDefaultValueException();
        }
    }

    /**
     * The method to return mainframe based default value (with trailing zeroes).
     * %% --> %
     * 0  --> 0
     * %d --> length
     * d  --> defaultValue
     * @param defaultValue is a raw java default value
     * @return is a mainframe based default value
     */
    private String initializeDefaultValue(Integer defaultValue) {
        String trailingZeroesFormat = new Formatter().format("%%0%dd", this.length).toString();
        return new Formatter().format(trailingZeroesFormat, defaultValue).toString();
    }

    /**
     * Main statement constructor with statement type default value specified (0).
     * @param level is a statement level
     * @param length is a field length
     * @param name is a field length
     */
    public IntegerDeclarationCobolStatement(Integer level, Integer length, String name) {
        this.level = level;
        this.length = length;
        this.name = name;
        defaultValue = initializeDefaultValue(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getLength() {
        return length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getLevel() {
        return level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatementType getStatementType() {
        return StatementType.INTEGER_STATEMENT;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IntegerDeclarationCobolStatement that = (IntegerDeclarationCobolStatement) o;
        return level.equals(that.level) &&
                length.equals(that.length) &&
                name.equals(that.name) &&
                defaultValue.equals(that.defaultValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, length, name, defaultValue);
    }
}
