package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.exception.InvalidDefaultValueException;
import com.epam.lemon.exception.InvalidGroupDeclarationException;
import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.parser.statement.registry.ConstructorParserRegistry;
import com.epam.lemon.parser.statement.registry.StatementParserRegistry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ParserTest {

    private StatementParserRegistry statementParserRegistry;

    @Before
    public void setUp() {
        statementParserRegistry = new ConstructorParserRegistry();
    }

    @Test
    public void parseCopybook_withNumericValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithNumericDefinition();

        assertCopybook(testCopybookCharacteristics);
    }

    private void assertCopybook(TestCopybookCharacteristics testCopybookCharacteristics) throws IOException {
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser(statementParserRegistry, statementIterator).parse();

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidNumericFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidNumericFieldDeclaration();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    private void tryToParseCopybook(TestCopybookCharacteristics testCopybookCharacteristics) throws IOException {
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser(statementParserRegistry, statementIterator).parse();
    }

    @Test
    public void parseCopybook_alphanumericValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithAlphanumericDefinition();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidAlphanumericFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidAlphanumericFieldDeclaration();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parseCopybook_groupValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithGroupFieldDefinition();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidGroupFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidGroupFieldDeclaration();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parseCopybook_withCompFields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithCompFields();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidCompFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidCompFieldDeclaration();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidCompFieldDataFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidCompFieldDataFormatDeclaration();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parseCopybook_withComp1Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp1Value();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp1FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp1Value();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parseCopybook_withComp2Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp2Value();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp2FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp2Value();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp2FieldDataFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp2ValueFormat();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parseCopybook_withComp3Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp3Value();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp3FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp3Value();
        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parse_withDefaultValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithDefaultValues();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidDefaultValueException.class)
    public void parse_invalidDefaultValueLengthDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidDefaultValueLength();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidDefaultValueFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidDefaultValueFormat();

        tryToParseCopybook(testCopybookCharacteristics);
    }


    @Test
    public void parse_nestedGroups() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithNestedGroups();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parse_nestedSubGroups() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithNestedSubGroups();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parse_severalNestedSubGroups() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithSeveralNestedSubGroups();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test
    public void parse_severalGeneralGroups() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithSeveralGeneralGroups();

        assertCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidGroupDeclarationException.class)
    public void parse_nestedGroupsWithoutChildren() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithSubGroupWithoutChildren();

        tryToParseCopybook(testCopybookCharacteristics);
    }

    @Test(expected = InvalidGroupDeclarationException.class)
    public void parse_generalCopybookWithoutAnyChildren() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithGroupWithoutAnyChildren();

        tryToParseCopybook(testCopybookCharacteristics);
    }
}
