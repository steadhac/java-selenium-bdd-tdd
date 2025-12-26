# Test Cases

This document outlines the key test cases for the e-commerce automation project.

---

## 1. Smoke Tests

| ID      | Title                             | Steps                                                                                                 | Expected Result                        |
|---------|-----------------------------------|-------------------------------------------------------------------------------------------------------|----------------------------------------|
| SMK-001 | Login and homepage loads          | 1. Navigate to ecommerce site<br>2. Login with valid credentials                                      | Inventory page loads, title is correct |
| SMK-002 | Add first product to cart         | 1. Login<br>2. Add first product to cart                                                              | Product appears in cart                |
| SMK-003 | Data-driven checkout              | 1. Add product(s) to cart using data-driven approach<br>2. Proceed to checkout<br>3. Fill shipping info<br>4. Complete order | Order confirmation is shown   |
| SMK-004 | Complete checkout with valid login| 1. Login<br>2. Add product to cart<br>3. Proceed to checkout<br>4. Fill shipping info<br>5. Complete order | Order confirmation is shown   |
| SMK-005 | Checkout with multiple products   | 1. Login<br>2. Add multiple products to cart<br>3. Proceed to checkout<br>4. Fill shipping info<br>5. Complete order | Order confirmation is shown   |

---

## 2. Shopping Cart Tests

| ID      | Title                        | Steps                                                                                      | Expected Result                                 |
|---------|------------------------------|--------------------------------------------------------------------------------------------|-------------------------------------------------|
| CRT-001 | View cart contents           | 1. Navigate to ecommerce site<br>2. Login with valid credentials<br>3. Add product to cart<br>4. View cart | Product is visible in cart                      |
| CRT-002 | Verify cart badge updates    | 1. Navigate to ecommerce site<br>2. Login with valid credentials<br>3. Add product to cart | Cart badge shows "1"                            |
| CRT-003 | Remove product from cart     | 1. Navigate to ecommerce site<br>2. Login with valid credentials<br>3. Add product to cart<br>4. Remove product from cart | Cart is empty                                   |


## References

- [TEST_PLAN.md](TEST_PLAN.md)
- [test_data.md](test_data.md)
- [configuration.md](configuration.md)