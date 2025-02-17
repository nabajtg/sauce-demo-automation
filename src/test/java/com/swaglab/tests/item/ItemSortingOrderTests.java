package com.swaglab.tests.item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import com.swaglab.constants.Constants.FilePaths;
import com.swaglab.data.item.ItemSortingTestData;
import com.swaglab.domains.Item;
import com.swaglab.enums.SortOptions;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.LoginPage;
import com.swaglab.tests.base.BaseTest;
import com.swaglab.utils.ExcelUtil;

public class ItemSortingOrderTests extends BaseTest{

    @Test
    public void SORT001_testItemsDisplayedInAscendingOrderByName(){
        testDisplayOrder(testCaseId);
    }


    @Test
    public void SORT002_testItemsDisplayedInAscendingOrderByName(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT003_testItemsDisplayedInAscendingOrderByName(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT004_testItemsDisplayedInDescendingOrderByName(){
        testDisplayOrder(testCaseId);    
    }

    @Test
    public void SORT005_testItemsDisplayedInDescendingOrderByName(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT006_testItemsDisplayedInDescendingOrderByName(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT007_testItemsDisplayedInAscendingOrderByPrice(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT008_testItemsDisplayedInAscendingOrderByPrice(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT009_testItemsDisplayedInAscendingOrderByPrice(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT010_testItemsDisplayedInDescendingOrderByPrice(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT011_testItemsDisplayedInDescendingOrderByPrice(){
        testDisplayOrder(testCaseId);
    }

    @Test
    public void SORT012_testItemsDisplayedInDescendingOrderByPrice(){
        testDisplayOrder(testCaseId);
    }

    private void testDisplayOrder(String testCaseId){
        ItemSortingTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_SORTING_TEST_DATA, 
            ItemSortingTestData.class, "TestCaseId", testCaseId);
        HomePage homePage = loginAndSelectDisplayOrder(testData);
        verifyDisplayOrder(homePage, testData.getDisplayOrder());
    }


    public HomePage loginAndSelectDisplayOrder(ItemSortingTestData testData){
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        homePage.selectSortOption(testData.getDisplayOrder());
        return homePage;
    }

    public void verifyDisplayOrder(HomePage homePage, String displayOrder){
        List<Item> items = homePage.getAllItemsDetailsDisplayed();
        assertUtil.assertTrue(items.size()>0, "Items not Empty");
        
        SortOptions sortOption = SortOptions.get(displayOrder);
        if(sortOption.equals(SortOptions.ASCENDING_BY_NAME) 
            || sortOption.equals(SortOptions.DESCENDING_BY_NAME)){
            
            List<String> namesInOriginalOrder = items.stream().map(Item::getName).toList();
            List<String> namesInExpectedOrder = Collections.emptyList();
            if (sortOption.equals(SortOptions.ASCENDING_BY_NAME)) {
                namesInExpectedOrder = items.stream().map(Item::getName).sorted().toList();
            } else {
                namesInExpectedOrder = new ArrayList<>(namesInOriginalOrder);
                Collections.sort(namesInExpectedOrder, Collections.reverseOrder());
            }

            assertUtil.assertEquals(namesInOriginalOrder, namesInExpectedOrder,
                    "Items Displayed in " + sortOption.getTestDataValue());

        }else{
            List<Double> pricesInOriginalOrder = items.stream().map(Item::getPrice).toList();
            List<Double> pricesInExpectedOrder = Collections.emptyList();
            if (sortOption.equals(SortOptions.ASCENDING_BY_PRICE)) {
                pricesInExpectedOrder = items.stream().map(Item::getPrice).sorted().toList();
            } else {
                pricesInExpectedOrder = new ArrayList<>(pricesInOriginalOrder);
                Collections.sort(pricesInExpectedOrder, Collections.reverseOrder());
            }

            assertUtil.assertEquals(pricesInOriginalOrder, pricesInExpectedOrder,
                    "Items Displayed in " + sortOption.getTestDataValue());
        }
    }
    

}
