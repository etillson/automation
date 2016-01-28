package test1;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class iOS extends seleniumTest{
	
	
	public void main(String [] args){
	//System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Downloads/chromedriver");
	
    //Desiredcap cap=Desiredcap.chrome();
	//Desiredcap cap = new Desiredcap();
	//cap.setCapability("deviceName", "device");
	//cap.setCapability("deviceName", "iPad");
	//cap.setCapability("udid", "FC443A61192372503AD93CFF2A5BC158985CD59F");
    //cap.setCapability("platformName", "iOS");
    //cap.setCapability("platformVersion", "7.1");
    //cap.setCapability("browserName", "safari");
	//cap.setCapability("platformName", "Android");
	//cap.setCapability("browserName", "Chrome");
	//cap.setCapability("appPackage", "org.mozilla.firefox");
	//cap.setCapability("appActivity", "org.mozilla.firefox.App");
    DesiredCapabilities cap = new DesiredCapabilities();
    cap.setCapability("deviceName", "iPhone Simulator");
    cap.setCapability("browserName", "safari");
    cap.setCapability("platformVersion", "7.1");
    cap.setCapability("platformName", "iOS");
	

    //ChromeOptions options=new ChromeOptions();  

    //options.setExperimentalOption("androidPackage", "com.android.chrome");
    //cap.setCapability(ChromeOptions.CAPABILITY, options);

    //driver = new ChromeDriver(cap);
		
	//driver = new FirefoxDriver(cap);
	URL url = null;
	try {
		url = new URL("http://localhost:4723/wd/hub");
	} catch (MalformedURLException e) {
		
		e.printStackTrace();
	}
	WebDriver driver = new RemoteWebDriver(url, cap);
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
