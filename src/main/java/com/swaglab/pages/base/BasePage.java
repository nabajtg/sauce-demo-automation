package com.swaglab.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
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

}
