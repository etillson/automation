package test1;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




public class seleniumTest{

	
	// WebDriver
	public static WebDriver driver;
	
	//Current logged in user
	public Users currentUser;
	
	//Temp WebElement
	public WebElement tempElement;
	
	//Browser determining booleans
	public static boolean safari = false;
	public static boolean ie = false;
	public static boolean mobile = false;
	public static boolean ipad = false;
	public static boolean chrome = false;
	public static boolean firefox = false;
	
	public static String baseURL = "https://qa3.viinetwork.net/";

//Constructors for seleniumTest - Set the webDriver and browser type.	
	
public seleniumTest(){

}

public seleniumTest(WebDriver currentDriver, boolean isSafari, boolean isIE, boolean isMobile, boolean isIpad, boolean isChrome, boolean isFirefox){
		driver = currentDriver;
		safari = isSafari;	
		ie = isIE;
		mobile = isMobile;
		ipad = isIpad;
		chrome = isChrome;
		firefox = isFirefox;
	}



	
	
	
/*************************************************
 *          	Methods	
 *
 *    The following methods are the bulk of the 
 *    methods for the QA testing
 * 
 *************************************************/



//************    Alerts    **************


//This checks the text in alert boxes by sending an array of strings of the desired text
public static boolean containsText(String[] values){
	boolean textFound = false;

	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("div[ng-class*='ngNotify.notifyClass']")));
	if(element.getText().contains(values[0]))
		System.out.println("it found the text!!!");
	/*
		WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//div[contains(@class, 'alert-box') and contains(text(),'" + values[0] + "')]")));*/
	for (int k = 0; k < values.length; k++){

		//List<WebElement> list = driver.findElements(By.cssSelector("div[ng-class*='ngNotify.notifyClass']"));
		if(element.getText().contains(values[k]))
		textFound = true;
		else
			return false;

	}
	return textFound;
}

public boolean alertPresent(WebDriver driver, String alert){
	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("div[ng-class*='ngNotify.notifyClass']")));
	//WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
		//	(By.cssSelector("div[class*='alert-box']")));
	if (element.getText().contains(alert))
		return true;
	return false;
}

//This checks to see if any alerts exist on the screen
public static boolean alertExists(WebDriver driver, String alertText){
	try{
	WebElement element = driver.findElement(By.cssSelector("div[ng-class*='ngNotify.notifyClass']"));
	System.out.println(element.getText());
	if(element.getText().contains(alertText))
		return true;
	else
		return false;
	}
	catch (Exception e) {
		System.out.println("Alert box does not exist");
		return false;
	}
	
}


//Checks to make sure all values of a string array are found in alerts
//This is passed the driver paremeter due to its use by the email webdriver
public static boolean alertsPassed(WebDriver driver, String[] values){
	
	boolean textFound = false;
	try{
		WebElement alerts = driver.findElement(By.cssSelector("div[ng-class*='ngNotify.notifyClass']"));
	for (int k = 0; k < values.length; k++){
		
		
		if(alerts.getText().contains(values[k]))
			textFound = true;
		else
			{
			textFound = false;
			break;
			}
	}
		return textFound;
	}
	catch(Exception e){
		return false;
	}
	
}


//Checks for the alert confirming that the test has passed
public void testPassed(String test, WriteToFile data)	{
try{
WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
		(By.xpath("//div[contains(@ng-class, 'ngNotify.notifyClass')]")));    
printResult(test, data, "passed");
shortPause();
}
catch(Exception e){
	printResult(test, data, "failed");
}
		
}


//************    Scheduled Actions    **************

public WebElement getActions(String name){
	List<WebElement> menus = driver.findElements(By.cssSelector("tr td"));
	for(int x = 0; x<menus.size(); x++){
		if(menus.get(x).getText().contains(name)){
			System.out.println(menus.get(x).getText());
			return menus.get(x+5);			
			}			
		}
	System.out.println("Menu list action not found");
	return null;
}


//Edit the day of a scheduled action
public boolean editSchedAction(int days, String type, String action){

	WebElement rc= (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("react-component[name='gridtable']")));
	WebElement saType= rc.findElement(By.xpath("//tr[contains(., '" + type + "')]"));
	saType.findElement(By.xpath("//span[contains(text(),'Update')]")).click();
	
	
	//getActions(name).findElement(By.cssSelector("a[title="+ action + "]")).click();
	longPause();
	shortPause();
	CalendarDate schedDate = new CalendarDate();
	schedDate.setDateByClick(driver, days);
	
	//schedDate.setDate(driver, days);
	shortPause();
	WebElement submit = driver.findElement(By.cssSelector("button[id='button-0']"));
	//jsMouseOver(driver, submit)
	submit.click();
	
	
	/*if(ie){
		shortPause();
		tableVisible(driver);
		goTo(driver, "Configure", "Site", "Scheduled Actions");
	}
	*/
	longPause();
	if(mobile)
		longPause();
	shortPause();
	
	String [] alertText ={"ScheduledActions_Intervals_hourly"};
	if (containsText(alertText))
		System.out.println("Alert Present");
	else{
		System.out.println("Alert not present");
		return false;
	}
	
	return true;
}

//Fires all scheduled actions
public void editSchedAction(WebDriver driver, int days){
	
	List <WebElement> rows = driver.findElements(By.cssSelector("tr"));
	if(rows.size() != 0){
		WebElement edit= (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//*[contains(text(),'Update')]")));
		edit.click();
		
		//getActions(name).findElement(By.cssSelector("a[title="+ action + "]")).click();
		longPause();
		shortPause();
		CalendarDate schedDate = new CalendarDate();
		schedDate.setDate(driver, days);
		shortPause();
		WebElement submit = driver.findElement(By.cssSelector("button[id='button-0']"));
		//jsMouseOver(driver, submit)
		submit.click();
		shortPause();
		tableVisible();
		editSchedAction(driver, days);
	}
	
}

//Method to delete all scheduled actions
public static void deleteSchedActions()	{

	List <WebElement> rows = driver.findElements(By.xpath("//th[contains(text(), 'Fires On')]"));
	System.out.println(rows.size());
	if(rows.size() != 0){
	{
	WebElement checkAll = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//th/input")));
	checkAll.click();
	WebElement tools = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("select[name=tool]")));
	Select selection = new Select(tools);
	selection.selectByIndex(1);
	WebElement apply = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("button-field[button-text=Apply]")));
	apply.click();
	try{
		shortPause();
	driver.switchTo().alert().accept();
	}
	catch(Exception e){
		shortPause();
		driver.switchTo().alert().accept();
	}
	
	}
	shortPause();
	deleteSchedActions();
	}
	
	/*List<WebElement> elements = driver.findElements(By.xpath("//span[contains(@data-dropdown, 'dropdown-')]"));
	
	while (elements.size() > 0){
		WebElement dropArrow = (new WebDriverWait(driver, 10))
				   .until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@data-dropdown, 'dropdown-')]")));
		WebElement delete = driver.findElement(By.xpath("//ul[contains(@id, 'dropdown-')]"));
		
		if(safari){
			//jsMouseOver(driver, dropArrow);
			dropArrow.click();
			shortPause();
			WebElement delete2 = driver.findElement(By.xpath("//a[contains(text(), 'Delete')]"));
			System.out.println(delete2.getText());
			delete2.click();
		}
		
		else{
		
		
		Actions builder = new Actions(driver);
		builder.moveToElement(dropArrow)
			.click()
			.moveToElement(delete)
			.click()
			.perform();
		}
		
		checkAlert(driver);
		elements = driver.findElements(By.xpath("//span[contains(@data-dropdown, 'dropdown-')]"));
		}	
		*/
}
	



