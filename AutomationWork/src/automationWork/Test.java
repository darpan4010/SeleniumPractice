package automationWork;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
public class Test {
	

	
		
		public static String yesterdayDate()
		{
			  Calendar cal = Calendar.getInstance();
		        DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss");
		       // System.out.println("Today's date is "+dateFormat.format(cal.getTime()));

		        cal.add(Calendar.DATE, -1);
		        String str= dateFormat.format(cal.getTime());
		     return str;
		}

		public static void main(String[] args) {
		
		        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		       
		        
		      //  dateFormat.format(yesterday());
			
			//Date yesterday = new Date(System.currentTimeMillis() - 1000L * 60L * 60L * 24L);
			
			//System.out.println(yesterday);
		      
		     System.out.println(yesterdayDate());

		}

	} 

