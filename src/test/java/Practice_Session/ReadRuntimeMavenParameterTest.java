package Practice_Session;

import org.testng.annotations.Test;

public class ReadRuntimeMavenParameterTest {

	@Test
	public void runtimeParameter() {
		System.out.println("hello");
		String URL = System.getProperty("url");
		String BROWSER = System.getProperty("browser");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		System.out.println("Env" +URL);
		System.out.println("Browser" +BROWSER);
		System.out.println("username" +USERNAME);
		System.out.println("password" +PASSWORD);


	}
}
