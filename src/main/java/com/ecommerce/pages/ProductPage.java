package com.ecommerce.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for individual product pages.
 * Handles product-specific actions and cart operations.
 * 
 * Features:
 * - Add products to cart
 * - Success message handling
 * - Continue shopping navigation
 */
public class ProductPage extends BasePage {
    private By addToCartButton = By.cssSelector(".btn.btn-default.cart");
    private By successModal = By.cssSelector(".modal-content");
    private By continueShoppingButton = By.cssSelector(".btn.btn-success");
    
    public ProductPage(WebDriver driver) {
        super(driver);
    }
    
    public void addToCart() {
        click(addToCartButton);
    }
    
    public boolean isSuccessMessageVisible() {
        return isDisplayed(successModal);
    }
    
    public void continueShopping() {
        click(continueShoppingButton);
    }
}