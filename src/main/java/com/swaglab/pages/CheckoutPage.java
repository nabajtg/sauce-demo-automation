package com.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.pages.base.BasePage;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "input#first-name")
    private WebElement firstNameFieldElement;

    @FindBy(css = "input#last-name")
    private WebElement lastNameFieldElement;

    @FindBy(css = "input#postal-code")
    private WebElement postalCodeFieldElement;
    
    @FindBy(css = "input#continue")
    private WebElement continueButtonElement;

    public OrderOverviewPage enterCustomerDetailsAndContinue(String firstName, String lastName, String postalCode){
        firstNameFieldElement.sendKeys(firstName);
        lastNameFieldElement.sendKeys(lastName);
        postalCodeFieldElement.sendKeys(postalCode);
        click(continueButtonElement);
        return new OrderOverviewPage(driver);
    }

}
