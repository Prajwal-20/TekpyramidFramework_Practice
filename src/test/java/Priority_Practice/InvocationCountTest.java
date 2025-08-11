package Priority_Practice;

import static org.testng.Assert.fail;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.testng.annotations.Test;

public class InvocationCountTest {
	
	@Test(invocationCount = 6)
	public void createContactTest() {
		System.out.println("Execute createContactTest");
	}
	
	@Test(enabled = false)
	public void modifyContactTest() {
		System.out.println("Exceute modifyContactTest");
	}

}
