package test1;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;



public class seleniumTest2{

	public static boolean safari = true;
    
	public static void main(String[] args) throws InterruptedException{
		
		
		
		System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Downloads/chromedriver");
	    WebDriver driver = new ChromeDriver();
	    //WebDriver driver = new FirefoxDriver();
	    
	    String test;
	    String username = "admin4";
	    String passwd = "3INecuador";

	    Users tester = new Users(driver, "Testing Provider");
	    Users admin = new Users(driver, "Admin");
		
/*********************************************** 
 *		
 *		Login to QA
 *
 *
 **********************************************/
	    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://newqa.viinetwork.net/login");
		
		login(driver, username, passwd);
		
		shortPause();
		
		goToQa(driver);
		
/***********************************************
 * 		
 * 		Scheduled Action 1
 * 		4 ScheduleOnUserLogin
 * 
 ***********************************************

		test = "4 Schedule";
		
		getTest(driver, test);
		shortPause();
		submit(driver);	
		longPause();
		logout(driver);
		login(driver, username, passwd);
		longPause();
		driver.navigate().refresh();
		testPassed(driver, test);
		goToQa(driver);
		

/*************************************************
 * 		
 * 		Scheduled Action 2
 * 		5 ScheduledActions_Now_+time_intervals
 * 
 *************************************************/
		
		/*
		test = "5 Schedule";
		
		getTest(driver, test);	
		shortPause();
		submit(driver);
		minutePause();
		driver.navigate().refresh();
		shortPause();
		
		String [] alertText = {"scheduledactions_+ 1 min", "ScheduledActions_Intervals_hourly", "ScheduledActions_Intervals_daily"
				, "ScheduledActions_Intervals_weekly", "ScheduledActions_Intervals_monthly", "ScheduledActions_Intervals_yearly"};
		boolean alertsPresent;
		boolean testPass = true;
		alertsPresent = containsText(alertText, driver);
		if (alertsPresent)
			System.out.println("All alerts present");
		else{
			System.out.println("Alerts not all present");
			testPass=false;
		}
		 
		shortPause();
		testPass = editSchedAction(driver);
		*/
		shortPause();
		if(ifSafari()){
			driver.get("https://newqa.viinetwork.net/admin/scheduled-actions");	
		}
		deleteSchedActions(driver);	
		/*if (testPass)
			System.out.println("Test " + test + " passed");
		else
			System.out.println("Test " + test + " failed");
		goToQa(driver);
		

/*************************************************
 * 		
 * 		Scheduled Action 3
 * 		6 ScheduledActions_@field@
 * 
 *************************************************
		
		test = "6 ScheduledActions_@field@";
		getTest(driver, test);	
		shortPause();
		submit(driver);
		longPause();
		String [] alertText2 = {"ScheduledActions_@field@", "ScheduledActions_@field2@", "ScheduledActions_@field3@"};
		alertsPresent = alertsPassed(driver, alertText2);
		if (alertsPresent)
			System.out.println("Test " + test + " passed");
		else
			System.out.println("Test " + test + " failed");
		goToQa(driver);
		
/*************************************************
 * 		
 * 		Scheduled Action 4
 * 		7 ScheduledActions_Minus
 * 
 *************************************************/		
		
		test = "7 ScheduledActions_Minus";
		
		
		getTest(driver, test);
		shortPause();
		WebElement dateBox = driver.findElement(By.xpath("//a[contains(@class, 'quickdate-button')]"));
		dateBox.click();
		WebElement dateInput = driver.findElement(By.xpath("//input[contains(@class, 'quickdate-date-input')]"));		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 2);  // number of days to add
		String dt = sdf.format(c.getTime());  // dt is now the new date		
		dateInput.sendKeys(dt);
		dateInput.sendKeys(Keys.RETURN);
		
		
		submit(driver);
		testPassed(driver, test);
		goToQa(driver);
					
	}


	
	
	
//		Methods	
	
	
public static boolean containsText(String[] values, WebDriver driver){
	boolean textFound = false;
	
	for (int k = 0; k < values.length; k++){
		List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class, 'alert-box') and contains(text(),'" + values[k] + "')]"));
		textFound = true;
		try{
			Assert.assertTrue(list.size() > 0, "Text not found!");
		} catch (AssertionError e) {
			e.printStackTrace();
			textFound = false;
			break;
		}
	}
	return textFound;
}

