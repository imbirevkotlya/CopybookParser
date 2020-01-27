package com.epam.lemon.parser.statement;

import com.epam.lemon.parser.CopybookParser;
import com.epam.lemon.statement.CompDataDeclarationStatement;
import com.epam.lemon.statement.DataDeclarationCobolStatement;

import java.util.function.Function;

public class CompStatementParser extends AbstractStatementParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;
    private static final int VALUE_DECLARATION_KEYWORD = 2;
    private static final int LENGTH_DECLARATION = 3;
    private static final int COMPUTATION_DECLARATION = 4;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] compStatementAttributes = new String[5];
        compStatementAttributes[LEVEL] = LEVEL_PATTERN;
        compStatementAttributes[NAME] = NAME_PATTERN;
        compStatementAttributes[VALUE_DECLARATION_KEYWORD] = CopybookParser.VALUE_DECLARATION_KEYWORD;
        compStatementAttributes[LENGTH_DECLARATION] = "^[9]*$";
        compStatementAttributes[COMPUTATION_DECLARATION] = "COMP";
        return compStatementAttributes;
    }

    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new CompDataDeclarationStatement(
                Integer.parseInt(statementAttributes[LEVEL]),
                statementAttributes[LENGTH_DECLARATION].length(),
                statementAttributes[NAME]
        );
    }
}
