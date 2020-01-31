package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.statement.*;

import java.util.ArrayList;
import java.util.List;

class TestCopybookFactory {

    static TestCopybookCharacteristics buildCopybookWithAlphanumericDefinition() {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new AlphanumericDeclarationCobolStatement(5, 2, "EMP-SURNAME"));
        cobolStatements.add(new AlphanumericDeclarationCobolStatement(5, 1, "EMP-POSITION"));
        return new TestCopybookCharacteristics("src/test/resources/ALPHA.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithNumericDefinition() {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
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
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        GroupDataDeclarationCobolStatement parent = new GroupDataDeclarationCobolStatement(1, "EMP-RECORD");
        parent.addChildrenStatement(new IntegerDeclarationCobolStatement(5, 2, "EMP-NAME"));
        cobolStatements.add(parent);
        return new TestCopybookCharacteristics("src/test/resources/GROUP.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidGroupFieldDeclaration() {
        return new TestCopybookCharacteristics("src/test/resources/GROUPINV.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithCompFields() {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new CompDataDeclarationStatement(6, 2, "EMP-SALARY"));
        return new TestCopybookCharacteristics("src/test/resources/COMP.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidCompFieldDeclaration() {
        return new TestCopybookCharacteristics("src/test/resources/COMPINVDCLR.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidCompFieldDataFormatDeclaration() {
        return new TestCopybookCharacteristics("src/test/resources/COMPINVFMT.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithComp1Value() {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new CompDataDeclarationStatement(10, 2, "EMP-SALARY"));
        return new TestCopybookCharacteristics("src/test/resources/COMP-1.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidComp1Value() {
        return new TestCopybookCharacteristics("src/test/resources/COMP-1INV.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithComp2Value() {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new CompDataDeclarationStatement(10, 2, "EMP-SALARY", 98));
        return new TestCopybookCharacteristics("src/test/resources/COMP-2.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidComp2Value() {
        return new TestCopybookCharacteristics("src/test/resources/COMP-2INV.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidComp2ValueFormat() {
        return new TestCopybookCharacteristics("src/test/resources/COMP-2INVFMT.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithComp3Value() {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new CompDataDeclarationStatement(10, 2, "EMP-SALARY", 8));
        return new TestCopybookCharacteristics("src/test/resources/COMP-3.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidComp3Value() {
        return new TestCopybookCharacteristics("src/test/resources/COMP-3INV.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithDefaultValues() {
        List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
        cobolStatements.add(new AlphanumericDeclarationCobolStatement(5, 2, "EMP-SURNAME", "AA"));
        cobolStatements.add(new CompDataDeclarationStatement(5, 2, "EMP-SALARY", 1));
        cobolStatements.add(new IntegerDeclarationCobolStatement(5, 1, "EMP-POSITION", 0));
        return new TestCopybookCharacteristics("src/test/resources/DEFVALUE.cpy", new Copybook(cobolStatements));
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidDefaultValueLength() {
        return new TestCopybookCharacteristics("src/test/resources/DEFVALUEINV.cpy");
    }

    static TestCopybookCharacteristics buildCopybookWithInvalidDefaultValueFormat() {
        return new TestCopybookCharacteristics("src/test/resources/DEFVALUEINVFMT.cpy");
    }
}
