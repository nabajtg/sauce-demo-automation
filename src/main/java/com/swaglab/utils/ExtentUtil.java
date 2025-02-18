package com.swaglab.utils;

import java.util.TimeZone;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentUtil {
   
    private ExtentReports extentReports = new ExtentReports();

    public void configureReporter(){
        ExtentSparkReporter extentSparkReporter  = new ExtentSparkReporter(
            System.getProperty("user.dir") + "/test-output/extentReport.html");
        
        extentSparkReporter.config().setDocumentTitle("Sauce Lab Automation Report");
        extentSparkReporter.config().setReportName("Test Report");
        extentSparkReporter.config().setTheme(Theme.DARK);
        extentSparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
        
        extentReports.attachReporter(extentSparkReporter);
    }

    public ExtentTest createExtentTest(String testName){
        return extentReports.createTest(testName);
    }

    public void logMessage(ExtentTest extentTest, String message){
        extentTest.log(Status.INFO, message);
    }

    public void logResult(ITestResult result, ExtentTest extentTest) {
            
        if (result.getStatus() == ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, result.getThrowable());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            extentTest.log(Status.PASS, result.getTestName());
        } else {
            extentTest.log(Status.SKIP, result.getTestName());
        }
    }

    public void tearDown() {
        extentReports.flush();
    }
}
