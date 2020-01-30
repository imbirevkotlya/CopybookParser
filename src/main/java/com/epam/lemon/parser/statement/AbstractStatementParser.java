package com.epam.lemon.parser.statement;

import com.epam.lemon.exception.InvalidStatementFormatException;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;

import java.util.Arrays;
import java.util.function.Function;

/**
 * Utility class to simplify the statement parser implementation.
 */
public abstract class AbstractStatementParser implements StatementParser {

    /**
     * Supported level numbers:
     * 01 - 49
     */
    static final String LEVEL_PATTERN = "[0-4][1-9]";
    /**
     * Supported names:
     * all, except the '.'
     */
    static final String NAME_PATTERN = "^[^.]+$";

    private static final String SPACE = " ";

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean matchesStatement(String statement) {
        return matchesStatement(statement.split(SPACE));
    }

    private boolean matchesStatement(String[] statementAttributes) {
        String[] necessaryStatementAttributeFormats = getNecessaryStatementAttributeFormats();
        String[] optionalStatementAttributeFormats = getDefaultValueStatementAttributeFormats();
        int allStatementAttributeFormatsLength = optionalStatementAttributeFormats.length + necessaryStatementAttributeFormats.length;
        String[] allStatementAttributeFormats = Arrays.copyOf(necessaryStatementAttributeFormats, allStatementAttributeFormatsLength);
        System.arraycopy(optionalStatementAttributeFormats, 0, allStatementAttributeFormats, necessaryStatementAttributeFormats.length, optionalStatementAttributeFormats.length);
        if (necessaryStatementAttributeFormats.length != statementAttributes.length) {
            if (statementAttributes.length != allStatementAttributeFormatsLength) {
                return false;
            } else {
                return checkAttributes(statementAttributes, allStatementAttributeFormats);
            }
        }
        return checkAttributes(statementAttributes, necessaryStatementAttributeFormats);
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
     * The method to return the default value pattern into the main parsing mechanism.
     * The order is important.
     *
     * Example of using:
     *
     * Imagine, you have such COBOL statement:
     *
     * 01 NAME PIC XX VALUE '12'.
     *
     * Here you have 2 default value attributes:
     * VALUE keyword
     * default value parameter
     *
     * The method implementation can be something like:
     *
     *  String[] defaultValuePattern = new String[2];
     *  defaultValuePattern[0] = "VALUE";
     *  defaultValuePattern[1] = "'([^' ]*)'";
     *  return defaultValuePattern;
     *
     * @return the regex expressions for the default value statement parts (divided by the space and trimmed)
     */
    protected abstract String[] getDefaultValueStatementAttributeFormats();

    private boolean checkAttributes(String[] statementAttributes, String[] statementAttributeFormats) {
        for (int i = 0; i < statementAttributes.length; i++) {
            String statementAttribute = statementAttributes[i];
            statementAttribute = statementAttribute.trim();
            String statementAttributeFormat = statementAttributeFormats[i];
            if (!statementAttribute.matches(statementAttributeFormat)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataDeclarationCobolStatement parseStatementWithLinkToGroup(GroupDataDeclarationCobolStatement parentStatement, String statement) throws InvalidStatementFormatException {
        DataDeclarationCobolStatement childStatement = parseStatement(statement);
        parentStatement.addChildrenStatement(childStatement);
        return parentStatement;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DataDeclarationCobolStatement parseStatement(String statement) throws InvalidStatementFormatException {
        String[] statementAttributes = statement.split(SPACE);
        if (matchesStatement(statementAttributes)) {
            return getBuildStatementFunction().apply(statementAttributes);
        }
        throw new InvalidStatementFormatException(statement);
    }

    /**
     * Should return the ability to construct the declaration statement. Generally, it can be some
     * factory method, constructor or something else.
     *
     * Example:
     *
     * return statementAttributes -> new AlphanumericDeclarationCobolStatement(
     *   Integer.parseInt(statementAttributes[0]),
     *   statementAttributes[3].length(),
     *    statementAttributes[1]
     * );
     *
     * Statement attributes description and declaration can be found in method above:
     * getNecessaryStatementAttributeFormats()
     *
     * @return the function from the statement declared attributes to the built and ready to use statement
     */
    protected abstract Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction();
}
