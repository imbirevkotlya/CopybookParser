package com.epam.lemon.parser.statement;

/**
 * Class represents the parsing mechanism for the comp-1 default statements (simple wrapper under the usual
 * comp-1 parser).
 */
public class Comp1DefaultValueStatementParser extends ComputationalDefaultValueStatementParser {
    /**
     * Main wrapper constructor with DI for main comp-1 parser.
     *
     * @param compStatementParser is a main implementation of comp-1 parser
     */
    public Comp1DefaultValueStatementParser(Comp1StatementParser compStatementParser) {
        super(compStatementParser);
    }
}
