package com.swaglab.pages.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.swaglab.utils.AssertUtil;

public abstract class BasePage {
    
    protected AssertUtil assertUtil = new AssertUtil();
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
