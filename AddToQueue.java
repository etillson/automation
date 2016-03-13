package test1;



import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToQueue extends seleniumTest{

	
	
	public AddToQueue(WebDriver driverCurrent, WriteToFile data) {
		

//		<<<  Variables  >>>
		
		driver = driverCurrent;
		
		String test;

		

		RandomGen id = new RandomGen();
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
		
		
		
//		<<< Instantiation of Users  >>>

		//Constant Users
	    Users tester = new Users(driver, "Testing Provider");
	    Users admin = new Users(driver, "Admin");
	    Users testPatient = new Users(driver, "Testing Patient");
		Users providerPartner = new Users(driver, "Provider Partner");
		Users admin6 = new Users(driver, "Admin6");
		Users testUser = new Users(driver, "testUser");
		Users nonUser = new Users(driver, "nonimportantUser");
		
		
		ArrayList<Users> userList = new ArrayList<Users>();
			userList.add(tester);
			userList.add(admin);
			userList.add(testPatient);
			userList.add(providerPartner);
			userList.add(admin6);
			userList.add(testUser);
			userList.add(nonUser);
	
		//Temporary Users
		Users patientNew = new Users(driver, "Enrique", "Iglesias", newPasswd, "etillson@viimed.com");
		Users patient = new Users(driver, "Enrique", "Iglesias", "!viimed689#", "etillson@viimed.com");
		Users nonExisting = new Users(driver, "Enrique", "Iglesias", "!viimed689#", "qatest2@viimed.com");
		Users eglesias = new Users(driver, "Enrique", "Iglesias", "!viimed689#", "hybkdruonq", "qatest2@viimed.com", "patient");
		
		String adminName = admin.getFirstName() + " " + admin.getLastName();
		
		
		System.out.println(patientNew.getUsername());
		System.out.println(patientNew.getPatientId());
		System.out.println(userList.size());
		
//		<<< Open Gmail for testing emails >>>
		
	    email gmail = new email();
	    
	    printTitle("Add To Queue", data);
	    
	    
//      << For timestamping phonecalls and texts >>	    
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
				driver.get("https://qa3.viinetwork.net/logout");
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
 * 		Clear Tasklists
 *
 ***********************************************
				
	    
				try{
				login(admin);
				dashboardVisible();
				
				
				 /* This is used to clear the tasks on Build 3
				goTo(driver, "Configure", "Groups");	
				tableVisible();
				longPause();
				List <WebElement> queues = driver.findElements(By.cssSelector("#label.round.alert"));
				for(int x = 0; x <queues.size(); x++){
					if(x==1){
						queues.get(x).click();
					}
					
				}
				//WebElement queue = driver.findElement(By.cssSelector("span[class='label round alert']"));
				//queue.click();
				longPause();
				//String [] filters = {null, null, null, null, "JHU Nurse Practitioner", null, null, null};				
				//Filters userFilter = new Filters(driver, filters, "users");
				//shortPause();
				//openQueue(driver, "jhu np 1");
				Tasks clearTasks = new Tasks(driver);
				shortPause();
				clearTasks.deleteTasksAdmin(driver);
				*/
				
				/*************************
				 * 
				 * 		Group Queue
				 * 
				 * 		Delete Tasks
				 * 
				 * 
				 ***************************
				goTo(driver, "Configure", "People", "Groups");
				shortPause();
				tableVisible();

				shortPause();
				if(openGroupQueue(driver, "Kelly and Associates") == 1){
					shortPause();
					tasklistVisible();
					Tasks clearTasks = new Tasks(driver);
					shortPause();
					clearTasks.deleteTasksAdmin(driver);
				}
				
				*//*
				
				for(int x=0; x < userList.size(); x++){
					goTo("Configure", "People", "Users");
					shortPause();
					tableVisible();
					pageTitleVisible("Manage Users");
					String [] filters = {userList.get(x).getFirstName(), userList.get(x).getLastName(), null, null, null, null, null, null};				
					Filters userFilter = new Filters(driver, filters, "users");
					shortPause();
					if(openQueue(userList.get(x).getUsername()) == 1){
						shortPause();
						tasklistVisible();
						Tasks clearTasks = new Tasks(driver, true);
						shortPause();
						clearTasks.deleteTasksAdmin(driver);
					}
				}
				/*
				goTo("Configure", "People", "Users");	
				longPause();
				longPause();
				tableVisible();
				pageTitleVisible("Manage Users");
				String [] filters2 = {null, null, null, null, "testuser", null, null, null};		
				Filters userFilter2 = new Filters(driver, filters2, "users");
				shortPause();
				if(openQueue(tester.getUsername()) == 1){
					shortPause();
					tasklistVisible();
					Tasks clearTasks2 = new Tasks(driver, true);
					shortPause();
					clearTasks2.deleteTasksAdmin(driver);
				}
				
				goTo("Configure", "People", "Users");
				longPause();
				longPause();
				tableVisible();
				pageTitleVisible("Manage Users");
				String [] filters3 = {null, null, null, null, "testuser", null, null, null};				
				Filters userFilter3 = new Filters(driver, filters3, "users");
				shortPause();
				if(openQueue(testPatient.getUsername()) == 1){
					shortPause();
					tasklistVisible();
					Tasks clearTasks3 = new Tasks(driver, true);
					shortPause();
					clearTasks3.deleteTasksAdmin(driver);
				}
				
				goTo("Configure", "People", "Users");
				longPause();
				longPause();
				tableVisible();
				pageTitleVisible("Manage Users");
				String [] filters5 = {"Enrique", null, null, null, null, null, null, null};				
				Filters userFilter5 = new Filters(driver, filters5, "users");
				shortPause();
				if(openQueue(newUsername) == 1){
					shortPause();
					tasklistVisible();
					Tasks clearTasks3 = new Tasks(driver, true);
					shortPause();
					clearTasks3.deleteTasksAdmin(driver);
				}
				*/
				
				
			
				
				/*login(driver, tester.getUsername(), tester.getPasswd());
				openTasklist();
				Tasks clearTasks3 = new Tasks(driver);
				clearTasks3.deleteTasks(driver);
				logout();
				
				login(driver, admin.getUsername(), admin.getPasswd());
				openTasklist();
				Tasks clearTasks = new Tasks(driver);
				clearTasks.deleteTasks(driver);
				logout();
				
				login(driver, testPatient.getUsername(), testPatient.getPasswd());
				openTasklist();
				Tasks clearTasks2 = new Tasks(driver);
				clearTasks2.deleteTasks(driver);
				logoutPatient(driver);
				*//*
				logout();
				}
				catch(Exception e){
					logout();
					e.printStackTrace();
				}
				
/***********************************************
 * 		
 * 		Clear Testing Emails
 * 		
 * 
 ***********************************************
				
								
	//Delete emails from gmail
	try{
	if (gmail.checkAllEmails("ViiNetwork"))
		gmail.clickDelete();
	shortPause();
	gmail.refreshEmail();
	longPause();
	longPause();
	if (gmail.checkAllEmails("Mr. Tester"))
		gmail.clickDelete();
	shortPause();
	gmail.refreshEmail();
	longPause();
	longPause();
	shortPause();
	if (gmail.checkAllEmails("noreply@viimed.com"))
		gmail.clickDelete();	
	shortPause();
	}
	catch(Exception e){
		logout();
		e.printStackTrace();	
	}
				
				
/***********************************************
 * 		
 * 		Add to Queue 1
 * 		8 Priority 
 *
 ***********************************************

		test = "8 Priority";
		
		try{
		login(admin);
		dashboardVisible();
		goToQa();
		getTest(test);
		submit();
		longPause();
		openTasklist();
		tasklistVisible();
		Tasks testTasks = new Tasks(driver, true);
		url = driver.getCurrentUrl();
	
		testTasks.checkTask(driver, "PriorityImportantQATest", true);
		testTasks.openTask(driver);
	
		testTasks.backButton(driver);
		shortPause();

		if(testTasks.checkStatusNameColor("important")){

			testTasks.checkTask(driver, "PriorityNormalQATest", true);
			testTasks.openTask(driver);
			shortPause();

			testTasks.backButton(driver);
			shortPause();
	
			if(testTasks.checkStatusNameColor("pending")){
				printResult(test, data, "passed");						
				}
				else
					printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");
		if(mobile)
			shortPause();
		testTasks.deleteTasks(driver);
		logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}
		
				

/***********************************************
 * 		
 * 		Add to Queue 2
 * 		9a_DestinationQueue
 *
 ***********************************************
				
		test = "9a_DestinationQueue";
	
		
		try{
			login(admin);
			dashboardVisible();
			goToQa();
			
			getTest(test);
			submit();
			openTasklist();
			tasklistVisible();
			Tasks testDestQueue = new Tasks(driver, true);
			
			if (testDestQueue.taskExists("destinationqueue", true)){
				testDestQueue.deleteTasks(driver);
				logout();
				login(testPatient);
				openTasklist();
				tasklistVisible();
				
				if(testDestQueue.taskExists("destinationqueue", true)){
					//testDestQueue.reply(driver);
					testDestQueue.openTask(driver, "destinationqueue=testingpatient");
					shortPause();
					submit();
					shortPause();
					//logoutPatient(driver);
					logout();
					login(admin);
					openTasklist();
					tasklistVisible();
					Tasks testQueue = new Tasks(driver, true);
					if(testQueue.checkTasks(driver, "destinationqueue", true))
						printResult(test, data, "passed");
					else 
						printResult(test, data, "failed");
					
				}
				else
					printResult(test, data, "failed");
				
			}
			else
				printResult(test, data, "failed");
			
			testDestQueue.deleteTasks(driver);
			logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
		}
		
				
/***********************************************
 * 		
 * 		Add to Queue 3
 * 		9c Add User Later
 * 
 *********************************************
				
		test = "9c Add User Later";
		
		
		try{
			login(admin);
			dashboardVisible();
			//this is to test PW field

	/*
			WebElement password = driver.findElement(By.cssSelector("password-field input[type='password']"));
			password.sendKeys("!viimed689#");
			//WebElement confirmPasswd = driverEmail.findElement(By.name("confirm"));
			WebElement confirmPasswd = driver.findElement(By.cssSelector("input[placeholder='Type password again']"));
			
			confirmPasswd.sendKeys("!viimed689#");
			
		*/	/*
			
			goToQa();
	
			
			getTest(test);
			shortPause();
			
			
			field = "Patient ID";
			fieldNum = getFieldNum(driver, field);
			setField(driver, fieldNum, patientNew.getPatientId());
			field = "First name";
			fieldNum = getFieldNum(driver, field);
			setField(driver, fieldNum, patientNew.getFirstName());
			field = "Last name";
			fieldNum = getFieldNum(driver, field);
			setField(driver, fieldNum, patientNew.getLastName());				
			field = "Email";
			fieldNum = getFieldNum(driver, field);
			setField(driver, fieldNum, patientNew.getEmail());
			field = "Username";
			fieldNum = getFieldNum(driver, field);
			setField(driver, fieldNum, patientNew.getUsername());
			field = "Patient Activation";
			choice2 = 2;
			fieldNum = getFieldNum(driver, field);
			getSelect(driver, fieldNum, choice2);
			clickContinue(driver);
			longPause();
			field = "Case Type";
			choice2 = 1;
			fieldNum = getFieldNum(driver, field);
			getSelect(driver, fieldNum, choice2);
			clickContinue(driver);
			
			//This field needs to be readded if there's more than one choice, currently it's just the Testing Provider
			
			field = "QA Testing Provider";
			user = "Provider, Testing";
			choice2 = 1;
			fieldNum = getFieldNum(driver, field);
			getSelectVisText(driver, fieldNum, user);
			
			
			shortPause();
			submit();
			
			//Setting the new user's user ID
			dashboardVisible();
			goTo("Configure", "People", "Users");
			shortPause();
			tableVisible();
			Filters userfilt = new Filters(driver, new String[]{"Enrique"}, "users");
			shortPause();
			patientNew.findUserId(driver);

			
			
			//Set the new user's case names and case IDs
			if(mobile){
				longPause();
			}
			System.out.println("3");
			patientNew.setCases(driver);
			System.out.println("4");


			patientNew.showCases(driver);
			
			
			thirtyPause();
			gmail.refreshEmail();
			longPause();
			longPause();
			field = "ViiNetwork QA";
			String field2 = "Mr. Tester";
			gmail.openEmail(field, field2);
			if(mobile){
				longPause();
			}
			shortPause();
			gmail.activate();
			longPause();
			
			pass = gmail.newPassword(patientNew.getUsername(), patientNew.getPasswd());
	
		
			if (pass)
				printResult(test, data, "passed");
			else{
				printResult(test, data, "failed");
				addUserLater = false;
			}
						
			gmail.closeWindow();
			gmail.switchToParent();
			gmail.backButton();
			logout();
			shortPause();
			
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
			addUserLater = false;


		}

				
/***********************************************
 * 		
 * 		Add to Queue 3.1
 * 		Add Permission
 * 
 **********************************************
	
		test = "Add Permission";
		
	try{
		login(tester);
		dashboardVisible();
		
		driver.get("https://qa3.viinetwork.net/milestones");
		//Milestones perm = new Milestones(driver, patientNew.getCaseId(driver, "QA Test Case"), 1, "Add Permission");
		
		coDash console = new coDash(driver);
		longPause();
		console.setInput(driver, patientNew.username);

		//Not necessary with new search bar
		//console.clickSearch(driver);
		longPause();
		if(console.countUsersPage(driver)==0){
			/*console.filterOption(driver, "Status");
			shortPause();
			console.filterSub(driver, "Closed");
			*//*
			console.setFilter("Completed");
			longPause();
		}
		System.out.println(console.countUsersPage(driver));
		if(console.countUsersPage(driver)==0){
			logout();
			login(patientNew);
	
			dashboardVisible();
			openTasklist();
			tasklistVisible();
			Tasks addperm = new Tasks(driver, "Add Permission", true);
			addperm.openTask(driver, "Add Permission");
			shortPause();
			
			if(mobile)
				shortPause();
			
			submit();
			shortPause();
			
			if(mobile)
				shortPause();
			
			logout();
			login(tester);
			dashboardVisible();
			driver.get("https://qa3.viinetwork.net/coordinator/console");
			longPause();

			console.setInput(driver, patientNew.username);
			//console.clickSearch(driver);
			longPause();
			if(console.countUsersPage(driver)==0){
				/*console.filterOption(driver,"Status");
				shortPause();
				console.filterSub(driver, "Closed");
				*//*
				console.setFilter("Completed");
				longPause();
			}	
			if(console.countUsersPage(driver) > 0){
				System.out.println("User present");
				printResult(test, data, "passed");
				logout();
			}
			else{
				printResult(test, data, "failed, user not present");
				logout();
				}
		}
		else{
			printResult(test, data, "failed, user already visible on console");
			logout();
			}
	}
	catch(Exception e){
		printResult(test, data, "failed due to exception");
		logout();
		e.printStackTrace();
		
	}
		

/***********************************************
 * 		
 * 		Add to Queue 3.1
 * 		Auto Initiate Form
 * 
 **********************************************
	
	test = "Auto Initiate Form";
	
	try{
		login(admin);
		dashboardVisible();
		goTo("Configure", "Content", "Forms");
		tableVisible();
		Forms aif = new Forms(driver, "Auto Initiate Form", new String[]{"addtoqueue", null, null, null});
		aif.updateForm();
		aif.findEndpoint(driver);
		shortPause();
		
		aif.openAction(driver, "(type: addtoqueue)", aif.openInspectorPart(driver, "Actions"));
		longPause();
		if(aif.countButtons(driverCurrent, "button", "launch-what") == 1 && 
				getSelectedByElement(driver, aif.getSelectField(driver, aif.getATQId(driver, "button", "launch-what"))).contentEquals("Launch Form")){
			
			aif.closeActionModal(driver);
			shortPause();
			goToQa();
			getTest(test);
			shortPause();
			submit();
			printResult(test, data, "passed");
			logout();
		}
		else{
			printResult(test, data, "failed due to button count or button type");	
			logout();
				}
		
	}
	catch(Exception e){
		printResult(test, data, "failed due to exception");
		logout();
		e.printStackTrace();		
	}
	
	
/***********************************************
 * 		
 * 		Add to Queue
 * 		Add Phone
 * 
 * 	Needs to be rewritten when user profiles are incorporated
 *  Phone numbers can currently only be added when a user is created 10/12/2015
 * 
 **********************************************
				
	test = "Add Phone";
	
	try{

		String tempUser = "newguy1";
		String tempPassword = "password";
		login(driver, tester.getUsername(), tester.getPasswd());
		dashboardVisible();
		goToQa();
		getTest("Add Phone");
		if(mobile)
			shortPause();
		//setSelectCaseDynamic(driver, tempUser, "Eric Tillson");
		setSelectCaseDynamic(driver, patientNew.getUsername(), patientNew.getFirstName() + " " + patientNew.lastName, "QA Test Case");
		//setSelectCaseValue(driver, patientNew.getCaseId(driver, "QA Test Case"));
		//setSelectCaseValue(driver, "1241");
		field = "Enter Your Cell Phone Number";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, phone1);			
		field = "Enter Your Home Phone Number";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, phone2);			
		field = "Enter Your Work Phone Number";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, phone3);
		field = "Enter An Extension For Your Work Number";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, ext);
		
		field = "Add Another Phone Number";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, phone4);
		
		submit();
		if(mobile)
			shortPause();
		logout();
		
		//login(driver, tempUser, tempPassword);
		
		login(driver, patientNew.getUsername(), patientNew.getPasswd());
		//login(driver, "axyehrqmda", "!viimed689#");
		dashboardVisible();
		if(mobile)
			longPause();
		clickLinkByURL(driver, "contacts");
		if(mobile)
			longPause();
		if(getPageText(driver).contains("Cell: 555-555-5555 edit") && getPageText(driver).contains("Home: 777-777-7777 x222 edit") &&
				getPageText(driver).contains("Work: 666-666-6666 edit") && getPageText(driver).contains("Other: 888-888-8888 edit"))
			printResult(test, data, "passed");
		else
			printResult(test, data, "failed");
		
		logout();
		
		
	}
	
	catch(Exception e){
		printResult(test, data, "failed due to exception");
		logout();
		e.printStackTrace();	
		
	}
	
/***********************************************
 * 		
 * 		Add to Queue 8
 * 		Badge Testing
 * 
 **********************************************
		
		test = "Badge Testing";
		
		
		try{
		
	    login(testUser);
		
	    dashboardVisible();
	    shortPause();
		goToQa();
		
	
		getTest(test);
		submit();
		thirtyPause();
		openTasklist();
		longPause();
		captureScreen(driver, "badgeTest");
		Tasks badge = new Tasks(driver, true);
		badge.deleteTasks(driver);
		logout();
		printResult(test, data, " passes upon checking the screenshot");
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}

/***********************************************
 * 		
 * 		Add to Queue 4
 * 		Forgot Password
 * 		
 * 
 ***********************************************
				
		test = "Forgot Password";
		String usernameTest = "hybkdruonq";  // for testing only
		
		
		try{
			
		
			String verificationCode;
	
			
			test = "Forgot Password Test";
			
			loginPageLoaded();
			
			if(ie || safari){
				driver.get(driver.getCurrentUrl());
			}
			else
			driver.navigate().refresh();
			
			login(nonExisting);
			longPause();
			if(alertExists(driver, "Your username or password was not found."))
				System.out.println("Alert present");
			else{
				printResult(test, data, "failed");
				forgotPassword = false;
			}
			WebElement passwdForget = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
					(By.xpath("//button[contains(., 'I forgot my password!')]")));
			passwdForget.click();
			//setFieldByName(driver, "username", newUsername);
			if(mobile)
				longPause();
			shortPause();
			setInputByAttribute("placeholder", "Username", usernameTest);
			clickButton("Retrieve Password");
			minutePause();
			gmail.refreshEmail();
			longPause();
			gmail.openEmail("Reset Password", "zzzzzzz");
			verificationCode = gmail.getVerificationCode();
			gmail.backButton();
			//setFieldByName(driver, "code", verificationCode);
			setInputByAttribute("placeholder", "Verification Code", verificationCode);
			clickButton("Verify my code");
			shortPause();
			//setField(driver, "password-set", newPasswd);
			//setField(driver, "password-set-confirm", newPasswd);
			setInputByAttribute("placeholder", "Password", newPasswd);
			setInputByAttribute("placeholder", "Type Password, Again", newPasswd);
			clickButton("Reset my Password");
			shortPause();
			//setFieldByName(driver, "username", newUsername);
			login(eglesias);
		
			longPause();
			dashboardVisible();
			System.out.println(driver.getCurrentUrl());
			if(driver.getCurrentUrl().contains("dashboard") && forgotPassword!=false)
				printResult(test, data, "passed");
				else{
					printResult(test, data, "failed");
					forgotPassword = false;
				}
			logout();
			shortPause();
			

		}
		catch(Exception e){
			e.printStackTrace();
			printResult(test, data, "failed");
			forgotPassword = false;
			logout();


		}			

/***********************************************
 * 		
 * 		Add to Queue 5
 * 		17 Send Email as User
 * 
 ***********************************************
				
				//This test only works if Add User Later and Forgot Password tests pass
				
				test = "17 Send Email as User";

				try{
					
				//if(addUserLater == false && forgotPassword == false)
					login(testUser);
				//else
					//login(driver, patientNew.getUsername(), patientNew.getPasswd());
				shortPause();
				dashboardVisible();
				goToQa();
				getTest(test);
				
				submit();
				longPause();
				dashboardVisible();
				logout();
				longPause();
				loginPageLoaded();
				login(admin);
				longPause();
				dashboardVisible();
				Telecom tele = new Telecom();
				tele.openTelecom(driver);
				System.out.println(tele.getDefaultEmail(driver));
				System.out.println(tele.getDefaultName(driver));
				String defaultEmail = tele.getDefaultEmail(driver);
				String defaultName = tele.getDefaultName(driver);
				String activationEmail = tele.getActivationSubject(driver);
				String activationEmailConfirmation = tele.getActivationConfirmationSubject(driver);
				String reset = tele.getResetPasswordSubject(driver);
		
				thirtyPause();


				longPause();
				if(gmail.checkNameEmail(defaultName, defaultEmail))
					System.out.println(activationEmail + " email exists");
					else
						pass = false;	
				
				if(gmail.checkSubject(activationEmail))
					System.out.println(activationEmail + " email exists");
					else
						pass = false;
				if(gmail.checkSubject(activationEmailConfirmation))
					System.out.println(activationEmailConfirmation + " email exists");
					else
						pass = false;
				if(gmail.checkSubject(reset))
					System.out.println(reset + " email exists");
					else
						pass = false;
				
				if (pass)
					printResult(test, data, "passed");
					
				else
					printResult(test, data, "failed");
				
					logout();
					shortPause();
					if (gmail.checkAllEmails("ViiNetwork QA"))
						gmail.clickDelete();
				}
				catch(Exception e){
					printResult(test, data, "failed");
					logout();
					e.printStackTrace();
					if (gmail.checkAllEmails("ViiNetwork QA"))
						gmail.clickDelete();
				}
			
				
				
/***********************************************
 * 		
 * 		Add to Queue 6
 * 		18 Delete User
 * 
 ***********************************************
		test = "18 Delete User";
		
		//  You must first perform 9c Add User Later test in order to create the user
		if(safari || ie || mobile){
		printResult(test, data, "must be tested manually for Safari, Firefox, IE, and mobile");
		}
		
		else{
			if(addUserLater == true){
		try{
		login(admin);	
		
		
		if(deleteUser(patientNew))
			printResult(test, data, "passed");
		else
			printResult(test, data, "failed");
		//deleteUser(driver, "enrique5");
		
		logout();
		}
		catch(Exception e){
			shortPause();
			driver.switchTo().alert().accept();
			printResult(test, data, "failed");
			shortPause();
			logout();
	
		}
			}
		}
		
		
/***********************************************
 * 		
 * 		Add to Queue 7
 * 		AddUser_AddRelationship_AddCase_milestones
 *
 ***********************************************
				
		test = "Add User_Add Relationship_Add Case_Milestones";
		
		try{
		
		login(driver, admin.username, admin.passwd);	

		System.out.println(patient.getUsername());
		System.out.println(patient.getPatientId());
		
		goToQa();
		shortPause();
		getTest(test);
		shortPause();
		
		
		field = "Patient ID";
		fieldNum = getFieldNum(driver, field);
		System.out.println(fieldNum);
		setField(driver, fieldNum, patient.getPatientId());
		field = "First name";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient.getFirstName());
		field = "Last name";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient.getLastName());				
		field = "Email";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient.getEmail());
		field = "Username";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient.getUsername());
		field = "Patient Activation";
		choice2 = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice2);

		clickButton(driver, "patientactivation");
		field = "Password";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient.getPasswd());
		field = "Confirm Password";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, patient.getPasswd());
		shortPause();
		clickContinue(driver);
		shortPause();
		field = "Case Type";
		choice2 = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice2);
		clickContinue(driver);
		field = "QA Testing Provider";
		choice2 = 3;
		fieldNum = getFieldNum(driver, field);
		user = "Provider, Testing";
		getSelectVisText(driver, fieldNum, user);
		shortPause();
		submit();
		shortPause();
		logout();
		shortPause();
		shortPause();
		//loginPageLoaded(driver);
		login(driver, patient.getUsername(), patient.getPasswd());
		shortPause();
		shortPause();
		termsConditionsVisible(driver);
		WebElement iAgree = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//button[contains(text(), 'I Agree')]")));
		iAgree.click();
		shortPause();
		if(mobile)
			longPause();
		goToDashboard(driver, patient.getFirstName());
		shortPause();
		dashboardVisible();
		String relationships = getRelationships(driver);
		pass = hasRelationship(driver, relationships, "Test Provider");
		if (pass)
			System.out.println("Relationship present");
		else
			printResult(test, data, "failed");
	

		
		
		shortPause();

		pageTitleVisible(patient.getFirstName());
		openTasklist();
		shortPause();
		tasklistVisible();
		Tasks tasks2 = new Tasks(driver);
		String[] alerts = {"Test user links", "First Follow Up Milestone Test", "Second Follow Up Task"};
		pass = tasks2.hasTasks(driver, alerts);
		if (pass)
			System.out.println("All tasks present");
		else
			printResult(test, data, "failed");
		tasks2.openTask(driver, alerts[0]);
		url = driver.getCurrentUrl();
		
		
		/*  This link has been removed from the software
		if(mobile)
			shortPause();
		WebElement providerName = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//span[contains(text(), 'Created by')]/a")));
		providerName.click();
		shortPause();
		if (hasOverview(driver, adminName))
			System.out.println("Contains Overview");
		else
			printResult(test, data, "failed");
		if (safari){
			driver.get(url);
		}
		else
			driver.navigate().back();
		*/
		/*
		
		shortPause();
		WebElement providerName = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//strong[contains(text(), '" + adminName + "')]")));
		providerName.click();
		shortPause();
		if (hasOverview(driver, adminName))
			System.out.println("Contains Overview");
		else
			printResult(test, data, "failed");
		if (safari){
			driver.get(url);
		}
		else
			driver.navigate().back();
		
		tasklistVisible();
		tasks2.clickTaskButton(driver);
	
		tasks2.openTask(driver, alerts[1]);
		shortPause();
		//tasks2.clickTaskButton(driver);   no longer needed due to form auto initiate
		submit();
		
		//These pauses are to allow a time to make sure milestone 1 is processed first
		shortPause();
		shortPause();
		longPause();
		longPause();
		
		
		openTasklist();
		tasklistVisible();

		tasks2.openTask(driver, alerts[2]);

		//tasks2.clickTaskButton(driver);   No longer needed due to form auto initiate
		shortPause();
		submit();

		openTasklist();
		

		logout();

		
		// To delete
		login(driver, admin.getUsername(), admin.getPasswd());
		longPause();
		
		dashboardVisible();
		
		patient.setCases(driver);
		patient.showCases(driver);
		
		/* Milestones are now done by case ID and not patient ID
		goTo(driver, "Configure", "People", "Users");
		patient.findUserId(driver);
		 */
		/*
		logout();
		
		
		login(driver, tester.getUsername(), tester.getPasswd());
		WebElement dashboard = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//p[contains(text(), 'Welcome to your dashboard')]")));
		driver.get("https://qa3.viinetwork.net/milestones");

		Milestones first = new Milestones(driver, patient.getCaseId(driver, "QA Test Case"), 1, "First Follow Up");
		first.setMilestone(driver);
		//Milestones first = new Milestones(driver, "344", 1, "First Follow Up");
		Milestones second = new Milestones(driver, patient.getCaseId(driver, "QA Test Case"), 2, "Second Follow Up");
		second.setMilestone(driver);
		//Milestones second = new Milestones(driver, "344", 2, "Second Follow Up");
		if(first.checkMilestone(driver) && second.checkMilestone(driver)){
			System.out.println("Milestones appear correctly");
			printResult(test, data, "passed");
		}
		else{
			printResult(test, data, "passed, but Milestones are out of order");
		}


		logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}



/***********************************************
 * 		
 * 		Add to Queue 9
 * 		Send Video Message
 * 
 ***********************************************
		
		test = "Send Video Message";
		if(mobile){
			
		}
		else
		try{
		
			
		//login(driver, patient.getUsername(), patient.getPasswd());************
		login(driver, newUsername, newPasswd);
		
		shortPause();

		
		goToQa();
		shortPause();
		getTest(test);
		longPause();
		longPause();

		//If the field is a look ahead, use the following code
		
		//field = "Choose which case this data";
		//choice = "Enrique Iglesias";
		//fieldNum = getFieldNum(driver, field);
		//chooseDynamic(driver, fieldNum, choice);
		//shortPause();
		//field = "Select Recipient";
		//choice = "admin4";
		//fieldNum = getFieldNum(driver, field);
		//chooseDynamic(driver, fieldNum, choice);
		
		
		
		//If the field is a dropdown, use the following code
		
		
		/*  These are no longer required due to the 1 choice on a select field causes the selection to be hidden
		 
		field = "Choose which case";
		choice2 = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice2);

		
		field = "To";
		choice2 = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice2);

		*//*
		
		shortPause();
		
		useRecorder(driver);

		shortPause();
		
		field = "Message";
		fieldNum = getFieldNumExact(driver, field);

		setTextBox(driver, fieldNum, "Take a look at this sweet video");
		
		captureScreen(driver, "Video Message");
		shortPause();
		submit();
		shortPause();
		//logout(driver, patient.getFirstName()); // ******************
		logout(driver, "Enrique");
		
		
		login(driver, tester.username, tester.passwd);
		shortPause();
		openTasklist();
		shortPause();
		Tasks task = new Tasks(driver, "You have a message");
		task.openTask(driver, "You have a message");
		shortPause();
		playVideo(driver, "Video Message");
		captureScreen(driver, "Video Playback");
		shortPause();
		task.taskButton(driver, "Mark as viewed");
		shortPause();
		//logout(driver, tester.lastName);
		logout();
		printResult(test, data, "passes upon review of screenshots");
	
		
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}
		
		
/***********************************************
 * 		
 * 		Add to Queue 10
 * 		Queue_DeriveDestinationQueue
 * 
 ***********************************************
 	
		test = "10 DeriveDestinationQueue";
		
		//newUsername = "yeovioawqk"; //****************
	
	
		try{
		//login(driver, patient.username, patient.passwd);
		login(testUser);
		
		
		shortPause();
		goToQa();
		shortPause();
		getTest(test);
		
		/* Not required with 2.3 update for selection fields with only one choice
		field = "Choose which case";
		choice2 = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice2);
		*//*
				
				
				
		submit();
		//logout(driver, patient.firstName);
		logout();
		shortPause();
		login(tester);
		dashboardVisible();
		shortPause();
		openTasklist();
		shortPause();
		Tasks task10 = new Tasks(driver, "DeriveDestinationQueue", true);
		
		task10.openTask(driver);
		shortPause();
		
		task10.clickTaskButton(driver);
		printResult(test, data, "passed");
		logout();

		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}

		
		
/***********************************************
 * 		
 * 		Add to Queue 10b
 * 		Queue_AddToQueue_DDQ_By_Action
 *
 ***********************************************
 
 
		test = "10b_AddToQueue_DDQ_By_Action";
		
		
		//newUsername = "yeovioawqk"; //****************
	
	
		try{
		//login(driver, patient.username, patient.passwd);
		login(testUser);
		
		
		shortPause();
		goToQa();
		shortPause();
		getTest(test);
		
		/* Not required with 2.3 update to auto select select fields with one choice
		field = "Choose which case";
		choice2 = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice2);
		*//*
		
		submit();
		shortPause();
		//logout(driver, patient.firstName);
		logout();
		shortPause();
		login(tester);
		shortPause();
		openTasklist();
		shortPause();
		Tasks task10b = new Tasks(driver, "DeriveDestinationQueue", true);
		url = driver.getCurrentUrl();
	
		task10b.openTask(driver);
		if (task10b.getCurrentStatus().equalsIgnoreCase("pending") && task10b.getCurrentPriority().equalsIgnoreCase("normal")){
			minutePause();
			if (safari)
				driver.get(url);
			else
				driver.navigate().back();
			shortPause();
			Tasks task10bLate = new Tasks(driver, "FiredByLateQueueItem", true);
			task10bLate.openTask(driver);
			shortPause();
			task10bLate.clickTaskButton(driver);

			shortPause();
			Tasks task10b2 = new Tasks(driver, "DeriveDestinationQueue", true);
			task10b2.openTask(driver);
			shortPause();
			task10b2.clickTaskButton(driver);
			shortPause();
			if(mobile)
				longPause();

			if (task10b2.getCurrentStatus().equalsIgnoreCase("late") && task10b2.getCurrentPriority().equalsIgnoreCase("normal") && 
					task10bLate.getCurrentStatus().equalsIgnoreCase("pending") && task10bLate.getCurrentPriority().equalsIgnoreCase("important"))
				printResult(test, data, "passed");
			else
				printResult(test, data, "failed");
		}
		else
			printResult(test, data, "failed");		
		
		logout();

		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}
		
/***********************************************
 * 		
 * 		Add to Queue 11
 * 		AddToQueue_Timers
 *
 **********************************************
		
		test = "Timers";
		
		int priority = 0;
		try{
		login(admin);
		dashboardVisible();
		goToQa();
		getTest(test);
		submit();
		dashboardVisible();
		openTasklist();
		tasklistVisible();
		Tasks timer = new Tasks(driver, "timer_status:pending", true);
		if(timer.getPriority())
			System.out.println("Priority is high");
		if (!timer.getPriority()){
			System.out.println("Priority is low");
			priority++;
			System.out.println(priority);
		}
		url = driver.getCurrentUrl();
		timer.openTask(driver);
		System.out.println(timer.getCurrentPriority());
		System.out.println(timer.getCurrentStatus());
		if(timer.getCurrentPriority().equals("Normal") && timer.getCurrentStatus().equals("Pending"))
			{
			
				clickBackToTasklist(driver);
			
			
		
				minutePause();
				longPause();
				driver.navigate().refresh();
				
				shortPause();
				if(mobile)
					longPause();
						
				timer.checkTask(driver, "timer_status:pending->late_priority:normal->important", true);
				if(timer.getPriority())
					System.out.println("Priority is high");
					priority++;
					System.out.println(priority);
				if(timer.getPriority()){
					System.out.println("Priority is low");
				}
				
				timer.openTask(driver);
				System.out.println(timer.getCurrentPriority());
				System.out.println(timer.getCurrentStatus());
				if(timer.getCurrentPriority().equalsIgnoreCase("Important") && timer.getCurrentStatus().equalsIgnoreCase("Late")){
						
						timer.taskButton(driver, " Passed!");
						
						shortPause();
					
						if (priority ==2)
							printResult(test, data, "passed");
						else{
							printResult(test, data, "failed");
							System.out.println("Look 1");
						}
						logout();
					}
				else{
					printResult(test, data, "failed");
					System.out.println("Look 2");
					logout();
					}
			}
		else{
			printResult(test, data, "failed");
			System.out.println("Look 3");
			logout();
	
		}
		
		}
		catch(Exception e){
			e.printStackTrace();
			printResult(test, data, "failed");
			logout();

		}
		
		
/***********************************************
 * 		
 * 		Add to Queue 12
 * 		AddToQueue_Rules
 * 
 ***********************************************/
		test = "Rules";
		
	try{
			
		login(admin);
		
		goToQa();
		getTest(test);
		field = "Type in your personal email";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, admin.getEmail());
		submit();
		
		minutePause();
		thirtyPause();
		driver.navigate().refresh();
		try{
			
			longPause();
			//reminders are no longer supported
			//WebElement reminder = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				//	(By.cssSelector("div.alert.alert-info strong")));
			longPause();
			if(gmail.checkSubject("The rule worked!")){			
				//reminder.click();
			
				shortPause();
				Tasks rules = new Tasks(driver, "Rule_when(status:pending->late)sendemail_when(priority:low->high)sendreminder", true);
				rules.openTask(driverCurrent);
				shortPause();
				rules.taskButton(driver, " Passed!");
				shortPause();
				
				printResult(test, data, "passed");
			}
			else
				printResult(test, data, "failed");
				logout();
		}
		catch(Exception e){
			
			System.out.println("Email or task not present");
			printResult(test, data, "failed");
			e.printStackTrace();
			logout();
		}
		//email gmail = new email();  //you need to comment this out
		
	
		//WebElement reminder = driver.findElement(By.xpath("//div[contains(., 'Reminders')]/div[2]"));

		

		}
	catch(Exception e){
		printResult(test, data, "failed");
		logout();
		e.printStackTrace();
	}

/***********************************************
 * 		
 * 		Add to Queue 13.1
 * 		Queue_SMS&Text-to-Speech - Remove Phone Number
 *  		
 *  
 *  
 * 
 ***********************************************/
		
				
				
		test = "SMS & Text-to-Speech";
		
		try{
		if(safari){
			printResult(test, data, "needs to be tested manually for Safari");
		}
		else
		{
		login(admin);
		
		longPause();
		
		driver.get("https://qa3.viinetwork.net/user/contact");
		shortPause();
		/*if(safari){
			shortPause();
		}
		shortPause();
		Contact contact = new Contact();
		if(contact.checkPhoneExists(driver)){
			contact.deletePhoneNumber(driver, "cell");
			driver.switchTo().alert().accept();
		}
		*/
		clickLinkAttribute(driver, "Edit", "title");
		remove("phones", 0);
		submitInput();
		
		shortPause();
		driver.get("https://qa3.viinetwork.net/dashboard");
		shortPause();
		goToQa();
		getTest(test);
		submit();
		longPause();
		now.setDate(driver);
		dashboardVisible();
		if(checkIfOnDashboard(driver))
			printResult(test, data, " - Remove Phone Number passed if no text and call were received @ " + now.getToday());
		else
			printResult(test, data, "failed");
		
	
		logout();
		}
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}		
	
	
/***********************************************
 * 		
 * 		Add to Queue 13
 * 		Queue_SMS&Text-to-Speech
 * 
 ***********************************************/
		test = "SMS & Text-to-Speech";
		
		try{
			
		login(admin);
		
		longPause();
		
		driver.get("https://qa3.viinetwork.net/user/contact");
		shortPause();
		
		if(safari || mobile){
			shortPause();
		}
		
		
		clickLinkAttribute(driver, "Edit", "title");
		
		//Contact contact = new Contact();
		//contact.editPhoneNumber(driver, admin.getPhoneNumber(), "cell", "");
		shortPause();
		
		add("phones");
		clearInputByLabel("phones", "Number", null, 0);
		setInputByLabel("phones", "Number", null, 0, admin.getPhoneNumber());
		clickInputByLabel("phones", "Number", "Mobile", 0);



		submitInput();
		shortPause();
		
		driver.get("https://qa3.viinetwork.net/dashboard");
		
		goToQa();
		getTest(test);
		submit();
		
		now.setDate(driver);
		printResult(test, data, "passes if you received a text and a call @ " + now.getToday());
	
		logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}
		
/***********************************************
 * 		
 * 		Add to Queue 12a
 * 		Queue_Rules_AddToQueue_sms_texttospeech_alert
 * 
 ***********************************************/
 
		test = "Rule_addtoqueue_alert_sms_texttospeech";
		
		
		try{
			
		login(admin);
		dashboardVisible();
		goToQa();
		getTest(test);
		field = "Just type something";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "Blah blah blah");
		submit();
		minutePause();
		longPause();
		longPause();
		if(mobile){
			thirtyPause();
		}
		openTasklist();

		tasklistVisible();
		Tasks rules12a = new Tasks(driver, "Rule2_addtoqueue", true);
		rules12a.openTask(driver);
		if(mobile)
			shortPause();
		rules12a.taskButton(driver, "Passed!");
		if(mobile)
			shortPause();
		logout();
		shortPause();
		login(tester);
		openTasklist();
		tasklistVisible();
		Tasks rules12a2 = new Tasks(driver, "rule_addtoqueue", true);
		rules12a2.openTask(driver);
		if(mobile)
			shortPause();
		rules12a2.taskButton(driver, "Passed!");
		if(mobile)
			shortPause();
		now.setDate(driver);
		printResult(test, data, "passes if you recieved text and call @ " + now.getToday());
		logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}
		
		
/***********************************************
 * 		
 * 		Add to Queue 12b
 * 		Queue_Rules_reassigntask
 *
 **********************************************/
		
		test = "12b_rule-reassigntask";

		try{
			
		login(admin);
		dashboardVisible();
		goToQa();
		getTest(test);
		shortPause();
		field = "pretend this is a form";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "Blah blah blah");
		submit();
		minutePause();
		longPause();
		openTasklist();
		tasklistVisible();
		Tasks rules12b = new Tasks(driver, "rule_reassigntask", true);
		rules12b.openTask(driver);
		if(mobile)
			shortPause();
		System.out.println(rules12b.getCurrentStatus());
		if(rules12b.getCurrentStatus().equals("Late"))
			printResult(test, data, "passed");

		else
			printResult(test, data, "failed");
		if(mobile)
			shortPause();
		rules12b.taskButton(driver, "Passed!");
		logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			logout();
			e.printStackTrace();
		}


		
