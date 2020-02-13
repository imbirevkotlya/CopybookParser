package com.epam.lemon.copybook;

import com.epam.lemon.statement.DataDeclarationCobolStatement;
import com.epam.lemon.statement.RegularDataDeclarationCobolStatement;
import com.epam.lemon.statement.alphanumeric.AlphanumericDeclarationCobolStatement;
import com.epam.lemon.statement.group.GroupDataDeclarationCobolStatement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class CopybookTest {

  private static final String GROUP_NAME = "TEST";
  private static final String SUBGROUP_NAME = "TEST2";
  private static final String CHILD_NAME = "CHILD";

  private static final int GROUP_LEVEL = 1;
  private static final int SUBGROUP_LEVEL = 5;
  private static final int CHILD_LEVEL = 10;

  private static final int LENGTH = 5;

  private static final int EMPTY = 0;

  private Copybook copybook;

  @Test
  public void getFlatStructure() {
    List<DataDeclarationCobolStatement> children = getChildren();
    copybook = new Copybook(Collections
        .singletonList(new GroupDataDeclarationCobolStatement(GROUP_LEVEL, GROUP_NAME, children)));

    List<RegularDataDeclarationCobolStatement> actualStatements = copybook
        .getValueContainsCobolStatements();

    Assert.assertEquals(children, actualStatements);
  }

  private List<DataDeclarationCobolStatement> getChildren() {
    List<DataDeclarationCobolStatement> children = new ArrayList<>();
    children.add(new AlphanumericDeclarationCobolStatement(CHILD_LEVEL, LENGTH, CHILD_NAME));
    return children;
  }

  @Test
  public void getFlatStructure_nestedGroups() {
    List<DataDeclarationCobolStatement> firstLevelChildren = new ArrayList<>();
    List<DataDeclarationCobolStatement> secondLevelChildren = getChildren();
    firstLevelChildren.add(new GroupDataDeclarationCobolStatement(SUBGROUP_LEVEL, SUBGROUP_NAME, secondLevelChildren));
    copybook = new Copybook(Collections
        .singletonList(new GroupDataDeclarationCobolStatement(GROUP_LEVEL, GROUP_NAME, firstLevelChildren)));

    List<RegularDataDeclarationCobolStatement> actualStatements = copybook.getValueContainsCobolStatements();

    Assert.assertEquals(secondLevelChildren, actualStatements);
  }

  @Test
  public void getFlatStructure_severalGroups() {
    List<DataDeclarationCobolStatement> secondLevelChildren = getChildren();
    List<DataDeclarationCobolStatement> firstLevelChildren = new ArrayList<>();
    firstLevelChildren.add(new GroupDataDeclarationCobolStatement(SUBGROUP_LEVEL, SUBGROUP_NAME, secondLevelChildren));
    firstLevelChildren.add(new GroupDataDeclarationCobolStatement(SUBGROUP_LEVEL, SUBGROUP_NAME, secondLevelChildren));
    copybook = new Copybook(Collections
        .singletonList(new GroupDataDeclarationCobolStatement(GROUP_LEVEL, GROUP_NAME, firstLevelChildren)));

    List<RegularDataDeclarationCobolStatement> actualStatements = copybook.getValueContainsCobolStatements();

    List<DataDeclarationCobolStatement> expected = new ArrayList<>(secondLevelChildren);
    expected.addAll(secondLevelChildren);
    Assert.assertEquals(expected, actualStatements);
  }

  @Test
  public void getFlatStructure_emptyStatements() {
    Copybook emptyCopybook = new Copybook(Collections.emptyList());

    List<RegularDataDeclarationCobolStatement> actualStatements = emptyCopybook.getValueContainsCobolStatements();

    Assert.assertEquals(EMPTY, actualStatements.size());
  }

  @Test
  public void getFlatStructure_onlyValueContainsStatements() {
    List<DataDeclarationCobolStatement> valueContainsStatements = getChildren();

    copybook = new Copybook(valueContainsStatements);

    Assert.assertEquals(valueContainsStatements, copybook.getValueContainsCobolStatements());
  }

  @Test
  public void getRecordLength() {
    List<DataDeclarationCobolStatement> statements = getChildren();

    copybook = new Copybook(statements);

    Assert.assertEquals(LENGTH, copybook.getRecordLength().intValue());
  }

  @Test
  public void getRecordLength_withGroupStatement() {
    List<DataDeclarationCobolStatement> children = getChildren();
    copybook = new Copybook(Collections
        .singletonList(new GroupDataDeclarationCobolStatement(GROUP_LEVEL, GROUP_NAME, children)));

    Integer recordLength = copybook.getRecordLength();

    Assert.assertEquals(LENGTH, recordLength.intValue());
  }

  @Test
  public void getRecordLength_withNestedGroupStatements() {
    List<DataDeclarationCobolStatement> firstLevelChildren = new ArrayList<>();
    List<DataDeclarationCobolStatement> secondLevelChildren = getChildren();
    firstLevelChildren.add(new GroupDataDeclarationCobolStatement(SUBGROUP_LEVEL, SUBGROUP_NAME, secondLevelChildren));
    copybook = new Copybook(Collections
        .singletonList(new GroupDataDeclarationCobolStatement(GROUP_LEVEL, GROUP_NAME, firstLevelChildren)));

    Integer recordLength = copybook.getRecordLength();

    Assert.assertEquals(LENGTH, recordLength.intValue());
  }

  @Test
  public void getRecordLength_withSeveralGroupStatements() {
    List<DataDeclarationCobolStatement> secondLevelChildren = getChildren();
    List<DataDeclarationCobolStatement> firstLevelChildren = new ArrayList<>();
    firstLevelChildren.add(new GroupDataDeclarationCobolStatement(SUBGROUP_LEVEL, SUBGROUP_NAME, secondLevelChildren));
    firstLevelChildren.add(new GroupDataDeclarationCobolStatement(SUBGROUP_LEVEL, SUBGROUP_NAME, secondLevelChildren));
    copybook = new Copybook(Collections
        .singletonList(new GroupDataDeclarationCobolStatement(GROUP_LEVEL, GROUP_NAME, firstLevelChildren)));

    Integer recordLength = copybook.getRecordLength();

    Assert.assertEquals(LENGTH + LENGTH, recordLength.intValue());
  }

  @Test
  public void getRecordLength_emptyStatements() {
    Copybook emptyCopybook = new Copybook(Collections.emptyList());

    Assert.assertEquals(EMPTY, emptyCopybook.getRecordLength().intValue());
  }
}
