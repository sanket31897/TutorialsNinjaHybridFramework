package com.tutorialsninja.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.SearchPage;

public class SearchTest extends BaseClass {

	public WebDriver driver;
	SearchPage searchPage;

	public  SearchTest() {
		super();
	}

	@BeforeMethod
	public void setUp() {
		driver= initializeBrowserandOpenAppUrl(prop.getProperty("browser"));

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)

	public void verifySearchWithValidProduct() {

		HomePage homepage = new HomePage(driver);
		homepage.enterProductIntoSearchBox(dataProp.getProperty("validProduct"));
		searchPage = homepage.clickOnSearchButton();
		Assert.assertTrue(searchPage.displayStatusOfHPProduct(), "HP Product is not displayed after the search");

	}

	@Test(priority=2)

	public void verifySearchWithInvalidProduct() {
		HomePage homepage = new HomePage(driver);
		homepage.enterProductIntoSearchBox(dataProp.getProperty("invalidProduct"));
		searchPage= homepage.clickOnSearchButton();
		String actualSearchMessage = searchPage.retriveNoProductMessage();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResults"),"No Product matches message is not displayed.");

	}

	@Test(priority=3, dependsOnMethods= {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})

	public void verifySearchWithoutAnyProduct() {
		HomePage homepage = new HomePage(driver);
		searchPage= homepage.clickOnSearchButton();
		String actualSearchMessage = searchPage.retriveNoProductMessage();
		Assert.assertEquals(actualSearchMessage, dataProp.getProperty("noProductTextInSearchResults"),"No Product matches message is not displayed.");

	}
}
