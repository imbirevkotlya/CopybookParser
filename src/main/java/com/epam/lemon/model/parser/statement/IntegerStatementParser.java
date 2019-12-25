package com.epam.lemon.model.parser.statement;

import com.epam.lemon.model.statement.DataDeclarationCobolStatement;
import com.epam.lemon.model.statement.IntegerDeclarationCobolStatement;

import java.util.function.Function;

public class IntegerStatementParser extends AbstractStatementParser {

    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] integerStatementAttributes = new String[4];
        integerStatementAttributes[0] = "[0-4][1-9]";
        integerStatementAttributes[1] = "^[^.]+$";
        integerStatementAttributes[2] = "PIC";
        integerStatementAttributes[3] = "^[9]*$";
        return integerStatementAttributes;
    }

    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new IntegerDeclarationCobolStatement(
                Integer.parseInt(statementAttributes[0]),
                statementAttributes[3].length(),
                statementAttributes[1]
        );
    }
}