/***********************************************
 * 		
 * 		Add to Queue 12c
 * 		Queue_Rules_reassigntask
 * 
 ***********************************************/
		
		test = "12c_Queue_Rules_reassigntask_DestQueue";

		try{
			
		login(testUser);
		dashboardVisible();		
		goToQa();
		getTest(test);
		shortPause();
		/* No longer required in v. 2.3
		field = "Choose which case";
		choice2 = 1;
		fieldNum = getFieldNum(driver, field);
		getSelect(driver, fieldNum, choice2);
		*/
		
		field = "pretend this is a form";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, "Blah blah blah");
		submit();
		minutePause();
		logout();
		login(admin);
		dashboardVisible();
		openTasklist();
		Tasks rules12b = new Tasks(driver, "rule_reassigntask", true);
		rules12b.openTask(driver);
		System.out.println(rules12b.getCurrentStatus());
		if(rules12b.getCurrentStatus().equals("Late"))
			printResult(test, data, "passed");

		else
			printResult(test, data, "failed");
		
		rules12b.taskButton(driver, "Passed!");
		logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			driver.get("https://qa3.viinetwork.net/logout");
			e.printStackTrace();
		}
		
/***********************************************
 * 		
 * 		Add to Queue 14
 * 		14 Due Date
 *
 ***********************************************/
		
		test = "14 Due Date";
		
		try{
			
		login(admin);
		
		goToQa();
		getTest(test);
		CalendarDate date14 = new CalendarDate();
		
		if(ie || safari)
			date14.setDateByClick(driver, 1);
		else
			date14.setDate(driver, 1);
		
		longPause();
		submit();
		dashboardVisible();
		openTasklist();
		tasklistVisible();
		Tasks test14 = new Tasks(driver, true);
		System.out.println(date14.getToday() + date14.getTomorrow());
		
		test14.openTaskWithDates(driver, date14.getToday(), date14.getTomorrow());
		test14.taskButton(driver, "Passed");
		printResult(test, data, "passed");
		logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			driver.get("https://qa3.viinetwork.net/logout");
			e.printStackTrace();
		}

