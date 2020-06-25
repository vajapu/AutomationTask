package com.HealthyfiMe.factory;

public class DriverManagerFactory {
	public static DriverManager getDriverManager(DriverType type) {
		DriverManager driverManager = null;
		switch (type) {
		case Android:
			driverManager=new AndroidDriverManager();
			break;
		default:
			break;
		}
		return driverManager;
	}
}
