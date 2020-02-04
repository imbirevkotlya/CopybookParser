package com.epam.lemon.parser.statement;

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
   */
  List<StatementParser> registerStatementParsers();

}
