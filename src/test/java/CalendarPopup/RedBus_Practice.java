package CalendarPopup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RedBus_Practice {
	
	public static void main(String[] args) throws Throwable {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.redbus.in/");
		
//		Thread.sleep(3000);
//		WebElement ele = driver.findElement(By.xpath("//div[text()='From']"));
//		ele.click();
//		ele.sendKeys("chennai");
//		driver.findElement(By.xpath("//div[@class='listHeader___40b031']")).click();
		
		driver.findElement(By.xpath("//div[@class='dateInputWrapper___f4c22e']")).click();
		driver.findElement(By.xpath("//span[.='20']")).click();
		
	}

}
