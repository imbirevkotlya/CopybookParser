package com.epam.lemon.statement;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompDataDeclarationStatement that = (CompDataDeclarationStatement) o;
        return length.equals(that.length) &&
                level.equals(that.level) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, level, name);
    }
}
