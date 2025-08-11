package Broken_Links_Practice;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BrokenLinksPractice {

	
	@Test
	public void handleBrokenLinks() throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.onlinesbi.sbi/");
		List<WebElement> allLinks = driver.findElements(By.xpath("//a"));
		System.out.println(allLinks.size());
		for(WebElement link:allLinks) {
			@Nullable
			String eachLink = link.getAttribute("href");
			
		try {
				URL url = new URL(eachLink);
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				int statusCode = con.getResponseCode();
				if(statusCode>=400) {
					System.out.println(eachLink + "Broken Links");
				}
		}catch(Exception e) {
			
		}
		}
		driver.quit();
	}
}
