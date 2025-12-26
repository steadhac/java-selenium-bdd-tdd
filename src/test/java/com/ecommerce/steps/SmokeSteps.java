package com.ecommerce.steps;

import com.ecommerce.config.ConfigManager;
import com.ecommerce.config.DriverManager;
import com.ecommerce.pages.HomePage;
import io.cucumber.java.en.*;
import org.testng.Assert;

public class SmokeSteps {
    private HomePage homePage;
    
    @Given("I navigate to the ecommerce site")
    public void i_navigate_to_the_ecommerce_site() {
        homePage = new HomePage(DriverManager.getDriver());
        homePage.navigateTo(ConfigManager.getAppUrl());
    }
    
    @When("I login with valid credentials")
    public void i_login_with_valid_credentials() {
        homePage.login();
    }
    
    @Then("the inventory page should load")
    public void the_inventory_page_should_load() {
        Assert.assertTrue(homePage.isInventoryVisible());
    }
    
    @Then("the page title should contain {string}")
    public void the_page_title_should_contain(String expectedTitle) {
        Assert.assertTrue(homePage.getPageTitle().contains(expectedTitle));
    }
    
    @When("I add the first product to cart")
    public void i_add_the_first_product_to_cart() {
        homePage.clickFirstProduct();
    }
    
    @Then("the cart should show items")
    public void the_cart_should_show_items() {
        homePage.goToCart();
        Assert.assertTrue(DriverManager.getDriver().getCurrentUrl().contains("cart"));
    }
}