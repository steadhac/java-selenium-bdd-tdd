package com.ecommerce.tests;

import com.ecommerce.config.ConfigManager;
import com.ecommerce.config.DriverManager;
import com.ecommerce.data.ShippingAddress;
import com.ecommerce.data.TestDataFactory;
import com.ecommerce.pages.HomePage;
import com.ecommerce.pages.CartPage;
import com.ecommerce.pages.CheckoutPage;
import org.testng.Assert;
import org.testng.annotations.*;

/**
 * Data-driven tests for checkout functionality.
 * Uses TestNG DataProvider for multiple shipping address scenarios.
 */
public class DataDrivenCheckoutTest {
    
    private HomePage homePage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    
    @BeforeMethod
    public void setUp() {
        DriverManager.initializeDriver();
        homePage = new HomePage(DriverManager.getDriver());
        cartPage = new CartPage(DriverManager.getDriver());
        checkoutPage = new CheckoutPage(DriverManager.getDriver());
    }
    
    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
    
    @DataProvider(name = "validAddresses")
    public Object[][] getValidAddresses() {
        return TestDataFactory.getValidAddresses().stream()
            .map(address -> new Object[]{address})
            .toArray(Object[][]::new);
    }
    
    @DataProvider(name = "internationalAddresses") 
    public Object[][] getInternationalAddresses() {
        return TestDataFactory.getInternationalAddresses().stream()
            .map(address -> new Object[]{address})
            .toArray(Object[][]::new);
    }
    
    @DataProvider(name = "boundaryAddresses")
    public Object[][] getBoundaryAddresses() {
        return TestDataFactory.getBoundaryAddresses().stream()
            .map(address -> new Object[]{address})
            .toArray(Object[][]::new);
    }
    
    @Test(dataProvider = "validAddresses")
    public void testCheckoutWithValidAddresses(ShippingAddress address) {
        homePage.navigateTo(ConfigManager.getAppUrl());
        homePage.login();
        homePage.clickFirstProduct();
        homePage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.fillShippingInfo(address);
        checkoutPage.clickContinue();
        checkoutPage.finishOrder();
        Assert.assertTrue(checkoutPage.isConfirmationVisible());
    }
    
    @Test(dataProvider = "internationalAddresses")
    public void testCheckoutWithInternationalAddresses(ShippingAddress address) {
        homePage.navigateTo(ConfigManager.getAppUrl());
        homePage.login();
        homePage.clickFirstProduct();
        homePage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.fillShippingInfo(address);
        checkoutPage.clickContinue();
        checkoutPage.finishOrder();
        Assert.assertTrue(checkoutPage.isConfirmationVisible());
    }
    
    @Test(dataProvider = "boundaryAddresses")
    public void testCheckoutWithBoundaryAddresses(ShippingAddress address) {
        homePage.navigateTo(ConfigManager.getAppUrl());
        homePage.login();
        homePage.clickFirstProduct();
        homePage.goToCart();
        cartPage.proceedToCheckout();
        checkoutPage.fillShippingInfo(address);
        checkoutPage.clickContinue();
        checkoutPage.finishOrder();
        Assert.assertTrue(checkoutPage.isConfirmationVisible());
    }
}