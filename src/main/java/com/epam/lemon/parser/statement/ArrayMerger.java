package com.epam.lemon.parser.statement;

import java.util.Arrays;

/**
 * Utility class with simple array merging feature
 */
class ArrayMerger {

    private final String[] firstArray;
    private final String[] secondArray;

    ArrayMerger(String[] firstArray, String[] secondArray) {
        this.firstArray = firstArray;
        this.secondArray = secondArray;
    }

    /**
     * Main utility method to merge two arrays into one
     * @return the new array with new length
     */
    String[] merge() {
        int fullLength = secondArray.length + firstArray.length;
        String[] fullArray = Arrays.copyOf(firstArray, fullLength);
        int initialPosition = 0;
        System.arraycopy(secondArray, initialPosition, fullArray, firstArray.length, secondArray.length);
        return fullArray;
    }
}
