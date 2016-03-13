package test1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Users extends seleniumTest{

// 		<<<  Random Class  >>>

	RandomGen rand = new RandomGen();
	
//		<<<  User Info  >>>	
	
	String firstName;
	String lastName;
	String username;
	String passwd;
	String userId;
	String patientId;
	String email;
	String phonenumber;
	String role;
	String tempCaseId;
	String[][] patientCases;
	

	
//		<<< Constructors  >>>
	
public Users(WebDriver driver, String firstName, String lastName, String passwd, String email){
	setFirstName(driver, firstName);
	setLastName(driver, lastName);
	randomUsername(driver);
	setPasswd(driver, passwd);
	setEmail(driver, email);	
	setPatientId(driver);
}

public Users(WebDriver driver, String firstName, String lastName, String passwd, String email, String role){
	setFirstName(driver, firstName);
	setLastName(driver, lastName);
	randomUsername(driver);
	setPasswd(driver, passwd);
	setEmail(driver, email);	
	setPatientId(driver);
	setRole(driver, role);
}

public Users(WebDriver driver, String firstName, String lastName, String passwd, String username, String email, String role){
	setFirstName(driver, firstName);
	setLastName(driver, lastName);
	setUserName(driver, username);
	setPasswd(driver, passwd);
	setEmail(driver, email);	
	setPatientId(driver);
	setRole(driver, role);
}


// Used to objects for permanent users
public Users(WebDriver driver, String user){
	
	if (user.equalsIgnoreCase("testing provider"))
	{
		setFirstName(driver, "Testing");
		setLastName(driver, "Provider");
		setUserName(driver, "testing provider");
		setUserId(driver, "132");
		setPasswd(driver, "viimedtester");
		setEmail(driver, "testingprovider@viimed.com");	
		setRole(driver, "Test Provider");
	}
	else if (user.equalsIgnoreCase("Provider Partner"))
	{
		setFirstName(driver, "Partner");
		setLastName(driver, "Provider");
		setUserName(driver, "provider partner");
		setUserId(driver, "3129");
		setPasswd(driver, "viimedtester");
		setEmail(driver, "etillson@viimed.com");	
		
	}	
	
	else if (user.equalsIgnoreCase("admin"))
	{
		setFirstName(driver, "Admin");
		setLastName(driver, "4");
		setUserName(driver, "admin4");
		setUserId(driver, "249");
		setPasswd(driver, "3INecuador");
		setEmail(driver, "etillson@viimed.com");
		setUserPhone(driver, "301-221-0217");
		
	}
	else if (user.equalsIgnoreCase("admin6"))
	{
		setFirstName(driver, "Admin");
		setLastName(driver, "6");
		setUserName(driver, "admin6");
		setUserId(driver, "249");
		setPasswd(driver, "password");
		setEmail(driver, "etillson@viimed.com");
		setUserPhone(driver, "301-221-0217");
		
	}
	else if (user.equalsIgnoreCase("testUser"))
	{
		setFirstName(driver, "Enrique");
		setLastName(driver, "Iglesias");
		setUserName(driver, "hybkdruonq");
		setUserId(driver, "249");
		setPasswd(driver, "!viimed689#");
		setEmail(driver, "etillson@viimed.com");
		setUserPhone(driver, "301-221-0217");
		
	}
	else if (user.equalsIgnoreCase("nonimportantUser"))
	{
		setFirstName(driver, "Enrique");
		setLastName(driver, "Iglesias");
		setUserName(driver, "yygmjsujoo");
		setUserId(driver, "249");
		setPasswd(driver, "!viimed689#");
		setEmail(driver, "etillson@viimed.com");
		setUserPhone(driver, "301-221-0217");
		
	}
	else if (user.equalsIgnoreCase("testing admin"))
	{
		setFirstName(driver, "Tester");
		setLastName(driver, "Admin");
		setUserName(driver, "testing admin");
		setUserId(driver, "130");
		setPasswd(driver, "viimedtester");
		setEmail(driver, "TestAdmin@viimed.com");		
	}
	else if (user.equalsIgnoreCase("partner"))
	{
		setFirstName(driver, "Partner");
		setLastName(driver, "Testing");
		setUserName(driver, "testing partner");
		setUserId(driver, "546");
		setPasswd(driver, "viimedtester");
		setEmail(driver, "TestAdmin@viimed.com");	
		
	}
	else if (user.equalsIgnoreCase("testing patient"))
	{
		setFirstName(driver, "Testing");
		setLastName(driver, "Patient");
		setUserName(driver, "testing patient");
		setUserId(driver, "133");
		setPasswd(driver, "viimedtester");
		setEmail(driver, "testingpatient@viimed.com");	
		
	}
	else if (user.equalsIgnoreCase("hip patient"))
	{
		setFirstName(driver, "Hip");
		setLastName(driver, "Patient");
		setUserName(driver, "hip patient" + rand.randomId(4));
		setPasswd(driver, "viimedtester");
		setEmail(driver, "etillson@viimed.com");	
		
	}
	else if (user.equalsIgnoreCase("testing nurse"))
	{
		setFirstName(driver, "Testing");
		setLastName(driver, "Nurse");
		setUserName(driver, "Testing Nurse");
		setUserId(driver, "921");
		setPasswd(driver, "viimedtester");
		setEmail(driver, "etillson@viimed.com");
		setRole(driver, "Registered Nurse");
		
	}
	else if (user.equalsIgnoreCase("office admin"))
	{
		setFirstName(driver, "Office");
		setLastName(driver, "Admin");
		setUserName(driver, "Office Admin");
		setUserId(driver, "3265");
		setPasswd(driver, "viimedtester");
		setEmail(driver, "etillson@viimed.com");
		setRole(driver, "Office Administrator");
		
	}
	else if (user.equalsIgnoreCase("surgical coordinator"))
	{
		setFirstName(driver, "Surgical");
		setLastName(driver, "Coordinator");
		setUserName(driver, "Surgical Coordinator");
		setUserId(driver, "3650");
		setPasswd(driver, "password");
		setEmail(driver, "etillson@viimed.com");
		setRole(driver, "Surgical Coordinator");
		
	}
	else
		System.out.println("Invalid User");
}




//		<<<  set variables	>>>


public void setFirstName(WebDriver driver, String firstNameUser){
	firstName = firstNameUser;	
}

public void setLastName(WebDriver driver, String lastNameUser){
	lastName = lastNameUser;	
}

public void setUserName(WebDriver driver, String userNameUser){
	username = userNameUser;	
}

public void setPatientId(WebDriver driver){
	
	patientId = randomId();
}

public void setPasswd(WebDriver driver, String passwdUser){	
	passwd = passwdUser;
}

public void setEmail(WebDriver driver, String emailUser){
	email = emailUser;	
}

public void setUserId(WebDriver driver, String userIdUser){
	userId = userIdUser;	
}

public void setUserPhone(WebDriver driver, String userPhone){
	phonenumber = userPhone;	
}

public void setRole(WebDriver driver, String userRole){
	role = userRole;	
}

public void setUsername(WebDriver driver, String newUsername){
	username = newUsername;	
}

//		<<<  get variables  >>>


public String getPatientId(){
	return patientId;
}

public String getUserId(){
	return userId;
}

public String getUsername(){
	return username;
}

public String getPasswd(){
	return passwd;
}

public String getEmail(){
	return email;	
}

public String getFirstName(){
	return firstName;
}

public String getLastName(){
	return lastName;
}

public String getPhoneNumber(){
	return phonenumber;
}

public String getRole(){
	return role;
}

//		<<<  random generating functions  >>>



public void randomUsername(WebDriver driver){
	String alphabet = new String("abcdefghijklmnopqrstuvwxyz"); 
	int n = alphabet.length(); 

	String result = new String(); 
	Random r = new Random(); 

	for (int i=0; i<10; i++) 
	    result = result + alphabet.charAt(r.nextInt(n)); 

	setUserName(driver, result);
}

public String randomId(){
	
	int randId = 1 + (int)(Math.random()* 999999999);
	return Integer.toString(randId);
	
}




//		<<<  Navigation  >>>


public void goToUsers(WebDriver driver){
	WebElement admin = driver.findElement(By.xpath("//a[contains(text(), 'Configure')]"));
	WebElement people = driver.findElement(By.xpath("//a[contains(text(), 'People')]"));
	WebElement users = driver.findElement(By.xpath("//a[contains(text(), 'Users')]"));
	Actions select = new Actions(driver);
		select.moveToElement(admin);
		select.moveToElement(people);
		select.moveToElement(users).click().perform();
	shortPause();	
}

public void goToNext(WebDriver driver){
	try{
		WebElement element = driver.findElement(By.xpath("//a[contains(text(), 'Next')]"));
		Actions select = new Actions(driver);
		select.moveToElement(element).click().perform();
	}
	catch(Exception e){
		System.out.println("There is no next");
	}
}



//		<<<   User Management  >>>


public void deleteUser(WebDriver driver, String username){

	try{

	WebElement userList = driver.findElement(By.xpath("//tr[td[contains(text(), '" + username + "')]]/td[7]//span[contains(text(), 'delete')]"));
	WebElement delete = driver.findElement(By.xpath("//tr[td[contains(text(), '" + username + "')]]/td[7]//span[contains(text(), 'delete')]"));
	System.out.println(userList.getText());
	//delete.click();
	shortPause();
	checkAlert(driver);
	shortPause();
	/*try{
	WebElement userGone = driver.findElement(By.xpath("//tr[td[contains(text(), '" + username + "')]"));
	System.out.println("User not deleted");
	}
	catch (Exception e) {
		System.out.println("User deleted");
		shortPause();
		}
		*/
	
	//*[@id="ngApp"]/div/div/div/div/div/div[3]/vii-resource-list/resource-table/div[3]/div/table/tbody/tr[25]/td[10]/span/span/span[3]/span/span/a/span
	//*[@id="ngApp"]/div/div/div/div/div/div[3]/vii-resource-list/resource-table/div[3]/div/table/tbody/tr[25]/td[3]/span/span
	}
	
	catch(Exception e){
		System.out.println("User does not exist");
		
	}	
}

public void setCases(WebDriver driver){
	try{
		System.out.println("hello");
	if(!driver.getCurrentUrl().contains("/admin/users/list"))
		goTo("Configure", "People", "Users");
	System.out.println("hello");
	shortPause();
	tableVisible();
	WebElement poo = findListItemByName(username, driver, "user").findElement(By.xpath(".//span[contains(text(), 'View')]"));
	System.out.println(poo.getText());
	poo.click();
	shortPause();
	/*String[][] tempArray = {caseTableColumn(driver, "case"), caseTableColumn(driver, "actions")};
	patientCases = tempArray;
	System.out.println(patientCases.length);*/
	WebElement userProf = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.cssSelector("user-profile")));
	System.out.println(userProf.getText());

	List <WebElement> caseLink = userProf.findElements(By.xpath(".//a[contains(@href, 'case')]"));
	String [] caseID = new String [caseLink.size()];
	String [] caseName = new String [caseLink.size()];
	for(int x = 0; x < caseLink.size(); x++){
		caseID[x] = caseLink.get(x).getAttribute("href").replaceAll("[^0-9]", "");
		caseName[x] = caseLink.get(x).getText();
		System.out.println(caseLink.get(x).getAttribute("href").replaceAll("[^0-9]", ""));
		System.out.println(caseLink.get(x).getText());
	}
	String[][] tempArray = {caseID, caseName};
	patientCases = tempArray;

	
	}
	catch(Exception e){
		e.printStackTrace();
		
	}
}

