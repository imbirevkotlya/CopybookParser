package com.epam.lemon.parser.statement;

import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;

public interface StatementParser {

    boolean matchesStatement(String statement);

    DataDeclarationCobolStatement parseStatement(String statement) throws InvalidStatementFormatException;

    DataDeclarationCobolStatement parseStatementWithLinkToGroup(GroupDataDeclarationCobolStatement parentStatement, String statement)
            throws InvalidStatementFormatException;
}
