package com.swaglab.tests.item;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

import com.swaglab.constants.Constants;
import com.swaglab.constants.Constants.FilePaths;
import com.swaglab.data.item.ItemDetailsTestData;
import com.swaglab.domains.Item;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.IndividualItemPage;
import com.swaglab.pages.LoginPage;
import com.swaglab.tests.base.BaseTest;
import com.swaglab.utils.ExcelUtil;

public class ItemDetailsTests extends BaseTest{

    
    @Test
    public void ITEM001_testSingleItemDetails(){
        testSingleItemDetails(testCaseId);
    }

    @Test
    public void ITEM002_testSingleItemDetails(){
        testSingleItemDetails(testCaseId);
    }

    @Test
    public void ITEM003_testSingleItemDetails(){
        testSingleItemDetails(testCaseId);
    }

    @Test
    public void ITEM004_testMultipleItemDetails(){
        testMultipleItemDetails(testCaseId);
    }

    @Test
    public void ITEM005_testMultipleItemDetails(){
        testMultipleItemDetails(testCaseId);
    }

    @Test
    public void ITEM006_testMultipleItemDetails(){
        testMultipleItemDetails(testCaseId);
    }

    private void testSingleItemDetails(String testCaseId){
        ItemDetailsTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_DETAILS_TEST_DATA, 
            ItemDetailsTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        
        Item Item = homePage.getSingleItemDetails(testData.getItemNames());
        IndividualItemPage individualItemPage = homePage.goToIndividualItemPage(
                testData.getItemNames());

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


    private void testMultipleItemDetails(String testCaseId){
        ItemDetailsTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.ITEM_DETAILS_TEST_DATA, 
            ItemDetailsTestData.class, "TestCaseId", testCaseId);
        
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        
        List<String> itemNames = Arrays.asList(testData.getItemNames().split(","));
        itemNames.forEach(ItemName->{
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

}
