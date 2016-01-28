package test1;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Permissions extends seleniumTest{

	
	String test; 
	public WebDriver driver;
	String url;
	String url2;
	String field;
	String fieldNum;
	String perm = "You don't have permission to access this content.";
	
    //Date object
    CalendarDate now = new CalendarDate();
	
//Constructor in which the tests run
	
public Permissions(WebDriver driverCurrent, WriteToFile data){
    
	driver = driverCurrent;
	
   

    
    //Setup users for tests
    Users tester = new Users(driver, "Testing Provider");
    Users admin = new Users(driver, "Admin");
    Users testPatient = new Users(driver, "Testing Patient");
    Users testNurse = new Users(driver, "Testing Nurse");
    Users testAdmin = new Users(driver, "Testing Admin");
    
    //Forms used for testing
    Forms permTest = new Forms(driver, "Permission Test", new String[]{"permissions", null, null, null});
    

    
    
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


/*********************************************** 
*		
*		Field Permission Test - Text Field
*
*
**********************************************/
	test = "Field Permission Test - Text Field";
	
	try{
		if(!userLoggedIn("Admin"))	
			login(admin);
		longPause();
		goTo("Configure", "Content", "Forms");
		tableVisible();
		permTest.updateForm();
		longPause();
		
		permTest.openSettings();
		shortPause();
		permTest.clearChecked(1);
		permTest.clearChecked(2);
		String[] choices = {"Logged in Users"};
		permTest.setSettingsCheckbox(1, choices);
		permTest.setSettingsCheckbox(2, choices);
		clickButtonByText(driver, "OK");
		shortPause();
		
		permTest.openFieldInspector(driver, "Text Field");
		permTest.openInspectorPart(driver, "Permissions");
		shortPause();
		
		permTest.clearCheckedInspector(1);
		permTest.clearCheckedInspector(2);
		
		String[] choices2 = {"Registered Nurse", "Test Patient"};
		permTest.permSelect(driver, choices2, 1);
		permTest.publish();
		if(alertPresent(driver, "The form was successfully saved and published."))
			System.out.println("Form has been updated");
		goTo("Configure", "Content", "Forms");
		tableVisible();
		permTest.openForm();
		url = driver.getCurrentUrl();
		if(!fieldVisible(driver, "Text Field")){
			logout();
			login(testNurse);
			pageTitleVisible("Welcome to your dashboard");
			driver.get(url);
			formVisible(driver);
		
			if(fieldVisible(driver, "Text Field")){
				logout();
				login(testPatient);
				dashboardVisible();
				driver.get(url);
				formVisible(driver);
			
			if(fieldVisible(driver, "Text Field")){
				logout();
				login(tester);
				dashboardVisible();
				driver.get(url);
				formVisible(driver);
				longPause();
			if(!fieldVisible(driver, "Text Field")){
				printResult(test, data, "passed");
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
		else
			printResult(test, data, "failed");
		
		logout();
	}
	catch(Exception e){
		e.printStackTrace();
		printResult(test, data, "failed");
		logout();
	}
  
/*********************************************** 
*		
*		Field Permission Test - View Data
*
*
**********************************************/

	test = "Field Permission Test - View Data";
	
	try{
	if(!userLoggedIn("Admin"))	
		login(admin);
	longPause();
	
	
	goTo("Configure", "Content", "Forms");
	tableVisible();	
	permTest.updateForm();
	longPause();
	permTest.openFieldInspector(driver, "Text Field");
	permTest.openInspectorPart(driver, "Permissions");
	shortPause();
	permTest.clearCheckedInspector(1);
	permTest.clearCheckedInspector(2);
	
	
	longPause();
	permTest.openSettings();
	shortPause();
	permTest.clearChecked(1);
	permTest.clearChecked(2);
	String[] choices = {"Logged in Users"};
	permTest.setSettingsCheckbox(1, choices);
	permTest.setSettingsCheckbox(2, choices);
	clickButtonByText(driver, "OK");
	shortPause();
	
	permTest.publish();
	
	
	goTo("Configure", "Content", "Forms");
	tableVisible();	
	permTest.openForm();
	/*now.setDate(driver);
	field = "Text Field";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, now.getToday());
	submit(driver);
	longPause();
	*/
	
	completePermTestForm();
	goTo("Configure", "Content", "Forms");
	tableVisible();	
	permTest.getFormResults();
	permTest.viewResult();
	shortPause();
	if(getPageText(driver).contains(now.getToday())){
		goTo("Configure", "Content", "Forms");
		tableVisible();	
		permTest.updateForm();
		longPause();
		permTest.openFieldInspector(driver, "Text Field");
		permTest.openInspectorPart(driver, "Permissions");
		shortPause();
		String [] choices2 = {"Registered Nurse"};
		permTest.permSelect(driver, choices2, 2);
		permTest.publish();
		goTo("Configure", "Content", "Forms");
		tableVisible();	
		permTest.getFormResults();
		permTest.viewResult();
		longPause();
		url = driver.getCurrentUrl();
		if(!getPageText(driver).contains(now.getToday())){
			logout();
			login(testNurse);
			pageTitleVisible("Welcome to your dashboard");
			driver.get(url);
			formVisible(driver);
			shortPause();
			if(getPageText(driver).contains(now.getToday()))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
	}
	else
		printResult(test, data, "failed");
	logout();
	shortPause();
	}
	catch(Exception e){
		e.printStackTrace();
		logout();
		shortPause();
	}

/*********************************************** 
*		
*		Form Permission Test - Who can use? - No selection
*
*
**********************************************/
	test = "Form Permission Test - Who can use? - No selection";
	
	try{
	if(!userLoggedIn("Admin"))	
		login(admin);
	goTo("Configure", "Content", "Forms");
	tableVisible();
	permTest.updateForm();
	longPause();
	permTest.openFieldInspector(driver, "Text Field");
	permTest.openInspectorPart(driver, "Permissions");
	shortPause();
	permTest.clearCheckedInspector(1);
	permTest.clearCheckedInspector(2);
	longPause();
	permTest.openSettings();
	shortPause();
	permTest.clearChecked(1);
	permTest.clearChecked(2);
	clickButtonByText(driver, "OK");
	shortPause();
	permTest.publish();
	goTo("Configure", "Content", "Forms");
	tableVisible();
	permTest.openForm();
	if(!getPageText(driver).contains(perm)){
		url = driver.getCurrentUrl();
		completePermTestForm();
		logout();
		login(testAdmin);
		dashboardVisible();
		driver.get(url);
		if(!getPageText(driver).contains(perm)){
			url = driver.getCurrentUrl();
			completePermTestForm();
			logout();
			login(testPatient);
			dashboardVisible();
			driver.get(url);
			if(getPageText(driver).contains(perm)){
				logout();
				login(testNurse);
				dashboardVisible();
				driver.get(url);
				if(getPageText(driver).contains(perm))
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
	
	logout();
	}
	catch (Exception e){
		e.printStackTrace();
		printResult(test, data, "failed");
	}

/*********************************************** 
*		
*		Form Permission Test - Who can use? - Selected
*
*
**********************************************/
	
	test = "Form Permission Test - Who can use? - Selected";
	
	try{
	if(!userLoggedIn("Admin"))	
		login(admin);
	goTo("Configure", "Content", "Forms");
	tableVisible();
	permTest.updateForm();
	longPause();
	permTest.openFieldInspector(driver, "Text Field");
	permTest.openInspectorPart(driver, "Permissions");
	shortPause();
	permTest.clearCheckedInspector(1);
	permTest.clearCheckedInspector(2);
	longPause();
	permTest.openSettings();
	shortPause();
	permTest.clearChecked(1);
	permTest.clearChecked(2);
	String[] choices = {"Registered Nurse", "Test Patient"};
	permTest.setSettingsCheckbox(1, choices);
	clickButtonByText(driver, "OK");
	shortPause();
	permTest.publish();
	goTo("Configure", "Content", "Forms");
	tableVisible();
	permTest.openForm();
	if(!getPageText(driver).contains(perm)){
		url = driver.getCurrentUrl();
		completePermTestForm();
		logout();
		login(testAdmin);
		dashboardVisible();
		driver.get(url);
		if(!getPageText(driver).contains(perm)){
			url = driver.getCurrentUrl();
			completePermTestForm();
			logout();
			login(testPatient);
			dashboardVisible();
			driver.get(url);
			if(!getPageText(driver).contains(perm)){
				logout();
				login(testNurse);
				dashboardVisible();
				driver.get(url);
				if(!getPageText(driver).contains(perm)){
					logout();
					login(tester);
					dashboardVisible();
					driver.get(url);
					if(getPageText(driver).contains(perm))
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
	else
	printResult(test, data, "failed");
	
	logout();
	}
	catch (Exception e){
		e.printStackTrace();
		printResult(test, data, "failed");
	}	

	
/*********************************************** 
*		
*		Form Permission Test - Who can use? and Who can view data? - Everyone"
*
*
**********************************************/	
	
	test = "Form Permission Test - Who can use? and Who can view data? - Everyone";
	
	try{
	if(!userLoggedIn("Admin"))	
		login(admin);
	dashboardVisible();
	goTo("Configure", "Content", "Forms");
	tableVisible();
	permTest.updateForm();
	longPause();
	permTest.openFieldInspector(driver, "Text Field");
	permTest.openInspectorPart(driver, "Permissions");
	shortPause();
	permTest.clearCheckedInspector(1);
	permTest.clearCheckedInspector(2);
	longPause();
	permTest.openSettings();
	shortPause();
	permTest.clearChecked(1);
	permTest.clearChecked(2);
	clickButtonByText(driver, "OK");
	shortPause();
	permTest.publish();
	
	
	goTo("Configure", "Content", "Forms");
	tableVisible();
	permTest.openForm();
	shortPause();
	url = driver.getCurrentUrl();
	logout();
	shortPause();
	driver.get(url);
	longPause();
	
	if(getPageText(driver).contains("Login to your account.")){
		driver.get("https://qa3.viinetwork.net");
		login(admin);
		dashboardVisible();
		goTo("Configure", "Content", "Forms");
		tableVisible();
		permTest.updateForm();
		longPause();
		permTest.openSettings();
		shortPause();
		permTest.clearChecked(1);
		permTest.clearChecked(2);
		String[] choices = {"Everyone"};
		permTest.setSettingsCheckbox(1, choices);
		clickButtonByText(driver, "OK");
		shortPause();
		permTest.publish();
		goTo("Configure", "Content", "Forms");
		tableVisible();
		permTest.openForm();
		if(!getPageText(driver).contains(perm)){
			url = driver.getCurrentUrl();
			completePermTestForm();
			goTo("Configure", "Content", "Forms");
			tableVisible();	
			permTest.getFormResults();
			permTest.viewResult();
			longPause();
			url2 = driver.getCurrentUrl();
			
			
			logout();
			shortPause();	
			driver.get(url);
			if(!getPageText(driver).contains(perm)){
				completePermTestForm();
				driver.get(url2);
				if(getPageText(driver).contains(perm)){
					driver.get("https://qa3.viinetwork.net");
					login(admin);
					dashboardVisible();
					goTo("Configure", "Content", "Forms");
					tableVisible();
					permTest.updateForm();
					longPause();
					permTest.openSettings();
					shortPause();
					permTest.setSettingsCheckbox(2, choices);
					clickButtonByText(driver, "OK");
					shortPause();
					permTest.publish();
					logout();
					shortPause();
					driver.get(url2);
					longPause();
					if(getPageText(driver).contains(now.getToday()))
						printResult(test, data, "passed");
					else
						printResult(test, data, "failed1");
					}
					else
						printResult(test, data, "failed2");
				}
					else
						printResult(test, data, "failed3");
			}
			else
			printResult(test, data, "failed4");
	}
	else
		printResult(test, data, "failed5");
	
	driver.get("https://qa3.viinetwork.net");
	}
	catch (Exception e){
		e.printStackTrace();
		printResult(test, data, "failed");
	}	


}

public void completePermTestForm(){
	now.setDate(driver);
	field = "Text Field";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, now.getToday());
	submit();
	longPause();
}

}