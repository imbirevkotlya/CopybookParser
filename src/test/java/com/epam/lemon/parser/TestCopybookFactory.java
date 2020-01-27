package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.statement.*;

import java.util.ArrayList;

class TestCopybookFactory {

    static TestCopybookCharacteristics buildCopybookWithAlphanumericDefinition() {
        ArrayList<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new AlphanumericDeclarationCobolStatement(5, 2, "EMP-SURNAME"));
        cobolStatements.add(new AlphanumericDeclarationCobolStatement(5, 1, "EMP-POSITION"));
        return new TestCopybookCharacteristics("src/test/resources/ALPHA.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithNumericDefinition() {
        ArrayList<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new IntegerDeclarationCobolStatement(5, 2, "EMP-SURNAME"));
        cobolStatements.add(new IntegerDeclarationCobolStatement(5, 1, "EMP-POSITION"));
        return new TestCopybookCharacteristics("src/test/resources/NUM.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidNumericFieldDeclaration() {
        return new TestCopybookCharacteristics("src/test/resources/NUMINV.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidAlphanumericFieldDeclaration() {
        return new TestCopybookCharacteristics("src/test/resources/ALPHAINV.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithGroupFieldDefinition() {
        ArrayList<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        GroupDataDeclarationCobolStatement parent = new GroupDataDeclarationCobolStatement(1, "EMP-RECORD");
        parent.addChildrenStatement(new IntegerDeclarationCobolStatement(5, 2, "EMP-NAME"));
        cobolStatements.add(parent);
        return new TestCopybookCharacteristics("src/test/resources/GROUP.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidGroupFieldDeclaration() {
        return new TestCopybookCharacteristics("src/test/resources/GROUPINV.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithCompFields() {
        ArrayList<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new CompDataDeclarationStatement(6, 2, "EMP-SALARY"));
        return new TestCopybookCharacteristics("src/test/resources/COMP.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidCompFieldDeclaration() {
        return new TestCopybookCharacteristics("src/test/resources/COMPINVDCLR.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidCompFieldDataFormatDeclaration() {
        return new TestCopybookCharacteristics("src/test/resources/COMPINVFMT.cpy");
    }
}
