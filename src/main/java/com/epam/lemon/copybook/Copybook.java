package com.epam.lemon.copybook;

import com.epam.lemon.statement.DataDeclarationCobolStatement;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Copybook copybook = (Copybook) o;
        return cobolStatements.equals(copybook.cobolStatements);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cobolStatements);
    }
}
