package com.HealthyfiMe.page;

import org.openqa.selenium.support.PageFactory;

import com.HealthyfiMe.BaseFunctions;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class Dashboard extends BaseFunctions{

	@AndroidFindBy(xpath = "//*[contains(@text,'Cal Eaten')]")
	private MobileElement calorieEaten;

	public Dashboard(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator((AppiumDriver<MobileElement>) driver), this);
	}
	
	public void clickCalEaten() {
		if(isDisplayed(calorieEaten)) {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("Dashboard")));
			report(LogStatus.PASS,"Dashboard page is displayed");
			click(calorieEaten);
		}else {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("Dashboard")));
			report(LogStatus.FAIL,"Dashboard page is not displayed");
		}
	}
	
}
