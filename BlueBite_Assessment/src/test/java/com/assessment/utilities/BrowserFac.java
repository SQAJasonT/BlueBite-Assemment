package com.assessment.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserFac {
	//this class sets up the web browser with an if/else statement depending on the choice of browser that will be used

	public static WebDriver startApplication(WebDriver driver, String browserName, String appURL)
	{
		//have chrome here as well in order to carry out cross browser testing if needed
		if(browserName.equals("Chrome")) 
		{
			System.setProperty("webdriver.chrome.driver", "./Driver/chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}
		else if (browserName.equals("Firefox"))
		{
			System.setProperty("webdriver.gecko.driver", "./Driver/geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		}
		else
		{
			System.out.println("We do not support this browser.");
		}

		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(appURL);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;		
	}

	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}
}
