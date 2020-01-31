package com.epam.lemon.parser.statement;

import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.group.GroupDataDeclarationCobolStatement;

/**
 * The utility class to handle the statement constructions. Each language statement type should contain it's own
 * statement parser (group statement, alphanumeric statement and so on).
 *
 * The main pattern here is Chain of Responsibility.
 *
 * Basically, all you need is to create a new implementation from this interface and add it to the CopybookParser
 * class to include it in the parsing process. After this, all you need is to add the new statement formats in the
 * copybook.
 */
public interface StatementParser {

    /**
     * Main determination method to understand, if this statement is for this parser type.
     *
     * @param statement the copybook statement, trimmed.
     * @return the true - if this parser rules are matching for this statement,
     * false - if use another statement parser for it.
     */
    boolean matchesStatement(String statement);

    /**
     * Main parser method for the regular statements (not a group one).
     *
     * @param statement the raw copybook statement, trimmed (without any extra spaces and line separators)
     * @return the completed copybook statement from the incoming statement attributes
     * @throws InvalidStatementFormatException if statement format is not compatible
     */
    DataDeclarationCobolStatement parseStatement(String statement) throws InvalidStatementFormatException;

    /**
     * Main parser method for the children statements in the copybook.
     *
     * @param parentStatement is a group statement, in which we need to add incoming statement as a child
     * @param statement is a child statement, which need to be parsed and added to the parent statement as a child
     * @return the group statement with updated children list
     * @throws InvalidStatementFormatException if child statement format is not compatible
     */
    DataDeclarationCobolStatement parseStatementWithLinkToGroup(GroupDataDeclarationCobolStatement parentStatement, String statement)
            throws InvalidStatementFormatException;
}
