package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ColorFontTooltip {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//How to Initialize the driver.
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
				WebDriver driver = new ChromeDriver();
				driver.get("http://demo.guru99.com/test/delete_customer.php");
				WebElement ele1 = driver.findElement(By.xpath("//h2[@class='barone']"));
				
				String backgroundColor = ele1.getCssValue("background-color");
				String font = ele1.getCssValue("font-size");
				
				
				String titleText = driver.findElement(By.xpath("//a[contains(text(),'Demo Site')]")).getAttribute("title").toString();
				System.out.println("Tooltip is:- "+titleText);
				System.out.println(backgroundColor +" "+ font);
				
				
	}

}
