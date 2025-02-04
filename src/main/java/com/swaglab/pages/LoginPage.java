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

    public HomePage loginNormalUser(String userType){
        performLogin(userType);
        return new HomePage(driver);
    }

    public LoginPage loginExpectingError(String userType){
        performLogin(userType);
        return this;
    }

    public void performLogin(String userType){
        LoginCredentials creds = LoginCredentials.get(userType);
        userNameInput.sendKeys(creds.getUsername());
        passwordInput.sendKeys(creds.getPassword());
        loginButton.submit();
    }

    public String getLoginErrorMessage(){
        return driver.findElement(loginErrorMessage).getText();
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
