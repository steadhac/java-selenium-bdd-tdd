package com.ecommerce.steps;

import com.ecommerce.config.DriverManager;
import com.ecommerce.pages.HomePage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class CartSteps {
    private HomePage homePage;
    
    @Then("I should see the product in my cart")
    public void i_should_see_the_product_in_my_cart() {
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("cart"));
    }
    
    @Then("the cart badge should show {string}")
    public void the_cart_badge_should_show(String expectedCount) {
        homePage = new HomePage(DriverManager.getDriver());
        String badgeText = homePage.getCartBadgeText();
        Assert.assertEquals(badgeText, expectedCount);
    }
    
    @When("I remove the product from cart")
    public void i_remove_the_product_from_cart() {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.goToCart();
        homePage.removeFirstProduct();
    }
    
    @Then("the cart should be empty")
    public void the_cart_should_be_empty() {
        Assert.assertTrue(homePage.isCartEmpty());
    }
}