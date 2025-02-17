package com.swaglab.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.constants.Constants;
import com.swaglab.domains.Item;
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

    @FindBy(css = ".shopping_cart_link")
    private WebElement shoppingCartLink;

    private By itemNameBy = By.className("inventory_item_name");
    private By itemDescBy = By.className("inventory_item_desc");
    private By itemPriceBy = By.className("inventory_item_price");

    private static final String addRemoveButtonXpathTemplate = "//div[text()='ITEM_NAME']/parent::a/parent::div/following-sibling::div[@class='pricebar']/button";

    public void selectSortOption(String sortOption){
        selectOptionFromDropdown(sortOptionsSelect, 
            SortOptions.get(sortOption).getSelectValue());
        
        if(isAlertPresent()){
            acceptAlert();
        }
        
    }
    

    public List<Item> getAllItemsDetailsDisplayed(){
        List<Item> itemList = new ArrayList<>();
        itemElementList.forEach((itemElement)->{
            itemList.add(getSingleItemDetails(itemElement));
        });
        return itemList;
    }


    public Item getSingleItemDetails(WebElement itemElement){
        String name = itemElement.findElement(itemNameBy).getText();
        String desc = itemElement.findElement(itemDescBy).getText();
        Double price = 0.0;
        try {
            String priceString = itemElement.findElement(itemPriceBy).getText();
            price = Double.parseDouble(priceString.replace("$", ""));
        } catch (Exception e) {
            System.err.println("Not able to read/convert price");
            e.printStackTrace();
        }

        return Item.builder()
                .name(name)
                .description(desc)
                .price(price)
                .build();
    }

    public Item getSingleItemDetails(String itemName){
        By itemElementBy = By.xpath("//div[text()='" + itemName + "']" 
                + "/parent::a/parent::div/parent::div[@class='inventory_item_description']");
        WebElement itemElement = findElement(itemElementBy);
        return getSingleItemDetails(itemElement);
    }

    public List<Item> getItemDetails(List<String> productNames) {
        List<Item> products = new ArrayList<>();
        productNames.forEach(itemName->{
            products.add(getSingleItemDetails(itemName));
        });
        return products;
    }

    public IndividualItemPage goToIndividualItemPage(String itemName){
        By itemElementBy = By.xpath("//div[text()='" + itemName + "']/parent::a");
        click(itemElementBy);
        waitForPage(Constants.INDIVIDUAL_ITEM_PAGE_URL, Duration.ofSeconds(5));
        return new IndividualItemPage(driver);
    }

    public void clickOnAddRemoveButton(String itemName){
        String addToCardButtonXpath = addRemoveButtonXpathTemplate.replace("ITEM_NAME", itemName);
        click(By.xpath(addToCardButtonXpath));
        
    }

    public void addItemsToCart(List<String> itemNames){
        itemNames.forEach(itemName->{
            clickOnAddRemoveButton(itemName);
        });
    }

    public void removeItemsFromCart(List<String> itemNames){
        itemNames.forEach(itemName->{
            clickOnAddRemoveButton(itemName);
        });
    }

    public CartPage goToCart(){
        click(shoppingCartLink);
        return new CartPage(driver);
    }

}
