package com.swaglab.tests.base;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.swaglab.constants.Constants;
import com.swaglab.utils.AssertUtil;
import com.swaglab.utils.ExtentUtil;

import lombok.Getter;

public abstract class BaseTest{

    protected WebDriver driver;
    protected @Getter String testCaseId;

    protected ExtentUtil extentUtil = new ExtentUtil();
    private ExtentTest extentTest;
    protected AssertUtil assertUtil;
    

    @BeforeTest
    public void beforeTest(){
        extentUtil.configureReporter();
    }

    @BeforeMethod
    public void beforeMethod(Method method, ITestContext testContext){
        String testName = method.getName();
        System.out.println("Test Name: " + testName);
        extentTest = extentUtil.createExtentTest(testName);
        extentTest.log(Status.INFO, "Test Name: " + testName);
        assertUtil = new AssertUtil(extentTest);
        

        testCaseId = method.getName().split("_")[0];

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(Constants.IMPLICITLY_WAIT_TIMEOUT));
        driver.get(Constants.BASE_URL);
        driver.manage().window().maximize();
        
        assertUtil.setResults(new ArrayList<String>());
        assertUtil.assertEquals(driver.getTitle(), "Swag Labs", "Page Title");

        
    }

    @AfterMethod
    public void afterMethod(ITestResult result, Method method){
        assertUtil.assertAll(method.getName());
        driver.quit();

        extentUtil.logResult(result, extentTest);
    }

    @AfterTest
    public void afterTest(){
        extentUtil.tearDown();
    }

    protected void logMessageInExtentReport(String message){
        extentUtil.logMessage(extentTest, message);
    }

}
