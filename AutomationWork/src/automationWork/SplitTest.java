package automationWork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class SplitTest {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		JFrame f= new JFrame("TextField Example");  
	    JTextField t1,t2;  
	    t1=new JTextField("Welcome to Javatpoint.");  
	    t1.setBounds(50,100, 200,30);  
	    t2=new JTextField("AWT Tutorial");  
	    t2.setBounds(50,150, 200,30);  
	    f.add(t1); f.add(t2);  
	    f.setSize(400,400);  
	    f.setLayout(null);  
	    f.setVisible(true);  
	    System.out.println(t2.getText().toString());
		
		String csvFile = "C:\\Users\\ac40104\\Desktop\\NewSheet.csv";
        String line = "1";
        String cvsSplitBy = ",";
        
        ArrayList<String> strIPs = new ArrayList<>();
        String strDate = null;
        String preDate=null;
        String IPList="";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
           
        	while ((line = br.readLine()) != null) {
            	
                // use comma as separator
                String[] val = line.split(cvsSplitBy);
               
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
                		System.out.println(preDate+"--"+IPList+"\n");
                		IPList="";
                		IPList += val[0] + ",";
                	}
                	preDate=strDate;
                }
              
            }
        	System.out.println(preDate+"--"+IPList+"\n");
            System.out.println(strIPs.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
	
	}

}
