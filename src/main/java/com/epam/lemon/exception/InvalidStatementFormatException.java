package com.epam.lemon.exception;

/**
 * General class for the copybook validation failures.
 * All the rules of the copybook parsing are described in the CopybookParser class.
 */
public class InvalidStatementFormatException extends RuntimeException {

    /**
     * Constructor, provides the message of the broken copybook statement format.
     * @param statement the statement, where the problem was detected
     */
    public InvalidStatementFormatException(String statement) {
        super("Wrong statement format: " + statement);
    }
}
