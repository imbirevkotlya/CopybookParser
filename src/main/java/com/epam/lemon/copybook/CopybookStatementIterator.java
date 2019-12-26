package com.epam.lemon.copybook;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The wrapper class for the COBOL copybook, provided the ability to iterate the copybook records, divide them by the
 * '.' and trim all line separators and extra spaces.
 *
 * Generally, it is an usual iterator pattern implementation for the byte array iteration.
 *
 * Example of creating:
 *
 * byte[] copybookFile = Files.readAllBytes(Path.of("path_to_file"));
 * CopybookStatementIterator copybookStatementIterator = new CopybookStatementIterator(copybookFile);
 *
 * Example of using:
 *
 * while (copybookStatementIterator.hasNext()) {
 *  String copybookStatement = copybookStatementIterator.next();
 *  doSmt(copybookStatement);
 * }
 *
 */
public class CopybookStatementIterator implements Iterator<String> {

    private static final Charset ASCII = Charset.forName("windows-1251");
    /**
     * The '.' keyword from the ASCII table encoding.
     */
    private static final byte STATEMENT_DELIMITER = 46;
    private static final String SPACE = " ";
    private static final String LINE_SEPARATOR = System.lineSeparator();

    private final byte[] copybookValue;
    private final List<String> statements;
    private final Integer statementsAmount;
    private Integer cursor;

    /**
     * Main wrapper constructor with the iterator cursor activation with statements parsing from the provided copybook.
     * @param copybookValue the byte array of the copybook (only ASCII encoding is supported)
     */
    public CopybookStatementIterator(byte[] copybookValue) {
        this.copybookValue = copybookValue;
        statements = new ArrayList<>();
        initStatements();
        statementsAmount = statements.size();
        cursor = 0;
    }

    private void initStatements() {
        ByteBuffer buffer = ByteBuffer.allocate(copybookValue.length);
        for (byte b : copybookValue) {
            if (b == STATEMENT_DELIMITER) {
                byte[] rawStatement = buffer.array();
                String statement = new String(rawStatement, ASCII);
                statements.add(prepareStatement(statement));
                buffer = ByteBuffer.allocate(copybookValue.length);
            } else {
                buffer.put(b);
            }
        }
    }

    private String prepareStatement(String statement) {
        return changeAllNewLinesToSpaces(statement);
    }

    private String changeAllNewLinesToSpaces(String statement) {
        return statement.trim().replaceAll(LINE_SEPARATOR, SPACE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasNext() {
        return cursor < statementsAmount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String next() {
        if (hasNext()) {
            return statements.get(cursor++);
        }
        throw new NoSuchElementException();
    }
}
