package com.mob.tester;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mob.tester.actions.driver.ActionDriver;
import com.mob.tester.executor.ScenarioExecutor;

import io.appium.java_client.android.AndroidDriver;

public class BaseTest {

	private final static Logger LOGGER =LoggerFactory.getLogger(BaseTest.class);


	private final static ActionDriver actionDriver = new ActionDriver();
	
	private static ScenarioExecutor scenarioExecutor=new ScenarioExecutor();

	public static void main(String[] args) throws IOException {

		LOGGER.info("Initiating Test!!!!!");
		String userName = System.getProperty("app.pCloudyUsername");

        String apiKey = System.getProperty("app.pCloudyApiKey");
        
        
		AndroidDriver<WebElement> driver = actionDriver.getActionDriver();
		LOGGER.info("Driver Acquired");
		
		LOGGER.info("Calling Scenario Executor");
		
		scenarioExecutor.executeScenario(driver);
		driver.quit();
		
		
		
		

	}

}
