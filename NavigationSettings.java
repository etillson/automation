package test1;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class NavigationSettings extends Settings{
	
	WebDriver driver;
	List <String> currentNavRoles = new ArrayList <String>();
	List <String> currentPrimNav = new ArrayList <String>();
	List <String> currentSecondNav = new ArrayList <String>();
	String originalRoleMenuSelect;
	String currentRoleMenuSelect;
	
	public NavigationSettings(WebDriver driverNew){
		
		driver = driverNew;
	
	}

	
	
	// WILL NEED TO EDIT in v.2.4*********************
	
	
	public List <String> getNavigationRoles(){
		return currentNavRoles;	
	}
	
	public List <String> getPrimNav(){
		return currentPrimNav;	
	}
	
	public List <String> getSecondNav(){
		return currentSecondNav;	
	}
	
	public String getRoleMenu(){	
		return currentRoleMenuSelect;
	}
	
	public String getOriginalRoleMenu(){	
		return originalRoleMenuSelect;
	}
	
	//Gets the titles of each field on the Site Navigation Settings page.  Need to edit when the toggle is removed
	public void setNavigationRoles(String helperText, String exclusionText){
		currentNavRoles.clear();
		currentNavRoles.addAll(setSettingRoles(driver, helperText, exclusionText));
		
		
		/*List <WebElement> navRoles = driver.findElements(By.cssSelector("span[once-html='field.label']"));
		
		for(int x = 0; x < navRoles.size(); x++){
			System.out.println(navRoles.get(x).getText());
			if(navRoles.get(x).getText().contains("Secondary Navigation For"));
			currentNavRoles.add(x, navRoles.get(x).getText());
		}
		*/
	}
	

	

	
	public void setRoleMenu(String role){
		List <WebElement> roleMenu = driver.findElements(By.cssSelector("form-fields > div"));
		for(int x = 0; x < roleMenu.size(); x++){
			if(roleMenu.get(x).getText().contains(role)){
				System.out.println("Role found");
				Select selection = new Select(roleMenu.get(x).findElement(By.cssSelector("select")));
				currentRoleMenuSelect = selection.getFirstSelectedOption().getText();
				break;				
			}
		}	
	}
	
	public void selectRoleMenu(String role, String menu){
		List <WebElement> roleMenu = driver.findElements(By.cssSelector("form-fields > div"));
		for(int x = 0; x < roleMenu.size(); x++){
			if(roleMenu.get(x).getText().contains(role)){
				System.out.println("Role found");
				Select selection = new Select(roleMenu.get(x).findElement(By.cssSelector("select")));
				originalRoleMenuSelect = selection.getFirstSelectedOption().getText();
				selection.selectByVisibleText(menu);
				currentRoleMenuSelect = menu;
				break;				
			}
		}	
	}
	
	public boolean compareMenus(List <String> navList, List <String> menuItems, String userName){
		
		System.out.println(navList.size());
		System.out.println(menuItems.size());
		for (int x = 0; x < menuItems.size(); x++){
			System.out.println(navList.get(x).toLowerCase());
			System.out.println(menuItems.get(x).toLowerCase());
			if(!menuItems.get(x).toLowerCase().contains(navList.get(x).toLowerCase()))
				if(!(menuItems.get(x).toLowerCase().contains("@fname@") && navList.get(x).toLowerCase().contains(userName.toLowerCase())))
					if(!(menuItems.get(x).toLowerCase().contains("tasks") && navList.get(x).toLowerCase().contains("tasks"))){
						System.out.println(menuItems.get(x).toLowerCase().contains("tasks"));
						System.out.println(navList.get(x).toLowerCase().contains("tasks"));
						return false;	
					}
		}
		return true;
	}
	
	public void setPrimaryNav(){
		List <WebElement> primaryNav = driver.findElements(By.cssSelector("nav > section > ul:nth-child(1) > li"));

		for(int x = 0; x < primaryNav.size(); x++){
			System.out.println(primaryNav.get(x).getText());
			currentPrimNav.add(x, primaryNav.get(x).getText());
		}
	}
	
	public void setSecondNav(){
		List <WebElement> secondNav = driver.findElements(By.xpath("//ul[@class='right']//li/a"));
		int y = 0;
		for(int x = 0; x < secondNav.size(); x++){
			System.out.println(secondNav.get(x).getText() + x);
			if(!secondNav.get(x).getText().contentEquals("")){
			currentSecondNav.add(y, secondNav.get(x).getText());
			if(secondNav.get(x).getText().contains("Tasks"))
			currentSecondNav.add(y, "Tasks");
			y++;
			}
		}
	}
	
	public void saveChanges(){
		clickSave(driver, "Save Changes");
	    if(alertPresent(driver, "The information has been saved"))
	    	System.out.println("Saved - alert present");
	    shortPause();
	}
	
	
}
