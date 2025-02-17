package com.swaglab.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.domains.Item;
import com.swaglab.pages.base.BasePage;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItemElementList;

    private By itemNameBy = By.cssSelector(".inventory_item_name");
    private By itemDescBy = By.cssSelector(".inventory_item_desc");
    private By itemPriceBy = By.cssSelector(".inventory_item_price");
    private By itemQuantityBy = By.cssSelector(".cart_quantity");
    private By removeButtonBy = By.cssSelector("button");
    

    public int getNoOfItemsInCart(){
        return cartItemElementList.size();
    }

    public HomePage goBackToHomePage(){
        navigateBack();
        return new HomePage(driver);
    }

    public List<Item> getItemsInCart(){
        List<Item> itemsInCart = new ArrayList<>();
        cartItemElementList.forEach(cartItemElement->{
            String name = cartItemElement.findElement(itemNameBy).getText();
            String desc = cartItemElement.findElement(itemDescBy).getText();
            String price = cartItemElement.findElement(itemPriceBy).getText();
            String quantity = cartItemElement.findElement(itemQuantityBy).getText();
            itemsInCart.add(Item.builder()
                .name(name)
                .description(desc)
                .price(Double.parseDouble(price.replace("$", "")))
                .quantity(Integer.parseInt(quantity))
                .build());
        });
        return itemsInCart;
    } 

}
