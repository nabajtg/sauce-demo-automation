# SauceDemo Test Automation Project

## Overview

Test automation framework for [SauceDemo](https://www.saucedemo.com/) , using **Java, Selenium WebDriver, TestNG and Extent Report**, implemented using **Page Object Model** design pattern. The framework supports **data-driven testing**, utilizing both **Excel and JSON** files for test data management.

## Technologies Used

- **Java** - Programming language for test script development
- **Selenium WebDriver** - Browser automation tool
- **TestNG** - Testing framework for test execution and reporting
- **Apache POI** - Library for reading/writing Excel files
- **jackson-databind** - Library for parsing JSON test data
- **Extent Reports** - Library for generating extent reports
- **Maven** - Build and dependency management tool

## Project Structure
```bash
sauce-demo-automation/
│-- src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── swaglab/
│   │   │   │   │   ├── constants
│   │   │   │   │   ├── domains      
│   │   │   │   │   ├── enums
│   │   │   │   │   ├── pages        # Page Object Model (POM) classes
│   │   │   │   │   ├── utils        # Utility classes (Excel, JSON, Assert, Extent Report)
│   ├── test/
│   │   ├── java/
│   │   │   ├── com/
│   │   │   │   ├── swaglab/
│   │   │   │   │   ├── data        # Test data (Excel and JSON)
│   │   │   │   │   ├── tests       # Test cases   
│-- pom.xml
│-- README.md
│-- SauceLab_TestcaseDoc.xlsx
```

## Test Data Management

To demostrate data-driven testing, I have used both **Excel** and **JSON** files.

### Utility Classes

- **ExcelUtil** - Handles reading and writing Excel test data.

- **JsonUtil** - Manages parsing and retrieving test data from JSON files.

### JSON Test Data
Example: **src/test/java/com/swaglab/data/cart/CART009.json**
```JSON
{
    "user": "standard_user",
    "itemsToAdd": [
        "Sauce Labs Bolt T-Shirt",
        "Sauce Labs Fleece Jacket",
        "Sauce Labs Onesie"
    ],
    "itemsToRemove": [
        "Sauce Labs Bolt T-Shirt",
        "Sauce Labs Onesie"
    ]
}
```
### Excel Test Data
Example: **src/test/java/com/swaglab/data/login/LoginTestData.xlsx**

TestCaseId | User | Error
--- | --- | --- |
LOGIN001 | invalid_user | Epic sadface: Username and password do not match any user in this service

## Reporting
Test reports are generated using Extent Report under test-output after test execution. \
Created one utility class to manage Extent Reporting named **ExtentUtil**

## Assertion
Created one utility class **AssertUtil** which extends **SoftAssert** class from **TestNG framework** \
This utility class helps to record all assertion performed during test execution and at the last it logs all assertion results to console/extent-report.
```java
public class AssertUtil extends SoftAssert {
    @Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
        //record assert failure
    }

    @Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
        //record assert success
    }

    @Override
    public void assertAll(String msg) {
        // log all assertions at last
    }
}
```

## Contributing

Feel free to fork this repository and submit pull requests.

## License

This project is licensed under the MIT License.
