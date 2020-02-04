package com.epam.lemon.parser.statement.group;

import com.epam.lemon.copybook.StatementIterator;
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
        Integer groupLevel = parseLevel(groupStatementAttributes[LEVEL]);
        List<DataDeclarationCobolStatement> childrenStatements = parseChildrenStatements(groupLevel);
        return new GroupDataDeclarationCobolStatement(groupLevel, groupStatementAttributes[NAME], childrenStatements);
    }

    private Integer parseLevel(String statementAttribute) {
        return Integer.parseInt(statementAttribute);
    }

    private List<DataDeclarationCobolStatement> parseChildrenStatements(Integer groupLevel) {
        List<DataDeclarationCobolStatement> childrenStatements = new ArrayList<>();
        List<StatementParser> statementParsers = statementParserRegistry.registerStatementParsers(
            statementParserRegistry, statementIterator);
        while (statementIterator.hasNext()) {
            String copybookStatement = statementIterator.next();
            String[] statementAttributes = copybookStatement.split(SPACE);
            if (parseLevel(statementAttributes[LEVEL]) > groupLevel) {
                DataDeclarationCobolStatement childStatement = null;
                for (StatementParser childParser : statementParsers) {
                    childStatement = childParser.parseStatement(copybookStatement);
                    if (childStatement != null) {
                        childrenStatements.add(childStatement);
                        break;
                    }
                }
                if (childStatement == null) {
                    throw new InvalidStatementFormatException(copybookStatement);
                }
            }
            else {
                statementIterator.previous();
                break;
            }
        }
        return childrenStatements;
    }

}
