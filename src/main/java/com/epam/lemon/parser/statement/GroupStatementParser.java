package com.epam.lemon.parser.statement;

import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;

import java.util.function.Function;

/**
 * COBOL grouping statement contains only two attributes: level and name.
 *
 * Example:
 *
 * 01 GROUP-NAME.
 *
 */
public class GroupStatementParser extends AbstractStatementParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] groupAttributeFormats = new String[2];
        groupAttributeFormats[LEVEL] = LEVEL_PATTERN;
        groupAttributeFormats[NAME] = NAME_PATTERN;
        return groupAttributeFormats;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new GroupDataDeclarationCobolStatement(
                Integer.parseInt(statementAttributes[LEVEL]),
                statementAttributes[NAME]
        );
    }
}
