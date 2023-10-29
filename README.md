# JUnit 5 Tutorial Starter Project

This starter project is designed to help you practice writing JUnit 5 tests and understand various testing concepts, including the test lifecycle, conditional testing, and assumptions.

## Introduction

JUnit is a popular testing framework for Java applications, and JUnit 5 is the latest version, offering various features and improvements over its predecessors. In this tutorial, you'll not only learn about the test lifecycle but also explore conditional testing and assumptions in JUnit 5.

## Lifecycle of a Test Class

A JUnit 5 test typically follows a specific lifecycle, which is essential for proper test execution and management. The key components of the test lifecycle include:

- **@BeforeAll**: This annotation is used to perform one-time setup tasks that should run before any test cases are executed. For example, you can initialize resources or establish a connection to a database.

- **@BeforeEach**: Use this annotation to perform setup tasks that run before each individual test case. It ensures that each test starts with a clean slate, preventing interference between tests.

- **@Test**: The @Test annotation marks a method as a test case. This method contains the actual test logic, including the input, execution, and assertions.

- **@AfterEach**: After each test case, any necessary cleanup or teardown operations can be performed using this annotation. For instance, closing resources or resetting the environment.

- **@AfterAll**: This annotation is used for one-time cleanup tasks that should run after all test cases have been executed. It is typically used to release resources and perform any final cleanup.

#### NOTE: By default, the Lifecycle of a Test class is PER_METHOD, so, after each test method is executed, the constructor of the Test class is called. This can be changed by using the @TestInstance(TestInstance.Lifecycle.PER_CLASS).

### Quick Summary

- **@Before*** annotations are used to perform initialization before running test cases to set up the environment for testing.

- **@Test**: The actual test case logic.

- **@After*** annotations are used to perform cleanup after running test cases to release resources and restore the environment to its original state.

By understanding the test lifecycle and using the appropriate annotations, you can ensure that your tests are executed in a controlled and reliable manner.

## Conditional Testing

Conditional testing allows you to run specific test cases based on certain conditions. JUnit 5 provides built-in support for conditional test execution through annotations such as **@EnabledOnOs**, **@DisabledOnOs**, **@EnabledIfSystemProperty**, and more. These annotations let you control which tests run under specific conditions, enhancing test flexibility.

## Assumptions

JUnit 5 also supports assumptions using annotations like **@Assumptions**. Assumptions are used to validate certain conditions before running a test case. If the assumptions fail, the test is marked as "skipped" rather than "failed," which can be useful for tests that depend on certain conditions being met.

## Repeated Tests
JUnit 5's @RepeatedTest annotation enables running the same test method multiple times with different inputs or conditions. This is valuable for ensuring the consistency and reliability of your code across multiple test repetitions. Use the value attribute to specify the number of repetitions and the name attribute to customize display names for each run. It's a powerful tool to strengthen your test suite and validate your code's consistency.

````
@RepeatedTest(value = numberOfRepetitions, name = "Display Name Pattern")
````
- value: Specifies the number of times the test method should be repeated.
- name: (Optional) Allows customizing the display name for each repetition using placeholders like {displayName}, {currentRepetition}, and {totalRepetitions}.


## Parameterized Tests
JUnit 5 offers the @ParameterizedTest annotation, a powerful feature that allows you to run the same test method with multiple sets of input parameters. This is particularly useful when you want to verify that your code behaves correctly under various conditions or with different inputs.

````
@ParameterizedTest
@ValueSource(type = ParameterType.class, values = { /* Provide input values */ })
@EnumSource(EnumType.class)
@MethodSource("methodName")
@CsvSource({"value1, result1", "value2, result2", "value3, result3"})
@CsvFileSource(resources = "/data.csv")
````

- @ParameterizedTest: Marks a method as a parameterized test.
- @ValueSource: Specifies the source of input values, such as an array or collection of values.
- Type parameter: Represents the parameter provided for each test iteration.
- @EnumSource: Specifies values from Enum.
- @MethodSource: Specifies the source of method which passes the input values, such as an array or collection of values.
- @CsvSource: Specifies input values directly within your test method.
- @CsvFileSource: Allows you to provide input values from a CSV file.

You can use various sources for input values, such as arrays, collections, or custom sources, to create a set of test cases with different parameters. JUnit 5 will execute the test method for each set of input values, allowing you to verify that your code functions correctly under a range of scenarios.

### NOTE: @RepeatedTest & @ParameterizedTest substitute @Test annotation

## Nested Class Test
JUnit 5 allows you to use nested classes to better structure and organize your tests. Nested classes are a valuable tool for:

- Grouping related test cases within the same test class.
- Reducing code duplication by sharing setup and context within a group of tests.
- Ensuring isolation between test groups.

#### NOTE:
- Can only use @BeforeEach & @AfterEach
- Cannot use @BeforeAll & @AfterAll by default

## Disable a Test

### @Disabled:
The @Disabled annotation is used to temporarily disable a test or a test class. When you annotate a test method or a test class with @Disabled, it won't be executed during test runs. This is useful for excluding tests that are currently under development, failing, or not relevant for the time being. It allows you to keep them in the codebase without affecting the test results.

## Getting Started

To get started with this tutorial, clone or download this starter project and explore the sample code provided. You can use this project as a basis for writing your own JUnit 5 tests, including conditional tests and assumptions.

Feel free to modify the sample tests, add new test cases, and practice writing tests for your own Java applications while considering test conditions and assumptions.

Happy testing! 🧪🚀

---

This expanded README provides an overview of the JUnit 5 tutorial starter project and includes additional concepts like conditional testing and assumptions in JUnit 5. It's designed to help you practice writing effective JUnit 5 test cases and understand various aspects of testing in Java.