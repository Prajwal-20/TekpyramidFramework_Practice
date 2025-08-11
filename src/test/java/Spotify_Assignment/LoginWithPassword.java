package Spotify_Assignment;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginWithPassword {

	public static void main(String[] args) throws InterruptedException {		
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://open.spotify.com/");
		driver.findElement(By.xpath("//span[text()='Log in']")).click();
		WebElement mailtxt = driver.findElement(By.xpath("//input[@placeholder='Email or username']"));
		mailtxt.sendKeys("ckavya498@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		Thread.sleep(10000);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@data-testid='search-input']")).sendKeys("arijit singh");
		List<WebElement> songList = driver.findElements(By.xpath("//div[@class='e-91000-text encore-text-body-medium encore-internal-color-text-base btE2c3IKaOXZ4VNAb8WQ standalone-ellipsis-one-line']"));
		for(WebElement song:songList) {
			//System.out.println("Songs by Arijit Singh");
		System.out.println(song.getText());
		}
			
			Thread.sleep(2000);
			driver.findElement(By.xpath("//div[text()='Saiyaara Reprise - Female']")).click();
			driver.findElement(By.xpath("//span[@class='e-91000-baseline e-91000-overflow-wrap-anywhere e-91000-button-primary__inner encore-bright-accent-set e-91000-button-icon-only--large']")).click();
		}
	}

