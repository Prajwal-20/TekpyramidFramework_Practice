package Practice_Session;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ShadowRoot_Practice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://demoapps.qspiders.com/ui/shadow?sublist=0");
		 SearchContext shadow = driver.findElement(By.xpath("//div[@class='my-3'][1]")).getShadowRoot();
		shadow.findElement(By.cssSelector("input[type='text']")).sendKeys("abc@gmail.com");
		
		 SearchContext shadow1 = driver.findElement(By.xpath("//div[@class='my-3'][2]")).getShadowRoot();
		shadow1.findElement(By.cssSelector("input[type='text']")).sendKeys("xyz@123");
	}

}
