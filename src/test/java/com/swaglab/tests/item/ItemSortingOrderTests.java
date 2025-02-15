package com.swaglab.tests.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.swaglab.constants.Constants.FilePaths;
import com.swaglab.data.item.ItemSortingTestData;
import com.swaglab.domains.Item;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.LoginPage;
import com.swaglab.tests.base.BaseTest;
import com.swaglab.utils.ExcelUtil;

public class ItemSortingOrderTests extends BaseTest{

    @Test
    public void SORT001_testItemsDisplayedInAscendingOrderByName(){
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        
        List<String> itemNamesInOriginalOrder = items.stream().map(Item::getName).toList();
        List<String> itemNamesInAscendingOrder = items.stream().map(Item::getName).sorted().toList();
        assertUtil.assertEquals(itemNamesInOriginalOrder, itemNamesInAscendingOrder,
            "Items Displayed in Ascending order by Name"
        );
    }

    @Test
    public void SORT002_testItemsDisplayedInAscendingOrderByName(){
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        
        List<String> itemNamesInOriginalOrder = items.stream().map(Item::getName).toList();
        List<String> itemNamesInAscendingOrder = items.stream().map(Item::getName).sorted().toList();
        assertUtil.assertEquals(itemNamesInOriginalOrder, itemNamesInAscendingOrder,
            "Items Displayed in Ascending order by Name"
        );
    }

    @Test
    public void SORT003_testItemsDisplayedInAscendingOrderByName(){
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        
        List<String> itemNamesInOriginalOrder = items.stream().map(Item::getName).toList();
        List<String> itemNamesInAscendingOrder = items.stream().map(Item::getName).sorted().toList();
        assertUtil.assertEquals(itemNamesInOriginalOrder, itemNamesInAscendingOrder,
            "Items Displayed in Ascending order by Name"
        );
    }

    @Test
    public void SORT004_testItemsDisplayedInDescendingOrderByName(){
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<String> itemNamesInOriginalOrder = items.stream().map(Item::getName).toList();
        List<String> itemNamesInDescendingOrder = new ArrayList<>(itemNamesInOriginalOrder);
        Collections.sort(itemNamesInDescendingOrder, Collections.reverseOrder());
        assertUtil.assertEquals(itemNamesInOriginalOrder, itemNamesInDescendingOrder,
            "Items Displayed in Descending order by Name"
        );
    }

    @Test
    public void SORT005_testItemsDisplayedInDescendingOrderByName(){
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<String> itemNamesInOriginalOrder = items.stream().map(Item::getName).toList();
        List<String> itemNamesInDescendingOrder = new ArrayList<>(itemNamesInOriginalOrder);
        Collections.sort(itemNamesInDescendingOrder, Collections.reverseOrder());
        assertUtil.assertEquals(itemNamesInOriginalOrder, itemNamesInDescendingOrder,
            "Items Displayed in Descending order by Name"
        );
    }

    @Test
    public void SORT006_testItemsDisplayedInDescendingOrderByName(){
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<String> itemNamesInOriginalOrder = items.stream().map(Item::getName).toList();
        List<String> itemNamesInDescendingOrder = new ArrayList<>(itemNamesInOriginalOrder);
        Collections.sort(itemNamesInDescendingOrder, Collections.reverseOrder());
        assertUtil.assertEquals(itemNamesInOriginalOrder, itemNamesInDescendingOrder,
            "Items Displayed in Descending order by Name"
        );
    }

    @Test
    public void SORT007_testItemsDisplayedInAscendingOrderByPrice(){
        
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
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
    public void SORT008_testItemsDisplayedInAscendingOrderByPrice(){
        
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
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
    public void SORT009_testItemsDisplayedInAscendingOrderByPrice(){
        
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        
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

    

}
