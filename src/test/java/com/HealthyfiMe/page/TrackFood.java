package com.HealthyfiMe.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.support.PageFactory;

import com.HealthyfiMe.BaseFunctions;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class TrackFood extends BaseFunctions {

	@AndroidFindBy(xpath = "//*[@resource-id='com.healthifyme.basic:id/iv_add_food_log']")
	private List<MobileElement> addIcon;

	@AndroidFindBy(xpath = "//*[@resource-id='com.healthifyme.basic:id/tv_food_log_meal_type']")
	private List<MobileElement> mealTypes;

	@AndroidFindBy(xpath = "//*[contains(@resource-id,'meal_type') or (contains(@resource-id,'food_name'))]")
	private List<MobileElement> foodItems;

	@AndroidFindBy(id = "com.healthifyme.basic:id/rb_eight")
	private MobileElement rating;

	@AndroidFindBy(id = "com.healthifyme.basic:id/btn_submit")
	private MobileElement ratingSubmit;

	@AndroidFindBy(id = "com.healthifyme.basic:id/cb_nps2")
	private MobileElement feature;

	@AndroidFindBy(id = "com.healthifyme.basic:id/btn_back")
	private MobileElement backToDashboard;

	@AndroidFindBy(xpath = "//*[contains(@text,'Cal Eaten')]")
	private MobileElement calorieEaten;

	public TrackFood(AppiumDriver<MobileElement> driver) {
		super(driver);
		PageFactory.initElements(new AppiumFieldDecorator((AppiumDriver<MobileElement>) driver), this);
	}

	public Map<String, List<String>> getFoodList() {
		Map<String, List<String>> items = new HashMap<String, List<String>>();
		List<String> foodList = new ArrayList<String>();
		String mealtype = "";
		// foodItems = driver.findElements(MobileBy.AndroidUIAutomator("new
		// UiSelector().resourceIdMatches(\".*meal_type|.*food_name\")"));
		for (MobileElement in : foodItems) {
			String food = getText(in);
			if (food.trim().matches("Breakfast|Morning Snack|Lunch|Evening Snack|Dinner")) {
				mealtype = food;
				foodList.clear();
				items.put(mealtype, null);
			} else {
				foodList.add(food);
				items.put(mealtype, new ArrayList<String>(foodList));
			}
		}
		return items;
	}

	public void verifyIfFoodNamesAreDisplayed(MEALTYPE type, Map<String, List<String>> foodList, List<String> items) {
		if (foodList.get(getString(type)).containsAll(items)) {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("TrackedFood")));
			report(LogStatus.PASS, "Tracked food items are displayed");
		} else {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("TrackedFood")));
			report(LogStatus.FAIL, "Tracked food items are not displayed");
		}
	}

	public void handleRatingFlow() {
		if (isDisplayed(rating)) {
			click(rating);
			click(feature);
			click(ratingSubmit);
			click(backToDashboard);
			click(calorieEaten);
		}
	}

	public void selectMealType(MEALTYPE type) {
		if (isDisplayed(addIcon.get(0))) {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("FoodLog")));
			report(LogStatus.PASS, "Food log page is displayed");
			switch (type) {
			case BREAKFAST:
				click(addIcon.get(0));
				break;
			case MORNING_SNACK:
				click(addIcon.get(1));
				break;
			case LUNCH:
				click(addIcon.get(2));
				break;
			case EVENING_SNACK:
				click(addIcon.get(3));
				break;
			case DINNER:
				click(addIcon.get(4));
				break;
			default:
				break;
			}
		} else {
			test.log(LogStatus.INFO,test.addScreenCapture(takeScreenshot("FoodLog")));
			report(LogStatus.FAIL, "Food log page is not displayed");
		}
	}

	public enum MEALTYPE {
		BREAKFAST, MORNING_SNACK, LUNCH, EVENING_SNACK, DINNER
	}

	public String getString(MEALTYPE type) {
		switch (type) {
		case BREAKFAST:
			return "Breakfast";
		case MORNING_SNACK:
			return "Morning Snack";
		case LUNCH:
			return "Lunch";
		case EVENING_SNACK:
			return "Evening Snack";
		case DINNER:
			return "Dinner";
		default:
			return null;
		}
	}
}