public void showCases(WebDriver driver){
	for(int i=0; i<patientCases.length; i++)
	{
	    for(int j=0; j<patientCases[0].length; j++){
	        System.out.print(patientCases[i][j]+ " ");
	     System.out.println(i + " " + j);   
	    }
	    	
	}
			
	
	
}



public String getCaseId(WebDriver driver, String patientCase){
	for(int i=0; i<patientCases.length; i++){
		if(patientCases[0][i].equalsIgnoreCase(patientCase)){
			return patientCases[1][i];
		}
	}
	return null;
}

public String[] caseTableColumn (WebDriver driver, String column){
	List <WebElement> cases = driver.findElements(By.cssSelector("td.column-"+ column));
	String[] tempArray = new String[cases.size()];
	if(column.equalsIgnoreCase("case")){
		for(int x =0; x < cases.size(); x++){
			System.out.println(cases.get(x).getText().replaceAll(".*: ", ""));
			tempArray[x] = cases.get(x).getText().replaceAll(".*: ", "");
		}
		return tempArray;
	}
	if(column.equalsIgnoreCase("actions")){
		System.out.println("hello");
		for(int x =0; x < cases.size(); x++){
			System.out.println(cases.get(x).getText());
			String temp = cases.get(x).findElement(By.xpath(".//a")).getAttribute("href");
			temp = temp.replaceAll("[^0-9]", "");
			System.out.println(temp);
			tempArray[x] = temp;
		}
		return tempArray;
	}
	return null;
	
	
}

