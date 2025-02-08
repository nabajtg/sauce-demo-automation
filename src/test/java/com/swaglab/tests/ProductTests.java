package com.swaglab.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.swaglab.constants.Constants.FilePaths;
import com.swaglab.data.product.ProductTestData;
import com.swaglab.domains.Product;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.LoginPage;
import com.swaglab.tests.base.BaseTest;
import com.swaglab.utils.ExcelUtil;

public class ProductTests extends BaseTest{

    @Test
    public void PRODUCT001_testProductsDisplayedInAscendingOrderByNameForStandardUser(){
        ProductTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.PRODUCT_TEST_DATA, 
            ProductTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        List<Product> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        
        List<String> itemNamesInOriginalOrder = items.stream().map(Product::getName).toList();
        List<String> itemNamesInAscendingOrder = items.stream().map(Product::getName).sorted().toList();
        assertUtil.assertTrue(
            itemNamesInOriginalOrder.equals(itemNamesInAscendingOrder),
            "Items Displayed in Ascending order by Name"
        );
    }

    @Test
    public void PRODUCT002_testProductsDisplayedInDescendingOrderByName(){
        ProductTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.PRODUCT_TEST_DATA, 
            ProductTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Product> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<String> itemNamesInOriginalOrder = items.stream().map(Product::getName).toList();
        List<String> itemNamesInDescendingOrder = new ArrayList<>(itemNamesInOriginalOrder);
        Collections.sort(itemNamesInDescendingOrder, Collections.reverseOrder());
        assertUtil.assertTrue(
            itemNamesInOriginalOrder.equals(itemNamesInDescendingOrder),
            "Items Displayed in Descending order by Name"
        );
    }

    @Test
    public void PRODUCT003_testProductsDisplayedInAscendingOrderByPriceForStandardUser(){
        
        ProductTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.PRODUCT_TEST_DATA, 
            ProductTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Product> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<Double> itemPricesInOriginalOrder = items.stream().map(Product::getPrice).toList();
        List<Double> itemPricesInDescendingOrder = items.stream().map(Product::getPrice)
            .sorted().toList();
        assertUtil.assertTrue(
            itemPricesInOriginalOrder.equals(itemPricesInDescendingOrder),
            "Items Displayed in Ascending order by Price"
        );

    }

    @Test
    public void PRODUCT004_testProductsDisplayedInDescendingOrderByPriceForStandardUser(){
        
        ProductTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.PRODUCT_TEST_DATA, 
            ProductTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        
        List<Product> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        List<Double> itemPricesInOriginalOrder = items.stream().map(Product::getPrice).toList();
        List<Double> itemPricesInDescendingOrder = new ArrayList<>(itemPricesInOriginalOrder);
        Collections.sort(itemPricesInDescendingOrder, Collections.reverseOrder());
        assertUtil.assertTrue(
            itemPricesInOriginalOrder.equals(itemPricesInDescendingOrder),
            "Items Displayed in Descending order by Price"
        );

    }
}