/***********************************************
 * 		
 * 		Email Action
 * 		Send Email
 * 
 ***********************************************/
		
		test = "16 Send Email Action";
		
		try{
			
		login(admin);
		
		goToQa();
		getTest(test);
		field = "Type in your email";
		fieldNum = getFieldNum(driver, field);
		setField(driver, fieldNum, admin.getEmail());
		submit();
		minutePause();
		//email gmail = new email(); //***************
		if(gmail.checkSubject("This test passed!"))
			printResult(test, data, "passed");
		else
			printResult(test, data, "failed");
		logout();
		}
		catch(Exception e){
			printResult(test, data, "failed");
			driver.get("https://qa3.viinetwork.net/logout");
			e.printStackTrace();
		}

		
		
/***********************************************
* 		
* 		Add To Queue
* 		Send SMS to Case Owner
* 
*  
***********************************************/
		
		test = "Send SMS to Case Owner";
		
		try{
		login(testPatient);
		goToQa();
		getTest(test);
		shortPause();
		setSelectCase(driver, "Testing Patient: QA Test Case");
		submit();
		shortPause();
		logout();
		login(tester);
		shortPause();
		openTasklist();
		Tasks sendSMS = new Tasks(driver, "Send patient Text Response", true);
		sendSMS.openTask(driver);
		//sendSMS.taskButton(driver, "Send Patient Text");   no longer needed due to auto initiate form
		shortPause();
		submit();
		shortPause();
		openTasklist();
		Tasks sendTTS = new Tasks(driver, "Send patient Text-to-speech", true);
		sendTTS.openTask(driver);
		//sendTTS.taskButton(driver, "Send Text-to-speech");  no longer needed due to auto initiate form
		shortPause();
		submit();
		shortPause();
		logout();
		
		now.setDate(driver);
		printResult(test, data, "passed if text and call were received @ " + now.getToday());
		}
		catch(Exception e){
			printResult(test, data, "failed");
			driver.get("https://qa3.viinetwork.net/logout");
			e.printStackTrace();
		}
		
		
