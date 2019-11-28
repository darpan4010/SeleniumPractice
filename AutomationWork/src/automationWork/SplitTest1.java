package automationWork;

public class SplitTest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String sample = "https://wallethub.com/profile/test-insurance-company-13732055i";
		String arr[] = sample.split("/");
		for(int i=0;i<arr.length;i++) {
			System.out.println(arr[i]);	
		}
		String arr1[] = arr[4].split("-");
		System.out.println(arr1[3]);
	}

}
