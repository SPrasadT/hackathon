package com.mob.tester.scenario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;

import com.mob.tester.dto.StepResult;
import com.mob.tester.scenario.strategy.ScenarioStrategy;
import com.ssts.pcloudy.exception.ConnectError;

import io.appium.java_client.android.AndroidDriver;

public class Scenario1 extends ScenarioStrategy{

	
	
	@Override
	public List<StepResult> ExecuteScenario(AndroidDriver<WebElement> driver){
		List<StepResult> stepResultList = new ArrayList<StepResult>();
		//driver.launchApp();
		//stepResultList.add(generateStepResult(this.getClass().getName(),"Launch APP","PASS","","",driver));
		
		try {
			stepResultList.addAll(appLogin(driver));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return stepResultList;
	};
	
	
	
}
