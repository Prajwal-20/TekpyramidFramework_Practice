package DataProvider;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvideAmazonTest {
	
	@Test(dataProvider = "getData")
	public void fetchProductPrice(String brandName,String productName) {
		WebDriver driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://www.amazon.in/");
		
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys(brandName);
		driver.findElement(By.id("nav-search-submit-button")).click();
		
		String x="//h2[@aria-label='"+productName+"']/ancestor::div[@class='a-section a-spacing-small a-spacing-top-small']/descendant::span[@class='a-price-whole']";
		String price=driver.findElement(By.xpath(x)).getText();
		System.out.println(price);
		
		driver.quit();
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr=new Object[3][2];
		objArr[0][0]="iphone";
		objArr[0][1]="Apple iPhone 15 (128 GB) - Blue";
		objArr[1][0]="iphone";
		objArr[1][1]="Apple iPhone 15 (128 GB) - Black";
		objArr[2][0]="iphone";
		objArr[2][1]="Apple iPhone 13 (128GB) - Midnight";
		return objArr;
	}

}
