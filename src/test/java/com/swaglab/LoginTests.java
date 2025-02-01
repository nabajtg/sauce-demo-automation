package com.swaglab;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.swaglab.base.BaseTest;
import com.swaglab.pages.LoginPage;

public class LoginTests extends BaseTest{

    @Test
    public void testValidLogin(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.loginNormalUser("standard_user"); 
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
