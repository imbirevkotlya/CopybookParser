package com.epam.lemon.model.statement;

public class IntegerDeclarationCobolStatement implements RegularDataDeclarationCobolStatement {

    private final Integer level;
    private final Integer length;
    private final String name;

    public IntegerDeclarationCobolStatement(Integer level, Integer length, String name) {
        this.level = level;
        this.length = length;
        this.name = name;
    }

    @Override
    public Integer getLength() {
        return length;
    }

    @Override
    public Integer getDecimalLength() {
        return -1;
    }

    @Override
    public Integer getLevel() {
        return level;
    }

    @Override
    public String getName() {
        return name;
    }
}
