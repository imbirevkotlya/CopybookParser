package com.epam.lemon.parser.statement.numeric;

import com.epam.lemon.parser.CopybookParser;
import com.epam.lemon.parser.statement.AbstractStatementParser;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.numeric.IntegerDeclarationCobolStatement;

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

    private static final String LENGTH_DECLARATION_PATTERN = "^[9]*$";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] integerStatementAttributes = new String[4];
        integerStatementAttributes[LEVEL] = LEVEL_PATTERN;
        integerStatementAttributes[NAME] = NAME_PATTERN;
        integerStatementAttributes[VALUE_DECLARATION_KEYWORD] = CopybookParser.VALUE_DECLARATION_KEYWORD;
        integerStatementAttributes[LENGTH_DECLARATION] = LENGTH_DECLARATION_PATTERN;
        return integerStatementAttributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new IntegerDeclarationCobolStatement(
                Integer.parseInt(statementAttributes[LEVEL]),
                statementAttributes[LENGTH_DECLARATION].length(),
                statementAttributes[NAME]
        );
    }
}
