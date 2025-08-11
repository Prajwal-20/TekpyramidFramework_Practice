package Priority_Practice;

import org.testng.annotations.Test;

public class dependsOnMethod_PracticeTest {
	
	@Test
	public void createOrderTest() {
		System.out.println("Execute createOrder==>123");
		String i = null;
		System.out.println(i.equals("223"));
	}
	
	@Test(dependsOnMethods = "createOrderTest")
	public void billingAnOrderTest() {
		System.out.println("Execute billingAnOrderTest");
	}
	
	@Test(dependsOnMethods = {"createOrderTest", "billingAnOrderTest"})
	public void orderCreatedTest() {
		System.out.println("Exceute orderCreatedTest");
	}
}
