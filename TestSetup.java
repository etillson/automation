package test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.runners.Suite;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

public class TestSetup {

	public static void main(String[]args){
		try{
		System.out.println("hello");
		XmlSuite suite = new XmlSuite();
		suite.setName("Suite");
		 
		XmlTest test = new XmlTest(suite);
		
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("browser", "chrome");

		test.setName("ChromeTest");
		test.setParameters(map);
		List<XmlClass> classes = new ArrayList<XmlClass>();
		System.out.println("hello");
		classes.add(new XmlClass("test1.TestNGConfig"));
		
		test.setXmlClasses(classes) ;
		List<XmlSuite> suites = new ArrayList<XmlSuite>();
		suites.add(suite);
		
		CheckBoxMenu cb = new CheckBoxMenu();
		/*
		TestNG tng = new TestNG();
		tng.setXmlSuites(suites);
		tng.run();
		*/
		
		}
		catch(Exception e){
			e.printStackTrace();
		}

	}
	

	
}