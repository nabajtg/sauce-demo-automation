package com.swaglab.tests.cart;

import java.util.List;

import org.testng.annotations.Test;

import com.swaglab.constants.Constants.FilePaths;
import com.swaglab.data.cart.CartData;
import com.swaglab.domains.Item;
import com.swaglab.pages.CartPage;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.LoginPage;
import com.swaglab.tests.base.BaseTest;
import com.swaglab.utils.JsonUtil;

public class CartTests extends BaseTest{

    @Test
    public void CART001_testAddingSingleItemToCardFromHomePage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        gotoCartAndVerify(homePage, items);
        
    }

    @Test
    public void CART002_testAddingSingleItemToCardFromHomePage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        gotoCartAndVerify(homePage, items);
        
    }

    @Test
    public void CART003_testAddingSingleItemToCardFromHomePage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        gotoCartAndVerify(homePage, items);
        
    }

    @Test
    public void CART004_testAddingMultipleItemToCardFromHomePage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        gotoCartAndVerify(homePage, items);
                
    }

    @Test
    public void CART005_testAddingMultipleItemToCardFromHomePage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        gotoCartAndVerify(homePage, items);
                
    }

    @Test
    public void CART006_testAddingMultipleItemToCardFromHomePage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        gotoCartAndVerify(homePage, items);
                
    }

    @Test
    public void CART007_testRemovingSingleItemFromCartFromHomepage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        CartPage cartPage = gotoCartAndVerify(homePage, items);
        homePage = cartPage.goBackToHomePage();
        homePage.removeItemsFromCart(cartData.getItemsToRemove());
        gotoCartAndVerify(homePage, getRemainingItemsInCart(items, cartData.getItemsToRemove()));
    }

    @Test
    public void CART008_testRemovingMultipleItemsFromCartFromHomepage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        CartPage cartPage = gotoCartAndVerify(homePage, items);
        homePage = cartPage.goBackToHomePage();
        homePage.removeItemsFromCart(cartData.getItemsToRemove());
        gotoCartAndVerify(homePage, getRemainingItemsInCart(items, cartData.getItemsToRemove()));
    }

    @Test
    public void CART009_testRemovingMultipleItemsFromCartFromHomepage(){
        CartData cartData = JsonUtil.convertJsonToPojo(
            JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        HomePage homePage = loginAndAddToCart(testCaseId);
        List<Item> items = homePage.getItemDetails(cartData.getItemsToAdd());
        CartPage cartPage = gotoCartAndVerify(homePage, items);
        homePage = cartPage.goBackToHomePage();
        homePage.removeItemsFromCart(cartData.getItemsToRemove());
        gotoCartAndVerify(homePage, getRemainingItemsInCart(items, cartData.getItemsToRemove()));
    }
        
    private List<Item> getRemainingItemsInCart(List<Item> itemsAdded, 
        List<String> itemNamesToRemove){
        return itemsAdded.stream()
            .filter(item-> !itemNamesToRemove.contains(item.getName()))
            .toList();
    }
    
        
    private HomePage loginAndAddToCart(String testCaseId) {
        CartData cartData = JsonUtil.convertJsonToPojo(
                JsonUtil.readFile(FilePaths.CART_TEST_DATA + testCaseId), CartData.class);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(cartData.getUser());

        cartData.getItemsToAdd().forEach(itemName->{
            homePage.clickOnAddRemoveButton(itemName);
        });

        return homePage;
    }

    private CartPage gotoCartAndVerify(HomePage homePage, List<Item> items) {
        CartPage cartPage = homePage.goToCart();
        List<Item> itemsInCart = cartPage.getItemsInCart();

        assertUtil.assertEquals(itemsInCart.size(), items.size(),
                "No Of items in Cart");
        
        items.forEach(item->{
            if(itemsInCart.size()>0){
                Item itemInCart = itemsInCart.stream()
                                    .filter(cartItem-> cartItem.getName().equals(item.getName()))
                                    .findFirst().get();
                assertUtil.assertNotNull(itemInCart, "Item found in Cart");
                assertUtil.assertEquals(itemInCart.getName(), item.getName(),
                    "Item Name");
                assertUtil.assertEquals(itemInCart.getPrice(), item.getPrice(),
                        "Item Price");
                assertUtil.assertEquals(itemInCart.getDescription(),
                        item.getDescription(), "Item Description");
            }
        });

        return cartPage;
    }
}
