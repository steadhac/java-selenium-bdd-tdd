# Configuration Management

## Structure

```
src/test/resources/
├── config.properties          # Main test settings
├── environments/
│   ├── dev.properties        # Development URLs & DB
│   ├── staging.properties    # Staging URLs & DB
│   └── prod.properties       # Production URLs & DB
└── features/
```

## Configuration Files

### Main Config (`config.properties`)
- Browser settings (chrome, firefox)
- Test timeouts and waits
- Environment selector
- Screenshot and reporting paths

### Environment Files (`environments/*.properties`)
- Application URLs
- Environment-specific credentials
- **Test credentials** (username/password)

## Usage

### Switch Environments
```bash
# Development (default)
mvn test

# Staging
mvn test -Denvironment=staging

# Production
mvn test -Denvironment=prod
```

### In Code
```java
// Get URLs from environment config
String appUrl = ConfigManager.getAppUrl();

// Get test settings from main config
String browser = ConfigManager.getBrowser();
boolean headless = ConfigManager.isHeadless();

// Get test credentials from environment config
String username = ConfigManager.getTestUsername();
String password = ConfigManager.getTestPassword();
```

## Chrome Configuration
- Password save popups disabled
- Weak password warnings suppressed
- Headless mode support

## Best Practices
- Never hardcode URLs in test code
- Use environment-specific credentials
- Keep sensitive data in environment files
- Use ConfigManager for all property access
- **Test credentials managed per environment**
- **Page Objects handle their own credential logic**