public static boolean alertExists(WebDriver driver, String alertText){
	try{
	WebElement element = driver.findElement(By.xpath("//div[contains(@class, 'alert-box')]"));
	System.out.println(element.getText());
	if(element.getText().equals(alertText))
		return true;
	else
		return false;
	}
	catch (Exception e) {
		System.out.println("Alert box does not exist");
		return false;
	}
	
}


public static boolean editSchedAction(WebDriver driver){
	boolean textFound;
	
	WebElement edit= driver.findElement(By.xpath("//*[contains(text(),'Edit')]"));
	edit.click();
	shortPause();
	WebElement textBox = driver.findElement(By.xpath("//input[contains(@type, 'text')]"));
	textBox.clear();
	textBox.sendKeys("09/04/2014 11:47");
	WebElement submit = driver.findElement(By.xpath("//button[contains(@type, 'submit')]"));
	submit.click();
	shortPause();
	driver.navigate().refresh();
	shortPause();
	String [] alertText ={"ScheduledActions_Intervals_hourly"};
	textFound = containsText(alertText, driver);
	if (textFound)
		System.out.println("Alert Present");
	else
		System.out.println("Alert not present");
	
	return textFound;
}

public static boolean alertsPassed(WebDriver driver, String[] values){
	
	boolean textFound = false;
	
	for (int k = 0; k < values.length; k++){
		
		List<WebElement> list = driver.findElements(By.xpath("//div[contains(@class, 'alert-box') and contains(text(),'" + values[k] + "')]"));
		if(list.size() > 0)
			textFound = true;
		else
			{
			textFound = false;
			break;
			}
	}
		return textFound;
	
}

public static void deleteSchedActions(WebDriver driver)	{
	List<WebElement> elements = driver.findElements(By.xpath("//span[contains(@data-dropdown, 'dropdown-')]"));
	
	while (elements.size() > 0){
		WebElement dropArrow = (new WebDriverWait(driver, 10))
				   .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-dropdown, 'dropdown-')]")));
		WebElement delete = driver.findElement(By.xpath("//ul[contains(@id, 'dropdown-')]"));
		
		if (safari){
			dropArrow.click();
			shortPause();
			WebElement delete2 = driver.findElement(By.xpath("//a[contains(text(), 'Delete')]"));
			System.out.println(delete2.getText());
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", delete2); 
			checkAlert(driver);
			shortPause();

		
			
		}
		
		else{
		Actions builder = new Actions(driver);
		builder.moveToElement(dropArrow)
			.click()
			.moveToElement(delete)
			.click()
			.perform();
		checkAlert(driver);
		}
		
		elements = driver.findElements(By.xpath("//span[contains(@data-dropdown, 'dropdown-')]"));
		}	
}
	
	
	
public static void checkAlert(WebDriver driver) {
	    try {
	        WebDriverWait wait = new WebDriverWait(driver, 4);
	        wait.until(ExpectedConditions.alertIsPresent());
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    } catch (Exception e) {
	       System.out.println("Alert failed");
	    }
	}	

	
public static void getTest(WebDriver driver, String test){
	WebElement pass = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(test)));
	pass.click();

}

public static void submit(WebDriver driver){
	WebElement submit = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@name, 'submit')]")));
	submit.click();
	
}

public static void submitButton(WebDriver driver){
	WebElement submit = driver.findElement(By.xpath("//button[@type='submit']"));
	submit.click();
	
}

public static void clickButton(WebDriver driver, String name){
	WebElement submit = driver.findElement(By.xpath("//button[contains(@name, '"+ name + "')]"));
	submit.click();
	
}

