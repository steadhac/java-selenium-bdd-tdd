# CI/CD Pipeline Setup

## GitHub Actions Workflow

### Basic Pipeline
```yaml
name: Ecommerce QA Automation

on:
  push:
    branches: [ main, develop ]
  pull_request:
    branches: [ main ]
  schedule:
    - cron: '0 2 * * *'  # Daily at 2 AM

jobs:
  test:
    runs-on: ubuntu-latest
    
    steps:
    - uses: actions/checkout@v3
    
    - name: Set up JDK 11
      uses: actions/setup-java@v3
      with:
        java-version: '11'
        distribution: 'temurin'
    
    - name: Cache Maven dependencies
      uses: actions/cache@v3
      with:
        path: ~/.m2
        key: ${{ runner.os }}-m2-${{ hashFiles('**/pom.xml') }}
    
    - name: Run Smoke Tests
      run: mvn test -Dcucumber.filter.tags="@smoke"
    
    - name: Run Regression Tests
      run: mvn test -Dcucumber.filter.tags="@regression"
    
    - name: Generate Test Report
      uses: dorny/test-reporter@v1
      if: success() || failure()
      with:
        name: Test Results
        path: target/surefire-reports/*.xml
        reporter: java-junit
    
    - name: Upload Test Reports
      uses: actions/upload-artifact@v3
      if: always()
      with:
        name: test-reports
        path: |
          target/surefire-reports/
          target/cucumber-reports/
```

## Jenkins Pipeline

### Jenkinsfile
```groovy
pipeline {
    agent any
    
    tools {
        maven 'Maven-3.8.1'
        jdk 'JDK-11'
    }
    
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                sh 'mvn clean compile'
            }
        }
        
        stage('Smoke Tests') {
            steps {
                sh 'mvn test -Dcucumber.filter.tags="@smoke"'
            }
        }
        
        stage('Regression Tests') {
            when {
                branch 'main'
            }
            steps {
                sh 'mvn test -Dcucumber.filter.tags="@regression"'
            }
        }
    }
    
    post {
        always {
            publishTestResults testResultsPattern: 'target/surefire-reports/*.xml'
            publishHTML([
                allowMissing: false,
                alwaysLinkToLastBuild: true,
                keepAll: true,
                reportDir: 'target/cucumber-reports',
                reportFiles: 'index.html',
                reportName: 'Cucumber Report'
            ])
        }
    }
}
```

## Docker Integration

### Dockerfile
```dockerfile
FROM maven:3.8.1-openjdk-11-slim

WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean compile

CMD ["mvn", "test"]
```

### Docker Compose
```yaml
version: '3.8'
services:
  tests:
    build: .
    environment:
      - HEADLESS=true
      - BASE_URL=https://www.saucedemo.com
    volumes:
      - ./target:/app/target
```

## Test Execution Strategies

### Parallel Execution
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-surefire-plugin</artifactId>
    <configuration>
        <parallel>methods</parallel>
        <threadCount>4</threadCount>
    </configuration>
</plugin>
```

### Environment-Specific Runs
```bash
# Development
mvn test -Denv=dev

# Staging  
mvn test -Denv=staging

# Production
mvn test -Denv=prod -Dcucumber.filter.tags="@smoke"
```