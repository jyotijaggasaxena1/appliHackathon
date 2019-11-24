package com.applitools.tests;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.applitools.BaseTest;
import com.applitools.driver.DriverManager;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.TestResultsSummary;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.pages.DemoApp;
import com.applitools.pages.LoginPage;
import com.applitools.utils.PropUtil;

public class VisualAITests extends BaseTest {
	private EyesRunner runner;
	private Eyes eyes;
	private static BatchInfo batch;
	private WebDriver driver;

	LoginPage loginPage;
	DemoApp demoApp;
	PropUtil props;

	String appName = "Demo App";
	String batchName = "Hackathon";
	private String apiKey = System.getenv("MY_APPLITOOLS_API_KEY");
	private Configuration appliConfig;

	@BeforeClass(alwaysRun = true)
	@Parameters({ "viewportWidth", "viewportHeight" })
	public void setup(int viewPortWidth, int viewPortHeight) throws IOException {
		props = new PropUtil();
		runner = new ClassicRunner();
		appliConfig = new Configuration();
		batch = new BatchInfo(batchName);
		appliConfig.setViewportSize(new RectangleSize(viewPortWidth, viewPortHeight)).setApiKey(apiKey)
				.setAppName(appName);

		driver = DriverManager.getDriver().driver;
		driver.navigate().to(props.getURL());
		loginPage = new LoginPage();

	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		eyes = new Eyes(runner);
		appliConfig.setBatch(batch);
		eyes.setConfiguration(appliConfig);
		eyes.setBatch(batch);
	}

	@Test(priority = 1)
	public void loginPageUITest() {
		Configuration testConfig = eyes.getConfiguration();
		testConfig.setTestName("Hackathon - Test loginPage UI");
		eyes.setConfiguration(testConfig);
		eyes.open(driver);
		eyes.checkWindow("Login Window");
		eyes.closeAsync();
	}

	@Test(priority = 2)
	public void testInvalidUserName() {
		Configuration testConfig = eyes.getConfiguration();
		testConfig.setTestName("Hackathon - Test invalid data- username");
		eyes.setConfiguration(testConfig);

		eyes.open(driver);
		loginPage.typeUserName("invalid");
		loginPage.submit();
		eyes.checkWindow();
		eyes.close();
	}

	@Test(priority = 2)
	public void testInvalidPassword() {
		Configuration testConfig = eyes.getConfiguration();
		testConfig.setTestName("Hackathon - Test invalid data - password");
		eyes.setConfiguration(testConfig);

		eyes.open(driver);
		loginPage.typeUserName(props.getUserName());
		loginPage.typePassword("invalid");
		loginPage.submit();
		eyes.checkWindow();
		eyes.close();
	}

	@Test(priority = 2)
	public void testEmptyUsername() {
		Configuration testConfig = eyes.getConfiguration();
		testConfig.setTestName("Hackathon - Test invalid data - blank username");
		eyes.setConfiguration(testConfig);

		eyes.open(driver);
		loginPage.typeUserName("");
		loginPage.typePassword("invalid");
		loginPage.submit();
		eyes.checkWindow();
		eyes.close();
	}

	@Test(priority = 2)
	public void testEmptyPassword() {
		Configuration testConfig = eyes.getConfiguration();
		testConfig.setTestName("Hackathon - Test invalid data-empty password");
		eyes.setConfiguration(testConfig);

		eyes.open(driver);
		loginPage.typeUserName("invalid");
		loginPage.submit();
		eyes.checkWindow();
		eyes.close();
	}

	@Test(priority = 3)
	public void loginAndSortTableByAmount() {
		Configuration testConfig = eyes.getConfiguration();
		testConfig.setTestName("Hackathon - Test transactions table");
		eyes.setConfiguration(testConfig);
		demoApp = new DemoApp();
		login();
		assertTrue(demoApp.isHeaderVisible());
		eyes.open(driver);
		eyes.setScrollToRegion(true);
		eyes.checkRegion(demoApp.getTransactionTableLocator());
		demoApp.clickAmountHeader();
		eyes.checkRegion(demoApp.getTransactionTableLocator());
		eyes.closeAsync();
	}

	@Test(priority = 4)
	public void testExpenseChart() {
		Configuration testConfig = eyes.getConfiguration();
		testConfig.setTestName("Hackathon - Test expense chart");
		eyes.setConfiguration(testConfig);

		demoApp.clickCompareExpenses();
		eyes.open(driver);
		eyes.checkRegion(demoApp.getCanvasLocator());
		demoApp.clickShowNextYrData();
		eyes.checkRegion(demoApp.getCanvasLocator());
		eyes.closeAsync();
	}

	@Test(priority = 5)
	public void testDynamicContent() {
		Configuration testConfig = eyes.getConfiguration();
		testConfig.setTestName("Hackathon - Test dynamic content");
		eyes.setConfiguration(testConfig);

		eyes.open(driver);
		driver.get(props.getURLwithAds());
		eyes.checkWindow();
		eyes.close();
	}

	@AfterClass(alwaysRun = true)
	public void afterAllTests() {
		driver.quit();
		eyes.abortIfNotClosed();
		TestResultsSummary allTestResults = runner.getAllTestResults(false);
		System.out.println(allTestResults);
	}

	private void login() {
		if (!demoApp.isHeaderVisible()) {
			loginPage.typeUserName(props.getUserName());
			loginPage.typePassword(props.getPassword());
			loginPage.submit();

		}
	}

}
