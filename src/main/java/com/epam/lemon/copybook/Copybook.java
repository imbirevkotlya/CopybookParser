package com.epam.lemon.copybook;

import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.GroupDataDeclarationCobolStatement;
import com.epam.lemon.statement.StatementType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
     * Provide a copy of the copybook statements (group statements will contain the children statements inside it).
     * @return the ordered list of the cobol data declaration statements in this copybook
     */
    public List<DataDeclarationCobolStatement> getCobolStatements() {
        return new ArrayList<>(cobolStatements);
    }

    /**
     * Return the statements with the same name as passed. The search will be done in the regular statements and also
     * from the children statements in each group.
     * If statements has the same name - first will be found (from up to down).
     * @param name the name of the statement to find
     * @return the data declaration statement or null (if nothing was found).
     */
    public DataDeclarationCobolStatement getStatementByName(String name) {
        for (DataDeclarationCobolStatement cobolStatement : cobolStatements) {
            if (StatementType.GROUP_STATEMENT.equals(cobolStatement.getStatementType())) {
                GroupDataDeclarationCobolStatement groupStatement = (GroupDataDeclarationCobolStatement) cobolStatement;
                for (DataDeclarationCobolStatement childStatement : groupStatement.getChildrenStatements()) {
                    if (childStatement.getName().equalsIgnoreCase(name)) {
                        return childStatement;
                    }
                }
            }
            if (cobolStatement.getName().equalsIgnoreCase(name)) {
                return cobolStatement;
            }
        }
        return null;
    }

    /**
     * Return the cobol statements from the copybook with the same level as provided.
     * @param level the level to find the statements
     * @return the collection of the items with this level, or empty collection, if such level is not presented
     */
    public List<DataDeclarationCobolStatement> getStatementsByLevel(Integer level) {
        return cobolStatements.stream()
                .filter(statement -> statement.getLevel().equals(level))
                .collect(Collectors.toList());
    }

}
