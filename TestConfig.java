package test1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;



public class TestConfig {

	public TestConfig(String browsers, String tests, String os) throws MalformedURLException{
	String testName;
	String testsChosen;
	String ossChosen;
	WebDriver driver;
	String browsersChosen;
	
	browsersChosen = browsers;
	testsChosen = tests;
	ossChosen = os;
	
	System.out.println(browsersChosen + "here here");
	for(int y= 0; y< ossChosen.length(); y++ ){
		System.out.println(y + "this one");
		char osSelect = ossChosen.charAt(y);
		System.out.println(osSelect);

		for(int x=0; x< browsersChosen.length(); x++){
		

		
			
		
		char browserSelect = browsersChosen.charAt(x);
		System.out.println(browserSelect);

		if(browserSelect == 'm' && osSelect=='q'){
			
			// locate chromedriver in the jar resources
			URL res = getClass().getResource("/chromedriver");
			System.out.println(res.getPath());
			// locate chromedriver in the jar filesystem
			File f = new File(res.getFile());
			// copy chromedriver out into the real filesystem
			File target = new File(System.getProperty("user.home") + System.getProperty("file.separator") + f.getName());
			System.out.println(target.getPath());
			f.setReadable(true, false);
			f.setExecutable(true, false);
			f.setWritable(true, false);
			
			try {
				java.nio.file.Files.copy(f.toPath(), target.toPath(), StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			if (!target.canExecute())
				try {
					throw new FileNotFoundException("chromedriver copy did not work!");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				*/
			try {
				System.setProperty("webdriver.chrome.driver", target.getCanonicalPath());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
					//System.setProperty("webdriver.chrome.driver", "/user/etillson/");
		  			DesiredCapabilities caps = new DesiredCapabilities();
		  			caps.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		  			driver = new ChromeDriver(caps);
		  			seleniumTest driverSet = new seleniumTest(driver, false, false, false, false, true, false);
		  			testName = "chrome";
		}
		
		else if(browserSelect == 'n' && osSelect=='q'){
			
					driver = new FirefoxDriver();
	      			seleniumTest driverSet2 = new seleniumTest(driver, true, false, false, false, false, true);
	      			testName = "Firefox OSX";
		}
					
		else if(browserSelect == 'o' && osSelect=='q'){	
					driver = new SafariDriver();
		  			seleniumTest driverSet3 = new seleniumTest(driver, true, false, false, false, false, false);
		  			testName = "safari";
		}
		
		/*  
		  __        ___           _                      ___   _ 
		  \ \      / (_)_ __   __| | _____      _____   ( _ ) / |
		   \ \ /\ / /| | '_ \ / _` |/ _ \ \ /\ / / __|  / _ \ | |
		    \ V  V / | | | | | (_| | (_) \ V  V /\__ \ | (_) || |
		     \_/\_/  |_|_| |_|\__,_|\___/ \_/\_/ |___/  \___(_)_|
		     
		     */
		
		  else if (browserSelect == 'p'  && osSelect == 'r'){
			  
			  //This is where the webdriver for the remote Windows 8.1 mahcine is created for Firefox
			  
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("internet explorer");
			
		
					capabilities.setCapability("nativeEvents", false);
					//driver = new RemoteWebDriver(new URL("http://192.168.1.7:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.79:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.138:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.87:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.129:4444/wd/hub"), capabilities);
					driver = new RemoteWebDriver(new URL("http://10.7.2.146:5555/wd/hub"), capabilities);
					seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
					testName = "ie11-windows8.1";
				
		  }

		  else if (browserSelect == 'n'  && osSelect == 'r'){
			  
			  //This is where the webdriver for the remote Windows 8.1 mahcine is created for Firefox
			  
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("firefox");
			
				
					driver = new RemoteWebDriver(new URL("http://10.7.2.75:5555/wd/hub"), capabilities);
					
		
				seleniumTest driverSet = new seleniumTest(driver, true, false, false, false, false, true);
				testName = "firefox-windows8.1";
		  }

		  else if (browserSelect == 'p'  && osSelect == 'r'){
			  
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("internet explorer");
				
				
				
					capabilities.setCapability("nativeEvents", false);

					driver = new RemoteWebDriver(new URL("http://10.7.2.102:5555/wd/hub"), capabilities);
			
			
			
				
				seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
				testName = "ie10-windows8.1";
		  }
		  else if (browserSelect == 'm'  && osSelect == 'r'){
			  
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("chrome");
				
				
			
					capabilities.setCapability("nativeEvents", false);
					driver = new RemoteWebDriver(new URL("http://10.7.2.75:5555/wd/hub"), capabilities);
			
		
				seleniumTest driverSet = new seleniumTest(driver, false, false, false, false, true, false);
				testName = "chromeWin8.1";
		  }	
		  
		  /*	 __        ___           _                     _____ 
				 \ \      / (_)_ __   __| | _____      _____  |___  |
				  \ \ /\ / /| | '_ \ / _` |/ _ \ \ /\ / / __|    / / 
				   \ V  V / | | | | | (_| | (_) \ V  V /\__ \   / /  
				    \_/\_/  |_|_| |_|\__,_|\___/ \_/\_/ |___/  /_/   
				                                                    
		 */
		                                                                        
		  else if (browserSelect == 'm'  && osSelect == 's'){
			  
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("chrome");
				
				
			
					capabilities.setCapability("nativeEvents", false);
					driver = new RemoteWebDriver(new URL("http://10.7.2.109:5555/wd/hub"), capabilities);
			
	
				seleniumTest driverSet = new seleniumTest(driver, false, false, false, false, true, false);
				testName = "chromeWin7";
				
				
		  }
		  else if (browserSelect == 'p'  && osSelect == 's'){
			  
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("internet explorer");
				
				
			
					
					capabilities.setCapability("nativeEvents", false);
					//driver = new RemoteWebDriver(new URL("http://192.168.1.7:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.79:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.138:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.87:4444/wd/hub"), capabilities);
					driver = new RemoteWebDriver(new URL("http://10.7.2.109:5555/wd/hub"), capabilities);
			
		
				seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
				testName = "ie10-windows7";
		  }
			
		  else if (browserSelect == 'n'  && osSelect == 's'){
			  
			  
			  
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("firefox");
			
			
					driver = new RemoteWebDriver(new URL("http://10.7.2.109:5555/wd/hub"), capabilities);
					
	
				seleniumTest driverSet = new seleniumTest(driver, true, false, false, false, false, true);
				testName = "firefox-windows7";
		  }
		  else if (browserSelect == 'p'  && osSelect == 's'){
			  
				DesiredCapabilities capabilities = new DesiredCapabilities();
				capabilities.setBrowserName("internet explorer");
				
				
		
					capabilities.setCapability("nativeEvents", false);
					//driver = new RemoteWebDriver(new URL("http://192.168.1.7:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.79:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.138:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.87:4444/wd/hub"), capabilities);
					//driver = new RemoteWebDriver(new URL("http://10.7.2.129:4444/wd/hub"), capabilities);
					driver = new RemoteWebDriver(new URL("http://10.7.2.109:5555/wd/hub"), capabilities);
					
	

				
				seleniumTest driverSet = new seleniumTest(driver, false, true, false, false, false, false);
				testName = "ie11-windows7";
		  }
		else
			break;
	 
	  // Doesn't the browser type, lauch the Website
	 
	 // driver.get("https://newqa.viinetwork.net/logout"); 
	 
	  
	
  

  CalendarDate now = new CalendarDate();
  now.setDate(driver);
  String file_name = "/Users/erictillson/Documents/" + testName + " " + now.getToday() + ".txt";
  WriteToFile data = new WriteToFile( file_name , true );	

		
		
		
		System.out.println(testsChosen.length());
		
		//AddToQueue atqr = new AddToQueue(driver, data);
		
		for(int k=0; k< testsChosen.length(); k++){
		
		char select = testsChosen.charAt(k);
		System.out.println(select);
		
		switch (select) {
		
		case 'a': 	AddToQueue atq = new AddToQueue(driver, data);
					break;
		case 'b':	Relationships relationships = new Relationships(driver, data);
					break;
		case 'c':	Reports report = new Reports(driver, data);
					break;
		case 'd':	ScheduledActions sa = new ScheduledActions(driver, data);
					break;
		case 'e':	QaCriteria qc = new QaCriteria(driver, data);
					break;
		case 'f':	Notifications notifications = new Notifications(driver, data);
					break;
		case 'g':	SiteSettings ss= new SiteSettings(driver, data);
					break;
		case 'h':	Permissions perm= new Permissions(driver, data);
					break;	
		case 'i':	SmokeTest st= new SmokeTest(driver, data);
					break;	
		case 'j':	MediaManager mm= new MediaManager(driver, data);
					break;	
		case 'k':	FilterTests ft= new FilterTests(driver, data);
					break;		
		case 'l':	AccessTokens at= new AccessTokens(driver, data);
					break;
		case 't':	DynamicObjects dob = new DynamicObjects(driver, data);
					break;
					
		}
		
		
		}
	
		
	}
	
	}
	}

}
