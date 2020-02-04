package com.epam.lemon.parser.statement.registry;

import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.parser.statement.StatementParser;
import com.epam.lemon.parser.statement.alphanumeric.AlphanumericDefaultValueStatementParser;
import com.epam.lemon.parser.statement.alphanumeric.AlphanumericStatementParser;
import com.epam.lemon.parser.statement.group.GroupStatementParser;
import com.epam.lemon.parser.statement.numeric.IntegerDefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.IntegerStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp.CompDefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp.CompStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp1.Comp1DefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp1.Comp1StatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp2.Comp2DefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp2.Comp2StatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp3.Comp3DefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp3.Comp3StatementParser;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents the simple parsers registration function - via their constructors.
 */
public class ConstructorParserRegistry implements StatementParserRegistry {

  /**
   * {@inheritDoc}
   * @param statementRegistry
   * @param statementIterator
   */
  @Override
  public List<StatementParser> registerStatementParsers(StatementParserRegistry statementRegistry, StatementIterator statementIterator) {
    List<StatementParser> statementParsers = new ArrayList<>();
    statementParsers.addAll(registerGroupParsers(statementRegistry, statementIterator));
    statementParsers.addAll(registerAlphanumericParsers());
    statementParsers.addAll(registerNumericParsers());
    return statementParsers;
  }

  private List<StatementParser> registerGroupParsers(StatementParserRegistry statementRegistry, StatementIterator statementIterator) {
    List<StatementParser> groupParsers = new ArrayList<>();
    groupParsers.add(new GroupStatementParser(statementIterator, statementRegistry));
    return groupParsers;
  }

  private List<StatementParser> registerAlphanumericParsers() {
    AlphanumericStatementParser alphanumericStatementParser = new AlphanumericStatementParser();
    List<StatementParser> alphanumericParsers = new ArrayList<>();
    alphanumericParsers.add(alphanumericStatementParser);
    alphanumericParsers.add(new AlphanumericDefaultValueStatementParser(alphanumericStatementParser));
    return alphanumericParsers;
  }

  private List<StatementParser> registerNumericParsers() {
    List<StatementParser> numericParsers = new ArrayList<>();
    IntegerStatementParser integerStatementParser = new IntegerStatementParser();
    numericParsers.add(integerStatementParser);
    numericParsers.add(new IntegerDefaultValueStatementParser(integerStatementParser));
    numericParsers.addAll(registerComputationalParsers());
    return numericParsers;
  }

  private List<StatementParser> registerComputationalParsers() {
    ArrayList<StatementParser> computationalParsers = new ArrayList<>();
    CompStatementParser compStatementParser = new CompStatementParser();
    Comp1StatementParser comp1StatementParser = new Comp1StatementParser();
    Comp2StatementParser comp2StatementParser = new Comp2StatementParser();
    Comp3StatementParser comp3StatementParser = new Comp3StatementParser();
    computationalParsers.add(compStatementParser);
    computationalParsers.add(comp1StatementParser);
    computationalParsers.add(comp2StatementParser);
    computationalParsers.add(comp3StatementParser);
    computationalParsers.add(new CompDefaultValueStatementParser(compStatementParser));
    computationalParsers.add(new Comp1DefaultValueStatementParser(comp1StatementParser));
    computationalParsers.add(new Comp2DefaultValueStatementParser(comp2StatementParser));
    computationalParsers.add(new Comp3DefaultValueStatementParser(comp3StatementParser));
    return computationalParsers;
  }
}
