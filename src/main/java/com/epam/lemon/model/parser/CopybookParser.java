package com.epam.lemon.model.parser;

import com.epam.lemon.model.copybook.CopybookStatementIterator;
import com.epam.lemon.model.copybook.Copybook;
import com.epam.lemon.model.exception.InvalidStatementFormatException;
import com.epam.lemon.model.parser.statement.AlphanumericStatementParser;
import com.epam.lemon.model.parser.statement.GroupStatementParser;
import com.epam.lemon.model.parser.statement.IntegerStatementParser;
import com.epam.lemon.model.parser.statement.StatementParser;
import com.epam.lemon.model.statement.DataDeclarationCobolStatement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class CopybookParser {

    private static final String SPACE = " ";
    private final List<StatementParser> statementParsers;

    public CopybookParser() {
        statementParsers = new ArrayList<>();
        statementParsers.add(new IntegerStatementParser());
        statementParsers.add(new AlphanumericStatementParser());
        statementParsers.add(new GroupStatementParser());
    }

    public Copybook parse(CopybookStatementIterator copybookStatementIterator) {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        while (copybookStatementIterator.hasNext()) {
            String copybookStatement = copybookStatementIterator.next();
            cobolStatements.add(parseStatement(copybookStatement));
        }
        return new Copybook(cobolStatements);
    }

    private DataDeclarationCobolStatement parseStatement(String copybookStatement) {
        String[] statementAttributes = copybookStatement.split(SPACE);
        for (StatementParser statementParser : statementParsers) {
            if (statementParser.matchesStatement(copybookStatement)) {
                return statementParser.parseStatement(copybookStatement);
            }
        }
        throw new InvalidStatementFormatException();
    }
}
