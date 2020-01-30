package com.epam.lemon.parser.statement;

import java.util.Arrays;

class ArrayMerger {

    private final String[] firstArray;
    private final String[] secondArray;

    ArrayMerger(String[] firstArray, String[] secondArray) {
        this.firstArray = firstArray;
        this.secondArray = secondArray;
    }

    String[] merge() {
        int allStatementAttributeFormatsLength = secondArray.length + firstArray.length;
        String[] allStatementAttributeFormats = Arrays.copyOf(firstArray, allStatementAttributeFormatsLength);
        System.arraycopy(secondArray, 0, allStatementAttributeFormats, firstArray.length, secondArray.length);
        return allStatementAttributeFormats;
    }
}
