package com.epam.lemon.parser.statement;

import com.epam.lemon.statement.AlphanumericDeclarationCobolStatement;
import com.epam.lemon.statement.DataDeclarationCobolStatement;

import java.util.function.Function;

public class AlphanumericStatementParser extends AbstractStatementParser {

    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] alphanumericStatementAttributes = new String[4];
        alphanumericStatementAttributes[0] = "[0-4][1-9]";
        alphanumericStatementAttributes[1] = "^[^.]+$";
        alphanumericStatementAttributes[2] = "PIC";
        alphanumericStatementAttributes[3] = "^[X]*$";
        return alphanumericStatementAttributes;
    }

    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new AlphanumericDeclarationCobolStatement(
                Integer.parseInt(statementAttributes[0]),
                statementAttributes[3].length(),
                statementAttributes[1]
        );
    }
}
