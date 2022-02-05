package com.BasePageModel;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.ClassModel.LoginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPageModel extends LoginPage {
	WebDriver driver;
	ExtentReports report;
	ExtentTest testCase;
	long data=startTime();
	
	@Test(priority=2,dependsOnMethods="lunchBrowser",enabled=true)
	public void registerPage() {
		testCase=report.createTest("To check the Registration process in the register link");
				
		
		
		
	}
	
	
	
	
	
	
	
	@Test(priority=1,enabled=true,alwaysRun=true, dependsOnMethods="startTime")
	public void checkDetails() {
		testCase= report.createTest("To check the correct Title");
		testCase.log(Status.INFO, "To check the Details are correct in the order");
		testCase.log(Status.PASS, "Opening the Home page");
		PageFactory.initElements(driver, "LoginPage");
		testCase.log(Status.INFO, "Listing the Number of URL in the home page /n");
		List<WebElement> linkAll = driver.findElements(By.tagName("a"));
		int size = linkAll.size();
		for (WebElement links : linkAll) {
			String attribute = links.getAttribute("href");
			testCase.log(Status.PASS, attribute);
		}
		List<WebElement> imageCount = driver.findElements(By.tagName("img"));
		int size2 = imageCount.size();
		for (WebElement images : imageCount) {
			String src = images.getAttribute("src");
			testCase.log(Status.PASS, "The Number of images in the Home Page is : "+src);
		}
	}
	@BeforeTest
	public long startTime() {
		testCase=report.createTest("Calculating the Starting Time");
		long startTime = System.currentTimeMillis();
		testCase.log(Status.PASS, "The Start Time for the application is : "+startTime);
		return startTime;
	
	}
	@AfterTest(dependsOnMethods="endBrowser")
	public void endTime() {
		long endTime = System.currentTimeMillis();
		testCase = report.createTest("The ending time of the applicaton is :"+endTime);
		long totalTime=endTime-data;
		testCase.log(Status.INFO, "The Total Run time is : "+totalTime);
	}
	
	@BeforeSuite
	public void lunchBrowser() {
		testCase=report.createTest("Lunching the Browser and Entering the URL");
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--start-maximized");
		driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://adactinhotelapp.com/");
		testCase.log(Status.PASS, "The Browser is launched in the Chrome Driver");
	}

	@AfterSuite
	public void endBrowser() {
		driver.quit();
	}
}
