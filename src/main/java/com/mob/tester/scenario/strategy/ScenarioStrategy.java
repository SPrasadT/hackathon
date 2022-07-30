package com.mob.tester.scenario.strategy;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mob.tester.dto.StepResult;
import com.ssts.pcloudy.Connector;
import com.ssts.pcloudy.exception.ConnectError;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;

public class ScenarioStrategy {

	private final Logger LOGGER =LoggerFactory.getLogger(this.getClass());

	public List<StepResult> ExecuteScenario(AndroidDriver<WebElement> driver){
		return null;
	};
	

	public StepResult generateStepResult(String scenarioName,String description,String Status,String expectedData,String actualData,AndroidDriver<WebElement> driver){
		StepResult stepResult=new StepResult();
		stepResult.setStepDesc(description);
		stepResult.setScreenshotPath(captureScreenShot(driver,scenarioName));
		stepResult.setStatus(Status);
		stepResult.setActualData(actualData);
		stepResult.setExpectedData(expectedData);
		return stepResult;
	}
	
	public List<StepResult> appLogin(AndroidDriver<WebElement> driver) throws IOException, ConnectError {
		List<StepResult> stepResultList = new ArrayList<StepResult>();
		
		driver.launchApp();
		stepResultList.add(generateStepResult(this.getClass().getName(),"Launch APP","PASS","","",driver));
		
		JSONObject obj = new JSONObject(getCoordinates(driver));
        JSONArray arr = obj.getJSONArray("data");
        int xCoord = arr.getJSONObject(0).getInt("Left");
        arr.getJSONObject(0).getInt("Height");
        int yCoord = arr.getJSONObject(0).getInt("Top");
        arr.getJSONObject(0).getInt("Width");
        LOGGER.info("xCoord: "+xCoord);
        LOGGER.info("yCoord: "+yCoord);;
		stepResultList.add(generateStepResult(this.getClass().getName(),"Get Coordinates","PASS","",xCoord+","+yCoord,driver));

        
        clickOnCoordinates(xCoord,yCoord,driver);
        stepResultList.add(generateStepResult(this.getClass().getName(),"Click On Login Coordinates","PASS","",xCoord+","+yCoord,driver));

		return stepResultList;
		
		

	}
	
	
	String getCoordinates(AndroidDriver<WebElement> driver) throws IOException, ConnectError{
		
		BufferedImage bufferedImage = ImageIO.read(((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE));
		ImageIO.write(bufferedImage, "png", new File("/tmp/Login.png"));

		
		String baseImageId = getOCRCoords(new File("/tmp/Login.png"));
		LOGGER.info("baseImageId: "+baseImageId);
        Map< String, Object> params = new HashMap< String, Object>();
        params.put("imageId", baseImageId);
        params.put("word", "LOGIN");
        String ocrResponse = driver.executeScript("mobile:ocr:coordinate",params).toString();
        LOGGER.info(ocrResponse);
        JSONObject obj = new JSONObject(ocrResponse);
        JSONArray arr = obj.getJSONArray("data");
        int xCoord = arr.getJSONObject(0).getInt("Left");
        arr.getJSONObject(0).getInt("Height");
        int yCoord = arr.getJSONObject(0).getInt("Top");
        arr.getJSONObject(0).getInt("Width");
        LOGGER.info("xCoord: "+xCoord);
        LOGGER.info("yCoord: "+yCoord);
        
        return ocrResponse;
	}
	
	void clickOnCoordinates(int xCoord,int yCoord,AndroidDriver<WebElement> driver) {
		TouchAction touchAction = new TouchAction(driver);
        touchAction.tap(PointOption.point(xCoord, yCoord)).perform();
	}
	
	public String getOCRCoords (File fileToBeUploaded) throws IOException, ConnectError {
        Connector con = new Connector("https://device.pcloudy.com/");
        //To get the Access key - Login to pCloudy platform->Go to Profile and click on Settings->Copy the access key
        String authToken = con.authenticateUser("gupta.punit.pg@gmail.com", "km7j422xyh6txbr38t7qb2yd");
        System.out.println(Files.probeContentType(fileToBeUploaded.toPath()));
        String baseImageId = con.getImageId(authToken, fileToBeUploaded);
        
        return baseImageId;
    }
	
	String captureScreenShot(AndroidDriver<WebElement> driver,String scenarioName) {
		try{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
			String stimeStamp=new SimpleDateFormat("DD_MM_yyyy_hh_mm_ss").format(new Date());
			String sScreenShotPath= "/Screenshots/Screenshot_"+scenarioName.concat(stimeStamp)+".jpg";
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+sScreenShotPath));
			return sScreenShotPath;
	}
		catch(Exception e)
		{
			LOGGER.error("Error while taking screenshot",e);
		}
		return scenarioName;
	}
	
}
