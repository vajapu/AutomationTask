package com.HealthyfiMe.page;

import org.openqa.selenium.support.PageFactory;

import com.HealthyfiMe.BaseFunctions;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LogIn extends BaseFunctions {

	@AndroidFindBy(id = "com.healthifyme.basic:id/btn_email_phone")
	private MobileElement signInEmailButton;

	@AndroidFindBy(id = "com.healthifyme.basic:id/et_username")
	private MobileElement email;

	@AndroidFindBy(id = "com.healthifyme.basic:id/et_password")
	private MobileElement password;

	@AndroidFindBy(id = "com.healthifyme.basic:id/btn_login_signup")
	private MobileElement login;

	public LogIn(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator((AppiumDriver<MobileElement>) driver), this);
	}

	public void signInWithEmail() {
		if(isDisplayed(signInEmailButton)) {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("Login")));
			report(LogStatus.PASS,"SiginIn page is displayed");
			click(signInEmailButton);
		}else {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("Login")));
			report(LogStatus.FAIL,"SiginIn page is not displayed");
		}
	}

	public void enterEmailAndPassword(String mail, String pwd) {
		sendKeys(email, mail);
		sendKeys(password, pwd);
	}

	public void clickOnLogin() {
		click(login);
	}

}
