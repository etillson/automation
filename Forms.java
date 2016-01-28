package test1;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Forms extends seleniumTest{

	String formName;
	String title;
	String optionalSub;
	String optionalDes;
	boolean content;
	boolean published;
	String cache;
	String[][] permissions;
	WebDriverWait wait;
	WebDriver driver;
	WebElement form;
	//This defines the filters for forms. Element 1 and 2 are tags, 3 is Name, 4 is Title
	String[] filters = {null, null, null, null};

/************************************
 * 
 * Constructors
 * 	
 ***********************************/	
	
	
	public Forms(WebDriver driverNew, String form, String[] filterNames){
			
		driver = driverNew;
		wait = new WebDriverWait(driver, 10);
		formName = form;
		filters = filterNames;
		
		/*getSettingsCBs(driver, "published", wait);
		getSettingsCBs(driver, "permissions", wait);
		getSettingsCBs(driver, "permissions_results", wait);
		getSettingsCBs(driver, "permission_set", wait);
		getSettingsCBs(driver, "case_select", wait);
		*/
	}

	public void setForm(String formName){
		boolean found = false;
		List<WebElement> formList = driver.findElements(By.cssSelector("td"));
		for(int x = 0; x< formList.size(); x++){
	    	if (formList.get(x).getText().contains(formName)){
	    		form = formList.get(x+5);
	    		found = true;
	    		
	    	}	
		}
		if (!found)
		form = null;
	}
	
	//Publish the form
	public void publish(){
		driver.findElement(By.xpath("//a[contains(., 'Publish Changes')]")).click();
	}
	
	public WebElement getForm(){
		return form;
	}
	

	
/************************************
 * 
 * Field Inspector
 * 	
 ***********************************/
	
	public void openFieldInspector(WebDriver driver, String fieldLabel){
		
		try{
		WebElement label = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//fb-field-main-controls[contains(.,'Text Field')]")));
		System.out.println(label.getText());
		WebElement inpector = label.findElement(By.xpath("./div/div//a[contains(text(), 'Inspect')]"));
		inpector.click();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		//*[@id="field-2f8ef149630d57a7757b59f5af7feff6f1eb776693f7ca34f4080b5f960d94d8-wrapper"]/fb-field-main/div[1]/fb-field-main-controls/div/div[2]
	}
	
	//Select a checkbox in the permissions section of the inspector - the num is either 1 for who can use the field or
	//2 for who can see data captured by the field
	public void permSelect (WebDriver driver, String[] choices, int num){
		try{
		List <WebElement> checkbox = driver.findElements(By.cssSelector("fb-field-permissions checkbox-field"));
		for(int x = 0; x<choices.length; x++){
			WebElement box = checkbox.get(num-1).findElement(By.xpath(".//li[contains(., '" + choices[x] +"')]"));
			box.click();
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
		
	public WebElement openInspectorPart(WebDriver driver, String part){
		WebElement inspector = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.cssSelector("section[inspector]")));
		WebElement isOpen = inspector.findElement(By.cssSelector("dd[is-open='true']"));
		System.out.println(inspector.getText());
		WebElement actions = isOpen.findElement(By.xpath("following-sibling::dd//a[contains(text(), '" + part + "')]"));
		actions.click();
		return actions;
	}
	
	public void clearCheckedInspector(int num){
		try{
		List <WebElement> checkbox = driver.findElements(By.cssSelector("fb-field-permissions checkbox-field"));
		List <WebElement> inputs = checkbox.get(num-1).findElements(By.cssSelector("input[type='checkbox']"));
		for(int x = 0; x< inputs.size(); x++){
			if(inputs.get(x).isSelected())
				inputs.get(x).click();
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
/************************************
 * 
 * Form Actions
 * 	
 ***********************************/
	
	public void formAction(WebDriver driver, String action, WebElement element){
		System.out.println(element.getText());
		WebElement actionElement = element.findElement(By.xpath(".//span[contains(text(), '" + action + "')]"));
		actionElement.click();
	}
	
	public void findEndpoint(WebDriver driver){
		
		WebElement endpoint = (new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//span[contains(text(), 'endpoint')]")));
		System.out.println(endpoint.getText());
		WebElement inspector = endpoint.findElement(By.xpath("../preceding-sibling::div//a[contains(text(), 'Inspect Field')]"));
		inspector.click();
	}

	
	public void openAction(WebDriver driver, String action, WebElement actions){
		WebElement element = actions.findElement(By.xpath("//a[contains(text(), '"+ action +"')]"));
		element.click();
	}
	
	public void getSettingsCBs(WebDriver driver, String listType, WebDriverWait wait){
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//label[contains(@for, '"+listType+"')]/following::div")));
		List<WebElement>listElements = driver.findElements(By.xpath("//label[contains(@for, '"+listType+"')]/following::div[1]//input"));

		System.out.println(listElements.size());
		System.out.println(field.getAttribute("class"));
		String [] lines = divLines(field.getText());
		String [] [] permissions = new String [listElements.size()][2];
		for(int x = 0; x < lines.length-1; x++){
			permissions[x][0] = lines[x];
			permissions[x][1] = listElements.get(x).getAttribute("checked");
			System.out.println(permissions[x][0] + " " + permissions [x][1]);			
		}
	}

	public void getSettingsTBs(WebDriver driver, String listType, WebDriverWait wait){
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//label[contains(@for, '"+listType+"')]/following::div")));
		List<WebElement>listElements = driver.findElements(By.xpath("//label[contains(@for, '"+listType+"')]/following::div[1]//input"));
	
		System.out.println(listElements.size());
		System.out.println(field.getAttribute("class"));
		String [] lines = divLines(field.getText());
		String [] [] permissions = new String [listElements.size()][2];
		for(int x = 0; x < lines.length-1; x++){
			permissions[x][0] = lines[x];
			permissions[x][1] = listElements.get(x).getAttribute("checked");
			System.out.println(permissions[x][0] + " " + permissions [x][1]);			
		}
	}
		
	public void getSettingsTAs(WebDriver driver, String listType, WebDriverWait wait){
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//label[contains(@for, '"+listType+"')]/following::div")));
		List<WebElement>listElements = driver.findElements(By.xpath("//label[contains(@for, '"+listType+"')]/following::div[1]//input"));
	
		System.out.println(listElements.size());
		System.out.println(field.getAttribute("class"));
		String [] lines = divLines(field.getText());
		String [] [] permissions = new String [listElements.size()][2];
		for(int x = 0; x < lines.length-1; x++){
			permissions[x][0] = lines[x];
			permissions[x][1] = listElements.get(x).getAttribute("checked");
			System.out.println(permissions[x][0] + " " + permissions [x][1]);			
		}
	}

	public void getSettingsDDBs(WebDriver driver, String listType, WebDriverWait wait){
		WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//label[contains(@for, '"+listType+"')]/following::div")));
		List<WebElement>listElements = driver.findElements(By.xpath("//label[contains(@for, '"+listType+"')]/following::div[1]//input"));
	
		System.out.println(listElements.size());
		System.out.println(field.getAttribute("class"));
		String [] lines = divLines(field.getText());
		String [] [] permissions = new String [listElements.size()][2];
		for(int x = 0; x < lines.length-1; x++){
			permissions[x][0] = lines[x];
			permissions[x][1] = listElements.get(x).getAttribute("checked");
			System.out.println(permissions[x][0] + " " + permissions [x][1]);			
		}		
	}
	
	private String[] divLines(String str){
		   String[] lines = str.split("\r\n|\r|\n");
		   return  lines;
		}

	
	//Actions - accessing and checking action settings within a form
	//Send the action component (button, rule, timer etc..) and it returns the Id in a String
	public String getATQId(WebDriver driver, String actionComponent, String idTag){
		try{
			WebElement component = driver.findElement(By.cssSelector("fb-action-addtoqueue-"+actionComponent+" label[for*='" + idTag +"']"));
			return component.getAttribute("for");
		}
		catch(Exception e){
			System.out.println("The "+ actionComponent + " component wasn't found");
			return null;
		}
	}
	public WebElement getSelectField(WebDriver driver, String component){
		WebElement select = driver.findElement(By.cssSelector("fb-action-addtoqueue-button select[id='"+component+"']"));
		return select;
	}
	
	public int countButtons(WebDriver driver, String actionComponent, String idTag){
		List<WebElement> numActionType = driver.findElements(By.cssSelector("fb-action-addtoqueue-"+actionComponent+" label[for*='" + idTag +"']"));
		return numActionType.size();
	}
	
	public void closeActionModal(WebDriver driver){
		WebElement close = driver.findElement(By.cssSelector("fb-action-modal a.close-reveal-modal"));
		close.click();
	}
		
	public void clickAction(String action){
		form.findElement(By.xpath(".//span[contains(text(), '"+ action +"')]")).click();
	}
	
/************************************
 * 
 * Forms Admin Page
 * 	
 ***********************************/	
	
	//This method navigates the form Admin List Page to find the desired form
	public WebElement findForm(WebDriver driver){
		(new WebDriverWait(driver, 10)).until(ExpectedConditions.visibilityOfElementLocated
				(By.xpath("//th[contains(text(), 'Name')]")));
		List<WebElement> total = driver.findElements(By.tagName("td"));
		System.out.println(total.size());
		for (int k=0; k < total.size(); k++){
			if (total.get(k).getText().equalsIgnoreCase(formName)){
				System.out.println(k);
				System.out.println(total.get(k).getText());
				return total.get(k+5);
			}

		}
		if(next(driver)){

			return findForm(driver);
			
		}
		return null;		
	}
	
	//this clicks the view form button on the form list page
	public void openForm(){
		Filters formFilter = new Filters(driver, filters, "forms");	
		formAction(driver, "View", findForm(driver));
		formVisible(driver);
	}
	
	//this clicks the update form button on the form list page
	public void updateForm(){
		Filters formFilter = new Filters(driver, filters, "forms");	
		formAction(driver, "Update", findForm(driver));
		
	}
	
	//Open the first result from a form submission from the Form Results admin page
	public void viewResult(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(., 'View')]//a"))).click();
	}
	
	//this clicks the results form button on the form list page
	public void getFormResults(){
		Filters formFilter = new Filters(driver, filters, "forms");	
		formAction(driver, "Results", findForm(driver));
		pageTitleVisible("Form Results");
		
	}
	
	//This method creates a wait period until the form builder loads
	public void fbVisible(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("header")));

	}
	


/************************************
 * 
 * Form Settings
 * 	
 ***********************************/
		
	
	//this method opens the form settings found at the top of the form builder
	public void openSettings(){
		driver.findElement(By.cssSelector("a[ng-click*='openFormSettings']")).click();
	}
	
	/*
	public void openFormSettings(WebDriver driver){
		WebElement settings = driver.findElement(By.xpath("//a[contains(@class, 'form-settings')]"));
		settings.click();
		
	}
	*/
	
	//This method is passed which checkbox 1 for "Who can use this form?" and 2 "Who can view the data?"
	//It then selects the checkboxes who's values are passed in the array
	public void setSettingsCheckbox(int num, String[] roles){
		try{
		List <WebElement> checkbox = driver.findElements(By.cssSelector("fb-form-settings-modal checkbox-field"));
		for(int x = 0; x< roles.length; x++){
			WebElement box = checkbox.get(num-1).findElement(By.xpath(".//li[contains(., '" + roles[x] +"')]"));
			box.click();
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void clearChecked(int num){
		try{
		List <WebElement> checkbox = driver.findElements(By.cssSelector("fb-form-settings-modal checkbox-field"));
		List <WebElement> inputs = checkbox.get(num-1).findElements(By.cssSelector("input[type='checkbox']"));
		for(int x = 0; x< inputs.size(); x++){
			if(inputs.get(x).isSelected())
				inputs.get(x).click();
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