//public String returnCase

public void findUserId(WebDriver driver){
	try{
	boolean found = false;
	List<WebElement> total = driver.findElements(By.cssSelector("td"));
	System.out.println(total.size());
	for (int k=0; k < total.size(); k++){
		System.out.println(k);
		System.out.println(total.get(k).getText());
		if (total.get(k).getText().equalsIgnoreCase(username)){
			System.out.println(k);
			System.out.println(total.get(k).getText());
			WebElement poo = total.get(k+7).findElement(By.xpath(".//a"));
			String id = poo.getAttribute("href").replace("qa3", "").replaceAll("[^0-9]", "");
			System.out.println(id);
			userId = id;
			found = true;
		}
	}
	if(!found && next(driver)){
		longPause();
		if(ipad){
			longPause();
			longPause();
		}
		System.out.println("next");
                                  
		findUserId(driver);
	}
	}
	catch(Exception e){
		e.printStackTrace();
	}
}

public void findCaseId(WebDriver driver){
	boolean found = false;
	
	
}


public void clickNext(WebDriver driver){
	WebElement next = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
			(By.xpath("//a[contains(text(), 'Next')]")));
	next.click();
}


public void addUser(WebDriver driver){
	
	String field;
	String fieldNum;
	
	field = "First Name";
	fieldNum = getFieldNumAngular(driver, field);
	setField(driver, fieldNum, getFirstName());	
	field = "Last Name";
	fieldNum = getFieldNumAngular(driver, field);
	setField(driver, fieldNum, getLastName());	
	field = "Email";
	fieldNum = getFieldNumAngular(driver, field);
	setField(driver, fieldNum, getEmail());	
	field = "Username";
	fieldNum = getFieldNumAngular(driver, field);
	setField(driver, fieldNum, getUsername());
	
	field = "Role";
	fieldNum = getFieldNumAngular(driver, field);
	getSelectVisText(driver, fieldNum, getRole());
	
	field = "Activate Account?";
	fieldNum = getFieldNumAngular(driver, field);
	clickToggle(driver, fieldNum);
	
	field = "Password";
	fieldNum = getFieldNumAngular(driver, field);
	setField(driver, fieldNum, getPasswd());
	
	field = "Confirm Password";
	fieldNum = getFieldNumAngularNoBinding(driver, field);
	setField(driver, fieldNum, getPasswd());
	shortPause();
	
	clickSave(driver, "Save User");
	shortPause();
	
	
	
}

}
