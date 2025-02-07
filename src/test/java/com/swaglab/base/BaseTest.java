package com.swaglab.base;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.swaglab.constants.Constants;
import com.swaglab.utils.AssertUtil;

public abstract class BaseTest{

    protected AssertUtil assertUtil = new AssertUtil();
    protected WebDriver driver;
    protected String testCaseId;

    @BeforeMethod
    public void initializeBrowser(Method method){
        System.out.println("Test Name: " + method.getName());
        testCaseId = method.getName().split("_")[0];

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Constants.IMPLICITLY_WAIT_TIMEOUT));
        driver.get(Constants.BASE_URL);

        assertUtil.setResults(new ArrayList<String>());
        assertUtil.assertEquals(driver.getTitle(), "Swag Labs", "Page Title");
    }

    @AfterMethod
    public void tearDown(Method method){
        assertUtil.assertAll(method.getName());
        driver.quit();
    }
}
