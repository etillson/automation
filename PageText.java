package test1;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class PageText extends seleniumTest{

	
	
	public List <String> pageText = new ArrayList <String>();
	public String pageTextExact;
	
//Constructor in which the tests run
	
public PageText(String page){
    

	if(page.contentEquals("Manage Access Tokens")){
		pageText.add("Manage Access Tokens");
		pageText.add("View and manage your site's access tokens.");
		pageText.add("Manage Access Token Batches");
		pageText.add("Add Access Token Batch");
		pageText.add("Tools");
		pageText.add("With Selected...");
		pageText.add("Expire");
		pageText.add("Name Created Used / Total Tokens Expires Expired Actions");
		pageText.add("© 2015 ViiNetwork");
		pageText.add("Powered by ViiMed™");	
	}
	
	
	if(page.contentEquals("Access Token Batch"))
	pageTextExact = "Access Token Batch\nCreate an access token batch.\nManage Access Token Batches\nAdd Access Token Batch\n"+
	"Batch Name\nNumber To Generate\nMust Be An Integer Between 1 And 1000.\nExpiration Date\nChoose Date\n" +
	"Required Expiration Date For This Batch. The Batch Will Expire At The Start Of This Day.\nChoose Form\n" +
	"Select\nClone 0 AddUser_AddRelationship_AddCase_milestones\nClone 4 AddUser_AddRelationship_AddCase_milestones\n" +
	"Clone - Add Case Milestone and User\nChoose The Form That This Batch Of Tokens Will Grant Access To. Required.\n" +
	"Field Defaults\nThere are no eligible fields that can have their defaults set.\nCreate Batch\n© 2015 ViiNetwork\n" +
	"Powered by ViiMed™";
		
}	


public boolean checkPageText(String text){
	System.out.println(pageText.size());
	for(int x = 0; x < pageText.size(); x++){
		if(!text.contains(pageText.get(x)))
				return false;	
	}
	System.out.println("Page text matches");
	return true;
}

public boolean checkPageTextExact(String text){
	
	if(!pageTextExact.contentEquals(text))
				return false;	
	
	return true;
}


}
	
	

