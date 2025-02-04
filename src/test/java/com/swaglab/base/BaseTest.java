package com.swaglab.base;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class BaseTest{

    protected WebDriver driver;
    protected String testCaseId;

    @BeforeMethod
    public void initializeBrowser(Method method){
        log.info("Test Name: " + method.getName());
        testCaseId = method.getName().split("_")[0];

        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
