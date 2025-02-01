package com.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.swaglab.pages.base.BasePage;

public class HomePage extends BasePage{
    private final WebDriver driver;

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;

        Assert.assertEquals(driver.getCurrentUrl(), 
            "https://www.saucedemo.com/inventory.html");

        PageFactory.initElements(driver, this);
    }


}
