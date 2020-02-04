package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.exception.InvalidDefaultValueException;
import com.epam.lemon.exception.InvalidStatementFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ParserTest {

    @Test
    public void parseCopybook_withNumericValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithNumericDefinition();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidNumericFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidNumericFieldDeclaration();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_alphanumericValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithAlphanumericDefinition();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidAlphanumericFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidAlphanumericFieldDeclaration();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_groupValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithGroupFieldDefinition();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidGroupFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidGroupFieldDeclaration();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_withCompFields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithCompFields();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidCompFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidCompFieldDeclaration();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidCompFieldDataFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidCompFieldDataFormatDeclaration();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_withComp1Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp1Value();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp1FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp1Value();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_withComp2Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp2Value();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp2FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp2Value();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp2FieldDataFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp2ValueFormat();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_withComp3Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp3Value();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp3FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp3Value();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parse_withDefaultValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithDefaultValues();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidDefaultValueException.class)
    public void parse_invalidDefaultValueLengthDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidDefaultValueLength();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidDefaultValueFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidDefaultValueFormat();
        StatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }
}
