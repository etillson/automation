package test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Filters extends seleniumTest{
	
	static WebDriver driver;

	

	public Filters (WebDriver currentDriver, String [] filters, String type){
		
		driver = currentDriver;
		
		clickFilterButton();
		shortPause();
		
		if (type.equalsIgnoreCase("users")){
		for(int x = 0; x < filters.length; x++){
			System.out.println(x);
			if (filters[x] != "null"){
				if (x==0){
					WebElement field = getFieldText("input#fname");
					setFieldText(field, filters[x]);
				}
				if (x==1){
					WebElement field = getFieldText("input#lname");
					setFieldText(field, filters[x]);
				}
				if (x==2){
					WebElement field = getFieldText("input#email");
					setFieldText(field, filters[x]);
				}
				if (x==3){
					WebElement field = getFieldText("label[for='related_only']");
					clickField(field);
				}
				
				if (x==4){
					setCheckbox("tags", filters[x]);
				}
				if (x==5){
					setCheckbox("role_id", filters[x]);
				}
				if (x==6){
					CalendarDate date1 = new CalendarDate();
					date1.setDate(driver, filters[x], 1);
				}
				if (x==7){
					CalendarDate date2 = new CalendarDate();
					date2.setDate(driver, filters[x], 2);
				}
			}
		}	
		}
		
		if (type.equalsIgnoreCase("media")){
			for(int x = 0; x < filters.length; x++){
				if (x==0){
					setCheckbox("tags", filters[x]);
				}
				if (x==1){
					setCheckbox("tags", filters[x]);
				}
				if (x==2){
					setCheckbox("tags", filters[x]);
				}
				if (x==3){
					setCheckbox("categories", filters[x]);
				}
				if (x==4){
					setCheckbox("categories", filters[x]);
				}
			
		}
		}
		
		if (type.equalsIgnoreCase("forms")){
			for(int x = 0; x < filters.length; x++){
				if(filters[x] != null){
				if (x==0){
					setCheckbox("tags", filters[x]);
				}
				if (x==1){
					setCheckbox("tags", filters[x]);
				}
				if (x==2){
					WebElement field = getFieldText("input#name");
					setFieldText(field, filters[x]);
				}
				if (x==3){
					WebElement field = getFieldText("input#title");
					setFieldText(field, filters[x]);
				}
				}
				
		}
		}
			
			if (type.equalsIgnoreCase("scheduledActions")){

				for(int x = 0; x < filters.length; x++){
					System.out.println(x + "filter");
					if (filters[x] != "null"){
						if (x==0){
			
						WebElement field = getFieldText("input#caseowner.id_value");
						setSelectCaseDynamic(field, filters[x], filters[x+1], filters[x+2]);
						
					}

					if (x==3){
						WebElement field = getFieldText("input#actiontype");
						setFieldText(field, filters[x]);
					}
					if (x==4){
						CalendarDate date1 = new CalendarDate();
						date1.setDate(driver, filters[x], 1);
					}
					if (x==5){
						CalendarDate date1 = new CalendarDate();
						date1.setDate(driver, filters[x], 2);
					}
					if (x==6){
						CalendarDate date1 = new CalendarDate();
						date1.setDate(driver, filters[x], 3);
					}
					if (x==7){
						CalendarDate date1 = new CalendarDate();
						date1.setDate(driver, filters[x], 4);
					}
					if (x==8){
						WebElement field = getFieldText("label[for='show_failed']");
						clickField(field);
					}
					}
				
			}
		}
			
		if (type.equalsIgnoreCase("tasklist")){
			for(int x = 0; x < filters.length; x++){
				System.out.println(x);
				if (filters[x] != "null"){
					if (x==0){
						WebElement field = getFieldText("select[ng-model*='status_id']");
						getSelectVisText(field, filters[x]);
					}
					if (x==1){
						WebElement field = getFieldText("#show_checked_out");
						clickField(field);
					}
					if (x==2){
						WebElement field = getFieldText("#show_closed");
						clickField(field);
					}
					if (x==3){
						WebElement field = getFieldText("input[ng-model*='sender_name']");
						setFieldText(field, filters[x]);
					}
					
					if (x==4){
						WebElement field = getFieldText("input[ng-model*='case_title']");
						setFieldText(field, filters[x]);
					}
					if (x==5){
						WebElement field = getFieldText("input[ng-model='data.filters.title']");
						setFieldText(field, filters[x]);
					}

				}
			}	
			}
		applyFilters();
		shortPause();
		clickFilterButton();
		shortPause();	
		
	}
	
	
	public void clickFilterButton(){
		WebElement filterButton = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("button[vii-toggle]")));
		filterButton.click();
		
	}
	
	public WebElement getFieldText(String id){
		
		WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector(id)));
		return field;
	}

	
	public WebElement getFieldCheck(String id){
		
		WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector(id)));
		return field;
	}
	

	
	public void setFieldText(WebElement fieldSet, String value){
		fieldSet.sendKeys(value);		
	}
	
	public void getSelectVisText(WebElement fieldSet, String value){
		

		Select choice = new Select(fieldSet);
		choice.selectByVisibleText(value);
	}

	public void clickField(WebElement fieldSet){
		fieldSet.click();		
	}
	
	public void setCheckbox(String id, String value){
		WebElement checkBoxName = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//checkbox-field[@elid = '"+ id + "']")));
		List<WebElement> listItems = checkBoxName.findElements(By.tagName("label"));
		for (int k = 0; k < listItems.size(); k++){
			if (listItems.get(k).getText().equalsIgnoreCase(value)){
				//These were commented out due to a change in checkbox visibility on 10/7/2015
				//WebElement toCheck = listItems.get(k).findElement(By.xpath("preceding-sibling::input"));
				//System.out.println(toCheck.getAttribute("elid"));
				listItems.get(k).click();
				}
			}
		}
	
	public void applyFilters(){
		WebElement applyFilter = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("button-field[button-text='Apply Filters'] button")));
		//WebElement applyFilter = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				//(By.xpath("//button[contains(text(), 'Apply Filters')]")));
	
		applyFilter.click();
	}
	
	public void resetFilters(){
		WebElement resetFilter = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("button-field[button-text='Reset Filters'] button")));
		//WebElement applyFilter = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				//(By.xpath("//button[contains(text(), 'Apply Filters')]")));
	
		resetFilter.click();
	}
	
	public void clickToggle(String fieldName){
		WebElement field = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector(fieldName)));
		field.click();
	}
	
	public static void setSelectCaseDynamic(WebElement fieldSet, String username, String fullname, String caseType){
		

		if(safari){
			
			jsMouseOver(driver, fieldSet);
			shortPause();
			fieldSet.click();
			fieldSet.sendKeys(username);
		}
		else{
		Actions builder = new Actions(driver);
		builder.moveToElement(fieldSet).click().sendKeys(username).perform();
		}
		shortPause();
		try{
		WebElement element2 = driver.findElement(By.xpath("//div[contains(text(), '" + fullname + "') and contains(text(), '" + caseType + "')]"));
			if(safari){
				
				jsMouseOver(driver, fieldSet);
				shortPause();
				element2.click();
			}
			else
			{	
				Actions builder = new Actions(driver);
				builder.moveToElement(element2).click().perform();
			}
		}
		catch(Exception e){
			System.out.println("Web Element already entered");
		}
	}
	
}