//************    JavaScript Alert Handling    **************


//Switches to the alert window and accepts the warning 
public static void checkAlert(WebDriver driver) {
	    try {
	    	driver.switchTo().alert().accept();
	       // WebDriverWait wait = new WebDriverWait(driver, 4);
	       // wait.until(ExpectedConditions.alertIsPresent());
	        
	      
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    	
	    }
}	





//************    Pauses   **************



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
	
public static void superShortPause(){
	try {
	    Thread.sleep(500);
	} catch (InterruptedException e) {
	    e.printStackTrace();
		}
}	



//************    Site Navigation   **************


//Opens the current test from the QA menu
public static void getTest(String test){
	shortPause();
	WebElement pass = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(test)));
	pass.click();
}

//Clicks a submit button
public static void submit(){
	WebElement submit = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@name, 'submit')]")));
	submit.click();	
	longPause();
	if(mobile)
		longPause();
}

//alternate to click a submit button
public static void submitButton(){
	WebElement submit = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//button[@type='submit']")));
	submit.click();	
}

public static void submitInput(){
	WebElement submit = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//input[@type='submit']")));
	submit.click();	
}

//Generic button clicking method.  Clicks a button containing the name.
public static void clickButton(WebDriver driver, String name){
	WebElement submit = driver.findElement(By.xpath("//button[contains(@name, '"+ name + "')]"));
	submit.click();	
}

//Generic button clicking method.  Clicks a button containing the name.
public static void clickButton(String text){
	WebElement submit = driver.findElement(By.xpath("//button[contains(text(), '"+ text + "')]"));
	submit.click();	
}

//Generic button clicking method.  Clicks a button containing the text
public static void clickButtonByText(WebDriver driver, String text){
	WebElement submit = driver.findElement(By.xpath("//button[contains(., '"+ text + "')]"));
	submit.click();	
}

public void clickBackToTasklist(WebDriver driver){
	WebElement back = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("a.sub-nav-button")));
	back.click();
	
	
}

//alternate refresh method
public static void refresh(WebDriver driver){
	
	if(ie){
		Actions refresh = new Actions(driver);
		refresh.keyDown(Keys.CONTROL).sendKeys("R").perform();	
	}
	else{
	Actions refresh = new Actions(driver);
	refresh.keyDown(Keys.COMMAND).sendKeys("R").perform();	
	}
}

public static void refreshAlt(WebDriver driver){
	driver.get(driver.getCurrentUrl());	
}

//resize the window to make all menus and content accessible
public static void resizeWindow(WebDriver driver, int x, int y){
	if(!mobile)
	driver.manage().window().setSize(new Dimension(x, y));
}

public static void resizeWindow(WebDriver driver){
	if(!mobile)
	driver.manage().window().setSize(new Dimension(1920, 1080));
}

//waits for a table to be visible
public static void tableVisible(){
	try{
		shortPause();
	WebElement table = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("th input[type='checkbox']")));
	}
	catch(Exception e){
		System.out.println("Table not loaded");
	}
}

public static void termsConditionsVisible(WebDriver driver){
	try{
	WebElement page = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("div[id='acknowledgement_message']")));
	}
	catch(Exception e){
		System.out.println("Terms and conditions page not loaded");
	}
}

//waits for a form to be visible
public void formVisible(WebDriver driver){
	try{
		shortPause();
	WebElement form = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("h2[class*='vii-form-header-text']")));
	}
	catch(Exception e){
		System.out.println("Form not loaded");
	}
}

//Checks to see if a task list has loaded
public static void tasklistVisible(){
	try{
	shortPause();
	WebElement tasklist = driver.findElement(By.cssSelector("div[class='route-wrapper]"));
	if(!tasklist.getText().contains("No Results")){
	WebElement tasks = (new WebDriverWait(driver, 5)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("div.event-block.task")));
	}
	}
	catch(Exception e){
		System.out.println("Tasks not loaded");
	}
}

//waits for a form to be visible
public void pageVisible(WebDriver driver){
	try{
		shortPause();
	WebElement form = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("div.row")));
	}
	catch(Exception e){
		System.out.println("Page not loaded");
	}
}

//waits for a form to be visible
public void pageTitleVisible(String title){
	try{
		shortPause();
	WebElement form = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//h2[contains(text(), '" + title+ "')]")));
	}
	catch(Exception e){
		System.out.println("Page not loaded");
	}
}


//Page header, which should contain any titles
public void pageHeaderVisible(WebDriver driver, String title){
	try{
		shortPause();
	WebElement header = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//div[@class='page-header' and text(), '" + title+ "']")));
	}
	catch(Exception e){
		System.out.println("Page not loaded");
	}
}

//opens the user queue from the user list page determined by the username
public int openQueue(String username){
	String row = "null";
    WebElement tableLoad = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
    		(By.cssSelector("react-component[name='gridtable']")));
    
	List<WebElement> rows = tableLoad.findElements(By.xpath(".//tr"));
	System.out.println(rows.size());
	for (int k = 0; k< rows.size(); k++){
		if (rows.get(k).getText().contains(username)){
			System.out.println(k);
			row = rows.get(k).findElement(By.xpath(".//span[contains(text(), 'Queue')]")).getText();
	
		//span[contains(text(), 'Queue')]
		System.out.println(row);
		if(!row.contains("Queue 0")){
			rows.get(k).findElement(By.xpath(".//span[contains(text(), 'Queue')]")).click();
			return 1;
		}
		System.out.println("User has no tasks");
		return 0;
		}
		if (k == (rows.size())-1){
			WebElement next = driver.findElement(By.xpath("//a[contains(text(), 'Next')]"));
			System.out.println(next.getText());
			next.click();
			openQueue(username);
			
			//*[@id="ngApp"]/div/div/div/div/div/div[3]/vii-resource-list/resource-table/div[3]/div/ul/li[6]/a
		}
	}
	System.out.println("Error deleting tasks");
	return -1;

}

//opens the user queue from the group list page determined by the groupName
public int openGroupQueue(WebDriver driver, String groupName){
	String row = "null";
  WebElement tableLoad = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
  		(By.xpath("//*[@ng-if='rows.length']/table/tbody")));
  
	List<WebElement> rows = driver.findElements(By.xpath("//*[@ng-if='rows.length']/table/tbody/tr"));
	System.out.println(rows.size());
	for (int k = 0; k< rows.size(); k++){
		if (rows.get(k).getText().contains(groupName)){
			System.out.println(k);
			row = rows.get(k).findElement(By.xpath(".//td[4]//span[contains(text(), 'Queue')]")).getText();

		//span[contains(text(), 'Queue')]
		System.out.println(row);
		if(!row.contains("Queue 0")){
			rows.get(k).findElement(By.xpath(".//td[4]//span[contains(text(), 'Queue')]")).click();
			return 1;
		}
		System.out.println("User has no tasks");
		return 0;
		}
		if (k == (rows.size())-1){
			WebElement next = driver.findElement(By.xpath("//a[contains(text(), 'Next')]"));
			System.out.println(next.getText());
			next.click();
			openQueue(groupName);
			
			//*[@id="ngApp"]/div/div/div/div/div/div[3]/vii-resource-list/resource-table/div[3]/div/ul/li[6]/a
		}
	}
	System.out.println("Error deleting tasks");
	return -1;

}


