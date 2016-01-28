package test1;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccessTokens extends seleniumTest{

	
	
	public WebDriver driver;
	
	
//Constructor in which the tests run
	
public AccessTokens(WebDriver driverCurrent, WriteToFile data){
    
	driver = driverCurrent;
	
 
    int choice;
    boolean pass;
    String test;
    
    //Setup users for tests
    Users tester = new Users(driver, "Testing Provider");
    Users admin = new Users(driver, "Admin");
    Users testPatient = new Users(driver, "Testing Patient");
    
    //Set current date and time
    CalendarDate now = new CalendarDate();
    now.setDate(driver);
    
    
    printTitle("Scheduled Actions", data);
	
/*********************************************** 
*		
*		Login to QA
*
*
**********************************************/
    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://newqa.viinetwork.net/logout");
	shortPause();
	resizeWindow(driver);


/*********************************************** 
*		
*		Manage Access Tokens Page - Check Content
*
*
**********************************************
	
	test = "Check content";
	
	try{
	login(driver, admin.getUsername(), admin.getPasswd());
	dashboardVisible(driver);
	driver.get("https://newqa.viinetwork.net/admin/accesstokenbatches");
	tableVisible(driver);
	PageAssertions ATokens = new PageAssertions(driver);
	String [] subnav = {"Manage Access Token Batches", "Add Access Token Batch"};
	
				if(ATokens.checkPageTitle("Manage Access Tokens") &&
				!ATokens.checkPageSubtitle("") && 
				ATokens.checkPageDescription("View and manage your site's access tokens.")&&
				ATokens.checkSubnav(subnav))
				{
					PageText ATokensText = new PageText("Manage Access Tokens");
					if(ATokensText.checkPageText(ATokens.getPageText()))
					{
					navigateSubNav(driver, "Add Access Token Batch");
					tableVisible(driver);
					PageAssertions ATokenBatch = new PageAssertions(driver);
						if(ATokenBatch.checkPageTitle("Access Token Batch")&&
								!ATokenBatch.checkPageSubtitle("")&&
								ATokenBatch.checkPageDescription("Create an access token batch.")&&
								ATokenBatch.checkSubnav(subnav))
						{
										
							PageText ATokenBatchText = new PageText("Access Token Batch");
							if(ATokenBatchText.checkPageTextExact(ATokenBatch.getPageText()))
								printResult(test, data, "passed");
							else
								printResult(test, data, "failed");
						}
						else
							printResult(test, data, "failed");
					}
					else
					printResult(test, data, "failed");
				}
				else
					printResult(test, data, "failed");
	}
	catch(Exception e){
		printResult(test, data, "failed");
		e.printStackTrace();
		logout(driver);
	}

/*********************************************** 
*		
*		Manage Access Tokens Page - Create Tokens
*
*
**********************************************/
	
	if(!userLoggedIn("Admin"))	{
		login(driver, admin.getUsername(), admin.getPasswd());
		dashboardVisible();
	}
	
	driver.get("https://newqa.viinetwork.net/admin/accesstokenbatches");
	tableVisible();
	navigateSubNav(driver, "Add Access Token Batch");
	pageTitleVisible("Access Token Batch");
	setField(driver, "batchName", "Test Batch " + now.getToday());
	setField(driver, "number", "500");
	if(ie || safari)
		now.setDateByClick(driver, 1);
	else
		now.setDate(driver, 1);
	getSelectVisText(driver, "viiform", "Clone - Add Case Milestone and User");
	getSelectVisText(driver, "qatestingprovider", "Provider, Testing");
	clickSave(driver, "Create Batch");
	
}	
}
	
	

