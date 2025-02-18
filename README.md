# SauceDemo Test Automation Project

## Overview

Test automation framework for [SauceDemo](https://www.saucedemo.com/), developed using **Java, Selenium WebDriver, and TestNG**. The framework supports data-driven testing, utilizing both **Excel and JSON** files for test data management.

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
│   │   │   │   │   ├── utils        # Utility classes (Excel, JSON handling, WebDriver management)
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

For test data management, both **Excel** and **JSON** files are used.

### JSON Test Data

For **Cart and Order** test cases, JSON test data is utilize.

Example:

Test Data Location: src/test/com/swaglab/data/cart/CART009.json
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

Test data stored in Excel files are located in src/test/resources/testdata/data.xlsx.

### Utility Classes

- **ExcelUtil** - Handles reading and writing Excel test data.

- **JsonUtil** - Manages parsing and retrieving test data from JSON files.

## Reporting

Test reports are generated under target/surefire-reports after test execution.

## Contributing

Feel free to fork this repository and submit pull requests.

## License

This project is licensed under the MIT License.
