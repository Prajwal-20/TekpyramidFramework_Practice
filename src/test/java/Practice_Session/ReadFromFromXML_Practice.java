package Practice_Session;

import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class ReadFromFromXML_Practice {
	
	//by passing argument
	
	@Test
	public void sampleTest(XmlTest test) {
		System.out.println(test.getParameter("browser"));
		System.out.println(test.getParameter("url"));
		System.out.println(test.getParameter("username"));
		System.out.println(test.getParameter("password"));
	}

}
