package test1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class email extends seleniumTest{
	String handle;
	String handle2;
	
	public WebDriver driverEmail;
	public email(){
		try{
		System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Downloads/chromedriver");
		driverEmail = new ChromeDriver();
		shortPause();
		driverEmail.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driverEmail.get("https://www.gmail.com");
		shortPause();
		String emailUser = "etillson@viimed.com";
		String id = "Email";
		setField(driverEmail, id, emailUser);
		String password = "6!lIN12silverspring24";
		try{
		id = "Passwd";
		setField(driverEmail, id, password);
		WebElement signIn = driverEmail.findElement(By.id("signIn"));
		signIn.click();
		}
		catch(Exception e){
			WebElement next = driverEmail.findElement(By.id("next"));
			next.click();
			shortPause();
			id = "Passwd";
			setField(driverEmail, id, password);
			WebElement signIn = driverEmail.findElement(By.id("signIn"));
			signIn.click();
			
		}
		}
		catch(Exception e){
			System.out.println("You may already be logged into your email");
		}
		
	}
	
	public boolean checkNameEmail(String name, String email){
		List<WebElement> total = driverEmail.findElements(By.tagName("tr")); 
		List<WebElement> element = driverEmail.findElements(By.xpath("//span[contains(text(), '" + name + "')]")); 
		System.out.println(total.size()); 
		for (int k =0; k < 12; k++){
		System.out.println(total.get(k).getText());
		System.out.println(element.get(k*2).getAttribute("email"));
		if(total.get(k).getText().contains(name) && total.get(k).getText().contains(email) && element.get(k*2).getAttribute("email").equals(email));
		return true;
		}
		
		return false;
		
	}
	
	public boolean checkSubject(String subject){
		WebElement element = (new WebDriverWait(driverEmail, 20)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//table")));
		List<WebElement> total = driverEmail.findElements(By.tagName("tr")); 
		System.out.println(total.size()); 
		for (int k =0; k < 12; k++){
		System.out.println(total.get(k).getText());
		if(total.get(k).getText().contains(subject))
			return true;
		}
		
		return false;	
	}

	public String getBodyText(){
		WebElement element = (new WebDriverWait(driverEmail, 20)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//center")));
		System.out.println(element.getText());
		return element.getText();
	}
	
	public void openEmail(String id, String id2){
		
		List<WebElement> total = driverEmail.findElements(By.tagName("tr")); 
		System.out.println(total.size()); 
		for (int k =0; k < 10; k++){
		System.out.println(total.get(k).getText());
		if(total.get(k).getText().contains(id)){
			total.get(k).click();
			break;
		}
		if(total.get(k).getText().contains(id2)){
				total.get(k).click();
		break;
		}		
		}		
	}
	
	public String getVerificationCode(){
		WebElement element = (new WebDriverWait(driverEmail, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//span[contains(text(), 'Verification Code')]/following::strong")));
		System.out.println(element.getText());
		return element.getText();	
	}
	
	public void activate(){
		try{
		handle= driverEmail.getWindowHandle();
		System.out.println(handle);
		//if(mobile)
			longPause();
		//WebElement activate = (new WebDriverWait(driverEmail, 10)).until(ExpectedConditions.visibilityOfElementLocated
				//(By.xpath("//span[contains(text(), 'Activate My Account')]")));
		//WebElement text = driverEmail.findElement(By.xpath("//body"));
		//System.out.println(text.getText());
		List <WebElement> activate = driverEmail.findElements(By.cssSelector("td"));
		System.out.println(activate.size());
		for(int i = 0; i < activate.size(); i++){
			if(activate.get(i).getText().contains("Activate My Account")){
		        if(driverEmail.getWindowHandles().size()>1)	         		
					break;
				activate.get(i).click();
				longPause();
				System.out.println(driverEmail.getWindowHandle());

		        }
			
		}
		
		//activate.click();
		shortPause();
        for(String x : driverEmail.getWindowHandles()){
            driverEmail.switchTo().window(x);
          }
		handle2= driverEmail.getWindowHandle();
		System.out.println(handle2);
		driverEmail.switchTo().window(handle2);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public int checkOffEmail(String name){
		boolean found = false;
	
		WebElement element = (new WebDriverWait(driverEmail, 20)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//table")));
		List<WebElement> total = driverEmail.findElements(By.tagName("tr")); 
		//List<WebElement> activateCheckbox = total.findElements(By.cssSelector("div[role='Checkbox']"));;			
		
		
		
		System.out.println(total.size());
		
		for (int k =0; k < 30; k++){
		System.out.println(total.get(k).getText());	
			if(total.get(k).getText().contains(name)){
				WebElement activateCheckbox = total.get(k).findElement(By.cssSelector("td:nth-child(2) div"));
				//activateCheckbox.click();
				if (activateCheckbox.getAttribute("aria-checked").equalsIgnoreCase("false")){
				activateCheckbox.click();
				shortPause();
				found = true;
				}
			}
		}
		shortPause();
		if (found)
			return 1;
		else
			return 0;
	}	
	
	public boolean newPassword(String user, String newPasswd){
		//WebElement password = driverEmail.findElement(By.name("password"));
		WebElement password = driverEmail.findElement(By.cssSelector("input[type='password']"));
		//WebElement confirmPasswd = driverEmail.findElement(By.name("confirm"));
		WebElement confirmPasswd = driver.findElement(By.cssSelector("input[placeholder='Type password again']"));
		password.sendKeys("!viimed689#");
		confirmPasswd.sendKeys("!viimed689#");
		WebElement setPasswd = driverEmail.findElement(By.xpath("//button[contains(text(), 'Set Password')]"));
		setPasswd.click();
		shortPause();	
		WebElement username = driverEmail.findElement(By.name("username"));
		WebElement name = driverEmail.findElement(By.name("password"));
		username.sendKeys(user);
		name.sendKeys(newPasswd);
		WebElement login = driverEmail.findElement(By.xpath("//button[contains(text(), 'Login')]"));
		login.click();
		shortPause();
		longPause();
		WebElement iAgree = (new WebDriverWait(driverEmail, 10)).until(ExpectedConditions.elementToBeClickable
				(By.cssSelector("acknowledgement-form a[class*='button primary']")));
		iAgree.click();
		shortPause();
		String[] alertPassed = {"Thank you for reviewing and accepting"};
		return alertsPassed(driverEmail, alertPassed);
		
		
	}
	
	public void closeWindow(){
		driverEmail.close();
		
	}
	public void backButton(){
		driverEmail.navigate().back();
	}
	
	public void switchToParent(){
		driverEmail.switchTo().window(handle);
	}
	
	public void refreshEmail(){
		driverEmail.navigate().refresh();
	}
	
	public void clickDelete(){
		WebElement trashcan = (new WebDriverWait(driverEmail, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("div[title='Delete']")));
		//*[@id=":5"]/div/div[1]/div[1]/div/div/div[2]/div[3]
		System.out.println(trashcan.getClass());
		trashcan.click();
		shortPause();
	}
	
	public boolean checkAllEmails(String name){
		int count = 0;
		int count2 = 0;
		while (count == count2){
		
		count = checkOffEmail(name) + count;
		count2++;
			}
		if (count > 0){
			return true;
		}
		else
			return false;
		}
}
