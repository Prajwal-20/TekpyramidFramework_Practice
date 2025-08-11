package Practice_Session;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProperyFile_Practice {

	public static void main(String[] args) throws IOException, Exception {
		
		FileInputStream fis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[contains(text(),'Organizations')])[1]")).click();
		
		driver.findElement(By.xpath("//img[@alt='Create Organization...']")).click();
		WebElement ele = driver.findElement(By.xpath("//input[@name='accountname']"));
		ele.sendKeys("CMR_TEK4");
		driver.findElement(By.xpath("(//input[@name='assigntype'])[1]")).click();
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
		
		
		Thread.sleep(2000);
		//driver.switchTo().alert().accept();
		
		String ele1 = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(ele1.contains("CMR_TEK4")) {
			System.out.println(ele1);
		} else {
			System.out.println("creation failed");
		}
		
		driver.findElement(By.xpath("(//td[@class='small'])[2]")).click();
		driver.findElement(By.xpath("(//div[@class='drop_mnu_user'])[1]")).click();
		driver.quit();
	}

}
