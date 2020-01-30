package com.epam.lemon.parser.statement;

import com.epam.lemon.statement.AlphanumericDeclarationCobolStatement;
import com.epam.lemon.statement.DataDeclarationCobolStatement;

import java.util.function.Function;

public class AlphanumericDefaultValueStatementParser extends AlphanumericStatementParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;
    private static final int LENGTH_DECLARATION = 3;
    private static final int DEFAULT_VALUE_DECLARATION = 5;

    private static final String DEFAULT_VALUE_KEYWORD_PATTERN = "VALUE";
    private static final String DEFAULT_VALUE_PATTERN = "'([^' ]*)'";

    private static final int DEFAULT_VALUE_KEYWORD = 0;
    private static final int DEFAULT_VALUE = 1;

    private static final String EMPTY_STRING = "";
    private static final String SINGLE_QUOTE = "'";

    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] necessaryStatementAttributeFormats = super.getNecessaryStatementAttributeFormats();
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

    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new AlphanumericDeclarationCobolStatement(
                Integer.parseInt(statementAttributes[LEVEL]),
                statementAttributes[LENGTH_DECLARATION].length(),
                statementAttributes[NAME],
                statementAttributes[DEFAULT_VALUE_DECLARATION].replaceAll(SINGLE_QUOTE, EMPTY_STRING)
        );
    }
}
