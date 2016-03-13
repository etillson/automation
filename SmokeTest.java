package test1;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SmokeTest extends seleniumTest{

	
	String test; 
	public WebDriver driver;
	
	private List <String> visitedLinks = new ArrayList<String>(); 
	private String url2;
	private String field;
	private String fieldNum;
	private String perm = "You don't have permission to access this content.";
	private String link;
	private String name;
    //Date object
    CalendarDate now = new CalendarDate();
    

//No-Argument constructor
    public SmokeTest(){};
	
//Constructor in which the tests run
    
public SmokeTest(WebDriver driverCurrent, WriteToFile data){
    
	driver = driverCurrent;
	
    //Setup users for tests
    Users tester = new Users(driver, "Testing Provider");
    Users admin = new Users(driver, "Admin");
    Users testPatient = new Users(driver, "Testing Patient");
    Users testNurse = new Users(driver, "Testing Nurse");
    Users testAdmin = new Users(driver, "Testing Admin");


    
    CalendarDate now = new CalendarDate();
    now.setDate(driver);
    //Forms used for testing
    Forms permTest = new Forms(driver, "Permission Test", new String[]{"permissions", null, null, null});
	String testName = "links";
	String file_name = "/Users/erictillson/Documents/Screenshots/mac/chrome/" + testName + " " + now.getToday() + ".html";
	WriteToFile html = new WriteToFile( file_name , true );
    
    
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
	resizeWindow(driver, 1200, 1200);


/*********************************************** 
*		
*		Check Site Navigation - Text Field
*
*
**********************************************/
	
	login(admin);

	
	longPause();
	
	LinkSets dashboard = new LinkSets(driver);
	dashboard.setPageLinks();
	
	List <WebElement> links = driver.findElements(By.cssSelector("a"));
	System.out.println(links.size());
	for(int x =0; x < links.size(); x++){
		System.out.println(links.get(x).getText());
		System.out.println(links.get(x).getAttribute("href"));
	}
	
	try {
		html.createFileHTML(now.getToday());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    
	//clickAllLinks(admin, html, true, getMenuSize());

	
}

public void clickAllLinks(Users user, WriteToFile html, boolean firstLevel, int menuSize){
	WebElement nav = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("vii-primary-nav")));
}

public WebElement setParentElement(String selector){
	WebElement parent = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector(selector)));
	return parent;
}




/*
public void clickAllLinks(Users user, WriteToFile html, boolean firstLevel, int menuSize){
	
	try{
	
	int y=0;
	boolean deep = false;
	boolean login = false;
	boolean skip = false;
	
	String url;
	url = driver.getCurrentUrl();
	List <WebElement> links = driver.findElements(By.cssSelector("a"));
	System.out.println(links.size());
	
	
	for(int x =0; x < links.size(); x++){
		skip = false;

		List <WebElement> links2 = driver.findElements(By.cssSelector("a"));
		System.out.println(linkVisited(linkFilter(links2.get(x).getAttribute("href"))) + "The link has been visited");
		if((!firstLevel && x <= menuSize) || links2.get(x).getText().contains("Click to Set Date"))
			skip = true;
		if(linkVisited(linkFilter(links2.get(x).getAttribute("href"))))
			skip = true;
		if(!skip){
			if(!links2.get(x).getAttribute("href").contains("#") && !parentHasDropdown(links2.get(x))
					&& !links2.get(x).getAttribute("href").contains("javascript:void") || (x >= menuSize)){
				if(menuLevel1(links2.get(x))){
				
					if(menuLevel2(links2.get(x))){
						hoverOver(driver, links2.get(x).findElement(By.xpath("./ancestor::li[2]")).findElement(By.xpath("./ancestor::li")));
						}
				
					hoverOver(driver, links2.get(x).findElement(By.xpath("./ancestor::li[2]")));
	
					}

			
				if(!links2.get(x).isDisplayed()){
					System.out.println("Taking the alternate route");
					hoverOver(driver, "Configure", "Content");
	
					List <WebElement> links3 = driver.findElements(By.cssSelector("a"));
					
					System.out.println(links3.get(x).getAttribute("href"));
					System.out.println(links3.get(x).getAttribute("class"));
					link = links2.get(x).getAttribute("href");
					login = link.contains("logout");
					name = links3.get(x).getText();
					if(name.contains("Tasklist"))
						name = "Tasklist";
					if(name.contains("Activity"))
						name = "Activity";
					visitedLinks.add(linkFilter(links3.get(x).getAttribute("href")));
					links3.get(x).click();
	
				}	
				else{
				System.out.println(links2.get(x).getAttribute("href"));
				System.out.println(links2.get(x).getAttribute("class"));
				link = links2.get(x).getAttribute("href");
				login = link.contains("logout");
				name = links2.get(x).getText();
				if(name.contains("Tasklist"))
					name = "Tasklist";
				if(name.contains("Activity"))
					name = "Activity";
				visitedLinks.add(linkFilter(links2.get(x).getAttribute("href")));
				links2.get(x).click();
				}
	
				longPause();
				longPause();
				System.out.println(getElementLocation(driver.findElement(By.cssSelector(".pull-right.muted")), "y")+100);
				double size = (double)(getElementLocation(driver.findElement(By.cssSelector(".pull-right.muted")), "y")+ 100)/1800;
				int divs = (int) Math.ceil(size);
				System.out.println(divs);
				
				//Firefox takes full page screenshots without having to scroll the window, so it doesn't need this
				if(!firefox){
				for(int k = 0; k < divs; k++){
					if(getElementLocation(driver.findElement(By.cssSelector(".pull-right.muted")), "y")+100 > 1800){
						resizeWindow(driver, 1200, (getElementLocation(driver.findElement(By.cssSelector(".pull-right.muted")), "y") + 200)/divs);
						shortPause();
						scrollTo(((getElementLocation(driver.findElement(By.cssSelector(".pull-right.muted")), "y") + 100)/divs)*k);
					}
					else
						resizeWindow(driver, 1200, getElementLocation(driver.findElement(By.cssSelector(".pull-right.muted")), "y") + 100);
						
						shortPause();
						html.insertNewLine(name + k + ".png", link);
						captureScreen(driver, name + k);
						System.out.println(!driver.getCurrentUrl().equalsIgnoreCase("https://qa3.viinetwork.net/dashboard") && !login);
						if(!driver.getCurrentUrl().equalsIgnoreCase("https://qa3.viinetwork.net/dashboard") && !login)
							clickAllLinks(user, html, false, menuSize);
				}
				}
				else if(firefox){
					shortPause();
					html.insertNewLine(name + ".png", link);
					captureScreen(driver, name);
					System.out.println(!driver.getCurrentUrl().equalsIgnoreCase("https://qa3.viinetwork.net/dashboard") && !login);
					if(!driver.getCurrentUrl().equalsIgnoreCase("https://qa3.viinetwork.net/dashboard") && !login)
						clickAllLinks(user, html, false, menuSize);
				
				}
				if(login){
					login(user);
					longPause();
				}
				
				driver.get(url);	
				longPause();
				}
			// this doesn't seem to be needed here y=x;
			}
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
*/

