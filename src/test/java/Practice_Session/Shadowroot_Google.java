package Practice_Session;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Shadowroot_Google {

	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com/");
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//div[@class='niO4u VDgVie SlP8xc'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[@aria-label='Google apps']")).click();
		driver.switchTo().frame(0);
		Actions a = new Actions(driver);
//		WebElement ele = driver.findElement(By.xpath("//span[.='Chrome Web Store']"));
//		a.scrollToElement(ele).perform();
//		ele.click();
		
		WebElement ele = driver.findElement(By.xpath("//span[.='Books']"));
		a.scrollToElement(ele).perform();
		ele.click();
		

		
	}

}
