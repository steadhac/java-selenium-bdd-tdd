Feature: Checkout Flow
  As a customer
  I want to complete a purchase
  So that I can buy products online

  @SMK-004
  Scenario: Complete checkout process
    Given I navigate to the ecommerce site
    And I login with valid credentials
    When I add a product to cart
    And I proceed to checkout
    And I fill shipping information
    And I complete the order
    Then I should see order confirmation

  @SMK-005 @multipleProducts
  Scenario: Checkout with multiple products
    Given I navigate to the ecommerce site
    And I login with valid credentials
    When I add multiple products to cart
    And I proceed to checkout
    And I fill shipping information
    And I complete the order
    Then I should see order confirmation