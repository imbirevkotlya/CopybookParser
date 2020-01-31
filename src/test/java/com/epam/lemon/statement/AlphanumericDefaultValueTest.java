package com.epam.lemon.statement;

import com.epam.lemon.exception.InvalidDefaultValueException;
import org.junit.Assert;
import org.junit.Test;

public class AlphanumericDefaultValueTest {

    private static final String FIELD_NAME = "NAME";
    private static final int FIELD_LENGTH = 5;
    private static final int FIELD_LEVEL = 1;

    @Test
    public void initializeStatementWithEmptyDefaultValue() {
        RegularDataDeclarationCobolStatement statement =
                new AlphanumericDeclarationCobolStatement(FIELD_LEVEL, FIELD_LENGTH, FIELD_NAME);

        String defaultValue = statement.getDefaultValue();

        String emptyAlphanumericDefaultValue = "     ";
        Assert.assertEquals(emptyAlphanumericDefaultValue, defaultValue);
    }

    @Test
    public void initializeStatementWithDefaultValue() {
        String expectedDefaultValue = "AAAAA";
        RegularDataDeclarationCobolStatement statement =
                new AlphanumericDeclarationCobolStatement(FIELD_LEVEL, FIELD_LENGTH, FIELD_NAME, expectedDefaultValue);

        String defaultValue = statement.getDefaultValue();

        Assert.assertEquals(expectedDefaultValue, defaultValue);
    }

    @Test
    public void initializeStatementWithSmallDefaultValue() {
        String trailingSpaces = "   ";
        String smallDefaultValue = "AA";
        RegularDataDeclarationCobolStatement statement =
                new AlphanumericDeclarationCobolStatement(FIELD_LEVEL, FIELD_LENGTH, FIELD_NAME, smallDefaultValue);

        String defaultValue = statement.getDefaultValue();

        Assert.assertEquals(trailingSpaces + smallDefaultValue, defaultValue);
    }

    @Test(expected = InvalidDefaultValueException.class)
    public void initializeStatementWithTooBigDefaultValue() {
        String tooBigDefaultValue = "AAAAAAAAAAA";

        new AlphanumericDeclarationCobolStatement(FIELD_LEVEL, FIELD_LENGTH, FIELD_NAME, tooBigDefaultValue);
    }
}
