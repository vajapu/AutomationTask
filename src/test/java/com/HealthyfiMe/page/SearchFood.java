package com.HealthyfiMe.page;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.support.PageFactory;

import com.HealthyfiMe.BaseFunctions;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class SearchFood extends BaseFunctions{
	
	@AndroidFindBy(xpath = "//*[@resource-id='com.healthifyme.basic:id/tv_food_name']")
	private List<MobileElement> foodItem;
	
	@AndroidFindBy(xpath = "//*[@resource-id='com.healthifyme.basic:id/cb_track_food']")
	private List<MobileElement> addItem;
	
	@AndroidFindBy(id = "com.healthifyme.basic:id/tv_snackbar_cta_1")
	private MobileElement done;

	public SearchFood(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator((AppiumDriver<MobileElement>) driver), this);
	}
	
	public List<String> selectFoodItems(int number) {
		List<String> foodList=new ArrayList<String>();
		for (int i = 0; i < number; i++) {
			int random= new Random().nextInt(foodItem.size());
			foodList.add(getText(foodItem.get(random)));
			click(addItem.get(random));
		}
		return foodList;
	}
	
	public void clickOnDone() {
		if(isDisplayed(done)) {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("IFL")));
			report(LogStatus.PASS, "IFL screen is displayed");
			click(done);
		}
		else {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("IFL")));
			report(LogStatus.FAIL, "FIFL screen is not displayed");
		}
	}
}
