package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.CopybookStatementIterator;
import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.parser.statement.AlphanumericStatementParser;
import com.epam.lemon.parser.statement.GroupStatementParser;
import com.epam.lemon.parser.statement.IntegerStatementParser;
import com.epam.lemon.parser.statement.StatementParser;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;

import java.util.ArrayList;
import java.util.List;

public class CopybookParser {

    private static final String VALUE_DECLARATION_KEYWORD = "PIC";

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
            DataDeclarationCobolStatement result;
            if (copybookStatement.contains(VALUE_DECLARATION_KEYWORD)) {
                result = parseRegularStatement(copybookStatement);
            } else {
                result = parseGroupStatementWithChildren(copybookStatementIterator, copybookStatement);
            }
            cobolStatements.add(result);
        }
        return new Copybook(cobolStatements);
    }

    private DataDeclarationCobolStatement parseRegularStatement(String statement) {
        for (StatementParser statementParser : statementParsers) {
            if (statementParser.matchesStatement(statement)) {
                return statementParser.parseStatement(statement);
            }
        }
        throw new InvalidStatementFormatException(statement);
    }

    private DataDeclarationCobolStatement parseGroupStatementWithChildren(CopybookStatementIterator statementIterator, String statement) {
        GroupDataDeclarationCobolStatement parentStatement = parseGroupStatement(statement);
        while (statementIterator.hasNext()) {
            String childrenStatement = statementIterator.next();
            parseChildrenStatement(parentStatement, childrenStatement);
        }
        return parentStatement;
    }

    private GroupDataDeclarationCobolStatement parseGroupStatement(String statement) {
        for (StatementParser statementParser : statementParsers) {
            if (statementParser.matchesStatement(statement)) {
                return (GroupDataDeclarationCobolStatement) statementParser.parseStatement(statement);
            }
        }
        throw new InvalidStatementFormatException(statement);
    }

    private void parseChildrenStatement(GroupDataDeclarationCobolStatement parentStatement, String childrenStatement) {
        for (StatementParser statementParser : statementParsers) {
            if (statementParser.matchesStatement(childrenStatement)) {
                statementParser.parseStatementWithLinkToGroup(parentStatement, childrenStatement);
            }
        }
    }
}