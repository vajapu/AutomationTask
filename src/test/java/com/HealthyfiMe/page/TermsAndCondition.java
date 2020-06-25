package com.HealthyfiMe.page;


import org.openqa.selenium.support.PageFactory;

import com.HealthyfiMe.BaseFunctions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TermsAndCondition extends BaseFunctions{
	
	@AndroidFindBy(id = "com.healthifyme.basic:id/btn_agree")
	private MobileElement iAgree;

	public TermsAndCondition(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator((AppiumDriver<MobileElement>) driver), this);
	}
	
	public void agreeTermsAndCondition() {
		click(iAgree);
	}
}
