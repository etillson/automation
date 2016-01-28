package test1;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

public class DeletePatients extends seleniumTest{

	public WebDriver driver;
	
	
	
	public DeletePatients(WebDriver driverCurrent){
		
		
		driver = driverCurrent;
		
		
		
		
		String[] userFilters = {"null", "null", "null", "null", "loadPatient", "null", "null", "null"};
		Users admin = new Users(driver, "Admin");

		
		
	    driver.manage().window().setSize(new Dimension(1920, 1080));
	    
		driver.get("https://newqa.viinetwork.net/logout");
		
		login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
		
		goTo("Configure", "People", "Users");
		
		tableVisible();
		Filters filter = new Filters(driver, userFilters, "users");
		longPause();
		
		while(countTableResults(driver) != 0){
		selectAllTable(driver);
		
		toolSelect(driver, "Delete");
		shortPause();
		clickAnyButton(driver, "Apply", 1);
		driver.switchTo().alert().accept();
		longPause();
		longPause();
		}
		
		
	}
	
	
	
	
}
