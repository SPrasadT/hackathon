package com.mob.tester.scenario;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.PointerInput.Kind;
import org.openqa.selenium.interactions.PointerInput.MouseButton;
import org.openqa.selenium.interactions.PointerInput.Origin;

import com.mob.tester.SAMPLE;
import com.mob.tester.dto.StepResult;
import com.mob.tester.scenario.strategy.ScenarioStrategy;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class Scenario2 extends ScenarioStrategy {

	private Duration NO_TIME = Duration.ofMillis(0);
    private Origin VIEW = Origin.viewport();
    private Duration STEP_DURATION = Duration.ofMillis(20);
	
	@Override
	public List<StepResult> ExecuteScenario(AndroidDriver<WebElement> driver) {
		List<StepResult> stepResultList = new ArrayList<StepResult>();
		
		try {
			stepResultList.addAll(appLogin(driver));

			//driver.findElement(By.id("com.pcloudyhackathon:id/login")).click();
			
			List<WebElement> elements = driver.findElements(By.id("com.pcloudyhackathon:id/tv_name"));
			
			for(WebElement element:elements) {
				if(element.getText().equals("Draw")) {
					element.click();
					break;
				}
			}
			drawFace(driver);
			stepResultList.add(generateStepResult(this.getClass().getName(),"Draw smiley face :)","PASS","","",driver));
			
		} catch (Exception e) {
			e.printStackTrace();
			stepResultList.add(generateStepResult(this.getClass().getName(),"Draw smiley face :)","Fail","","",driver));
		}
		
		return stepResultList;
	};

	private Point getPointOnCircle(int step, int totalSteps, Point origin, double radius) {
		double theta = 2 * Math.PI * ((double) step / totalSteps);
		int x = (int) Math.floor(Math.cos(theta) * radius);
		int y = (int) Math.floor(Math.sin(theta) * radius);
		return new Point(origin.x + x, origin.y + y);
	}

	private void drawCircle(AppiumDriver driver, Point origin, double radius, int steps, boolean isSmile) {
		Point firstPoint = getPointOnCircle(0, steps, origin, radius);

		PointerInput finger = new PointerInput(Kind.TOUCH, "finger");
		Sequence circle = new Sequence(finger, 0);
		circle.addAction(finger.createPointerMove(NO_TIME, VIEW, firstPoint.x, firstPoint.y));
		circle.addAction(finger.createPointerDown(MouseButton.LEFT.asArg()));

		for (int i = 1; i < steps + 1; i++) {
			Point point = getPointOnCircle(i, steps, origin, radius);
			circle.addAction(finger.createPointerMove(STEP_DURATION, VIEW, point.x, point.y));
			if (isSmile) {
				if (i == steps / 2) {
					break;
				}
			}
		}

		circle.addAction(finger.createPointerUp(MouseButton.LEFT.asArg()));

		driver.perform(Arrays.asList(circle));
	}

	public void drawFace(AndroidDriver<WebElement> driver) {
		Point head = new Point(220, 450);
		Point leftEye = head.moveBy(-50, -50);
		Point rightEye = head.moveBy(50, -50);
		Point mouth = head.moveBy(0, 50);

		drawCircle(driver, head, 150, 30, false);
		drawCircle(driver, leftEye, 20, 20, false);
		drawCircle(driver, rightEye, 20, 20, false);
		drawCircle(driver, mouth, 40, 20, true);
	}
}