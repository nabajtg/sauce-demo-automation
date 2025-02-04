package com.swaglab;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglab.Constants.FilePaths;
import com.swaglab.base.BaseTest;
import com.swaglab.data.login.LoginTestData;
import com.swaglab.pages.LoginPage;
import com.swaglab.utils.ExcelUtil;

public class LoginTests extends BaseTest{

    @Test
    public void LOGIN01_testValidLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginNormalUser(testData.getUser());
    }

    @Test
    public void LOGIN02_testInvalidLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingError(testData.getUser());
        
        Assert.assertEquals(loginPage.getLoginErrorMessage(), testData.getError()); 
    }

    @Test
    public void LOGIN03_testLockedUserLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingError(testData.getUser());
        
        Assert.assertEquals(loginPage.getLoginErrorMessage(), testData.getError()); 
    }


}
