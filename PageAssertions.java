package test1;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageAssertions extends seleniumTest{

	
	
	public WebDriver driver;
	public String pageText;
	public String footer;
	
//Constructor in which the tests run
	
public PageAssertions(WebDriver driverCurrent){
    
	driver = driverCurrent;

	pageText = driver.findElement(By.cssSelector("div#ngApp")).getText();
	footer = driver.findElement(By.cssSelector("footer")).getText();
	pageText = (pageText + "\n" + footer);
	
}	

public boolean checkPageTitle(String title){
	if(driver.findElements(By.cssSelector("h2[ng-show='data.page.title']")).size() != 0){
		WebElement pageTitle = driver.findElement(By.cssSelector("h2[ng-show='data.page.title']"));
		if(!pageTitle.isDisplayed())
			return false;
		if(!pageTitle.getText().contains(title))
			return false;
		System.out.println("Title correct");
		return true;
	}
	return false;	
}

public boolean checkPageSubtitle(String subtitle){
	if(driver.findElements(By.cssSelector("small[ng-show='data.page.subtitle']")).size() != 0){
		WebElement pageSubtitle = driver.findElement(By.cssSelector("small[ng-show='data.page.subtitle']"));
		if(!pageSubtitle.isDisplayed())
			return false;
		if(!pageSubtitle.getText().equals(subtitle))
			return false;
		return true;
	}
	return false;	
}

public boolean checkPageDescription(String description){
	if(driver.findElements(By.cssSelector("p[ng-show='data.page.description']")).size() != 0){
		WebElement pageDescription = driver.findElement(By.cssSelector("p[ng-show='data.page.description']"));
		if(!pageDescription.isDisplayed())
			return false;
		if(!pageDescription.getText().equals(description))
			return false;
		System.out.println("Page Description correct");
		return true;
	}
	return false;	
}

public boolean checkSubnav(String[] descriptions){
	if(driver.findElements(By.cssSelector("dl.sub-nav dd")).size() != 0){
		List <WebElement> pageDescription = driver.findElements(By.cssSelector("dl.sub-nav dd"));
		
		for(int x = 0; x < pageDescription.size(); x++){
			if(!pageDescription.get(x).isDisplayed())
				return false;
			if(!pageDescription.get(x).getText().equals(descriptions[x]))
				return false;
		}
		System.out.println("Subnav correct");
		return true;
	}
	return false;	
}

public String getPageText(){
	return pageText;
}


}
	
	

