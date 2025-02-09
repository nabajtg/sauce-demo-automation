package com.swaglab.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.constants.Constants;
import com.swaglab.pages.base.BasePage;

import lombok.Getter;

public class IndividualProductPage extends BasePage{
    
    private @Getter String currentUrl;

    public IndividualProductPage(WebDriver driver){
        super(driver);
        currentUrl = driver.getCurrentUrl();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_details_name")
    private WebElement itemNameElement;

    @FindBy(css = ".inventory_details_desc")
    private WebElement itemDescElement;

    @FindBy(css = ".inventory_details_price")
    private WebElement itemPriceElement;

    public String getProductName(){
        return itemNameElement.getText();
    }

    public String getProductDesc(){
        return itemDescElement.getText();
    }

    public Double getProductPrice(){
        return Double.parseDouble(itemPriceElement.getText().replace("$", ""));
    }

    public void navigateBack() {
        navigateBack(Constants.HOME_PAGE_URL);
    }

}
