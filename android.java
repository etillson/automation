package test1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class android extends seleniumTest{
	
	
	public void main(String [] args){
	//System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Downloads/chromedriver");
	
    //DesiredCapabilities capabilities=DesiredCapabilities.chrome();
	DesiredCapabilities capabilities = new DesiredCapabilities();
	capabilities.setCapability("deviceName", "device");
	capabilities.setCapability("platformName", "Android");
	capabilities.setCapability("browserName", "Chrome");
	//capabilities.setCapability("appPackage", "org.mozilla.firefox");
	//capabilities.setCapability("appActivity", "org.mozilla.firefox.App");
	
	

    //ChromeOptions options=new ChromeOptions();  

    //options.setExperimentalOption("androidPackage", "com.android.chrome");
    //capabilities.setCapability(ChromeOptions.CAPABILITY, options);

    //driver = new ChromeDriver(capabilities);
		
	//driver = new FirefoxDriver(capabilities);
	URL url = null;
	try {
		url = new URL("http://localhost:4723/wd/hub");
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
	}
	WebDriver driver = new RemoteWebDriver(url, capabilities);
	driver.get("https://newqa.viinetwork.net/login");
	
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.get("https://newqa.viinetwork.net/login");
	Users admin = new Users(driver, "admin");
	String username = admin.getUsername();
	String passwd = admin.getPasswd();
	shortPause();
	login(driver, "admin4", "3INecuador");
	
	shortPause();
	driver.close();
	
	}
	

	
}
