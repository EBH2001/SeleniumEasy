package src.Selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.*;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Driver {

	//Set a webdriver instance
    public static WebDriver Instance = null;
	//public static WebDriverWait wait = new WebDriverWait(Driver.Instance, 45000);
        
    //Initialize driver to open a browser instance of your choice
    public static void Initialize(String BrowserName) throws IOException, InterruptedException
    {
        switch(BrowserName)
        {
            case "CR":
                System.setProperty("webdriver.chrome.driver", "C:\\Users\\evata\\MercuryTours2\\Tools\\chromedriver.exe");
                Instance = new ChromeDriver();
                Driver.Instance.manage().timeouts().implicitlyWait(45000, TimeUnit.SECONDS);
                break;
            default:
                System.setProperty("webdriver.firefox.driver", "C:\\Users\\evata\\OneDrive\\Documents\\Evas Docs_2019\\OtherAutomation\\HDSupply\\eBizAutomation\\eBizDevelopment\\Tools\\Drivers\\selenium-firefox-driver-2.53.1");
                Instance = new FirefoxDriver();
                Driver.Instance.manage().timeouts().implicitlyWait(45000, TimeUnit.SECONDS);
                break;                  
        }
    }
    
    
    //Base URL
    public static String BaseAddress(String Url) throws InterruptedException
    {             
        switch(Url)
        {
            case "MT":
                Driver.Instance.navigate().to("http://demo.guru99.com/test/newtours/index.php");               
                Driver.Instance.manage().timeouts().pageLoadTimeout(45000, TimeUnit.SECONDS);
                break;
        }
		return Url;
    }

    //Go to URL of choice, implement how long to wait before timing out, and maximize the browser
    public static void GoTo() throws InterruptedException
    {
    	Driver.BaseAddress("MT");
    	Driver.Instance.manage().timeouts().pageLoadTimeout(45000, TimeUnit.SECONDS);
        Driver.Instance.manage().window().maximize();   
    	Driver.Instance.manage().timeouts().pageLoadTimeout(45000, TimeUnit.SECONDS);
    }

}