public static void refresh(WebDriver driver){
	Actions refresh = new Actions(driver);
	refresh.keyDown(Keys.COMMAND).sendKeys("R").perform();
	
}
	
	
public static void longPause(){
	try {
	    Thread.sleep(4000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
		}
}

public static void minutePause(){
	try {
	    Thread.sleep(68000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
		}
}

public static void thirtyPause(){
	try {
	    Thread.sleep(30000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
		}
}

public static void shortPause(){
	try {
	    Thread.sleep(1000);
	} catch (InterruptedException e) {
	    e.printStackTrace();
		}
}
	
	
	//Checks for the passed element on the dashboard to confirm the test passed
	
public static void testPassed(WebDriver driver, String test)	{
	try{
	WebElement element = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//div[contains(@class, 'alert-box')]")));    
	System.out.println("Test " + test + " Passed \n");
	}
	catch(Exception e){
	System.out.println("Test " + test + " Failed \n");
	}
		
	
}
	
	
	
	// Takes the user to the QA test list via the dropdown at the top of the screen
	
public static void goToQa(WebDriver driver){
	WebElement qa = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(text(), 'Quality Assurance')]")));
	WebElement testList = driver.findElement(By.xpath("html/body/div[1]/nav/section/ul[2]/li[1]/ul/li[3]/a"));
	
	if(safari){
	String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript(mouseOverScript, qa);
	testList.click();
	}
	
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(qa)
		.moveToElement(testList)
		.click()
		.perform();
	}
	
	
}

public static void goTo(WebDriver driver, String head, String cat, String subCat){
	WebElement element1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + head + "')]")));
	WebElement element2 = driver.findElement(By.xpath("//a[contains(text(), '" + cat + "')]"));
	WebElement element3 = driver.findElement(By.xpath("//a[contains(text(), '" + subCat + "')]"));
	Actions builder = new Actions(driver);
	builder.moveToElement(element1)
		.moveToElement(element2)
		.moveToElement(element3)
		.click()
		.perform();
}

public static void goTo(WebDriver driver, String head, String cat){
	WebElement element1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + head + "')]")));
	WebElement element2 = driver.findElement(By.xpath("//a[contains(text(), '" + cat + "')]"));
	Actions builder = new Actions(driver);
	builder.moveToElement(element1)
		.moveToElement(element2)
		.click()
		.perform();
}

public static void goToDashboard(WebDriver driver, String name){
	//if (name.startsWith("admin") || name.startsWith("Admin"))
		//name = "admin";
	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(text(), '" + name + "')]")));
	WebElement element2 = driver.findElement(By.xpath("//a[contains(text(), 'Dashboard')]"));
	Actions builder = new Actions(driver);
	builder.moveToElement(element)
		.moveToElement(element2)
		.click()
		.perform();
}

public static void goToDashboardFromEdit(WebDriver driver, String name){
	//if (name.startsWith("admin") || name.startsWith("Admin"))
		//name = "admin";
	WebElement element = (new WebDriverWait(driver, 10))
			   .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + name + "')]")));
	WebElement element2 = driver.findElement(By.xpath("//a[contains(text(), 'Dashboard')]"));
	Actions builder = new Actions(driver);
	builder.moveToElement(element)
		.click()
		.moveToElement(element2)
		.click()
		.perform();
}
	// Logs user out of the system via the dropdown at the top of the screen


public static void logout(WebDriver driver, String user){
	WebElement logout = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//nav//a[contains(text(), '" + user + "')]")));
	WebElement testList = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
	Actions builder = new Actions(driver);
	builder.moveToElement(logout)
		.moveToElement(testList)
		.click()
		.perform();	
}

public static void logout(WebDriver driver){
	WebElement logout = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@class='right']/li[last()]")));
	WebElement testList = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
	Actions builder = new Actions(driver);
	builder.moveToElement(logout)
		.moveToElement(testList)
		.click()
		.perform();	
}
/*public static void logout(WebDriver driver){
	WebElement logout = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("html/body/div[1]/nav/section/ul[2]/li[3]/a")));
	WebElement testList = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
	Actions builder = new Actions(driver);
	builder.moveToElement(logout)
		.moveToElement(testList)
		.click()
		.perform();	
}*/

public static void logoutPatient(WebDriver driver){
	WebElement logout = driver.findElement(By.xpath("html/body/div[1]/nav/section/ul[2]/li[3]/a"));
	WebElement testList = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
	Actions builder = new Actions(driver);
	builder.moveToElement(logout)
		.moveToElement(testList)
		.click()
		.perform();
		
}

	// This logs in the user to the dashboard

public static void login(WebDriver driver, String username, String passwd){

	WebElement user = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.name("username")));
	WebElement name = driver.findElement(By.name("password"));
	WebElement submit = driver.findElement(By.xpath("//*[@id='ngApp']/div/div/div/div/div/div/vii-login-form/div/div/form/button"));
	user.sendKeys(username);
	name.sendKeys(passwd);
	submit.click();	
	
}

public static void loginPatient(WebDriver driver){

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://newqa.viinetwork.net/login");
	WebElement username = driver.findElement(By.name("username"));
	WebElement name = driver.findElement(By.name("password"));
	WebElement submit = driver.findElement(By.xpath("//*[@id='ngApp']/div/div/div/div/div/div/vii-login-form/div/div/form/button"));
	username.sendKeys("Testing Patient");
	name.sendKeys("viimedtester");
	submit.click();	
}


