Feature: Ecommerce Smoke Tests
  As a customer
  I want to perform basic ecommerce operations
  So that I can shop online successfully

  @SMK-001
  Scenario: Login and homepage loads
    Given I navigate to the ecommerce site
    When I login with valid credentials
    Then the inventory page should load
    And the page title should contain "Swag Labs"

  @SMK-002
  Scenario: Add product to cart
    Given I navigate to the ecommerce site
    And I login with valid credentials
    When I add the first product to cart
    Then the cart should show items