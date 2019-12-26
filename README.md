# Copybook parser

The project is about parsing the COBOL copybook into the Java model, providing the ability to interact with it.

## Getting started

Prerequisites:

1. You need to have Java 11 installed on your machine
2. You need to have Gradle 5 installed on your machine
3. You need to prepare your COBOL copybooks according to rules, described below

### Installing

- Clone the project from the version control system:

`git clone ...`

- Go to the project root:

`cd test`

- execute the gradle task to build the application jar file:

`gradle clean jar --info`

- now, you can find the jar file in this path: <project_root>/build/libs/---.jar

### Running tests

- execute the gradle task to run tests:

`gradle clean test` 

### Build with

- Gradle (dependency management and build system)

### Authors

- Imbirev Nikolai (creator and supporter)

### Versioning

- Now, the 1.0-SNAPSHOT version is released with simple functions
- In development stage: 1.1-SNAPSHOT version with COMP fields support
- In plans: 1.2-SNAPSHOT version with DEFAULT values support
- ...

### Acknowledges

Supported copybook formats:

- only integer numeric, alphanumeric, group fields are supported
- only field declarations with XXX (not X(2)) are supported
- no default values supported
- no any other digits or symbols supported (even if they in comment zone)
- no comps, arrays, redefines are supported
- no comments are supported
- no nested groups or several groups are supported
- the example of valid copybook can be found in path: <project_root>/src/test/resource/PERSINFO.cpy

### Licensing

The License file can be found in project root (LICENSE.txt)