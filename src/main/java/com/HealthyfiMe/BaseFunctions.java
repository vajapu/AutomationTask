package com.HealthyfiMe;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class BaseFunctions {
	private AppiumDriver<MobileElement> driver;
	int waitTime = 30;
	public static ExtentTest test;

	public BaseFunctions(AppiumDriver<MobileElement> driver) {
		this.driver = driver;
	}

	public void click(MobileElement element) {
		try {
			scrollIntoView(element);
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOf(element)).click();
		} catch (Exception e) {
			test.log(LogStatus.FAIL,e);
			test.log(LogStatus.FAIL,test.addScreenCapture(takeScreenshot("TrackedFood")));
		}
	}

	public void sendKeys(MobileElement element, String text) {
		try {
			scrollIntoView(element);
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
		} catch (Exception e) {
			test.log(LogStatus.FAIL,e);
			test.log(LogStatus.FAIL,test.addScreenCapture(takeScreenshot("TrackedFood")));
		}
	}

	public String getText(MobileElement element) {
		try {
			scrollIntoView(element);
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			return wait.until(ExpectedConditions.visibilityOf(element)).getText();
		} catch (Exception e) {
			test.log(LogStatus.FAIL,e);
			test.log(LogStatus.FAIL,test.addScreenCapture(takeScreenshot("TrackedFood")));
		}
		return null;
	}

	public boolean isDisplayed(MobileElement element) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, waitTime);
			return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void scrollIntoView(MobileElement element) {
		try {
			Dimension size = driver.manage().window().getSize();
			int starty = (int) (size.height * 0.4);
			int startx = size.width / 5;
			int endy = (int) (size.height * 0.6);
			int endx = size.width / 5;
			boolean found = false;
			long startTime = System.currentTimeMillis(), endTime = 0;
			while (!found && (endTime - startTime < 60000)) {
				try {
					found = element.isDisplayed();
				} catch (Exception e) {
				}
				if (found)
					break;
				new TouchAction(driver).press(PointOption.point(startx, endy))
						.waitAction(WaitOptions.waitOptions(Duration.ofMillis(500)))
						.moveTo(PointOption.point(endx, starty)).release().perform();
				Thread.sleep(1000);
				endTime = System.currentTimeMillis();
			}

		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public synchronized String takeScreenshot(String screenShotName) {
		try {
			String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
			File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String destination = System.getProperty("user.dir") + "/ExtentReport/" + "/screenshots/" + screenShotName
					+ dateName + ".png";
			File finalDestination = new File(destination);
			FileUtils.copyFile(source, finalDestination);
			return destination;
		} catch (IOException e) {
			System.err.println("ERROR IN TAKING SCREENSHOT: " + e.getMessage());
		}
		return null;
	}
	
	public void report(LogStatus status,String message ) {
		test.log(status, message);
	}

}
