package com.applitools.pages;

import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.applitools.utils.ImageUtil;
import com.applitools.utils.Transactions;

public class DemoApp extends Page {

	By amountLocator = By.id("amount");
	By transactionTableLocator = By.id("transactionsTable");
	By compareExpensesLocator = By.id("showExpensesChart");
	By canvasLocator = By.id("canvas");
	By flashSale1 = By.id("flashSale");
	By flashSale2 = By.id("flashSale2");
	By showNextYrData = By.id("addDataset");

	List<Transactions> currentTransactions;

	public boolean isHeaderVisible() {
		wait.until(ExpectedConditions.presenceOfElementLocated(compareExpensesLocator));
		return driver.findElement(compareExpensesLocator).isDisplayed();
	}

	public void clickAmountHeader() {
		cacheTransactions();
		driver.findElement(amountLocator).click();
	}

	private void cacheTransactions() {
		currentTransactions = populateTransactionTable();
	}

	public List<Transactions> populateTransactionTable() {
		List<Transactions> transactions = new ArrayList<Transactions>();
		List<WebElement> tableRows = driver.findElements(By.xpath("//table[@id='transactionsTable']/tbody/tr"));
		for (WebElement tableRow : tableRows) {
			List<WebElement> rowValues = tableRow.findElements(By.xpath("//td"));
			String status = rowValues.get(0).getText();
			String transactionDate = rowValues.get(1).getText();
			String description = rowValues.get(2).getText();
			String category = rowValues.get(3).getText();
			String amountStr = rowValues.get(4).getText();
			amountStr = amountStr.replaceAll("[+, USD]", "");
			float amount = Float.parseFloat(amountStr);
			transactions.add(new Transactions.Builder(description).withAmount(amount).withDate(transactionDate)
					.withCategory(category).withStatus(status).build());
		}
		return transactions;
	}

	public void validateTableRows() {
		List<Transactions> postClickTransactions = populateTransactionTable();
		assertTrue(currentTransactions.retainAll(postClickTransactions));
	}

	public boolean compareImages(String reference) {
		File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(source, new File("./Screenshots/testImage.png"));
			String referenceFile = "./Screenshots/" + reference + ".png";
			System.out.println("Diff percentage:" + ImageUtil.compareImages(source, new File(referenceFile)));
			return (ImageUtil.compareImages(source, new File(referenceFile)) < 0.2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	public void clickCompareExpenses() {
		driver.findElement(compareExpensesLocator).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(canvasLocator));

	}

	public void validateGif() {
		assertTrue(driver.findElement(flashSale1).isDisplayed());
		assertTrue(driver.findElement(flashSale2).isDisplayed());
	}

	public void clickShowNextYrData() {
		driver.findElement(showNextYrData).click();
	}

	public By getCanvasLocator() {
		return canvasLocator;
	}

	public By getTransactionTableLocator() {
		return transactionTableLocator;
	}

}