public boolean findUserAdminPage(WebDriver driver, String username){
	
	List<WebElement> rows = driver.findElements(By.xpath("//*[@ng-if='rows.length']/table/tbody/tr"));
	System.out.println(rows.size());
	for (int k = 0; k< rows.size(); k++){
		if (rows.get(k).getText().contains(username))
			return true;
	}
	return false;
	
}


//************    Site Menu Navigation   **************



// Takes the user to the QA test list via the dropdown at the top of the screen	
public static void goToQa(){
	
	try{
	if(mobile){
		longPause();
		WebElement menu = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//span[contains(., 'Menu')]")));
		menu.click();

		WebElement qa2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//span[contains(., 'Quality Assurance')] | //span[contains(., 'Testing')]")));
		qa2.click();

		WebElement testList2 = driver.findElement(By.xpath("//span[contains(., 'Test List')] | //span[contains(., 'Testing Menu')]"));
		testList2.click();
	}
	else{
		shortPause();
		WebElement qa = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//span[contains(., 'Quality Assurance')] | //span[contains(., 'Testing')]")));
		qa.click();
		shortPause();
		
		WebElement testList = driver.findElement(By.xpath("//span[contains(., 'Test List')] | //span[contains(., 'Testing Menu')] | //span[contains(., 'Test Menu')]"));
	
	//Safari javascript workaround for actions
	if(safari){
		jsMouseOver(driver, qa);
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
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

public void goToUserAdminPage(){
	
	driver.get(baseURL + "admin/users");
}


public void goToSettings(){
	
	
	if(mobile){
		WebElement menu = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), 'Menu')]")));
		menu.click();
		WebElement config = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), 'Configure')]")));
		config.click();
		WebElement site = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), 'Site')]")));
		site.click();
		WebElement settings = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), 'Settings')]")));
		settings.click();
	}
	else if(safari)
		driver.get(baseURL + "admin/site/settings");
	else{
		WebElement config = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), 'Configure')]")));
		WebElement site = driver.findElement(By.xpath("//a[contains(text(), 'Site')]"));
		WebElement settings = driver.findElement(By.xpath("//a[contains(text(), 'Settings')]"));
	



	Actions builder = new Actions(driver);
	builder.moveToElement(config)
		.moveToElement(site)
		.moveToElement(settings).click()
		.perform();
	}
	
}

public void goToRelationships(){
	goTo(" Configure", "People", "Relationships");
	
}

//The link is the name of the sub navigation link.  These are the subnav links at the top of admin list pages.
public void navigateSubNav(WebDriver driver, String link){
	WebElement users = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//vii-admin-subnav//a[contains(text(), '" + link +"')]")));
	users.click();	
}

//Go to a menu item that is three categories deep
public static void goTo(String head, String cat, String subCat){
	WebElement nav = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("vii-primary-nav")));
	if(mobile){
		clickable(nav.findElement(By.xpath("//a[contains(., 'Menu')]")), 5).click();
		clickable(nav.findElement(By.xpath("//a[contains(., '" + head + "')]")), 5).click();
		clickable(nav.findElement(By.xpath("//a[contains(., '" + cat + "')]")), 5).click();
		clickable(nav.findElement(By.xpath("//a[contains(., '" + subCat + "')]")), 5).click();

	}
	else{

		clickable(nav.findElement(By.xpath("//a[contains(., '" + head + "')]")), 5).click();
		clickable(nav.findElement(By.xpath("//a[contains(., '" + cat + "')]")), 5).click();
		clickable(nav.findElement(By.xpath("//a[contains(., '" + subCat + "')]")), 5).click();
		
	//Safari javascript workaround for actions
	
	/*if (safari && !ipad){
		
		jsMouseOver(driver, element1);
		jsMouseOver(driver, element2);
		element3.click();
	}

	else if (ie){
		Actions builderie = new Actions(driver);
		builderie.moveToElement(element1).click()
			.moveToElement(element2).click()
			.moveToElement(element3)
			.click().build().perform();	
		
	}
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(element1)
		.moveToElement(element2)
		.moveToElement(element3)
		.click().build().perform();
		
	}
	*/
	}
}


//Go to a menu item that is two categories deep
public static void goTo(String head, String cat){
	WebElement nav = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("vii-primary-nav")));
	if(mobile){
		clickable(nav.findElement(By.xpath("//a[contains(., 'Menu')]")), 5).click();
		clickable(nav.findElement(By.xpath("//a[contains(., '" + head + "')]")), 5).click();
		clickable(nav.findElement(By.xpath("//a[contains(., '" + cat + "')]")), 5).click();

	}
	else{

		clickable(nav.findElement(By.xpath("//a[contains(., '" + head + "')]")), 5).click();
		clickable(nav.findElement(By.xpath("//a[contains(., '" + cat + "')]")), 5).click();
		
	}
}

public static void hoverOver(WebDriver driver, String head, String cat){
	WebElement element1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + head + "')]")));
	WebElement element2 = driver.findElement(By.xpath("//a[contains(text(), '" + cat + "')]"));
	
	//Safari javascript workaround for actions
	if (safari || ie){
		jsMouseOver(driver, element1);
		jsMouseOver(driver, element2);
	}
	
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(element1)
		.moveToElement(element2)
		.perform();
	}
}

public static void hoverOver(WebDriver driver, String head){

	
	WebElement element1 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), '" + head + "')]")));

	
	//Safari javascript workaround for actions
	if (safari){
		jsMouseOver(driver, element1);
	}
	
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(element1)
		.perform();

	}
}

public static void hoverOver(WebDriver driver, WebElement element1){

	
	//Safari javascript workaround for actions
	if (safari){
		jsMouseOver(driver, element1);
	}
	
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(element1)
		.perform();
	}
}

public static void goToDashboard(WebDriver driver, String name){
	//if (name.startsWith("admin") || name.startsWith("Admin"))
		//name = "admin";
	try{
	if(mobile){
		WebElement menu = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), 'Menu')]")));
		menu.click();
		shortPause();	
		WebElement element = driver.findElement(By.xpath("//a[contains(text(), '" + name + "')]"));
		element.click();
		shortPause();
		WebElement element2 = driver.findElement(By.xpath("//a[contains(text(), 'Dashboard')]"));
		element2.click();		
	}
	else{
		WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), '" + name + "')]")));
		WebElement element2 = driver.findElement(By.xpath("//a[contains(text(), 'Dashboard')]"));
		if(safari){
			
			jsMouseOver(driver, element);
			shortPause();
			element2.click();
		}
		else{
		Actions builder = new Actions(driver);
		builder.moveToElement(element)
			.moveToElement(element2)
			.click()
			.perform();
		}
	}
	}
	catch(Exception e){
		driver.get(baseURL + "dashboard");
		e.printStackTrace();
	}
}

