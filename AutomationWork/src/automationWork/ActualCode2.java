package automationWork;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import java.util.ArrayList;

public class ActualCode2 {


	public ArrayList<String> displayallIPS(String csvFile)
	{
		//		String csvFile = "C:\\Workspace\\AutomationWork\\Resources\\Example Scan Schedule.csv";
		//		String csvFile = System.getProperty("user.dir")+"\\Resources\\NewSheet.csv";
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

}
