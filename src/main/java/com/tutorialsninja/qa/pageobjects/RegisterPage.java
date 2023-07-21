package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement confirmPasswordField;
	
	@FindBy(name="agree")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@value='Continue']")
	private WebElement continueOption;
	
	@FindBy(xpath="//input[@name='newsletter'][@value='1']")
	private WebElement newsLetterSubscribeOption;
	
	@FindBy(xpath="//div[contains(@class,'alert-dismissible')]")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath="//div[contains(@class,'alert alert-danger alert-dismissible')]")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver) {
		this.driver= driver;
		PageFactory.initElements(driver,this);
	}
	
	public void enterFirstName(String fisrtNameText) {
		firstNameField.sendKeys(fisrtNameText);
	}
	
	public void enterLastName(String lastNameText) {
		lastNameField.sendKeys(lastNameText);
	}
	
	public void enterEmail(String emailText) {
		emailField.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneText) {
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText) {
		passwordField.sendKeys(passwordText);
	}

	public void enterConfirmPassword(String confirmPasswordFieldText) {
		confirmPasswordField.sendKeys(confirmPasswordFieldText);
	}
	
	public void selectPrivacyPolicy() {
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage selectContinueOption() {
		continueOption.click();
		return new AccountSuccessPage(driver);
	}
	
	public void selectNewsLetterSubsribeOption() {
		newsLetterSubscribeOption.click();
	}
	
	public String retriveduplicateEmailWarningMessage() {
		String duplicateEmailWarningText = duplicateEmailWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrivePrivacyPolicyWarningMessage() {
		String privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retriveFirstNameWarningMessage() {
		String firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retriveLastNameWarningMessage() {
		String lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retriveEmailWarningMessage() {
		String emialWarningText = emailWarning.getText();
		return emialWarningText;
	}
	
	public String retriveTelephoneWarningMessage() {
		String telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}
	

	public String retrivePasswordWarningMessage() {
		String passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
}
