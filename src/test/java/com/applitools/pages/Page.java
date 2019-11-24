package com.applitools.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.applitools.driver.DriverManager;

public class Page {

	protected WebDriver driver;
	WebDriverWait wait;

	Page() {
		driver = DriverManager.getDriver().driver;
		wait = new WebDriverWait(driver, 5000);
	}

}
