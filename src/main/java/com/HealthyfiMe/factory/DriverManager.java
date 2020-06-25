package com.HealthyfiMe.factory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class DriverManager {
	protected AppiumDriver<MobileElement> driver;
	protected abstract void createDriver();
	public void quitDriver() {
		if(driver!=null) {
			driver.quit();
			driver=null;
		}
	}
	public AppiumDriver<MobileElement> getDriver(){
		if(driver==null) {
			createDriver();
		}
		return driver;
	}
}
