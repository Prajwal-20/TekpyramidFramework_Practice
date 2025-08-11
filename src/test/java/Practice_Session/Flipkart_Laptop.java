package Practice_Session;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Flipkart_Laptop {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.findElement(By.xpath("//span[contains(text(),'Electronics')]")).click();
		driver.findElement(By.xpath("//input[@class='zDPmFV']")).sendKeys("laptops");
		driver.findElement(By.xpath("//button[@class='MJG8Up']")).click();
		Actions a = new Actions(driver);
		a.scrollByAmount(0, 500).perform();
		Thread.sleep(2000);
		List<WebElement> ratings = driver.findElements(By.xpath("//div[@class='KzDlHZ']|//div[@class='KzDlHZ']/ancestor::div[@class='yKfJKb row']/descendant::div[@class='Nx9bqj _4b5DiR']|//div[@class='KzDlHZ']/ancestor::div[@class='yKfJKb row']/descendant::span[contains(text(),'Ratings')]"));
		for(WebElement rate:ratings) {
			System.out.println(rate.getText());
		}
	}

}