public static void goToDashboardFromEdit(WebDriver driver, String name){
	//if (name.startsWith("admin") || name.startsWith("Admin"))
		//name = "admin";
	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(text(), '" + name + "')]")));
	WebElement element2 = driver.findElement(By.xpath("//a[contains(text(), 'Dashboard')]"));
	
	if(safari){
		
		jsMouseOver(driver, element);
		shortPause();
		element2.click();
	}
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(element)
		.click()
		.moveToElement(element2)
		.click()
		.perform();
	}
}
	

//Logs out user of the system via the dropdown at the top of the screen by user's first name
public static void logout(WebDriver driver, String user){
	try{
	

	if(mobile){
		WebElement menu = (new WebDriverWait(driver, 10)).until
				(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Menu')]")));
			menu.click();
			shortPause();
		WebElement name = driver.findElement(By.xpath("//nav//a[contains(text(), '" + user + "')]"));	
			name.click();
			shortPause();
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
			logout.click();
		shortPause();
	}
	
	else{
		
	WebElement logout = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//nav//a[contains(text(), '" + user + "')]")));
	WebElement testList = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
	if(safari){
		
		driver.get(baseURL + "logout");
		shortPause();
	}
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(logout)
		.moveToElement(testList)
		.click()
		.perform();	
	}
	}
	}
	catch(Exception e){

		driver.get(baseURL + "logout");
		shortPause();
		e.printStackTrace();
	}	
	
}

//logs out any user
public void logout(WebDriver driver){
	try{

	
	if(safari){
		driver.get(baseURL + "logout");
		shortPause();
	}
	else if(mobile){
		WebElement menu = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Menu')]")));
		//WebElement header = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
		//(By.xpath("/html/body/div[1]/nav/section/ul[2]/li[3]/ul/li[4]/a")));
		
		menu.click();	
		shortPause();
		WebElement header = driver.findElement(By.xpath("//nav//ul[@class='right']/li[last()]"));
		//WebElement header = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				//(By.cssSelector("ul[class*='right'] li:nth-child(3)")));
		//body > div.contain-to-grid.nav-contain-to-grid > nav > section > ul.right > li:nth-child(3) > a
		header.click();
		shortPause();
		WebElement logout = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
		logout.click();
		shortPause();
		
	}
	else{
		WebElement nav = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("vii-primary-nav")));
		nav.findElement(By.xpath("./span[contains(., '" + currentUser.getFirstName() +"')]")).click();
	WebElement header = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//ul[@class='right']/li[last()]")));
	//WebElement header = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
		//(By.cssSelector("ul[class*='right'] li:nth-child(3)")));
	
	//body > div.contain-to-grid.nav-contain-to-grid > nav > section > ul.right > li:nth-child(3) > ul > li:nth-child(4) > a
	//WebElement logout = driver.findElement(By.xpath("//a[contains(text(), 'Logout')]"));
	WebElement logout = driver.findElement(By.cssSelector("a[href*='logout']"));
	Actions builder = new Actions(driver);
	builder.moveToElement(header)
		.moveToElement(logout)
		.click()
		.perform();	
		shortPause();
	}

	}
	catch(Exception e){

		driver.get(baseURL + "logout");
		loginPageLoaded();
		shortPause();
		e.printStackTrace();
	}
}

//This is the logout to replace the old  -- QA3-update
public void logout(){
	try{

	
	if(safari){
		driver.get(baseURL + "logout");
		shortPause();
	}
	else if(mobile){
		WebElement menu = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Menu')]")));

		
		menu.click();	
		shortPause();
		WebElement nav = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("vii-primary-nav")));
		nav.findElement(By.xpath("./span[contains(., '" + currentUser.getFirstName() +"')]")).click();
		shortPause();
		driver.findElement(By.cssSelector("a[href*='logout']")).click();

		shortPause();
		
	}
	else{
		WebElement nav = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("vii-primary-nav")));
		clickable(nav.findElement(By.xpath("//span[contains(., '" + currentUser.getFirstName() +"')]")), 5).click();
		shortPause();
		WebElement nav2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("vii-primary-nav section[data-nav='dig']")));
		nav2.findElement(By.xpath("//span[contains(., 'Logout')]/..")).click();
	}

	}
	catch(Exception e){

		driver.get(baseURL + "logout");
		loginPageLoaded();
		shortPause();
		e.printStackTrace();
	}
}

//Checks to see if the login page has loaded
public boolean loginPageLoaded(){
	WebElement loginPage = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//button[contains(., 'Login')]")));
	if (loginPage.getText().contains("Login")){
		System.out.println("Login page loaded");
		return true;
	}
	else
		System.out.println("Login page not loaded");
		return false;
}

//Checks to see if the user is already logged in
public boolean userLoggedIn(String user){
	try{
	WebElement nav = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("vii-primary-nav")));
	
	List <WebElement> name = nav.findElements(By.xpath("//span[contains(., '" + user +"')]"));

	if(name.size() != 0)
		if(name.get(0).getText().contains(user))
			return true;
	return false;
	}
	catch(Exception e){
		return false;
	}
		
}	


//Logs out the patient, since their name is Testing, which is already a menu item
public static void logoutPatient(WebDriver driver){
	WebElement header = driver.findElement(By.xpath("html/body/div[1]/nav/section/ul[2]/li[3]/a"));
	WebElement logout = driver.findElement(By.xpath("//a[contains(text(), ' Logout')]"));
	if(safari){
		
		driver.get(baseURL + "logout");
	}
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(header)
		.moveToElement(logout)
		.click()
		.perform();
	}
}


