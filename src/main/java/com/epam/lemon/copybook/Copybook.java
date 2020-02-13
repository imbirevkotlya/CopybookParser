package com.epam.lemon.copybook;

import com.epam.lemon.PublicApi;
import com.epam.lemon.statement.DataDeclarationCobolStatement;

import com.epam.lemon.statement.RegularDataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;
import com.epam.lemon.statement.group.GroupDataDeclarationCobolStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Illustrates the main COBOL business object for data representing.
 *
 * Generally, Copybook is for reducing the code duplication and reusing of data declaration in the COBOL sources.
 *
 * The descriptive link is - http://docs.attachmate.com/verastream/vbi/5.0/com.attachmate.eclipse.transaction.designer.help/tasks/copybook_ov.html
 * And also - https://www.tutorialspoint.com/cobol/cobol_data_layout.htm
 *
 * The copybook contains data declaration statements (delimited by special COBOL keyword - '.').
 *
 * Example of creating this class:
 *
 * byte[] copybookFile = Files.readAllBytes(Path.of("path_to_file"));
 * CopybookStatementIterator copybookStatementIterator = new CopybookStatementIterator(copybookFile);
 * CopybookParser copybookParser = new CopybookParser();
 * Copybook copybook = copybookParser.parse(copybookStatementIterator);
 *
 * Another option to create:
 *
 * List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
 * cobolStatements.add(new GroupDataDeclarationCobolStatement(1, "GROUP"));
 * cobolStatements.add(new IntegerDeclarationCobolStatement(5, 10, "INTEGER"));
 * cobolStatements.add(new AlphanumericDeclarationCobolStatement(5, 10, "ALPHA"));
 * Copybook copybook = new Copybook(cobolStatements);
 *
 * Options to interact with copybook:
 *
 * List<DataDeclarationCobolStatement> cobolStatements = copybook.getCobolStatements();
 * for (DataDeclarationCobolStatement cobolStatement : cobolStatements) {
 *   System.out.println(cobolStatement.getLevel());
 *   System.out.println(cobolStatement.getName());
 *   System.out.println(cobolStatement.getStatementType());
 * }
 *
 * DataDeclarationCobolStatement statementByName = copybook.getStatementByName("NAME");
 *
 * List<DataDeclarationCobolStatement> statementsByLevel = copybook.getStatementsByLevel(1);
 *
 */
public class Copybook {

    private final List<DataDeclarationCobolStatement> cobolStatements;

    /**
     * Copybook class initializer constructor.
     * @param cobolStatements is a list of copybook statements divided by '.'
     */
    public Copybook(List<DataDeclarationCobolStatement> cobolStatements) {
        this.cobolStatements = cobolStatements;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copybook copybook = (Copybook) o;
        return cobolStatements.equals(copybook.cobolStatements);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(cobolStatements);
    }

    /**
     * Method, returns the all copybook statements declaration.
     * @return the all copybook internals.
     */
    @PublicApi
    public List<DataDeclarationCobolStatement> getCobolStatements() {
       return new ArrayList<>(cobolStatements);
    }

    /**
     * The main purpose of the method is returning the flat copybook structure field declarations,
     * which contains real value definition, not grouping or something else.
     *
     * For example:
     * If copybook contains one group statement with several child declarations, the children
     * statement declarations will be returned.
     *
     * @return the value contains flat field declaration list
     */
    @PublicApi
    public List<RegularDataDeclarationCobolStatement> getValueContainsCobolStatements() {
        return getRegularDataDeclarationCobolStatements(getCobolStatements());
    }

    private List<RegularDataDeclarationCobolStatement> getGroupFlatStructure(
                                               GroupDataDeclarationCobolStatement parentStatement) {

        return getRegularDataDeclarationCobolStatements(parentStatement.getChildrenStatements());
    }

    private List<RegularDataDeclarationCobolStatement> getRegularDataDeclarationCobolStatements(
                                                   List<DataDeclarationCobolStatement> statements) {

        List<RegularDataDeclarationCobolStatement> regularStatements = new ArrayList<>();
        for (DataDeclarationCobolStatement cobolStatement : statements) {
            if (isGroupStatement(cobolStatement)) {
                regularStatements
                    .addAll(getGroupFlatStructure((GroupDataDeclarationCobolStatement) cobolStatement));
            } else {
                regularStatements.add((RegularDataDeclarationCobolStatement) cobolStatement);
            }
        }
        return regularStatements;
    }

    private boolean isGroupStatement(DataDeclarationCobolStatement cobolStatement) {
        return StatementType.GROUP_STATEMENT.equals(cobolStatement.getStatementType());
    }

    /**
     * Method to return the record length of the copybook.
     * @return the symbols length of one record in dataset, which this copybook can describe.
     */
    @PublicApi
    public Integer getRecordLength() {
        List<RegularDataDeclarationCobolStatement> leafStatements = getValueContainsCobolStatements();
        Integer recordLength = 0;
        for (RegularDataDeclarationCobolStatement leafStatement : leafStatements) {
            Integer fieldLength = leafStatement.getLength();
            recordLength += fieldLength;
        }
        return recordLength;
    }
}
