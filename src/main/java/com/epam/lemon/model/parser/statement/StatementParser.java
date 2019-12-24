package com.epam.lemon.model.parser.statement;

import com.epam.lemon.model.exception.InvalidStatementFormatException;
import com.epam.lemon.model.statement.DataDeclarationCobolStatement;

public interface StatementParser {

    boolean matchesStatement(String statement);

    DataDeclarationCobolStatement parseStatement(String statement) throws InvalidStatementFormatException;

}
