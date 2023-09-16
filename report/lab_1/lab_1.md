# Topic: SOLID Principles

### Course: Software Design Techniques and Mechanisms
### Author: Botnari Ciprian

## Theory
**SOLID** is a set of five object-oriented design principles intended to make software designs more maintainable, flexible, and easy to understand. The acronym stands for Single Responsibility Principle, Open-Closed Principle, Liskov Substitution Principle, Interface Segregation Principle, and Dependency Inversion Principle. Each principle addresses a specific aspect of software design, such as the organization of responsibilities, the handling of dependencies, and the design of interfaces. By following these principles, software developers can create more modular, testable, and reusable code that is easier to modify and extend over time. These principles are widely accepted and adopted in the software development community and can be applied to any object-oriented programming language.
## Objectives:
1. Study and understand the SOLID Principles.
2. Choose a domain, define its main classes/models/entities and choose the appropriate instantiation mechanisms.
3. Create a sample project that respects SOLID Principles.

## Project
Banking System

## Implementation

### parse
* Initializes the **tokens** variable by calling the *tokenize* method on the **lexer** object.
* Sets the **currentTokenIndex** to 0.
* Invokes the *parseProgram* method to initiate the parsing process and checks for any remaining tokens. If there are remaining tokens, an *IllegalArgumentException* is thrown.
```
public void parse() {
    tokens = lexer.tokenize();
    currentTokenIndex = 0;

    // Start the parsing process
    parseProgram();

    // Check if there are any remaining tokens
    if (currentTokenIndex < tokens.size() - 1) {
      throw new IllegalArgumentException("Unexpected token at the end of the input");
    }
}
```

## Results
For each input the lexer tokens and the AST are displayed.

## Conclusions 
In conclusion, I have successfully achieved the objectives of the laboratory, which were to familiarize myself with parsing and its programming implementation, as well as gain an understanding of Abstract Syntax Trees (ASTs). Throughout this journey, I have expanded my knowledge and skills in these areas.

