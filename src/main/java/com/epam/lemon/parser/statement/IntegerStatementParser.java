package com.epam.lemon.parser.statement;

import com.epam.lemon.parser.CopybookParser;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.IntegerDeclarationCobolStatement;

import java.util.function.Function;

/**
 * COBOL integer statement contains only 4 attributes: level, name, PIC keyword, length declaration (only 9 are supported)
 *
 * Example:
 *
 * 01 NAME PIC 999.
 *
 * NOTICE:
 * Not supported example:
 *
 * 01 NAME PIC 9(3).
 *
 * Should be:
 *
 * 01 NAME PIC 999.
 *
 */
public class IntegerStatementParser extends AbstractStatementParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;
    private static final int VALUE_DECLARATION_KEYWORD = 2;
    private static final int LENGTH_DECLARATION = 3;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] integerStatementAttributes = new String[4];
        integerStatementAttributes[LEVEL] = LEVEL_PATTERN;
        integerStatementAttributes[NAME] = NAME_PATTERN;
        integerStatementAttributes[VALUE_DECLARATION_KEYWORD] = CopybookParser.VALUE_DECLARATION_KEYWORD;
        integerStatementAttributes[LENGTH_DECLARATION] = "^[9]*$";
        return integerStatementAttributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getDefaultValueStatementAttributeFormats() {
        String[] defaultValuePattern = new String[2];
        defaultValuePattern[0] = "VALUE";
        defaultValuePattern[1] = "[0-9]";
        return defaultValuePattern;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> {
            if (statementAttributes.length == getNecessaryStatementAttributeFormats().length) {
                return new IntegerDeclarationCobolStatement(
                        Integer.parseInt(statementAttributes[LEVEL]),
                        statementAttributes[LENGTH_DECLARATION].length(),
                        statementAttributes[NAME]
                );
            }
            else {
                return new IntegerDeclarationCobolStatement(
                        Integer.parseInt(statementAttributes[LEVEL]),
                        statementAttributes[LENGTH_DECLARATION].length(),
                        statementAttributes[NAME],
                        Integer.parseInt(statementAttributes[5])
                );
            }
        };
    }
}
