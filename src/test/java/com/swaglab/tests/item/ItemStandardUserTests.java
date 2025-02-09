package com.swaglab.tests.item;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.swaglab.constants.Constants;
import com.swaglab.constants.Constants.FilePaths;
import com.swaglab.data.item.ItemTestData;
import com.swaglab.domains.Item;
import com.swaglab.pages.CartPage;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.IndividualItemPage;
import com.swaglab.pages.LoginPage;
import com.swaglab.tests.base.BaseTest;
import com.swaglab.utils.ExcelUtil;

public class ItemStandardUserTests extends BaseTest{

    @Test
    public void ITEM001_testItemsDisplayedInAscendingOrderByNameForStandardUser(){
        ItemTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        
        List<String> itemNamesInOriginalOrder = items.stream().map(Item::getName).toList();
        List<String> itemNamesInAscendingOrder = items.stream().map(Item::getName).sorted().toList();
        assertUtil.assertTrue(
            itemNamesInOriginalOrder.equals(itemNamesInAscendingOrder),
            "Items Displayed in Ascending order by Name"
        );
    }

    @Test
    public void ITEM002_testItemsDisplayedInDescendingOrderByNameForStandardUser(){
        ItemTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<String> itemNamesInOriginalOrder = items.stream().map(Item::getName).toList();
        List<String> itemNamesInDescendingOrder = new ArrayList<>(itemNamesInOriginalOrder);
        Collections.sort(itemNamesInDescendingOrder, Collections.reverseOrder());
        assertUtil.assertTrue(
            itemNamesInOriginalOrder.equals(itemNamesInDescendingOrder),
            "Items Displayed in Descending order by Name"
        );
    }

    @Test
    public void ITEM003_testItemsDisplayedInAscendingOrderByPriceForStandardUser(){
        
        ItemTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<Double> itemPricesInOriginalOrder = items.stream().map(Item::getPrice).toList();
        List<Double> itemPricesInDescendingOrder = items.stream().map(Item::getPrice)
            .sorted().toList();
        assertUtil.assertTrue(
            itemPricesInOriginalOrder.equals(itemPricesInDescendingOrder),
            "Items Displayed in Ascending order by Price"
        );

    }

    @Test
    public void ITEM004_testItemsDisplayedInDescendingOrderByPriceForStandardUser(){
        
        ItemTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<Double> itemPricesInOriginalOrder = items.stream().map(Item::getPrice).toList();
        List<Double> itemPricesInDescendingOrder = new ArrayList<>(itemPricesInOriginalOrder);
        Collections.sort(itemPricesInDescendingOrder, Collections.reverseOrder());
        assertUtil.assertTrue(
            itemPricesInOriginalOrder.equals(itemPricesInDescendingOrder),
            "Items Displayed in Descending order by Price"
        );

    }

    @Test
    public void ITEM005_testSingleItemDetailsForStandardUser(){
        ItemTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        
        Item Item = homePage.getSingleItemDetails(testData.getProductNames());
        IndividualItemPage individualItemPage = homePage.goToIndividualItemPage(
                testData.getProductNames());

        assertUtil.assertTrue(individualItemPage.getCurrentUrl().contains(
                                Constants.INDIVIDUAL_ITEM_PAGE_URL
                            ), 
                            "Individual Page Url is Correct");
        assertUtil.assertEquals(individualItemPage.getItemName(), Item.getName(),
            "Item Name");
        assertUtil.assertEquals(individualItemPage.getItemDesc(), Item.getDescription(),
            "Item Desc");
        assertUtil.assertEquals(individualItemPage.getItemPrice(), Item.getPrice(),
            "Item Price");
    }

    @Test
    public void ITEM006_testMultipleItemDetailsForStandardUser(){
        ItemTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        
        List<String> ItemNames = Arrays.asList(testData.getProductNames().split(","));
        ItemNames.forEach(ItemName->{
            Item Item = homePage.getSingleItemDetails(ItemName);
            IndividualItemPage individualItemPage = homePage.goToIndividualItemPage(
                    ItemName);

            assertUtil.assertTrue(individualItemPage.getCurrentUrl().contains(
                                    Constants.INDIVIDUAL_ITEM_PAGE_URL
                                ), 
                                "Indivitual Page Url is Correct");
            assertUtil.assertEquals(individualItemPage.getItemName(), Item.getName(),
                "Item Name");
            assertUtil.assertEquals(individualItemPage.getItemDesc(), 
                Item.getDescription(), "Item Desc");
            assertUtil.assertEquals(individualItemPage.getItemPrice(), Item.getPrice(),
                "Item Price");
            
            individualItemPage.navigateBack();
        });   
    }

    @Test
    public void ITEM007_testAddingSingleItemToCardFromHomePageForStandardUser(){
        ItemTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        String ItemName = testData.getProductNames();
        Item Item = homePage.getSingleItemDetails(ItemName);
        homePage.clickOnAddToCart(ItemName);
        CartPage cartPage = homePage.goToCart();
        List<Item> ItemsInCart = cartPage.getItemsInCart();
        
        assertUtil.assertEquals(ItemsInCart.size(), 1, 
            "No Of items in Cart");
        assertUtil.assertEquals(ItemsInCart.get(0).getName(), Item.getName(),
            "Item Name");
        assertUtil.assertEquals(ItemsInCart.get(0).getPrice(), Item.getPrice(),
            "Item Price");
        assertUtil.assertEquals(ItemsInCart.get(0).getDescription(), 
            Item.getDescription(), "Item Description");
        

    }

    @Test
    public void ITEM008_testAddingMultipleItemsToCardFromHomePageForStandardUser(){
        ItemTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        List<String> ItemNames = Arrays.asList(testData.getProductNames().split(","));
        List<Item> ItemsInHomePage = homePage.getItemDetails(ItemNames);
        
        ItemNames.forEach(ItemName->{
            homePage.clickOnAddToCart(ItemName);
        });
        CartPage cartPage = homePage.goToCart();
        List<Item> ItemsInCart = cartPage.getItemsInCart();

        assertUtil.assertEquals(ItemsInCart.size(), ItemNames.size(),
                "No Of items in Cart");
        ItemsInHomePage.forEach(ItemInHomePage->{
            Item filteredItemInCart = ItemsInCart.stream()
                .filter(ItemInCart -> ItemInCart.getName().equals(
                            ItemInHomePage.getName()))
                .findFirst().get();
            
            assertUtil.assertEquals(filteredItemInCart.getName(), ItemInHomePage.getName(),
                "Item Name");
            assertUtil.assertEquals(filteredItemInCart.getPrice(), ItemInHomePage.getPrice(),
                "Item Price");
            assertUtil.assertEquals(filteredItemInCart.getDescription(),
                ItemInHomePage.getDescription(), "Item Description");
            
        });

        
        
        

    }


}
