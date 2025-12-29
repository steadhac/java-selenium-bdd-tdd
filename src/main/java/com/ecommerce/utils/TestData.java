package com.ecommerce.utils;

/**
 * Test data constants for user credentials.
 * Provides standardized user accounts for different test scenarios.
 */
public class TestData {
    
    public static class Users {
        /** Standard user for normal flow testing */
        public static final String STANDARD_USER = "standard_user";
        
        /** Locked user for negative testing */
        public static final String LOCKED_USER = "locked_out_user";
        
        /** Problem user for error scenario testing */
        public static final String PROBLEM_USER = "problem_user";
        
        /** Performance glitch user for performance testing */
        public static final String PERFORMANCE_USER = "performance_glitch_user";
        
        /** Common password for all test users */
        public static final String PASSWORD = "secret_sauce";
    }
    
    public static class Products {
        public static final String BACKPACK = "sauce-labs-backpack";
        public static final String BIKE_LIGHT = "sauce-labs-bike-light";
        public static final String BOLT_TSHIRT = "sauce-labs-bolt-t-shirt";
        public static final String FLEECE_JACKET = "sauce-labs-fleece-jacket";
        public static final String ONESIE = "sauce-labs-onesie";
        public static final String RED_TSHIRT = "test.allthethings()-t-shirt-(red)";
    }
    
}