/** @return true if the parent of element contains a dropdown menu*/
public boolean parentHasDropdown(WebElement element){
	try{
		if(element.findElement(By.xpath("..")).getAttribute("class").contains("has-dropdown")){
		System.out.println("true");
		return true;
		}
		else
			return false;
	}
	catch(Exception e){
		System.out.println("false");
		return false;
	}
	
}

/** @return true if the passed element is a first level menu item, meaning not a sub item of another menu item, else false*/
public boolean menuLevel1(WebElement element){
	try{
		System.out.println(element.getAttribute("class"));
		element.findElement(By.xpath("./ancestor::li[2]")).getAttribute("class").contains("has-dropdown");
		System.out.println("true2");
		return true;
	}
	catch(Exception e){
		System.out.println("false2");
		return false;
	}
}


/** @return true if the passed element is a second level menu item, meaning a sub item of another menu item, else false*/
public boolean menuLevel2(WebElement element){
	try{
		System.out.println(element.getAttribute("class"));
		element.findElement(By.xpath("./ancestor::li[2]")).findElement(By.xpath("./ancestor::li")).getAttribute("class").contains("has-dropdown");
		System.out.println("true2");
		return true;
	}
	catch(Exception e){
		System.out.println("false2");
		return false;
	}
}


/** @return scrolls the viewport to a specific y coordinate */
public void scrollTo(int y){
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("window.scrollTo(0,"+ y + " );");
	
}


public boolean navHeaderCheck(String href){
	if (href.contentEquals("#"))
		return true;
	else return false;
}

public String modifyNavHeader(String href, WebElement element){
	if(navHeaderCheck(href)){
		href = href + element.getText();
	}
	return href;
}

/** @return adds a link to the list that stores all of the visited links*/
public void addVisitedLink(String href){
	if (!linkVisited(href))
		visitedLinks.add(href);
}

/** @return checks href against against the list containing previously visited links, if contained returns true, else false */
public boolean linkVisited(String href){

	for (String temp : visitedLinks) {
		System.out.println(temp);
	}
	
	for (String temp : visitedLinks) {
		if(visitedLinks.contains(href))
			return true;
	}
	return false;
}

/** @return number of links that make up the primary and secondary nav */
/*
 *     New Menu doesn't use hover events
 * 
public int getMenuSize(){
	List <WebElement> links = driver.findElements(By.cssSelector("a"));
	int y = 0;
	for(int x =0; x < links.size(); x++){
	
		List <WebElement> links2 = driver.findElements(By.cssSelector("a"));
		if(!links2.get(x).getAttribute("href").contains("#") && !parentHasDropdown(links2.get(x))
				&& !links2.get(x).getAttribute("href").contains("javascript:void") && !links2.get(x).equals(null)){
			if(menuLevel1(links2.get(x))){
			
				if(menuLevel2(links2.get(x))){
					hoverOver(driver, links2.get(x).findElement(By.xpath("./ancestor::li[2]")).findElement(By.xpath("./ancestor::li")));
					}
			
				hoverOver(driver, links2.get(x).findElement(By.xpath("./ancestor::li[2]")));
				y=x;
				}
		}
	}
	return y;	
}
*/

/** @return parsed string not containing numbers in order to prevent multiple screenshots of similar content */
public String linkFilter(String link){
	try{
	int index;
	String temp;
	if(matcher("[0-9]\\/", link).find()){
		System.out.println("works");
		index = indexOf(matcher("[0-9]\\/", link));
		System.out.println(index);
		temp = link.substring( 0, index) + link.substring(index + 2, link.length());
	}
	else{
		temp = link;
	}
	temp = temp.replaceAll("[0-9]", "");
		System.out.println(temp);
	return temp;
	}
	catch(Exception e){
		e.printStackTrace();
		return null;
	}
	
}

public Matcher matcher(String regex, String s){
	Pattern pattern = Pattern.compile(regex);
	Matcher matcher = pattern.matcher(s);
	return matcher;
	
}

/** @return index of pattern in s or -1, if not found */
public static int indexOf(Matcher matcher) {
    return matcher.find() ? matcher.start() : -1;
}



}