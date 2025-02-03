package com.swaglab.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest{

    protected WebDriver driver;

    @BeforeMethod
    public void initializeBrowser(){
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

    public String getTestCaseId(){
        
        return null;
    }
}
