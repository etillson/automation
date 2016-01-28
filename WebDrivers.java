package test1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDrivers {
	static String currentDriver;
	static boolean safari = false;
	WebDriver driver;
	
	public WebDrivers(){
		System.out.println("You must define the webdriver");
	}
	
	public WebDrivers(String webDriver){
		
		if(webDriver.equals("chrome"))
			currentDriver = "ChromeDriver";
			safari = false;
		
		if(webDriver.equals("safari")){
			WebDriver driver = new SafariDriver();
			safari = true;
		}
		
		if(webDriver.equals("firefox"))
			currentDriver = "FirefoxDriver";
			safari = false;	
	}

	public WebDriver getDriver(){
		return driver;
	}
	
	public boolean getSafari(){
		return safari;
	}

}
