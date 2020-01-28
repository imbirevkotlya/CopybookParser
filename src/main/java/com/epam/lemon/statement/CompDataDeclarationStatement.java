package com.epam.lemon.statement;

import java.util.Objects;

/**
 * The class represents COBOL computational statement.
 *
 * Description can be found here: https://www.mainframestechhelp.com/tutorials/cobol/cobol-computation.htm
 */
public class CompDataDeclarationStatement implements RegularDataDeclarationCobolStatement {

    private final Integer length;
    private final Integer level;
    private final String name;

    /**
     * Main statement constructor.
     * @param level is a statement level
     * @param length is a field length
     * @param name is a field length
     */
    public CompDataDeclarationStatement(Integer level, Integer length, String name) {
        this.length = length;
        this.level = level;
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
        return StatementType.COMP_STATEMENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompDataDeclarationStatement that = (CompDataDeclarationStatement) o;
        return length.equals(that.length) &&
                level.equals(that.level) &&
                name.equals(that.name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(length, level, name);
    }
}
