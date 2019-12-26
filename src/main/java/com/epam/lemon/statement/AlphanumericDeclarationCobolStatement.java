package com.epam.lemon.statement;

public class AlphanumericDeclarationCobolStatement implements RegularDataDeclarationCobolStatement {

    private final Integer level;
    private final Integer length;
    private final String name;

    public AlphanumericDeclarationCobolStatement(Integer level, Integer length, String name) {
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

    @Override
    public StatementType getStatementType() {
        return StatementType.ALPHANUMERIC_STATEMENT;
    }
}
