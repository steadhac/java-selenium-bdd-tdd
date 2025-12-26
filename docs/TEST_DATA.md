# Test Data Management

## Overview
Centralized test data management for consistent and maintainable test scenarios.

## Test Data Structure

### User Credentials
```java
public class TestUsers {
    public static final String STANDARD_USER = "standard_user";
    public static final String LOCKED_USER = "locked_out_user";
    public static final String PROBLEM_USER = "problem_user";
    public static final String PASSWORD = "secret_sauce";
}
```

### Product Data
```java
public class TestProducts {
    public static final String BACKPACK = "sauce-labs-backpack";
    public static final String BIKE_LIGHT = "sauce-labs-bike-light";
    public static final String BOLT_TSHIRT = "sauce-labs-bolt-t-shirt";
}
```

### Shipping Information
```java
public class TestShipping {
    public static final String FIRST_NAME = "John";
    public static final String LAST_NAME = "Doe";
    public static final String POSTAL_CODE = "12345";
    public static final String COUNTRY = "United States";
}
```

## Data Generation

### Faker Integration
```java
public class DataGenerator {
    private static Faker faker = new Faker();
    
    public static UserData generateUser() {
        return UserData.builder()
            .firstName(faker.name().firstName())
            .lastName(faker.name().lastName())
            .email(faker.internet().emailAddress())
            .phone(faker.phoneNumber().phoneNumber())
            .build();
    }
}
```

### External Data Sources
- **CSV Files**: `src/test/resources/testdata/users.csv`
- **JSON Files**: `src/test/resources/testdata/products.json`
- **Properties**: `src/test/resources/testdata/config.properties`

## Environment-Specific Data

### Development
```properties
base.url=https://dev.saucedemo.com
db.url=jdbc:mysql://dev-db:3306/ecommerce
```

### Staging
```properties
base.url=https://staging.saucedemo.com
db.url=jdbc:mysql://staging-db:3306/ecommerce
```

### Production
```properties
base.url=https://www.saucedemo.com
db.url=jdbc:mysql://prod-db:3306/ecommerce
```

## Best Practices
1. **No hardcoded data** in test methods
2. **Environment-specific** configuration files
3. **Data cleanup** after test execution
4. **Parameterized tests** for data-driven scenarios
5. **Faker library** for dynamic test data