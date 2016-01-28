package test1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SiteSettings extends seleniumTest{
	
	
	public WebDriver driver;	
	
	public SiteSettings(WebDriver driverCurrent, WriteToFile data){
		
		driver = driverCurrent;
		
		String test;
		String field;
		String fieldNum;
		String logoNew;
		String role;
		int choice;

		boolean pass  = true;

		GeneralSettings gs = new GeneralSettings();
		NavigationSettings nav = new NavigationSettings(driver);
		VisibilitySettings vs = new VisibilitySettings(driver);
		VisibilitySettings man = new VisibilitySettings(driver);
		Roles roles = new Roles(driver);
		Menus menus = new Menus(driver);

	    Users tester = new Users(driver, "Testing Provider");
	    Users admin = new Users(driver, "Admin");
	    Users testnurse = new Users(driver, "Testing Nurse");
	    Users officeadmin = new Users(driver, "Office Admin");
		Users newUser = new Users(driver, "Role", "Tester", "password", "etillson@viimed.com", "New Role");
	    
		email gmail2 = new email();
		
		printTitle("Site Settings", data);
		
/*********************************************** 
*		
*		Go to qa3
*
*
**********************************************/
	    
	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("https://qa3.viinetwork.net/logout");
	    resizeWindow(driver);
		WebDriverWait wait = new WebDriverWait(driver, 10);

/*********************************************** 
*		
*		Site Settings
*		General Settings - Change Logo
*
*
**********************************************/
		
	test = "change logo";
	try{
	logoNew = "INOVA Logo";
	login(admin);
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
		if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	
	WebElement logo = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("img")));
	System.out.println(logo.getSize().getHeight());
	System.out.println(logo.getSize().getWidth());
	if (logo.getSize().getHeight() == 35 && logo.getSize().getWidth() == 224){
			gs.selectLogo(driver, logoNew);
		clickSave(driver, "Save Changes");
	    if(alertPresent(driver, "The information has been saved"))
	    	System.out.println("Saved - alert present");
	    longPause();
		if(safari)
			driver.get("https://qa3.viinetwork.net/admin/site/settings");
		else
			if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
		shortPause();		
		formVisible(driver);
		shortPause();
		pageTitleVisible("Site General Settings");
		longPause();
		WebElement logo2 = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("img")));
		if (logo2.getSize().getHeight() == 35 && logo2.getSize().getWidth() == 138){
			logoNew="QA Logo";
			gs.selectLogo(driver, logoNew);
			clickSave(driver, "Save Changes");
		    if(alertPresent(driver, "The information has been saved"))
		    	System.out.println("Saved - alert present");
			printResult(test, data, "passed");
			}
		else
			printResult(test, data, "failed");
	}
	else
		printResult(test, data, "failed");
	}
	catch(Exception e){
		e.printStackTrace();
		printResult(test, data, "failed");
		logout();
		loginPageLoaded();

	}

/*********************************************** 
*		
*		Site Settings
*		General Settings - No Logo
*
*
**********************************************/
		
