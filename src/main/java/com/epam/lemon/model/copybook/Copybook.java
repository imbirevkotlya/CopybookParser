package com.epam.lemon.model.copybook;

import com.epam.lemon.model.statement.DataDeclarationCobolStatement;

import java.util.List;

public class Copybook {

    private final List<DataDeclarationCobolStatement> cobolStatements;

    public Copybook(List<DataDeclarationCobolStatement> cobolStatements) {
        this.cobolStatements = cobolStatements;
    }

    public List<DataDeclarationCobolStatement> getCobolStatements() {
        return cobolStatements;
    }
}
