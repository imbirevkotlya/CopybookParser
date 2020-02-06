package com.epam.lemon.parser.statement;

import com.epam.lemon.statement.DataDeclarationCobolStatement;

/**
 * Utility class to simplify the statement parser implementation.
 */
public abstract class AbstractStatementParser implements StatementParser {

    /**
     * Supported level numbers:
     * 01 - 49
     */
    protected static final String LEVEL_PATTERN = "[0-4][0-9]";
    /**
     * Supported names:
     * all, except the '.'
     */
    protected static final String NAME_PATTERN = "^[^.]+$";

    protected static final String SPACE = " ";

    protected static final String VALUE_DECLARATION_KEYWORD = "PIC";

    private boolean matchesStatement(String[] statementAttributes) {
        String[] necessaryStatementAttributeFormats = getNecessaryStatementAttributeFormats();
        if (necessaryStatementAttributeFormats.length != statementAttributes.length) {
            return false;
        }
        return checkAttributes(statementAttributes, necessaryStatementAttributeFormats);
    }

    private boolean checkAttributes(String[] statementAttributes, String[] necessaryStatementAttributeFormats) {
        for (int i = 0; i < statementAttributes.length; i++) {
            String statementAttribute = statementAttributes[i];
            statementAttribute = statementAttribute.trim();
            String necessaryStatementAttributeFormat = necessaryStatementAttributeFormats[i];
            if (!statementAttribute.matches(necessaryStatementAttributeFormat)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Should report the regex expressions for the statement parts (imagine, that the each statementPart is trimmed)
     * The order is important.
     *
     * Example of using:
     *
     * Imagine, that you have such COBOL statement:
     *
     * 01 NAME PIC X.
     *
     * Here you have 4 attributes (all the attributes are divided by the space):
     *
     * 1 - level (is two digits)
     * 2 - name (anything except the dot)
     * 3 - PIC keyword
     * 4 - field type and length declaration
     *
     * The method implementation will be like this:
     *
     * String[] alphanumericStatementAttributes = new String[4];
     * alphanumericStatementAttributes[0] = "[0-4][1-9]";
     * alphanumericStatementAttributes[1] = "^[^.]+$";
     * alphanumericStatementAttributes[2] = "PIC";
     * alphanumericStatementAttributes[3] = "^[X]*$";
     * return alphanumericStatementAttributes;
     *
     * @return the regex expressions for the statement parts (divided by the space and trimmed)
     */
    protected abstract String[] getNecessaryStatementAttributeFormats();

    /**
     * {@inheritDoc}
     */
    @Override
    public DataDeclarationCobolStatement parseStatement(String statement) {
        String[] statementAttributes = splitStatement(statement);
        if (matchesStatement(statementAttributes)) {
            return parseMatchedStatement(statementAttributes);
        }
        return null;
    }

    protected String[] splitStatement(String statement) {
        return statement.split(SPACE);
    }

    /**
     * Method declares the logic to parse statement (by it's attributes): level, name and so on.
     * The statement attributes are defined in the method getNecessaryStatementAttributeFormats.
     *
     * @param statementAttributes is a statement attributes
     * @return a fully parsed domain object from statementAttributes
     */
    protected abstract DataDeclarationCobolStatement parseMatchedStatement(String[] statementAttributes);
}