//This is the login to replace the old -- QA3-update
public void login(Users user){
	try{
		if(!ie){
		if(!loginPageLoaded()){
			driver.get(baseURL + "logout");
			loginPageLoaded();
		}
		}
	if(mobile)
		longPause();
	longPause();

	WebElement username = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("input[ng-model='credentials.username']")));
	//body > div.vii-wrapper.ng-scope > div:nth-child(2) > ui-view > div > div > div > div > vii-login-form > div > div > form > div:nth-child(1) > div.medium-9.columns > input
	WebElement pass = driver.findElement(By.cssSelector("input[name='password']"));
	WebElement submit = driver.findElement(By.cssSelector("button[data-button='pill enthusiasm block extra-large']"));
	username.click();
	username.sendKeys(user.getUsername());
	pass.sendKeys(user.getPasswd());
	
	submit.click();	
	currentUser = user;
	System.out.println(user.getFirstName() + " is the current user");
	if(mobile)
		longPause();
	}
	catch(Exception e){
		e.printStackTrace();
		driver.get(baseURL + "logout");

		WebElement username = (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfElementLocated
				(By.name("username")));
		WebElement pass = driver.findElement(By.name("password"));
		WebElement submit = driver.findElement(By.cssSelector("button[data-button='pill enthusiasm block extra-large']"));
		username.sendKeys(user.getUsername());
		pass.sendKeys(user.getPasswd());
		
		submit.click();	
		currentUser = user;
		System.out.println(user.getFirstName() + " is the current user");
	}
	
}

	
//This logs in the user to the dashboard
public void login(WebDriver driver, String username, String passwd){
	try{
		if(!loginPageLoaded()){
			driver.get(baseURL + "logout");
			loginPageLoaded();
		}
	if(mobile)
		longPause();
	longPause();
	WebElement user = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("input[ng-model='credentials.username']")));
	//body > div.vii-wrapper.ng-scope > div:nth-child(2) > ui-view > div > div > div > div > vii-login-form > div > div > form > div:nth-child(1) > div.medium-9.columns > input
	WebElement name = driver.findElement(By.cssSelector("input[name='password']"));
	WebElement submit = driver.findElement(By.cssSelector("button[type='submit']"));
	user.click();
	user.sendKeys(username);
	name.sendKeys(passwd);
	
	submit.click();	
	if(mobile)
		longPause();
	}
	catch(Exception e){
		driver.get(baseURL + "logout");

		WebElement user = (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfElementLocated
				(By.name("username")));
		WebElement name = driver.findElement(By.name("password"));
		WebElement submit = driver.findElement(By.xpath("//*[@id='ngApp']/div/div/div/div/div/div/vii-login-form/div/div/form/button"));
		user.sendKeys(username);
		name.sendKeys(passwd);
		
		submit.click();	
	}
}

//logs in patient, but may no longer be in use
public static void loginPatient(WebDriver driver){

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get(baseURL + "login");
	WebElement username = driver.findElement(By.name("username"));
	WebElement name = driver.findElement(By.name("password"));
	WebElement submit = driver.findElement(By.xpath("//*[@id='ngApp']/div/div/div/div/div/div/vii-login-form/div/div/form/button"));
	username.sendKeys("Testing Patient");
	name.sendKeys("viimedtester");
	submit.click();	
}


/*Opens the tasklist of the logged in user from the dashboard
public static void openTasklist(WebDriver driver){
	try{
		
		if(mobile)	{
			WebElement tasklist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			  (By.xpath("//h5[contains(text(), 'Tasklist')]")));
	
	
		//WebElement tasklist = driver.findElement(By.cssSelector("a[href*='tasklist']"));
		tasklist.click();
		}
	else{
	//WebElement tasklist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated	
			//(By.cssSelector("a[href*='tasklist']")));

	WebElement tasklist = (new WebDriverWait(driver, 30)).until(ExpectedConditions.elementToBeClickable	
		(By.cssSelector("a[href*='tasklist']")));	
		
		
	//WebElement taskNumber = driver.findElement(By.xpath("//span[contains(@class, 'label round alert')]"));
	WebElement taskNumber = driver.findElement(By.cssSelector("span[class='label round alert']"));
	System.out.println(taskNumber.getText());
	tasklist.click();
	}
		
	}
	catch(Exception e){
		driver.get(baseURL + "tasklist");
		e.printStackTrace();
		System.out.println("Task completion didn't redirect to dashboard");
	}	
}
*/

public static void openTasklist(){
	try{
		
		if(mobile)	{
			WebElement tasklist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			  (By.xpath("//h5[contains(text(), 'Tasklist')]")));
			tasklist.click();
		}
		
		
	//WebElement tasklist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			//(By.xpath("//h5[contains(text(), 'Tasklist')]")));
		else{
	WebElement tasklist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated	
			(By.cssSelector("div[class='route-wrapper'] a[href*='tasklist']")));
	WebElement taskNumber = driver.findElement(By.xpath("//span[contains(@class, 'label round alert')]"));
	System.out.println(taskNumber.getText());
	System.out.println(tasklist.getLocation());
	clickElementByLocation(driver, tasklist);
	//tasklist.click();
		}
	}
	catch(Exception e){
		System.out.println("Tasklist icon not found");
		e.printStackTrace();
	}	
}
//Opens the tasklist of the logged in user from the dashboard - For new tasklist icon
public static void openTasklistAlt(WebDriver driver){
	try{
	WebElement tasklist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[contains(text(), 'Tasklist')]")));
	WebElement taskNumber = driver.findElement(By.xpath("//div[contains(@class, 'tasks_indicator num_tasks')]"));
	System.out.println(taskNumber.getText());
	
	tasklist.click();
	}
	catch(Exception e){
		System.out.println("Tasklist not found");
	}	
}

//Checks the number on the dashboard for the number of tasks in the user's tasklist
public static void getTaskNum(WebDriver driver){
	
	List<WebElement> tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
	System.out.println(tasks.size());
}

public static void stopAssist(WebDriver driver){
	WebElement stopAssist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(text(), 'Stop assisting')]")));

	stopAssist.click();

}








//************    Task Methods   **************  //To delete




/*
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
*/








//************    Handling Form Fields  **************




//Looks for the field number by using the field's description text
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


//Sends the desired value to the field input box
public static void setField(WebDriver driver, String fieldId, String input){
	
	try{
	WebElement field = driver.findElement(By.id(fieldId));
	field.sendKeys(input);	
	}
	catch(Exception e){
		System.out.println("Fail - " + fieldId + " field does not exist");
	}
}

// This function takes the field name and input and fills the input
public static void setField(String fieldName, String input){
	
	try{
			
	WebElement field = driver.findElement(By.id(getFieldNum(driver, fieldName)));
	field.sendKeys(input);	
	}
	catch(Exception e){
		System.out.println("Fail - " + fieldName + " field does not exist");
	}
}

/****************************************
/
 *
 *		This group of functions is for the
 *		user profile 
 * 
 * @param section
 * 
 * 
 ****************************************/

//Set temp element that contains the label associated with an input field based on it's section
//Used currently on the user profile page
public void setProfileField(String section){
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//"+section+"-field")));
	tempElement = field;	
}

public void setSubSections(String item, int num){
	List <WebElement> elements = tempElement.findElements(By.xpath(".//label[contains(text(), '" + item + "')]"));
	System.out.println(elements.size());
	tempElement = elements.get(num);	
}


//Set temp element that contains the label associated with an input field
public void setLabelElement(String label){
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//label[contains(text(), '" + label + "')]")));
	tempElement = field;
}

//Set sub label - good for setting phone types address types etc...
public void setSubLabelElement(String subLabel){
	WebElement field = tempElement.findElement(By.xpath("//label[contains(text(), '" + subLabel + "')]"));
	tempElement = field;	
}

//Set input element associated with label
public void setInputByLabel(String section, String item, String subLabel, int num, String input){
	setProfileField(section);
	setSubSections(item, num);
	if(subLabel != null)
		setSubLabelElement(subLabel);	
	tempElement.findElement(By.cssSelector("input")).sendKeys(input);		
}

//Set input element associated with label

public void clickInputByLabel(String section, String item, String subLabel, int num){
	setProfileField(section);
	setSubSections(item, num);
	if(subLabel != null)
		setSubLabelElement(subLabel);	
	tempElement.findElement(By.cssSelector("input")).click();	
}

