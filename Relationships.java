package test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Relationships extends seleniumTest{

	public WebDriver driver;
	
	public Relationships(WebDriver driverCurrent, WriteToFile data){

	
		driver = driverCurrent;
		
		String test;	
		int choice;
		String field;
		String fieldNum;
	    Users tester = new Users(driver, "Testing Provider");
	    Users admin = new Users(driver, "Admin");
	    Users partner = new Users(driver, "Partner");
	    Users testPatient = new Users(driver, "Testing Patient");
	    String relation1 = "Test nurse navigator 2";
	    String relation11 ="Test  nurse navigator 2";
	    String relation2 = "Partner Testing";
	    String relation3 = "Testing Provider";
	    String relation4 = "Testing Nurse [testing nurse]";
	    String relation5 = "Testing Nurse Group";
	    String relation6 = "Partner Provider";

	    
// Resize the window in order to make sure the menu list appears properly
		driver.get("https://qa3.viinetwork.net/logout");
	    resizeWindow(driver);
	    
	    printTitle("Relationships", data);
	    
/***********************************************
 * 		
 * 		Relationships - Limit Relationships
 * 
 ***********************************************/
				
		test = "Limit Relationships";
		
		try{
		login(testPatient);
		shortPause();
		goTo("Testing", "Testing Menu");
		getTest(test);
		WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("#field1475")));
		element.click();
		List<WebElement> relationships = driver.findElements(By.cssSelector("option"));
		System.out.println(relationships.size());
		System.out.println(relationships.get(1).getText());
		if(relationships.size() != 2){
			printResult(test, data, "failed");
			logout();
		}
		else if (relationships.get(1).getText().contains(relation1) || relationships.get(1).getText().contains(relation11)){
			printResult(test, data, "passed");
			logout();
		}
		else{
			printResult(test, data, "failed");
			logout();
		}
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}

/***********************************************
 * 		
 * 		Limit Relationships - No Limitations
 * 
 ***********************************************/
		
		test = "Limit Relationships - No Limitations";
		
		try{
		login(testPatient);
		shortPause();
		goTo("Testing", "Testing Menu");
		getTest(test);
		WebElement element = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("#field1478")));
		element.click();
		List<WebElement> relationships2 = driver.findElements(By.cssSelector("option"));
		System.out.println(relationships2.size());
		if(relationships2.size() != 7){
			printResult(test, data, "failed");
			logout();
		}
		else {
			boolean pass = true;	
			for(int x = 1; x <relationships2.size(); x++){
				System.out.println(relationships2.get(x).getText());
		
				if((relationships2.get(x).getText().contains(relation1) ||
				relationships2.get(x).getText().contains(relation11) || 		
				relationships2.get(x).getText().contains(relation2) ||
				relationships2.get(x).getText().contains(relation3) ||
				relationships2.get(x).getText().contains(relation4) ||
				relationships2.get(x).getText().contains(relation5) ||
				relationships2.get(x).getText().contains(relation6)
				
						) 
				&&
				(!relationships2.get(1).getText().contains(relationships2.get(2).getText()) &&
				!relationships2.get(1).getText().contains(relationships2.get(3).getText()) &&
				!relationships2.get(1).getText().contains(relationships2.get(4).getText()) &&
				!relationships2.get(1).getText().contains(relationships2.get(5).getText()) &&
				!relationships2.get(1).getText().contains(relationships2.get(6).getText()) &&
				!relationships2.get(2).getText().contains(relationships2.get(3).getText()) &&
				!relationships2.get(2).getText().contains(relationships2.get(4).getText()) &&
				!relationships2.get(2).getText().contains(relationships2.get(5).getText()) &&
				!relationships2.get(2).getText().contains(relationships2.get(6).getText()) &&
				!relationships2.get(3).getText().contains(relationships2.get(4).getText()) &&
				!relationships2.get(3).getText().contains(relationships2.get(5).getText()) &&
				!relationships2.get(3).getText().contains(relationships2.get(6).getText()) &&
				!relationships2.get(4).getText().contains(relationships2.get(5).getText()) &&
				!relationships2.get(4).getText().contains(relationships2.get(6).getText()) &&
				!relationships2.get(5).getText().contains(relationships2.get(6).getText()) 
				)){
					
				pass = true;
				}
				else{
					pass = false;
					System.out.println("here");
					break;
				}
			}
					
			
			if (pass){
				printResult(test, data, "passed");
				logout();
			}
			else{	
				printResult(test, data, "failed");
			logout();
			}
		}
		}

		catch(Exception e){
			printResult(test, data, "failed");

			logout();
		}
		
/***********************************************
 * 		
 * 		Partner in Health - Queue Access
 * 
 ***********************************************/

		test = "Partner in Health - Queue Access";
		
		try{
		login(testPatient);
		shortPause();
		goTo("Testing", "Testing Menu");
		getTest(test);
		
		field = "Choose which case";
		choice = 1;
		fieldNum = getFieldNumAngular(driver, field);
		getSelect(driver, fieldNum, choice);
		
		submit();
		logout();
		login(partner);
		shortPause();
		openTasklist();
		Tasks assistButton = new Tasks(driver, "Assist Patient Button Test", true);
		assistButton.openTask(driver);
		assistButton.taskButton(driver, "Assist Patient");
		WebElement stopAssist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), 'Stop assisting')]")));
		stopAssist.click();
		if(mobile)
			longPause();
		WebElement assist = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("a[href*='assist']")));
		assist.click();

		pageTitleVisible("Testing Patient");
		openTasklist();
		Tasks partnerAssist = new Tasks(driver, "Partner_In_Health_Assist", true);
		partnerAssist.openTask(driver);
		String[] tasks = {"Stop assisting", "Partner_In_Health_Assist"}; 
		partnerAssist.taskButton(driver, " Passed");
		if(!partnerAssist.hasTasks(driver, tasks)){
			printResult(test, data, "passed");
			logout();
			
		}
		else{
			printResult(test, data, "failed");
			logout();
		}
		}
		catch(Exception e){
			e.printStackTrace();
			printResult(test, data, "failed");
			logout();
		}
		
	    
	    
/***********************************************
 * 		
 * 		Limit Relationships - No Limitations
 * 
 ***********************************************
	    
	    
		test = "Limit Relationships - No Limitations";
		
		//try{
		login(driver, admin.username, admin.passwd);
		goToRelationships(driver);
		WebElement element2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//a[contains(text(), 'View Relationship Types')]")));
		element2.click();
		WebElement element3 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//tr/td[contains(text(), 'QA')]//following::a[contains(text(), 'Edit')]")));
		element3.click();
	
		//}

		//catch(Exception e){
		//	System.out.println("Test " + test + " failed by exception");
		//	logoutPatient(driver);
		//}
	*/
		}
		
}
