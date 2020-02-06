package com.epam.lemon.parser.statement;

import com.epam.lemon.statement.DataDeclarationCobolStatement;

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
     * Main statement parser method, declares the feature to parse certain statement type.
     * @param statement is a raw statement
     * @return the domain object parsed from raw statement
     */
    DataDeclarationCobolStatement parseStatement(String statement);
}
