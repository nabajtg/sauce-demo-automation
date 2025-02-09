package com.swaglab.tests.login;

import org.testng.annotations.Test;

import com.swaglab.constants.Constants;
import com.swaglab.constants.Constants.FilePaths;
import com.swaglab.data.login.LoginTestData;
import com.swaglab.pages.HomePage;
import com.swaglab.pages.LoginPage;
import com.swaglab.tests.base.BaseTest;
import com.swaglab.utils.ExcelUtil;

public class LoginTests extends BaseTest{

    @Test
    public void LOGIN01_testStandardUserogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        assertUtil.assertEquals(homePage.getCurrentUrl(), 
            Constants.HOME_PAGE_URL, "Home Page Url");
    }

    @Test
    public void LOGIN02_testInvalidUserLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingError(testData.getUser());
        
        assertUtil.assertEquals(loginPage.getLoginErrorMessage(), testData.getError(),
                "Login Error Message"); 
       
    }

    @Test
    public void LOGIN03_testLockedUserLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingError(testData.getUser());
        
        assertUtil.assertEquals(loginPage.getLoginErrorMessage(), testData.getError(),
                "Login Error Message");  
    }

    @Test
    public void LOGIN04_testProblemUserLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        assertUtil.assertEquals(homePage.getCurrentUrl(), 
            Constants.HOME_PAGE_URL, "Home Page Url");
    }

    @Test
    public void LOGIN05_testPerformanceGlitchUserLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        assertUtil.assertEquals(homePage.getCurrentUrl(), 
            Constants.HOME_PAGE_URL, "Home Page Url");
    }

    @Test
    public void LOGIN06_testErrorUserLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        assertUtil.assertEquals(homePage.getCurrentUrl(), 
            Constants.HOME_PAGE_URL, "Home Page Url");
    }

    @Test
    public void LOGIN04_testVisualUserLogin(){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        assertUtil.assertEquals(homePage.getCurrentUrl(), 
            Constants.HOME_PAGE_URL, "Home Page Url");
    }


}
