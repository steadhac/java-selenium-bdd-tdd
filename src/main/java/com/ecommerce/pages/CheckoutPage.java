package com.ecommerce.pages;

import com.ecommerce.data.ShippingAddress;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for SauceDemo checkout flow pages.
 * Handles shipping information and order completion.
 * 
 * Features:
 * - Shipping information form filling
 * - Order completion workflow
 * - Confirmation message verification
 * 
 */
public class CheckoutPage extends BasePage {
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By confirmationMessage = By.className("complete-header");
    
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }
    
    public void fillShippingInfo(ShippingAddress address) {
        sendKeys(firstNameInput, address.getFirstName());
        sendKeys(lastNameInput, address.getLastName());
        sendKeys(postalCodeInput, address.getPostalCode());
    }
    
    public void clickContinue() {
        click(continueButton);
    }
    
    public void finishOrder() {
        click(finishButton);
    }
    
    public String getConfirmationMessage() {
        return getText(confirmationMessage);
    }
    
    public boolean isConfirmationVisible() {
        return isDisplayed(confirmationMessage);
    }
}