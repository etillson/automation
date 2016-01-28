package test1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class DynamicObjects extends seleniumTest{
	
	
		
	public DynamicObjects(WebDriver driverCurrent, WriteToFile data) {
		

//			<<<  Variables  >>>
		
		driver = driverCurrent;
		
		String test;

		

		RandomGen ID = new RandomGen();
		Boolean pass = true;
		String field;
		String fieldNum;
		String choice;
		String url;
		String user;
		int choice2;
		boolean addUserLater = true;
		boolean forgotPassword = true;
		
		String phone1 = "55555555555";
		String phone2 = "66666666666";
		String phone3 = "77777777777";
		String phone4 = "8888888888";
		String ext = "222";
		
		//User in the system that should not be deleted
		String newPasswd = "!viimed689#";
		String newUsername = "hybkdruonq";
		
		
		/*  For Testing only
		String patientID = id.randomId();
		String firstName = "Enrique";
		String lastName = "Iglesias";
		*/

		
		
		
		//System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Downloads/chromedriver");
	    //WebDriver driver = new ChromeDriver();
		//WebDriver driver = new FirefoxDriver();
	    //WebDriver driver = new SafariDriver();
		
		
		
//			<<< Instantiation of Users  >>>
	    
	    Users tester = new Users(driver, "Testing Provider");
	    Users admin = new Users(driver, "Admin");
	    Users testPatient = new Users(driver, "Testing Patient");
		Users patientNew = new Users(driver, "Enrique", "Iglesias", newPasswd, "etillson@viimed.com");
		Users patient = new Users(driver, "Enrique", "Iglesias", "!viimed689#", "etillson@viimed.com");
		Users providerPartner = new Users(driver, "Provider Partner");
		Users admin6 = new Users(driver, "Admin6");
		Users testUser = new Users(driver, "testUser");
		Users nonUser = new Users(driver, "nonimportantUser");
		Users surgCoord = new Users(driver, "surgical coordinator");
		Users hipPatient = new Users(driver, "hip patient");
		String adminName = admin.getFirstName() + " " + admin.getLastName();
		
		
		System.out.println(patientNew.getUsername());
		System.out.println(patientNew.getPatientId());
		
//			<<< Open Gmail for testing emails >>>
		
	   // email gmail = new email();
	    
	    printTitle("Dynamic Objects", data);
	    
	    
//	      << For timestamping phonecalls and texts >>	    
	    CalendarDate now = new CalendarDate();
		
/*********************************************** 
 *		
 *		Go to QA3
 *
 *
 **********************************************/
			    

	    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	    //driver.manage().window().setPosition(new Point(0, 0));
	    resizeWindow(driver);
		driver.get(baseURL + "logout");
	    //driver.get("https://connect.hss.edu/login");
		//driver.get("https://build3.viimed.com/login");
		//driver.get("https://www.rcconnect.org");
		//login(driver, username, passwd);
		

		//logout();
		//testPatient.setCases(driver);
		//testPatient.showCases(driver);
		//testPatient.getCaseId(driver, "QA Test Case");

		    if(ie){
		    	longPause();
		    	longPause();
		    	
		    }
				    
/*********************************************** 
 *		
 *		Enroll Patient with Achievements
 *
 *
 **********************************************/

		test = "Create User With Achievements";
		login(tester);
		
		dashboardVisible();
		goToQa();
		
		getTest(test);
		setField("Patient ID", ID.randomId());
		setField("First name", hipPatient.getFirstName());
		setField("Last name", hipPatient.getLastName());
		setField("Email", hipPatient.getEmail());
		setField("Username", hipPatient.getUsername());
		setSelectPartText("Patient Activation", "Activate now");
		clickContinue();
		setField("Password", hipPatient.getPasswd());
		setField("Confirm Password", hipPatient.getPasswd());
		setSelectPartText("QA Testing Provider", "Provider 2");
		submit();
		
}
	
}
