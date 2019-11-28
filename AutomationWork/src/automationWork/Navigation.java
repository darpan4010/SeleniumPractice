package automationWork;

import java.util.Set;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Navigation {
	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Resources\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("https://qualysguard.qualys.com/");

		driver.findElement(By.id("myform_UserLogin")).sendKeys("chckf_ta");
		driver.findElement(By.id("myform_UserPasswd")).sendKeys("drYgRiSL48");
		driver.findElement(By.name("_form_action1")).submit();

		JavascriptExecutor js = (JavascriptExecutor)driver;

		//Click on Scan
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement elementScan = driver.findElement(By.xpath("//*[@id=\"top_modules_bar\"]/div[2]/a"));
		js.executeScript("arguments[0].click();", elementScan);

		//Click on inner Scan
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement elementScan1 = driver.findElement(By.xpath("//*[@id=\"ext-gen63\"]/em/span/span"));
		js.executeScript("arguments[0].click();", elementScan1);

		ActualCode ac = new ActualCode();
		ArrayList<String> resultList = ac.displayallIPS();
		System.out.println(resultList.size());

		for(int i=1;i<resultList.size();i++)
		{
			String ipandTime = resultList.get(i);
			System.out.println(ipandTime);
			String test123[] = ipandTime.split("  ");
			//Scan Date
			System.out.println("Scan Date:>"+test123[0]);
			//Start Time
			System.out.println("Start Time:>"+test123[1]);
			//End Time
			System.out.println("End Time:>"+test123[2]);

			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1 = format.parse(test123[1]);
			Date date2 = format.parse(test123[2]);
			long difference = date2.getTime() - date1.getTime(); 
			int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(difference);

			if(minutes<0)minutes += 1440; 
			int hours1 = minutes / 60; //since both are ints, you get an int
			int minutes1 = minutes % 60;
			System.out.printf("%d:%02d", hours1, minutes1);

			String min = Integer.toString(minutes1); 
			//Click on New
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			WebElement elementNew = driver.findElement(By.xpath("//button[text()='New']"));
			js.executeScript("arguments[0].click();", elementNew);

			//Click on Scheduled Scan
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			WebElement elementScheduledscan = driver.findElement(By.xpath("(//*[@class='x-menu-item-text'])[2]"));
			js.executeScript("arguments[0].click();", elementScheduledscan);

			//Switch to iframe for pop 
			driver.switchTo().frame(driver.findElement(By.id("id_common_iframe")));
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";

			js.executeScript(javaScript, driver.findElement(By.xpath("//*[@id='task_edit_form_title']")));
			driver.findElement(By.xpath("//*[@id='task_edit_form_title']")).sendKeys("TEST_"+System.currentTimeMillis());
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			//Scanner appliance dropdown
			WebElement scannerAppliance = driver.findElement(By.xpath("//*[@id=\"task_edit_form_scanner_appliance\"]"));
			Select scannerAppliancedropdown = new Select(scannerAppliance);
			scannerAppliancedropdown.selectByVisibleText("chick1-at1-tms1");

			//Click on Target Host 
			WebElement elementTargetHost = driver.findElement(By.xpath("(//span[@class='x-tab-strip-text x-tot2ivn-vr-tab-strip-text '])[2]"));
			js.executeScript("arguments[0].click()", elementTargetHost);

			//Enter IP's in target Host popup and remove last ,
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@id='id_scantarget_targets']")).sendKeys(test123[3]);
			driver.findElement(By.xpath("//*[@id='id_scantarget_targets']")).sendKeys(Keys.BACK_SPACE);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//Click on Scheduling tab
			WebElement elementScheduling =driver.findElement(By.xpath("(//span[@class='x-tab-strip-text x-tot2ivn-vr-tab-strip-text '])[3]"));
			js.executeScript("arguments[0].click()", elementScheduling);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//Enter Scan Date/Start Date
			driver.findElement(By.xpath("//*[@id='task_edit_form_start_date']")).clear();
			driver.findElement(By.xpath("//*[@id='task_edit_form_start_date']")).sendKeys(test123[0]);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//Enter Start Time
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//*[@title='24 Hour Time']")).clear();
			driver.findElement(By.xpath("//*[@title='24 Hour Time']")).sendKeys(test123[1]);;

			//Click on Duration checkbox
			WebElement elementDuration = driver.findElement(By.xpath("//*[@id='duration']"));
			js.executeScript("arguments[0].click()", elementDuration);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//Select Cancel as the value
			WebElement testDropDown = driver.findElement(By.xpath("//*[@id='task_edit_form_duration_type']"));  
			Select dropdown = new Select(testDropDown);  
			dropdown.selectByVisibleText("Cancel");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//Select After hours as time difference from the dropdown
			WebElement afterElementmin = driver.findElement(By.xpath("//*[@id='task_edit_form_hours']"));
			Select afterDropdown = new Select(afterElementmin);
			afterDropdown.selectByIndex(hours1);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

			//Select After minutes as time difference from the dropdown
			WebElement afterElementsec = driver.findElement(By.xpath("//*[@id='task_edit_form_minutes']"));
			Select afterDropdown1 = new Select(afterElementsec);    
			afterDropdown1.selectByIndex(minutes1);			

/*			//Click on Cancel
			WebElement elementCancel = driver.findElement(By.xpath("(//*[@id='ext-gen13'])[1]"));
			elementCancel.click();*/
			
			
			//Click on Save
			WebElement elementSave = driver.findElement(By.xpath("//*[@id='ext-gen15']"));
			js.executeScript("arguments[0].click()", elementSave);
//			elementSave.click();
			System.out.println("coming here");
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
	
			
		}


	}


}