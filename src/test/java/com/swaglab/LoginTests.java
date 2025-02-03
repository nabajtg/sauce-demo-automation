package com.swaglab;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglab.base.BaseTest;
import com.swaglab.data.LoginTestData;
import com.swaglab.pages.LoginPage;
import com.swaglab.utils.ExcelUtil;

public class LoginTests extends BaseTest{

    private static final String testDataFilePath = "/home/nabajtg/automation-projects/swag-lab-automation/src/main/java/com/swaglab/data/LoginTestData.xlsx"; 

    @Test
    public void testValidLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(testDataFilePath, 
            LoginTestData.class, "TestCaseId", "LOGIN01");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginNormalUser(testData.getUser()); 
    }

    @Test
    public void testInvalidLogin(){
        
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingError("invalid_user");
        
        Assert.assertEquals(
            loginPage.getLoginErrorMessage(), 
            "Epic sadface: Username and password do not match any user in this service"
        ); 
    }
}
