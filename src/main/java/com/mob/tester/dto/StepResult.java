package com.mob.tester.dto;

public class StepResult {

	
	//String stepNo;
	String StepDesc;
	String expectedData;
	String actualData;
	String status;
	String screenshotPath;
	public String getStepDesc() {
		return StepDesc;
	}
	public void setStepDesc(String stepDesc) {
		StepDesc = stepDesc;
	}
	public String getExpectedData() {
		return expectedData;
	}
	public void setExpectedData(String expectedData) {
		this.expectedData = expectedData;
	}
	public String getActualData() {
		return actualData;
	}
	public void setActualData(String actualData) {
		this.actualData = actualData;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getScreenshotPath() {
		return screenshotPath;
	}
	public void setScreenshotPath(String screenshotPath) {
		this.screenshotPath = screenshotPath;
	}

	
	
}
