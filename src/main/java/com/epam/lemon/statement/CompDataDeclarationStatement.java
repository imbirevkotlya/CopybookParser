package com.epam.lemon.statement;

import com.epam.lemon.exception.InvalidDefaultValueException;

import java.util.Formatter;
import java.util.Objects;

/**
 * The class represents COBOL computational statement.
 *
 * Description can be found here: https://www.mainframestechhelp.com/tutorials/cobol/cobol-computation.htm
 */
public class CompDataDeclarationStatement implements RegularDataDeclarationCobolStatement {

    private final Integer length;
    private final Integer level;
    private final String name;
    private final String defaultValue;

    /**
     * Main statement constructor with statement based default value (0).
     * @param level is a statement level
     * @param length is a field length
     * @param name is a field length
     */
    public CompDataDeclarationStatement(Integer level, Integer length, String name) {
        this.length = length;
        this.level = level;
        this.name = name;
        defaultValue = initializeDefaultValue(0);
    }

    /**
     * Main statement constructor.
     * @param level is a statement level
     * @param length is a field length
     * @param name is a field length
     * @param defaultValue is a field default value
     */
    public CompDataDeclarationStatement(Integer level, Integer length, String name, Integer defaultValue) {
        this.length = length;
        this.level = level;
        this.name = name;
        validateDefaultValueSize(length, defaultValue);
        this.defaultValue = initializeDefaultValue(defaultValue);
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

    private void validateDefaultValueSize(Integer length, Integer defaultValue) {
        if (defaultValue.toString().length() > length) {
            throw new InvalidDefaultValueException();
        }
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
        return StatementType.COMP_STATEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompDataDeclarationStatement that = (CompDataDeclarationStatement) o;
        return length.equals(that.length) &&
                level.equals(that.level) &&
                name.equals(that.name) &&
                defaultValue.equals(that.defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(length, level, name, defaultValue);
    }
}
