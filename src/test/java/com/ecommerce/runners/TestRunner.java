package com.ecommerce.runners;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.ecommerce.config.DriverManager;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

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