public static void openTasklist(WebDriver driver){
	try{
	WebElement tasklist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h5[contains(text(), 'Tasklist')]")));
	WebElement taskNumber = driver.findElement(By.xpath("//span[contains(@class, 'label round alert')]"));
	System.out.println(taskNumber.getText());
	
	tasklist.click();
	}
	catch(Exception e){
		System.out.println("Task completion didn't redirect to dashboard");
	}	
}

public static void getTaskNum(WebDriver driver){
	
	List<WebElement> tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
	System.out.println(tasks.size());
}





public static void scanTasks(WebDriver driver){
	boolean hasHighPriority = false;
	int highPriority = 0;
	boolean hasNormalPriority = false;
	int normalPriority = 0;
	List<WebElement> tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
	for (int k = 0; k < tasks.size(); k++){
		
		WebElement task = driver.findElement(By.xpath("//div[contains(@class, 'event-block task')]"));
		System.out.println(task.getCssValue("background-color"));
		System.out.println(task.getAttribute("class"));
		if (task.getCssValue("background-color").equals("rgba(211, 34, 42, 1)") && task.getAttribute("class").equals("event-block task  high-priority-task")){
			System.out.println(task.getAttribute("class"));
			hasHighPriority = true;
			highPriority++;
			}
		else if (task.getCssValue("background-color").equals("rgba(211, 34, 42, 1)") && task.getAttribute("class").equals("event-block task")){
			hasNormalPriority = true;
			normalPriority++;
		}
			
		task.click();
		shortPause();
		WebElement passed = driver.findElement(By.xpath("//a[contains(text(), 'Passed!')]"));
		passed.click();
		shortPause();
		tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
		
		}	
	
}

public void deleteTasks(WebDriver driver){
	List<WebElement> tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
	while (tasks.size() > 0){
		
		WebElement task = driver.findElement(By.xpath("//div[contains(@class, 'event-block task')]"));
				
		task.click();
		shortPause();
		WebElement passed = driver.findElement(By.xpath("//a[contains(text(), 'Passed!')]"));
		passed.click();
		shortPause();
		tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
		
		}
	
}

public static String getFieldNum(WebDriver driver, String fieldLabel){
	try{
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//label[contains(text(), '"+fieldLabel+"')]")));
	return field.getAttribute("for");	
	}
	catch (Exception e){
	System.out.println("Field " +fieldLabel + " does not exist - Test Fail");
	return "";
	}
	
}

public static String getFieldNumExact(WebDriver driver, String fieldLabel){
	try{
	WebElement field = driver.findElement(By.xpath("//label[text()='"+fieldLabel+"']"));
	return field.getAttribute("for");	
	}
	catch (Exception e){
	System.out.println("Field " +fieldLabel + " does not exist - Test Fail");
	return "";
	}
	
}

public static void setField(WebDriver driver, String fieldId, String input){
	
	try{
	WebElement field = driver.findElement(By.id(fieldId));
	field.sendKeys(input);	
	}
	catch(Exception e){
		System.out.println("Fail - " + fieldId + " field does not exist");
	}
}

public static void setTag(WebDriver driver, String fieldId, String input){
	
	try{
	WebElement field = driver.findElement(By.xpath("//tags-input[contains(@id, '" + fieldId + "')]//following::input[contains(@class, 'tag-input')]"));
	field.sendKeys(input);	
	}
	catch(Exception e){
		System.out.println("Fail - " + fieldId + " field does not exist");
	}
}

public static void clearField(WebDriver driver, String fieldId){
	
	try{
	WebElement field = driver.findElement(By.id(fieldId));
	field.clear();	
	}
	catch(Exception e){
		System.out.println("Fail - " + fieldId + " field does not exist");
	}
}

public static void setTextBox(WebDriver driver, String fieldId, String input){
	WebElement field = driver.findElement(By.id(fieldId));
	System.out.println(field.getText());
	Actions builder = new Actions(driver);
	builder.moveToElement(field).moveByOffset(10, 10).click().click().sendKeys(input).perform();
	
}

public static void setFieldByName(WebDriver driver, String fieldName, String input){
	WebElement field = driver.findElement(By.name(fieldName));
	field.sendKeys(input);	
}

public static void getSelect(WebDriver driver, String fieldNum, String value){
	
	WebElement dropdown = driver.findElement(By.id(fieldNum));
	Select choice = new Select(dropdown);
	choice.selectByValue(value);	
}

