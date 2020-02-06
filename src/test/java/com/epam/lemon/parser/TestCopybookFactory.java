package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.alphanumeric.AlphanumericDeclarationCobolStatement;
import com.epam.lemon.statement.group.GroupDataDeclarationCobolStatement;
import com.epam.lemon.statement.numeric.IntegerDeclarationCobolStatement;
import com.epam.lemon.statement.numeric.computational.CompDataDeclarationStatement;
import java.util.ArrayList;
import java.util.List;

class TestCopybookFactory {

  private static final String VALID_TEST_PATH = "src/test/resources/valid";
  private static final String INVALID_TEST_PATH = "src/test/resources/invalid";

  static TestCopybookCharacteristics buildCopybookWithAlphanumericDefinition() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    cobolStatements.add(buildAlphanumericStatement(5, 2, "EMP-SURNAME"));
    cobolStatements.add(buildAlphanumericStatement(5, 1, "EMP-POSITION"));
    return buildCopybookCharacteristics(cobolStatements, "/alpha/ALPHA.cpy");
  }

  private static TestCopybookCharacteristics buildCopybookCharacteristics(
                                                List<DataDeclarationCobolStatement> cobolStatements,
                                                String copybookPath) {

    return new TestCopybookCharacteristics(VALID_TEST_PATH + copybookPath, new Copybook(cobolStatements));
  }

  private static AlphanumericDeclarationCobolStatement buildAlphanumericStatement(int level,
                                                                                  int length,
                                                                                  String name) {

    return new AlphanumericDeclarationCobolStatement(level, length, name);
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidAlphanumericFieldDeclaration() {
    return buildInvalidCopybookCharacteristics("/alpha/ALPHAINV.cpy");
  }

  private static TestCopybookCharacteristics buildInvalidCopybookCharacteristics(String s) {
    return new TestCopybookCharacteristics(INVALID_TEST_PATH + s);
  }

  static TestCopybookCharacteristics buildCopybookWithNumericDefinition() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    cobolStatements.add(buildIntegerStatement(5, 2, "EMP-SURNAME"));
    cobolStatements.add(buildIntegerStatement(5, 1, "EMP-POSITION"));
    return buildCopybookCharacteristics(cobolStatements, "/numeric/NUM.cpy");
  }

  private static IntegerDeclarationCobolStatement buildIntegerStatement(int level,
                                                                        int length,
                                                                        String name) {

    return new IntegerDeclarationCobolStatement(level, length, name);
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidNumericFieldDeclaration() {
    return buildInvalidCopybookCharacteristics("/numeric/NUMINV.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithGroupFieldDefinition() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    GroupDataDeclarationCobolStatement parent = buildGroupStatement(1, "EMP-RECORD");
    parent.addChildrenStatement(buildIntegerStatement(5, 2, "EMP-NAME"));
    cobolStatements.add(parent);
    return buildCopybookCharacteristics(cobolStatements, "/group/GROUP.cpy");
  }

  private static GroupDataDeclarationCobolStatement buildGroupStatement(Integer level, String name) {
    return new GroupDataDeclarationCobolStatement(level, name);
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidGroupFieldDeclaration() {
    return buildInvalidCopybookCharacteristics("/group/GROUPINV.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithCompFields() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    cobolStatements.add(buildCompStatement(6));
    return buildCopybookCharacteristics(cobolStatements, "/numeric/computational/COMP.cpy");
  }

  private static CompDataDeclarationStatement buildCompStatement(int level) {
    return new CompDataDeclarationStatement(level, 2, "EMP-SALARY");
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidCompFieldDeclaration() {
    return buildInvalidCopybookCharacteristics("/numeric/computational/COMPINVDCLR.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidCompFieldDataFormatDeclaration() {
    return buildInvalidCopybookCharacteristics("/numeric/computational/COMPINVFMT.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithComp1Value() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    cobolStatements.add(buildCompStatement(10));
    return buildCopybookCharacteristics(cobolStatements, "/numeric/computational/COMP-1.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidComp1Value() {
    return buildInvalidCopybookCharacteristics("/numeric/computational/COMP-1INV.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithComp2Value() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    cobolStatements.add(buildCompStatementWithDefaultValue(10, 98));
    return buildCopybookCharacteristics(cobolStatements, "/numeric/computational/COMP-2.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidComp2Value() {
    return buildInvalidCopybookCharacteristics("/numeric/computational/COMP-2INV.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidComp2ValueFormat() {
    return buildInvalidCopybookCharacteristics("/numeric/computational/COMP-2INVFMT.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithComp3Value() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    cobolStatements.add(buildCompStatementWithDefaultValue(10, 8));
    return buildCopybookCharacteristics(cobolStatements, "/numeric/computational/COMP-3.cpy");
  }

  private static CompDataDeclarationStatement buildCompStatementWithDefaultValue(int level,
                                                                                 int defaultValue) {

    return new CompDataDeclarationStatement(level, 2, "EMP-SALARY", defaultValue);
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidComp3Value() {
    return buildInvalidCopybookCharacteristics("/numeric/computational/COMP-3INV.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithDefaultValues() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    cobolStatements.add(new AlphanumericDeclarationCobolStatement(5, 2, "EMP-SURNAME", "AA"));
    cobolStatements.add(buildCompStatementWithDefaultValue(5, 1));
    cobolStatements.add(new IntegerDeclarationCobolStatement(5, 1, "EMP-POSITION", 0));
    return buildCopybookCharacteristics(cobolStatements, "/default-value/DEFVALUE.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidDefaultValueLength() {
    return buildInvalidCopybookCharacteristics("/default-value/DEFVALUEINV.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithInvalidDefaultValueFormat() {
    return buildInvalidCopybookCharacteristics("/default-value/DEFVALUEINVFMT.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithNestedGroups() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    GroupDataDeclarationCobolStatement parentStatement = buildGroupStatement(1, "EMP-RECORD");
    GroupDataDeclarationCobolStatement subGroupStatement = buildGroupStatement(5, "EMP-GROUP");
    subGroupStatement.addChildrenStatement(buildIntegerStatement(15, 2, "EMP-NAME"));
    parentStatement.addChildrenStatement(subGroupStatement);
    cobolStatements.add(parentStatement);
    return buildCopybookCharacteristics(cobolStatements, "/group/nested-group/NESGROUP.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithNestedSubGroups() {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    GroupDataDeclarationCobolStatement parentStatement = buildGroupStatement(1, "EMP-RECORD");
    GroupDataDeclarationCobolStatement subGroupStatement = buildGroupStatement(5, "EMP-GROUP");
    subGroupStatement.addChildrenStatement(buildIntegerStatement(15, 2, "EMP-NAME"));
    parentStatement.addChildrenStatement(subGroupStatement);
    subGroupStatement = buildGroupStatement(5, "EMP-GROUP2");
    subGroupStatement.addChildrenStatement(buildAlphanumericStatement(10, 2, "EMP-SURNAME"));
    parentStatement.addChildrenStatement(subGroupStatement);
    cobolStatements.add(parentStatement);
    return buildCopybookCharacteristics(cobolStatements, "/group/nested-group/NESGROUP2.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithSeveralNestedSubGroups() {
    List<DataDeclarationCobolStatement> cobolStatements = buildParentStatementWithSubGroups("EMP-RECORD");
    return buildCopybookCharacteristics(cobolStatements, "/group/nested-group/NESGROUP3.cpy");
  }

  private static List<DataDeclarationCobolStatement> buildParentStatementWithSubGroups(String rootName) {
    List<DataDeclarationCobolStatement> cobolStatements = new ArrayList<>();
    GroupDataDeclarationCobolStatement parentStatement = buildGroupStatement(1, rootName);
    GroupDataDeclarationCobolStatement subGroupStatement = buildGroupStatement(5, "EMP-GROUP");
    subGroupStatement.addChildrenStatement(buildIntegerStatement(15, 2, "EMP-NAME"));
    GroupDataDeclarationCobolStatement secondLevelSubGroup = buildGroupStatement(15, "EMP-SUBGROUP");
    secondLevelSubGroup.addChildrenStatement(buildIntegerStatement(20, 2, "EMP-NAME"));
    subGroupStatement.addChildrenStatement(secondLevelSubGroup);
    parentStatement.addChildrenStatement(subGroupStatement);
    subGroupStatement = buildGroupStatement(5, "EMP-GROUP2");
    subGroupStatement.addChildrenStatement(buildAlphanumericStatement(10, 2, "EMP-SURNAME"));
    parentStatement.addChildrenStatement(subGroupStatement);
    cobolStatements.add(parentStatement);
    return cobolStatements;
  }

  static TestCopybookCharacteristics buildCopybookWithSeveralGeneralGroups() {
    List<DataDeclarationCobolStatement> cobolStatements = buildParentStatementWithSubGroups("EMP-RECORD");
    cobolStatements.addAll(buildParentStatementWithSubGroups("EMP-RECORD2"));
    return buildCopybookCharacteristics(cobolStatements, "/group/nested-group/NESGROUP4.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithGroupWithoutAnyChildren() {
    return buildInvalidCopybookCharacteristics("/group/nested-group/NESGROUPINV.cpy");
  }

  static TestCopybookCharacteristics buildCopybookWithSubGroupWithoutChildren() {
    return buildInvalidCopybookCharacteristics("/group/nested-group/NESGROUPINV2.cpy");
  }
}
