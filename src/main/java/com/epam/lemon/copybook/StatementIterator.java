package com.epam.lemon.copybook;

import java.util.Iterator;

/**
 * The class represents the iteration pattern for copybook statements, encapsulates the logic
 * of iteration in the copybook fields declaration.
 *
 * The standard strategy to iterate is presented in the CopybookStatementIterator implementation.
 *
 */
public interface StatementIterator extends Iterator<String> {

  /**
   * Method returns the ability to get previous element from the iterator.
   * @return true - if previous element exists, false - if previous element doesn't exist
   */
  boolean hasPrevious();

  /**
   * Method, returns the previous iteration element, which was got by the next element with
   * reducing the cursor number on 1 element.
   *
   * If the current cursor is 0 - NoSuchElementException will be thrown
   *
   * @return previous iteration element with reducing the cursor with it
   */
  String previous();

}
