package CalendarPopup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class KSRTITC_Practice {

	public static void main(String[] args) throws Throwable {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.ksrtc.in/");
		
		driver.findElement(By.xpath("//span[text()='Select Departure City']")).click();
		WebElement ele1 = driver.findElement(By.xpath("//div[@class='chosen-drop']/descendant::div[@class='chosen-search']//input[@placeholder='Search Your City Name']"));
		ele1.sendKeys("bengaluru");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//li[@class='active-result result-selected']")).click();
		
//		driver.findElement(By.xpath("//input[@id='departDate']")).click();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//span[.='August']/ancestor::div[@class='ui-datepicker-group ui-datepicker-group-last']/descendant::a[.='15']")).click();
				
	}
}
