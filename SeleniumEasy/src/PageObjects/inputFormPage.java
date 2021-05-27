package src.PageObjects;

import org.openqa.selenium.By;
import java.util.List;
import src.Util.CommonUtil;

public class inputFormPage {
	
	private static By txtEnterMessage = By.id("user-message");
	private static By btnShowMessage = By.xpath("//button[text()='Show Message']");
	private static By txtMessage = By.id("display");
	private static By txtValue1 = By.id("sum1");
	private static By txtValue2 = By.id("sum2");
	private static By btnGetTotal = By.xpath("//button[text()='Get Total']");
	private static By txtTotal = By.id("displayvalue");
	public static String message = "This is my very important message";
	
	public static void enter_message(String value) {
		CommonUtil.enterObjectValue(txtEnterMessage, value);
	}
	
	public static void clickShowMessage() throws Exception {
		CommonUtil.clickObject(btnShowMessage);
	}
	
	public static void doesMessageDisplay(String value) {
		CommonUtil.doesObjectDisplayText(txtMessage, value);
	}
	
	public static void enter_value1_data(String value) {
		CommonUtil.enterObjectValue(txtValue1, value);
	}

}
