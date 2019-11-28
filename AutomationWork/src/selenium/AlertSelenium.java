package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AlertSelenium {

	public static boolean isAlertPresent(WebDriver driver) {
		
		try {
		driver.switchTo().alert();
		return true;
			
		} catch (NoAlertPresentException e) {
			// TODO: handle exception
			return false;
		}		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//How to Initialize the driver.
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://demo.guru99.com/test/delete_customer.php");
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys("143");
		driver.findElement(By.xpath("//input[@name='submit']")).click();
		Boolean flag = isAlertPresent(driver);
		System.out.println(flag);
		if(flag == true)
		{
			driver.switchTo().alert().accept();
			
		}
		Boolean flag1 = isAlertPresent(driver);
		System.out.println(flag1);
		if(flag1 == true)
		{
			String textAlert = driver.switchTo().alert().getText();
			System.out.println(textAlert);
			driver.switchTo().alert().accept();
			
		}
	}

}
