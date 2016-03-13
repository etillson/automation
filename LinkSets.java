package test1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkSets extends SmokeTest{
	
	private List <String> visitedLinks = new ArrayList<String>(); 
	private List <String> pageLinks = new ArrayList<String>();
	public WebDriver driver;
	
	public LinkSets(WebDriver newDriver){
		driver = newDriver;
	}
	
	public void setPageLinks(){
		List <WebElement> hrefs = driver.findElements(By.cssSelector("a"));
		for(int x = 0; x > hrefs.size(); x++){
			
			System.out.println(hrefs.get(x).getText());
		}
	}
	
	public void setDigs(){
		
	}

}
