package com.swaglab;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglab.base.BaseTest;
import com.swaglab.data.login.LoginTestData;
import com.swaglab.pages.LoginPage;
import com.swaglab.utils.ExcelUtil;

public class LoginTests extends BaseTest{

    private static final String testDataFilePath = "/home/nabajtg/automation-projects/swag-lab-automation/src/main/java/com/swaglab/data/login/LoginTestData.xlsx"; 

    @Test
    public void testValidLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(testDataFilePath, 
            LoginTestData.class, "TestCaseId", "LOGIN01");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginNormalUser(testData.getUser());
    }

    @Test
    public void testInvalidLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(testDataFilePath, 
            LoginTestData.class, "TestCaseId", "LOGIN02");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingError(testData.getUser());
        
        Assert.assertEquals(loginPage.getLoginErrorMessage(), testData.getError()); 
    }

    @Test
    public void testLockedUserLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(testDataFilePath, 
            LoginTestData.class, "TestCaseId", "LOGIN03");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingError(testData.getUser());
        
        Assert.assertEquals(loginPage.getLoginErrorMessage(), testData.getError()); 
    }


}
