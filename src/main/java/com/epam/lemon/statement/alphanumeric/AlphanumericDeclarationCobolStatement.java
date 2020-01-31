package com.epam.lemon.statement.alphanumeric;

import com.epam.lemon.exception.InvalidDefaultValueException;
import com.epam.lemon.statement.RegularDataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;

import java.util.Objects;

/**
 * Model represents the cobol alphanumeric statements.
 *
 * Description can be found here - https://www.mainframestechhelp.com/tutorials/cobol/cobol-alphanumeric-data-type.htm
 */
public class AlphanumericDeclarationCobolStatement implements RegularDataDeclarationCobolStatement {

    private final Integer level;
    private final Integer length;
    private final String name;
    private final String defaultValue;

    /**
     * Main statement constructor with statement based default value (" ").
     * @param level is a statement level
     * @param length is a field length
     * @param name is a field length
     */
    public AlphanumericDeclarationCobolStatement(Integer level, Integer length, String name) {
        this.level = level;
        this.length = length;
        this.name = name;
        defaultValue = initializeEmptyValue(this.length);
    }

    private String initializeEmptyValue(Integer length) {
        char[] defaultValue = new char[length];
        fillWithSpaces(defaultValue);
        return new String(defaultValue);
    }

    private void fillWithSpaces(char[] defaultValue) {
        char space = ' ';
        for (int i = 0; i < defaultValue.length; i++) {
            defaultValue[i] = space;
        }
    }

    /**
     * Main statement constructor.
     * @param level is a statement level
     * @param length is a field length
     * @param name is a field length
     * @param defaultValue is a field default value
     */
    public AlphanumericDeclarationCobolStatement(Integer level, Integer length, String name, String defaultValue) {
        this.level = level;
        this.length = length;
        this.name = name;
        validateDefaultValueSize(length, defaultValue);
        this.defaultValue = initializeDefaultValue(defaultValue);
    }

    private void validateDefaultValueSize(Integer length, String defaultValue) {
        if (defaultValue.length() > length) {
            throw new InvalidDefaultValueException();
        }
    }

    private String initializeDefaultValue(String defaultValue) {
        String trailingSpaces = initializeEmptyValue(this.length - defaultValue.length());
        return trailingSpaces + defaultValue;
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
        return StatementType.ALPHANUMERIC_STATEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlphanumericDeclarationCobolStatement that = (AlphanumericDeclarationCobolStatement) o;
        return level.equals(that.level) &&
                length.equals(that.length) &&
                name.equals(that.name) &&
                defaultValue.equals(that.defaultValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(level, length, name, defaultValue);
    }
}
