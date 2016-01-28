package test1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CloneForm extends seleniumTest{
	
	
	
	
	public void main(String[] args){
		
		String test;
		
		System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Downloads/chromedriver");
		WebDriver driver = new ChromeDriver();
	    Users tester = new Users(driver, "Testing Provider");
	    Users admin = new Users(driver, "Admin");
		
/*********************************************** 
*		
*		Go to NewQA
*
*
**********************************************/
	    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://newqa.viinetwork.net/login");
		WebDriverWait wait = new WebDriverWait(driver, 10);

/*********************************************** 
*		
*		CloneForm
*		30 Clone Form
*
*
**********************************************/
		
		test ="30 Clone Form";
		
		login(driver, admin.getUsername(), admin.getPasswd());
		goToQa();
		getTest(test);
		goTo("Configure", "Content", "Forms");
	
		//Forms cloneTest = new Forms(driver, "1K Criteria_AllTrue", wait);
		//WebElement settings = driver.findElement(By.xpath("//div[contains(@id, 'form-settings')]"));
		List <WebElement> setting = driver.findElements(By.xpath("//div[contains(@id, 'form-settings')]/*/*/*/*/*"));
		System.out.println(setting.size());
		
		String[][] settings = new String [4][setting.size()];
		
		for(int x = 0; x < setting.size(); x++){
			
			System.out.print(setting.get(x).getText() + " ");
			System.out.print(setting.get(x).getAttribute("checked")+ " ");
			System.out.print(setting.get(x).getAttribute("value")+ " ");
			System.out.print(setting.get(x).getAttribute("class")+ "\n\n");
			
		}
		
	
		//cloneTest.cloneForm(driver, "1K Criteria_AllTrue", wait);
		
		
		
		
		
	}

}
