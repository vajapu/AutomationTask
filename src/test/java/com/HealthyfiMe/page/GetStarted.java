package com.HealthyfiMe.page;

import org.openqa.selenium.support.PageFactory;

import com.HealthyfiMe.BaseFunctions;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class GetStarted extends BaseFunctions {

	@AndroidFindBy(id = "com.healthifyme.basic:id/btn_login")
	private MobileElement loginButton;

	public GetStarted(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator((AppiumDriver<MobileElement>) driver), this);
	}

	public void clickOnLogin() {
		click(loginButton);
	}

	public void verifyGetStartedPage() {
		if (isDisplayed(loginButton)) {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("GetStarted")));
			report(LogStatus.PASS, "GetStarted page is displayed successfully");
		} else {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("GetStarted")));
			report(LogStatus.FAIL, "GetStarted page is not displayed");
		}
	}

}
