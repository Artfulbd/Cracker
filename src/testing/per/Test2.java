package testing.per;

import java.util.concurrent.TimeUnit;

public class Test2 {
	public static void main(String []args) {
		try {
			System.out.println("Printing before hold:");
			TimeUnit.SECONDS.sleep(10);
			System.out.println("Printing after hold:");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
