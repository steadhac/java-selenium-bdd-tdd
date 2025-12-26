package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for SauceDemo shopping cart page.
 * Handles checkout initiation and cart verification.
 * 
 * Features:
 * - Checkout button interaction
 * - Cart button visibility verification
 */
public class CartPage extends BasePage {
    private By checkoutButton = By.id("checkout");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }
    
    public void proceedToCheckout() {
        click(checkoutButton);
    }
    
    public boolean isCheckoutButtonVisible() {
        return isDisplayed(checkoutButton);
    }
}