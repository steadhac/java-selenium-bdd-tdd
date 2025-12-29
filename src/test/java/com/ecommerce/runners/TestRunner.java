package com.ecommerce.runners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ecommerce.config.DriverManager;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Main Cucumber test runner for the e-commerce automation framework.
 *
 * This class configures and launches Cucumber tests using the specified options,
 * such as feature file locations, step definition packages, and report plugins.
 *
 * Usage:
 * - Executes all scenarios defined in the feature files.
 * - Generates HTML and JSON reports after test execution.
 */
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.ecommerce.steps",
    plugin = {
        "pretty",
        "html:target/cucumber-reports.html",
        "json:target/cucumber-reports/CucumberTestReport.json"
    },
    monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
    
    @BeforeMethod
    public void setUp() {
        DriverManager.initializeDriver();
    }
    
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}