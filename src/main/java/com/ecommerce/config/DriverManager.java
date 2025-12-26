package com.ecommerce.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Manages WebDriver instances for thread-safe Selenium tests.
 */
public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static final String HEADLESS_PROPERTY = "headless";

    /**
     * Initializes the Chrome WebDriver with recommended options.
     */
    public static void initializeDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();

        // Disable password manager and autofill
        options.addArguments(
            "--disable-save-password-bubble",
            "--disable-password-manager-reauthentication",
            "--disable-password-generation",
            "--disable-autofill"
        );

        // Disable notifications, popups, extensions
        options.addArguments(
            "--disable-notifications",
            "--disable-popup-blocking",
            "--disable-default-apps",
            "--disable-extensions"
        );

        // Remove automation indicators
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", java.util.Arrays.asList("enable-automation"));
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Preferences
        java.util.Map<String, Object> prefs = new java.util.HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("password_manager_enabled", false);
        prefs.put("autofill.profile_enabled", false);
        prefs.put("profile.default_content_setting_values.notifications", 2);
        prefs.put("profile.default_content_settings.popups", 0);
        options.setExperimentalOption("prefs", prefs);

        // Incognito mode
        options.addArguments("--incognito");

        // Headless mode if specified
        if (Boolean.parseBoolean(System.getProperty(HEADLESS_PROPERTY, "false"))) {
            options.addArguments("--headless");
        }

        driver.set(new ChromeDriver(options));
    }

    /**
     * Gets the current thread's WebDriver instance.
     * @return WebDriver instance or null if not initialized
     */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits and removes the current thread's WebDriver instance.
     */
    public static void quitDriver() {
        WebDriver webDriver = driver.get();
        if (webDriver != null) {
            webDriver.quit();
            driver.remove();
        }
    }
}