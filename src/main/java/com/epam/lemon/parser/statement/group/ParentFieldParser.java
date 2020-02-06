package com.epam.lemon.parser.statement.group;

import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.parser.statement.AbstractStatementParser;

/**
 * The class represents the statement parser, which is required the statement iterator
 * to fetch upcoming statements to build the children nodes.
 */
abstract class ParentFieldParser extends AbstractStatementParser {

  final StatementIterator statementIterator;

  /**
   * Main constructor to initialize object with statement iterator.
   * @param statementIterator the statement source to fetch upcoming field declaration
   */
  ParentFieldParser(StatementIterator statementIterator) {
    this.statementIterator = statementIterator;
  }
}
