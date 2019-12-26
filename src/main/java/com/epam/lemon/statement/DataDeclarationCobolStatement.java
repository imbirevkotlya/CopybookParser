package com.epam.lemon.statement;


public interface DataDeclarationCobolStatement {

    /**
     * Level of the data statement can be from 01 to 49, also there is special numbers: 66, 77, 88.
     * @return the level of the data declaration cobol statement
     */
    Integer getLevel();

    /**
     * Name of the cobol data statement (can be group name, field name, redefines field and so on).
     * @return the name of the data declaration cobol statement
     */
    String getName();

    /**
     *
     * @return
     */
    StatementType getStatementType();

}
