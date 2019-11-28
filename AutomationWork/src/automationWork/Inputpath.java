package automationWork;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Inputpath implements ActionListener {

	JTextField t1;
    JButton b1; 
    JLabel l1;
	public void inputTextBox()
	{
		JFrame f= new JFrame("Qualsys Job Execution");  
		
		l1=new JLabel();  
	    l1.setBounds(50,25,100,30);  
	    
	    t1=new JTextField("");  
	    t1.setBounds(50,50, 400,30);  
	    f.add(t1);  
	    f.setSize(800,400);  
	    f.setLayout(null);  
	    f.setVisible(true);  
	    f.add(l1);
	    l1.setText("Enter the excel sheet path in below text box.Excel should have only 4 columns e.g.:-1. IP	2. Scan Date	3. Start Time	4. End Time");	    
	    l1.setSize(l1.getPreferredSize());
	    b1=new JButton("Execute");
	    b1.setBounds(50,100,80,30);  
	    b1.addActionListener(this);
	    f.add(b1);
	    
	    
	    
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		 String s1=t1.getText();  
	          
	         
	        
	        if(e.getSource()==b1){  
	        	System.out.println("Path of Excel sheet:- "+s1);
	        	
	        	
	        }
	          
	          
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Inputpath ip = new Inputpath();
		ip.inputTextBox();
    
	}
	

}
