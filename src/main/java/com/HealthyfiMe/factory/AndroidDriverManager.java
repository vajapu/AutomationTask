package com.HealthyfiMe.factory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class AndroidDriverManager extends DriverManager {

	@Override
	protected void createDriver() {
//		File appDir = new File("src/test/resources/apps");
//		File app = new File(appDir, "HealthyfiMe.apk");

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("device", "Android");
		// mandatory capabilities
		capabilities.setCapability("deviceName", "Android");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "10");
		capabilities.setCapability("automationName", "UiAutomator2");
		capabilities.setCapability("appPackage", "com.healthifyme.basic");
		capabilities.setCapability("appActivity", "com.healthifyme.basic.activities.LaunchActivity");
//		capabilities.setCapability("app", app.getAbsolutePath());
		try {
			this.driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
