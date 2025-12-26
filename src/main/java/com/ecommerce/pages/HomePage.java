package com.ecommerce.pages;

import com.ecommerce.config.ConfigManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for SauceDemo home/inventory page.
 * Handles login, product selection, and cart operations.
 * 
 * Features:
 * - Environment-specific login credentials
 * - Product selection and cart management
 * - Cart badge verification
 * - Product removal functionality
 */
public class HomePage extends BasePage {
    private By usernameInput = By.id("user-name");
    private By passwordInput = By.id("password");
    private By loginButton = By.id("login-button");
    private By inventoryContainer = By.className("inventory_container");
    private By firstProduct = By.cssSelector(".inventory_item:first-child .btn_inventory");
    private By cartIcon = By.className("shopping_cart_link");
    private By cartBadge = By.className("shopping_cart_badge");
    private By removeButton = By.cssSelector(".cart_button");
    private By cartItems = By.className("cart_item");
    private By availableProducts = By.xpath("//button[text()='Add to cart']");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }
    
    public void navigateTo(String url) {
        driver.get(url);
    }
    
    /**
     * Login using credentials from ConfigManager.
     * Uses environment-specific test username and password.
     * Automatically navigates to inventory page on success.
     */
    public void login() {
        sendKeys(usernameInput, ConfigManager.getTestUsername());
        sendKeys(passwordInput, ConfigManager.getTestPassword());
        click(loginButton);
    }
    
    public void clickFirstProduct() {
        click(firstProduct);
    }
    
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    public boolean isInventoryVisible() {
        return isDisplayed(inventoryContainer);
    }
    
    public void goToCart() {
        click(cartIcon);
    }
    
    public String getCartBadgeText() {
        return getText(cartBadge);
    }
    
    public void removeFirstProduct() {
        click(removeButton);
    }
    
    public boolean isCartEmpty() {
        return !isDisplayed(cartItems);
    }
    
    /**
     * Clicks first available "Add to cart" button.
     * 
     * Behavior:
     * - Finds buttons with text "Add to cart"
     * - Skips buttons that show "Remove" (already added)
     * - Button text changes to "Remove" after clicking
     */
    public void clickNextAvailableProduct() {
        var availableButtons = driver.findElements(availableProducts);
        if (availableButtons.size() > 0) {
            availableButtons.get(0).click();
        }
    }
}