//Clear input element associated with label
public void clearInputByLabel(String section, String item, String subLabel, int num){
	setProfileField(section);
	setSubSections(item, num);
	if(subLabel != null)
		setSubLabelElement(subLabel);	
	tempElement.findElement(By.cssSelector("input")).clear();	
}

//Click an add or remove button
public void remove(String section, int num){
	
	setProfileField(section);
	List <WebElement> button = tempElement.findElements(By.xpath("//button[contains(text(), 'Remove')]"));
	button.get(num).click();
}

public void add(String section){
	
	setProfileField(section);
	WebElement button = tempElement.findElement(By.xpath("//button[contains(text(), 'Add')]"));
	button.click();
}





public static String getFieldNumAngular(WebDriver driver, String fieldLabel){
	try{
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//*[text()='"+fieldLabel+"']")));
	WebElement parent = field.findElement(By.xpath(".."));
	System.out.println(parent.getAttribute("for"));
	return parent.getAttribute("for");	
	}
	catch (Exception e){
	System.out.println("Field " +fieldLabel + " does not exist - Test Fail");
	return "";
	}

}

//This is for an input field that is not bound to stored data, like a confirm password input
public static String getFieldNumAngularNoBinding(WebDriver driver, String fieldLabel){
	try{
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//*[text()='"+fieldLabel+"']")));
	
	System.out.println(field.getAttribute("for"));
	return field.getAttribute("for");	
	}
	catch (Exception e){
	System.out.println("Field " +fieldLabel + " does not exist - Test Fail");
	return "";
	}

}

public boolean checkSelectOptions(WebDriver driver, String option, String fieldNum){
	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("#"+fieldNum)));
	element.click();
	List<WebElement> selectOptions = driver.findElements(By.cssSelector("option"));
	for(int x=0; x < selectOptions.size(); x++){
		if(selectOptions.get(x).getText().contains(option))
			return true;		
	}
	return false;
}


//
public static WebElement getToggleTitle(WebDriver driver, String fieldLabel){
	
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//*[text()='"+fieldLabel+"']")));
	WebElement parent = field.findElement(By.xpath(".."));
	System.out.println(parent.getAttribute("for"));
	return parent;
}
//Enters a tag value into the tag field
public static void setTag(WebDriver driver, String fieldId, String input){
	
	try{
	WebElement field = driver.findElement(By.xpath("//tags-input[contains(@id, '" + fieldId + "')]//following::input[contains(@class, 'tag-input')]"));
	field.sendKeys(input);	
	}
	catch(Exception e){
		System.out.println("Fail - " + fieldId + " field does not exist");
	}
}

public static void setTag(WebDriver driver, String input){
	
	try{
	WebElement field = driver.findElement(By.cssSelector("input[placeholder='Add a tag']"));
	field.sendKeys(input);	
	}
	catch(Exception e){
		System.out.println("Fail - tag-field not found");
	}
}


//Clears any values already entered into a field
public static void clearField(WebDriver driver, String fieldId){
	
	try{
	WebElement field = driver.findElement(By.id(fieldId));
	field.clear();	
	}
	catch(Exception e){
		System.out.println("Fail - " + fieldId + " field does not exist");
	}
}

//Enters desired text into a text field
public static void setTextBox(WebDriver driver, String fieldId, String input){
	WebElement field = driver.findElement(By.id(fieldId));
	field.sendKeys(input);
	//System.out.println(field.getText());
	//Actions builder = new Actions(driver);
	//builder.moveToElement(field).moveByOffset(10, 10).click().click().sendKeys(input).perform();
	
}

//Enters a vlue into a field based on the field css name value and not by field number
public static void setFieldByName(WebDriver driver, String fieldName, String input){
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.name(fieldName)));
	field.sendKeys(input);	
}


//Finds the field number of a dropdown select field by value
public static void getSelect(WebDriver driver, String fieldNum, String value){
	
	WebElement dropdown = driver.findElement(By.id(fieldNum));
	Select choice = new Select(dropdown);
	choice.selectByValue(value);
}

//Finds the field number of a dropdown select field by visible text
public void getSelectVisText(WebDriver driver, String fieldNum, String value){
	
	WebElement dropdown = driver.findElement(By.id(fieldNum));
	Select choice = new Select(dropdown);
	choice.selectByVisibleText(value);
}
public void setSelectPartText(String field, String text){
	
	WebElement dropdown = driver.findElement(By.id(getFieldNum(driver, field)));
	Select choice = new Select(dropdown); 
	choice.selectByIndex(getSelectByPartialText(dropdown, text));
}

public int getSelectByPartialText(WebElement element, String text){
	List<WebElement> options = element.findElements(By.cssSelector("option"));
	for (int k = 0; k < options.size(); k++){
		if(options.get(k).getText().contains(text)){
			return k;
		}
	}
	return 0;
}

//Finds the field number of a dropdown select field by index
public static void getSelect(WebDriver driver, String fieldNum, int value){
	
	WebElement dropdown = driver.findElement(By.id(fieldNum));
	
	Select choice = new Select(dropdown);
	choice.selectByIndex(value);
}

//Selects a case from the Select case dropdown
public static void setSelectCase(WebDriver driver, String value){
	
	WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("#fieldcaseselect")));
	System.out.println(dropdown.getText());
	Select choice = new Select(dropdown);
	choice.selectByVisibleText(value);
	//choice.selectByValue(value);
}

public static void setSelectCaseValue(WebDriver driver, String value){
	
	WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("#fieldcaseselect")));
	//System.out.println(dropdown.getText());
	Select choice = new Select(dropdown);
	//choice.selectByVisibleText(value);
	choice.selectByValue(value);
}

public static void setSelectCaseDynamic(WebDriver driver, String username, String fullname, String caseType){
	
	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("#fieldcaseselect")));
	if(safari){
		
		jsMouseOver(driver, element);
		shortPause();
		element.click();
		element.sendKeys(username);
	}
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(element).click().sendKeys(username).perform();
	}
	shortPause();
	try{
	WebElement element2 = driver.findElement(By.xpath("//div[contains(text(), '" + fullname + "') and contains(text(), '" + caseType + "')]"));
		if(safari){
			
			jsMouseOver(driver, element);
			shortPause();
			element2.click();
		}
		else
		{	
			Actions builder = new Actions(driver);
			builder.moveToElement(element2).click().perform();
		}
	}
	catch(Exception e){
		System.out.println("Web Element already entered");
	}
}

//Returns the selected value of a select field
public String getSelected(WebDriver driver, String fieldNum){
	
	WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.id(fieldNum)));
	
	Select choice = new Select(dropdown);
	return choice.getFirstSelectedOption().getText();
}
public String getSelectedByElement(WebDriver driver, WebElement element){
	
	Select choice = new Select(element);
	return choice.getFirstSelectedOption().getText();
}


