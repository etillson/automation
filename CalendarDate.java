package test1;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CalendarDate extends seleniumTest {

String today;	
String tomorrow;
	
//Takes the number of desired days + or - and changes the calendar date of the date field
public void setDate(WebDriver driver, int days) {
	WebElement dateBox = (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("a.quickdate-button.ng-binding")));
	
	dateBox.click();
	WebElement dateInput = driver.findElement(By
			.xpath("//input[contains(@class, 'quickdate-date-input')]"));
	SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
	Calendar c = Calendar.getInstance();
	today = sdf.format(c.getTime());
	c.add(Calendar.DAY_OF_MONTH, days); // number of days to add
	tomorrow = sdf.format(c.getTime()); // dt is now the new date
		dateInput.clear();
		dateInput.sendKeys(tomorrow);
		shortPause();
		if(ie){
			clickCalendarSave(driver);
			//dateInput.sendKeys(Keys.ENTER); - Changed 10/10/2015
			System.out.println("ie");
		}
		else
		//dateInput.sendKeys(Keys.RETURN); - Changed 10/10/2015
			clickCalendarSave(driver);
}

public void setDate(WebDriver driver, String date, int num) {
	List<WebElement> dateboxes = driver.findElements(By.cssSelector(".quickdate-button.ng-binding"));
	System.out.println(dateboxes.size());
	for (int k = 0; k < dateboxes.size(); k++){
		
		if(num == k+1){
			System.out.println(dateboxes.get(k).getAttribute("class"));
			shortPause();
		dateboxes.get(k).click();
		System.out.println(k);
		shortPause();
		WebElement dateInput = dateboxes.get(k).findElement(By
			.xpath("following-sibling::div/div/div/input"));
		
		/*
		System.out.println(dateInput.getAttribute("class"));
		SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
		Calendar c = Calendar.getInstance();
		today = sdf.format(c.getTime());
		System.out.println(dateInput.getAttribute("placeholder"));
		c.add(Calendar.DATE, days); // number of days to add
		tomorrow = sdf.format(c.getTime()); // dt is now the new date
		System.out.println(dateInput.getAttribute("placeholder"));
		*/
			dateInput.sendKeys(date);
			clickCalendarSave(driver);
			//dateInput.sendKeys(Keys.RETURN);
		}
	}
}

public void setDate(WebDriver driver){
	SimpleDateFormat sdf = new SimpleDateFormat("MMMMM.dd.yyyy hh.mm a");
	Calendar c = Calendar.getInstance();
	today = sdf.format(c.getTime());
	
}

public void setDate(WebDriver driver, String format){
	SimpleDateFormat sdf = new SimpleDateFormat(format);
	Calendar c = Calendar.getInstance();
	today = sdf.format(c.getTime());
	
}

public void setDate(WebDriver driver, int days, String fieldId) {
	WebElement dateBox = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//div[contains(@id, '"+ fieldId + "')]")));
	dateBox.click();
	WebElement dateInput = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//div[contains(@id, '"+ fieldId + "')]//following::input[contains(@class, 'quickdate-date-input')]")));
	SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
	Calendar c = Calendar.getInstance();
	today = sdf.format(c.getTime());
	c.add(Calendar.DATE, days); // number of days to add
	tomorrow = sdf.format(c.getTime());// dt is now the new date
		dateInput.clear();
		dateInput.sendKeys(tomorrow);
		dateInput.sendKeys(Keys.RETURN);
}

public String getToday(){
	return today;
}

public void clickCalendarSave(WebDriver driver){
	driver.findElement(By.cssSelector("a.quickdate-save")).click();
}

public String getTomorrow(){
	return tomorrow;
}


public void setDateByClick(WebDriver driver, int days) {
	
	if(safari){
		longPause();
	}
	WebElement dateBox = (new WebDriverWait(driver, 20)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("a.quickdate-button.ng-binding")));
	
	
	dateBox.click();

	shortPause();
	/*
	WebElement dateInput = driver.findElement(By
			.xpath("//input[contains(@class, 'quickdate-date-input')]"));
			*/
	SimpleDateFormat sdf = new SimpleDateFormat("M/d/yy");
	Calendar c = Calendar.getInstance();
	today = sdf.format(c.getTime());
	String input = today.substring(today.indexOf("/")+1, today.lastIndexOf("/"));
	int x = Integer.parseInt(input);
	c.add(Calendar.DAY_OF_MONTH, days); // number of days to add
	tomorrow = sdf.format(c.getTime());// dt is now the new date
	String input2 = tomorrow.substring(tomorrow.indexOf("/")+1, tomorrow.lastIndexOf("/"));
	int y = Integer.parseInt(input2);
	System.out.println(input2);
	if(y>x && days < 0)
		clickPrevious(driver);
	else if (y<x && days > 0)
		clickNext(driver);
	
	clickDate(driver, input2);
	
	
}


public void clickDate(WebDriver driver, String day){
	
	WebElement table = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("table.quickdate-calendar")));
	List <WebElement> days = table.findElements(By.cssSelector("td"));
	for(int x = 0; x < days.size(); x++){
		System.out.println(days.get(x).getText());
		if (days.get(x).getText().equals(day)){
			days.get(x).click();
			break;
		}
	}
}		
public void clickPrevious(WebDriver driver){
	
	WebElement prev = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("a.quickdate-prev-month.quickdate-action-link")));
	prev.click();
	shortPause();
}


public void clickNext(WebDriver driver){
	
	WebElement next = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("a.quickdate-next-month.quickdate-action-link")));
	next.click();
	shortPause();
}


}