public static void chooseDynamic(WebDriver driver, String fieldNum, String value){
	WebElement element = driver.findElement(By.id(fieldNum));
	Actions builder = new Actions(driver);
	builder.moveToElement(element).click().sendKeys(value).perform();
	shortPause();
	try{
	WebElement element2 = driver.findElement(By.xpath("//div[contains(text(), '" + value + "')]"));
	builder.moveToElement(element2).click().perform();
	}
	catch(Exception e){
		System.out.println("Web Element already entered");
	}
	
}

public static void clickContinue(WebDriver driver){
	WebElement continueButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//strong[contains(text(), 'Continue')]")));
	continueButton.click();
}

public static void deleteUser(WebDriver driver, String username){
	WebElement admin = driver.findElement(By.xpath("//a[contains(text(), 'Configure')]"));
	WebElement people = driver.findElement(By.xpath("//a[contains(text(), 'People')]"));
	WebElement users = driver.findElement(By.xpath("//a[contains(text(), 'Users')]"));
	Actions select = new Actions(driver);
		select.moveToElement(admin);
		select.moveToElement(people);
		select.moveToElement(users).click().perform();
	shortPause();
	try{
	WebElement userList = driver.findElement(By.xpath("//tr[td[contains(text(), '" + username + "')]]/td[10]//span"));
	WebElement delete = driver.findElement(By.xpath("//tr[td[contains(text(), '" + username + "')]]/td[10]//a[contains(@class, 'confirm-delete')]"));
	System.out.println(userList.getText());
	select.moveToElement(userList).click().moveToElement(delete).click().perform();
	shortPause();
	checkAlert(driver);
	try{
	WebElement userGone = driver.findElement(By.xpath("//tr[td[contains(text(), '" + username + "')]"));
	System.out.println("User not deleted");
	}
	catch (Exception e) {
		System.out.println("User deleted");
		}
	}
	catch(Exception e){
		System.out.println("User does not exist");
	}
	

}

public static void openSettings(WebDriver driver){
	WebElement config = driver.findElement(By.xpath("//a[contains(text(), 'Configure')]"));
	WebElement site = driver.findElement(By.xpath("//a[contains(text(), 'Site')]"));
	WebElement settings = driver.findElement(By.xpath("//a[contains(text(), 'Settings')]"));
	Actions select = new Actions(driver);
	select.moveToElement(config);
	select.moveToElement(site);
	select.moveToElement(settings).click().perform();
	shortPause();
	
}

public static String getRelationships(WebDriver driver){
	WebElement element = driver.findElement(By.xpath("//span[contains(text(), 'Type:')]/.."));
	System.out.println(element.getText());
	return element.getText();
	
}

public static boolean hasRelationship(WebDriver driver, String relationship, String relationshipToFind){
	if (relationship.contains(relationshipToFind))
		return true;
	else
		return false;
}

public static boolean hasOverview(WebDriver driver, String name){
	
	try{
	WebElement element = driver.findElement(By.xpath("//h2"));
	if(element.getText().equals(name + " Overview"))
		return true;
	else
		return false;
	}
	catch (Exception e){
		return false;
				
	}
	
}

public static String captureScreen(WebDriver driver, String file) {

    String path;
    try {
       
        File source = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        path = "/Users/erictillson/Documents/Screenshots/mac/chrome/"+ file + ".png";
        FileUtils.copyFile(source, new File(path)); 
    }
    catch(IOException e) {
        path = "Failed to capture screenshot: " + e.getMessage();
    }
    return path;

}

public static void useRecorder(WebDriver driver){
	WebElement element = driver.findElement(By.xpath("//vii-recorder"));
	System.out.println(element.getLocation());
	int width = element.getSize().getWidth();
	int height = element.getSize().getHeight();
	Actions newAction = new Actions(driver);
	
	if(element.getSize().getWidth()<890)
	newAction.moveToElement(element, (width / 2) + 40, (int)(height * 0.59)).click().click().click().perform();
	else
	newAction.moveToElement(element, (width / 2), (int)(height * 0.59)).click().click().click().perform();
	
	System.out.println(element.getSize().getHeight());
	System.out.println(element.getSize().getWidth());
	shortPause();
	
	if(element.getSize().getWidth()<890)
	newAction.moveToElement(element, (width / 2) + 60, (int)(height * 0.90)).click().perform();
	else
	newAction.moveToElement(element, width / 2, (int)(height * 0.90)).click().perform();
	
	longPause();
	longPause();
	longPause();
	
	if(element.getSize().getWidth()<890)
		newAction.moveToElement(element, width / 2 + 60, (int)(height * 0.90)).click().perform();
	else
		newAction.moveToElement(element, width / 2, (int)(height * 0.90)).click().perform();


}

