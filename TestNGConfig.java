package test1;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;




public class TestNGConfig{

	String testName;

	public WebDriver driver;
	public WriteToFile data;
	URL url;


	 
	
	  @Parameters("browser")

	  
	  @BeforeClass //(groups = { "setConfig"})
	 
	  // Passing Browser parameter from TestNG xml
	  
	  public void beforeTest(String browser) {
	 
		  
		  
	//To close the selenium web server put in the URL http://localhost:4444/lifecycle-manager?action=shutdown

		  

		  
		/*  
		   __  __       _     _ _      
		  |  \/  | ___ | |__ (_) | ___ 
		  | |\/| |/ _ \| '_ \| | |/ _ \
		  | |  | | (_) | |_) | | |  __/
		  |_|  |_|\___/|_.__/|_|_|\___|  
		  
		  */
		  
	 if(browser.equalsIgnoreCase("chromiumS4")) {
		  
		  DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability("platformName", "ANDROID");
		  caps.setBrowserName("chrome");
		  caps.setCapability("deviceName", "4d0099344fc23103");
		//4d0099344fc23103 - GalaxyS4
		//c08088e382b3940 - Galaxy Tablet
			try{
				driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
		  
			seleniumTest driverSet = new seleniumTest(driver, false, false, true, false, true, false);
			//testName = "Chrome Galaxy Tablet";
			testName = "Chrome Galaxy S4";
		  
	  }
	 
	 if(browser.equalsIgnoreCase("chromium")) {
		  
		  DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability("platformName", "ANDROID");
		  caps.setBrowserName("chrome");
		  caps.setCapability("deviceName", "c08088e382b3940");
		//4d0099344fc23103 - GalaxyS4
		//c08088e382b3940 - Galaxy Tablet
			try{
				driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
		  
			seleniumTest driverSet = new seleniumTest(driver, false, false, true, false, true, false);
			//testName = "Chrome Galaxy Tablet";
			testName = "Chrome Galaxy Tablet";
		  
	  }
	 
	 if(browser.equalsIgnoreCase("iOS-iPhone")) {
		  
		  DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability("appium-version", "1.3.7");
		  caps.setCapability("platformName", "iOS");
		  caps.setCapability("platformVersion", "7.0.4");
		  caps.setBrowserName("safari");
		  //caps.setCapability("deviceName", "The-Brick-2");
		  caps.setCapability("deviceName", "The Brick 2");
		  //caps.setCapability("noReset", "true");
		  caps.setCapability("bundleId", "com.safariLauncher.safariLauncher");
		  caps.setCapability("udid", "856f93ceb4cd221250ceb91ef24d60b0fab67df1");
		//856F93CEB4CD221250CEB91EF24D60B0FAB67DF1
		  //856f93ceb4cd221250ceb91ef24d60b0fab67df1
		  //8c2ebdbebc8c7e701c9ce5518d935939b170b7a0
		  //8C2EBDBEBC8C7E701C9CE5518D935939B170B7A0
		  
		  //ios_webkit_debug_proxy -d -c 856f93ceb4cd221250ceb91ef24d60b0fab67df1:27753
		  
			try{
				driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
		  
			seleniumTest driverSet = new seleniumTest(driver, true, false, true, false, false, false);
			testName = "iPhone Safari";
		  
	  }
	 if(browser.equalsIgnoreCase("iOS-iPad")) {
		  
		  DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability("appium-version", "1.3.7");
		  caps.setCapability("platformName", "iOS");
		  caps.setCapability("platformVersion", "7.1");
		  caps.setBrowserName("safari");
		  //caps.setCapability("deviceName", "The-Brick-2");
		  caps.setCapability("deviceName", "Philip's iPad");
		  //caps.setCapability("noReset", "true");
		  caps.setCapability("bundleId", "com.safariLauncher.safariLauncher");
		  //caps.setCapability("app", "com.sas.safarilauncher");
		  caps.setCapability("udid", "8c2ebdbebc8c7e701c9ce5518d935939b170b7a0");
		  // ios-webkit-debug-proxy - ios_webkit_debug_proxy -c 8c2ebdbebc8c7e701c9ce5518d935939b170b7a0:27753 -d
		//856F93CEB4CD221250CEB91EF24D60B0FAB67DF1
		  //856f93ceb4cd221250ceb91ef24d60b0fab67df1
		  //8c2ebdbebc8c7e701c9ce5518d935939b170b7a0
		  //8C2EBDBEBC8C7E701C9CE5518D935939B170B7A0
		  
		  // ipad 8c2ebdbebc8c7e701c9ce5518d935939b170b7a0
			try{
				driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
		  
			seleniumTest driverSet = new seleniumTest(driver, true, false, true, true, false, false);
			testName = "iPad Safari";
		  
	  }
	 
	 /*
	   __  __               ___  ______  __
	  |  \/  | __ _  ___   / _ \/ ___\ \/ /
	  | |\/| |/ _` |/ __| | | | \___ \\  / 
	  | |  | | (_| | (__  | |_| |___) /  \ 
	  |_|  |_|\__,_|\___|  \___/|____/_/\_\
	 
	 */
	 
	  if(browser.equalsIgnoreCase("firefox")) {
	 
	      driver = new FirefoxDriver();
	      seleniumTest driverSet = new seleniumTest(driver, false, false, false, false, false, true);
	      testName = "Firefox OSX";
	  // If browser is chrome, then do this      

	      
	      
	      
	      
	  }else if (browser.equalsIgnoreCase("chrome")) { 
		  
	      // Setting up the path for the chromeDriver
		  //Current selenium chromedriver version is 2.16 updated on 6/8/2015
		  //System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Selenium/chromedriver3");
		  //File file = new File("src/WebDrivers/chromedriver");
		  
		  //http://stackoverflow.com/questions/3291255/deleting-files-created-with-fileoutputstream
		  //http://stackoverflow.com/questions/15968189/write-java-resource-in-src-directory
			  
		  //FileOutputStream output = new FileOutputStream("test.txt");
		 // InputStream input = url.openStream();
		 
		  //System.setProperty("webdriver.chrome.driver", "WebDrivers/chromedriver");
		  DesiredCapabilities caps = new DesiredCapabilities();
		  caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
	      driver = new ChromeDriver(caps);
	      seleniumTest driverSet = new seleniumTest(driver, false, false, false, false, true, false);
	      testName = "Chrome OSX";
	    
	      
	      
	      
	  }else if (browser.equalsIgnoreCase("safari")){
		  
		  //if this driver fails to launch, make sure the safari selenium extension is installed
		  //the extension somehow becomes removed from the browser periodically
		  //find the latest selenium safari driver at http://mvnrepository.com/artifact/org.seleniumhq.selenium/selenium-safari-driver
		  //Current version of Safari Driver is 2.45
		  driver = new SafariDriver();
		  seleniumTest driverSet = new seleniumTest(driver, true, false, false, false, false, false);
		  testName = "Safari OSX";		  
		  
	  }
	/*  
	  __        ___           _                      ___   _ 
	  \ \      / (_)_ __   __| | _____      _____   ( _ ) / |
	   \ \ /\ / /| | '_ \ / _` |/ _ \ \ /\ / / __|  / _ \ | |
	    \ V  V / | | | | | (_| | (_) \ V  V /\__ \ | (_) || |
	     \_/\_/  |_|_| |_|\__,_|\___/ \_/\_/ |___/  \___(_)_|
	     
	     */
	  else if (browser.equalsIgnoreCase("firefox-windows8.1")){
		  
		  //This is where the webdriver for the remote Windows 8.1 mahcine is created for Firefox
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
		
			try{
				driver = new RemoteWebDriver(new URL("http://10.7.2.75:5555/wd/hub"), capabilities);
				
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			seleniumTest driverSet = new seleniumTest(driver, true, false, false, false, false, true);
			testName = "firefox-windows8.1";
	  }

	  else if (browser.equalsIgnoreCase("ie11-windows8.1")){
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("internet explorer");
			
			
			try{
				capabilities.setCapability("nativeEvents", false);
				//driver = new RemoteWebDriver(new URL("http://192.168.1.7:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.79:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.138:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.87:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.129:4444/wd/hub"), capabilities);
				driver = new RemoteWebDriver(new URL("http://10.7.2.75:5555/wd/hub"), capabilities);
				
			}catch(MalformedURLException e){
				e.printStackTrace();
			}

			seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
			testName = "ie11-windows8.1";
	  }

	  else if (browser.equalsIgnoreCase("ie10-windows8.1")){
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("internet explorer");
			
			
			try{
				capabilities.setCapability("nativeEvents", false);

				driver = new RemoteWebDriver(new URL("http://10.7.2.102:5555/wd/hub"), capabilities);
		
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
			testName = "ie10-windows8.1";
	  }
	  else if (browser.equalsIgnoreCase("chrome-windows8.1")){
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("chrome");
			
			
			try{
				capabilities.setCapability("nativeEvents", false);
				driver = new RemoteWebDriver(new URL("http://10.7.2.75:5555/wd/hub"), capabilities);
		
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			seleniumTest driverSet = new seleniumTest(driver, false, false, false, false, true, false);
			testName = "chromeWin8.1";
	  }	
	  
	  /*	 __        ___           _                     _____ 
			 \ \      / (_)_ __   __| | _____      _____  |___  |
			  \ \ /\ / /| | '_ \ / _` |/ _ \ \ /\ / / __|    / / 
			   \ V  V / | | | | | (_| | (_) \ V  V /\__ \   / /  
			    \_/\_/  |_|_| |_|\__,_|\___/ \_/\_/ |___/  /_/   
			                                                    
	 */
	                                                                        
	  else if (browser.equalsIgnoreCase("chrome-windows7")){
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("chrome");
			
			
			try{
				capabilities.setCapability("nativeEvents", false);
				driver = new RemoteWebDriver(new URL("http://10.7.2.93:5555/wd/hub"), capabilities);
		
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			seleniumTest driverSet = new seleniumTest(driver, false, false, false, false, true, false);
			testName = "chromeWin7";
			
			
	  }
	  else if (browser.equalsIgnoreCase("ie10-windows7")){
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("internet explorer");
			
			
			try{
				
				capabilities.setCapability("nativeEvents", false);
				//driver = new RemoteWebDriver(new URL("http://192.168.1.7:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.79:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.138:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.87:4444/wd/hub"), capabilities);
				driver = new RemoteWebDriver(new URL("http://10.7.2.114:5555/wd/hub"), capabilities);
		
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
			testName = "ie10-windows7";
	  }
		
	  else if (browser.equalsIgnoreCase("firefox-windows7")){
		  
		  
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("firefox");
		
			try{
				driver = new RemoteWebDriver(new URL("http://10.7.2.93:5555/wd/hub"), capabilities);
				
			}catch(MalformedURLException e){
				e.printStackTrace();
			}
			seleniumTest driverSet = new seleniumTest(driver, true, false, false, false, false, true);
			testName = "firefox-windows7";
	  }
	  else if (browser.equalsIgnoreCase("ie11-windows7")){
		  
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName("internet explorer");
			
			
			try{
				capabilities.setCapability("nativeEvents", false);
				//driver = new RemoteWebDriver(new URL("http://192.168.1.7:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.79:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.138:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.87:4444/wd/hub"), capabilities);
				//driver = new RemoteWebDriver(new URL("http://10.7.2.129:4444/wd/hub"), capabilities);
				driver = new RemoteWebDriver(new URL("http://10.7.2.93:5555/wd/hub"), capabilities);
				
			}catch(MalformedURLException e){
				e.printStackTrace();
			}

			
			seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
			testName = "ie11-windows7";
	  }

		
		
	  /********************************************
	   * 
	   *   Create output file
	   *   
	   *   File to write testing results to is created here
	   * 
	   **********************************************/
	  
	  
		CalendarDate now = new CalendarDate();
		now.setDate(driver);
		String file_name = "/Users/erictillson/Documents/" + testName + " " + now.getToday() + ".txt";
		data = new WriteToFile( file_name , true );

		
		
		
		
		
		
	  // Doesn't the browser type, lauch the Website
	 
	  //driver.get("https://newqa.viinetwork.net/logout"); 
	 
	  }
	
	


	
	
	//public class NewQATestSuite extends TestNG{
	
		


	
		

	//}
	
	
	/*
		CalendarDate now = new CalendarDate();
		now.setDate(driver);
		String file_name = "/Users/erictillson/Documents/" + testName + " " + now.getToday() + ".txt";
		WriteToFile data = new WriteToFile( file_name , true );
	*/	
		


	/******************************************
	 * 
	 *     Enroll Patients
	 * 
	 *******************************************
		//EnrollPatients newusers = new EnrollPatients(driver);
		
		DeletePatients deleteEm = new DeletePatients(driver);
		
	/******************************************
	 * 
	 *     QA Tests
	 *     
	 *     
	 *     This is the order in which the QA tests run
	 *     you can move them around via copy paste or 
	 *     comment them out with //
	 * 
	 *******************************************/
	
	  
	  /*
	@Test
		
		
		public void testFilters(){
		
			CalendarDate now = new CalendarDate();
			now.setDate(driver);
			String file_name = "/Users/erictillson/Documents/" + testName + " " + now.getToday() + ".txt";
			WriteToFile data = new WriteToFile( file_name , true );
			
			AddToQueue atq = new AddToQueue(driver, data);
		}
		
		@Test (groups = { "media", "allTests"})
		
		public void testMedia(){
			
			MediaManager mm = new MediaManager(driver, data);
		}
		
		@Test (groups = { "reports", "allTests"})
		
		public void testReports(){
			
			Reports rep = new Reports(driver, data);
		}
		
		@Test (groups = { "scheduledActions", "allTests"})
		
		public void testScheduledActions(){
			
			ScheduledActions sa = new ScheduledActions(driver, data);
		}
	
		@Test (groups = { "criteria", "allTests"})
		
		public void testCriteria(){
		
			QaCriteria qc = new QaCriteria(driver, data);
		}
		
		@Test (groups = { "notifications", "allTests"})
		
		public void testNotifications(){
			
			Notifications notifications = new Notifications(driver, data);
		}		
		
		@Test (groups = { "relationships", "allTests"})
		
		public void testRelationships(){
			
			Relationships relationships = new Relationships(driver, data);
		}
	
		@Test (groups = { "addToQueue", "allTests"})
		
		public void testAddToQueue(){
			
			AddToQueue atq = new AddToQueue(driver, data);
		}
		
		@Test (groups = { "siteSettings", "allTests"})
		
		public void testSiteSettings(){
			
			SiteSettings site = new SiteSettings(driver,data);
		}
	
	*/
	
	
	

	
	@Test
	
	
	public void qaTests(){
	
	CalendarDate now = new CalendarDate();
	now.setDate(driver);
	String file_name = "/Users/erictillson/Documents/" + testName + " " + now.getToday() + ".txt";
	WriteToFile data = new WriteToFile( file_name , true );
	
	
	
	SmokeTest st = new SmokeTest(driver, data);
	
	AddToQueue atq = new AddToQueue(driver, data);
	
    ScheduledActions sa = new ScheduledActions(driver, data);
    
	QaCriteria qc = new QaCriteria(driver, data);
	
	Notifications notifications = new Notifications(driver, data);	

	FilterTests ftest = new FilterTests(driver, data);
	
	Relationships relationships = new Relationships(driver, data);	
	
	Reports rep = new Reports(driver, data);
	
	Permissions perm = new Permissions(driver, data);
	
	SiteSettings site = new SiteSettings(driver,data);
	
	MediaManager mm = new MediaManager(driver, data);
	
	AccessTokens at = new AccessTokens(driver, data);
	

	

	
	





	
		}

		



		
		
	
		//*/

		
		
		
		
		
	//}
	
	
	
	@AfterClass

	public void afterTest() {
		 
        driver.quit();
 
    }
	
}
