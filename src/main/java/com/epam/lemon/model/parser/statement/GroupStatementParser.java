package com.epam.lemon.model.parser.statement;

import com.epam.lemon.model.statement.DataDeclarationCobolStatement;
import com.epam.lemon.model.statement.GroupDataDeclarationCobolStatement;

import java.util.function.Function;

public class GroupStatementParser extends AbstractStatementParser {

    @Override
    protected String[] getNecessaryStatementAttributeFormats() {
        String[] groupAttributeFormats = new String[2];
        groupAttributeFormats[0] = "[0-4][1-9]";
        groupAttributeFormats[1] = "^[^.]+$";
        return groupAttributeFormats;
    }

    @Override
    protected Function<String[], DataDeclarationCobolStatement> getBuildStatementFunction() {
        return statementAttributes -> new GroupDataDeclarationCobolStatement(
                Integer.parseInt(statementAttributes[0]),
                statementAttributes[1]
        );
    }
}
