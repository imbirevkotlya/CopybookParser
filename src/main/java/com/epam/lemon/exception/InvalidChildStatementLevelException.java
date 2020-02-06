package com.epam.lemon.exception;

/**
 * The exception represents the invalid child statement in copybook declaration.
 */
public class InvalidChildStatementLevelException extends RuntimeException {

    /**
     * Main constructor, builds the final message: Child level is: &childLevel, when the group level is: &group level
     * @param childLevel is a child statement level
     * @param groupLevel is a parent statement level
     */
    public InvalidChildStatementLevelException(String childLevel, Integer groupLevel) {
        super("Child level is: " + childLevel + ", when the group level is: " + groupLevel);
    }
}
