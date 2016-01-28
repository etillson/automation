package test1;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MediaManager extends seleniumTest{
	
	public WebDriver driver;

	
	public MediaManager(WebDriver driverCurrent, WriteToFile data){
	    
		driver = driverCurrent;
		
		String test;    
	    String field;
	    String fieldNum;
	    String url;
	    String mediaName;
	    String file;
	    String date;
	    String date2;
	    
	    int choice;
	    boolean pass;
	    
	    //Setup users for tests
	    Users tester = new Users(driver, "Testing Provider");
	    Users admin = new Users(driver, "Admin");
	    Users testPatient = new Users(driver, "Testing Patient");
	    
	    printTitle("Media Manager", data);
	    CalendarDate today = new CalendarDate();
	    today.setDate(driver, "M/d/yyyy");
	    date = today.getToday();
	    today.setDate(driver, "MMMMM.dd.yyyy hh.mm a");
	    date2 = today.getToday();
	    
/*********************************************** 
*		
*		Login to QA
*
*
**********************************************/
    
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://newqa.viinetwork.net/logout");
	shortPause();
	driver.manage().window().setSize(new Dimension(1920, 1080));

	    
	    
/*******************************************
 * 
 *     Add Media - Image with tags
 *     
 *     
 *******************************************/
	test = "Add Media - Image with tags";
	
	try{
		
	login(driver, admin.getUsername(), admin.getPasswd());
	 
	mediaName = "001 Me";
    goTo("Configure", "Content", "Media");
    file = "/Users/erictillson/Documents/automation/photo2.jpg";
    navigateMediaFile("Add Media");
    longPause();
    addMedia(file);

    setField(driver, "title", mediaName);
    setField(driver, "description", "This is a picture of me");
    getSelectVisText(driver, "category", "Images");
    setTag(driver, "one two three four ");
    longPause();
    clickSave();
    if(alertPresent(driver, "The information has been saved"))
    	System.out.println("alert present");
    tableVisible();
    List<WebElement> tags = driver.findElements(By.cssSelector("td"));
    for(int x = 0; x< tags.size(); x++){
    	
    	if (tags.get(x).getText().contains(mediaName)){
    		System.out.println(tags.get(x+1).getText());
    		System.out.println(tags.get(x+2).getText());
    		System.out.println(tags.get(x+3).getText());
    		System.out.println(date);
    		if (tags.get(x+2).getText().contains("one, two, three, four") &&
    				tags.get(x+1).getText().contains("Images") &&
    				tags.get(x+3).getText().contains(date))
    			printResult(test, data, "passed");
    		else
    			printResult(test, data, "failed");
    			break;
    		}
    	}

    logout(driver);
	}
	catch(Exception e){
		
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
	}

/*******************************************
 * 
 *     View and Edit Media
 *     
 *     
 *******************************************/	
	test = "View and Edit Media";
	
	try{
		
	login(driver, admin.getUsername(), admin.getPasswd());
	 
	mediaName = "001 Me";
    goTo("Configure", "Content", "Media");
    file = "/Users/erictillson/Documents/automation/holome.jpg";
    tableVisible();
    viewMedia(mediaName);
    waitImageLoad();
    captureScreen(driver, "ViewMedia" + date2);
    navigateMediaFile("Edit Media");
    clickReplace();
    addMedia(file);
    longPause();
    clickSave();
    if(alertPresent(driver, "The information has been saved"))
    	System.out.println("alert present");
    tableVisible();
    viewMedia(mediaName);
    waitImageLoad();
    captureScreen(driver, "ViewMediaReplaced" + date2);
    navigateMediaFile("View All Media");
    tableVisible();
    printResult(test, data, "passed if the two screenshots are different "
    		+ "(ViewMedia " + date +" and  ViewMediaReplaced " + date +")");
    shortPause();
    deleteMedia(mediaName);
    logout(driver);
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
	}
	
	
/*******************************************
 * 
 *     Tags - Delete Tags
 *     
 *     
 *******************************************/
	
	test = "Delete Tags";
	
	login(driver, admin.getUsername(), admin.getPasswd());
	 
    goTo("Configure", "Content", "Tags");
    shortPause();
    if(deleteTag(driver, "four") &&
    	deleteTag(driver, "one") &&
    	deleteTag(driver, "three") &&
    	deleteTag(driver, "two"))
    	printResult(test, data, "passed");
    else
    	printResult(test, data, "failed");

    
/*******************************************
 * 
 *     Add Media - Video
 *     
 *     
 *******************************************/
	test = "Add Media - Video";
	
	try{
		
	login(driver, admin.getUsername(), admin.getPasswd());
	 
	mediaName = "001 Video";
    goTo("Configure", "Content", "Media");
    file = "/Users/erictillson/Documents/automation/Video.mp4";
    navigateMediaFile("Add Media");
    longPause();
    addMedia(file);

    setField(driver, "title", mediaName);
    setField(driver, "description", "This is a video on medication");
    getSelectVisText(driver, "category", "Videos");

    longPause();
    clickSave();
    if(alertPresent(driver, "The information has been saved"))
    	System.out.println("alert present");
    tableVisible();
    List<WebElement> tags = driver.findElements(By.cssSelector("td"));
    for(int x = 0; x< tags.size(); x++){
    	
    	if (tags.get(x).getText().contains(mediaName)){
    		System.out.println(tags.get(x+1).getText());
    		System.out.println(tags.get(x+2).getText());
    		System.out.println(tags.get(x+3).getText());
    		System.out.println(date);
    		if (tags.get(x+1).getText().contains("Videos") &&
    				tags.get(x+3).getText().contains(date))
    			printResult(test, data, "passed");
    		else
    			printResult(test, data, "failed");
    		}
    	}
    deleteMedia(mediaName);
    logout(driver);
	}
	catch(Exception e){
		
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
	}
    
/*******************************************
 * 
 *     Add Media - Document
 *     
 *     
 *******************************************/
	test = "Add Media - Document";
	
	try{
		
	login(driver, admin.getUsername(), admin.getPasswd());
	 
	mediaName = "001 Document";
    goTo("Configure", "Content", "Media");
    file = "/Users/erictillson/Documents/automation/test.pdf";
    navigateMediaFile("Add Media");
    longPause();
    addMedia(file);

    setField(driver, "title", mediaName);
    setField(driver, "description", "This is a pdf");
    getSelectVisText(driver, "category", "-- General");

    longPause();
    clickSave();
    if(alertPresent(driver, "The information has been saved"))
    	System.out.println("alert present");
    tableVisible();
    List<WebElement> tags = driver.findElements(By.cssSelector("td"));
    for(int x = 0; x< tags.size(); x++){
    	
    	if (tags.get(x).getText().contains(mediaName)){
    		System.out.println(tags.get(x+1).getText());
    		System.out.println(tags.get(x+2).getText());
    		System.out.println(tags.get(x+3).getText());
    		System.out.println(date);
    		if (tags.get(x+1).getText().contains("General") &&
    				tags.get(x+3).getText().contains(date))
    			printResult(test, data, "passed");
    		else
    			printResult(test, data, "failed");
    		}
    	}
    deleteMedia(mediaName);
    logout(driver);
	}
	catch(Exception e){
		
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
	}
    
    

	
/*******************************************
 * 
 *     Media - Filters
 *     
 *     
 *******************************************/
	
	test = "Media Filters";
	try{
	
	login(driver, admin.getUsername(), admin.getPasswd());
	goTo("Configure", "Content", "Media");
	tableVisible();
	String[] filters= {"null", "null", "null", "null", "null"};

	filters[0] = "cat";
	filters[1] = "mouse";
	filters[2] = "dog";
	Filters mediaFilters = new Filters(driver, filters, "media");
	longPause();
	if (countResults() == 7){

		Arrays.fill(filters, "null");
		filters[3] = "-- Side-by-Side Videos";
		Filters mediaFilters2 = new Filters(driver, filters, "media");
		longPause();
		if(countResults() == 2)
			printResult(test, data, "passed");
		else
			printResult(test, data, "failed");
	}
	else
		printResult(test, data, "failed");
	logout(driver);
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
		
	}	
	
}
	

public int countResults(){
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
	
public void deleteMedia(String media){
	
	List<WebElement> mediaList = driver.findElements(By.cssSelector("td"));
	for(int x = 0; x< mediaList.size(); x++){
    	if (mediaList.get(x).getText().contains(media)){
    		System.out.print(mediaList.get(x+4).getText());
    		mediaList.get(x+4).findElement(By.xpath(".//span[contains(text(), 'Delete')]")).click();
    		driver.switchTo().alert().accept();
    		break;
    	}
	}
}

public void viewMedia(String media){
	
	List<WebElement> mediaList = driver.findElements(By.cssSelector("td"));
	for(int x = 0; x< mediaList.size(); x++){
    	if (mediaList.get(x).getText().contains(media)){
    		System.out.print(mediaList.get(x+4).getText());
    		mediaList.get(x+4).findElement(By.xpath(".//span[contains(text(), 'View')]")).click();
    		break;
    	}
	}
}

public void addMedia(String file){
	//#ngApp > div > div > div > div > div > div:nth-child(4) > vii-resource > div > div > vform > form > div > div:nth-child(1) > div > form-fields > div:nth-child(1) > div > span > span > file-field > div > div > div > label > input[type="file"]
   // WebElement iframe = driver.findElement(By.cssSelector("#iframe-viifield-fileupload"));
   // driver.switchTo().frame(iframe);
	WebElement chooseFile = driver.findElement(By.xpath("//input[@type='file']"));
   // WebElement selectFile = driver.findElement(By.cssSelector("input[type='file']"));
    chooseFile.sendKeys(file);
    WebElement complete = (new WebDriverWait(driver, 60)).until(ExpectedConditions.visibilityOfElementLocated
    		(By.xpath("//h5[contains(text(), 'Upload Complete')]")));
   // WebElement deleteButton = (new WebDriverWait(driver, 240)).until(ExpectedConditions.visibilityOfElementLocated
    		//(By.cssSelector("button[data-type='DELETE']")));
    //driver.switchTo().defaultContent();

}

public void clickSave(){
    WebElement save = driver.findElement(By.cssSelector("button-field[button-text='Save Media']"));
    save.click();	
}

public void navigateMediaFile(String link){
	WebElement linkToClick = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(text(), '" + link + "')]")));
	linkToClick.click();
}

public void waitImageLoad(){
	WebElement linkToClick = (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("div.panel.radius > img")));
	System.out.println(linkToClick.getAttribute("src"));
	
}

public void clickReplace(){
	WebElement replace = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("a[title='Replace']")));
	replace.click();
	
}

public boolean deleteTag(WebDriver driver, String tag){
	try{
	boolean found = false;
	tableVisible();
	List<WebElement> tagList = driver.findElements(By.cssSelector("td"));
	System.out.println(tagList.size());

	for(int k = 0; k < tagList.size(); k++){
    	if (tagList.get(k).getText().contains(tag)){
    		found = true;
    		System.out.print(tagList.get(k+7).getText());
    		tagList.get(k+7).findElement(By.xpath(".//span[contains(text(), 'Delete')]")).click();
    		driver.switchTo().alert().accept();
    		goTo("Configure", "Content", "Tags");
    		System.out.println("1");
    		return true;
    	}
	}
	if (!found && tagList.size()==225){
		WebElement next = driver.findElement(By.xpath("//a[contains(text(), 'Next')]"));
		next.click();
		System.out.println("2");
		found = deleteTag(driver, tag);
		System.out.println("3");
		}
		
	return found;
	}
	catch(Exception e){
		System.out.println("Tag " + tag + " not found");
		e.printStackTrace();
		goTo("Configure", "Content", "Tags");
		return false;
	}

}
}