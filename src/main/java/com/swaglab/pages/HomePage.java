package com.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.swaglab.pages.base.BasePage;
import com.swaglab.utils.AssertUtil;

public class HomePage extends BasePage{
    
    private final WebDriver driver;
    private AssertUtil assertUtil = new AssertUtil();

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;

        assertUtil.assertEquals(driver.getCurrentUrl(), 
            "https://www.saucedemo.com/inventory.html", "Home Page Url");
        assertUtil.assertAll("Home Page");
        
        PageFactory.initElements(driver, this);
    }


}
