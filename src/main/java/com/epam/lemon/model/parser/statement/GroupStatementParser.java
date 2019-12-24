package com.epam.lemon.model.parser.statement;

import com.epam.lemon.model.exception.InvalidStatementFormatException;
import com.epam.lemon.model.statement.DataDeclarationCobolStatement;

public class GroupStatementParser implements StatementParser {

    @Override
    public boolean matchesStatement(String statement) {
        return false;
    }

    @Override
    public DataDeclarationCobolStatement parseStatement(String statement) throws InvalidStatementFormatException {
        return null;
    }
}
