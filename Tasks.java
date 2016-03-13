package test1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Tasks extends seleniumTest{
	

	boolean highPriority = false;
	WebElement taskDiv;
	WebElement taskStatus;
	WebElement currentTask;
	String currentStatus;
	String statusColor;
	String statusName;
	String currentPriority;
	String urlTasklist;
	List<WebElement> currentTasks = new ArrayList<WebElement>();
	List<WebElement> tasklists = new ArrayList<WebElement>();
	

		
//		<<  Constructors  >>

public Tasks(WebDriver driver, boolean open){	
	try{
	/*WebElement pageLoad = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//div[contains(@class, 'event-block task')]")));
	List<WebElement> tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
	*/
	int list;
	if(open)list=0;
	else list = 1;
	WebElement pageLoad = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("a[class='activity-title']")));

	
	setCurrentTasks(list);
	urlTasklist = driver.getCurrentUrl();
	System.out.println(urlTasklist);
	}
	catch(Exception e){
		System.out.println("User has no tasks");
	}
}

public void setTaskLists(){
	try{
	List<WebElement> tempList = driver.findElements(By.cssSelector("vii-flex-table-with-status"));
	System.out.println(tempList.size());
	tasklists = new ArrayList<WebElement>(tempList);
	System.out.println(tasklists.size());

	}
	catch(Exception e){
		e.printStackTrace();
	}
}
public void setCurrentTasks(int list){
	try{
	setTaskLists();
	currentTasks = tasklists.get(list).findElements(By.cssSelector("a[class='activity-title']"));
	System.out.println(currentTasks.size());
	
	for (int k = 0; k < currentTasks.size(); k++){
		
		currentTask = currentTasks.get(k);
		System.out.println(currentTasks.get(k).getText());
		setTaskDiv();
		setStatus();

		/*WebElement task = tasks.get(k);
		System.out.println(task.getCssValue("background-color"));
		System.out.println(task.getAttribute("class"));
		hasHighPriority(driver, task);
		hasNormalPriority(driver, task);*/
	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

// Set the open boolean to 0 if the tasklist you're using is for open tasks, use 1 for closed tasks
public void setTaskByTitle (String title, boolean open){
	try{
	if(open){
		setTaskLists();
		WebElement task = tasklists.get(0).findElement
			(By.xpath("//a[contains(@class, 'activity-title') and contains(., '" +title + "')]"));
		currentTask =task;
	}
	else{	
		WebElement task = tasklists.get(1).findElement
			(By.xpath("//a[contains(@class, 'activity-title') and contains(., '" +title + "')]"));
		currentTask =task;
	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

//Set the open boolean to 0 if the tasklist you're using is for open tasks, use 1 for closed tasks
public void setTasksByTitle (String title, boolean open){
	try{
	if(open){
		currentTasks = tasklists.get(0).findElements
			(By.xpath("//a[contains(@class, 'activity-title') and contains(., '" +title + "')]"));
	}
	else{	
		currentTasks = tasklists.get(1).findElements
			(By.xpath("//a[contains(@class, 'activity-title') and contains(., '" +title + "')]"));

	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

//Set the open boolean to 0 if the tasklist you're using is for open tasks, use 1 for closed tasks
public void setTasks (String title, boolean open){
	try{
	if(open){
		currentTasks = tasklists.get(0).findElements
			(By.cssSelector("div.flex-row"));
	}
	else{	
		currentTasks = tasklists.get(1).findElements
			(By.cssSelector("div.flex-row"));

	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

public Tasks(WebDriver driver, String title, boolean open){	
	 /*  
	WebElement task = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
						(By.xpath("//div[contains(@class, 'event-block task') and contains(., '" +title + "')]")));
		System.out.println(task.getText());
		System.out.println(task.getCssValue("background-color"));
		System.out.println(task.getAttribute("class"));
		hasHighPriority(driver, task);
		hasNormalPriority(driver, task);
		currentTask =task;
		*/
	setTaskLists();
	setTaskByTitle(title, open);
	setTaskDiv();
	setStatus();
	
}

public void checkTask(WebDriver driver, String title, boolean open){
	/*
		WebElement task = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//div[contains(@class, 'event-block task') and contains(., '" +title + "')]")));
		System.out.println(task.getText());
		System.out.println(task.getCssValue("background-color"));
		System.out.println(task.getAttribute("class"));
		hasHighPriority(driver, task);
		hasNormalPriority(driver, task);
	
		currentTask = task;	
		*/
		setTaskLists();
		setTaskByTitle(title, open);
		setTaskDiv();
		setStatus();
}

public void openTask(WebDriver driver){
	try{
	currentTask.click();
	WebElement status = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//span[contains(@ng-bind-html, 'queueStatus:data.statuses')]")));
	setStatus(status);
	if(mobile)
		shortPause();
	try{
		WebElement priority = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//span[contains(@ng-bind-html, 'queuePriority')]")));
		setPriority(priority);
	}
	catch(Exception a){
		System.out.println("No priority level displayed");
		currentPriority = "Normal";
	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

public String getText(WebDriver driver){
	
	WebElement bodyText = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("div.event-series.ng-scope")));
	return bodyText.getText();
	}

public void setStatus(WebElement element){
	currentStatus = element.getText();
}

public void setPriority(WebElement element){
	currentPriority = element.getText();
}

public String getCurrentStatus(){
	return currentStatus;
}

public String getCurrentPriority(){
	return currentPriority;
}


public static int getNumTasks(WebDriver driver){

	List<WebElement> tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
	return tasks.size();		
}
	
public void hasHighPriority(WebDriver driver, WebElement task){
	if (task.getCssValue("background-color").equals("rgba(189, 30, 38, 1)") || 
			task.getCssValue("background-color").equals("rgba(190, 33, 53, 1)") || 
			task.getCssValue("background-color").equals("rgba(211, 34, 42, 1)") || 
			task.getCssValue("background-color").equals("rgba(210, 73, 42, 1)") || 
			task.getCssValue("background-color").equals("rgba(240, 138, 36, 1)") ||
			task.getCssValue("background-color").equals("rgba(219, 57, 54, 1)") ||
			task.getCssValue("background-color").equals("rgba(234, 125, 16, 1)") &&  //redcross theme
			task.getAttribute("class").contains("high-priority-task")){

		setHighPriority();
	}		
}

public void setTaskDiv(){
	try{
	taskDiv = currentTask.findElement(By.xpath("../../../.."));
	System.out.println(taskDiv.getAttribute("class"));
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

public WebElement getTaskDiv(){
	
	return taskDiv; 
}

public void setStatus(){
	try{
	taskStatus = getTaskDiv().findElement(By.xpath(".//div[contains(@data-status-indicator,'tooltip')]"));
	
	
	if(taskStatus.getAttribute("data-status-indicator").contains("blame hollow tooltip"))
		statusName = "late";
	if(taskStatus.getAttribute("data-status-indicator").contains("content hollow tooltip"))
		statusName = "pending";
	if(taskStatus.getAttribute("data-status-indicator").contains("indifferent tooltip"))
		statusName = "canceled";
	if(taskStatus.getAttribute("data-status-indicator").contains("enthusiasm tooltip"))
		statusName = "completed";
	if(taskStatus.getAttribute("data-status-indicator").contains("anger hollow tooltip"))
		statusName = "important";
	
	statusColor = taskStatus.getCssValue("border-color");	
	System.out.println(statusColor);

	System.out.println(statusName);
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

public boolean checkStatusNameColor(String status){
	try{
	boolean statusPass = false;
	switch(status){
		case "late": 
			if (statusName.contentEquals("late") && statusColor.equals("rgb(242, 165, 0)"))
				statusPass = true;
			break;
		case "pending":
			if (statusName.contentEquals("pending") && statusColor.equals("rgb(63, 117, 207)"))
				statusPass = true;
			break;
		case "canceled":
			if (statusName.contentEquals("canceled") && statusColor.equals("rgb(151, 151, 151)"))
				statusPass = true;
			break;
		case "completed":
			if (statusName.contentEquals("completed") && statusColor.equals("rgb(83, 167, 40)"))
				statusPass = true;
			break;	
		case "important":
			if (statusName.contentEquals("important") && statusColor.equals("rgb(239, 78, 58)"))
				statusPass = true;
			break;		
	}
	return statusPass;	
	}
	catch(Exception e){
		e.printStackTrace();
		return false;
	}
					
}

public void hasNormalPriority(WebDriver driver, WebElement task){
	if ((task.getCssValue("background-color").equals("rgba(255, 255, 255, 1)") || 
			task.getCssValue("background-color").equals("rgba(244, 244, 244, 1)")) && //redcross theme
			task.getAttribute("class").equals("event-block task")){
	
		setNormalPriority();
	}
}


public void deleteTasks(WebDriver driver){
	try{
	setCurrentTasks(0);
	int count = currentTasks.size();
	if(currentTasks.size() != 0){
		
	while (count > 0){
		
		WebElement task = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("a[class='activity-title']")));
		task.click();
		shortPause();
				
	
		WebElement taskChoice = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(@class, 'button small expand')]")));
		
	
		if (taskChoice.getText().contains("Passed") || taskChoice.getText().contains("passed"))
			taskChoice.click();
		else if (taskChoice.getText().equals("Failed"))
			taskChoice.click();
		else if
			(taskChoice.getText().equals("Checkout Task") || taskChoice.getText().equals("Checkout Activity")){
			taskChoice.click();
			WebElement passed = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//a[contains(text(), 'Passed')]")));
			passed.click();
		}
		else if
			(taskChoice.getText().contains("Reply to Sender")){
			taskChoice.click();
			submit();
			openTasklist();
		}
		
		longPause();
		count--;
		}
	driver.get(baseURL + "tasklist" );
	shortPause();
	
	deleteTasks(driver);
	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
}


// Delete all tasks in the queue if the queue was accessed from the Manage Users list page
public void deleteTasksAdmin(WebDriver driver){
	//List<WebElement> tasks = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
	try{
	setCurrentTasks(0);
	int count = currentTasks.size();
	if(count!=0){
	while (count > 0){
		shortPause();
		WebElement task = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("a[class='activity-title']")));
				
		task.click();
		updateTaskCancelled(driver);
		shortPause();
		alertPresent(driver, "The task was successfully updated");
		//backButton(driver);
		driver.get(urlTasklist);
		shortPause();
		count--;
		
		}
	if(count==0){
		driver.get(urlTasklist);
		shortPause();
		deleteTasksAdmin(driver);
	}
	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
	
}


public void setHighPriority(){
		highPriority = true;
	}

public void setNormalPriority(){
		highPriority = false;
	}


public boolean getPriority(){
		return highPriority;
	}



public boolean taskExists(String title, boolean open){
	try{
	//WebElement destinationQueue = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			//(By.xpath("//strong[contains(text(), 'destinationqueue')]")));
	
	setTaskByTitle(title, open);

	System.out.println(currentTask.getText());
	return true;
	
	}
	catch (Exception e) {
	    e.printStackTrace();
	    return false;
		}
	
}


public boolean checkTasks(WebDriver driver, String queueItem, boolean open){
	
	try{
	//List<WebElement> tasks = driver.findElements(By.xpath("//strong[contains(text(), '" + queueItem + "')]"));
	currentTasks.clear();
	setTasksByTitle(queueItem, open);

	int checkout = 0;
	int noCheckout = 0;
	int reply = 0;
	
	if(queueItem.contains("destinationqueue")){
	for (int k = 0; k < 3; k++){
		WebElement task = currentTasks.get(k);
	
		System.out.println(task.getText());
		if (task.getText().equals("destinationqueue=QAGroup, no checkout") && noCheckout != 1)
			noCheckout++;
		//setFilter("Checked out");
		//shortPause();
		else if (task.getText().equals("destinationqueue=QAGroup, with checkout") && checkout != 1)
			checkout++;
		//setFilter("No Filter");
		//shortPause();
		else if (task.getText().equals("destinationqueue=replytosender") && reply != 1)
			reply++;
		
		else 
			return false;
		
	}
	}
	else if(queueItem.contains("Uncomplete")){
		if(currentTasks.size()==0)
			return false;
	}
	return true;
	}
	catch(Exception e){
		e.printStackTrace();
		return false;
	}
	
}


public void reply(WebDriver driver){
	//WebElement taskDestQueue = driver.findElement(By.xpath("//strong[contains(text(), 'destinationqueue=testingpatient')]"));
	
	WebElement taskDestQueue = driver.findElement(By.xpath("//span[contains(text(), 'destinationqueue=testingpatient')]"));
	taskDestQueue.click();
	shortPause();
	WebElement reply = driver.findElement(By.xpath("//a[contains(text(), 'Reply to Sender')]"));
	reply.click();
	shortPause();
	submit();
}

public void openTask(WebDriver driver, String task){
	try{
	//WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
	//		(By.xpath("//span[@class='title' and contains(text(), '" + task + "')]")));
	setTaskLists();
	WebElement element = tasklists.get(0).findElement(By.xpath("//a[contains(@class, 'activity-title') and contains(., '" +task + "')]"));	
	element.click();
	if(mobile)
		shortPause();
	}
	catch(Exception e){
		e.printStackTrace();
		System.out.println("Task " + task + " does not exist");
	}
}

public void openTaskWithDates(WebDriver driver, String today, String tomorrow){
	try{
		//WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				//(By.cssSelector("//div[@class='event-block task' and contains(., '" + today +"') and contains(., '"+ tomorrow +"')]")));
		
		//element.click();
		WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//em[contains(., '" + today +"') and contains(., '"+ tomorrow +"')]")));
	if(safari)
	element.findElement(By.cssSelector("span")).click();
	else{
	Actions build = new Actions(driver);
	build.moveToElement(element).click().perform();
	}
	}
	catch(Exception e){
		System.out.println("Task with both dates does not exist");
		e.printStackTrace();
	}
}

public boolean hasTasks(WebDriver driver, String[] tasks){
	String elementText;
	boolean has = false;
	List<WebElement> elements = driver.findElements(By.xpath("//div[contains(@class, 'event-block task')]"));
	for (int k = 0; k < elements.size(); k++){
		elementText = elements.get(k).getText();
		for(int j = 0; j < tasks.length; j++){
			if (elementText.contains(tasks[j])){
					has = true;
					break;
			}
			else 
				has = false;
		}
	}
	return has;
}

public void clickTaskButton(WebDriver driver){
	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(@class, 'button small expand')]")));
	System.out.println(element.getText());
	element.click();
	if(safari)
		shortPause();
	if(mobile)
		shortPause();
}


public void taskButton(WebDriver driver, String buttText){
	WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(text(), '"+ buttText +"')]")));
	System.out.println(element.getText());
	element.click();
	if(safari)
		shortPause();
	if(mobile)
		shortPause();
	
}

public boolean hasActivationInfo(WebDriver driver, String info, Users patientInherit){
	//int x = info.indexOf("Patient ID");
	//String patientID = info.substring(x+10, x+19);
	//System.out.println(patientID);
	
	if(info.contains("Patient ID") && info.contains(patientInherit.getPatientId()) &&
			info.contains("First name") && info.contains(patientInherit.getFirstName()) &&
			info.contains("Last name") && info.contains(patientInherit.getLastName()) &&
			info.contains("Email") && info.contains(patientInherit.getEmail()) &&
			info.contains("Username") && info.contains(patientInherit.getUsername()) &&
			info.contains("Patient Activation") && info.contains("1. Activate now 2. Create password for patient 3. Have patient login immediately") &&
			info.contains("Case Type") && info.contains("QA") &&
			info.contains("QA Testing Provider") && info.contains("Provider, Testing")
			)
		return true;
	else{
		System.out.println("Something wrong jere");
		return false;
	}
}

public boolean hasActivationInfoPatient(WebDriver driver, String info, Users patientInherit){
	//int x = info.indexOf("Patient ID");
	//String patientID = info.substring(x+10, x+19);
	//System.out.println(patientID);
	
	if( 	info.contains("First name") && info.contains(patientInherit.getFirstName()) &&
			info.contains("Last name") && info.contains(patientInherit.getLastName()) &&
			info.contains("Email") && info.contains(patientInherit.getEmail()) &&
			info.contains("Username") && info.contains(patientInherit.getUsername()) &&
			info.contains("Case Type") && info.contains("QA") &&
			info.contains("QA Testing Provider") && info.contains("Testing Provider")
			)
		return true;
	else{
		System.out.println("Something wrong jere");
		return false;
	}
}

//Select dropdown value for Task Status Value, currently 0=Pending 1=Late 2=In Progress 3=Cancelled 4=Elevated

public void updateTask(WebDriver driver, int value){
	WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("select[ng-model*='task.data.status']")));
	Select choice = new Select(dropdown);
	choice.selectByIndex(value);
	WebElement updateButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//button[contains(text(), 'Update Task') or contains(text(), 'Update Activity')]")));
	updateButton.click();
}

public void updateTaskCancelled(WebDriver driver){
	WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("select.ng-pristine.ng-valid")));
	Select choice = new Select(dropdown);
	choice.selectByVisibleText("Canceled");
	WebElement updateButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//button[contains(text(), 'Update')]")));
	updateButton.click();
}

public void backButton(WebDriver driver){
	WebElement back = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[@class='sub-nav-button']")));
	back.click();
}

/****************************
 * 
 *    Filters and Search
 * 
 ***************************/


public void setFilter(String value){
	
	WebElement dropdown = driver.findElement(By.cssSelector("select[vii-run-on-change='applyFilters']"));
	Select choice = new Select(dropdown);
	choice.selectByVisibleText(value);
}

}
