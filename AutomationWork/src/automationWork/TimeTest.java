package automationWork;

public class TimeTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/*
		String toSPlit = "Titan\r\n" + 
				"Analog Silver Dial Men's Watch-NK1584SL03\r\n" + 
				"434\r\n" + 
				"Price of Product is==> More Buying Choices\r\n" + 
				"?2,495 (2 new offers)\r\n" + 
				"";
		String[] arrSplit = toSPlit.split("\\R");
		System.out.println("Name of Product is ==> "+arrSplit[0]+" "+arrSplit[1]);
		System.out.println("Price of Product is==> "+arrSplit[2].substring(1));
		*/
		String userName = "darpansathe12313@gmail.com";
		String ar[] = userName.split("@");
		String usernm = ar[0].replace(".", "_");
		System.out.println(usernm);
		
	}

}
