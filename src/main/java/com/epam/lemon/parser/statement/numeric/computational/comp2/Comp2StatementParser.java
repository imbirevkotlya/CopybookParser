package com.epam.lemon.parser.statement.numeric.computational.comp2;

import com.epam.lemon.parser.statement.numeric.computational.ComputationalStatementParser;

/**
 * COBOL computational statement contains 5 attributes: level,name, pic keyword, digit length declaration, comp keyword
 *
 * Example:
 *
 * 01 COMP-NAME PIC 99 COMP-2.
 *
 */
public class Comp2StatementParser extends ComputationalStatementParser {

    private static final String COMP_KEYWORD_PATTERN = "COMP-2";

    @Override
    protected String getComputationalKeywordPattern() {
        return COMP_KEYWORD_PATTERN;
    }
}
