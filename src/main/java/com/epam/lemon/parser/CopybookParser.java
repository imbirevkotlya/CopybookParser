package com.epam.lemon.parser;

import com.epam.lemon.PublicApi;
import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.parser.statement.StatementParser;
import com.epam.lemon.parser.statement.registry.StatementParserRegistry;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Main class of the library for parsing the copybook. All the copybook statement rules can be found
 * in the StatementParser implementations.
 *
 * General copybook rules:
 *
 * Copybook should contains only fields description, without any numbers and so on (even if they are
 * in comment block in the source file).
 */
public class CopybookParser {

  private final List<StatementParser> statementParsers;
  private final StatementIterator statementIterator;

  /**
   * Constructor to register the statement parsers from provided registry.
   * @param statementParserRegistry is a user provided statement parser source
   * @param statementIterator is a user provided statement source
   */
  @PublicApi
  public CopybookParser(StatementParserRegistry statementParserRegistry,
                        StatementIterator statementIterator) {

    statementParsers = statementParserRegistry.registerStatementParsers(statementIterator);
    this.statementIterator = statementIterator;
  }

  /**
   * Method for parsing the copybook statements from user provided statement source.
   * @return the fully completed copybook object with parsed statements
   */
  @PublicApi
  public Copybook parse() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    while (statementIterator.hasNext()) {
      String copybookStatement = statementIterator.next();
      cobolStatements.add(parseStatement(copybookStatement));
    }
    return new Copybook(cobolStatements);
  }

  private DataDeclarationCobolStatement parseStatement(String copybookStatement) {
    DataDeclarationCobolStatement statement;
    for (StatementParser statementParser : statementParsers) {
      statement = statementParser.parseStatement(copybookStatement);
      if (statement != null) {
        return statement;
      }
    }
    throw new InvalidStatementFormatException(copybookStatement);
  }
}
