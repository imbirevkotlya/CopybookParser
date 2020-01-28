package com.epam.lemon.statement;

import java.util.Objects;

/**
 * Model represents the cobol alphanumeric statements.
 *
 * Description can be found here - https://www.mainframestechhelp.com/tutorials/cobol/cobol-alphanumeric-data-type.htm
 */
public class AlphanumericDeclarationCobolStatement implements RegularDataDeclarationCobolStatement {

    private final Integer level;
    private final Integer length;
    private final String name;

    /**
     * Main statement constructor.
     * @param level is a statement level
     * @param length is a field length
     * @param name is a field length
     */
    public AlphanumericDeclarationCobolStatement(Integer level, Integer length, String name) {
        this.level = level;
        this.length = length;
        this.name = name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getLength() {
        return length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer getLevel() {
        return level;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public StatementType getStatementType() {
        return StatementType.ALPHANUMERIC_STATEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlphanumericDeclarationCobolStatement that = (AlphanumericDeclarationCobolStatement) o;
        return level.equals(that.level) &&
                length.equals(that.length) &&
                name.equals(that.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(level, length, name);
    }
}
