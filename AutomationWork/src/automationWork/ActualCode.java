package automationWork;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import java.time.Duration;
import java.time.Instant;
public class ActualCode {

	
	public ArrayList<String> displayallIPS()
	{
//		String csvFile = "C:\\Workspace\\AutomationWork\\Resources\\Example Scan Schedule.csv";
		String csvFile = System.getProperty("user.dir")+"\\Resources\\NewSheet.csv";
//		
        String line = "1";
        String cvsSplitBy = ",";
        
        ArrayList<String> strIPs = new ArrayList<>();
        String strDate = null;
        String preDate=null;
        String IPList="";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
           
        	while ((line = br.readLine()) != null) {
//            	System.out.println(line.length());
            	
                // use comma as separator
                String[] val = line.split(cvsSplitBy);
//                System.out.println(val[1]);
                
                if(strDate==null) {
                	strDate  = val[1];
                	preDate=strDate;
                	IPList += val[0] ;
                }                
                else
                {
                	strDate=val[1];
                	if(preDate.equalsIgnoreCase(strDate))
                	{
                		IPList += val[0] + ",";
                	}
                	else
                	{
                		
                		strIPs.add(IPList);
//                		System.out.println(preDate+"--"+IPList+"\n");
                		IPList="";
                		IPList += val[0] + ",";
                		IPList = val[1]+"  "+val[2]+"  "+val[3]+"  "+IPList;
                	}
                	preDate=strDate;
                }
              
            }
        	System.out.println(preDate+"--"+IPList+"\n");
        	strIPs.add(IPList);
//        		System.out.println(strIPs.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return strIPs;
	}
	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		
		ActualCode ac = new ActualCode();
		ArrayList<String> resultList = ac.displayallIPS();
		System.out.println(resultList.size());
		for(int i=0;i<resultList.size();i++)
		{
			System.out.println(resultList.get(i));
		}
		
		/*System.out.println(resultList.size());
		String cvsSplitByTime = "$";
		   
		for(int i=1;i<resultList.size();i++)
		{
				
			String ipandTime = resultList.get(i);
			System.out.println(ipandTime);
			String test123[] = ipandTime.split("  ");
			//Scan Date
			System.out.println("Start Date:>"+test123[0]);
			
			//Start Time
			System.out.println("Start Time:>"+test123[1]);
			//End Time
			System.out.println("End Time:>"+test123[2]);
			
			System.out.println("IP's:- "+test123[3]);
			
			SimpleDateFormat format = new SimpleDateFormat("HH:mm");
			Date date1 = format.parse(test123[1]);
			Date date2 = format.parse(test123[2]);
			long difference = date2.getTime() - date1.getTime(); 
			int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(difference);

			
			if(minutes<0)minutes += 1440; 
			int hours1 = minutes / 60; //since both are ints, you get an int
			int minutes1 = minutes % 60;
			System.out.printf("%d:%02d", hours1, minutes1);
			String a = Integer.toString(hours1);
			System.out.println("aaaaaaa"+a);
			String b = Integer.toString(minutes1);
			System.out.println("bbbbbbb"+b);
			String b = Integer.toString(minutes1);
			System.out.println("bbbbbbb"+b);
			
		}*/
		
	}

}
