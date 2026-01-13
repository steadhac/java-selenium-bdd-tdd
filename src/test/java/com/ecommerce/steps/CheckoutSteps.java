package com.ecommerce.steps;

import com.ecommerce.config.DriverManager;
import com.ecommerce.data.ShippingAddress;
import com.ecommerce.data.TestDataFactory;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.CartPage;
import com.ecommerce.pages.CheckoutPage;
import io.cucumber.java.en.*;
import org.testng.Assert;

/**
 * Step definitions for checkout flow scenarios.
 * Handles complete ecommerce purchase process from cart to order confirmation.
 * 
 * Test Flow:
 * - Add single or multiple products to cart
 * - Navigate through checkout process
 * - Fill shipping information
 * - Complete order and verify confirmation
 */
public class CheckoutSteps {
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    
    /**
     * Adds a single product to cart and navigates to cart page.
     * Used in checkout flow scenarios.
     */
    @When("I add a product to cart")
    public void i_add_a_product_to_cart() {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.clickFirstProduct();
        homePage.goToCart();
    }
    
    /**
     * Adds multiple products to cart for testing bulk purchase scenarios.
     * Adds first product, navigates back, then adds second product.
     */
    @When("I add multiple products to cart")
    public void i_add_multiple_products_to_cart() {
        homePage = new HomePage(DriverManager.getDriver());
        // Add first product (button changes to "Remove")
        homePage.clickFirstProduct();
        // Add second available product (find next "Add to cart" button)
        homePage.clickNextAvailableProduct();
        // Go to cart to proceed with checkout
        homePage.goToCart();
    }
    
    /**
     * Initiates checkout process from cart page.
     * Verifies checkout button is visible before proceeding.
     */
    @When("I proceed to checkout")
    public void i_proceed_to_checkout() {
        cartPage = new CartPage(DriverManager.getDriver());
        Assert.assertTrue(cartPage.isCheckoutButtonVisible());
        cartPage.proceedToCheckout();
    }
    
    /**
     * Fills shipping information during checkout.
     * Uses default test data from TestDataFactory.
     */
    @When("I fill shipping information")
    public void i_fill_shipping_information() {
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
        ShippingAddress address = TestDataFactory.getValidAddress();
        checkoutPage.fillShippingInfo(address);
        checkoutPage.clickContinue();
    }
    
    /**
     * Completes the order by clicking finish button.
     * Final step in checkout process.
     */
    @When("I complete the order")
    public void i_complete_the_order() {
        checkoutPage.finishOrder();
    }
    
    /**
     * Verifies order confirmation is displayed.
     * Checks for confirmation message containing "Thank you" or "complete".
     */
    @Then("I should see order confirmation")
    public void i_should_see_order_confirmation() {
        Assert.assertTrue(checkoutPage.isConfirmationVisible());
        String confirmationText = checkoutPage.getConfirmationMessage();
        Assert.assertTrue(confirmationText.contains("Thank you") || 
                         confirmationText.contains("complete"));
    }
}