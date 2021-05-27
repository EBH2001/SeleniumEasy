package src.Util;

import java.io.File;
import java.io.IOException;
import src.Selenium.Driver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;


public class CaptureScreenshots {
	
	public static String captureScreenshot(WebDriver instance) throws IOException {
		File scrFile = ((TakesScreenshot) Driver.Instance).getScreenshotAs(OutputType.FILE);
		File Dest = new File("./reports/Screenshots/" + System.currentTimeMillis() + ".png");
		
		String imgpath = Dest.getAbsolutePath();
		FileHandler.copy(scrFile, Dest);
		
		return imgpath;
	}

}
