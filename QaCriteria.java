package test1;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class QaCriteria extends seleniumTest{

	//System.setProperty("webdriver.chrome.driver", "/Users/erictillson/Downloads/chromedriver");
    //WebDriver driver = new ChromeDriver();
    //WebDriver driver = new FirefoxDriver();
	//driver = new SafariDriver();
public WebDriver driver;


public QaCriteria(WebDriver driverCurrent, WriteToFile data){   
    String test;
    String field;
    driver = driverCurrent;

    List<WebElement> elements;
    
    printTitle("Qa Criteria", data);
	
/***************************************** 
*		
*		Login to QA
*
*
******************************************/

    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	//driver.get("https://newqa.viinetwork.net/logout");
    driver.get("https://qa3.viinetwork.net/logout");
    resizeWindow(driver);
	Users admin = new Users(driver, "admin");
	shortPause();
	login(driver, admin.getUsername(), admin.getPasswd());
	
	shortPause();
	goToQa();
	//goTo(driver, "Quality Assurance", "Test List");
	
/***************************************** 
*		
*		Test 1a Criteria_EqualsTo
*
*
******************************************/


	criteriaTest(driver, "1a", "Enter 1", "1", data);
	


/***************************************** 
*		
*		Test 1b Criteria_DoesNotEqualTo
*
*
******************************************/
	

	criteriaTest(driver, "1b", "Enter 2", "2", data);

	
/***************************************** 
*		
*		Test 1c Criteria_GreaterThan
*
*
******************************************/
	

	criteriaTest(driver, "1c", "Enter 3", "3", data);
	
	
/***************************************** 
*		
*		Test 1d Criteria_LessThan
*
*
******************************************/
	

	criteriaTest(driver, "1d", "Enter 4", "4", data);
	
	
/***************************************** 
*		
*		Test 1e Criteria_GreatThanOrEqualTo
*
*
******************************************/
	

	criteriaTest(driver, "1e", "Enter 5", "5", data);
	
	
/***************************************** 
*		
*		Test 1f Criteria_LessThanOrEqualTo
*
*
******************************************/
	

	criteriaTest(driver, "1f", "Enter 6", "6", data);
	

	
/***************************************** 
*		
*		1g Criteria_NotInDataSet
*
*
******************************************/
	
	
	
	test = "1g";
	
	getTest(test);
	shortPause();
	
	
	elements = driver.findElements(By.xpath("//input[contains(@type, 'text')]"));    
   
    System.out.println(elements.size());
    if (elements.size() > 0)
    	System.out.println("Test 1g failed");
    else
    	System.out.println("Test 1g passed");
	
	submit();

	testPassed(test, data);
	
	goToQa();
	
/***************************************** 
*		
*		1h Criteria_InDataSet
*
*
******************************************/
	

	criteriaTest(driver, "1h", "Enter 8", "8", data);


	
/***************************************** 
*		
*		1i Criteria_Contains
*
*
******************************************/
	

	criteriaTest(driver, "1i", "Enter viimed", "viimed", data);
	


/***************************************** 
*		
*		1j Criteria_DoesNotContain
*
*
******************************************/
	

	criteriaTest(driver, "1j", "Enter ViiNetwork", "ViiNetwork", data);
	

	
/***************************************** 
*		
*		1k Criteria_AllTrue 
*
*
******************************************/
	
	
	test = "1k";
	
	getTest(test);
	shortPause();
	elements = driver.findElements(By.xpath("//input[contains(@type, 'text')]"));
	System.out.println(elements.size());
	
	if (elements.size() > 9)
		System.out.println("Test 1k Failed");
	
	field = getFieldNum(driver, "Enter 1");
	setField(driver,field, "1" );
	field = getFieldNum(driver, "Enter 2");
	setField(driver,field, "2" );
	field = getFieldNum(driver, "Enter 3");
	setField(driver,field, "3" );
	field = getFieldNum(driver, "Enter 4");
	setField(driver,field, "4" );
	field = getFieldNum(driver, "Enter 5");
	setField(driver,field, "5" );
	field = getFieldNum(driver, "Enter 6");
	setField(driver,field, "6" );
	field = getFieldNum(driver, "Enter 8");
	setField(driver,field, "8" );
	field = getFieldNum(driver, "Enter viimed");
	setField(driver,field, "viimed" );
	field = getFieldNum(driver, "Enter viinetwork");
	setField(driver,field, "viinetwork" );
	submit();
	
	testPassed(test, data);
	
	goToQa();
	
	
/***************************************** 
*		
*		1l Criteria_OneTrue
*
*
******************************************/
	
	test = "1l";
	
	getTest(test);
	shortPause();
	
	field = getFieldNum(driver, "Enter 1");
	setField(driver,field, "1" );
	field = getFieldNum(driver, "Enter 2");
	setField(driver,field, "2" );
	field = getFieldNum(driver, "Enter 3");
	setField(driver,field, "3" );
	field = getFieldNum(driver, "Enter 4");
	setField(driver,field, "4" );
	field = getFieldNum(driver, "Enter 5");
	setField(driver,field, "5" );
	field = getFieldNum(driver, "Enter 6");
	setField(driver,field, "6" );
	field = getFieldNum(driver, "Enter 7");
	setField(driver,field, "7" );
	field = getFieldNum(driver, "Enter viimed");
	setField(driver,field, "viimed" );
	field = getFieldNum(driver, "Enter viinetwork");
	setField(driver,field, "viinetwork" );
	submit();
	
	testPassed(test, data);
	
	goToQa();
	
/***************************************** 
*		
*		test 2a
*
*
******************************************/
	
	criteriaTest(driver, "2a", "Enter 1", "1", data);

	
/***************************************** 
*		
*		Test 2b 
*
*
******************************************/
	
	
	criteriaTest(driver, "2b", "Enter 2", "2", data);
	
/***************************************** 
*		
*		Test 2c
*
*
******************************************/
	
	
	criteriaTest(driver, "2c", "Enter 3", "3", data);
	
/***************************************** 
*		
*		Test 2d
*
*
******************************************/
	

	criteriaTest(driver, "2d", "Enter 4", "4", data);
	
/***************************************** 
*		
*		Test 2e
*
*
******************************************/
	
	
	criteriaTest(driver, "2e", "Enter 5", "5", data);
	
/***************************************** 
*		
*		Test 2f
*
*
******************************************/
	
	
	criteriaTest(driver, "2f", "Enter 6", "6", data);
	
/***************************************** 
*		
*		Test 2g
*
*
******************************************/
	
	
	criteriaTest(driver, "2g", "Enter 7", "7", data);
	
/***************************************** 
*		
*		Test 2h
*
*
******************************************/
	
	test = "2h";
	
	getTest(test);
	
	shortPause();
	elements = driver.findElements(By.xpath("//input[contains(@type, 'text')]"));    
   
	
    System.out.println(elements.size());
    if (elements.size() == 0)
    	System.out.println("Test 2h failed");
    else
    	System.out.println("Test 2h passed");
	
	submit();
	
	longPause();
	
	testPassed(test, data);
	
	goToQa();
	
/***************************************** 
*		
*		Test 2i
*
*
******************************************/
	
	criteriaTest(driver, "2i", "Enter viimed", "viimed", data);

	
/***************************************** 
*		
*		Test 2j
*
*
******************************************/
	
	
	criteriaTest(driver, "2j", "Enter ViiNetwork", "ViiNetwork", data);

	
/***************************************** 
*		
*		Test 2k
*
*
******************************************/
	
	test = "2k";
	
	getTest(test);
	shortPause();

	
	field = getFieldNum(driver, "Enter 1");
	setField(driver,field, "1" );
	field = getFieldNum(driver, "Enter 2");
	setField(driver,field, "2" );
	field = getFieldNum(driver, "Enter 3");
	setField(driver,field, "3" );
	field = getFieldNum(driver, "Enter 4");
	setField(driver,field, "4" );
	field = getFieldNum(driver, "Enter 5");
	setField(driver,field, "5" );
	field = getFieldNum(driver, "Enter 6");
	setField(driver,field, "6" );
	field = getFieldNum(driver, "Enter 7");
	setField(driver,field, "7" );
	field = getFieldNum(driver, "Leave blank");
	setField(driver,field, "" );
	field = getFieldNum(driver, "Enter viimed");
	setField(driver,field, "viimed" );
	field = getFieldNum(driver, "Enter viinetwork");
	setField(driver,field, "viinetwork" );
	submit();
	longPause();
	
	testPassed(test, data);
	
	goToQa();
	
	
/***************************************** 
*		
*		Test 2l
*
*
******************************************/
	
	test = "2l";
	
	getTest(test);
	shortPause();
	
	field = getFieldNum(driver, "Enter 1");
	setField(driver,field, "1" );
	field = getFieldNum(driver, "Enter 2");
	setField(driver,field, "2" );
	field = getFieldNum(driver, "Enter 3");
	setField(driver,field, "3" );
	field = getFieldNum(driver, "Enter 4");
	setField(driver,field, "4" );
	field = getFieldNum(driver, "Enter 5");
	setField(driver,field, "5" );
	field = getFieldNum(driver, "Enter 6");
	setField(driver,field, "6" );
	field = getFieldNum(driver, "Enter 7");
	setField(driver,field, "7" );
	field = getFieldNum(driver, "Enter viimed");
	setField(driver,field, "viimed" );
	field = getFieldNum(driver, "Enter viinetwork");
	setField(driver,field, "viinetwork" );
	submit();
	longPause();
	
	testPassed(test, data);
	
	goToQa();
	
	


/***************************************** 
*		
*		Test 3a
*
*
******************************************/

criteriaTest(driver, "3a", "Enter ViiNetwork", "ViiNetwork", data);

/***************************************** 
*		
*		Test 3b
*		Dynamic Object Criteria Test
*
*
******************************************/





}

public void criteriaTest(WebDriver driver, String test, String fieldName, String input, WriteToFile data){
	getTest(test);
	shortPause();
	String field = getFieldNum(driver, fieldName);
	setField(driver,field, input );
	submit();	
	shortPause();

	testPassed(test, data);
	shortPause();
	goToQa();
}

}