/***********************************************
* 		
* 		Add To Queue
* 		Assist Add To Queue
* 
*  
***********************************************/		
		
		test = "Assist Add To Queue";
		
		try{
		login(tester);
		dashboardVisible();
		goToQa();
		getTest(test);
		shortPause();
		if(mobile)
			longPause();
		setSelectCaseDynamic(driver, "Testing Patient", "Testing Patient", "QA Test Case");
		//setSelectCase(driver, "Testing Patient: QA Test Case");
		submit();
		shortPause();
		logout();
		login(providerPartner);
		shortPause();
		openTasklistAlt(driver);
		shortPause();
		tasklistVisible();
		Tasks assist = new Tasks(driver, "Assist User Task", true);
		assist.openTask(driver);
		assist.taskButton(driver, "Assist Case Owner");
		shortPause();
		openTasklist();
		tasklistVisible();
		Tasks assistPatient = new Tasks(driver, "Assist Me", true);
		assistPatient.openTask(driver);
		assistPatient.taskButton(driver, "Passed");
		shortPause();
		if(!safari){
			stopAssist(driver);
			printResult(test, data, "passed, but STOP ASSIST button still needs to be tested for Safari");
		}
		else
			printResult(test, data, "Passed");
		logout();
		
		}
		catch(Exception e){
			printResult(test, data, "failed");
			driver.get("https://qa3.viinetwork.net/logout");
			e.printStackTrace();
		}

		
