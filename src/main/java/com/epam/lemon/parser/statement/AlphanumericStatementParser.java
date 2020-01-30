package com.epam.lemon.parser.statement;

import com.epam.lemon.parser.CopybookParser;
import com.epam.lemon.statement.AlphanumericDeclarationCobolStatement;
import com.epam.lemon.statement.DataDeclarationCobolStatement;

import java.util.function.Function;

/**
 * COBOL alphanumeric statement contains only 4 attributes: level, name, PIC keyword, length declaration (only X are supported)
 *
 * Example:
 *
 * 01 NAME PIC XXX.
 *
 * NOTICE:
 * Not supported example:
 *
 * 01 NAME PIC X(3).
 *
 * Should be:
 *
 * 01 NAME PIC XXX.
 *
 */
public class AlphanumericStatementParser extends AbstractStatementParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;
    private static final int VALUE_DECLARATION_KEYWORD = 2;
    private static final int LENGTH_DECLARATION = 3;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] alphanumericStatementAttributes = new String[4];
        alphanumericStatementAttributes[LEVEL] = LEVEL_PATTERN;
        alphanumericStatementAttributes[NAME] = NAME_PATTERN;
        alphanumericStatementAttributes[VALUE_DECLARATION_KEYWORD] = CopybookParser.VALUE_DECLARATION_KEYWORD;
        alphanumericStatementAttributes[LENGTH_DECLARATION] = "^[X]*$";
        return alphanumericStatementAttributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getDefaultValueStatementAttributeFormats() {
        String[] defaultValuePattern = new String[2];
        defaultValuePattern[0] = "VALUE";
        defaultValuePattern[1] = "'([^' ]*)'";
        return defaultValuePattern;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> {
            if (getNecessaryStatementAttributeFormats().length == statementAttributes.length) {
                return new AlphanumericDeclarationCobolStatement(
                        Integer.parseInt(statementAttributes[LEVEL]),
                        statementAttributes[LENGTH_DECLARATION].length(),
                        statementAttributes[NAME]
                );
            }
            else {
                return new AlphanumericDeclarationCobolStatement(
                        Integer.parseInt(statementAttributes[LEVEL]),
                        statementAttributes[LENGTH_DECLARATION].length(),
                        statementAttributes[NAME],
                        statementAttributes[5].replaceAll("'", "")
                );
            }
        };
    }
}
