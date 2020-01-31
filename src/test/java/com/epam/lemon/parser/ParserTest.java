package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.CopybookStatementIterator;
import com.epam.lemon.exception.InvalidDefaultValueException;
import com.epam.lemon.exception.InvalidStatementFormatException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ParserTest {

    @Test
    public void parseCopybook_withNumericValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithNumericDefinition();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidNumericFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidNumericFieldDeclaration();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_alphanumericValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithAlphanumericDefinition();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidAlphanumericFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidAlphanumericFieldDeclaration();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_groupValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithGroupFieldDefinition();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseCopybook_invalidGroupFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidGroupFieldDeclaration();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_withCompFields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithCompFields();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidCompFieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidCompFieldDeclaration();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidCompFieldDataFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidCompFieldDataFormatDeclaration();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_withComp1Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp1Value();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp1FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp1Value();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_withComp2Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp2Value();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp2FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp2Value();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp2FieldDataFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp2ValueFormat();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parseCopybook_withComp3Fields() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithComp3Value();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidComp3FieldDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidComp3Value();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test
    public void parse_withDefaultValues() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithDefaultValues();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        Copybook copybook = new CopybookParser().parse(statementIterator);

        Assert.assertEquals(copybook, testCopybookCharacteristics.getExpectedCopybook());
    }

    @Test(expected = InvalidDefaultValueException.class)
    public void parse_invalidDefaultValueLengthDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidDefaultValueLength();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parse_invalidDefaultValueFormatDeclaration() throws IOException {
        TestCopybookCharacteristics testCopybookCharacteristics = TestCopybookFactory.buildCopybookWithInvalidDefaultValueFormat();
        CopybookStatementIterator statementIterator = testCopybookCharacteristics.createIteratorFromFile();

        new CopybookParser().parse(statementIterator);
    }
}
