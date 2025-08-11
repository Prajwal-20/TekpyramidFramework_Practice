package Priority_Practice;

import org.testng.annotations.Test;

public class PriorityPractice2 {
	
	@Test
	public void createContactTest() {
		System.out.println("Execute createContactTest with HDFC");
	}
	
	@Test
	public void modifyContactTest() {
		System.out.println("Execute createContactTest with ICICI");
		System.out.println("Execute modifyContactTest-->ICICI => ICICI_1");
	}
	
	@Test
	public void deleteContactTest() {
		System.out.println("create UPI contact");
		System.out.println("Execute deleteContactTest=>UPI");
	}

}