public static void playVideo(WebDriver driver, String title){
	try{
	WebElement element = driver.findElement(By.xpath("//h4[contains(text(), '"+ title + "')]"));
	System.out.println(element.getLocation());
	int width = element.getSize().getWidth();
	int height = element.getSize().getHeight();
	Actions newAction = new Actions(driver);
	newAction.moveToElement(element, 100, 100).click().perform();
	longPause();
	longPause();
	}
	catch(Exception e){
		System.out.println("There was a problem with video playback");
		
	}
}

public static String getNotification(WebDriver driver){
	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//*[@class='vii-form-error-text']")));
	return element.getText();
	
}

public static void setRadio(WebDriver driver, String fieldId, String input){
	WebElement element = driver.findElement(By.xpath("//input[contains(@id, '"+ fieldId + "') and contains(@value, '"+input+"')]"));
	element.click();
}

public static void setCheckBox(WebDriver driver, String fieldId, String selection){
	WebElement element = driver.findElement(By.xpath("//input[contains(@id, '"+ fieldId + "-"+selection+"')]"));
	element.click();
}

public static void selectMediaCombo(WebDriver driver, String fieldId, String selection){
	WebElement element = driver.findElement(By.xpath("//label[contains(@for, '"+ fieldId + "')]//following::a[contains(text(), '"+ selection +"')]"));
	element.click();
}

public static void checkMedia(WebDriver driver, String fileName){
	WebElement mediaWait = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//legend[contains(text(), 'Media Library')]")));
	WebElement element = driver.findElement(By.xpath("//td[contains(text(), '"+ fileName + "')]//following::input[contains(@type, 'checkbox')]"));
	element.click();
	
}

public static void useRecorder(WebDriver driver, String fieldId){
	WebElement element = driver.findElement(By.xpath("//label[contains(@for, '"+ fieldId + "')]//following::vii-recorder"));

	System.out.println(element.getLocation());
	int width = element.getSize().getWidth();
	int height = element.getSize().getHeight();
	Actions newAction = new Actions(driver);
	newAction.moveToElement(element).perform();
	longPause();
	
	if(element.getSize().getWidth()<890)
	newAction.moveToElement(element, (width / 2) + 50, (int)(height * 0.59)).click().click().click().click().click().perform();
	else
		newAction.moveToElement(element, (width / 2), (int)(height * 0.59)).click().click().click().click().click().perform();

	
	System.out.println(element.getSize().getHeight());
	System.out.println(element.getSize().getWidth());
	shortPause();
	
	if(element.getSize().getWidth()<890)
	newAction.moveToElement(element, (width / 2) + 60, (int)(height * 0.90)).click().perform();
	else
	newAction.moveToElement(element, width / 2, (int)(height * 0.90)).click().perform();
	
	longPause();
	longPause();
	longPause();
	
	if(element.getSize().getWidth()<890)
		newAction.moveToElement(element, width / 2 + 60, (int)(height * 0.90)).click().perform();
	else
		newAction.moveToElement(element, width / 2, (int)(height * 0.90)).click().perform();

}

public static void flash(WebElement element, WebDriver driver) {
    JavascriptExecutor js = ((JavascriptExecutor) driver);
    String bgcolor  = element.getCssValue("backgroundColor");
    for (int i = 0; i <  3; i++) {
        changeColor("rgb(0,200,0)", element, js);
        changeColor(bgcolor, element, js);
    }
}
public static void changeColor(String color, WebElement element,  JavascriptExecutor js) {
    js.executeScript("arguments[0].style.backgroundColor = '"+color+"'",  element);

    try {
        Thread.sleep(20);
    }  catch (InterruptedException e) {
    }
 }

public static boolean ifSafari(){
	return safari;
}

public static void jsMouseOver(WebDriver driver, WebElement element){
	String mouseOverScript = 
			"if(document.createEvent)"
			+ "{var evObj = document.createEvent('MouseEvents');"
			+ "evObj.initEvent('mouseover', true, false); "
			+ "arguments[0].dispatchEvent(evObj);} "
			+ "else if(document.createEventObject) "
			+ "{ arguments[0].fireEvent('onmouseover');}";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript(mouseOverScript, element);	
}
}

