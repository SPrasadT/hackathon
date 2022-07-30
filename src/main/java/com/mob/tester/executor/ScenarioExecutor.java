package com.mob.tester.executor;



import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mob.tester.result.ResultGenerator;
import com.mob.tester.result.ResultHolder;
import com.mob.tester.scenario.strategy.ScenarioStrategy;
import com.mob.tester.scenario.strategy.ScenatioStrategyHolder;

import io.appium.java_client.android.AndroidDriver;

public class ScenarioExecutor {

	
	private final Logger LOGGER =LoggerFactory.getLogger(this.getClass());
	
	
	public void executeScenario(AndroidDriver<WebElement> driver) {

		ScenatioStrategyHolder scenatioStrategyHolder=new ScenatioStrategyHolder();
		
		for (String scenarioName : ScenatioStrategyHolder.allScenarioHolder.keySet()) {

			LOGGER.info("Executing Scenario: {}",scenarioName);
			
			ScenarioStrategy scenario = ScenatioStrategyHolder.allScenarioHolder.get(scenarioName);
			ResultHolder.scenarioResultHolder.put(scenarioName, scenario.ExecuteScenario(driver));
			
			LOGGER.info("Executed Complete, Scenario: {}",scenarioName);
			

		}
		
		System.out.println(ResultHolder.scenarioResultHolder);
		ResultGenerator.generateReport();
	}

}
