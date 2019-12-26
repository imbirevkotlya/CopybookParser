package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.CopybookStatementIterator;
import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;
import com.epam.lemon.statement.RegularDataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ParserTest {

    @Test
    public void parse() throws IOException {
        CopybookStatementIterator copybookStatementIterator = new CopybookStatementIterator(Files.readAllBytes(Path.of("src/test/resources/PERSINFO.cpy")));

        Copybook copybook = new CopybookParser().parse(copybookStatementIterator);

        List<DataDeclarationCobolStatement> actualStatements = copybook.getCobolStatements();
        Assert.assertEquals(1, actualStatements.size());
        DataDeclarationCobolStatement actualStatement = actualStatements.get(0);
        Assert.assertEquals(StatementType.GROUP_STATEMENT, actualStatement.getStatementType());
        Assert.assertEquals(1, actualStatement.getLevel().intValue());
        Assert.assertEquals("EMP-RECORD", actualStatement.getName());
        GroupDataDeclarationCobolStatement groupActualStatement = (GroupDataDeclarationCobolStatement) actualStatement;
        Assert.assertEquals(3, groupActualStatement.getChildrenStatements().size());
        List<DataDeclarationCobolStatement> actualStatementsWithFiveLevel = copybook.getStatementsByLevel(5);
        Assert.assertEquals(3, actualStatementsWithFiveLevel.size());
        Assert.assertEquals(StatementType.INTEGER_STATEMENT, copybook.getStatementByName("EMP-NAME").getStatementType());
        Assert.assertEquals(StatementType.ALPHANUMERIC_STATEMENT, copybook.getStatementByName("EMP-SURNAME").getStatementType());
        Assert.assertEquals(1, ((RegularDataDeclarationCobolStatement) copybook.getStatementByName("EMP-POSITION")).getLength().intValue());
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseInvalidGroup() throws IOException {
        CopybookStatementIterator copybookStatementIterator = new CopybookStatementIterator(Files.readAllBytes(Path.of("src/test/resources/INVALID_GROUP.cpy")));

        new CopybookParser().parse(copybookStatementIterator);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseInvalidNumeric() throws IOException {
        CopybookStatementIterator copybookStatementIterator = new CopybookStatementIterator(Files.readAllBytes(Path.of("src/test/resources/INVALID_NUM.cpy")));

        new CopybookParser().parse(copybookStatementIterator);
    }

    @Test(expected = InvalidStatementFormatException.class)
    public void parseInvalidAlphanumeric() throws IOException {
        CopybookStatementIterator copybookStatementIterator = new CopybookStatementIterator(Files.readAllBytes(Path.of("src/test/resources/INVALID_ALPHA.cpy")));

        new CopybookParser().parse(copybookStatementIterator);
    }
}
