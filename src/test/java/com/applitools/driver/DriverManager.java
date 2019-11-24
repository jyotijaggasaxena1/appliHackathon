package com.applitools.driver;

public class DriverManager {

	private static ThreadLocal<DriverClass> driver = new ThreadLocal<DriverClass>();

	public static DriverClass getDriver() {
		return driver.get();
	}

	public static void setDriver(DriverClass driverC) {
		driver.set(driverC);
	}
}
