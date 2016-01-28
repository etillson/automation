package test1;


import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;


public class ScheduledActions extends seleniumTest{
	
	
//Constructor in which the tests run
	
public ScheduledActions(WebDriver driverCurrent, WriteToFile data){
    
	driver = driverCurrent;
	
	String test;    

    
    //Setup users for tests
    //Users tester = new Users(driver, "Testing Provider");
    Users admin = new Users(driver, "Admin");
    //Users testPatient = new Users(driver, "Testing Patient");
    
    printTitle("Scheduled Actions", data);
	
/*********************************************** 
*		
*		Login to QA
*
*
**********************************************/
    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://qa3.viinetwork.net/logout");
	shortPause();
	resizeWindow(driver);

	/* This is used to delete the failed actions
	 * 
	login(driver, admin.getUsername(), admin.getPasswd());
	dashboardVisible(driver);
	driver.get("https://qa3.viinetwork.net/admin/scheduled-actions");
	longPause();
	String[] filters = {"null", "null", "null", "null", "null", "null", "null", "null", "true"};
	Filters sched = new Filters(driver, filters, "scheduledActions" );
	deleteSchedActions(driver);	
	*/
	
	if(!userLoggedIn("Admin"))	
		login(admin);
	driver.get("https://qa3.viinetwork.net/tasklist");
	shortPause();
	Tasks newTask = new Tasks(driver, "PriorityImportantQATest", true);
	newTask.openTask(driver);

/*********************************************** 
*		
*		Login to alternate site
*
*
**********************************************

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://build3.viimed.com/login");
	shortPause();
	driver.manage().window().setSize(new Dimension(1920, 1080));
	login(driver, admin.getUsername(), admin.getPasswd());
	shortPause();
	driver.get("https://build3.viimed.com/dashboard");
	dashboardVisible(driver);
	goTo(driver, "Configure", "Site", "Scheduled Actions");
	longPause();
	editSchedAction(driver, -2);
	
/***********************************************
* 		
* 		Scheduled Action 1
* 		4 ScheduleOnUserLogin
* 
***********************************************/

	test = "4 Schedule";
	
	try{
	if(!userLoggedIn("Admin"))	
		login(admin);
	goToQa();
	
	getTest(test);
	submit();	
	dashboardVisible();
	logout();
	login(admin);
	longPause();
	//driver.navigate().refresh();
	refreshAlt(driver);
	shortPause();
	dashboardVisible();
	testPassed(test, data);
	goToQa();
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout();		
	}

/*************************************************
* 		
* 		Scheduled Action 2
* 		5 ScheduledActions_Now_+time_intervals
* 
*************************************************/

	test = "5 Schedule";

		try{

	
	if(!userLoggedIn(admin.getFirstName()))	
		login(admin);
	
	boolean testPass = true;
	boolean alertsPresent;
	
	
	goToQa();

	
	
	getTest(test);	
	submit();
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/scheduledactions/list");
	
	minutePause();
	
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/scheduledactions/list");
	else
		driver.get("https://qa3.viinetwork.net/admin/scheduledactions/list");
	//goTo("Configure", "Site", "Scheduled Actions");	
	/*if (safari){
		driver.get(newURL);
		longPause();
	}
	else
	*/
	
	shortPause();
	
	String [] alertText = {"scheduledactions_+ 1 min", "ScheduledActions_Intervals_hourly", "ScheduledActions_Intervals_daily"
			, "ScheduledActions_Intervals_weekly", "ScheduledActions_Intervals_monthly", "ScheduledActions_Intervals_yearly"};

	//boolean testPass = true;
	alertsPresent = containsText(alertText);
	tableVisible();
	if (alertsPresent)
		System.out.println("All alerts present");
	else{
		System.out.println("Alerts not all present");
		testPass=false;
	}
	
	driver.get("https://qa3.viinetwork.net/admin/scheduledactions/list");
	shortPause();
	testPass = editSchedAction(-2, "Alert User", "Update");
	shortPause();
	
	

	tableVisible();
	
	if(mobile)
		shortPause();
	
	if (testPass)
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
	
	if(!safari)
	deleteSchedActions();	
	longPause();

		}
		catch(Exception e){
			e.printStackTrace();
			printResult(test, data, "failed");
			logout();
		}
	
	

/*************************************************
* 		
* 		Scheduled Action 3
* 		6 ScheduledActions_@field@
*
*************************************************/

	test = "6 ScheduledActions_@field@";
	
	try{
	if(!userLoggedIn("Admin"))	
		login(admin);
	
	shortPause();
	goToQa();
	shortPause();
	getTest(test);	
	longPause();
	submit();
	longPause();
	String [] alertText2 = {"ScheduledActions_@field@", "ScheduledActions_@field2@", "ScheduledActions_@field3@"};

	if (alertsPassed(driver, alertText2))
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
	goToQa();
	}
	catch(Exception e){
		e.printStackTrace();
		printResult(test, data, "failed");
		logout();
	}
	
/*************************************************
* 		
* 		Scheduled Action 4
* 		7 ScheduledActions_Minus
* 
************************************************/
	
	test = "7 ScheduledActions_Minus";
	
	try{
	if(!userLoggedIn("Admin"))	
		login(admin);
	goToQa();
	
	getTest(test);

	CalendarDate date = new CalendarDate();
	if(safari)
		date.setDateByClick(driver, 2);
	else
	date.setDate(driver, 2);
	submit();
	testPassed(test, data);
	logout();
	}
	catch(Exception e){
		e.printStackTrace();
		printResult(test, data, "failed");
		logout();
	}

	
/*************************************************
* 		
* 		Scheduled Action 5
* 		8 Scheduled_Actions_Permissions
* 
*************************************************
*
*
*
*
*This test is no longer used because it doesn't appear to test anything**********
	
	test = "Scheduled_Actions_Permissions";
	try{
		
	login(driver, testPatient.getUsername(), testPatient.getPasswd());
	dashboardVisible(driver);

	goTo(driver, "Testing", "Testing Menu");
	if(safari)
		longPause();
	getTest(test);
	if(safari)
		longPause();
		
	pass = accessContent(driver);
	if (pass){
		logout(driver);
		login(driver, tester.getUsername(), tester.getPasswd());
		dashboardVisible(driver);
		goTo(driver, "Testing", "Testing Menu");
		getTest(test);
		submit(driver);
		logout(driver);
		login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
		longPause();
		if (safari){
			driver.get("https://qa3.viinetwork.net/admin/r/scheduledactions/list#/resource");
			shortPause();
		}
		
		else
		goTo(driver, "Configure", "Site", "Scheduled Actions");
		findTitle(driver, "Scheduled Actions");
		List<WebElement> elements = driver.findElements(By.xpath("//tr"));
		for (int k =0; elements.size() > k; k++){
	
			if(elements.get(k).getText().contains("This alert is scheduled for 3 days")){
				printResult(test, data, "passed");
				pass = true;
				break;
			}
		}
		if(!pass)
			printResult(test, data, "failed");
		if(!safari)
		try{
		deleteSchedActions(driver);
		}
		catch(Exception e){
			shortPause();
			driver.switchTo().alert().accept();
			shortPause();
			logout(driver);
			e.printStackTrace();
			
		}
	}
	}
	catch(Exception e){
		e.printStackTrace();
		printResult(test, data, "failed");
	}
		
	
	driver.get("https://qa3.viinetwork.net/logout");
		

/*************************************************
* 		
* 		Scheduled Action 5
* 		9 Uncomplete Task
* 
*************************************************/
	
	test = "Uncomplete_Task";
	try{
		login(driver, admin.getUsername(), admin.getPasswd());
		dashboardVisible();
		goToQa();
		getTest(test);
		submit();
		dashboardVisible();
		if(mobile)
			shortPause();
		openTasklist();
		tasklistVisible();
		Tasks uncomplete1 = new Tasks(driver, true);
		uncomplete1.openTask(driver, "Uncomplete1");
		if(mobile)
			shortPause();
		uncomplete1.taskButton(driverCurrent, "Passed!");
		Tasks uncomplete2 = new Tasks(driver, true);
		uncomplete2.openTask(driver, "Uncomplete2");
		uncomplete2.taskButton(driverCurrent, "Passed!");
		minutePause();

		driver.navigate().refresh();
		Tasks uncomplete = new Tasks(driver, true);
		if(uncomplete.checkTasks(driver, "Uncomplete", true))
			printResult(test, data, "failed");
		else
			printResult(test, data, "passed");
		logout();
	}
	catch(Exception e){
		e.printStackTrace();
		printResult(test, data, "failed by exception");
		driver.get("https://qa3.viinetwork.net/logout");
	}
	
		
	//*/
	}
	
	
}
	
	

