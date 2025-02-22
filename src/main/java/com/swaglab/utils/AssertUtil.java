package com.swaglab.utils;

import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.Status;

import lombok.Setter;
import java.util.List;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.asserts.IAssert;

public class AssertUtil extends SoftAssert {
	
	@Setter private List<String> results;
	
	private enum AssertStatus {
		PASSED, FAILED
	};

	private ExtentUtil extentUtil;
	
	public AssertUtil(ExtentUtil extentUtil) {
		this.extentUtil = extentUtil;
	}

	@Override
    public void onAssertFailure(IAssert<?> assertCommand, AssertionError ex) {
		ITestResult result = Reporter.getCurrentTestResult();
        result.setStatus(ITestResult.FAILURE);
        
        results.add(AssertStatus.FAILED + " VALIDATION FOR: " + assertCommand.getMessage() + 
        		", EXPECTED: " + assertCommand.getExpected() + ", ACTUAL: " + assertCommand.getActual());
	}
    
    @Override
	public void onAssertSuccess(IAssert<?> assertCommand) {
    	results.add(AssertStatus.PASSED + " VALIDATION FOR: " + assertCommand.getMessage() + 
        		", EXPECTED: " + assertCommand.getExpected() + ", ACTUAL: " + assertCommand.getActual());
	}
    
    @Override
    public void assertAll(String msg) {
    	System.out.println();
    	System.out.println("**************************************************************");
    	System.out.println("VALIDATIONS FOR " + msg);
		extentUtil.logInfoInReport("VALIDATIONS FOR " + msg);
    	
		results.forEach(res ->{
    		if(res.contains(AssertStatus.PASSED.toString())) {
				System.out.println(res);
				extentUtil.getCurrentTest().log(Status.PASS, res);
    		}else {
    			System.err.println(res);
				extentUtil.getCurrentTest().log(Status.FAIL, res);
    		}
    		
    	});
    	System.out.println("**************************************************************");
		System.out.println();
    }
}
