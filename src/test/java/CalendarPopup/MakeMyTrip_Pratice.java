package CalendarPopup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MakeMyTrip_Pratice {

	public static void main(String[] args) throws Throwable {
		
		ChromeOptions options=new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.makemytrip.com/");
		
		driver.findElement(By.xpath("//span[@class='commonModal__close']")).click();
		driver.findElement(By.xpath("//span[@class='styles__Close-sc-1bytt3z-0 kezeYI']")).click();
		
		driver.findElement(By.xpath("//li[@data-cy='roundTrip']")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@id='fromCity']")).click();
		driver.findElement(By.xpath("//input[@aria-autocomplete='list']")).sendKeys("pune");
		driver.findElement(By.xpath("//p[.='Pune Airport']")).click();
		
		driver.findElement(By.xpath("//input[@id='toCity']")).click();
		driver.findElement(By.xpath("//input[@aria-autocomplete='list']")).sendKeys("benga");
		driver.findElement(By.xpath("//span[text()='Bengaluru']")).click();
		
		Thread.sleep(2000);
		//driver.findElement(By.id("departure")).click();
		for(;;) {
			try {
				driver.findElement(By.xpath("//div[text()='September 2025']/ancestor::div[@class='DayPicker-Month']/descendant::p[text()='30']")).click();
				break;
			}catch(Exception e) {
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			}
		}
		
		driver.findElement(By.xpath("//div[@data-cy='returnArea']")).click();
		for(;;) {
			try {
				driver.findElement(By.xpath("//div[text()='November']/ancestor::div[@class='DayPicker-Month']//p[.='20']")).click();
				break;
			}catch(Exception e) {
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			}
		}

		
		driver.quit();
	}

}
