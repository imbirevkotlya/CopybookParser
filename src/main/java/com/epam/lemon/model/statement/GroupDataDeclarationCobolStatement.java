package com.epam.lemon.model.statement;

import java.util.ArrayList;
import java.util.List;

public class GroupDataDeclarationCobolStatement implements DataDeclarationCobolStatement {

    private final Integer level;
    private final String name;
    private final List<DataDeclarationCobolStatement> childrenStatements;

    public GroupDataDeclarationCobolStatement(Integer level, String name) {
        this.level = level;
        this.name = name;
        this.childrenStatements = new ArrayList<>();
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getName() {
        return name;
    }

    public List<DataDeclarationCobolStatement> getChildrenStatements() {
        return childrenStatements;
    }

    public void addChildrenStatement(DataDeclarationCobolStatement childStatement) {
        childrenStatements.add(childStatement);
    }
}
