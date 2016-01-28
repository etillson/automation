package test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Contact extends seleniumTest{
	
String phoneNumber;
String phoneType;
String phoneExtension;

public void editPhoneNumber(WebDriver driver, String phoneNumber, String type, String ext){
	int choice;
	if(mobile || safari){
		WebElement edit = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(@href, 'edit')]")));
		edit.click();
		
	}
	else{
	WebElement edit = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//h4[contains(text(), 'Phone')]/following::a")));
	edit.click();
	}
	if(mobile || safari){
		longPause();
		WebElement num = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//input[contains(@name, 'number')]")));
		num.clear();
		clickAnyButton(driver, "Update Phone", 1);
		longPause();
		List <WebElement> error = driver.findElements(By.cssSelector(".text-error"));
		if(error.size() == 1){
			WebElement num2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//input[contains(@name, 'number')]")));
			num2.sendKeys("301-221-0217");
			clickAnyButton(driver, "Update Phone", 1);
		}
		else
			System.out.println("Text-error alert not present for blank field");	
	}
	else{
	if (type.equalsIgnoreCase("cell"))
		choice = 0;
	else if (type.equalsIgnoreCase("work"))
		choice = 1;
	else if (type.equalsIgnoreCase("home"))
		choice = 2;
	else
		choice = 3;
	WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//select[contains(@name, 'phonetype_id')]")));
	Select option = new Select(dropdown);
	option.selectByIndex(choice);
	WebElement num = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//input[contains(@name, 'number')]")));
	num.clear();
	num.sendKeys(phoneNumber);
	
	WebElement extension = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//input[contains(@name, 'extension')]")));
	extension.sendKeys(ext);
	//WebElement submit = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			//(By.xpath("//button[contains(text(), 'Create Phone')]")));
	if(!clickAnyButton(driver, "Create Phone", 1))
			clickAnyButton(driver, "Update Phone", 1);
	}
}

public void deletePhoneNumber(WebDriver driver, String type){
	int choice;
	//List <WebElement> edit = driver.findElements(By.cssSelector("i.icon-edit"));
	List <WebElement> edit = driver.findElements(By.xpath("//a[contains(., 'edit')]"));	
	edit.get(1).click();
	shortPause();
	if (type.equalsIgnoreCase("cell"))
		choice = 0;
	else if (type.equalsIgnoreCase("work"))
		choice = 1;
	else if (type.equalsIgnoreCase("home"))
		choice = 2;
	else
		choice = 3;
	WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//select[contains(@name, 'phonetype_id')]")));
	Select option = new Select(dropdown);
	option.selectByIndex(choice);
	WebElement delete = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("button.confirm-delete.btn-danger.btn")));
	delete.click();

}


public boolean checkPhoneExists(WebDriver driver){
	List <WebElement> phones = driver.findElements(By.cssSelector("a.btn.btn-info"));
	int count = 0;
	for(int x = 0; x < phones.size(); x++){
		if (phones.get(x).getText().contains("Add a phone"))
			count++;
		System.out.println(count);
	}
		if (count == 2)
			return false;
		else
			return true;
		
	}


	
}


