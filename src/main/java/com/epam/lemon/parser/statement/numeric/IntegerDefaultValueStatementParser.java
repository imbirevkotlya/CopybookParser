package com.epam.lemon.parser.statement.numeric;

import com.epam.lemon.parser.statement.AbstractStatementParser;
import com.epam.lemon.parser.statement.ArrayMerger;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.numeric.IntegerDeclarationCobolStatement;

import java.util.function.Function;

/**
 * Class represents the parsing mechanism for the integer default statements (simple wrapper under the usual
 * integer parser).
 */
public class IntegerDefaultValueStatementParser extends AbstractStatementParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;
    private static final int LENGTH_DECLARATION = 3;
    private static final int DEFAULT_VALUE_DECLARATION = 5;

    private static final String DEFAULT_VALUE_KEYWORD_PATTERN = "VALUE";
    private static final String DEFAULT_VALUE_PATTERN = "[0-9]";

    private static final int DEFAULT_VALUE_KEYWORD = 0;
    private static final int DEFAULT_VALUE = 1;

    private final IntegerStatementParser integerStatementParser;

    /**
     * Main wrapper constructor with DI for main integer parser.
     * @param integerStatementParser is a main implementation of integer parser
     */
    public IntegerDefaultValueStatementParser(IntegerStatementParser integerStatementParser) {
        this.integerStatementParser = integerStatementParser;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] necessaryStatementAttributeFormats = integerStatementParser.getNecessaryStatementAttributeFormats();
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
        return statementAttributes -> new IntegerDeclarationCobolStatement(
                Integer.parseInt(statementAttributes[LEVEL]),
                statementAttributes[LENGTH_DECLARATION].length(),
                statementAttributes[NAME],
                Integer.parseInt(statementAttributes[DEFAULT_VALUE_DECLARATION])
        );
    }
}
