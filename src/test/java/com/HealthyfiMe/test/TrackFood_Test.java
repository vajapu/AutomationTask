package com.HealthyfiMe.test;

import org.testng.annotations.Test;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.util.List;
import java.util.Map;

import com.HealthyfiMe.BaseFunctions;
import com.HealthyfiMe.factory.DriverManager;
import com.HealthyfiMe.factory.DriverManagerFactory;
import com.HealthyfiMe.factory.DriverType;
import com.HealthyfiMe.page.Dashboard;
import com.HealthyfiMe.page.GetStarted;
import com.HealthyfiMe.page.LogIn;
import com.HealthyfiMe.page.SearchFood;
import com.HealthyfiMe.page.SignUp;
import com.HealthyfiMe.page.TermsAndCondition;
import com.HealthyfiMe.page.TrackFood;
import com.HealthyfiMe.page.TrackFood.MEALTYPE;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class TrackFood_Test {
	DriverManager driverManager;
	AppiumDriver<MobileElement> driver;
	TrackFood trackFood;
	TermsAndCondition termsAndCondition;
	SignUp signUp;
	GetStarted getStarted;
	LogIn logIn;
	ExtentReports report;

	@BeforeClass
	public void login() {
		String reportPath = "";
		if (System.getProperty("os.name").contains("Windows"))
			reportPath = System.getProperty("user.dir") + "\\ExtentReport\\ExtentReportResults.html";
		else
			reportPath = System.getProperty("user.dir") + "/ExtentReport/ExtentReportResults.html";
		report = new ExtentReports(reportPath);
		ExtentTest test = report.startTest("Setup");
		driverManager = DriverManagerFactory.getDriverManager(DriverType.Android);
		driver = driverManager.getDriver();
		trackFood = new TrackFood(driver);
		BaseFunctions.test = test;
		if (!trackFood.isCalEatenDisplayed()) {
			termsAndCondition = new TermsAndCondition(driver);
			termsAndCondition.agreeTermsAndCondition();
			signUp = new SignUp(driver);
			signUp.closeScreen();
			getStarted = new GetStarted(driver);
			getStarted.verifyGetStartedPage();
			getStarted.clickOnLogin();
			logIn = new LogIn(driver);
			logIn.signInWithEmail();
			logIn.enterEmailAndPassword("hme-testpr435@example.com", "password");
			logIn.clickOnLogin();
		}
		new Dashboard(driver).clickCalEaten();
		report.endTest(test);
		report.flush();
	}

	@Test(dataProvider = "MealTypes")
	public void trackBreakfast(MEALTYPE type) {
		ExtentTest test = report.startTest("track" + type);
		BaseFunctions.test = test;
		trackFood.selectMealType(type);
		SearchFood searchFood = new SearchFood(driver);
		List<String> items = searchFood.selectFoodItems(2);
		searchFood.clickOnDone();
		trackFood.handleRatingFlow();
		Map<String, List<String>> foodList = trackFood.getFoodList();
		trackFood.verifyIfFoodNamesAreDisplayed(type, foodList, items);
		report.endTest(test);
		report.flush();
	}

	@DataProvider(name = "MealTypes")
	private Object[][] mealTypeProvider() {
		return new Object[][] { { MEALTYPE.BREAKFAST }, { MEALTYPE.LUNCH }, { MEALTYPE.DINNER } };
	}

	@AfterClass
	public void quit() {
		driverManager.quitDriver();
	}
}
