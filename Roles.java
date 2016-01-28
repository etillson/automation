package test1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Roles extends seleniumTest{

	WebDriver driver;
	List <String> systemRoles = new ArrayList <String>();

public Roles(WebDriver driverNew){
	
	driver = driverNew;
}

public List <String> getRoles(){
	
	return systemRoles;
}

public void setRoles(){
	if(systemRoles.size() > 0){
		systemRoles = new ArrayList <String>();
	}
	List<WebElement> roles = driver.findElements(By.cssSelector("tr td:nth-of-type(2)"));
	for(int x = 0; x<roles.size(); x++){
		System.out.println(roles.get(x).getText());
		systemRoles.add(x, roles.get(x).getText());
			}
}

public int countRoles(List <WebElement> roles){
	return roles.size();
	
}

public boolean compareRoles(List <String> list, List <String> rolesList){
	for (int x = 0; x < rolesList.size(); x++){
		System.out.println(rolesList.get(x));
		System.out.println(list.get(x));
		if(!list.get(x).contains(rolesList.get(x)))
			return false;			
	}
	return true;
}

public boolean roleExists(String role){
	
	for(int x=0; x < getRoles().size(); x++){
		if(getRoles().get(x).contains(role))
			return false;
		
	}
	return true;
}

public void deleteRole(String role){
	WebElement poo = findListItemByName(role, driver, "role").findElement(By.xpath(".//span[contains(text(), 'Delete')]"));
	
	System.out.println(poo.getText());
	try{
	poo.click();
	driver.switchTo().alert().accept();
	}
	catch (Exception e)
	{
		shortPause();
		driver.switchTo().alert().accept();
	}
	
	
}

}
