package com.epam.lemon.statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Model represents the cobol group statements.
 *
 * Description can be found here - mainframegurukul.com/tutorials/programming/cobol/cobol-elementary-group-data-items.html
 *
 */
public class GroupDataDeclarationCobolStatement implements DataDeclarationCobolStatement {

    private final Integer level;
    private final String name;
    private final List<DataDeclarationCobolStatement> childrenStatements;

    /**
     * Main statement constructor.
     * @param level is a statement level
     * @param name is a field length
     */
    public GroupDataDeclarationCobolStatement(Integer level, String name) {
        this.level = level;
        this.name = name;
        this.childrenStatements = new ArrayList<>();
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
        return StatementType.GROUP_STATEMENT;
    }

    public List<DataDeclarationCobolStatement> getChildrenStatements() {
        return childrenStatements;
    }

    public void addChildrenStatement(DataDeclarationCobolStatement childStatement) {
        childrenStatements.add(childStatement);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupDataDeclarationCobolStatement that = (GroupDataDeclarationCobolStatement) o;
        return level.equals(that.level) &&
                name.equals(that.name) &&
                childrenStatements.equals(that.childrenStatements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, name, childrenStatements);
    }
}