/***********************************************
 * 		
 * 		Clear Testing Emails
 * 		
 * 
 ***********************************************/
				
								
	//Delete emails from gmail
	gmail.refreshEmail();
	longPause();
	longPause();
	if (gmail.checkAllEmails("ViiNetwork"))
		gmail.clickDelete();
	shortPause();
	gmail.refreshEmail();
	longPause();
	longPause();
	if (gmail.checkAllEmails("Mr. Tester"))
		gmail.clickDelete();
	shortPause();
	gmail.refreshEmail();
	longPause();
	longPause();
	shortPause();
	if (gmail.checkAllEmails("noreply@viimed.com"))
		gmail.clickDelete();	
	shortPause();
	
	//logout();
	shortPause();
		
		
		
/***********************************************
 * 		
 * 		Delete Final Patient and close webdriver
 * 		if necessary 
 * 
 ***********************************************/		
		
		//  You must first perform 9c Add User Later test in order to create the user
		
		if(safari)
		logout();
		else{
		try{
		login(admin);	
		deleteUser(patient);
		//deleteUser(driver, "enrique5");
		
		logout();
		}
		catch(Exception e){
			shortPause();
			driver.switchTo().alert().accept();
	
			shortPause();
			logout();
	
		}
		}
		//driver.quit();
		
		
	}



}
