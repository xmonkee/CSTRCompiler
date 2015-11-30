# Introduction

This compiler compiles the language to the JVM. 

# Testing

## Easy way: 

    $ ./run.sh /tests/add.c
This will compile, assemble, link and run the program
The jasmin and class files will be moved to testout folder
Inspect the output by accessing testout/add.j for example

You can run all the tests by: 
    $ for file in tests/* ; do ./run.sh $file ; done"

## Hard(er) way:

1) Compile a .c file to the jvm language using sbt
    $ sbt "run tests/add.c"

This will generate the file add.j in the current directory

2) Use the jasmine assembler to generate class file
    $ java -jar jasmin.jar add.j

3) Compile the standard library (written in java, containing printd etc)
    $ javac Lib.java

4) Execute program
    $ java add

# Notes

- The typechecking and compilation happen in two seperate sweeps. 
- Compilation will not proceed if there are type errors
- Typechecker is very rigorous
- string + string is allowed, but int + string is not. Any other operations on strings also prohibited.
- There is no preprocessor
- Many of the tests have been modified to remove #define's and #include's
- Strings are not mutable. I have modified erato.c to use `tab = put_char_at(tab, j, '0')`
- You can add new functions to the standard library by modifying Lib.java, but functions should be declared in the c file before usage.
