package com.swaglab.tests.base;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.Status;
import com.swaglab.constants.Constants;
import com.swaglab.utils.AssertUtil;
import com.swaglab.utils.ExtentUtil;

import lombok.Getter;

public class BaseTest {

    protected static final ExtentUtil extentUtil = new ExtentUtil();
    
    private String suiteName; 

    protected WebDriver driver;
    protected @Getter String testCaseId;

    protected AssertUtil assertUtil;


    @BeforeMethod
    public void beforeMethod(ITestResult result, ITestContext testContext){
        String testName = result.getMethod().getMethodName();
        suiteName = testContext.getSuite().getName();
        System.out.println("Started test: " + testName);
        
        extentUtil.createExtentTest(testName, suiteName);
        extentUtil.getCurrentTest().log(Status.INFO, "Test: " + testName);
        
        assertUtil = new AssertUtil(extentUtil);
        testCaseId = testName.split("_")[0];

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
    public void afterMethod(ITestResult result){
        assertUtil.assertAll(result.getMethod().getMethodName());
        driver.quit();
        extentUtil.getReportInstance(suiteName).flush();    
    }


}
