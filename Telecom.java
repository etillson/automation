package test1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Telecom extends seleniumTest{
	
	public void openTelecom(WebDriver driver){
		
			goTo("Configure", "Site", "Settings");
			/*WebElement config = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//a[contains(text(), 'Configure')]")));
			WebElement site = driver.findElement(By.xpath("//a[contains(text(), 'Site')]"));
			WebElement settings = driver.findElement(By.xpath("//a[contains(text(), 'Settings')]"));
			
			if (safari){
				driver.get("https://newqa.viinetwork.net/admin/r/site/general#/resource");
			}
			else if (ie){
				Actions select = new Actions(driver);
				select.moveToElement(config).click()
				.moveToElement(site).click()
				.moveToElement(settings).click().perform();
				
			}
				
			else{
			Actions select = new Actions(driver);
			select.moveToElement(config);
			select.moveToElement(site);
			select.moveToElement(settings).click().perform();
			
			}
			*/
			pageTitleVisible("Site General Settings");
			navigateSubNav(driver, "Communications");
		
	}
	
	public String getDefaultEmail(WebDriver driver){
		WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//input[@id='default_email_from']")));
		System.out.println(element.getAttribute("value"));
		return element.getAttribute("value");		
		}
	
	public String getDefaultName(WebDriver driver){
		WebElement element = driver.findElement(By.xpath("//input[@id='default_email_from_name']"));
		System.out.println(element.getAttribute("value"));
		return element.getAttribute("value");		
		}
	
	public String getActivationSubject(WebDriver driver){
		WebElement element = driver.findElement(By.xpath("//input[@id='activation_email_subject']"));
		System.out.println(element.getAttribute("value"));
		return element.getAttribute("value");		
		}
	
	public String getActivationConfirmationSubject(WebDriver driver){
		WebElement element = driver.findElement(By.xpath("//input[@id='activation_confirmation_email_subject']"));
		System.out.println(element.getAttribute("value"));
		return element.getAttribute("value");		
		}
	
	public String getResetPasswordSubject(WebDriver driver){
		WebElement element = driver.findElement(By.xpath("//input[@id='reset_password_email_subject']"));
		System.out.println(element.getAttribute("value"));
		return element.getAttribute("value");		
		}
	
	public void setDefaultEamil(WebDriver driver, String email){
		WebElement element = driver.findElement(By.xpath("//input[@id='default_email_from']"));
		element.clear();
		element.sendKeys(email);
		}
	
	public void setDefaultName(WebDriver driver, String name){
		WebElement element = driver.findElement(By.xpath("//input[@id='default_email_from_name']"));
		element.clear();
		element.sendKeys(name);
		}
	
	public void setActivationSubject(WebDriver driver, String subject){
		WebElement element = driver.findElement(By.xpath("//input[@id='activation_email_subject']"));
		element.clear();
		element.sendKeys(subject);		
		}
	
	public void setActivationConfirmation(WebDriver driver, String confirmation){
		WebElement element = driver.findElement(By.xpath("//input[@id='activation_confirmation_email_subject']"));
		element.clear();
		element.sendKeys(confirmation);		
		}
	
	public void submitChanges(WebDriver driver){
		WebElement element = driver.findElement(By.cssSelector("#button-0"));
		//WebElement element = driver.findElement(By.xpath("//button-field[contains(@button-text, 'Save Changes')]"));
	
		element.click();
		shortPause();
		}
	
	public void saveChanges(WebDriver driver){
		clickSave(driver, "Save Changes");
	    if(alertPresent(driver, "The information has been saved"))
	    	System.out.println("Saved - alert present");
	    shortPause();
	}
	
	
		
}
