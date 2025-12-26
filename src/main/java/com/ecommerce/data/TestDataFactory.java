package com.ecommerce.data;

import com.ecommerce.utils.TestDataLoader;
import java.util.List;

/**
 * Factory class for creating test data objects.
 * Provides categorized test data loaded from external sources.
 */
public class TestDataFactory {
    
    public static ShippingAddress getValidAddress() {
        return new ShippingAddress("John", "Doe", "12345");
    }
    
    public static List<ShippingAddress> getValidAddresses() {
        return TestDataLoader.loadShippingAddressesByCategory("valid");
    }
    
    public static List<ShippingAddress> getInternationalAddresses() {
        return TestDataLoader.loadShippingAddressesByCategory("international");
    }
    
    public static List<ShippingAddress> getBoundaryAddresses() {
        return TestDataLoader.loadShippingAddressesByCategory("boundary");
    }
    
    public static List<ShippingAddress> getAllTestAddresses() {
        return TestDataLoader.loadShippingAddresses();
    }
}