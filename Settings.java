package test1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Settings extends seleniumTest{
	
	List <String> currentRoles = new ArrayList <String>();
	List <String> subRoles = new ArrayList <String>();
	List <List<String>> subRolesList = new ArrayList <List<String>>();
	List <WebElement> currentSubRoles = new ArrayList <WebElement>();
	
	
	
	
	public List <String> setSettingRoles(WebDriver driver, String helperText, String exclusionText){
		List <WebElement> roles = driver.findElements(By.cssSelector("span[once-html='field.label']"));
		int y = 0;
		
		for(int x = 0; x < roles.size(); x++){
			
			System.out.println(roles.get(x).getText());
			if((roles.get(x).getText().contains(helperText) || helperText.isEmpty()) && 
					!roles.get(x).getText().contains(exclusionText)){
			currentRoles.add(y, roles.get(x).getText());
			y++;
			}
		}
		return currentRoles;
	}
	
	// Need to create the set subRoles method for the checkboxes ****************************
	
	public boolean checkSubRolesLists(WebDriver driver, String exclusion, List <String> siteRoles){
		
		List <WebElement> roles = driver.findElements(By.cssSelector("span[once-html='field.label']"));
		
		//for(int x = 0; x < roles.size(); x++){
		//	List <WebElement> currentSubRoles = driver.findElements(By.cssSelector("/..span/ul//li/label"));
		//}
		//*[@id="ngApp"]/div/div/div/div/div/div[3]/vii-resource/div/div/vform/form/div/div[1]/div/form-fields/div[1]/div/span/span/div/checkbox-field/ul/li[2]/label
		System.out.println("currentRoles " + currentRoles.size());
		System.out.println("roles " + roles.size());
		int z = 0;

		for(int x = 0; x < currentRoles.size(); x ++){
			for(int y = 0; y < roles.size(); y++){
				currentSubRoles.clear();
				if(roles.get(y).getText().contains(currentRoles.get(x))){
					subRoles.clear();
					System.out.println(roles.get(y).getText());
					currentSubRoles = roles.get(y).findElements(By.xpath("../..//span//ul/li/label"));
					System.out.println("currentSubRoles " + currentSubRoles.size());
					int j = 0; //this is used to count the roles in the roles list
					for(int k = 0; k < currentSubRoles.size(); k++){
						if(!currentSubRoles.get(k).getText().contains(exclusion)){
							System.out.println(currentSubRoles.get(k).getText());
							System.out.println(roles.get(j).getText());
							
						if(!currentSubRoles.get(k).getText().contains(siteRoles.get(j))){
								return false;
								
								}
						j++;
						}
						
					}			
				}
				System.out.println(y + "y");
			}
			System.out.println(x + "x");
		}
		return true;
	}
	
	public boolean checkSubRoles(WebDriver driver){
		
		for(int x = 0; x < subRolesList.size(); x++){
			for(int y = 0; y < currentRoles.size(); y ++){
				if(subRolesList.get(x).get(y).equalsIgnoreCase(currentRoles.get(y)))
					System.out.println( x + " ," + y + " match");
				else
					return false;
			}
		}
		return true;
	}
	
	public boolean adminCheckboxSelect(WebDriver driver, String role, String roleToCheck){
		
		List <WebElement> roles = driver.findElements(By.cssSelector("span[once-html='field.label']"));
		for(int x =0; x < roles.size(); x ++){
			if(roles.get(x).getText().contains(role)){
				roles.get(x).findElement(By.xpath("../..//span//ul/li/label[contains(text(), '"+roleToCheck +"')]")).click();
				clickSave(driver, "Save Changes");
				alertPresent(driver, "The information has been saved.");
				return true;
			}
		}
		return false;
	}
	
	
}
