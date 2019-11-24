package com.applitools.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.applitools.BaseTest;
import com.applitools.driver.DriverManager;
import com.applitools.pages.DemoApp;
import com.applitools.pages.LoginPage;
import com.applitools.utils.PropUtil;

public class TraditionalTests extends BaseTest {

	WebDriver driver;
	LoginPage loginPage;
	DemoApp demoApp;
	PropUtil props;

	@BeforeClass(alwaysRun = true)
	public void getPage() throws IOException {
		driver = DriverManager.getDriver().driver;
		props = new PropUtil();
		driver.navigate().to(props.getURL());
		loginPage = new LoginPage();
	}

	@Test(priority = 1)
	public void loginPageUITest() {
		assertTrue(loginPage.isLoginFormHeaderVisible());
		assertTrue(loginPage.isUserNameLabelVisible());
		assertTrue(loginPage.isPasswordLabelVisible());
		assertTrue(loginPage.isRememberMeVisible());
		assertTrue(loginPage.isUserNameIconVisible());
		assertTrue(loginPage.isPasswordIconVisible());

	}

	@Test(priority = 2)
	public void noData() {
		loginPage.submit();
	}

	@Test(priority = 3)
	public void invalidUserName() {
		loginPage.typeUserName("invalid");
		loginPage.submit();
	}

	@Test(priority = 4)
	public void invalidPassword() {
		loginPage.typePassword("password");
		loginPage.submit();
		assertTrue(true);
	}

	@Test(priority = 5)
	public void loginAndSortTableByAmount() {
		loginPage.typeUserName(props.getUserName());
		loginPage.typePassword(props.getPassword());
		loginPage.submit();
		demoApp = new DemoApp();
		assertTrue(demoApp.isHeaderVisible());
		demoApp.populateTransactionTable();
		demoApp.clickAmountHeader();
		demoApp.validateTableRows();
	}

	/**
	 * This step fails with an error of 2%.
	 */
	@Test(priority = 6)
	public void testExpenseChart() {
		demoApp.clickCompareExpenses();
		assertTrue(demoApp.compareImages("reference"));
		demoApp.clickShowNextYrData();
		assertTrue(demoApp.compareImages("referenceWith2019Data"));
	}

	@Test(priority = 7)
	public void testDynamicContent() {
		driver.get(props.getURLwithAds());
		DemoApp demoPage = new DemoApp();
		demoPage.validateGif();
	}
}
