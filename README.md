# SauceDemo Test Automation Project

## Overview

Test automation framework for [SauceDemo](https://www.saucedemo.com/), developed using **Java, Selenium WebDriver, and TestNG**. The framework supports data-driven testing, utilizing both **Excel and JSON** files for test data management.

## Technologies Used

- **Java** - Programming language for test script development
- **Selenium WebDriver** - Browser automation tool
- **TestNG** - Testing framework for test execution and reporting
- **Apache POI** - Library for reading/writing Excel files
- **JSON Simple** - Library for parsing JSON test data
- **Maven** - Build and dependency management tool

## Project Structure
```bash
SauceDemoTestAutomation/
│-- src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── pages/        # Page Object Model (POM) classes
│   │   │   ├── utils/        # Utility classes (Excel, JSON handling, WebDriver management)
│   ├── test/
│   │   ├── java/
│   │   │   ├── tests/        # Test cases
│   │   │   ├── testdata/     # JSON and Excel test data files
│-- pom.xml
│-- README.md
```
## Installation & Setup

### Clone the Repository
```sh
git clone https://github.com/your-username/SauceDemoTestAutomation.git
```
### Navigate to Project Directory
```sh
cd SauceDemoTestAutomation
```
### Install Dependencies using Maven
```sh
mvn clean install
```
### Running Tests

To execute the test cases, run:
```sh
mvn test
```
Alternatively, use TestNG XML to execute specific tests:
```sh
mvn test -DsuiteXmlFile=src/test/resources/testng.xml
```
## Test Data Management

### JSON Test Data

Test data stored in JSON format can be found in src/test/resources/testdata/data.json.
Example:
```JSON
{
  "username": "standard_user",
  "password": "secret_sauce"
}
```
### Excel Test Data

Test data stored in Excel files are located in src/test/resources/testdata/data.xlsx.

## Reporting

Test reports are generated under target/surefire-reports after test execution.

## Contributing

Feel free to fork this repository and submit pull requests.

## License

This project is licensed under the MIT License.
