package com.epam.lemon.model.parser;

import com.epam.lemon.model.copybook.CopybookStatementIterator;
import com.epam.lemon.model.copybook.Copybook;
import com.epam.lemon.model.statement.DataDeclarationCobolStatement;

import java.util.ArrayList;
import java.util.List;

public class CopybookParser {

    public Copybook parse(CopybookStatementIterator copybookStatementIterator) {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        while (copybookStatementIterator.hasNext()) {
            String copybookStatement = copybookStatementIterator.next();
            cobolStatements.add(parseStatement(copybookStatement));
        }
        return new Copybook(cobolStatements);
    }

    private DataDeclarationCobolStatement parseStatement(String copybookStatement) {
        return null;
    }
}
