package com.mob.tester.scenario;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.mob.tester.dto.StepResult;
import com.mob.tester.scenario.strategy.ScenarioStrategy;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
public class Scenario3 extends ScenarioStrategy{
   
    
    
    @Override
	public List<StepResult> ExecuteScenario(AndroidDriver<WebElement> driver){
		List<StepResult> stepResultList = new ArrayList<StepResult>();
		
		try {
			stepResultList.addAll(appLogin(driver));
			
			driver.findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[3]/android.widget.TextView")).click();
	        Thread.sleep(1000);
	        
	        //first for the intial location to be long pressed
	        WebElement first= driver.findElementById("com.pcloudyhackathon:id/button");
	        //second location on which you need to move to
	        WebElement second= driver.findElementById("com.pcloudyhackathon:id/left_layout");
	        TouchAction action = new TouchAction(driver);
	        //performing the long press
	        action.longPress(new LongPressOptions().withElement(new
	                               ElementOption().withElement(first))).perform();
	        //performing the move to touch operation
	        action.moveTo(new ElementOption().withElement(second)).perform();
	        Thread.sleep(1000);
			stepResultList.add(generateStepResult(this.getClass().getName(),"Drag and Drop","PASS","","",driver));

		} catch (Exception e) {
			e.printStackTrace();
			stepResultList.add(generateStepResult(this.getClass().getName(),"Drag and Drop","Fail","","",driver));
		}
	return stepResultList;
	};
	
    
}