package test1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class VisibilitySettings extends Settings{
	
	
	WebDriver driver;
	List <String> currentVisiblityRoles = new ArrayList <String>();
	
	
	public VisibilitySettings(WebDriver driverNew){
		
		driver = driverNew;
	}
	
	public void setVisibilityRoles(String helperText, String exclusionText){
		currentVisiblityRoles.clear();
		currentVisiblityRoles.addAll(setSettingRoles(driver, helperText, exclusionText));
	}
	
	
	public List <String> getVisibilityRoles(){
		return currentVisiblityRoles;	
	}

	public boolean selectVisibilityCheckbox(String role, String toCheck){
		
		return adminCheckboxSelect(driver, role, toCheck);
	}
	
}
