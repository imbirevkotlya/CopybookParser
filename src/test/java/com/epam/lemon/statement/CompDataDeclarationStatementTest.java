package com.epam.lemon.statement;

import com.epam.lemon.exception.InvalidDefaultValueException;
import org.junit.Assert;
import org.junit.Test;


public class CompDataDeclarationStatementTest {

    private static final String FIELD_NAME = "NAME";
    private static final int FIELD_LENGTH = 5;
    private static final int FIELD_LEVEL = 1;

    @Test
    public void initializeStatementWithEmptyDefaultValue() {
        RegularDataDeclarationCobolStatement statement =
                new CompDataDeclarationStatement(FIELD_LEVEL, FIELD_LENGTH, FIELD_NAME);

        String defaultValue = statement.getDefaultValue();

        String emptyDefaultValue = "00000";
        Assert.assertEquals(emptyDefaultValue, defaultValue);
    }

    @Test
    public void initializeStatementWithDefaultValue() {
        Integer expectedDefaultValue = 12345;
        RegularDataDeclarationCobolStatement statement =
                new CompDataDeclarationStatement(FIELD_LEVEL, FIELD_LENGTH, FIELD_NAME, expectedDefaultValue);

        String defaultValue = statement.getDefaultValue();

        Assert.assertEquals(expectedDefaultValue.toString(), defaultValue);
    }

    @Test
    public void initializeStatementWithSmallDefaultValue() {
        Integer smallDefaultValue = 123;
        RegularDataDeclarationCobolStatement statement =
                new CompDataDeclarationStatement(FIELD_LEVEL, FIELD_LENGTH, FIELD_NAME, smallDefaultValue);

        String defaultValue = statement.getDefaultValue();

        String trailingZeroes = "00";
        Assert.assertEquals(trailingZeroes + smallDefaultValue.toString(), defaultValue);
    }

    @Test(expected = InvalidDefaultValueException.class)
    public void initializeStatementWithTooBigDefaultValue() {
        Integer expectedDefaultValue = 123456;
        new CompDataDeclarationStatement(FIELD_LEVEL, FIELD_LENGTH, FIELD_NAME, expectedDefaultValue);
    }
}
