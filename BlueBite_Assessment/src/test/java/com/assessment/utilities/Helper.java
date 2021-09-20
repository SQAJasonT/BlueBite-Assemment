package com.assessment.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Helper {
	
	//screenshot capturing function that puts them all into a folder properly labled format 
	public static String captureScreenshot(WebDriver driver)
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		String screenshotPath=System.getProperty("user.dir")+"/Screenshots/BlueBites"+ getCurrentDateTime() + ".png";
		
		try
		{FileHandler.copy(src, new File(screenshotPath));
		
		System.out.println("Screenshot captured Successfully");
		
		} catch (IOException e) 
		{
			System.out.println("Unable to capture screenshot");
		}
		return screenshotPath;
	}
	
	//labeling each screenshot thats taken in a simpledateformat
	public static String getCurrentDateTime()
	{
		DateFormat customformat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		
		Date currentDate=new Date();
		
		return customformat.format(currentDate);
	}
}
