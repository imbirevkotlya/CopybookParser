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
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new AlphanumericDeclarationCobolStatement(
                Integer.parseInt(statementAttributes[LEVEL]),
                statementAttributes[3].length(),
                statementAttributes[NAME]
        );
    }
}
