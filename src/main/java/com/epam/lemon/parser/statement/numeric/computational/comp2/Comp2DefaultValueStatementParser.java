package com.epam.lemon.parser.statement.numeric.computational.comp2;

import com.epam.lemon.parser.statement.numeric.computational.ComputationalDefaultValueStatementParser;

/**
 * Class represents the parsing mechanism for the comp-2 default statements (simple wrapper under the usual
 * comp-2 parser).
 */
public class Comp2DefaultValueStatementParser extends ComputationalDefaultValueStatementParser {
    /**
     * Main wrapper constructor with DI for main comp-2 parser.
     *
     * @param compStatementParser is a main implementation of comp-2 parser
     */
    public Comp2DefaultValueStatementParser(Comp2StatementParser compStatementParser) {
        super(compStatementParser);
    }
}
