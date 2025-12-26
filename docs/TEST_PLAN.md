# Test Plan

## 1. Introduction
This document outlines the test strategy, objectives, scope, and approach for the e-commerce automation project.

## 2. Objectives
- Validate core e-commerce workflows (login, product selection, checkout, etc.)
- Ensure application stability across environments (dev, staging, prod)
- Detect issues early via automated tests
- Promote code quality and maintainability through Test-Driven Development (TDD)

## 3. Scope
- Functional UI tests using Selenium and Cucumber
- Smoke tests and shopping cart tests
- Supported browsers: Chrome (others as future scope)
- Test-Driven Development (TDD) practices for new features and bug fixes

## 4. Test Strategy
- Page Object Model for maintainable locators and actions
- BDD with Cucumber for readable scenarios
- TDD: Write tests before implementing new features or changes to ensure requirements are met and code is robust
- Test data managed via config and JSON files

## 5. Test Types
- **Smoke Tests:** Basic end-to-end flows (login, add to cart, checkout)
- **Shopping Cart Tests:** Cart functionality scenarios

## 6. Environments
- dev, staging, prod (configurable via properties)

## 7. Test Data
- Managed in JSON files, path set via config.properties

## 8. Tools
- Java, Selenium WebDriver, Cucumber, TestNG, Maven/Gradle

## 9. Execution
- Local: via IDE or command line
- CI: via pipeline (see CI_CD.md)

## 10. Reporting
- Cucumber HTML reports
- Console and CI logs

## 11. Risks & Mitigations
- Test data drift: Regularly review JSON files
- UI changes: Maintain Page Objects

## 12. References

- [CI_CD.md](CI_CD.md): Continuous Integration and Deployment process and pipeline details.
- [test-cases.md](test-cases.md): List of all test cases and scenarios.
- [test_data.md](test_data.md): Test data sources, structure, and management.
- [configuration.md](configuration.md): Configuration files, environment setup, and property usage.