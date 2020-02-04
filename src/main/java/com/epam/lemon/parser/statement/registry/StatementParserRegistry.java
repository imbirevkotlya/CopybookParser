package com.epam.lemon.parser.statement.registry;

import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.parser.statement.StatementParser;
import java.util.List;

/**
 * The class represents the container builder for the different statement parsers.
 *
 * This class can be used to get the full list of the statement parsers, which are available now.
 */
public interface StatementParserRegistry {

  /**
   * The all ready to work statement parsers available now.
   * @return the list of statement parsers
   * @param statementRegistry
   * @param statementIterator
   */
  List<StatementParser> registerStatementParsers(
      StatementParserRegistry statementRegistry,
      StatementIterator statementIterator);

}
