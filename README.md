
# Calculator Unit & Mutation Testing Project

This project demonstrates a comprehensive testing approach for a Java calculator application, including **unit testing with JUnit 4**, **code coverage analysis with JaCoCo**, and **mutation testing with PIT**.

## 📋 Table of Contents

- [Overview](#overview)
- [Project Structure](#project-structure)
- [Prerequisites](#prerequisites)
- [Installation & Setup](#installation--setup)
- [Running Tests](#running-tests)
- [Code Coverage with JaCoCo](#code-coverage-with-jacoco)
- [Mutation Testing with PIT](#mutation-testing-with-pit)
- [Understanding the Reports](#understanding-the-reports)
- [Best Practices](#best-practices)
- [Additional Resources](#additional-resources)
- [License](#license)


## 🎯 Overview

This project contains a `Calculator` class with the following operations:
- Addition (`suma`)
- Subtraction (`resta`)
- Multiplication (`multiplica`)
- Division (`divideix`) with zero-division protection
- Maximum value (`maxim`)
- Square root (`arrelQuadrada`) with negative number validation
- Positive number check (`esPositiu`)
- Power calculation (`potencia`) with positive exponent validation

The main objectives are:
1. **Write comprehensive unit tests** covering normal cases, edge cases, and exceptions
2. **Measure code coverage** using JaCoCo
3. **Evaluate test quality** through mutation testing with PIT

## 📁 Project Structure

```
calculator-unit_mutation-testing/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/seidoropentrends/classes/
│   │           └── Calculator.java
│   └── test/
│       └── java/
│           └── com/seidoropentrends/classes/
│               └── CalculatorTest.java
├── target/
│   ├── site/jacoco/                 # JaCoCo reports generated here
│   └── pit-reports/                 # PIT reports generated here
├── pom.xml
└── README.md
```

## ✅ Prerequisites

Before running this project, ensure you have:

- **Java JDK 8** or higher (`java -version`)
- **Maven 3.6+** (`mvn -version`)
- An IDE such as **IntelliJ IDEA**, **Eclipse**, or **VS Code** (optional but recommended)

## 🚀 Installation & Setup

1. **Clone the repository** (or download the project):
   ```bash
   git clone <repository-url>
   cd calculator-unit_mutation-testing
   ```

2. **Verify Maven installation**:
   ```bash
   mvn --version
   ```

3. **Install dependencies**:
   ```bash
   mvn clean install
   ```

## 🧪 Running Tests

### Execute All Unit Tests

```bash
mvn test
```

This command will:
- Compile the source code
- Run all JUnit 4 tests in `CalculatorTest`
- Display test results in the console
- Generate JaCoCo coverage data

### Expected Output

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.seidoropentrends.classes.CalculatorTest
Tests run: X, Failures: 0, Errors: 0, Skipped: 0

Results :

Tests run: X, Failures: 0, Errors: 0, Skipped: 0
```

## 📊 Code Coverage with JaCoCo

### What is JaCoCo?

**JaCoCo (Java Code Coverage)** is a free code coverage library for Java that measures which parts of your code are executed during testing.

### How to Generate JaCoCo Reports

JaCoCo is already configured in the `pom.xml`. Simply run:

```bash
mvn clean test
```

### Viewing the JaCoCo Report

After running tests, open the HTML report:

```
target/site/jacoco/index.html
```

Open this file in your browser to see:
- **Line coverage**: Percentage of code lines executed
- **Branch coverage**: Percentage of conditional branches tested
- **Method coverage**: Percentage of methods invoked
- **Class coverage**: Percentage of classes tested

### Understanding JaCoCo Metrics

| Color | Meaning |
|-------|---------|
| 🟢 Green | Fully covered |
| 🟡 Yellow | Partially covered |
| 🔴 Red | Not covered |

**Coverage Types:**
- **Instructions**: Individual bytecode instructions
- **Branches**: Decision points (if/else, switch, etc.)
- **Lines**: Source code lines
- **Methods**: Method invocations
- **Classes**: Class instantiations

### JaCoCo Configuration in pom.xml

```xml
<plugin>
    <groupId>org.jacoco</groupId>
    <artifactId>jacoco-maven-plugin</artifactId>
    <version>0.8.12</version>
    <executions>
        <execution>
            <id>prepare-agent</id>
            <goals>
                <goal>prepare-agent</goal>
            </goals>
        </execution>
        <execution>
            <id>report</id>
            <phase>test</phase>
            <goals>
                <goal>report</goal>
            </goals>
        </execution>
    </executions>
</plugin>
```

## 🧬 Mutation Testing with PIT

### What is Mutation Testing?

**PIT (Pitest)** is a mutation testing tool that evaluates the **quality** of your tests by:
1. **Creating mutants**: Modifying the production code (e.g., changing `+` to `-`, `>` to `>=`)
2. **Running tests**: Checking if tests detect the mutations
3. **Reporting results**: 
   - ✅ **Killed**: Test detected the mutation (good!)
   - ❌ **Survived**: Test didn't catch the mutation (weak test!)
   - ⏱️ **Timed out**: Mutation caused infinite loop
   - ⚪ **No coverage**: Code not tested

### Why Use Mutation Testing?

Code coverage (JaCoCo) shows **which** code is executed, but not **how well** it's tested. A test can execute code without properly asserting results.

**Example:**
```java
public int suma(int a, int b) {
    return a + b;  // Mutant: change to a - b
}

@Test
public void testSuma() {
    calculator.suma(2, 3);  // Executes code but doesn't assert!
}
```

- **JaCoCo**: 100% coverage ✅
- **PIT**: Mutant survives because test doesn't verify result ❌

### Running PIT Mutation Tests

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```

Or simply:

```bash
mvn test pitest:mutationCoverage
```

### Viewing the PIT Report

After execution, open:

```
target/pit-reports/YYYYMMDDHHMM/index.html
```

(The folder name is a timestamp of when the report was generated)

### Understanding PIT Metrics

**Key Metrics:**
- **Line Coverage**: Percentage of lines executed (similar to JaCoCo)
- **Mutation Coverage**: Percentage of mutants killed
- **Test Strength**: (Killed mutants / Total mutants) × 100

**Mutation Status:**
- 🟢 **KILLED**: Test failed when mutation was introduced (excellent!)
- 🔴 **SURVIVED**: Test passed despite mutation (needs improvement!)
- 🟡 **NO_COVERAGE**: Line not executed by tests
- ⏱️ **TIMED_OUT**: Mutation caused test to hang
- 🔵 **MEMORY_ERROR**: Mutation exhausted memory
- ⚪ **NON_VIABLE**: Mutation prevented compilation

### PIT Configuration in pom.xml

```xml
<plugin>
    <groupId>org.pitest</groupId>
    <artifactId>pitest-maven</artifactId>
    <version>1.14.4</version>
    <configuration>
        <targetClasses>
            <param>com.seidoropentrends.classes.*</param>
        </targetClasses>
        <targetTests>
            <param>com.seidoropentrends.classes.*</param>
        </targetTests>
        <threads>4</threads>
        <verbose>true</verbose>
    </configuration>
</plugin>
```

### Common Mutations Applied by PIT

| Mutator | Description | Example |
|---------|-------------|---------|
| **Conditionals Boundary** | Changes `<` to `<=`, `>` to `>=` | `if (x > 0)` → `if (x >= 0)` |
| **Negate Conditionals** | Inverts conditions | `if (x == y)` → `if (x != y)` |
| **Math** | Changes operators | `a + b` → `a - b` |
| **Return Values** | Modifies return values | `return true` → `return false` |
| **Void Method Calls** | Removes method calls | Deletes statement |
| **Increments** | Changes `++` to `--` | `i++` → `i--` |

## 📈 Understanding the Reports

### Workflow

1. **Run tests**: `mvn test`
2. **Check JaCoCo**: Is all code executed?
   - ❌ **Low coverage** → Add more tests
   - ✅ **High coverage** → Proceed to mutation testing

3. **Run PIT**: `mvn pitest:mutationCoverage`
4. **Check mutation score**: Are mutants being killed?
   - ❌ **Low score** → Improve assertions and edge cases
   - ✅ **High score** → Tests are strong!

### Interpreting Results

**Scenario 1: High JaCoCo + Low PIT**
- Tests execute code but don't verify behavior
- **Solution**: Add more assertions (`assertEquals`, `assertTrue`, etc.)

**Scenario 2: Low JaCoCo + Low PIT**
- Not enough tests
- **Solution**: Write tests for uncovered code

**Scenario 3: High JaCoCo + High PIT**
- Excellent test suite! 🎉
- Tests cover code and verify correctness

### Target Metrics

- **JaCoCo Line Coverage**: Aim for **80%+**
- **PIT Mutation Coverage**: Aim for **75%+**

*Note: 100% is not always practical or necessary, especially with equivalent mutants.*

## 🎯 Best Practices

### For Unit Testing
1. **Follow AAA pattern**: Arrange, Act, Assert
2. **Test one behavior per test**
3. **Use descriptive test names**: `testDivideByZeroThrowsException()`
4. **Test edge cases**: zero, negative numbers, boundaries
5. **Test exceptions**: Verify expected errors are thrown

### For Code Coverage (JaCoCo)
1. **Don't chase 100%**: Focus on critical business logic
2. **Exclude generated code**: DTOs, getters/setters
3. **Use coverage as a guide**, not a goal

### For Mutation Testing (PIT)
1. **Don't obsess over 100%**: Some mutants are equivalent
2. **Focus on survived mutants**: These reveal weak tests
3. **Add boundary tests**: `>=` vs `>` mutations
4. **Verify return values**: Don't just call methods
5. **Test error conditions**: Exception paths are often weak
6. **Run periodically**: On CI/CD or before major releases
7. **Prioritize critical code**: Not all code needs mutation testing

### Improving Mutation Score

If PIT shows survived mutants:

1. **Check assertions**: Are you verifying results?
   ```java
   // Bad
   calculator.suma(2, 3);
   
   // Good
   assertEquals(5, calculator.suma(2, 3));
   ```

2. **Test boundaries**:
   ```java
   assertEquals(0, calculator.maxim(0, 0));    // Equal values
   assertEquals(5, calculator.maxim(5, -1));   // Positive vs negative
   assertEquals(-1, calculator.maxim(-1, -5)); // Both negative
   ```

3. **Test conditionals thoroughly**:
   ```java
   assertTrue(calculator.esPositiu(1));   // True case
   assertFalse(calculator.esPositiu(0));  // Boundary
   assertFalse(calculator.esPositiu(-1)); // False case
   ```

## 📚 Additional Resources

- [JaCoCo Official Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [PIT Mutation Testing](https://pitest.org/)
- [JUnit 4 Documentation](https://junit.org/junit4/)
- [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/)

## 📝 License

This project is for educational purposes.
---

**Happy Testing! 🧪✨**
```
