package com.mob.tester.scenario;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;

import com.mob.tester.actions.driver.ActionDriver;
import com.mob.tester.dto.StepResult;
import com.mob.tester.scenario.strategy.ScenarioStrategy;
import com.ssts.pcloudy.Connector;
import com.ssts.pcloudy.exception.ConnectError;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Scenario4 extends ScenarioStrategy {

	@Override
	public List<StepResult> ExecuteScenario(AndroidDriver<WebElement> driver) {
		List<StepResult> stepResultList = new ArrayList<StepResult>();

		try {
			stepResultList.addAll(appLogin(driver));
			Map<String, Object> params = new HashMap<String, Object>();
			// Find base image button and tap
			driver.findElement(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[4]/android.widget.TextView"))
					.click();

			String baseImgPath = captureScreen(driver);
			System.out.println("Base IMG: " + baseImgPath);
			// Get base image ID
			String baseImageId = getImgId(baseImgPath);
			// go back to the screen
			driver.pressKey(new KeyEvent().withKey(AndroidKey.BACK));
			// Find secondary image and tap
			driver.findElement(By.xpath(
					"/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.LinearLayout/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[5]/android.widget.TextView"))
					.click();
			Thread.sleep(1000);
			// Capture screen
			String secondaryImgPath = captureScreen(driver);
			System.out.println("Secondary IMG: " + secondaryImgPath);
			// Get the image ID
			String secondaryImageId = getImgId(secondaryImgPath);
			// Find the difference between two images
			File file = findDiff(baseImageId, secondaryImageId, driver);
			StepResult stepResult = new StepResult();
			stepResult.setStepDesc("Difference image");
			stepResult.setScreenshotPath(file.getPath());
			stepResult.setStatus("Pass");
			stepResult.setActualData("");
			stepResult.setExpectedData("");
			stepResultList.add(stepResult);

			// Extract text from base image
			Map<String, Object> baseImgParams = new HashMap<String, Object>();
			baseImgParams.put("imageId", baseImageId);
			baseImgParams.put("word", "Titans");
			String ocrText = driver.executeScript("mobile:ocr:text", params).toString();
			System.out.println(ocrText);
			stepResultList.add(generateStepResult(this.getClass().getName(), "All test from base image", "PASS", "",
					ocrText, driver));

			// check if the given keyword exists
			if (ocrText.contains("Titans")) {
				System.out.println("The base image has 'Titans' word");
				stepResultList.add(generateStepResult(this.getClass().getName(), "titans present in base image", "PASS",
						"", "", driver));
			} else {
				stepResultList.add(generateStepResult(this.getClass().getName(), "titans present in base image", "Fail",
						"", "", driver));

			}

		} catch (Exception e) {
			e.printStackTrace();
			stepResultList
					.add(generateStepResult(this.getClass().getName(), "Draw smiley face :)", "Fail", "", "", driver));
		}

		return stepResultList;
	};

	public static String getImgId(String filePath) throws IOException, ConnectError {
		Connector con = new Connector("https://device.pcloudy.com/");
		// To get the Access key - Login to pCloudy platform->Go to Profile and click on
		// Settings->Copy the access key
		String authToken = con.authenticateUser(ActionDriver.userName,ActionDriver.apiKey);
		File fileToBeUploaded = new File(filePath + ".png");
		String imageId = con.getImageId(authToken, fileToBeUploaded);
		return imageId;
	}

	public static String captureScreen(AndroidDriver<WebElement> driver) throws IOException {
		File srcFile = driver.getScreenshotAs(OutputType.FILE);
		String filename = UUID.randomUUID().toString();
		File targetFile = new File(System.getProperty("user.dir")+"/" + filename + ".jpg");
		FileUtils.copyFile(srcFile, targetFile);
		// read a jpeg from a inputFile
		BufferedImage bufferedImage = ImageIO.read(new File(System.getProperty("user.dir")+"/" + filename + ".jpg"));
		// write the bufferedImage back to outputFile
		ImageIO.write(bufferedImage, "png", new File(System.getProperty("user.dir")+"/" + filename + ".png"));
		return System.getProperty("user.dir")+"/" + filename;
	}

	public static File findDiff(String baseImageId, String secondaryImageId, AndroidDriver<WebElement> driver)
			throws IOException {
		Map<String, Object> params = new HashMap<String, Object>();
		// Declare Image ID of Base image
		params.put("baseImageId", baseImageId);
		// declare Image ID of second image
		params.put("secondImageId", secondaryImageId);
		// Find the difference between two image
		String base64 = (String) driver.executeScript("mobile:visual:imageDiff", params);
		// Enter path
		File imgFile = new File(System.getProperty("user.dir")+"/diff.png");
		BufferedImage img = ImageIO
				.read(new ByteArrayInputStream(org.apache.commons.codec.binary.Base64.decodeBase64(base64)));
		ImageIO.write(img, "png", imgFile);
		return imgFile;
	}

}
