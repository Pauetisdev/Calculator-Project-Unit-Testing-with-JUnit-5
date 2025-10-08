# Calculator Unit & Mutation Testing Project

A Java calculator project demonstrating **unit testing with JUnit 4**, **code coverage with JaCoCo**, and **mutation testing with PIT**.

## 📋 Overview

Simple calculator with basic operations (add, subtract, multiply, divide, max, square root, power) that includes comprehensive testing to ensure code quality.

**Testing Stack:**
- **JUnit 4**: Unit testing framework
- **JaCoCo**: Code coverage analysis
- **PIT**: Mutation testing for test quality

## 🚀 Quick Start

### Prerequisites
- Java JDK 8+
- Maven 3.6+

### Run Tests
```bash
mvn clean test
```

### Generate Coverage Report (JaCoCo)
```bash
mvn clean test
```
View report: `target/site/jacoco/index.html`

### Run Mutation Tests (PIT)
```bash
mvn org.pitest:pitest-maven:mutationCoverage
```
View report: `target/pit-reports/YYYYMMDDHHMM/index.html`

## 📊 JaCoCo - Code Coverage

**What it does:** Measures which lines/branches of code are executed during tests.

**Key Metrics:**
- **Line Coverage**: % of code lines executed
- **Branch Coverage**: % of conditional branches tested
- **Method Coverage**: % of methods invoked

**Understanding Colors:**
- 🟢 Green = Fully covered
- 🟡 Yellow = Partially covered  
- 🔴 Red = Not covered

**Target:** Aim for 80%+ coverage

## 🧬 PIT - Mutation Testing

**What it does:** Changes your code (mutations) and checks if tests catch the changes.

**Why it matters:** Code coverage shows *what* code runs, but not *how well* it's tested.

**Example:**
```java
// Original
public int suma(int a, int b) {
    return a + b;
}

// PIT mutates to:
return a - b;

// If your test doesn't assert the result, mutant SURVIVES ❌
```

**Mutation Results:**
- ✅ **KILLED** = Test caught the mutation (good!)
- ❌ **SURVIVED** = Test missed the mutation (improve test!)
- ⏱️ **TIMED_OUT** = Mutation caused infinite loop
- ⚪ **NO_COVERAGE** = Code not tested

**Common Mutations:**
- Math operators: `+` → `-`, `*` → `/`
- Conditionals: `>` → `>=`, `==` → `!=`
- Return values: `true` → `false`
- Boundaries: `<` → `<=`

**Target:** Aim for 75%+ mutation coverage

## 📈 Interpreting Results

| JaCoCo Coverage | PIT Mutation Score | Diagnosis |
|----------------|-------------------|-----------|
| High | High | ✅ Excellent tests! |
| High | Low | ⚠️ Tests run code but don't verify behavior |
| Low | Low | ❌ Need more tests |

## 🎯 Improving Test Quality

### If JaCoCo is low:
Add more tests to cover untested code.

### If PIT score is low (mutants survive):

1. **Add assertions:**
   ```java
   // Bad
   calculator.suma(2, 3);
   
   // Good  
   assertEquals(5, calculator.suma(2, 3));
   ```

2. **Test boundaries:**
   ```java
   assertEquals(0, calculator.maxim(0, 0));
   assertEquals(5, calculator.maxim(5, -1));
   ```

3. **Test both branches:**
   ```java
   assertTrue(calculator.esPositiu(1));   // positive
   assertFalse(calculator.esPositiu(-1)); // negative
   ```

4. **Test exceptions:**
   ```java
   @Test(expected = IllegalArgumentException.class)
   public void testDivideByZero() {
       calculator.divideix(5, 0);
   }
   ```

## 🔧 Configuration

Both tools are configured in `pom.xml`:

**JaCoCo** runs automatically with `mvn test` and generates reports in `target/site/jacoco/`.

**PIT** configuration:
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
    </configuration>
</plugin>
```

## 📚 Resources

- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)
- [PIT Mutation Testing](https://pitest.org/)
- [JUnit 4 Guide](https://junit.org/junit4/)

---

**Happy Testing! 🧪**
```
