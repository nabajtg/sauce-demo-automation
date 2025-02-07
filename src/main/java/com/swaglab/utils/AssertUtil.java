package com.swaglab.utils;

import org.testng.asserts.SoftAssert;

import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.IAssert;

public class AssertUtil extends SoftAssert {
	@Setter private List<String> results;
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
    			System.err.println(res);
    		}
    		
    	});
    	System.out.println("**************************************************************");
		System.out.println();
    }
}
