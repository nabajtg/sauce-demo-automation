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
    public void LOGIN01_testStandardUserLogin(){
        testCorrectLogin(testCaseId);
    }

    @Test
    public void LOGIN02_testInvalidUserLogin(){
       testIncorrectLogin(testCaseId);
    }

    @Test
    public void LOGIN03_testLockedUserLogin(){
        testIncorrectLogin(testCaseId); 
    }

    @Test
    public void LOGIN04_testProblemUserLogin(){
        testCorrectLogin(testCaseId);
    }

    @Test
    public void LOGIN05_testPerformanceGlitchUserLogin(){
        testCorrectLogin(testCaseId);
    }

    @Test
    public void LOGIN06_testErrorUserLogin(){
        testCorrectLogin(testCaseId);
    }

    @Test
    public void LOGIN04_testVisualUserLogin(){
        testCorrectLogin(testCaseId);
    }

    private void testCorrectLogin(String testCaseId){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = loginPage.loginExpectingSuccess(testData.getUser());
        assertUtil.assertEquals(homePage.getCurrentUrl(), 
            Constants.HOME_PAGE_URL, "Home Page Url");
    }

    private void testIncorrectLogin(String testCaseId){
        LoginTestData testData = ExcelUtil.getRowIntoPojo(FilePaths.LOGIN_TEST_DATA, 
            LoginTestData.class, "TestCaseId", testCaseId);
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginExpectingError(testData.getUser());
        
        assertUtil.assertEquals(loginPage.getLoginErrorMessage(), testData.getError(),
                "Login Error Message"); 
    }


}
