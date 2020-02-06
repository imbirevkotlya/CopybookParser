package com.epam.lemon.parser.statement.alphanumeric;

import com.epam.lemon.parser.statement.AbstractStatementParser;
import com.epam.lemon.statement.alphanumeric.AlphanumericDeclarationCobolStatement;
import com.epam.lemon.statement.DataDeclarationCobolStatement;

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

    private static final String LENGTH_DECLARATION_PATTERN = "^[X]*$";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] alphanumericStatementAttributes = new String[4];
        alphanumericStatementAttributes[LEVEL] = LEVEL_PATTERN;
        alphanumericStatementAttributes[NAME] = NAME_PATTERN;
        alphanumericStatementAttributes[VALUE_DECLARATION_KEYWORD] = AbstractStatementParser.VALUE_DECLARATION_KEYWORD;
        alphanumericStatementAttributes[LENGTH_DECLARATION] = LENGTH_DECLARATION_PATTERN;
        return alphanumericStatementAttributes;
    }

    @Override
    protected DataDeclarationCobolStatement parseMatchedStatement(String[] statementAttributes) {
        return new AlphanumericDeclarationCobolStatement(
            Integer.parseInt(statementAttributes[LEVEL]),
            statementAttributes[LENGTH_DECLARATION].length(),
            statementAttributes[NAME]
        );
    }

}
