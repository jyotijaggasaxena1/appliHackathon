package com.applitools.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverClass {

	public WebDriver driver;

	public WebDriver initializeDriver() {
		// Add logic here for cross browser testing.
		String pwd = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", pwd + "//src//test//resources//driver//chromedriver.exe");
		driver = new ChromeDriver();
		return driver;
	}

}
