package com.applitools;

import java.util.logging.Logger;

import org.testng.TestRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.applitools.driver.DriverClass;
import com.applitools.driver.DriverFactory;
import com.applitools.driver.DriverManager;

public class BaseTest {

	private static final Logger LOGGER = Logger.getLogger(TestRunner.class.getName());

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		DriverClass driverC = DriverFactory.createInstance();
		driverC.initializeDriver();
		DriverManager.setDriver(driverC);

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		DriverManager.getDriver().driver.quit();
	}
}
