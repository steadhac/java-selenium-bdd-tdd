package com.ecommerce.data;

/**
 * Data object for shipping address information.
 * Used in checkout flow testing with multiple address scenarios.
 */
public class ShippingAddress {
    private String firstName;
    private String lastName;
    private String postalCode;
    
    public ShippingAddress(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public String getPostalCode() {
        return postalCode;
    }
}