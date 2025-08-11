package Priority_Practice;

import org.testng.annotations.Test;

public class dependsOnMethodTest {
	
	@Test
	public void createOrderTest() {
		System.out.println("Execute createOrder==>123");
	}
	
	@Test(dependsOnMethods = "createOrderTest")
	public void billingAnOrderTest() {
		System.out.println("Execute billingAnOrderTest");
	}
}
