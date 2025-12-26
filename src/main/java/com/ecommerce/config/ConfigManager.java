package com.ecommerce.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Manages configuration loading from properties files.
 * 
 * Loads configuration from two sources:
 * - config.properties: Main test settings (browser, timeouts, environment selector)
 * - environments/{env}.properties: Environment-specific URLs and credentials
 * 
 * Environment-specific properties take precedence over main config properties.
 * Default environment is 'dev' if not specified.
 * 
 * Usage:
 *   String url = ConfigManager.getAppUrl();
 *   String username = ConfigManager.getTestUsername();
 */
public class ConfigManager {
    private static Properties config = new Properties();
    private static Properties envConfig = new Properties();
    
    static {
        loadConfigurations();
    }
    
    private static void loadConfigurations() {
        try {
            // Load main config
            InputStream configStream = ConfigManager.class.getClassLoader()
                .getResourceAsStream("config.properties");
            config.load(configStream);
            
            // Load environment-specific config
            String environment = config.getProperty("environment", "dev");
            InputStream envStream = ConfigManager.class.getClassLoader()
                .getResourceAsStream("environments/" + environment + ".properties");
            envConfig.load(envStream);
            
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration files", e);
        }
    }
    
    /**
     * Gets property value with environment precedence.
     * Checks environment config first, then falls back to main config.
     * 
     * @param key Property key to retrieve
     * @return Property value or null if not found
     */
    public static String getProperty(String key) {
        // Check environment config first, then main config
        return envConfig.getProperty(key, config.getProperty(key));
    }
    
    public static String getAppUrl() {
        return getProperty("app.base.url");
    }
    
    public static String getBrowser() {
        return getProperty("browser");
    }
    
    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }
    
    public static int getTimeout() {
        return Integer.parseInt(getProperty("timeout"));
    }
    
    public static String getTestUsername() {
        return getProperty("test.username");
    }
    
    public static String getTestPassword() {
        return getProperty("test.password");
    }
}