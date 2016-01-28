package test1;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;



public class TestNGSauceLabs {

	public WebDriver driver;
	
	 @Parameters({"browser"})
	 
	 @BeforeClass

	 
	  // Passing Browser parameter from TestNG xml
	 
	  public void beforeTest(String browser) {
	 
	  // If the browser is Firefox, then do this
	 
	  if(browser.equalsIgnoreCase("firefox")) {
	 
	      driver = new FirefoxDriver();
	      seleniumTest driverSet = new seleniumTest(driver, true, false, false, false, false, true);
	 
	  // If browser is chrome, then do this      
	 
	  }
	  
	  /*else if (browser.equalsIgnoreCase("chrome")) { 
	 
	      // Here I am setting up the path for my chromeDriver
	        String username = "erictillson";
	        String key = "7482b6e7-853e-4b6c-b803-1839cb9b2d16";
	        String version = "38.0";
	        String os = "OS X 10.6";
	        String browser2 = "chrome";
	        
	        DesiredCapabilities caps = DesiredCapabilities.chrome();
	        caps.setCapability("platform", "OS X 10.6");
	        caps.setCapability("version", "36");
	        caps.setCapability("deviceName", "");
	        // Create the connection to Sauce Labs to run the tests
	        try {
				this.driver = new RemoteWebDriver(
				        new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
				        caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      seleniumTest driverSet = new seleniumTest(driver, false, false);
	      
	  }*/
	  else if (browser.equalsIgnoreCase("galaxyS4")) { 
			 
	      
	        String username = "erictillson";
	        String key = "7482b6e7-853e-4b6c-b803-1839cb9b2d16";

	        
	        DesiredCapabilities caps = DesiredCapabilities.iphone();
	        caps.setCapability("platform", "OS X 10.9");
	        caps.setCapability("version", "7.1");
	        caps.setCapability("device-orientation", "portrait");

	    
	    
	        // Create the connection to Sauce Labs to run the tests
	        try {
				this.driver = new RemoteWebDriver(
				        new URL("http://" + username + ":" + key + "@ondemand.saucelabs.com:80/wd/hub"),
				        caps);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      seleniumTest driverSet = new seleniumTest(driver, false, false, true, false, true, false);
	      
	  }
	  else if (browser.equalsIgnoreCase("safari")){
		  
		  driver = new SafariDriver();
		  seleniumTest driverSet = new seleniumTest(driver, true, false, false, false, false, false);
	  
	  }else if (browser.equalsIgnoreCase("firefox-windows8.1")){
		  
		  //This is where the webdriver for the remote Windows 8.1 mahcine is created for Firefox
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
		
			try{
				driver = new RemoteWebDriver(new URL("http://10.7.2.140:4444/wd/hub"), capabilities);
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			seleniumTest driverSet = new seleniumTest(driver, true, false, false, false, false, true);
	  }
	  else if (browser.equalsIgnoreCase("ie11-windows8.1")){
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("internet explorer");
			
			
			try{
				capabilities.setCapability("nativeEvents", false);
				driver = new RemoteWebDriver(new URL("http://10.7.2.140:4444/wd/hub"), capabilities);
		
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
	  }
	 
	 
	  // Doesn't the browser type, lauch the Website
	 
	  driver.get("https://newqa.viinetwork.net/logout"); 
	 
	  }

	@Test
	public void test(){
		
		//AddToQueue atq = new AddToQueue(driver);
		//Relationships relationships = new Relationships(driver);
		//Notifications notifications = new Notifications(driver);
		
		//Reports report = new Reports(driver);
		//ScheduledActions sa = new ScheduledActions(driver);
		//QaCriteria qc = new QaCriteria(driver);
		
	}
	
	@AfterClass

	public void afterTest() {
		 
      driver.quit();

  }
	
	
	
}
