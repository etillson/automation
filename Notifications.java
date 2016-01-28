package test1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Notifications extends seleniumTest{

	public WebDriver driver;
	
public Notifications(WebDriver driverCurrent, WriteToFile data)	{
	System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Downloads/chromedriver");
	//WebDriver driver = new ChromeDriver();
	driver = driverCurrent;

	String test;
	String field;
	String fieldNum;
	//<<< Instantiation of Users  >>>
	
	Users tester = new Users(driver, "Testing Provider");
	Users admin = new Users(driver, "Admin");

	
	printTitle("Notifications", data);
	
/*********************************************** 
*		
*		Go to qa3
*
*
**********************************************/
	    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://qa3.viinetwork.net/login");
		resizeWindow(driver);
	    
		WebDriverWait wait = new WebDriverWait(driver, 10);
		//login(driver, username, passwd);
		

/*********************************************** 
*		
*		Notifications
*		18 Required
*
*
**********************************************/		
		test = "18 Required";
		
		try{
		login(admin);
		shortPause();
		goToQa();
		getTest(test);
		submit();
		if(getNotification(driver).equals("This field is required.")){
			shortPause();
			field = "Leave this field blank";
			fieldNum = getFieldNum(driver, field);
			setField(driver, fieldNum, "Vii123");
			submit();
			shortPause();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		}
		catch(Exception e){
			printResult(test, data, "failed");
		}
		
		
/*********************************************** 
*		
*		Notifications
*		19 Letters Only
*
*
**********************************************/			
		test = "19 Letters Only";
		
		try{

		goToQa();
		getTest(test);
		field = "Type Vii123";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "Vii123");
		submit();
		if(getNotification(driver).equals("Only letters are allowed.")){
			shortPause();
			clearField(driver, fieldNum);
			setField(driver, fieldNum, "ViTi");
			submit();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		}
		catch(Exception e){
			printResult(test, data, "failed");
		}	
		
/*********************************************** 
*		
*		Notifications
*		20 Letters and Numbers Only
*
*
**********************************************/			
		test = "20 Letters and Numbers Only";
		
		try{

		goToQa();
		getTest(test);
		field = "Type @Vii123@";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "@Vii123@");
		submit();
		if(getNotification(driver).equals("Only letters and numbers are allowed.")){
			shortPause();
			clearField(driver, fieldNum);
			setField(driver, fieldNum, "Vii123");
			submit();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		}
		catch(Exception e){
			printResult(test, data, "failed");
		}

		
/*********************************************** 
*		
*		Notifications
*		21 Letters, numbers - and _ only
*
*
**********************************************/			
		test = "21 Letters, numbers - and _ only";
		
		try{

		goToQa();
		getTest(test);
		field = "Type Vii+123";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "Vii+123");
		submit();
		if(getNotification(driver).equals("Only letters, numbers, dashes, and underscores are allowed.")){
			shortPause();
			clearField(driver, fieldNum);
			setField(driver, fieldNum, "Vii123_--");
			submit();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		}
		catch(Exception e){
			printResult(test, data, "failed");
		}

		
/*********************************************** 
*		
*		Notifications
*		22 Numbers Only
*
*
**********************************************/			
		test = "22 Numbers Only";
		
		try{

		goToQa();
		getTest(test);
		field = "Type Vii123";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "Vii123");
		submit();
		if(getNotification(driver).equals("This field must contain a valid number.")){
			shortPause();
			clearField(driver, fieldNum);
			setField(driver, fieldNum, "5987.55");
			submit();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");		
			else
				printResult(test, data, "failed");		}
		else
			printResult(test, data, "failed");		}
		catch(Exception e){
			printResult(test, data, "failed");
		}
		
		
/*********************************************** 
*		
*		Notifications
*		23 Whole Numbers
*
*
**********************************************/			
		test = "23 Whole Numbers";
		
		try{

		goToQa();
		getTest(test);
		field = "Type 1.5";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "1.5");
		submit();
		if(getNotification(driver).equals("This field must contain a valid integer.")){
			shortPause();
			clearField(driver, fieldNum);
			setField(driver, fieldNum, "1981");
			submit();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		}
		catch(Exception e){
			printResult(test, data, "failed");
		}		


/*********************************************** 
*		
*		Notifications
*		24 Web Address
*
*
**********************************************/			
		test = "24 Web Address";
		
		try{

		goToQa();
		getTest(test);
		field = "Type Vii123";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "Vii123");
		submit();
		if(getNotification(driver).equals("This field must contain a valid URL.")){
			shortPause();
			clearField(driver, fieldNum);
			setField(driver, fieldNum, "https://www.google.com");
			submit();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		}
		catch(Exception e){
			printResult(test, data, "failed");
		}
		
		
/*********************************************** 
*		
*		Notifications
*		25 Unique Username
*
*
**********************************************/			
		test = "25 Unique Username";
		
		try{

		goToQa();
		getTest(test);
		field = "Type admin1";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "admin1");
		submit();
		if(getNotification(driver).equals("This username has already been taken.")){
			shortPause();
			clearField(driver, fieldNum);
			RandomGen newUser = new RandomGen();
			setField(driver, fieldNum, newUser.randomUsername());
			submit();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		}
		catch(Exception e){
			printResult(test, data, "failed");
		}
		
		
/*********************************************** 
*		
*		Notifications
*		28 Unique Identifier
*
*
**********************************************/			
		test = "28 Unique Identifier";
		
		try{

		goToQa();
		getTest(test);
		field = "Patient ID";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "123");
		submit();
		if(getNotification(driver).equals("This has already been taken.")){
			shortPause();
			clearField(driver, fieldNum);
			RandomGen newId = new RandomGen();
			setField(driver, fieldNum, newId.randomId());
			submit();
			WebElement element = wait.until(ExpectedConditions.elementToBeClickable
					(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
			if(!driver.getCurrentUrl().contains("https://qa3.viinetwork.net/forms"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		}
		catch(Exception e){
			printResult(test, data, "failed");
		}


		
}		



}
