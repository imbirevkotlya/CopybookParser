package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.exception.InvalidDefaultValueException;
import com.epam.lemon.exception.InvalidGroupDeclarationException;
import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.parser.statement.registry.ConstructorParserRegistry;
import com.epam.lemon.parser.statement.registry.StatementParserRegistry;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

  private StatementParserRegistry statementParserRegistry;

  @Before
  public void setUp() {
    statementParserRegistry = new ConstructorParserRegistry();
  }

  @Test
  public void parseCopybook_withNumericValues() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithNumericDefinition();

    assertCopybook(testCopybookCharacteristics);
  }

  private void assertCopybook(TestCopybookCharacteristics copybookCharacteristics) throws IOException {
    StatementIterator statementIterator = copybookCharacteristics.createIteratorFromFile();

    Copybook copybook = new CopybookParser(statementParserRegistry, statementIterator).parse();

    Assert.assertEquals(copybook, copybookCharacteristics.getExpectedCopybook());
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parseCopybook_invalidNumericFieldDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidNumericFieldDeclaration();

    tryParse(testCopybookCharacteristics);
  }

  private void tryParse(TestCopybookCharacteristics copybookCharacteristics) throws IOException {
    StatementIterator statementIterator = copybookCharacteristics.createIteratorFromFile();

    new CopybookParser(statementParserRegistry, statementIterator).parse();
  }

  @Test
  public void parseCopybook_alphanumericValues() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithAlphanumericDefinition();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parseCopybook_invalidAlphanumericFieldDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidAlphanumericFieldDeclaration();

    tryParse(testCopybookCharacteristics);
  }

  @Test
  public void parseCopybook_groupValues() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithGroupFieldDefinition();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parseCopybook_invalidGroupFieldDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidGroupFieldDeclaration();

    tryParse(testCopybookCharacteristics);
  }

  @Test
  public void parse_nestedGroups() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithNestedGroups();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test
  public void parse_nestedSubGroups() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithNestedSubGroups();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test
  public void parse_severalNestedSubGroups() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithSeveralNestedSubGroups();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test
  public void parse_severalGeneralGroups() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithSeveralGeneralGroups();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test(expected = InvalidGroupDeclarationException.class)
  public void parse_nestedGroupsWithoutChildren() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithSubGroupWithoutChildren();

    tryParse(testCopybookCharacteristics);
  }

  @Test(expected = InvalidGroupDeclarationException.class)
  public void parse_generalCopybookWithoutAnyChildren() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithGroupWithoutAnyChildren();

    tryParse(testCopybookCharacteristics);
  }

  @Test
  public void parseCopybook_withCompFields() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithCompFields();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parse_invalidCompFieldDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidCompFieldDeclaration();

    tryParse(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parse_invalidCompFieldDataFormatDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidCompFieldDataFormatDeclaration();

    tryParse(testCopybookCharacteristics);
  }

  @Test
  public void parseCopybook_withComp1Fields() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithComp1Value();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parse_invalidComp1FieldDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidComp1Value();

    tryParse(testCopybookCharacteristics);
  }

  @Test
  public void parseCopybook_withComp2Fields() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithComp2Value();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parse_invalidComp2FieldDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidComp2Value();

    tryParse(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parse_invalidComp2FieldDataFormatDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidComp2ValueFormat();

    tryParse(testCopybookCharacteristics);
  }

  @Test
  public void parseCopybook_withComp3Fields() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithComp3Value();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parse_invalidComp3FieldDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidComp3Value();

    tryParse(testCopybookCharacteristics);
  }

  @Test
  public void parse_withDefaultValues() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithDefaultValues();

    assertCopybook(testCopybookCharacteristics);
  }

  @Test(expected = InvalidDefaultValueException.class)
  public void parse_invalidDefaultValueLengthDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidDefaultValueLength();

    tryParse(testCopybookCharacteristics);
  }

  @Test(expected = InvalidStatementFormatException.class)
  public void parse_invalidDefaultValueFormatDeclaration() throws IOException {
    TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory
        .buildCopybookWithInvalidDefaultValueFormat();

    tryParse(testCopybookCharacteristics);
  }
}
