package com.epam.lemon.parser.statement.numeric.computational.comp;

import com.epam.lemon.parser.statement.numeric.computational.ComputationalDefaultValueStatementParser;

/**
 * Class represents the parsing mechanism for the comp default statements (simple wrapper under the usual
 * comp parser).
 */
public class CompDefaultValueStatementParser extends ComputationalDefaultValueStatementParser {

    /**
     * Main wrapper constructor with DI for main comp parser.
     *
     * @param compStatementParser is a main implementation of comp parser
     */
    public CompDefaultValueStatementParser(CompStatementParser compStatementParser) {
        super(compStatementParser);
    }
}
