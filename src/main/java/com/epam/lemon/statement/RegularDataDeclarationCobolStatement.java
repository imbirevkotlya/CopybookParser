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

}
