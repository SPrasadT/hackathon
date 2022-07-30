package com.mob.tester.actions.driver;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class ActionDriver {

	public AndroidDriver<WebElement> getActionDriver() {
		/*
		 * DesiredCapabilities capabilities = new DesiredCapabilities();
		 * capabilities.setCapability("pCloudy_Username", "gupta.punit.pg@gmail.com");
		 * capabilities.setCapability("pCloudy_ApiKey", "km7j422xyh6txbr38t7qb2yd");
		 * capabilities.setCapability("pCloudy_DurationInMinutes", 2);
		 * capabilities.setCapability("newCommandTimeout", 600);
		 * capabilities.setCapability("launchTimeout", 90000);
		 * capabilities.setCapability("pCloudy_DeviceFullName",
		 * "ONEPLUS_9R_Android_11.0.0_feae8");
		 * capabilities.setCapability("platformVersion", "11.0.0");
		 * capabilities.setCapability("platformName", "Android");
		 * capabilities.setCapability("automationName", "uiautomator2");
		 * capabilities.setCapability("pCloudy_ApplicationName",
		 * "PCloudyHackathon.apk"); capabilities.setCapability("appPackage",
		 * "com.pcloudyhackathon.ui.login.LoginActivity");
		 * capabilities.setCapability("appActivity", "com.pcloudyhackathon");
		 * capabilities.setCapability("pCloudy_WildNet", "false");
		 * capabilities.setCapability("pCloudy_EnableVideo", "true");
		 * capabilities.setCapability("pCloudy_EnablePerformanceData", "true");
		 * capabilities.setCapability("pCloudy_EnableDeviceLogs", "true");
		 */
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("pCloudy_Username", "gupta.punit.pg@gmail.com");
		capabilities.setCapability("pCloudy_ApiKey", "km7j422xyh6txbr38t7qb2yd");
		capabilities.setCapability("pCloudy_DurationInMinutes", 10);
		capabilities.setCapability("newCommandTimeout", 600);
		capabilities.setCapability("launchTimeout", 90000);
		capabilities.setCapability("pCloudy_DeviceManufacturer", "SAMSUNG");
		capabilities.setCapability("pCloudy_DeviceVersion", "12.0.0");
		capabilities.setCapability("platformVersion", "12.0.0");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("automationName", "uiautomator2");
		capabilities.setCapability("pCloudy_ApplicationName", "PCloudyHackathon.apk");
		capabilities.setCapability("appPackage", "com.pcloudyhackathon");
		capabilities.setCapability("appActivity", "com.pcloudyhackathon.ui.login.LoginActivity");
		capabilities.setCapability("pCloudy_WildNet", "false");
		capabilities.setCapability("pCloudy_EnableVideo", "false");
		capabilities.setCapability("pCloudy_EnablePerformanceData", "false");
		capabilities.setCapability("pCloudy_EnableDeviceLogs", "false");
		//AndroidDriver<WebElement> driver = new AndroidDriver<WebElement>(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities);
		
		AndroidDriver<WebElement> driver = null;
		try {
			driver = new AndroidDriver<WebElement>(
					new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return driver;
	}
}
