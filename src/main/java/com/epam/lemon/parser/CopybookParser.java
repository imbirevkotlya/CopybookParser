package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.parser.statement.*;
import com.epam.lemon.parser.statement.alphanumeric.AlphanumericDefaultValueStatementParser;
import com.epam.lemon.parser.statement.alphanumeric.AlphanumericStatementParser;
import com.epam.lemon.parser.statement.group.GroupStatementParser;
import com.epam.lemon.parser.statement.numeric.*;
import com.epam.lemon.parser.statement.numeric.computational.comp.CompDefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp.CompStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp1.Comp1DefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp1.Comp1StatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp2.Comp2DefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp2.Comp2StatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp3.Comp3DefaultValueStatementParser;
import com.epam.lemon.parser.statement.numeric.computational.comp3.Comp3StatementParser;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.group.GroupDataDeclarationCobolStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class of the library for parsing the copybook.
 * All the copybook statement rules can be found in the StatementParser implementations.
 *
 * General copybook rules:
 *
 * Copybook should contains only fields description,
 * without any numbers and so on (even if they are in comment block in the source file).
 * Each statement should have '.' after it declaration.
 *
 */
public class CopybookParser {

    public static final String VALUE_DECLARATION_KEYWORD = "PIC";

    private final List<StatementParser> statementParsers;

    /**
     * Main parser constructor to initialize the statement parsers, which it will use.
     */
    public CopybookParser() {
        IntegerStatementParser integerStatementParser = new IntegerStatementParser();
        AlphanumericStatementParser alphanumericStatementParser = new AlphanumericStatementParser();
        CompStatementParser compStatementParser = new CompStatementParser();
        Comp1StatementParser comp1StatementParser = new Comp1StatementParser();
        Comp2StatementParser comp2StatementParser = new Comp2StatementParser();
        Comp3StatementParser comp3StatementParser = new Comp3StatementParser();
        statementParsers = new ArrayList<>();
        statementParsers.add(new GroupStatementParser());
        statementParsers.add(integerStatementParser);
        statementParsers.add(alphanumericStatementParser);
        statementParsers.add(compStatementParser);
        statementParsers.add(comp1StatementParser);
        statementParsers.add(comp2StatementParser);
        statementParsers.add(comp3StatementParser);
        statementParsers.add(new AlphanumericDefaultValueStatementParser(alphanumericStatementParser));
        statementParsers.add(new IntegerDefaultValueStatementParser(integerStatementParser));
        statementParsers.add(new CompDefaultValueStatementParser(compStatementParser));
        statementParsers.add(new Comp1DefaultValueStatementParser(comp1StatementParser));
        statementParsers.add(new Comp2DefaultValueStatementParser(comp2StatementParser));
        statementParsers.add(new Comp3DefaultValueStatementParser(comp3StatementParser));
    }

    /**
     * Method to parse the copybook using the copybook iterator.
     * @param copybookStatementIterator the copybook statement iterator to work with copybook statements one by one
     * @return the completed copybook class with statements inside it
     * @throws InvalidStatementFormatException if copybook format is wrong or not supported
     */
    public Copybook parse(StatementIterator copybookStatementIterator) throws InvalidStatementFormatException {
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

    private DataDeclarationCobolStatement parseGroupStatementWithChildren(StatementIterator statementIterator, String statement) {
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
                return;
            }
        }
        throw new InvalidStatementFormatException(childrenStatement);
    }
}
