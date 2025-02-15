package com.swaglab.pages.base;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
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

    protected WebElement findElement(By byExpression){
        try {
            return driver.findElement(byExpression);
        } catch (Exception e) {
            System.err.println("Unable to find element : " + byExpression);
            e.printStackTrace();
            return null;
        }
    }

    protected void enterText(By element, String text){
        try {
            driver.findElement(element).sendKeys(text);
        } catch (Exception e) {
            System.err.println("Unable to enter text in element : " + element);
            e.printStackTrace();
        }
    }

    protected void click(By element){
        try {
            waiElementToBeClickable(element, Duration.ofSeconds(5));
            driver.findElement(element).click();
        } catch (Exception e) {
            System.err.println("Unable to click element : " + element);
            e.printStackTrace();
        }
    }

    protected void click(WebElement element){
        try {
            waiElementToBeClickable(element, Duration.ofSeconds(5));
            element.click();
        } catch (Exception e) {
            System.err.println("Unable to click element : " + element);
            e.printStackTrace();
        }
    }

    protected void selectOptionFromDropdown(By selectElementBy, String option){
        try {
            WebElement selectElement = driver.findElement(selectElementBy);
            Select select = new Select(selectElement);
            select.selectByValue(option);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unable to select option: " + option + " from " + selectElementBy);
        }
        
    }

    protected void selectOptionFromDropdown(WebElement selectElement, String option){
        try {
            Select select = new Select(selectElement);
            select.selectByValue(option);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Unable to select option: " + option + " from " + selectElement);
        }
    }

    protected String getText(By element){
        try {
            return driver.findElement(element).getText();            
        } catch (Exception e) {
            System.err.println("Unable to get text for : " + element);
            e.printStackTrace();
            return null;
        }
    }

    protected String getText(WebElement element){
        try {
            return element.getText();            
        } catch (Exception e) {
            System.err.println("Unable to get text for : " + element);
            e.printStackTrace();
            return null;
        }
    }

    protected void navigateBack(String expectedUrl){
        try {
            driver.navigate().back();
            waitForPage(expectedUrl, Duration.ofSeconds(5));
        } catch (Exception e) {
            System.err.println("Exceptions occured while navigating back to : " + expectedUrl);
            e.printStackTrace();
        }
    }

    protected Alert waitForAlertVisible(){
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            return wait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            System.err.println("Alert not found");
            e.printStackTrace();
            return null;
        }
    }

    protected WebElement waiElementToBeClickable(WebElement element, Duration timeout){
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.err.println("Element not clickable : " + element);
            e.printStackTrace();
            return null;
        }
    }

    protected WebElement waiElementToBeClickable(By element, Duration timeout){
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            System.err.println("Element not clickable : " + element);
            e.printStackTrace();
            return null;
        }
    }

    protected WebElement waitForElement(By element, Duration timeout){
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception e) {
            System.err.println("Exceptions occured while waiting for element : " + element);
            e.printStackTrace();
            return null;
        }
    }

    protected boolean waitForPage(String url, Duration timeout){
        try {
            Wait<WebDriver> wait = new WebDriverWait(driver, timeout);
            return wait.until(ExpectedConditions.urlContains(url));
        } catch (Exception e) {
            System.err.println("Exceptions occured while waiting for page : " + url);
            e.printStackTrace();
            return false;
        }
    }

    protected boolean isAlertPresent(){
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }catch(Exception e){
            System.err.println("Exception occured while checking if alert present");
            e.printStackTrace();
            return false;
        }
    }

    protected Alert getAlert(){
        try {
            return driver.switchTo().alert();
        } catch (Exception e) {
            System.err.println("Exception occured while getting alert");
            e.printStackTrace();
            return null;
        }
    }

    protected String getAlertText(){
        try{
            if(isAlertPresent()){
                Alert alert = getAlert();
                return alert.getText();
            }else{
                System.err.println("No Alert present");
                return null;
            }
        }catch(Exception e){
            System.err.println("Unable to get alert text");
            e.printStackTrace();
            return null;
        }
    }

    protected void acceptAlert(){
        try{
            if(isAlertPresent()){
                Alert alert = getAlert();
                System.out.println("Alert visible: " + alert.getText());
                alert.accept();
            }else{
                System.err.println("Unable to accept alert, No Alert present");
            }
        }catch(Exception e){
            System.err.println("Unable to accept alert");
            e.printStackTrace();
        }
    }

    protected void dismissAlert(){
        try{
            if(isAlertPresent()){
                Alert alert = getAlert();
                alert.dismiss();
            }else{
                System.err.println("Unable to dismiss alert, No Alert present");
            }
        }catch(Exception e){
            System.err.println("Unable to dismiss alert");
            e.printStackTrace();
        }
    }

}
