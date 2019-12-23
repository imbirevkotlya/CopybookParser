package com.epam.lemon.model.statement;

import java.util.List;

public class GroupDataDeclarationCobolStatement implements DataDeclarationCobolStatement {

    private final Integer level;
    private final String name;
    private final List<DataDeclarationCobolStatement> childrenStatements;

    public static GroupDataDeclarationCobolStatement buildStatement(String groupStatement) {
        return new GroupDataDeclarationCobolStatement(null, null, null);
    }

    private GroupDataDeclarationCobolStatement(Integer level, String name, List<DataDeclarationCobolStatement> childrenStatements) {
        this.level = level;
        this.name = name;
        this.childrenStatements = childrenStatements;
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
}
