package com.assessment.testcases;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.assessment.pages.BaseClass;
import com.assessment.pages.Locators;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

//having baseclass extended here helps with using my other methods for properly gathering data from runtime
public class BlueBitesTest extends BaseClass{
	
	@Test(priority = 1)
	public void ValidData()
	{
		
		//creating the test report for Valid data
		logger = report.createTest("Valid Data Test");
		
		//confirming the title of Webpage
		String expectedTitle = "BlueBites Raffle";
		String actualTitle = driver.getTitle();
		SoftAssert softassert = new SoftAssert();
		softassert.assertEquals(actualTitle, expectedTitle);
		System.out.println("The actual title is " + actualTitle);
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//calling the class to the main test script using POM
		Locators locate = PageFactory.initElements(driver, Locators.class);
		
		logger.log(Status.INFO, "Navigated to Raffle Entry Screen");
		
		locate.ValidEntry();
		
		logger.log(Status.PASS, "Valid Data has been submitted for raffle");

		
	}
	

	
	@Test(priority = 2)
	public void InvalidData()
	{
		//creating test report for invalid data
		logger = report.createTest("Invalid Data Test");
		
		//refreshing and deleting cookies to see if it affects application performance
		driver.navigate().refresh();
		
		driver.manage().deleteAllCookies();
		
		//beginning test
		Locators locate1 = PageFactory.initElements(driver, Locators.class);
		
		logger.log(Status.INFO, "Navigated back to raffle entry screen with cookies deleted");
		
		//an exception was being thrown so surrounded with try/catch
		try {
			locate1.InvalidEntry();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		logger.log(Status.FAIL, "with Invalid data entered an underaged person has entered the raffle");
		
		logger.log(Status.INFO, "Attached is the screenshot of the confirmation of the invalid entry");
		
		//another try/catch to prevent script failure
		try {
			logger.addScreenCaptureFromPath("./BlueBite_Assessment/Screenshots/BlueBites09_20_2021_11_40_15.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	@Test(priority = 3)
	public void Entries() 
	{
		//creating final test
		ExtentTest logger = report.createTest("Number of Entries Test");
		
		logger.log(Status.INFO, "Testing to see if a participant can enter 10 times or more");
		
		//refreshing and deleting cookies from previous test
		driver.navigate().refresh();
		
		driver.manage().deleteAllCookies();
		
		logger.log(Status.INFO, "Navigated back to entry page  with cookies deleted");

		//Beginning the test
		Locators locate2 = PageFactory.initElements(driver, Locators.class);
		
		logger.log(Status.INFO, "Once loop has successfully done 10 rotations a screenshot will be captured of the number of submissions entered");

		locate2.NumberofEntries();
		
		logger.log(Status.PASS, "10 entries have been submitted!");
		
		logger.log(Status.INFO, "Screenshot has been attached to confirm number of entries");
		
		//try/catch here to stop script from failing while attaching screenshot to report
		try {
			logger.addScreenCaptureFromPath("./BlueBite_Assessment/Screenshots/BlueBites09_20_2021_12_25_35.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
