# Banking BDD Cucumber Automation Framework
**Author:** Dhwani Parikh  
**Technology Stack:** Java 11+, Cucumber-JVM 7, Selenium WebDriver 4, TestNG, Maven, Gherkin, ThreadLocal Parallel Scenarios

---

## 1. Overview
This repository contains a **Behavior-Driven Development (BDD)** test automation framework using **Cucumber-JVM** and **Selenium WebDriver** for enterprise banking flows.

Target Application under test: [ParaBank Internet Banking](https://parabank.parasoft.com/)

---

## 2. Key Architectural Highlights
- **Thread-Safe Scenario Parallel Execution:** Overrides `@DataProvider(parallel = true)` in `TestRunner.java` combined with `ThreadLocal<WebDriver>` in `DriverManager` to run BDD scenarios in parallel.
- **Clean Gherkin Feature Files:** Declarative user stories with `Scenario Outline`, `Examples` tables, and background blocks.
- **Custom Cucumber Hooks (`ApplicationHooks`):**
  - `@Before`: Initializes fresh driver context.
  - `@After`: Automatically captures embedded full screenshots on test failures directly into the Cucumber HTML report.
- **Tag-Based Test Execution:** Supports `@smoke`, `@regression`, `@login`, and `@transfer` tags for CI/CD execution filters.

---

## 3. Project Structure
```text
banking-bdd-cucumber-framework/
├── src/
│   ├── main/java/com/banking/
│   │   ├── driver/             # ThreadLocal DriverManager
│   │   └── pages/              # BankingLoginPage & TransferFundsPage
│   └── test/
│       ├── java/com/banking/
│       │   ├── hooks/          # ApplicationHooks (@Before, @After screenshot)
│       │   ├── stepdefinitions/# Step definitions mapping Gherkin to POM
│       │   └── runners/        # TestRunner with parallel scenario DataProvider
│       └── resources/
│           └── features/       # banking.feature
├── pom.xml                     # Maven dependencies (Cucumber, Selenium, TestNG)
└── README.md
```

---

## 4. Execution Commands

### Run All BDD Scenarios (Parallel)
```bash
mvn clean test
```

### Run Smoke Tests Only
```bash
mvn test -Dcucumber.filter.tags="@smoke"
```

---

## 5. Test Reports
After running tests, open the generated HTML report:
- `target/cucumber-reports/cucumber.html`