//Used to select choices in a lookahead select field
public static void chooseDynamic(WebDriver driver, String fieldNum, String value){
	WebElement element = driver.findElement(By.id(fieldNum));
	if(safari){
		
		jsMouseOver(driver, element);
		shortPause();
		element.click();
		element.sendKeys(value);
	}
	else{
	Actions builder = new Actions(driver);
	builder.moveToElement(element).click().sendKeys(value).perform();
	}
	shortPause();
	try{
	WebElement element2 = driver.findElement(By.xpath("//div[contains(text(), '" + value + "')]"));
		if(safari){
			
			jsMouseOver(driver, element);
			shortPause();
			element.click();
		}
		else
		{	
			Actions builder = new Actions(driver);
			builder.moveToElement(element2).click().perform();
		}
	}
	catch(Exception e){
		System.out.println("Web Element already entered");
	}
	
}

//Clicks a continue button found in a form
public static void clickContinue(WebDriver driver){
	WebElement continueButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//strong[contains(text(), 'Continue')]")));
	continueButton.click();
}

public static void clickContinue(){
	WebElement continueButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//strong[contains(text(), 'Continue')]")));
	continueButton.click();
}

//Switches a toggle (Switch Round) on and off
public static void clickToggle(WebDriver driver, String fieldName){
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("label[for='" + fieldName + "']")));
	field.click();
}

//Input into a tag field
public static void tagSet(WebDriver driver, String tag){
	WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("input.tag-input.ng-pristine.ng-valid")));
	field.sendKeys(tag);
	
	
}

//Submit for angular form
public void submitAngular(WebDriver driver){
	WebElement element = driver.findElement(By.cssSelector("#button-0"));
	//WebElement element = driver.findElement(By.xpath("//button-field[contains(@button-text, 'Save Changes')]"));

	element.click();
	shortPause();
	}

public void clickSave(WebDriver driver, String text){
    WebElement save = driver.findElement(By.cssSelector("button-field[button-text='"+ text +"'] button"));
    save.click();	
}




public boolean deleteUser(Users user){
	
	boolean userFound = false;
	goTo("Configure", "People", "Users");

	shortPause();
	try{
		
	
		longPause();
		tableVisible();
		pageTitleVisible("Manage Users");
		String [] filters = {user.getFirstName(), "null", "null", "null", "null", "null", "null", "null"};				
		Filters userFilter = new Filters(driver, filters, "users");
		
		//WebElement poo = findListItemByName(username, driver, "user").findElement(By.xpath(".//span[contains(text(), 'Delete')]"));
		longPause();
		
		//update 08/17/2015 for react components

		while(userFound==false){
		WebElement rc= (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("react-component[name='gridtable']")));
		List <WebElement> findUser= rc.findElements(By.xpath("//tr[contains(., '" + user.username + "')]"));
		
		if(findUser.size() == 1){
			//WebElement actionType = findUser.get(0).findElement(By.xpath(".//span[contains(text(),'Delete')]"));
			WebElement actionType = findListItemByName(user.username, driver, "user").findElement(By.xpath(".//span[contains(text(),'Delete')]"));
			System.out.println(actionType.getText());
			userFound=true;
				try{
				actionType.click();
				shortPause();
				driver.switchTo().alert().accept();
				}
				catch (StackOverflowError e)
				{
					shortPause();
					driver.switchTo().alert().accept();
				}
				
			}
		else{
			next(driver);
			shortPause();
		}
		}
		
		

		
	
	//checkAlert(driver);
	shortPause();
	try{
	WebElement userGone = driver.findElement(By.xpath("//tr[td[contains(text(), '" + user.username + "')]"));
	System.out.println("User not deleted");
	}
	catch (Exception e) {
		System.out.println("User deleted");
		shortPause();
		return true;
		}
	}
	catch(Exception e){
		System.out.println("User does not exist");
		e.printStackTrace();
		return false;
	}
	return false;
}

