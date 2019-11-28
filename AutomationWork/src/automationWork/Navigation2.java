package automationWork;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;


public class Navigation2 extends WindowAdapter implements ActionListener {
	static WebDriver driver;
	JTextField t1,t2,t3,t4,t5;
	JButton b1; 
	JLabel l1,l2,l3,l4,l5;
	JFrame f= new JFrame("Qualsys Job Execution");

	public void inputTextBox()
	{

		//CSV Path
		l1=new JLabel();  
		l1.setBounds(50,235,100,30);
		
		//Chrome Driver
		l2=new JLabel();  
		l2.setBounds(50,305,100,30);
		
		//URL
		l3=new JLabel();  
		l3.setBounds(50,25,100,30);

		//UserName
		l4=new JLabel();  
		l4.setBounds(50,95,100,30);
		
		//Password
		l5=new JLabel();  
		l5.setBounds(50,165,100,30);

		
		t1=new JTextField("");
		t2=new JTextField("");
		t3=new JTextField("");
		t4=new JTextField("");
		t5=new JTextField("");

		t1.setBounds(50,255, 400,30);
		t2.setBounds(50,325, 400,30);
		t3.setBounds(50,45, 400,30);
		t4.setBounds(50,115, 400,30);
		t5.setBounds(50,185, 400,30);
		

		
		f.add(t1);  
		f.add(t2);
		f.add(t3);
		f.add(t4);
		f.add(t5);

		f.setSize(900,600);  
		f.setLayout(null);  
		f.setVisible(true);  

		f.add(l1);
		f.add(l2);
		f.add(l3);
		f.add(l4);
		f.add(l5);

		l1.setText("Enter the excel sheet path in below text box.Excel should have only 4 columns e.g.:-1. IP	2. Scan Date	3. Start Time	4. End Time");	    
		l1.setSize(l1.getPreferredSize());

		l2.setText("Enter the latest chrome driver path");	    
		l2.setSize(l2.getPreferredSize());
		
		l3.setText("Enter URL");	    
		l3.setSize(l3.getPreferredSize());
		
		l4.setText("Enter Username");	    
		l4.setSize(l4.getPreferredSize());

		
		l5.setText("Enter Password");	    
		l5.setSize(l5.getPreferredSize());
		
		b1=new JButton("Execute");
		b1.setBounds(50,470,80,30);  
		b1.addActionListener(this);
		f.add(b1);
	}
	/*public void windowClosing(WindowEvent e) {  
	    f.dispose();  
	} */
	@Override
	public void actionPerformed(ActionEvent e)  {
		// TODO Auto-generated method stub
		
		long startTime = System.currentTimeMillis();
		String s1=t1.getText();  
		String s2 = t2.getText();
		String s3 = t3.getText();
		String s4 = t4.getText();
		String s5 = t5.getText();
		
		System.out.println("CSV Path:- "+s1);
		System.out.println("ChromeDriver Path:- "+s2);
		System.out.println("URL:- "+s3);
		System.out.println("Username:- "+s4);
		System.out.println("Password:- "+s5);

		if(e.getSource()==b1){  
			System.out.println("Path of CSV sheet:- "+s1);
			System.out.println("Path of ChromeDriver:- "+s2);

			System.setProperty("webdriver.chrome.driver",s2);
			driver = new ChromeDriver();

			driver.get(s3);
			driver.manage().window().maximize();

			driver.findElement(By.id("myform_UserLogin")).sendKeys(s4);
			driver.findElement(By.id("myform_UserPasswd")).sendKeys(s5);
			driver.findElement(By.name("_form_action1")).submit();

			JavascriptExecutor js = (JavascriptExecutor)driver;

			//Click on Scan
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			WebElement elementScan = driver.findElement(By.xpath("//*[@id=\"top_modules_bar\"]/div[2]/a"));
			js.executeScript("arguments[0].click();", elementScan);

			//Click on inner Scan
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			WebElement elementScan1 = driver.findElement(By.xpath("//*[@id=\"ext-gen63\"]/em/span/span"));
			js.executeScript("arguments[0].click();", elementScan1);

			ActualCode2 ac = new ActualCode2();
			ArrayList<String> resultList = ac.displayallIPS(s1);
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
				Date date1 = null;
				try {
					date1 = format.parse(test123[1]);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				Date date2 = null;
				try {
					date2 = format.parse(test123[2]);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				long difference = date2.getTime() - date1.getTime(); 
				int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(difference);

				if(minutes<0)minutes += 1440; 
				int hours1 = minutes / 60; //since both are ints, you get an int
				int minutes1 = minutes % 60;
				System.out.printf("%d:%02d", hours1, minutes1);

				//				String min = Integer.toString(minutes1); 
				//Click on New
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
				WebElement elementNew = driver.findElement(By.xpath("//button[text()='New']"));
				js.executeScript("arguments[0].click();", elementNew);

				//Click on Scheduled Scan
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
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
				
				//*********************************CHANGES ADDED***********************************************
				
				WebElement processingPriority = driver.findElement(By.xpath("//*[@id='task_edit_form_scan_priority']"));
				Select processingPrioritydropdown = new Select(processingPriority);
				processingPrioritydropdown.selectByVisibleText("1 - Emergency");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//(GMT -05:00) United States, Georgia (Eastern Standard Time)		
				
				//*********************************************************************************************

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
				driver.findElement(By.xpath("//*[@title='24 Hour Time']")).sendKeys(test123[1]);

				
				
				//*********************************CHANGES ADDED***********************************************
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("//*[@id='task_edit_form_time_zone']")).clear();
				driver.findElement(By.xpath("//*[@id='task_edit_form_time_zone']")).sendKeys("(GMT -05:00) United States, Georgia (Eastern Standard Time)");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				driver.findElement(By.xpath("//*[@id='task_edit_form_time_zone']")).sendKeys(Keys.TAB);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				
				
				//*********************************************************************************************

				
				
				//Click on Duration checkbox
				WebElement elementDuration = driver.findElement(By.xpath("//*[@id='duration']"));
				js.executeScript("arguments[0].click()", elementDuration);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				//Select Duration Type as the value
				WebElement testDropDown = driver.findElement(By.xpath("//*[@id='task_edit_form_duration_type']"));  
				Select dropdown = new Select(testDropDown);  
				dropdown.selectByVisibleText("Pause");
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				//Select After hours as time difference from the dropdown				
				WebElement afterElementmin = driver.findElement(By.xpath("//*[@id='task_edit_form_hours']"));
				Select afterDropdown = new Select(afterElementmin);
				afterDropdown.selectByIndex(7);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

				/*//Select After minutes as time difference from the dropdown
				WebElement afterElementsec = driver.findElement(By.xpath("//*[@id='task_edit_form_minutes']"));
				Select afterDropdown1 = new Select(afterElementsec);    
				afterDropdown1.selectByIndex(minutes1);	*/		

				/*			//Click on Cancel
	    			WebElement elementCancel = driver.findElement(By.xpath("(//*[@id='ext-gen13'])[1]"));
	    			elementCancel.click();*/

				//*********************************CHANGES ADDED***********************************************
				WebElement endsAfter = driver.findElement(By.xpath("//*[@id='ends_after']"));
				js.executeScript("arguments[0].click()", endsAfter);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				System.out.println("Is enabled"+driver.findElement(By.xpath("//input[@id='task_edit_form_recurrence_occurrences']")).isEnabled());
				driver.findElement(By.xpath("//input[@id='task_edit_form_recurrence_occurrences']")).click();
				driver.findElement(By.xpath("//input[@id='task_edit_form_recurrence_occurrences']")).clear();
				driver.findElement(By.xpath("//input[@id='task_edit_form_recurrence_occurrences']")).sendKeys("1");
				
				
				//Click on Notification tab
				WebElement elementNotifications =driver.findElement(By.xpath("(//span[@class='x-tab-strip-text x-tot2ivn-vr-tab-strip-text '])[4]"));
				js.executeScript("arguments[0].click()", elementNotifications);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				WebElement elementAddGroup =driver.findElement(By.xpath("//a[contains(text(),'Add Group')]"));
				js.executeScript("arguments[0].click()", elementAddGroup);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
				/*ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
				System.out.println(tabs.size());				
				driver.switchTo().window(tabs.get(2));	*/			
				
				/*String your_title = "Add Distribution Group";
				String currentWindow = driver.getWindowHandle();  //will keep current window to switch back
				for(String winHandle : driver.getWindowHandles()){
				   if (driver.switchTo().window(winHandle).getTitle().equals(your_title)) {
				     //This is the one you're looking for
					   System.out.println("Comint here inside the loop after title.");
					   
					   driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
						
					   WebElement distributionGroup = driver.findElement(By.xpath("//select[@id='add_distribution_group_groups']"));
						Select oSelect =  new Select(distributionGroup);
						oSelect.selectByIndex(0);						
												
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);						
					
						WebElement addtoList = driver.findElement(By.xpath("//input[@value='Add to list']"));
						js.executeScript("arguments[0].click()", addtoList);
						driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);												
				     break;
				   } 
				   else {
				      driver.switchTo().window(currentWindow);
				   } 
				}
				*/
				
				String MainWindow=driver.getWindowHandle();		

				// To handle all new opened window.				
				Set<String> s11=driver.getWindowHandles();		
				Iterator<String> i1=s11.iterator();		
				
				while(i1.hasNext())			
				{		
					String ChildWindow=i1.next();		

					if(!MainWindow.equalsIgnoreCase(ChildWindow))			
					{    						
						// Switching to Child window
						driver.switchTo().window(ChildWindow);			
						  WebElement distributionGroup = driver.findElement(By.xpath("//select[@id='add_distribution_group_groups']"));
							Select oSelect =  new Select(distributionGroup);
							oSelect.selectByIndex(0);						
													
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);						
						
							WebElement addtoList = driver.findElement(By.xpath("//input[@value='Add to list']"));
							js.executeScript("arguments[0].click()", addtoList);
							driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);	
					}		
				}		
				// Switching to Parent window i.e Main Window.
				driver.switchTo().window(MainWindow);
				driver.switchTo().frame(driver.findElement(By.id("id_common_iframe")));
				
				//*********************************************************************************************
				
				driver.findElement(By.xpath("//input[@id='after_notification_checkbox']")).click();

				//Click on Save
				WebElement elementSave = driver.findElement(By.xpath("//button[@id='ext-gen15']"));
				js.executeScript("arguments[0].click()", elementSave);
				//	    			elementSave.click();
				System.out.println("coming here");
				driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);


			}
			long endTime = System.currentTimeMillis();
			long executionTime = (endTime-startTime);
			System.out.println("StartTime in Milisec "+startTime);
			System.out.println("EndTime in Milisec"+endTime);
			System.out.println("ExecutionTime in Milisec"+executionTime);
			
			System.out.println("ExecutionTime :- "+TimeUnit.SECONDS.convert(executionTime, TimeUnit.MILLISECONDS)+" Seconds");
			
			driver.findElement(By.xpath("//button[contains(text(),'Logout')]")).click();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			driver.close();
			driver.quit();
		}
	}


	public static void main(String[] args) throws InterruptedException, ParseException {
		// TODO Auto-generated method stub

		Navigation2 nv2 = new Navigation2();
		nv2.inputTextBox();




	}
}