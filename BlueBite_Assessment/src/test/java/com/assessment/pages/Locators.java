package com.assessment.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.assessment.utilities.Helper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


//this class is for webelement location and to reduce the amount of hard coding within  the main test scripts
public class Locators {

	WebDriver driver;
	int i;
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("./BlueBite_Assessment/Reports.html");
	ExtentReports extent = new ExtentReports();

	//have the wed elements located and stored in one place to be used throughout script
	public Locators(WebDriver ldriver)
	{
		this.driver = ldriver;
	}

	//have all elements within webpage located to be called when i need them
	@FindBy(xpath = "//input[@name='name']") WebElement name;
	@FindBy(xpath = "//input[@name='email']") WebElement email;
	@FindBy(xpath = "//input[@name='age']") WebElement age;
	@FindBy(xpath = "//input[@name='reason']") WebElement reason;
	@FindBy(xpath = "//button[@type='button']") WebElement submit;

	public void ValidEntry()
	{

		//implementing a explicit wait here in order to have the web page fully loaded and ready 
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='name']")));
		
		//entering the valid data
		name.sendKeys("Jordan Smith");
		email.sendKeys("J.Smith@hotmail.com");
		age.sendKeys("20");
		reason.sendKeys("I am a sneaker collector and these would complet my rare set");
		submit.click();
		
		//another wait to confirm the valid data has been entered successfully
		WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Submission')]")));
		
		//once the status has been confirmed a screenshot will be taken of the entry for verification 
		boolean status1 = confirmation.isDisplayed();

		if (status1)
		{
			Helper.captureScreenshot(driver);
			System.out.println("Confirmation Succsessful! and Screenshot has been Captured");
		}
		else 
		{
			System.out.println("Unable to capture Screenshot!");
		}

		

	}

	public void InvalidEntry() throws Exception
	{
		//using Invalid data to see if i will be denied entry into raffle
		name.sendKeys("Tim Holmes");
		email.sendKeys("T.Holmes@gmail.com");
		age.sendKeys("17");
		reason.sendKeys(" ");
		submit.click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		//capturing a shot if the invalid data that has been used
		Helper.captureScreenshot(driver);

		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement confirmation = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Submission')]")));
		
		//confirming that the status is being displayed and getting a screenshot of it
		boolean status = confirmation.isDisplayed();

		if (status)
		{
			Helper.captureScreenshot(driver);
			System.out.println("Particpant still entered even with being underaged");
		}
		else
		{
			System.out.println("Screenshot not captured!");
		}

		

		

	}


	public void NumberofEntries()
	{
		//setting the amount of times I want the data to be looped
		int numOfTimesToLoop = 11;

		//created said loop to have the same participant enter up to ten times
		for (int i = 0; i < numOfTimesToLoop; i++)
		{
			name.sendKeys("Abigail Marston");
			email.sendKeys("Abigail_Marston@yahoo.com");
			age.sendKeys("25");
			submit.click();

			WebDriverWait wait = new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'Submission')]")));
			driver.navigate().refresh();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//breaking it at ten meaning the amount of entries has hit double digits
			if (i == 10)
			{
				break;
			}
		}
		
		//entering data one ome time for the final entry and screenshot confirmation of number of entries
		name.sendKeys("Abigail Marston");
		email.sendKeys("Abigail_Marston@yahoo.com");
		age.sendKeys("25");
		submit.click();
		
		//making sure that the proper message is visible and ready to captured
		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(), 'email')]")));

		Helper.captureScreenshot(driver);
		System.out.println("Number of entries have reached double digits.");

		System.out.println("Screenshot Captured!");

	}






}
