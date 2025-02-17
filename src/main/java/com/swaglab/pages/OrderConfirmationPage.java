package com.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.pages.base.BasePage;

public class OrderConfirmationPage extends BasePage{

    public OrderConfirmationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "h2.complete-header")
    private WebElement confirmationHeaderElement;

    @FindBy(css = "div.complete-text")
    private WebElement confirmationTextElement;

    public String getConfirmationHeaderText(){
        return confirmationHeaderElement.getText();
    }

    public String getConfirmationText(){
        return confirmationTextElement.getText();
    }
}
