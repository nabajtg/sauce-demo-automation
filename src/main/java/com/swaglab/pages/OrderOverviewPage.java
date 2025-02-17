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

public class OrderOverviewPage extends BasePage{

    public OrderOverviewPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = ".cart_item")
    private List<WebElement> cartItemElementList;

    @FindBy(css = "button#checkout")
    private WebElement checkOutButtonElement;

    @FindBy(css = "div.summary_subtotal_label")
    private WebElement itemTotaElement;

    @FindBy(css = "div.summary_tax_label")
    private WebElement taxElement;

    @FindBy(css = "div.summary_total_label")
    private WebElement totalElement;

    @FindBy(css = "button#finish")
    private WebElement finishButtonElement;

    private By itemNameBy = By.cssSelector(".inventory_item_name");
    private By itemDescBy = By.cssSelector(".inventory_item_desc");
    private By itemPriceBy = By.cssSelector(".inventory_item_price");
    private By itemQuantityBy = By.cssSelector(".cart_quantity");


    public OrderConfirmationPage confirmOrder(){
        click(finishButtonElement);
        return new OrderConfirmationPage(driver);

    }

    public int getNoOfItems(){
        return cartItemElementList.size();
    }

    public Double getItemTotalAmount(){
        String itemTotalText = getText(itemTotaElement);
        return Double.parseDouble(itemTotalText.replace("Item total: $", ""));
    }

    public Double getTaxAmount(){
        String taxText = getText(taxElement);
        return Double.parseDouble(taxText.replace("Tax: $", ""));
    }

    public Double getTotalAmount(){
        String totalText = getText(totalElement);
        return Double.parseDouble(totalText.replace("Total: $", ""));
    }

    public List<Item> getItems(){
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
