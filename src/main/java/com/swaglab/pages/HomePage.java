package com.swaglab.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.constants.Constants;
import com.swaglab.domains.Product;
import com.swaglab.enums.SortOptions;
import com.swaglab.pages.base.BasePage;

import lombok.Getter;

public class HomePage extends BasePage{
    
    private @Getter String currentUrl;;

    public HomePage(WebDriver driver){
        super(driver);
        this.currentUrl = driver.getCurrentUrl();
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".inventory_list .inventory_item")
    private List<WebElement> itemElementList;

    @FindBy(css = "select.product_sort_container")
    private WebElement sortOptionsSelect;

    private By itemNameBy = By.className("inventory_item_name");
    private By itemDescBy = By.className("inventory_item_desc");
    private By itemPriceBy = By.className("inventory_item_price");

    public void selectSortOption(String sortOption){
        selectOptionFromDropdown(sortOptionsSelect, 
            SortOptions.get(sortOption).getSelectValue());
    }
    

    public List<Product> getAllItemsDetailsDisplayed(){
        List<Product> itemList = new ArrayList<>();
        itemElementList.forEach((itemElement)->{
            itemList.add(getSingleItemDetails(itemElement));
        });
        return itemList;
    }

    public Product getSingleItemDetails(WebElement itemElement){
        String name = itemElement.findElement(itemNameBy).getText();
        String desc = itemElement.findElement(itemDescBy).getText();
        String price = itemElement.findElement(itemPriceBy).getText();
        return Product.builder()
                .name(name)
                .description(desc)
                .price(Double.parseDouble(price.replace("$", "")))
                .build();
    }

    public Product getSingleItemDetails(String itemName){
        By itemElementBy = By.xpath("//div[text()='" + itemName + "']" 
                + "/parent::a/parent::div/parent::div[@class='inventory_item_description']");
        WebElement itemElement = findElement(itemElementBy);
        return getSingleItemDetails(itemElement);
    }

    public IndividualProductPage goToIndividualProductPage(String itemName){
        By itemElementBy = By.xpath("//div[text()='" + itemName + "']/parent::a");
        click(itemElementBy);
        waitForPage(Constants.INDIVIDUAL_ITEM_PAGE_URL, Duration.ofSeconds(5));
        return new IndividualProductPage(driver);
    }


    public void acceptPasswordBreachAlert() {
        // if(driver.switchTo().
        Alert alert = waitForAlertVisible();
        alert.accept();
    }



}
