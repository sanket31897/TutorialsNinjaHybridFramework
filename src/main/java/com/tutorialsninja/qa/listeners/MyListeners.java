package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class MyListeners implements ITestListener{
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName; 
	
public void onStart(ITestContext context) {
		
		extentReport = ExtentReporter.generateExtentReport();
		
	}

	public void onTestStart(ITestResult result) {
		testName= result.getName();
		extentTest = extentReport.createTest(testName);
		extentTest.log(Status.INFO, testName+"Started executing");
		
	}

	public void onTestSuccess(ITestResult result) {
		
		extentTest.log(Status.PASS, testName+"Test successfully executed.");
		
		
	}

	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
		
			e.printStackTrace();
		} catch (SecurityException e) {
			
			e.printStackTrace();
		}
		String destScreenshotPath = Utilities.captureScreenshot(driver, testName);
		extentTest.addScreenCaptureFromPath(destScreenshotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL,testName+"Test got failed.");
		System.out.println("Screenshot taken.");
	}

	public void onTestSkipped(ITestResult result) {
		
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP,testName+"Test got skipped.");
		
		
	}

	

	public void onFinish(ITestContext context) {
		
		extentReport.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html";
		File extentReport = new File(pathOfExtentReport);
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
	
	

}
