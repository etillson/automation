package test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Menus extends seleniumTest{
	
	WebDriver driver;
	List <String> systemMenus = new ArrayList <String>();
	List <String> currentMenuItemsPrimary = new ArrayList <String>();
	List <String> currentMenuItemsSecondary = new ArrayList <String>();
	
public Menus(WebDriver driverNew){
	
	driver = driverNew;
}

public List <String> getMenus(){
	
	return systemMenus;
}


public WebElement getActionMenu(String name){
	List<WebElement> menus = driver.findElements(By.cssSelector("tr td"));
	for(int x = 0; x<menus.size(); x++){
		if(menus.get(x).getText().contains(name)){
			System.out.println(menus.get(x).getText());
			return menus.get(x+3);			
			}			
		}
	System.out.println("Menu list action not found");
	return null;
}

public List <String> getMenuItems(String menu){
	if(menu == "Primary")
		return currentMenuItemsPrimary;
	if(menu == "Secondary")
		return currentMenuItemsSecondary;
	else
		return null;
}

public void clickAction(String name, String action){
	System.out.println(getActionMenu(name).getText());
	getActionMenu(name).findElement(By.cssSelector("a[title="+ action + "]")).click();
}


public int countMenus(List <WebElement> menus){
	return menus.size();
	
}

public void setMenus(){
	List<WebElement> menus = driver.findElements(By.cssSelector("tr td:nth-of-type(2)"));
	for(int x = 0; x<menus.size(); x++){
		System.out.println(menus.get(x).getText());
		systemMenus.add(x, menus.get(x).getText());
			}
}

public void setMenuItems(String menu){
	if(menu.contentEquals("Primary"))
		currentMenuItemsPrimary.clear();
	if(menu.contentEquals("Secondary"))
		currentMenuItemsSecondary.clear();
	if(menu.contentEquals("Default")){
		currentMenuItemsSecondary.clear();
		currentMenuItemsSecondary.addAll(Arrays.asList("@fname@", "Dashboard", "Tasks", "Logout"));
	}
	if(!menu.contentEquals("Default")){
	List<WebElement> menuItems = driver.findElements(By.cssSelector("div[class*='angular-ui-tree-handle']"));
	for(int x = 0; x<menuItems.size(); x++){
		System.out.println(menuItems.get(x).getText());
		if(menu == "Primary")
			currentMenuItemsPrimary.add(x, menuItems.get(x).getText());
		if(menu =="Secondary")
			currentMenuItemsSecondary.add(x, menuItems.get(x).getText());
			}
	}
}



}
