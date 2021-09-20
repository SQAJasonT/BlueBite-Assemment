package com.assessment.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.assessment.utilities.BrowserFac;
import com.assessment.utilities.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class BaseClass {
	
	public WebDriver driver;
	public static ExtentReports report;
	public ExtentTest logger;
	
	//setting up my reporting feature
	@BeforeSuite
	public static void setUpSuite()
	{
		ExtentHtmlReporter extent = new ExtentHtmlReporter(new File(System.getProperty("user.dir")+ "/BlueBite_Assessment/"+ Helper.getCurrentDateTime() + "BlueBite_Assessment.html"));
		report=new ExtentReports();
		report.attachReporter(extent);
	}
	
	//starting up the application
	@BeforeClass
	public void setup()
	{
		driver = BrowserFac.startApplication(driver, "Firefox", "https://blue-bite-dev-3.bluebite.io/04425f7c-4fdd-47f6-85b3-b800d12bb9ca");
	}
	
	//shutting down the appliciation once all tests have been completed
	@AfterClass
	public void tearDown()
	{
		BrowserFac.quitBrowser(driver);
	}
	
	//after every run of the methods in the script either pass or fail a screenshot will be taken and reports will constantly updated when changes are made
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.FAILURE)
		{
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());
		}
		else if(result.getStatus()== ITestResult.SUCCESS)
		{
			logger.pass("Test Passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreenshot(driver)).build());

		}
		
		report.flush();
	}

	

}
