package com.epam.lemon.statement;

/**
 * Main interface of the COBOL copybook statement of the data declaration division (or environment division).
 *
 * Description can be found here:
 *
 * https://www.mainframestechhelp.com/tutorials/cobol/cobol-data-item-declaration.htm
 * https://supportline.microfocus.com/Documentation/books/oc41books/lrcpro.htm
 */
public interface DataDeclarationCobolStatement {

    /**
     * Level of the data statement can be from 01 to 49.
     *
     * Description can be found here - https://www.mainframestechhelp.com/tutorials/cobol/cobol-level-numbers.htm
     *
     * @return the level of the data declaration cobol statement
     */
    Integer getLevel();

    /**
     * Name of the cobol data statement (can be group name, field name, redefines field and so on).
     * @return the name of the data declaration cobol statement
     */
    String getName();

    /**
     * Type of the statement to cast it and use more specific statement implementation abilities.
     * @return the statement type
     */
    StatementType getStatementType();

}
