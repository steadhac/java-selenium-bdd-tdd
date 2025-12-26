package com.ecommerce.utils;

import com.ecommerce.data.ShippingAddress;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility class for loading test data from external JSON files.
 * 
 * Loads shipping address data from test-data/shipping-addresses.json file.
 * Supports filtering by category and validates data integrity.
 * 
 * Expected JSON format:
 * [
 *   {
 *     "firstName": "John",
 *     "lastName": "Doe",
 *     "postalCode": "12345",
 *     "category": "valid"
 *   }
 * ]
 * 
 * Supported categories: valid, international, boundary
 */
public class TestDataLoader {
    
    /**
     * Loads all shipping addresses from the JSON test data file.
     * 
     * Reads from: src/test/resources/test-data/shipping-addresses.json
     * 
     * @return List of all ShippingAddress objects from the file
     * @throws RuntimeException if file not found, parsing fails, or no data exists
     * 
     */
    public static List<ShippingAddress> loadShippingAddresses() {
        try {
            String jsonContent = readJsonFile("test-data/shipping-addresses.json");
            List<ShippingAddress> addresses = parseAddresses(jsonContent);
            
            if (addresses.isEmpty()) {
                throw new RuntimeException("No shipping addresses found in test data file");
            }
            
            return addresses;
                
        } catch (Exception e) {
            throw new RuntimeException("Failed to load shipping addresses: " + e.getMessage(), e);
        }
    }
    
    /**
     * Loads shipping addresses filtered by category from the JSON test data file.
     * 
     * Reads from: src/test/resources/test-data/shipping-addresses.json
     * Filters records where "category" field matches the provided category.
     * 
     * @param category The category to filter by (e.g., "valid", "international", "boundary")
     * @return List of ShippingAddress objects matching the category
     * @throws IllegalArgumentException if category is null or empty
     * @throws RuntimeException if file not found, parsing fails, or no matching data
     * 
     */
    public static List<ShippingAddress> loadShippingAddressesByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new IllegalArgumentException("Category cannot be null or empty");
        }
        
        try {
            String jsonContent = readJsonFile("test-data/shipping-addresses.json");
            List<ShippingAddress> allAddresses = parseAddresses(jsonContent);
            
            List<ShippingAddress> filteredAddresses = new ArrayList<>();
            for (ShippingAddress address : allAddresses) {
                if (category.equals(extractCategory(jsonContent, address))) {
                    filteredAddresses.add(address);
                }
            }
            
            if (filteredAddresses.isEmpty()) {
                throw new RuntimeException("No shipping addresses found for category: " + category);
            }
            
            return filteredAddresses;
                
        } catch (Exception e) {
            throw new RuntimeException("Failed to load shipping addresses by category: " + e.getMessage(), e);
        }
    }
    
    private static String readJsonFile(String fileName) throws IOException {
        InputStream inputStream = TestDataLoader.class.getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null) {
            throw new RuntimeException("Test data file not found: " + fileName);
        }
        
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }
    
    private static List<ShippingAddress> parseAddresses(String jsonContent) {
        List<ShippingAddress> addresses = new ArrayList<>();
        
        Pattern objectPattern = Pattern.compile("\\{[^}]+\\}");
        Matcher objectMatcher = objectPattern.matcher(jsonContent);
        
        while (objectMatcher.find()) {
            String objectJson = objectMatcher.group();
            
            String firstName = extractValue(objectJson, "firstName");
            String lastName = extractValue(objectJson, "lastName");
            String postalCode = extractValue(objectJson, "postalCode");
            
            if (firstName != null && lastName != null && postalCode != null) {
                addresses.add(new ShippingAddress(firstName, lastName, postalCode));
            }
        }
        
        return addresses;
    }
    
    private static String extractValue(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(json);
        return matcher.find() ? matcher.group(1) : null;
    }
    
    private static String extractCategory(String jsonContent, ShippingAddress address) {
        Pattern objectPattern = Pattern.compile("\\{[^}]+\\}");
        Matcher objectMatcher = objectPattern.matcher(jsonContent);
        
        while (objectMatcher.find()) {
            String objectJson = objectMatcher.group();
            
            String firstName = extractValue(objectJson, "firstName");
            String lastName = extractValue(objectJson, "lastName");
            String postalCode = extractValue(objectJson, "postalCode");
            
            if (address.getFirstName().equals(firstName) && 
                address.getLastName().equals(lastName) && 
                address.getPostalCode().equals(postalCode)) {
                return extractValue(objectJson, "category");
            }
        }
        
        return null;
    }
}