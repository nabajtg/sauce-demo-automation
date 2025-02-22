package com.swaglab.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentUtil {

    private ExtentReports extentReports = null;

    private Map<Integer, ExtentTest> testMap = new HashMap<>(); 

    private String suiteName = null;

    public ExtentReports getReportInstance(String suiteName){
        if(null == extentReports && null == this.suiteName){
            this.suiteName = suiteName;
            ExtentSparkReporter extentSparkReporter  = new ExtentSparkReporter(
            System.getProperty("user.dir") + "/test-output/" + suiteName + "_ExtentReport.html");
        
            extentSparkReporter.config().setDocumentTitle("Sauce Lab Automation Report");
            extentSparkReporter.config().setReportName("Test Report");
            extentSparkReporter.config().setTheme(Theme.DARK);
            extentSparkReporter.config().setTimeStampFormat("dd-MM-yyyy HH:mm:ss");
            TimeZone.setDefault(TimeZone.getTimeZone("Asia/Kolkata"));
            
            extentReports = new ExtentReports();
            extentReports.attachReporter(extentSparkReporter);
        }
        return extentReports;
    }

    public ExtentTest createExtentTest(String testName, String suiteName){
        ExtentTest extentTest = getReportInstance(suiteName).createTest(testName);
        testMap.put((int)Thread.currentThread().getId(), extentTest);
        return extentTest;
    }

    public ExtentTest getCurrentTest(){
        return testMap.get((int)Thread.currentThread().getId());
    }

    public void logInfoInReport(String message){
        getCurrentTest().log(Status.INFO, message);
    }

    public void tearDown(String suiteName){
        getReportInstance(suiteName).flush();
    }
}
