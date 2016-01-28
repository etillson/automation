package test1;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class EnrollPatients extends seleniumTest{

public WebDriver driver;
public String field;
public String fieldNum;
public int choice;
public String test;
public String password;
public int choiceRole = 2;  // 1 = Super Admin, 2 = Patient, 15 = Test Provider
public String tag = "loadpatient";

Users admin = new Users(driver, "Admin");

	public EnrollPatients(WebDriver driverCurrent) throws IOException {
		driver = driverCurrent;
		
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    //driver.manage().window().setPosition(new Point(0, 0));
	    driver.manage().window().setSize(new Dimension(1920, 1080));
	    
		driver.get("https://newqa.viinetwork.net/logout");
		
		
		
		CalendarDate now = new CalendarDate();
		now.setDate(driver);
		String file_name = "/Users/erictillson/Documents/NewUsers/" + "PatientList " + now.getToday() + ".csv";
		WriteToFile users = new WriteToFile( file_name , true );
		
		RandomGen passGen = new RandomGen();
		

		
		login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
		
		
		for(int x = 664; x < 2001; x++){
			
		password = passGen.randomPassword();
		
	
		
		Users patientInherit = new Users(driver, "Test", "Patient" + x, password, "etillson@viimed.com");
		
		
		
		System.out.println(patientInherit.getUsername() + " " + password);
				
		
		driver.get("https://newqa.viinetwork.net/admin/r/users/create#/resource");
		
		WebElement title = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//p[contains(text(), 'Create a user')]")));
		
		longPause();
		//Set patient information
		field = "First Name";
		fieldNum = getFieldNumAngular(driver, field);
		setField(driver, fieldNum, patientInherit.getFirstName());

		field = "Last Name";
		fieldNum = getFieldNumAngular(driver, field);
		setField(driver, fieldNum, patientInherit.getLastName());				
		
		field = "Email";
		fieldNum = getFieldNumAngular(driver, field);
		setField(driver, fieldNum, patientInherit.getEmail());
		
		field = "Username";
		fieldNum = getFieldNumAngular(driver, field);
		setField(driver, fieldNum, patientInherit.getUsername());
		
		field = "External System Identifier";
		fieldNum = getFieldNumAngular(driver, field);
		setField(driver, fieldNum, patientInherit.getPatientId());
		shortPause();
		
		field = "Role";
		fieldNum = getFieldNumAngular(driver, field);
		getSelect(driver, fieldNum, choiceRole);
		shortPause();
		
		field = "Tags";
		tagSet(driver, tag);
		
		//Activate now toggle		
		field = "Activate Account?";
		WebElement toggle = getToggleTitle(driver, field);
		toggle.click();
		
		//Set password
		field = "Password";
		fieldNum = getFieldNumAngular(driver, field);
		setField(driver, fieldNum, patientInherit.getPasswd());
		
		field = "Confirm Password";
		fieldNum = getFieldNumAngularNoBinding(driver, field);
		setField(driver, fieldNum, patientInherit.getPasswd());
		shortPause();
		
		submitAngular(driver);
		longPause();
		
		
		/*goToQa(driver);
		shortPause();
		getTest(driver, test);
		shortPause();
		
		
		field = "Patient ID";
		fieldNum = getFieldNum(driver, field);
		System.out.println(fieldNum);
		setField(driver, fieldNum, patientInherit.getPatientId());
		field = "First name";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patientInherit.getFirstName());
		field = "Last name";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patientInherit.getLastName());				
		field = "Email";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patientInherit.getEmail());
		field = "Username";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patientInherit.getUsername());
		field = "Patient Activation";
		choice = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice);
		
		clickButton(driver, "patientactivation");
		field = "Password";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patientInherit.getPasswd());
		field = "Confirm Password";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patientInherit.getPasswd());
		shortPause();
		clickContinue(driver);
		shortPause();
		field = "Case Type";
		choice = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice);
		clickContinue(driver);
		field = "QA Testing Provider";
		choice = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice);
		shortPause();
		submit(driver);
		longPause();
		longPause();
		*/
		
		users.writeToFileCSV(patientInherit.getUsername(), patientInherit.getPasswd());
		
		}
		
		
	}
	
	
}