public WebElement findListItemByName(String itemName, WebDriver driver, String listType){

	(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//th[contains(text(), 'Name')]")));
	List<WebElement> total = driver.findElements(By.tagName("td"));
	System.out.println(total.size());
	for (int k=0; k < total.size(); k++){
		if (total.get(k).getText().equalsIgnoreCase(itemName)){
			System.out.println(k);
			System.out.println(total.get(k).getText());
			if(listType.toLowerCase().contains("user"))
				return total.get(k+7);
			if(listType.toLowerCase().contains("role"))
				return total.get(k+1);
		}

	}
	if(next(driver)){
		//System.out.println(findUser(username, driver));
		return findListItemByName(itemName, driver, "user");
		
	}
	return null;
	
}



public boolean next(WebDriver driver){

	List<WebElement> next = driver.findElements(By.xpath("//a[contains(text(), 'Next')]"));
	if(next.size() == 0)
		return false;
	else{
		next.get(0).click();
		return true;
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
	
	if(mobile){
		WebElement record = driver.findElement(By.xpath("//button-field[contains[text(), 'Record Video"));
		record.click();
		
	}
	WebElement element = driver.findElement(By.xpath("//vii-recorder"));
	scrollElementIntoView(driver, element);
	System.out.println(element.getLocation());
	int width = element.getSize().getWidth();
	int height = element.getSize().getHeight();
	
	if(safari){
		int x = (int)(element.getSize().width * .5);
		int y = (int)(element.getSize().height * .59);
		int y2 = (int)(element.getSize().height * .90);
	    int a = driver.manage().window().getPosition().x;    
	    int b = driver.manage().window().getPosition().y+73;
		mouseOffset(driver, element, x+a, y+b);
		mouseOffset(driver, element, x+a, y+b);
		mouseOffset(driver, element, x+a, y+b);
		mouseClick();
		mouseClick();
		shortPause();
		mouseOffset(driver, element, x+a, y2+b);

		longPause();
		longPause();
		longPause();
		mouseOffset(driver, element, x+a, y2+b);		
		
		
	}
	
	else{
	Actions newAction = new Actions(driver);
	if(element.getSize().getWidth() < 890)
	newAction.moveToElement(element, (int)(width * .4) , (int)(height * 0.60)).click().click().click().click().perform();
	else
	newAction.moveToElement(element, (int)(width * .4), (int)(height * 0.60)).click().click().click().click().perform();
	
	System.out.println(element.getSize().getHeight());
	System.out.println(element.getSize().getWidth());
	shortPause();
	
	if(element.getSize().getWidth()<890)
		newAction.moveToElement(element, (635), (int)(height - 5)).click().perform();
	else
		newAction.moveToElement(element, (635), (int)(height - 5)).click().perform();
	
	longPause();
	longPause();
	longPause();
	
	if(element.getSize().getWidth()<890)
		newAction.moveToElement(element, (635), (int)(height - 5)).click().perform();
	else
		newAction.moveToElement(element, (635), (int)(height - 5)).click().perform();
	}


}


public static void playVideo(WebDriver driver, String title){
	try{
	WebElement element = driver.findElement(By.xpath("//h4[contains(text(), '"+ title + "')]"));
	System.out.println(element.getLocation());
	int width = element.getSize().getWidth();
	int height = element.getSize().getHeight();
	if (safari){
	    int a = driver.manage().window().getPosition().x;    
	    int b = driver.manage().window().getPosition().y+73;
	    longPause();
		mouseOffset(driver, element, 100+a, 100+b);
		mouseOffset(driver, element, 100+a, 100+b);
		mouseOffset(driver, element, 100+a, 100+b);
		longPause();
		longPause();
		
	}
	else{
	Actions newAction = new Actions(driver);
	newAction.moveToElement(element, 100, 100).click().perform();
	longPause();
	longPause();
	}
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
	newAction.moveToElement(element, (width / 2) - 40, (int)(height * 0.60)).click().click().click().click().click().perform();
	else
		newAction.moveToElement(element, (width / 2), (int)(height * 0.59)).click().click().click().click().click().perform();

	
	System.out.println(element.getSize().getHeight());
	System.out.println(element.getSize().getWidth());
	shortPause();
	
	if(element.getSize().getWidth()<890)
	newAction.moveToElement(element, (width) - 5, (int)(height - 5)).click().perform();
	else
	newAction.moveToElement(element, width / 2, (int)(height * 0.90)).click().perform();
	
	longPause();
	longPause();
	longPause();
	
	if(element.getSize().getWidth()<890)
		newAction.moveToElement(element, width / 2 - 5, (int)(height - 5)).click().perform();
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


public static void mouseOffset(WebDriver driver, WebElement element, int x, int y){
	try{
	Robot robot = new Robot();
	shortPause();
    System.out.println(element.getLocation().x+x);
    System.out.println((element.getLocation().y+y));
	robot.mouseMove((element.getLocation().x + x),
		(element.getLocation().y + y));
	robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	robot.mouseRelease(InputEvent.BUTTON1_MASK);



	}
	catch(Exception e){
		System.out.println("MouseMove didn't work");
	}
}


public static void mouseClick(){
	try{
	Robot robot = new Robot();
	robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}
	catch(Exception e){
		System.out.println("MouseClick didn't work");
	}
}


public static void clickElement(WebDriver driver, WebElement element){
	
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
}

public static void clickElementByLocation(WebDriver driver, WebElement element){
	
	((JavascriptExecutor)driver).executeScript("window.scrollTo(0,"+element.getLocation().x+")");	 
	 
	// Click the element
	 
	((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);

}

public static void scrollElementIntoView(WebDriver driver, WebElement element) {
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
}

public boolean isSafari(){
	return safari;
}


public boolean accessContent(WebDriver driver){
	try{
	WebElement permission = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//body")));
	System.out.println(permission.getText());
	if(permission.getText().contains("You don't have permission to access this content"))
		return true;
	else
		return false;
	}
	catch(Exception e){
		System.out.println("Fail, the incorrect user has access");
		return false;
	}
}

public void printResult(String test, WriteToFile data, String result){
	System.out.println("Test " + test + " " + result);
	try{
	data.writeToFile("Test " + test + " " + result);
	}
	catch(Exception e){
		System.out.println("Problem writing to file");
	}
}

public void printTitle(String title, WriteToFile data){
	try{
	data.writeToFile("\n" + title + "\n");
	}
	catch(Exception e){
		System.out.println("Problem writing to file");
	}
	
}

public void findTitle(WebDriver driver, String title){
	WebElement permission = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//h2[contains(text(), '" + title +"')]")));
	
}

public String getPageText(WebDriver driver){
	WebElement pageText = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("body")));
	return pageText.getText();
}

public boolean checkIfOnDashboard(WebDriver driver){
	List <WebElement> dashboard = driver.findElements(By.cssSelector("p.lead"));
	for (int x = 0; x < dashboard.size(); x++){
		if (dashboard.get(x).getText().contains("Welcome to your dashboard"));
			return true;
	}
	return false;
		
}

public void dashboardVisible(){
	try{
		shortPause();
	WebElement pageText = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
	}
	catch(Exception e){
		System.out.println("Dashboard not found");
	}	
}


//Checks the body of the current page for specifc text
public boolean pageContainsText(WebDriver driver, String text){
	
	WebElement body = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("body")));
	if (body.getText().contains(text))
		return true;
	else return false;
	
}

//Returns the text found in the page footer
public String getFooterText(WebDriver driver){
	
	WebElement footer = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("footer")));
	return footer.getText();
	
}


public void selectAllTable(WebDriver driver){
	
	List <WebElement> selectAll = driver.findElements(By.cssSelector("th > input"));
	for (int x = 0; x < selectAll.size(); x ++){
		if(selectAll.get(x).getAttribute("type").equalsIgnoreCase("checkbox"))
			selectAll.get(x).click();
		
	}
}

public void toolSelect(WebDriver driver, String tool){
	
	WebElement tools = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("select[name='tool']")));
	Select selection = new Select(tools);
	selection.selectByVisibleText(tool);
}


public boolean clickAnyButton(WebDriver driver, String button, int index){
	List <WebElement> buttons = driver.findElements(By.cssSelector("button"));
	int count = 0;
	for(int x = 0; x < buttons.size(); x++){
		if(buttons.get(x).getText().equalsIgnoreCase(button)){
			count++;
			if(buttons.get(x).getText().equalsIgnoreCase(button) && count == index){
			buttons.get(x).click();
			return true;
			}
		}
		
	}
	return false;
}




public int countTableResults(WebDriver driver){
	int count = 0;

	List<WebElement> mediaList = driver.findElements(By.cssSelector("td"));
	System.out.println(mediaList.size());
	for(int x = 0; x< mediaList.size(); x++){
    	if (mediaList.get(x).getText().contains("View Update Delete")){
    		count++;
    	}
    	}
	return count;
	
}

public int findHeaders(WebDriver driver, String header, String text){
	List<WebElement> butt = driver.findElements(By.xpath("//"+header+"[contains(text(), '"+text+"')]"));
	return butt.size();
	
}

public void clickLink(WebDriver driver, String text){
	
	WebElement link = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(text()='"+ text + "')]")));
	link.click();
}

public void clickLinkAttribute(WebDriver driver, String text, String attribute){
	WebElement link = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(@"+ attribute + ",'"+ text + "')]")));
	link.click();
	
}

public void clickLinkByURL(WebDriver driver, String text){
	if(mobile){
		WebElement link = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(@href, '"+ text + "')]")));
		link.click();
	}
	else{
	WebElement link = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("a[href*='"+ text + "'")));
	link.click();
	}
}

public String returnLink(WebDriver driver, String text){
	WebElement link = driver.findElement(By.xpath("//a[text()='" + text + "']"));
	return link.getAttribute("href");
}

//Check to see if a particular field is visible
public boolean fieldVisible(WebDriver driver, String field){
	List <WebElement> fields = driver.findElements(By.xpath("//label[contains(., '" + field + "')]"));
	System.out.println(fields.size());
	if(fields.size() > 0)
		return true;
	else
		return false;
	
}

public int getElementLocation(WebElement element, String coord){
	
	if (coord.equalsIgnoreCase("x"))
			return element.getLocation().getX();
	
	else if (coord.equalsIgnoreCase("y"))
		return element.getLocation().getY();
	
	System.out.println("x or y wasn't sent to getElementLocation");
	return 0;
	
}

//By passing a webelement and time in seconds the driver will wait that much time for the element to be clickable
//This is useful for load times and elements hidden or temporarily covered

public static WebElement clickable(WebElement we, int time){
	
	WebElement element = (new WebDriverWait(driver, time)).until(ExpectedConditions.elementToBeClickable(we));
	return element;
}




}







