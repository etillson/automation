package test1;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Reports extends seleniumTest{

	public WebDriver driver;
	
	public Reports(WebDriver driverCurrent, WriteToFile data){
	
	driver = driverCurrent;
		
	String field;
	String fieldNum;
	String test;
	int choice;
	boolean pass;
	

    Users tester = new Users(driver, "Testing Provider");
    Users admin = new Users(driver, "Admin");
    Users testAdmin = new Users(driver, "Testing Admin");
    Users patientInherit = new Users(driver, "Enrique", "Iglesias", "!viimed689#", "etillson@viimed.com");
    
    printTitle("Reports", data);
	
/*********************************************** 
*		
*		Go to QA3
*
*
**********************************************/
    

	driver.get("https://qa3.viinetwork.net/logout");
    resizeWindow(driver);
	WebDriverWait wait = new WebDriverWait(driver, 10);
	
/*********************************************** 
*		
*		Reports
*		LoginByRole
*
*
**********************************************/
	
	test = "LoginByRole";
	try{
	login(admin);
	dashboardVisible();
	goToQa();
	getTest(test);
	submit();
	WebElement loginCount = wait.until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//th[contains(text(), 'Login Count')]/following::tr[2]")));
	System.out.println(loginCount.getText());
	
	int count =  Integer.parseInt(loginCount.getText().replaceAll("\\D+", ""));
	logout();
	shortPause();
	login(admin);
	dashboardVisible();
	goToQa();
	getTest(test);
	submit();
	loginCount = wait.until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//th[contains(text(), 'Login Count')]/following::tr[2]")));
	System.out.println(loginCount.getText());
	if(count + 1 == Integer.parseInt(loginCount.getText().replaceAll("\\D+",""))){
		printResult(test, data, "passed");
	}
	else{
		printResult(test, data, "failed");
	}
	logout();
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout();
		e.printStackTrace();
	}
	

/*********************************************** 
*		
*		Reports
*		Inherit Case
*
*
**********************************************/
	
	
	test = "Inherit Case";
	
	try{
	
	login(admin);	
	dashboardVisible();

	System.out.println(patientInherit.getUsername());
	System.out.println(patientInherit.getPatientId());
	
	goToQa();
	shortPause();
	getTest(test);
	shortPause();
	
	
	field = "Patient ID";
	fieldNum = getFieldNum(driver, field);
	System.out.println(fieldNum);
	setField(driver, fieldNum, patientInherit.getPatientId());
	field = "First name";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, patientInherit.getFirstName());
	field = "Last name";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, patientInherit.getLastName());				
	field = "Email";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, patientInherit.getEmail());
	field = "Username";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, patientInherit.getUsername());
	field = "Patient Activation";
	choice = 1;
	fieldNum = getFieldNum(driver, field);
	getSelect(driver, fieldNum, choice);
	
	clickButton(driver, "patientactivation");
	field = "Password";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, patientInherit.getPasswd());
	field = "Confirm Password";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, patientInherit.getPasswd());
	shortPause();
	clickContinue(driver);
	shortPause();
	field = "Case Type";
	choice = 1;
	fieldNum = getFieldNum(driver, field);
	getSelect(driver, fieldNum, choice);
	clickContinue(driver);
	field = "QA Testing Provider";
    //choice = 2;
	fieldNum = getFieldNum(driver, field);
	getSelectVisText(driver, fieldNum, "Provider, Testing");
	shortPause();
	submit();
	longPause();
	longPause();
	longPause();
	longPause();
	openTasklist();
	Tasks InheritCase = new Tasks(driver, "Inherit Case", true);
	InheritCase.openTask(driver);
	shortPause();
	System.out.println(InheritCase.getText(driver));
	if(InheritCase.hasActivationInfo(driver, InheritCase.getText(driver), patientInherit)){
		//InheritCase.clickTaskButton(driver);
		
		shortPause();
		submit();
		logout();
		longPause();
		login(patientInherit);
		shortPause();
		termsConditionsVisible(driver);
		WebElement iAgree = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("acknowledgement-form a[class*='button primary']")));
		iAgree.click();
		shortPause();
		goToDashboard(driver, patientInherit.getFirstName());
		shortPause();
		String relationships = getRelationships(driver);
		pass = hasRelationship(driver, relationships, "Test Provider");
		if (pass)
		System.out.println("Relationship present");
		else{
			printResult(test, data, "failed");
		}
		shortPause();
		
		
		openTasklist();
		tasklistVisible();
		Tasks patientTask = new Tasks (driver, "Does this say inherit case", true);
		patientTask.openTask(driver);
		WebElement showMore = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("a.event-number.ng-binding")));
		//#ngApp > div > div > div > div > div > vii-task > div.ng-scope > div > div > div.event-series.ng-scope > div > div:nth-child(1) > div > section > div:nth-child(3) > a
		showMore.click();
		WebElement history = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("span.subtitle.ng-binding")));
		history.click();
		shortPause();
		System.out.println(patientTask.getText(driver));
		if(patientTask.hasActivationInfoPatient(driver, InheritCase.getText(driver), patientInherit)){
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "failed");
		}
	}
	else{
		printResult(test, data, "failed");
	}
	
	logout();
	}
		catch(Exception e){
			e.printStackTrace();
		}		


