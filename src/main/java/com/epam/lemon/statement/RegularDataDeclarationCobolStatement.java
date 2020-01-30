package com.epam.lemon.statement;

/**
 * Main interface specified for the regular statements (not group statements).
 */
public interface RegularDataDeclarationCobolStatement extends DataDeclarationCobolStatement {

    /**
     * The field definition should contains the length description, like this (for example): XXXX.
     * @return the field declaration length
     */
    Integer getLength();

    /**
     * Method to return the field default value, if it was specified.
     * Example of pattern to specify:
     *
     * 01 NAME PIC 99 VALUE 12.
     *
     * The value will be returned is 12.
     *
     * If no default value was specified - the statement type default value will be returned:
     * Alphanumeric - " "
     * Integer - 0
     * Computational - 0
     *
     * Example:
     * 01 NAME PIC 99.
     *
     * The value will be returned: 00.
     *
     * @return the field default value
     */
    String getDefaultValue();

}
