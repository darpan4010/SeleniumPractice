package selenium;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
public class SrcshtDrpdwnJS {

	
	public static void takeScreeshot(WebDriver driver, String filePath) throws IOException {
		//Screenshot
				TakesScreenshot scr = (TakesScreenshot)driver;
				File srcFile = scr.getScreenshotAs(OutputType.FILE);
				File destFile = new File(filePath);
				FileHandler.copy(srcFile, destFile);
				
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String email = "abc@centurylink.com";
		String[] arraych  = email.split("@");
		
		Random rand = new Random(); 
		// Generate random integers in range 0 to 999 
		int rand_int1 = rand.nextInt(1000); 
		String emailID = arraych[0]+rand_int1+"@"+arraych[1];
		// Print random integers 
		System.out.println("Random Integers: "+emailID);

		
		

		//How to Initialize the driver.
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		JavascriptExecutor js =(JavascriptExecutor) driver;
		driver.get("https://www.javatpoint.com/call-by-value-and-call-by-reference-in-java");
		
		//Javascript executor
		WebElement ele = driver.findElement(By.xpath("//a[contains(text(),'Java OOPs Concepts')]"));
		js.executeScript("arguments[0].click();", ele);
		takeScreeshot(driver, System.getProperty("user.dir")+"\\Resources\\Test.png");
		
		
		//Dropdown
		/*WebElement ele1 = driver.findElement(By.xpath(""));
		Select oSelect = new Select(ele1);
		oSelect.selectByIndex(1);
		oSelect.selectByValue("Darpan");
		oSelect.selectByVisibleText("Dar");
		oSelect.getOptions();
		oSelect.deselectAll();
		*/
		
		
		driver.close();
		
		
	}

}
