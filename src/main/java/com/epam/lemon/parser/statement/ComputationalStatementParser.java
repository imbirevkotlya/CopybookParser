package com.epam.lemon.parser.statement;

import com.epam.lemon.parser.CopybookParser;
import com.epam.lemon.statement.CompDataDeclarationStatement;
import com.epam.lemon.statement.DataDeclarationCobolStatement;

import java.util.function.Function;

public abstract class ComputationalStatementParser extends AbstractStatementParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;
    private static final int VALUE_DECLARATION_KEYWORD = 2;
    private static final int LENGTH_DECLARATION = 3;
    private static final int COMPUTATION_DECLARATION = 4;

    private static final String LENGTH_DECLARATION_PATTERN = "^[9]*$";

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] compStatementAttributes = new String[5];
        compStatementAttributes[LEVEL] = LEVEL_PATTERN;
        compStatementAttributes[NAME] = NAME_PATTERN;
        compStatementAttributes[VALUE_DECLARATION_KEYWORD] = CopybookParser.VALUE_DECLARATION_KEYWORD;
        compStatementAttributes[LENGTH_DECLARATION] = LENGTH_DECLARATION_PATTERN;
        compStatementAttributes[COMPUTATION_DECLARATION] = getComputationalKeywordPattern();
        return compStatementAttributes;
    }

    /**
     * Method to return the computational keyword pattern in the implementation.
     * Examples: COMP, COMP-1, COMP-2 and etc.
     * @return the computational keyword pattern
     */
    protected abstract String getComputationalKeywordPattern();

    /**
     * {@inheritDoc}
     */
    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new CompDataDeclarationStatement(
                Integer.parseInt(statementAttributes[LEVEL]),
                statementAttributes[LENGTH_DECLARATION].length(),
                statementAttributes[NAME]
        );
    }
}
