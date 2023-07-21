package com.tutorialsninja.qa.tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.BaseClass;
import com.tutorialsninja.qa.pageobjects.AccountSuccessPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends BaseClass{
	public WebDriver driver;
	RegisterPage registerPage ;
	 AccountSuccessPage asp;
	public  RegisterTest() {
		super();
	}

	@BeforeMethod
	public void setUpMethod() {

		driver= initializeBrowserandOpenAppUrl(prop.getProperty("browser"));
		HomePage hp = new HomePage(driver);
		hp.clickOnMyAccount();
	    registerPage = hp.selectRegisterOption();
	
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority=1)

	public void verifyRegisterAccWithMandFields() {
        
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(Utilities.generateEmailwithTimeStamp());
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectPrivacyPolicy();
       AccountSuccessPage asp = registerPage.selectContinueOption();
		String actualSuccessHeading =asp.retriveSuccessHeadingMessage() ;
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success page is not displayed");




	}
	@Test(priority=2)

	public void verifyRegisteringaccwithAllfields() {
		
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(Utilities.generateEmailwithTimeStamp());
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectNewsLetterSubsribeOption();
        registerPage.selectPrivacyPolicy();
        asp= registerPage.selectContinueOption();
		String actualSuccessHeading =asp.retriveSuccessHeadingMessage() ;
		Assert.assertEquals(actualSuccessHeading, dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account success page is not displayed");

	}

	@Test(priority=3)
	public void verifyRegisteringAccwithExistingEmailAddress() {

		
        registerPage.enterFirstName(dataProp.getProperty("firstName"));
        registerPage.enterLastName(dataProp.getProperty("lastName"));
        registerPage.enterEmail(prop.getProperty("validEmail"));
        registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
        registerPage.enterPassword(prop.getProperty("validPassword"));
        registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
        registerPage.selectNewsLetterSubsribeOption();
        registerPage.selectPrivacyPolicy();
        registerPage.selectContinueOption();
		String actualWarning =registerPage.retriveduplicateEmailWarningMessage() ;
		Assert.assertEquals(actualWarning, dataProp.getProperty("duplicateEmailWarning"),"Account success page is not displayed");


	}
	@Test(priority=4)

	public void verifyRegisteringAccwithoutfillingDetails() {
		registerPage.selectContinueOption();
		

		String actualPrivacyPolicyWarining = registerPage.retrivePrivacyPolicyWarningMessage();
		Assert.assertTrue(actualPrivacyPolicyWarining.contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Policy Message is not displayed");

		String actualFirstNameWarning = registerPage.retriveFirstNameWarningMessage();
		Assert.assertTrue(actualFirstNameWarning.contains(dataProp.getProperty("firstNameWarning")),"First Name Warning Message is not displayed");

		String actualLastNameWarning = registerPage.retriveLastNameWarningMessage();
		Assert.assertTrue(actualLastNameWarning.contains(dataProp.getProperty("lastNameWarning")),"Last Name Warning Message is not displayed");

		String actualEmailWarning = registerPage.retriveEmailWarningMessage();
		Assert.assertTrue(actualEmailWarning.contains(dataProp.getProperty("emailWarning")),"Email Warning Message is not displayed");

		String actualTelephoneWarning = registerPage.retriveTelephoneWarningMessage();
		Assert.assertTrue(actualTelephoneWarning.contains(dataProp.getProperty("telephoneWarning")),"Telephone Warning Message is not displayed");

		String actualPasswordWarning = registerPage.retrivePasswordWarningMessage();
		Assert.assertTrue(actualPasswordWarning.contains(dataProp.getProperty("passwordWarning")),"Password Warning Message is not displayed");


	}

}
