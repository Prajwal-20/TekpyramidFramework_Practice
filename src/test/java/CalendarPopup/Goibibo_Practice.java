package CalendarPopup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Goibibo_Practice {
	public static void main(String[] args) throws Throwable {
		
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.goibibo.com/");
		
		driver.findElement(By.xpath("//span[@class='logSprite icClose']")).click();
		driver.findElement(By.xpath("//p[@class='sc-jlwm9r-1 ewETUe']")).click();
	    WebElement ele1 = driver.findElement(By.xpath("//div[@class='sc-12foipm-22 kGtxGm']/descendant::p[@class='sc-12foipm-6 erPfRs']"));
	    ele1.click();
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("chennai");
	    driver.findElement(By.xpath("//div[@class='sc-12foipm-32 jVTqNw']/descendant::p[@class='sc-12foipm-33 kUcHKT']//span[text()='Chennai, India']")).click();
	    
	    WebElement ele2 = driver.findElement(By.xpath("//div[@class='sc-12foipm-25 fbAAhv']/descendant::input[@type='text']"));
		ele2.click();
		ele2.sendKeys("shirdi");
		driver.findElement(By.xpath("//div[@class='sc-12foipm-32 jVTqNw']/descendant::p[@class='sc-12foipm-33 kUcHKT']//span[text()='Shirdi, India']")).click();
		
		driver.findElement(By.xpath("//span[text()='Departure']/ancestor::div[@class='sc-12foipm-2 eTBlJr fswFld ']/descendant::p[@class='sc-12foipm-4 czGBLf fswWidgetTitle']")).click();
		for(;;) {
			try {
				driver.findElement(By.xpath("//div[text()='September 2025']/ancestor::div[@class='DayPicker-Month']/descendant::div[@class='DayPicker-Day']/p[.='20']")).click();
			break;
			}catch(Exception e) {
				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
			}
		}
		
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//span[text()='Return']/ancestor::div[@class='sc-12foipm-20 jPzQOy']/descendant::p[@class='sc-12foipm-4 czGBLf fswWidgetTitle']")).click();
//		for(;;) {
//			try {
//				driver.findElement(By.xpath("//div[text()='December 2025']/ancestor::div[@class='DayPicker-Month']/descendant::div[@class='DayPicker-Day']/p[.='18']")).click();
//			}catch(Exception e) {
//				driver.findElement(By.xpath("//span[@aria-label='Next Month']")).click();
//			}
//		}
}
}
