Feature: Shopping Cart Management
  As a customer
  I want to manage my shopping cart
  So that I can control my purchases

  @cart @smoke @CRT-001
  Scenario: View cart contents
    Given I navigate to the ecommerce site
    And I login with valid credentials
    When I add a product to cart
    Then I should see the product in my cart

  @cart @regression @CRT-002
  Scenario: Verify cart badge updates
    Given I navigate to the ecommerce site
    And I login with valid credentials
    When I add a product to cart
    Then the cart badge should show "1"

  @cart @regression @CRT-003
  Scenario: Remove product from cart
    Given I navigate to the ecommerce site
    And I login with valid credentials
    When I add a product to cart
    And I remove the product from cart
    Then the cart should be empty