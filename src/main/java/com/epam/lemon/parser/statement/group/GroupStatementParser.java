package com.epam.lemon.parser.statement.group;

import com.epam.lemon.copybook.StatementIterator;
import com.epam.lemon.exception.InvalidChildStatementLevelException;
import com.epam.lemon.exception.InvalidGroupDeclarationException;
import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.parser.statement.StatementParser;
import com.epam.lemon.parser.statement.registry.StatementParserRegistry;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.group.GroupDataDeclarationCobolStatement;

import java.util.ArrayList;
import java.util.List;

/**
 * COBOL grouping statement contains only two attributes: level and name.
 *
 * Example:
 *
 * 01 GROUP-NAME.
 *
 */
public class GroupStatementParser extends ParentFieldParser {

    private static final int LEVEL = 0;
    private static final int NAME = 1;

    private final StatementParserRegistry statementParserRegistry;

    private Integer groupLevel;
    /**
     * Main constructor to initialize object with statement iterator.
     *
     * @param statementIterator the statement source to fetch upcoming field declaration
     */
    public GroupStatementParser(StatementIterator statementIterator,
        StatementParserRegistry statementParserRegistry) {
        super(statementIterator);
        this.statementParserRegistry = statementParserRegistry;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] groupAttributeFormats = new String[2];
        groupAttributeFormats[LEVEL] = LEVEL_PATTERN;
        groupAttributeFormats[NAME] = NAME_PATTERN;
        return groupAttributeFormats;
    }

    @Override
    protected DataDeclarationCobolStatement parseMatchedStatement(String[] groupStatementAttributes) {
        groupLevel = parseLevel(groupStatementAttributes[LEVEL]);
        List<StatementParser> statementParsers = statementParserRegistry.registerStatementParsers(statementIterator);
        List<DataDeclarationCobolStatement> childrenStatements = parseChildrenStatements(statementParsers);
        checkChildrenSize(childrenStatements);
        return new GroupDataDeclarationCobolStatement(groupLevel,
                                                      groupStatementAttributes[NAME],
                                                      childrenStatements);
    }

    private Integer parseLevel(String statementAttribute) {
        return Integer.parseInt(statementAttribute);
    }

    private List<DataDeclarationCobolStatement> parseChildrenStatements(List<StatementParser> childrenParsers) {
        List<DataDeclarationCobolStatement> childrenStatements = new ArrayList<>();
        DataDeclarationCobolStatement childStatement;
        while (statementIterator.hasNext()) {
            String copybookStatement = statementIterator.next();
            try {
                childStatement = parseChildStatement(copybookStatement, childrenParsers);
            } catch (InvalidChildStatementLevelException e) {
                statementIterator.previous();
                break;
            }
            childrenStatements.add(childStatement);
        }
        return childrenStatements;
    }

    private DataDeclarationCobolStatement parseChildStatement(String statement, List<StatementParser> parsers) {
        String[] statementAttributes = splitStatement(statement);
        String childLevel = statementAttributes[LEVEL];
        if (childLevelHigherGroupLevel(childLevel)) {
            return parseChildStatementWithSuitableParser(statement, parsers);
        }
        throw new InvalidChildStatementLevelException(childLevel, groupLevel);
    }

    private DataDeclarationCobolStatement parseChildStatementWithSuitableParser(String statement,
                                                                                List<StatementParser> parsers) {
        DataDeclarationCobolStatement childStatement;
        boolean statementWasProcessedWithSuitableParser;
        for (StatementParser childParser : parsers) {
            childStatement = childParser.parseStatement(statement);
            statementWasProcessedWithSuitableParser = (childStatement != null);
            if (statementWasProcessedWithSuitableParser) {
                return childStatement;
            }
        }
        throw new InvalidStatementFormatException(statement);
    }

    private boolean childLevelHigherGroupLevel(String levelAttribute) {
        return parseLevel(levelAttribute) > groupLevel;
    }

    private void checkChildrenSize(List<DataDeclarationCobolStatement> childrenStatements) {
        if (childrenStatements.isEmpty()) {
            throw new InvalidGroupDeclarationException();
        }
    }
}
