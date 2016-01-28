package test1;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class UsingRemoteWebDriver {

	public static void main (String...args){
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setBrowserName("firefox");

		RemoteWebDriver remoteWD = null;
		try{
			remoteWD = new RemoteWebDriver(new URL("http://10.7.2.139:4444/wd/hub"), capabilities);
		}catch(MalformedURLException e){
			e.printStackTrace();
		}
		remoteWD.get("http://www.google.com");
		WebElement element = remoteWD.findElement(By.name("q"));
		element.sendKeys("dkhafodhfa");
		System.out.println("hello");
		remoteWD.quit();
		
	}
}
