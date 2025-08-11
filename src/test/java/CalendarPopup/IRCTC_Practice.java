package CalendarPopup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class IRCTC_Practice {

	public static void main(String[] args) throws Throwable {
		
		
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.irctc.co.in/");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
//		driver.switchTo().alert().accept();
//		System.out.println("Alert handled succesfully");
		
		Thread.sleep(2000);
		WebElement ele = driver.findElement(By.xpath("//input[@aria-controls='pr_id_1_list']"));
		ele.click();
		ele.sendKeys("ksr benga");		
		driver.findElement(By.xpath("//span[text()=' KSR BENGALURU - SBC ']")).click();
		
		WebElement ele1 = driver.findElement(By.xpath("//input[@aria-controls='pr_id_2_list']"));
		ele1.click();
		ele1.sendKeys("shiroor");
		driver.findElement(By.xpath("//span[text()=' SHIROOR - SHMI ']")).click();
		
		driver.findElement(By.xpath("//div[@class='ui-dropdown-label-container ng-tns-c65-12']")).click();
		driver.findElement(By.xpath("//span[.='LADIES']")).click();
		
		driver.findElement(By.xpath("//span[@class='ng-tns-c65-11 ui-dropdown-label ui-inputtext ui-corner-all ng-star-inserted']")).click();
		driver.findElement(By.xpath("//span[.='Sleeper (SL)']")).click();
		
		driver.findElement(By.xpath("//span[@class='ng-tns-c59-10 ui-calendar']")).click();
		driver.findElement(By.xpath("//a[.='31']")).click();
		
		driver.findElement(By.xpath("//label[.='Flexible With Date']")).click();
		driver.findElement(By.xpath("//label[.='Railway Pass Concession']")).click();
		driver.findElement(By.xpath("//span[@class='ui-button-icon-left ui-clickable pi pi-check']")).click();

		
		driver.quit();
	}

}
