package test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Milestones extends seleniumTest{

//		<<  Variables  >>
	
	WebElement elementMilestone;
	String milestone;
	int num;
	String user;
	boolean milestoneExists;
	

//		<<  Constructors  >>
	
public Milestones(WebDriver driver, String userId, int milestoneNum, String milestoneName){
	milestone = milestoneName;
	num = milestoneNum;
	user = userId;
	
}
	


//		<<  Milestone Methods  >>

public boolean checkUser(WebDriver driver){
	try{
	tableVisible();
	List <WebElement> element = driver.findElements
			(By.xpath("//a[contains(@href, '" + getUser() + "')]/following::span["+ 2 * getNum() +"]"));
	if(element.size()<1 && next(driver)){
		checkUser(driver);
	}
	else if(element.size()>=1)
		return true;
	
		return false;
	}
	catch (Exception e){
		System.out.println("Milestone " + getMilestoneName() + " does not exist");
		return false;
		
	}
	
}

public void setMilestone(WebDriver driver){
	try{
	WebElement element2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(@href, '" + getUser() + "')]/following::span["+ 2 * getNum() +"]")));
	
	System.out.println(element2.getText());	
	elementMilestone = element2;
	milestoneExists = true;
	}
	catch (Exception e){
		System.out.println("Milestone " + getMilestoneName() + " does not exist");
		WebElement element2 = null;
		elementMilestone = element2;
		milestoneExists = false;
	}
	
}

public int getNum(){
	return num;
}

public String getUser(){
	return user;
}

public String getMilestoneName(){
	return milestone;
}

public boolean milestoneExists(){
	
	return milestoneExists;
}

public void getMilestones(WebDriver driver, String userId){
	WebElement element = driver.findElement(By.xpath("//a[contains(@href, '" + userId + "')]/following::td[1]"));
	System.out.println(element.getText());
}


public boolean checkMilestone(WebDriver driver){
	
	if (!elementMilestone.getCssValue("background-color").equals("rgba(85, 118, 48, 1)") && elementMilestone.getText().contains(milestone))
	{
		System.out.println("Milestone is not green");
		return false;
	}
	else if (elementMilestone.getCssValue("background-color").equals("rgba(85, 118, 48, 1)") && !elementMilestone.getText().contains(milestone))
	{
		System.out.println("Milestone is out of order");
		return false;
	}
	else if (elementMilestone.getCssValue("background-color").equals("rgba(85, 118, 48, 1)") && elementMilestone.getText().contains(milestone))
		return true;
	else
		return false;
	
}


	
	
}
