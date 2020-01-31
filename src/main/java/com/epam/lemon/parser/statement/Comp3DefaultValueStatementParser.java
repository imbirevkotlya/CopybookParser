package com.epam.lemon.parser.statement;

/**
 * Class represents the parsing mechanism for the comp-3 default statements (simple wrapper under the usual
 * comp-3 parser).
 */
public class Comp3DefaultValueStatementParser extends ComputationalDefaultValueStatementParser {
    /**
     * Main wrapper constructor with DI for main comp-3 parser.
     *
     * @param compStatementParser is a main implementation of comp-3 parser
     */
    public Comp3DefaultValueStatementParser(Comp3StatementParser compStatementParser) {
        super(compStatementParser);
    }
}
