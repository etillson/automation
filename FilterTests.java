package test1;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class FilterTests extends seleniumTest{
	

	
	public FilterTests(WebDriver driverCurrent, WriteToFile data) {
		
		
		driver = driverCurrent;
		
		String test;
		
		String[] filters = {"null", "null", "null", "null", "null", "null", "null", "null"};
		String[] filtersTasks = {"null", "null", "null", "null", "null", "null"};
		Users admin = new Users(driver, "Admin");
		
		printTitle("Filter Tests", data);
		
		
		
/*********************************************** 
 *		
 *		Go to qa3
 *
 *
 **********************************************/
			    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

	    resizeWindow(driver);
		driver.get("https://qa3.viinetwork.net/logout");

		login(admin);
				
/*********************************************** 
 *		
 *		First Name Filter
 *
 *
 **********************************************		
		
		test = "First Name Filter";
		
		
		try{
		if(!userLoggedIn("Admin"))	
			login(admin);
		if(mobile)
			longPause();
		goTo("Configure", "People", "Users");
		Arrays.fill(filters, "null");
		filters[0] = "zane";
		Filters firstname = new Filters(driver, filters, "users");
		
		shortPause();
		shortPause();
		if (getPageText(driver).contains("ztestpatient")){
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "failed");
		}
		}
		catch(Exception e){
			e.printStackTrace();
			driver.get("https://qa3.viinetwork.net/logout");
			login(admin);
			printResult(test, data, "failed");
		}

		
/*********************************************** 
 *		
 *		Last Name Filter
 *
 *
 **********************************************		
		
		test = "Last Name Filter";
		
		
		try{

		
		goTo("Configure", "People", "Users");
		longPause();
		pageTitleVisible("Manage Users");
		
		
		Arrays.fill(filters, "null");
		filters[1] = "zaine";
		
		Filters lastname = new Filters(driver, filters, "users");
		shortPause();
		shortPause();
		if (getPageText(driver).contains("ztestpatient")){
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "failed");
		}
		}
		catch(Exception e){
			e.printStackTrace();
			driver.get("https://qa3.viinetwork.net/logout");
			login(admin);
			printResult(test, data, "failed");
		}
		
	
	
/*********************************************** 
 *		
 *		Email Filter
 *
 *
 **********************************************
		
		test = "Email Filter";
		
		
		try{

		
		goTo("Configure", "People", "Users");
		longPause();
		pageTitleVisible("Manage Users");

		Arrays.fill(filters, "null");
		filters[2] = "zzaine@viimed.com";
		
		Filters email = new Filters(driver, filters, "users" );
		shortPause();
		shortPause();
		if (getPageText(driver).contains("ztestpatient")){
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "failed");
		}
		}
		catch(Exception e){
			e.printStackTrace();
			driver.get("https://qa3.viinetwork.net/logout");
			login(admin);
			printResult(test, data, "failed");
		}
		
		
/*********************************************** 
 *		
 *		Relation Filter
 *
 *
 **********************************************
		
		test = "Relation Filter";
		
		
		try{

		
		goTo("Configure", "People", "Users");
		longPause();
		pageTitleVisible("Manage Users");

		Arrays.fill(filters, "null");
		filters[3] = "true";
		
		Filters relation = new Filters(driver, filters, "users" );
		shortPause();
		shortPause();
		if (getPageText(driver).contains("ztestpatient")){
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "failed");
		}
		}
		catch(Exception e){
			e.printStackTrace();
			driver.get("https://qa3.viinetwork.net/logout");
			login(admin);
			printResult(test, data, "failed");
		}

/*********************************************** 
 *		
 *		Tags Filter
 *
 *
 **********************************************
		
		test = "Tags Filter";
		
		
		try{

		
		goTo("Configure", "People", "Users");
		longPause();
		pageTitleVisible("Manage Users");

		Arrays.fill(filters, "null");
		filters[4] = "zman";
		
		Filters tag = new Filters(driver, filters, "users" );
		shortPause();
		shortPause();
		if (getPageText(driver).contains("ztestpatient")){
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "failed");
		}
		}
		catch(Exception e){
			e.printStackTrace();
			driver.get("https://qa3.viinetwork.net/logout");
			login(admin);
			printResult(test, data, "failed");
		}
		
/*********************************************** 
 *		
 *		Roles Filter
 *
 *
 **********************************************
		
		test = "Roles Filter";
		
		
		try{

		
		goTo("Configure", "People", "Users");
		longPause();
		pageTitleVisible("Manage Users");

		Arrays.fill(filters, "null");
		filters[5] = "Specialist";
		
		Filters tag = new Filters(driver, filters, "users" );
		shortPause();
		shortPause();
		if (getPageText(driver).contains("ztestpatient")){
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "failed");
		}
		}
		catch(Exception e){
			e.printStackTrace();
			driver.get("https://qa3.viinetwork.net/logout");
			login(admin);
			printResult(test, data, "failed");
		}
		
		
/*********************************************** 
 *		
 *		Tag Date Created Start
 *
 *
 **********************************************	

		
		//I need to come back and create a new user on 1/21/2015 and modify the test accordingly ********
		
		test = "Tag Date Created Start";
		
		
		try{

		
		goTo("Configure", "People", "Users");
		longPause();
		pageTitleVisible("Manage Users");

		Arrays.fill(filters, "null");
		filters[6] = "1/21/2015";
		filters[4] = "qman";
		Filters dateStart = new Filters(driver, filters, "users" );
		Arrays.fill(filters, "null");
		filters[4] = "zman";
		Filters tag = new Filters(driver, filters, "users");
		shortPause();
		shortPause();
		if (getPageText(driver).contains("ztestpatient")){
			printResult(test, data, "failed");
		}
		else{
			tag.clickFilterButton();
			shortPause();
			tag.resetFilters();
			shortPause();
			longPause();
			pageTitleVisible("Manage Users");
			Arrays.fill(filters, "null");
			filters[6] = "1/19/2015";
			filters[4] = "qman";
			
			Filters dateStart2 = new Filters(driver, filters, "users" );
			Arrays.fill(filters, "null");
			filters[4] = "zman";
			Filters tag2 = new Filters(driver, filters, "users");

			shortPause();
			shortPause();
			if (getPageText(driver).contains("ztestpatient"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
			dateStart2.clickFilterButton();
			shortPause();
			dateStart2.resetFilters();
			
		}
		
		
		}
		catch(Exception e){
			e.printStackTrace();
			driver.get("https://qa3.viinetwork.net/logout");
			login(admin);
			printResult(test, data, "failed");
		}
		
		
/*********************************************** 
 *		
 *		Tag Date Created End
 *
 *
 **********************************************	
		
		//I need to come back and create a new user on 1/21/2015 and modify the test accordingly *********
		
		test = "Tag Date Created End";
		
		
		try{

		
		goTo("Configure", "People", "Users");
		longPause();
		pageTitleVisible("Manage Users");

		Arrays.fill(filters, "null");
		filters[6] = "1/22/2015";
		filters[7] = "1/24/2015";
		
		Filters dateEnd = new Filters(driver, filters, "users" );
		shortPause();
		shortPause();
		if (getPageText(driver).contains("qtestpatient") && !getPageText(driver).contains("ztestpatient")){
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "failed");
		}
		}
		catch(Exception e){
			e.printStackTrace();
			driver.get("https://qa3.viinetwork.net/logout");
			login(admin);
			printResult(test, data, "failed");
		}
		
		
/*********************************************** 
 *		
 *		Tasklist Filter Tests
 *
 *
 **********************************************/
		goTo(admin.getFirstName(), "Tasks");
		longPause();
		Arrays.fill(filtersTasks, "null");
		filtersTasks[0] = "Late";
		filtersTasks[1] = "Check";
		filtersTasks[2] = "Check";
		filtersTasks[3] = "qaguy";
		filtersTasks[4] = "hipguy";
		filtersTasks[5] = "Checkout task thing";
		Filters tasklist = new Filters(driver, filtersTasks, "tasklist" );


	}

}
