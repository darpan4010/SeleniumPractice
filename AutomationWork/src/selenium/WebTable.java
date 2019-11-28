package selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebTable {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub


		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://demo.guru99.com/test/web-table-element.php");Thread.sleep(3000);
		driver.get("http://google.com/");Thread.sleep(3000);
		
		
		/*WebElement tableEle = driver.findElement(By.xpath("//table[@class='dataTable']"));
		List<WebElement> tableRows = tableEle.findElements(By.tagName("tr"));
		for(int i=0;i<tableRows.size();i++) {

			List<WebElement> tableColumns = tableEle.findElements(By.tagName("td"));

			for(int j=0;j<tableColumns.size();j++) {

				System.out.println(tableColumns.get(j).getText().toString());

			}
		}*/
	}
}
