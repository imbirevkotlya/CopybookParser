package com.epam.lemon.model.statement;

import com.epam.lemon.model.length.LengthDeterminationStrategy;

public class ArrayRegularDataDeclarationCobolStatement implements RegularDataDeclarationCobolStatement {

    private final LengthDeterminationStrategy lengthDeterminationStrategy;
    private final Integer level;
    private final String name;

    public static ArrayRegularDataDeclarationCobolStatement buildStatement(String statement) {
        return new ArrayRegularDataDeclarationCobolStatement(null, null, null);
    }

    private ArrayRegularDataDeclarationCobolStatement(LengthDeterminationStrategy lengthDeterminationStrategy, Integer level, String name) {
        this.lengthDeterminationStrategy = lengthDeterminationStrategy;
        this.level = level;
        this.name = name;
    }

    @Override
    public Integer getLength() {
        return lengthDeterminationStrategy.getArrayLength();
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
