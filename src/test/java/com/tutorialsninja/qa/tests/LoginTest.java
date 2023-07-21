package com.tutorialsninja.qa.tests;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pageobjects.AccountPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends BaseClass {

	public WebDriver driver;
	LoginPage loginpage;
	

	public  LoginTest() {
		super();
	}

	@BeforeMethod
	public void setupMethod() {

		driver = initializeBrowserandOpenAppUrl(prop.getProperty("browser"));
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccount();
		loginpage = hp.selectLoginOption();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority= 1, dataProvider="validCredSupplier")
	public void verifyLoginWithValidCred(String email, String password) {

        
		loginpage.enterEmailAddress(email);
		loginpage.enterPassword(password);
		AccountPage accountPage = loginpage.clickOnLoginButton();

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccInfoOption(),"Edit your account information option is not displayed");

	}

	@DataProvider(name="validCredSupplier")
	public Object[][] supplyTestData() {
		Object [][] data= Utilities.getTestDataFormExcel("Login");
     return data;
	}


	@Test(priority= 2)

	public void verifyLoginWithInvalidCred() {
		
		
		loginpage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();

		String actualWarningMessage = loginpage.retriveEmailPasswordNotmatchWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warining msg not displayed");

	}

	@Test(priority= 3)
	public void verifyLnwithInvalidEmailandValidPassword() {

		
		loginpage.enterEmailAddress(Utilities.generateEmailwithTimeStamp());
		loginpage.enterPassword(prop.getProperty("validPassword"));
		loginpage.clickOnLoginButton();
		
		String actualWarningMessage =  loginpage.retriveEmailPasswordNotmatchWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warining msg not displayed");


	}
	@Test(priority= 4)
	public void verifyLoginwithValidEmailandInvalidPassword() {
		
		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
		loginpage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginpage.clickOnLoginButton();
		
		
		String actualWarningMessage = loginpage.retriveEmailPasswordNotmatchWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warining msg not displayed");


	}
	@Test(priority= 5)
	public void verifyLoginwithoutProvidingCred() {

		
		loginpage.clickOnLoginButton();
		
		String actualWarningMessage = loginpage.retriveEmailPasswordNotmatchWarningMessage();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNotMatchWarning");
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),"Expected warining msg not displayed");


	}




}
