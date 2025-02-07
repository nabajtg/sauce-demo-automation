package com.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.pages.base.BasePage;

import lombok.Getter;

public class HomePage extends BasePage{
    
    private final WebDriver driver;
    private @Getter String currentUrl;;

    public HomePage(WebDriver driver){
        super(driver);
        this.driver = driver;
        this.currentUrl = driver.getCurrentUrl();
        PageFactory.initElements(driver, this);
    }


}
