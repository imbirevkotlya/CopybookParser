package com.epam.lemon.model.parser.statement;

import com.epam.lemon.model.exception.InvalidStatementFormatException;
import com.epam.lemon.model.statement.DataDeclarationCobolStatement;
import com.epam.lemon.model.statement.GroupDataDeclarationCobolStatement;

public interface StatementParser {

    boolean matchesStatement(String statement);

    DataDeclarationCobolStatement parseStatement(String statement) throws InvalidStatementFormatException;

    DataDeclarationCobolStatement parseStatementWithLinkToGroup(GroupDataDeclarationCobolStatement parentStatement, String statement)
            throws InvalidStatementFormatException;
}
