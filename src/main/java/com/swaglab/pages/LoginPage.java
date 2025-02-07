package com.swaglab.pages;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.swaglab.enums.LoginCredentials;
import com.swaglab.pages.base.BasePage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginPage extends BasePage{
    private final WebDriver driver;

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "user-name")
    private WebElement userNameInput;
    
    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "login_credentials")
    private WebElement loginCredsBox;

    private By loginErrorMessage = By.cssSelector("h3[data-test='error']"); 

    public HomePage loginExpectingSuccess(String userType){
        System.out.println("Login expecting success : START");
        performLogin(userType);
        return new HomePage(driver);
    }

    public LoginPage loginExpectingError(String userType){
        System.out.println("Login expecting error : START");
        performLogin(userType);
        return this;
    }

    public void performLogin(String userType){
        LoginCredentials creds = LoginCredentials.get(userType);
        System.out.println("Login User: " + creds.getUsername());
        userNameInput.sendKeys(creds.getUsername());
        passwordInput.sendKeys(creds.getPassword());
        loginButton.submit();
    }

    public String getLoginErrorMessage(){
        String errorMessage = driver.findElement(loginErrorMessage).getText();
        return errorMessage;
    }

    public Map<String, String> getLoginCreds(){
        Map<String, String> credsMap = new HashMap<>();
        String loginCredBoxText = loginCredsBox.getText();
        Arrays.asList(loginCredBoxText.split("\n")).forEach(text->{
            if(!text.contains("Accepted usernames are:")){
                credsMap.put(text, text);
            }
        });
        return credsMap;
    }

}
