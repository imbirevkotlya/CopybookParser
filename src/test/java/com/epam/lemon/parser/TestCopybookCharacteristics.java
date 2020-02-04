package com.epam.lemon.parser;

import com.epam.lemon.copybook.Copybook;
import com.epam.lemon.copybook.CopybookStatementIterator;

import com.epam.lemon.copybook.StatementIterator;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class TestCopybookCharacteristics {

    private final String copybookPath;
    private final Copybook expectedCopybook;

    TestCopybookCharacteristics(String copybookPath, Copybook expectedCopybook) {
        this.copybookPath = copybookPath;
        this.expectedCopybook = expectedCopybook;
    }

    TestCopybookCharacteristics(String copybookPath) {
        this.copybookPath = copybookPath;
        this.expectedCopybook = null;
    }

    StatementIterator createIteratorFromFile() throws IOException {
        return new CopybookStatementIterator(Files.readAllBytes(Path.of(copybookPath)));
    }

    Copybook getExpectedCopybook() {
        return expectedCopybook;
    }
}