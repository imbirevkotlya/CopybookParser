package com.epam.lemon.statement;

public class CompDataDeclarationStatement implements RegularDataDeclarationCobolStatement {

    private final Integer length;
    private final Integer level;
    private final String name;

    public CompDataDeclarationStatement(Integer level, Integer length, String name) {
        this.length = length;
        this.level = level;
        this.name = name;
    }

    @Override
    public Integer getLength() {
        return length;
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public StatementType getStatementType() {
        return StatementType.COMP_STATEMENT;
    }
}
