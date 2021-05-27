package test.BaseClass;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.text.SimpleDateFormat;
import java.util.Date;

import src.Selenium.Driver;
import java.io.File;
import java.io.IOException;

public class BaseClass {
	
	public static ExtentTest test = null;
	public static ExtentReports report = null;
	
	@BeforeClass
	public static void setUp() throws IOException, InterruptedException {
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		initReporting(System.getProperty("user.dir") + "./reports/MercuryToursRpt" + timeStamp + ".html");
		Driver.Initialize("CR");
		Driver.GoTo();
	} 
	
	//Initialize the reporting and add parameters
	public static void initReporting(String filePath) {
		
		//Add reporter to extent report
        report = new ExtentReports(filePath, false);
        report.addSystemInfo("Author:", "Eva Tate");
        report.addSystemInfo("Browser:", "Chrome 8.0.4240.183");
        report.addSystemInfo("Environment:", "www.merurytours.com");
        report.loadConfig(new File(System.getProperty("user.dir") + "\\src\\test\\Reports\\extent-config.xml"));
    }
	
	
	//Write to the report and close
	public static void writeReport() {
		if(report != null) {
			report.flush();
		}
	}
	
	@AfterClass
	public static void tearDown() throws IOException {
		writeReport();	
		Driver.Instance.close();
	}
	
	@After
	public void testStatus() throws IOException, InterruptedException {
		var status = test.getRunStatus();
		//var stackTrace =  " + TestContext.CurrentContext.Result.StackTrace + ";
		
		if(status == LogStatus.PASS) {
			test.log(LogStatus.PASS, "Test Passed");
		}else {
			test.log(LogStatus.FAIL, test.addScreenCapture(src.Util.CaptureScreenshots.captureScreenshot(Driver.Instance)));
			Driver.Instance.navigate().to(Driver.BaseAddress("MT"));
		}	
		report.endTest(test);
	}		
    
//	public static void testStatus(){
//	    if(LogStatus.FAIL != null){
//	        try{
//	            TakesScreenshot screenshot = (TakesScreenshot)Driver.Instance;
//	            File source = screenshot.getScreenshotAs(OutputType.FILE);
//	            FileHandler.copy(source, new File("logs/screenshots/" + test.getTest() + ".png"));
//	            System.out.println("Screenshot taken");
//	        } catch (Exception e){
//	            System.out.println("Exception while taking screenshot " + e.getMessage());
//	        }
//	    }
//	}

}
