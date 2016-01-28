package test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GeneralSettings extends Settings{
	
	
	public void setTextField(WebDriver driver, String id, String input) {
		WebElement element = driver.findElement(By.cssSelector("textarea#"+ id));
		element.clear();
		element.sendKeys(input);		
		}
	
	public void setInputField(WebDriver driver, String id, String input) {
		WebElement element = driver.findElement(By.cssSelector("input#"+ id));
		element.clear();
		element.sendKeys(input);		
		}
	
	public void setToggle(WebDriver driver, String id) {
		WebElement element = driver.findElement(By.cssSelector("label[for='"+ id + "']"));
		element.click();		
		}
	
	public void setSelectVisText(WebDriver driver, String id, String value){		
		WebElement dropdown = driver.findElement(By.cssSelector("select#"+ id));
		Select choice = new Select(dropdown);
		choice.selectByVisibleText(value);
		}
	
	public String getTextField(WebDriver driver, String id){
		WebElement element = driver.findElement(By.cssSelector("textarea#"+ id));
		System.out.println(element.getAttribute("value"));
		return element.getAttribute("value");		
		}
	
	public String getInputField(WebDriver driver, String id){
		WebElement element = driver.findElement(By.cssSelector("input#"+ id));
		System.out.println(element.getAttribute("value"));
		return element.getAttribute("value");		
		}
	
	public String getSelectVisibleText(WebDriver driver, String id){		
		WebElement dropdown = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("select#"+ id)));
		System.out.println(dropdown.getText());
		Select choice = new Select(dropdown);		
		return choice.getFirstSelectedOption().getText();
		}
	
	public String getLogo(WebDriver driver){	
		try{
		WebElement logo = driver.findElement(By.cssSelector("media-select-field[elid='site_logo'] li.description"));
		return logo.getText();
		}
		catch(Exception e){
			return "No logo selected";
		}
		}
	
	public String getTitle(WebDriver driver){		
		WebElement title = driver.findElement(By.cssSelector("h1"));
		return title.getText();
		}
	public void clickTitle(WebDriver driver){		
		WebElement title = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("h1 a")));
		title.click();
		}
	
	public void selectLogo(WebDriver driver, String logo){
		WebElement logoSelect = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("media-select-field[elid='site_logo']")));
		System.out.println(logoSelect.getText());
		List <WebElement> logos = logoSelect.findElements(By.cssSelector("tr"));
		for(int x = 0; x < logos.size(); x++){
			if(logos.get(x).getText().contains(logo)){
				logos.get(x).click();
				break;
			}
			if(logos.get(x).getText().contains("Sorry, there are no media items to display.")){
				selectLogo(driver, logo);
				break;
			}
		}
	}
	
	public void saveChanges(WebDriver driver){
		clickSave(driver, "Save Changes");
	    if(alertPresent(driver, "The information has been saved"))
	    	System.out.println("Saved - alert present");
	    shortPause();
	}

}
