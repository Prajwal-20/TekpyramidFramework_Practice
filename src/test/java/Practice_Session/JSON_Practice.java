package Practice_Session;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class JSON_Practice {

	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		
		JSONParser praser = new JSONParser();
		Object obj = praser.parse(new FileReader(".//src//test//resources//appCommonData.json"));
		JSONObject map = (JSONObject)obj;
		String URL = map.get("url").toString();
		String BROWSER = map.get("browser").toString();
		String USERNAME = map.get("username").toString();
		String PASSWORD = map.get("password").toString();
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();


	}

}
