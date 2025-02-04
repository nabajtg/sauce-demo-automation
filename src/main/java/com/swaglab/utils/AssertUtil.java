package com.swaglab.utils;

import org.testng.asserts.SoftAssert;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.IAssert;

@Slf4j
public class AssertUtil extends SoftAssert {
	private List<String> results = new ArrayList<String>();
	private enum Status {
		PASSED, FAILED
		};
    
	@Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		ITestResult result = Reporter.getCurrentTestResult();
        result.setStatus(ITestResult.FAILURE);
        
        results.add(Status.FAILED + " VALIDATION FOR: " + assertCommand.getMessage() + 
        		", EXPECTED: " + assertCommand.getExpected() + ", ACTUAL: " + assertCommand.getActual());
	}
    
    @Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
    	results.add(Status.PASSED + " VALIDATION FOR: " + assertCommand.getMessage() + 
        		", EXPECTED: " + assertCommand.getExpected() + ", ACTUAL: " + assertCommand.getActual());
	}
    
    @Override
    public void assertAll(String msg) {
    	System.out.println();
    	System.out.println("**************************************************************");
    	System.out.println("VALIDATIONS FOR " + msg);
    	results.forEach(res ->{
    		if(res.contains(Status.PASSED.toString())) {
    			System.out.println(res);
    		}else {
    			System.out.println(res);
    		}
    		
    	});
    	System.out.println("**************************************************************");
		System.out.println();
    }
}