test = "No logo";

	try{

	if(!userLoggedIn("Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
		if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
	formVisible(driver);
	longPause();

	if(gs.getLogo(driver) != "No logo selected"){
		gs.selectLogo(driverCurrent, gs.getLogo(driver));	
	    gs.saveChanges(driver);
			if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
		formVisible(driver);
		longPause();
		}
	

	System.out.println(gs.getInputField(driver, "sitename"));
	System.out.println(gs.getTitle(driver));

    if(gs.getTitle(driver).contains(gs.getInputField(driver, "sitename")))
    	printResult(test, data, "passed");
    else
    	printResult(test, data, "failed");
    
    gs.selectLogo(driverCurrent, "QA Logo2");
    gs.saveChanges(driver);
   // logout(driver);
	}
	catch(Exception e){
		printResult(test, data, "failed");
		//logout(driver);
		e.printStackTrace();
	}


/*********************************************** 
*		
*		Site Settings
*		General Settings - No Logo
*
*
**********************************************
	
	test = "timezones";

//This needs to be written after the 2.4 release


/*********************************************** 
*		
*		Site Settings
*		General Settings - Production
*
*
**********************************************/

	test = "Production Toggle";
	
	try{
	
	if(!userLoggedIn("Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");

	else
	goTo("Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	gs.setToggle(driver, "on_production");
    gs.saveChanges(driver);
    
    goTo("Configure", "Content", "Forms");
    tableVisible();
    shortPause();
    Forms form = new Forms(driver, "001 Text Case Owner", new String[]{null, null, "Text Case Onwer", null});
   
    form.formAction(driver, "Update", form.findForm(driver));
    if(pageContainsText(driver, "You don't have permission to access this content"))
    	printResult(test, data, "passed");
    else
    	printResult(test, data, "failed");
    
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	gs.setToggle(driver, "on_production");
    gs.saveChanges(driver);
    
    //logout(driver);
    
	}
	catch(Exception e){
		printResult(test, data, "failed");
		//logout(driver);
		e.printStackTrace();
	}

/*********************************************** 
*		
*		Site Settings
*		General Settings - Themes
*
*
**********************************************
	
	//Still needs to be written
	
/*********************************************** 
*		
*		Site Settings
*		General Settings - Change Homepage
*
*
**********************************************
	test = "Change Homepage";
	
	try{
	
	if(!userLoggedIn(driver, "Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	else
		goToDashboard(driver, "Admin");
	dashboardVisible(driver);
	longPause();
	gs.clickTitle(driver);
	pageVisible(driver);
	
	if(checkIfOnDashboard(driver)){
		
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
		formVisible(driver);
		longPause();
	
		if(gs.getSelectVisibleText(driver, "homepage").contains("Login")){
			System.out.println("Homepage originally set correctly");
			gs.setSelectVisText(driver, "homepage", "Help Page");
			gs.saveChanges(driver);
			gs.clickTitle(driver);
			shortPause();
			gs.clickTitle(driver);
			shortPause();
			if(pageContainsText(driver, "Video Playback Issues"))
		    	printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
			
				if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
			pageTitleVisible(driver, "Site General Settings");
			shortPause();
			
			gs.setSelectVisText(driver, "homepage", "Login Page");
			gs.saveChanges(driver);
		}
		else
			printResult(test, data, "failed");
	}
	else
		printResult(test, data, "failed");
	
    logout(driver);
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
	}
	
/*********************************************** 
*		
*		Site Settings
*		General Settings - Sitewide Notification
*
*
**********************************************
		
	test = "Sitewide Notification";
	
	try{
	if(!userLoggedIn(driver, "Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	gs.setTextField(driverCurrent, "sitewide_notifications", "This is only a test");
	gs.saveChanges(driverCurrent);
	driver.get("https://qa3.viinetwork.net/dashboard");
	dashboardVisible(driver);
	shortPause();
	if(alertExists(driver, "This is only a test"))
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
		if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	gs.setTextField(driverCurrent, "sitewide_notifications", "");
	gs.saveChanges(driverCurrent);
	
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
	}
	
/*********************************************** 
*		
*		Site Settings
*		General Settings - Footer Message
*
*
**********************************************
	
	test = "Footer Message";
	
	try{
	if(!userLoggedIn(driver, "Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	gs.setTextField(driverCurrent, "footer_text", "<p>© 2015 Ford Fiesta</p><span class=\"pull-right muted\"><sub>Powered by BioDiesel</sub></span>");
	gs.saveChanges(driverCurrent);
	driver.get("https://qa3.viinetwork.net/dashboard");
	dashboardVisible(driver);
	shortPause();
	if(getFooterText(driver).contains("Ford Fiesta") && getFooterText(driver).contains("BioDiesel"))
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
		if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	gs.setTextField(driverCurrent, "footer_text", "<p>© 2015 ViiNetwork</p><span class=\"pull-right muted\"><sub>Powered by ViiMed™</sub></span>");
	gs.saveChanges(driverCurrent);
	
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
	}
	
/*********************************************** 
*		
*		Site Settings
*		Navigation Settings - Check Roles
*
*
**********************************************
	
	test = "Navigation - Check Roles";
	try{	
	if(!userLoggedIn(driver, "Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	goTo(driver, "Configure", "People", "Roles");
	tableVisible(driver);
	longPause();
	roles.setRoles();
	
		if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	navigateSubNav(driver, "Navigation");
	longPause();
	
	nav.setNavigationRoles("Secondary Navigation For", "Users With No Role");
	if(roles.compareRoles(nav.getNavigationRoles(), roles.getRoles()))
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
	longPause();
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
		
	}

/*********************************************** 
*		
*		Site Settings
*		Navigation Settings - Check Menus
*
*
**********************************************
		
		test = "Check Menus";
		
		try{
		if(!userLoggedIn(driver, "Admin"))	
			login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
			if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
		formVisible(driver);
		longPause();
		navigateSubNav(driver, "Navigation");
		longPause();
		nav.setRoleMenu("Super Administrator");
		
		
		goTo(driver, "Configure", "Site", "Menus");
		tableVisible(driver);
		menus.clickAction(nav.getRoleMenu(), "View");
		formVisible(driver);
		menus.setMenuItems("Secondary");
		hoverOver(driver, "Configure", "Content");
		nav.setSecondNav();
		
		if(nav.compareMenus(nav.getSecondNav(), menus.getMenuItems("Secondary"), admin.getFirstName())){
			System.out.println("Secondary menu matches settings");
		
		
		goTo(driver, "Configure", "Site", "Menus");
		tableVisible(driver);
		menus.clickAction("Primary Navigation Menu", "View");
		formVisible(driver);
		menus.setMenuItems("Primary");
		
		
		nav.setPrimaryNav();

		if(nav.compareMenus(nav.getPrimNav(), menus.getMenuItems("Primary"),admin.getFirstName())){
			System.out.println("Primary menu matches settings");
			printResult(test, data, "passed");
			}
		else
			printResult(test, data, "failed");
		}
		longPause();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout(driver);
			e.printStackTrace();			
		}
	
/*********************************************** 
*		
*		Site Settings
*		Navigation Settings - Change User Menu
*
*
**********************************************
		
	test = "Change User Menu";
		
	try{			
		if(!userLoggedIn(driver, "Admin"))	
			login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
			if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
		formVisible(driver);
		longPause();
		navigateSubNav(driver, "Navigation");
		longPause();
		
		nav.selectRoleMenu(testnurse.getRole(), "Test Nurse");
		nav.saveChanges();
		longPause();
		
		
		nav.setRoleMenu(testnurse.getRole());
		
		goTo(driver, "Configure", "Site", "Menus");
		tableVisible(driver);
		menus.clickAction(nav.getRoleMenu(), "View");
		formVisible(driver);
		menus.setMenuItems("Secondary");
		
		goTo(driver, "Configure", "Site", "Menus");
		tableVisible(driver);
		menus.clickAction("Primary Navigation Menu", "View");
		formVisible(driver);
		menus.setMenuItems("Primary");
		
		logout(driver);
		login(driver, testnurse.getUsername(), testnurse.getPasswd());
		longPause();
		//hoverOver(driver, "Configure", "Content");
		pageTitleVisible(driver, "Welcome to your dashboard");
		nav.setSecondNav();
		nav.setPrimaryNav();
		
		if(nav.compareMenus(nav.getSecondNav(), menus.getMenuItems("Secondary"), testnurse.getFirstName())){
			System.out.println("Secondary menu matches settings");
			

		if(nav.compareMenus(nav.getPrimNav(), menus.getMenuItems("Primary"),testnurse.getFirstName())){
			System.out.println("Primary menu matches settings");
			
			logout(driver);
			
			login(driver, admin.getUsername(), admin.getPasswd());
			longPause();
			
		if(safari)
			driver.get("https://qa3.viinetwork.net/admin/site/settings");
		else
			goTo(driver, "Configure", "Site", "Settings");
			formVisible(driver);
			longPause();
			navigateSubNav(driver, "Navigation");
			longPause();
			nav.selectRoleMenu(testnurse.getRole(), "Test Patient");
			nav.saveChanges();
			
			goTo(driver, "Configure", "Site", "Menus");
			tableVisible(driver);
			menus.clickAction(nav.getRoleMenu(), "View");
			formVisible(driver);
			menus.setMenuItems("Secondary");
			
			logout(driver);
			login(driver, testnurse.getUsername(), testnurse.getPasswd());
			
			longPause();
			pageTitleVisible(driver, "Welcome to your dashboard");
			nav.setSecondNav();
			nav.setPrimaryNav();
			
				if(nav.compareMenus(nav.getSecondNav(), menus.getMenuItems("Secondary"), testnurse.getFirstName())){
					System.out.println("Secondary menu matches settings");
					
	
				if(nav.compareMenus(nav.getPrimNav(), menus.getMenuItems("Primary"),testnurse.getFirstName())){
					System.out.println("Primary menu matches settings");
					printResult(test, data, "passed");
					}
					else
						printResult(test, data, "failed1");
				}
				else
					printResult(test, data, "failed2");
		
			}
			else
				printResult(test, data, "failed3");
		}
		else
			printResult(test, data, "failed4");
		
		logout(driver);
		login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
		
			if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
		formVisible(driver);
		longPause();
		navigateSubNav(driver, "Navigation");
		longPause();
		nav.selectRoleMenu(testnurse.getRole(), "Test Nurse");
		nav.saveChanges();
	}
		catch(Exception e){
			printResult(test, data, "failed by exception");
			logout(driver);
			e.printStackTrace();
			
		}

/*********************************************** 
*		
*		Site Settings
*		Navigation Settings - Default Secondary Menu
*
*
**********************************************
	
	test = "Default Secondary Menu";
	
	try{			
		if(!userLoggedIn(driver, "Admin"))	
			login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
		formVisible(driver);
		longPause();
		navigateSubNav(driver, "Navigation");
		longPause();
		
		nav.selectRoleMenu(tester.getRole(), "Select");
		nav.saveChanges();
		longPause();
		
		menus.setMenuItems("Default");
		
		logout(driver);
		login(driver, tester.getUsername(), tester.getPasswd());
		
		longPause();

		nav.setSecondNav();
		nav.setPrimaryNav();
		
		if(nav.compareMenus(nav.getSecondNav(), menus.getMenuItems("Secondary"), testnurse.getFirstName())){
			System.out.println("Secondary menu matches settings");
			

		if(nav.compareMenus(nav.getPrimNav(), menus.getMenuItems("Primary"),testnurse.getFirstName())){
			System.out.println("Primary menu matches settings");
			printResult(test, data, "passed");
			}
			else
				printResult(test, data, "failed1");
		}
		else
			printResult(test, data, "failed2");
	
		logout(driver);
		login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
		
			if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo(driver, "Configure", "Site", "Settings");
		formVisible(driver);
		longPause();
		navigateSubNav(driver, "Navigation");
		longPause();
		nav.selectRoleMenu(testnurse.getRole(), "Test Admin");
		nav.saveChanges();
		
	}
	catch(Exception e){
		printResult(test, data, "failed by exception");
		logout(driver);
		e.printStackTrace();
		
	}

/*********************************************** 
*		
*		Site Settings
*		Visibility Settings - Check Roles
*
*
**********************************************/
	
	//Need to look into the outofbounds issue
	
	test = "Visibility - Check Roles";
	
	try{	
	if(!userLoggedIn("Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	goTo("Configure", "People", "Roles");
	tableVisible();
	longPause();
	roles.setRoles();
	
		if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	navigateSubNav(driver, "Visibility");
	longPause();
	
	vs.setVisibilityRoles("", "No Exclusion Text");
	if(roles.compareRoles(vs.getVisibilityRoles(), roles.getRoles()))
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
	longPause();
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
		
	}

/*********************************************** 
*		
*		Site Settings
*		Visibility Settings - Check Sub Roles
*
*
**********************************************/
	
	//Need to look into the endless looping issue
	
test = "Visibility - Check Sub Roles";
	
	try{	
	if(!userLoggedIn("Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	goTo("Configure", "People", "Roles");
	tableVisible();
	longPause();
	roles.setRoles();
	
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	navigateSubNav(driver, "Visibility");
	longPause();
	
	vs.setVisibilityRoles("", "No Exclusion Text");
		
	if(vs.checkSubRolesLists(driver,  "All Roles", roles.getRoles()))
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
	longPause();
	}
	
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
		
	}

/*********************************************** 
*		
*		Site Settings
*		Management Settings - Check Roles
*
*
**********************************************/
	

	
	test = "Management - Check Roles";
	
	try{	
	if(!userLoggedIn("Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	goTo("Configure", "People", "Roles");
	tableVisible();
	longPause();
	roles.setRoles();
	
		if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	navigateSubNav(driver, "Management");
	longPause();
	
	man.setVisibilityRoles("", "No Exclusion Text");
	if(roles.compareRoles(man.getVisibilityRoles(), roles.getRoles()))
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
	longPause();
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
		
	}

/*********************************************** 
*		
*		Site Settings
*		Management Settings - Check Sub Roles
*
*
**********************************************/

	
test = "Management - Check Sub Roles";
	
	try{	
	if(!userLoggedIn("Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	goTo("Configure", "People", "Roles");
	tableVisible();
	longPause();
	roles.setRoles();
	
	if(safari)
		driver.get("https://qa3.viinetwork.net/admin/site/settings");
	else
	goTo("Configure", "Site", "Settings");
	formVisible(driver);
	longPause();
	
	navigateSubNav(driver, "Management");
	longPause();
	
	man.setVisibilityRoles("", "No Exclusion Text");
		
	if(vs.checkSubRolesLists(driver,  "All Roles", roles.getRoles()))
		printResult(test, data, "passed");
	else
		printResult(test, data, "failed");
	longPause();
	}
	
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
		
	}


/*********************************************** 
*		
*		Site Settings
*		Add Role and Add Visibility
*
*
**********************************************/

	test = "Add Role and Add Visibility";

	
	try{	
	if(!userLoggedIn("Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	//Create a new role
	goTo("Configure", "People", "Roles");
	formVisible(driver);
	longPause();
	roles.setRoles();
	if(!roles.roleExists("New Role"))
		roles.deleteRole("New Role");
	navigateSubNav(driver, "Add Role");
	shortPause();
	field = "Name";
	fieldNum = getFieldNumAngular(driver, field);
	setField(driver, fieldNum, "New Role");		
	clickSave(driver, "Save Role");
	shortPause();
	logout(driver);
	login(driver, officeadmin.getUsername(), officeadmin.getPasswd());
	goTo("Testing","Testing Menu");
	getTest("User Select");
	shortPause();
	field = "User Select";
	fieldNum = getFieldNum(driver, field);
	if(!checkSelectOptions(driver, newUser.getLastName() + ", " +newUser.getFirstName(), fieldNum)){
	
		logout();
		login(admin);
		//Create a new user with the role New Role
		
		goTo("Configure", "People", "Roles");
		navigateSubNav(driver, "Add User");
		shortPause();
		
	
		newUser.addUser(driver);
		System.out.println(newUser.getUsername());
		longPause();
		goTo("Configure", "Site", "Settings");
		formVisible(driver);
		longPause();	
		navigateSubNav(driver, "Visibility");
		longPause();
		
		if(vs.selectVisibilityCheckbox("Office Administrator", "New Role")){
			logout(driver);
			login(driver, officeadmin.getUsername(), officeadmin.getPasswd());
			goTo("Testing","Testing Menu");
			getTest("User Select");
			shortPause();
			field = "User Select";
			if(checkSelectOptions(driver, newUser.getLastName() + ", " +newUser.getFirstName(), getFieldNum(driver, field))){
				printResult("Add Role", data, "passed");
				
				// Unchecks the role from the user visibility page
				logout();
				login(admin);
				goTo("Configure", "Site", "Settings");
				formVisible(driver);
				longPause();	
				navigateSubNav(driver, "Visibility");
				longPause();
				vs.selectVisibilityCheckbox("Office Administrator", "New Role");
			}
		}
		else
			printResult(test, data, "failed, user not visible");
	}
	else
		printResult(test, data, "failed due to user already existing");
		
	
	}
	
	catch(Exception e){
		printResult(test, data, "failed");
		logout();
		e.printStackTrace();
		
	}

/*********************************************** 
*		
*		Site Settings
*		Add Management
*
*
**********************************************/		
	
	test = "Add Management";
	
	try{	
	if(!userLoggedIn("Office"))	
		login(officeadmin);
	shortPause();
	goToUserAdminPage();

					
	
	shortPause();
	if(!findUserAdminPage(driver, newUser.getUsername())){
		logout(driver);
		login(driver, admin.getUsername(), admin.getPasswd());
		shortPause();
		goTo("Configure", "Site", "Settings");
		formVisible(driver);
		longPause();
		navigateSubNav(driver, "Management");
		longPause();
		if(vs.selectVisibilityCheckbox("Office Administrator", "New Role")){
			logout(driver);
			login(driver, officeadmin.getUsername(), officeadmin.getPasswd());
			shortPause();
			goToUserAdminPage();
			shortPause();
			tableVisible();
			pageTitleVisible("Manage Users");
			String [] filters = {"null", "null", "null", "null", "null", "New Role", "null", "null"};
			Filters userFilter2 = new Filters(driver, filters, "users");
			shortPause();
			
			if(findUserAdminPage(driver, newUser.getUsername())){
				printResult(test, data, "passed");
				
				// Unchecks the role from the user management page
				logout(driver);
				login(driver, admin.getUsername(), admin.getPasswd());
				goTo("Configure", "Site", "Settings");
				formVisible(driver);
				longPause();	
				navigateSubNav(driver, "Management");
				longPause();
				man.selectVisibilityCheckbox("Office Administrator", "New Role");
			
			}
			else
				printResult(test, data, "failed, user not visible on admin page");
		}
		else
			printResult(test, data, "failed, checkbox not found");
	}
	else		
		printResult(test, data, "failed, user already visible on admin page");	
	
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
		
	}
				
	
	
/*********************************************** 
*		
*		Site Settings
*		Delete Role and Delete User
*
*
**********************************************/	
	
	// Tests to see
	
	test = "Delete Role and Delete User";
	
	try{
		
	if(!userLoggedIn("Admin"))	
		login(driver, admin.getUsername(), admin.getPasswd());
	
	//Delete the newly created Role
	goTo("Configure", "People", "Roles");
	formVisible(driver);
	
	navigateSubNav(driver, "Manage Roles");
	roles.deleteRole("New Role");
	shortPause();
	if(alertPresent(driver, "The role \"New Role\" is in use and cannot be deleted.")){
	    	System.out.println("alert present");
	
		deleteUser(newUser);
		goTo("Configure", "People", "Roles");
		formVisible(driver);
		
		navigateSubNav(driver, "Manage Roles");
		roles.deleteRole("New Role");
		if(alertPresent(driver, "The selected roles were deleted.")){
	    	System.out.println("alert present");
	    	printResult(test, data, "passed");
		}
		else
			printResult(test, data, "failed, no alert present");
		
	}
	else
		printResult(test, data, "failed, role delete alert not present");
	
	}
	catch(Exception e){
		printResult(test, data, "failed");
		logout(driver);
		e.printStackTrace();
		
	}
	
	
	

	
	
	
/*********************************************** 
*		
*		Site Settings
*		Default Email - Test 26
*
*
**********************************************/

		test = "26 DefaultEmail";


		try{
			
		if(!userLoggedIn("Admin"))	
			login(driver, admin.getUsername(), admin.getPasswd());
		
		
		
		if (gmail2.checkAllEmails("ViiNetwork QA"))
			gmail2.clickDelete();
		gmail2.refreshEmail();
		longPause();	
		if (gmail2.checkAllEmails("Mr. Tester"))
			gmail2.clickDelete();
		
		
		
		Telecom test26 = new Telecom();
		test26.openTelecom(driver);


		shortPause();
		test26.setDefaultEamil(driver, "ViiMed@viinetwork.com");
		test26.setDefaultName(driver, "Mr. Tester");
		test26.setActivationSubject(driver, "Testing Activation");
		test26.setActivationConfirmation(driver, "Testing Confirmation Activation");
		test26.saveChanges(driver);

	    navigateSubNav(driver, "General");
	    formVisible(driver);
	    
	    gs.setInputField(driver, "site_org", "Pee Wee");
	    gs.setInputField(driver, "sitename", "Big Top");
	    gs.saveChanges(driver);
	    	
	    
		goToQa();
		getTest(test);
		
		Users patient2 = new Users(driver, "Enrique", "Iglesias", "!viimed689#", "etillson@viimed.com");
		
		//Set fields for new user
		field = "Patient ID";
		fieldNum = getFieldNum(driver, field);
		System.out.println(fieldNum);
		setField(driver, fieldNum, patient2.getPatientId());
		field = "First name";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient2.getFirstName());
		field = "Last name";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient2.getLastName());				
		field = "Email";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient2.getEmail());
		field = "Username";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient2.getUsername());
		field = "Patient Activation";
		choice = 2;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice);
		clickContinue(driver);
		field = "Case Type";
		choice = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice);
		clickContinue(driver);
		field = "QA Testing Provider";
		choice = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice);
		submit();
		
		minutePause();
		gmail2.refreshEmail();
		shortPause();
		shortPause();
		longPause();


		if (gmail2.checkNameEmail("Mr. Tester", "Testing Activation"))
			System.out.println("Subject,name, and address are correct");
		else{
			gmail2.refreshEmail();
			shortPause();
			shortPause();
			longPause();
			if (gmail2.checkNameEmail("Mr. Tester", "Testing Activation"))
				System.out.println("Subject,name, and address are correct");
			else{
				pass = false;
				System.out.println("test failure");
			}
		}
		
		gmail2.openEmail("Testing Activation", "Mr. Tester");
		
		if(gmail2.getBodyText().contains(patient2.getFirstName() + " " + patient2.getLastName()) && 
				gmail2.getBodyText().contains(patient2.getUsername()) && 
				gmail2.getBodyText().contains("Welcome to Pee Wee's Big Top"))
				System.out.println("Email contains username, patient name, site name, and org name");
		else{
			pass = false;
			System.out.println("test failure");
		}

		gmail2.activate();  
		gmail2.newPassword(patient2.getUsername(), patient2.getPasswd());
		
		gmail2.closeWindow();
		gmail2.switchToParent();
		gmail2.backButton();
		minutePause();
		
		if (gmail2.checkSubject("Testing Confirmation Activation"))
			System.out.println("Subject of Activation Confirmation correct");
		else{
			pass = false;
			System.out.println("test failure");
		}
		
		logout(driver, "Admin");
		//*/
		
		
		login(driver, admin.getUsername(), admin.getPasswd());
		
		
		
		test26.openTelecom(driver);
		formVisible(driver);
		test26.setDefaultEamil(driver, "noreply@viinetwork.net");
		test26.setDefaultName(driver, "ViiNetwork QA");
		test26.setActivationSubject(driver, "Activate your account");
		test26.setActivationConfirmation(driver, "\"Your account has been activated\"");
		test26.saveChanges(driver);
		
	    navigateSubNav(driver, "General");
	    formVisible(driver);
		gs.setInputField(driver, "site_org", "Kramer");
	    gs.setInputField(driver, "sitename", "qa3");
	    gs.saveChanges(driver);
			    
		logout(driver);
		
		if (pass == true)
			printResult(test, data, "passed");
		else
			printResult(test, data, "failed");
		
		if (gmail2.checkAllEmails("ViiNetwork QA"))
			gmail2.clickDelete();
		
		gmail2.refreshEmail();
		longPause();
		
		if (gmail2.checkAllEmails("Mr. Tester"))
			gmail2.clickDelete();
		
		}
		catch(Exception e){
			
			printResult(test, data, "failed");
			e.printStackTrace();
			
			logout(driver);
			
			if (gmail2.checkAllEmails("ViiNetwork QA"))
				gmail2.clickDelete();
			gmail2.refreshEmail();
			longPause();	
			if (gmail2.checkAllEmails("Mr. Tester"))
				gmail2.clickDelete();

		}		
	}	
}