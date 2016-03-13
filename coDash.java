package test1;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class coDash {

	WebDriver coDashDriver;
	
	public coDash(WebDriver driver){
	
		
			coDashDriver = driver;
			
	}
	
	public WebElement getInput(WebDriver driver){
		try{
		WebElement input = (new WebDriverWait(driver, 5)).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("co-dash input[type*='search']")));
				//until(ExpectedConditions.elementToBeClickable(By.cssSelector("co-dash input[class*='search']")));
		return input;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void setFilter(String filter){
		try{
		Select choice = new Select(getSelectFilter(filter));
		choice.selectByVisibleText(filter);
		}
		catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	public WebElement getSelectFilter(String filter){
		try{
		List <WebElement> input = coDashDriver.findElements(By.cssSelector("div.tool"));
		for(int x=0; x < input.size(); x++){
			if(input.get(x).getText().contains(filter))
				return input.get(x).findElement(By.cssSelector("select"));
			
		}
				//until(ExpectedConditions.elementToBeClickable(By.cssSelector("co-dash input[class*='search']")));
		return null;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public void setInput(WebDriver driver, String search){
		try{
		getInput(driver).sendKeys(search);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void clickSearch(WebDriver driver){
		try{
		WebElement input = (new WebDriverWait(driver, 5)).
				until(ExpectedConditions.elementToBeClickable(By.cssSelector("co-dash button[data-button='search']")));
		input.click();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int countUsersPage(WebDriver driver){
		try{
			int count = 0;
		//List <WebElement> users = driver.findElements(By.cssSelector("co-dash div[data-layout='user-table-row']"));

			WebElement userList = (new WebDriverWait(driver, 5)).
					until(ExpectedConditions.elementToBeClickable(By.cssSelector("div[data-area='user-info']")));

			List <WebElement> users = userList.findElements(By.cssSelector("span.name.ng-binding"));
		for(int x = 0; x < users.size(); x++){
			if(users.get(x).isDisplayed())
				count++;			
		}
		System.out.println(count + " users showing on coDash");
		return count;
		}
		catch(Exception e){
			System.out.println("data area not present, 0 users displayed");
			return 0;
		}
	}
	
	public void filterOption(WebDriver driver, String menuItem){
		WebElement status = (new WebDriverWait(driver, 5)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//co-dash//filter-bar-dropdowns//a[contains(., '"+menuItem +"')]")));
		status.click();
	}
	
	public void filterSub(WebDriver driver, String menuItem){
		WebElement status = (new WebDriverWait(driver, 5)).
				until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'drop-content')]//a[contains(., '"+menuItem +"')]")));
		status.click();
	}
	
}
