import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class Assertion_Practice {
	
	@Test
	public void homePageTest(Method mtd) {
		System.out.println(mtd.getName() + "Test Start");
		SoftAssert soft = new SoftAssert();
		System.out.println("Step-1");
		System.out.println("Step-2");
		soft.assertEquals("HomePage", "Home");
		System.out.println("Step-3");
		soft.assertEquals("HomePage", "HomePage");
		System.out.println("Step-4");
		System.out.println(mtd.getName() + "Test End");
		soft.assertAll();
	}
	
	@Test
	public void verifyLogoHomePageTest(Method mtd) {
		System.out.println(mtd.getName() + "Test Start");
		System.out.println("Step-1");
		System.out.println("Step-2");
		Assert.assertTrue(true);
		System.out.println("Step-3");
		System.out.println("Step-4");
		System.out.println(mtd.getName() + "Test End");
	}

}
