package com.swaglab.pages.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public void enterText(By element, String text){
        try {
            driver.findElement(element).sendKeys(text);
        } catch (Exception e) {
            System.err.println("Unable to enter text in element : " + element);
            e.printStackTrace();
        }
    }

    public void click(By element){
        try {
            driver.findElement(element).click();
        } catch (Exception e) {
            System.err.println("Unable to click element : " + element);
            e.printStackTrace();
        }
    }

    public void click(WebElement element){
        try {
            element.click();
        } catch (Exception e) {
            System.err.println("Unable to click element : " + element);
            e.printStackTrace();
        }
    }

    public void selectOptionFromDropdown(By selectElementBy, String option){
        try {
            WebElement selectElement = driver.findElement(selectElementBy);
            Select select = new Select(selectElement);
            select.selectByValue(option);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unable to select option: " + option + " from " + selectElementBy);
        }
        
    }

    public void selectOptionFromDropdown(WebElement selectElement, String option){
        try {
            Select select = new Select(selectElement);
            select.selectByValue(option);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unable to select option: " + option + " from " + selectElement);
        }
    }

    public String getText(By element){
        try {
            return driver.findElement(element).getText();            
        } catch (Exception e) {
            System.err.println("Unable to get text for : " + element);
            e.printStackTrace();
            return null;
        }
    }

    public String getText(WebElement element){
        try {
            return element.getText();            
        } catch (Exception e) {
            System.err.println("Unable to get text for : " + element);
            e.printStackTrace();
            return null;
        }
    }

    public WebElement waitForElement(By element, Duration timeout){
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            System.err.println("Exceptions occured while waiting for element : " + element);
            e.printStackTrace();
            return null;
        }
    }

}
