package com.applitools.pages;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginPage extends Page {
	By userNameLabelLocator = By.xpath("//label[text()='Username']");
	By passwordLabelLocator = By.xpath("//label[text()='Password']");
	By userNameLocator = By.id("username");
	By passwordLocator = By.id("password");
	By loginButtonLocator = By.id("log-in");
	By userNameIcon = By.xpath("//div[contains(@class,'os-icon-user-male-circle')]");
	By passwordIcon = By.xpath("//div[contains(@class,'os-icon-fingerprint')]");
	By rememberMeIcon = By.cssSelector("form-check-input");
	By twitterIcon = By.xpath("//img[contains(@src,'twitter')]");
	By linkedInIcon = By.xpath("//img[contains(@src,'linkedin')]");
	By facebookIcon = By.xpath("//img[contains(@src,'facebook')]");
	By loginFormHeader = By.xpath("//h4[contains(text(),'Login Form')]");

	public void typeUserName(String userName) {
		WebElement userNameElement = driver.findElement(userNameLocator);
		userNameElement.clear();
		userNameElement.sendKeys(userName);
	}

	public void typePassword(String password) {
		WebElement passwordElement = driver.findElement(passwordLocator);
		passwordElement.clear();
		passwordElement.sendKeys(password);
	}

	public void submit() {
		driver.findElement(loginButtonLocator).click();
	}

	public boolean isUserNameIconVisible() {
		return driver.findElement(userNameIcon).isDisplayed();
	}

	public boolean isPasswordIconVisible() {
		return driver.findElement(passwordIcon).isDisplayed();
	}

	public boolean isRememberMeVisible() {
		return driver.findElement(userNameIcon).isDisplayed();
	}

	public boolean isFacebookIconVisible() {
		return driver.findElement(facebookIcon).isDisplayed();
	}

	public boolean isLinkedinIconVisible() {
		return driver.findElement(linkedInIcon).isDisplayed();
	}

	public boolean isTwitterIconVisible() {
		return driver.findElement(twitterIcon).isDisplayed();
	}

	public boolean isLoginFormHeaderVisible() {
		return driver.findElement(loginFormHeader).isDisplayed();
	}

	public boolean isUserNameLabelVisible() {
		return driver.findElement(userNameLabelLocator).isDisplayed();
	}

	public boolean isPasswordLabelVisible() {
		return driver.findElement(passwordLabelLocator).isDisplayed();
	}

	public DemoApp login(String userName, String password) {
		typeUserName(userName);
		typePassword(password);
		submit();
		DemoApp demoApp = new DemoApp();
		assertTrue(demoApp.isHeaderVisible());
		return demoApp;
	}

}
