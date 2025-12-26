package com.ecommerce.steps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.ecommerce.config.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;

public class Hooks {

    private final WebDriver driver;

    public Hooks() {
        this.driver = DriverManager.getDriver(); // Adjust to your driver management
    }

    @After
    public void takeScreenshot(Scenario scenario) {
        if (driver instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "Screenshot");

            // Save screenshot as file
            String status = scenario.isFailed() ? "FAILED" : "PASSED";
            String scenarioName = scenario.getName().replaceAll("[^a-zA-Z0-9]", "_");
            String dirPath = "target/screenshots/";
            String filePath = dirPath + scenarioName + "_" + status + ".png";
            try {
                Files.createDirectories(Paths.get(dirPath));
                Files.write(Paths.get(filePath), screenshot);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}