package src.Util;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import src.Selenium.Driver;
import org.openqa.selenium.support.ui.Select;


public class CommonUtil {

	//Declare the variables here that will be used either within this class
	//or publicly in some of the classes
	private static Random rnd = new Random();
	private static WebElement element = null;
	private static Actions act = new Actions(Driver.Instance);
	public static String pgTitle = null;
	public static WebDriverWait wait = new WebDriverWait(Driver.Instance, 10000);


	public static String getPageTitle() throws InterruptedException {
  	  	Thread.sleep(2000);
        pgTitle = Driver.Instance.getTitle();
        wait.until(ExpectedConditions.titleContains(pgTitle));
  	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
        return pgTitle;
	}

	public static void clickObject(By object) throws Exception {  
  	  	try { 
  	  		if(Driver.Instance.findElements(object).size() != 0) {
	  	  		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
	  	  	  	element = Driver.Instance.findElement(object);
	  	  	  	Thread.sleep(1000);
	  	  	  	element.click();
	  	  	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);	
  	  		}else {
  	  	  		System.out.println("Failed to find element."); 
  	  		}
  	  	}catch (NoSuchElementException e){
  	  		System.out.println(e.getMessage() + " : " + object + " was not found.");
  	  	}
	}
	
	public static void clickAndHoldObject(WebElement object) throws Exception{
		try { 
  	  		Thread.sleep(2000);
  	  	  	Actions act = new Actions(Driver.Instance);
  	  	  	act.moveToElement(object).clickAndHold(object).perform();
  	  		Driver.Instance.manage().timeouts().implicitlyWait(30000, TimeUnit.MINUTES);
  	  	}catch (NoSuchElementException e){
	  	  		Assert.fail("Failed to find " + object); 
  	  	}
	}
	
	
	public static void selectFromDropdownList(String value) {
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(By.tagName("ul"));
		
	  	List<WebElement> elements = element.findElements(By.tagName("li"));
	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
	  	try {
	  		for(WebElement el : elements) {
		  		if(el.getText() == value) {
		  			Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		  			el.click();
		  		}
	  		}	  		
	  	}catch (NoSuchElementException e){
  	  		Assert.fail(e.getMessage() + " : " + value + " was not found.");
  	  	}
	}
	
	public static void selectOptionFromDropdown(By el, String value) {
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(el);
		element.click();
	
	  	List<WebElement> elements = element.findElements(By.tagName("option"));
	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
	  	try {
	  	//Iterate thru list and check if there are NULLs or blanks
			//If not, add to new list
			for(var tag : elements) {
				Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
				if(tag.getText() == value) {
					Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
					tag.click();
					Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
					break;
				}
	  		}
	  	}catch (NoSuchElementException e){
  	  		Assert.fail(e.getMessage() + " : " + value + " was not found.");
  	  	}
	}
	
	public static void randomSelectOptionFromDropdown(By obj) throws Exception {
		
		//Create a new list
		List<WebElement> allTags = new ArrayList<WebElement>();
		
		//Search for the <ul> tag
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(By.tagName("ul"));
		
	  	List<WebElement> elements = element.findElements(By.tagName("li"));
	  	Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
		
		//Iterate thru list and check if there are NULLs or blanks
		//If not, add to new list
		for(var tag : elements) {
			Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
			if(tag.getText() != "" && tag.getText() != " ") {
				Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
				allTags.add(tag);
				Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
			}
		}
		
		//Store total size of list into an integer variable
		int newAllTags = allTags.size();
		Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
		
		//Using ThreadLocalRandom function, random select from integer variable
		int randomValue = ThreadLocalRandom.current().nextInt(0, newAllTags);
		Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
		
		//Now get the random value selected
		//From the allTags container
		allTags.get(randomValue).click();		
		Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
	}

	public static void randomSelectOptionFromDropdown(List<WebElement> objs) throws Exception {
		
		//Create a new list
		List<WebElement> allTags = new ArrayList<WebElement>();
		
		//Iterate thru list and check if there are NULLs or blanks
		//If not, add to new list
		for(var tag : objs) {
			Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
			if(tag.getText() != "" && tag.getText() != " ") {
				Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
				allTags.add(tag);
				Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
			}
		}
		
		//Store total size of list into an integer variable
		int newAllTags = allTags.size();
		Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
		
		//Using ThreadLocalRandom function, random select from integer variable
		int randomValue = ThreadLocalRandom.current().nextInt(0, newAllTags);
		Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
		
		//Now get the random value selected
		//From the allTags container
		allTags.get(randomValue).click();		
		Driver.Instance.manage().timeouts().implicitlyWait(15000, TimeUnit.MINUTES);
	}
	
	public static boolean doesObjectDisplayText(WebElement obj1, String obj2) {
		var val = obj1.getText();
		
		if(val.contains(obj2)) {
			return true;
		}else
			return false;
	}
	
	public static boolean doesObjectDisplayText(By obj1, String obj2) {
		var val = obj1.toString();
		
		if(val.contains(obj2)) {
			return true;
		}else
			return false;
	}
		
	public static void selectOptionByText(String value) {
		//Selenium Select
		Select s = new Select(Driver.Instance.findElement(By.xpath("//selected[@value='search-alias=aps']")));
		
		//Select by text
		s.selectByVisibleText(value);
	}
	
	public static void selectOptionByInt(int value) {
		//Selenium Select
		Select s = new Select(Driver.Instance.findElement(By.xpath("//selected[@value='search-alias=aps']")));
		
		//Select by text
		s.selectByIndex(value);
	}
	
	//DATE PICKER
	public static void selectDateFromCalendar(By obj1, String value) {
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(obj1);
		
		//Collect a list of dates
		List<WebElement> cols = element.findElements(By.tagName("td"));
		
		//Iterate through the list
		//Select the date you want
		for (WebElement cell : cols) {
			if(cell.getText().equals(value)) {
				cell.findElement(By.linkText(value)).click();
				break;
			}
		}
	}
	
	public static void enterCurrentDate(By obj1) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		Date dateobj = new Date();
		
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(obj1);
		element.sendKeys(df.format(dateobj));
	}
	
	public static void enterPastDate(By obj1, int value) {
		Calendar c = Calendar.getInstance();
		Date dt = c.getTime(); // Now use today date.
		//int val = c.add(Calendar.DATE, 15); // Adds 15 days
		
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(obj1);
		element.sendKeys();
	}
	 
	public static void enterObjectValue(By obj1, String obj2) {
  	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(obj1);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(obj2);
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
	}
	
	public static void enterIntObjectValue(By obj1, int obj2) {
  	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(obj1);
		wait.until(ExpectedConditions.visibilityOf(element));
		element.sendKeys(String.valueOf(obj2));
  	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element.sendKeys(Keys.TAB);
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
	}
	
	public static void doesTextValueExist(By obj1, String obj2) {
  	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(obj1);
  	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		if(element.getText().contains(obj2)) {
			System.out.println(obj1 + " displays successfully.");
		}else {
			Assert.fail(obj2 + " did not display.");
		}
	}
	
	public static void selectDatePicker(By obj, String date){  //Here any date you can give 
	    WebElement eval = Driver.Instance.findElement(obj);
	    List<WebElement> alldates = eval.findElements(By.tagName("td"));
	    for(WebElement cell:alldates){
	         String day=cell.getText();
	            if (cell.getText().equals(date)) {           
	                 cell.click();
	                 break;
	        }
	    }
	}
	
	public void selectEndDate(By obj, String date){  //Here any date you can give 
	    WebElement eval = Driver.Instance.findElement(By.xpath("//div[contains(@class,'calenderReturnSpan_calendar-placeholder')]/table/tbody"));
	    List<WebElement> alldates = eval.findElements(By.tagName("td"));
	    for(WebElement cell:alldates){
	         String day=cell.getText();
	            if (cell.getText().contains(date)) {           
	                 Driver.Instance.findElement(By.xpath("//div[contains(@class,'calenderReturnSpan_calendar-placeholder')]/table/tbody/tr/td[text()='"+ day + "']")).click();
	                 break;
	       }
	    }
	}
	
	public static void enterAmount(int obj2) {
		Random rnd = new Random();
		element.sendKeys(String.valueOf(obj2));
	}
	
	public static void enterRandomPhoneNumber(By el) {
		//Create three integer properties
		int num1 = 0;
		int num2 = 0;
		int num3 = 0;
		
		//Generate a random number for each property
		num1 = rnd.nextInt(999) + 100;
		num2 = rnd.nextInt(999) + 100;
		num3 = rnd.nextInt(9999) + 1000;
		
		//Return the formatted phone number
		String value =  "(" + num1 + ")" + " " + num2 + "-" + num3;

		//Locate element and enter phone number
		Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(el);
		element.sendKeys(value);
	}
	
	public static void enterRandomPostalCode(By el) {
		//Create three integer properties
				int num1 = 0;
				
				//Generate a random number
				num1 = rnd.nextInt(99999) + 10000;
				
				//Return the formatted number
				String value =  Integer.toString(num1);

				//Locate element and enter phone number
				Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
				element = Driver.Instance.findElement(el);
				element.sendKeys(value);
	}
	
	public static void mouseOverElement(By object) {
  	  	Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
		element = Driver.Instance.findElement(object);
		wait.until(ExpectedConditions.visibilityOf(element));
		
		//Hover over the element
		act.moveToElement(element).perform();
        Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);  
	}
	
	//HOMEWORK:  Create a common function that will select the link from a list
	//1.  Create a public void function called clickHyperLink
	//2.  Pass three parameters with the following types:  By values and String value
	//3   Find all the link elements and store them in a List (i.e. List<WebElement) and call it Links
	//4.  Loop through the list until you find the link you want, based on the link text
	
	public void clickHyperLink(By values, String value) throws Exception {
        List<WebElement> links = Driver.Instance.findElements(values);
        Driver.Instance.manage().timeouts().implicitlyWait(10000, TimeUnit.MINUTES);
        for(WebElement link : links)
        {
            if(link.getText().contains(value))
            {
                link.click();
                break;
            }
        }
    }
	
	public static void isCheckBoxSelected(By object) {
		element = Driver.Instance.findElement(object);
		
		List<WebElement> tags = element.findElements(By.tagName("checkbox"));
		for(WebElement tag : tags)
		{
			if(tag.isSelected()) {
				
			}
		}
	}
	

}
