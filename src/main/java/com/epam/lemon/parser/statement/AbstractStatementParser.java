package com.epam.lemon.parser.statement;


import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;

import java.util.function.Function;

public abstract class AbstractStatementParser implements StatementParser {

    private static final String SPACE = " ";

    @Override
    public boolean matchesStatement(String statement) {
        return matchesStatement(statement.split(SPACE));
    }

    private boolean matchesStatement(String[] statementAttributes) {
        String[] necessaryStatementAttributeFormats = getNecessaryStatementAttributeFormats();
        if (necessaryStatementAttributeFormats.length != statementAttributes.length) {
            return false;
        }
        return checkAttributes(statementAttributes, necessaryStatementAttributeFormats);
    }

    private boolean checkAttributes(String[] statementAttributes, String[] necessaryStatementAttributeFormats) {
        for (int i = 0; i < statementAttributes.length; i++) {
            String statementAttribute = statementAttributes[i];
            statementAttribute = statementAttribute.trim();
            String necessaryStatementAttributeFormat = necessaryStatementAttributeFormats[i];
            if (!statementAttribute.matches(necessaryStatementAttributeFormat)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Should report the regex expressions for the statement parts (imagine, that the each statementPart is trimmed)
     * The order is important.
     * @return the regex expressions for the statement parts
     */
    protected abstract String[] getNecessaryStatementAttributeFormats();

    @Override
    public DataDeclarationCobolStatement parseStatementWithLinkToGroup(GroupDataDeclarationCobolStatement parentStatement, String statement) throws InvalidStatementFormatException {
        DataDeclarationCobolStatement childStatement = parseStatement(statement);
        parentStatement.addChildrenStatement(childStatement);
        return parentStatement;
    }

    protected abstract Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction();

    @Override
    public DataDeclarationCobolStatement parseStatement(String statement) throws InvalidStatementFormatException {
        String[] statementAttributes = statement.split(SPACE);
        if (matchesStatement(statementAttributes)) {
            return getBuildStatementFunction().apply(statementAttributes);
        }
        throw new InvalidStatementFormatException(statement);
    }
}
