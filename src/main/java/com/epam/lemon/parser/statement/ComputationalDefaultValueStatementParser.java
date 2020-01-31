package com.epam.lemon.parser.statement;

import com.epam.lemon.statement.CompDataDeclarationStatement;
import com.epam.lemon.statement.DataDeclarationCobolStatement;

import java.util.function.Function;

/**
 * Class represents the abstract computational statement with default value parser. The extended class should
 * override the constructor with the specified basic computational parser implementation.
 * Example:
 * public class Comp3DefaultValueStatementParser extends ComputationalDefaultValueStatementParser {
 *     public Comp3DefaultValueStatementParser(Comp3StatementParser compStatementParser) {
 *         super(compStatementParser);
 *     }
 * }
 *
 */
public abstract class ComputationalDefaultValueStatementParser extends AbstractStatementParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;
    private static final int LENGTH_DECLARATION = 3;
    private static final int DEFAULT_VALUE_DECLARATION = 6;

    private static final String DEFAULT_VALUE_KEYWORD_PATTERN = "VALUE";
    private static final String DEFAULT_VALUE_PATTERN = "[0-9]+";

    private static final int DEFAULT_VALUE_KEYWORD = 0;
    private static final int DEFAULT_VALUE = 1;

    private final ComputationalStatementParser compStatementParser;

    /**
     * Main wrapper constructor with DI for main comp parser.
     * @param compStatementParser is a main implementation of comp parser
     */
    public ComputationalDefaultValueStatementParser(ComputationalStatementParser compStatementParser) {
        this.compStatementParser = compStatementParser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] necessaryStatementAttributeFormats = compStatementParser.getNecessaryStatementAttributeFormats();
        String[] defaultValueStatementAttributeFormats = getDefaultStatementAttributeFormats();
        ArrayMerger arrayMerger = new ArrayMerger(necessaryStatementAttributeFormats, defaultValueStatementAttributeFormats);
        return arrayMerger.merge();
    }

    private String[] getDefaultStatementAttributeFormats() {
        String[] defaultValueStatementAttributeFormats = new String[2];
        defaultValueStatementAttributeFormats[DEFAULT_VALUE_KEYWORD] = DEFAULT_VALUE_KEYWORD_PATTERN;
        defaultValueStatementAttributeFormats[DEFAULT_VALUE] = DEFAULT_VALUE_PATTERN;
        return defaultValueStatementAttributeFormats;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new CompDataDeclarationStatement(
                Integer.parseInt(statementAttributes[LEVEL]),
                statementAttributes[LENGTH_DECLARATION].length(),
                statementAttributes[NAME],
                Integer.parseInt(statementAttributes[DEFAULT_VALUE_DECLARATION])
        );
    }
}