/*********************************************** 
*		
*		Reports
*		All fields
*
*
**********************************************	
	test = "All Field Types";
	
	login(driver, admin.getUsername(), admin.getPasswd());
	goToQa(driver);
	getTest(driver, test);
	/*
	field = "Write A";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, "A");

	field = "Write B";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, "B");
	
	field = "Write your email";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, admin.getEmail());
	
	field = "Write F";
	fieldNum = getFieldNum(driver, field);
	setTag(driver, fieldNum, "FAT");
	
	field = "Make up password";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, admin.getPasswd());
	
	field = "Confirm Password";
	fieldNum = getFieldNum(driver, field);
	setField(driver, fieldNum, admin.getPasswd());
	
	CalendarDate newDate = new CalendarDate();
	field = "Date";
	fieldNum = getFieldNum(driver, field);
	newDate.setDate(driver, 3);
	
	field = "Date and Time";
	fieldNum = getFieldNum(driver, field);
	newDate.setDate(driver, 3, fieldNum);
	
	field = "Choose A";
	choice = "0";
	fieldNum = getFieldNum(driver, field);
	getSelect(driver, fieldNum, choice);
	
	field = "Choose B";
	fieldNum = getFieldNum(driver, field);
	setRadio(driver, fieldNum, "B");

	field = "Choose C";
	fieldNum = getFieldNum(driver, field);
	setCheckBox(driver, fieldNum, "2");
	
	field = "Upload something";
	fieldNum = getFieldNum(driver, field);
	selectMediaCombo(driver, fieldNum, "Select From Media");
	longPause();
	checkMedia(driver, "Partner in Health");
	
	field = "Record something";
	fieldNum = getFieldNum(driver, field);
	useRecorder(driver, fieldNum);

	field = "Record yourself simultaneous";
	fieldNum = getFieldNum(driver, field);
	useRecorder(driver, fieldNum);	
	
	field = "Record yourself sequential";
	fieldNum = getFieldNum(driver, field);
	useRecorder(driver, fieldNum);	
	
	WebElement fileInput = driver.findElement(By.xpath("//span[contains(text(), 'Select File')]"));
	fileInput.sendKeys("/Users/erictillson/Documents/screenshot.png");
	
	
	
	}
	
/***********************************************
 * 		
 * 		Delete Final Patient and close webdriver
 * 		if necessary 
 * 
 ***********************************************/		
			
			//  You must first perform 9c Add User Later test in order to create the user
			
			try{
			login(admin);
			shortPause();
			deleteUser(patientInherit);
			//deleteUser(driver, "enrique5");
			
			logout();
			}
			catch(Exception e){
				shortPause();
				driver.switchTo().alert().accept();
				shortPause();
				logout();
		
			}
			if(safari)
			logout();	
			//driver.quit();
			
			
		}	
	
}


