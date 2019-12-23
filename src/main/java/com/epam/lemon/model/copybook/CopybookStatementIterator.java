package com.epam.lemon.model.copybook;

import java.util.Iterator;

public class CopybookStatementIterator implements Iterator<String> {

    private final byte[] copybookValue;

    public CopybookStatementIterator(byte[] copybookValue) {
        this.copybookValue = copybookValue;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public String next() {
        return null;
    }

    public byte[] getCopybookValue() {
        return copybookValue;
    }
}
