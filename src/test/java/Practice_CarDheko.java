import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Practice_CarDheko {
	
	public static void main(String[] args) {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://www.cardekho.com/");
		Actions a = new Actions(driver);
		a.scrollByAmount(0, 600).perform();
		List<WebElement> mG = driver.findElements(By.xpath("//a[@href='https://www.cardekho.com/mg/cyberster']|//a[@href='https://www.cardekho.com/mg/cyberster']/ancestor::div[@class='gsc_col-xs-12 holder truncate']/descendant::div[@class='price']|(//a[@href='https://www.cardekho.com/mahindra/be-6']|//a[@href='https://www.cardekho.com/mahindra/be-6']/ancestor::div[@class='gsc_col-xs-12 holder truncate']/descendant::div[@class='price'])|(//a[@href='https://www.cardekho.com/mg/windsor-ev']|//a[@href='https://www.cardekho.com/mg/windsor-ev']/ancestor::div[@class='gsc_col-xs-12 holder truncate']/descendant::div[@class='price'])"));
		System.out.println(mG.size()); 
//		for(int i=0;i<mG.size();i++) {
//			System.out.println(mG.get(i).getText());
//		}
//		
		for(WebElement mg : mG) {
			 System.out.println(mg.getText());
		 }
		driver.quit();
		 
	}
	}


