package com.epam.lemon.model.length;

public class FixedLengthDeterminationStrategy implements LengthDeterminationStrategy {

    private final Integer length;

    public FixedLengthDeterminationStrategy(Integer length) {
        this.length = length;
    }

    @Override
    public Integer getArrayLength() {
        return length;
    }
}
