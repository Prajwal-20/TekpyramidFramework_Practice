package Practice_Session;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Flipkart {

	public static void main(String[] args) throws Throwable, IOException {
		FileInputStream fis = new FileInputStream(".//src//test//resources//CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url1");
		
		FileInputStream fis1 = new FileInputStream(".//src//test//resources//excel.xlsx");
		Workbook wb = WorkbookFactory.create(fis1);
		Sheet sh = wb.createSheet("Flipkart5");
		
		
		WebDriver driver = null;
		if(BROWSER.contains("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.contains("edge")) {
			driver = new EdgeDriver();
		} else if(BROWSER.contains("firefox")) {
			driver = new FirefoxDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		driver.findElement(By.name("q")).sendKeys("samsung s24 ultra");
		
		driver.findElement(By.xpath("//button[@title='Search for Products, Brands and More']")).click();
//		List<WebElement> productName1 = driver.findElements(By.xpath("//div[contains(text(),'Samsung')]"));
//		for(int i=1;i<productName1.size();i++) {
//				for(WebElement product:productName1) {
//			String product1 = product.getText();
//			System.out.println(product1);
//			Row row = sh.createRow(i);
//			Cell cel = row.createCell(0);
//			cel.setCellValue(product1);	
//				}
//		}
//			Thread.sleep(2000);
			List<WebElement> price = driver.findElements(By.xpath("//div[contains(@class,'Nx9bqj')]"));

		for(int j=1;j<=price.size();j++) {
		//System.out.println(price.size());
		for(WebElement productPrice:price) {
			String productPrice1 = productPrice.getText();
			System.out.println(productPrice);
			Row row1 = sh.createRow(j);
			Cell cel1 = row1.createCell(1);
			cel1.setCellValue(productPrice1);

		}
		}
	FileOutputStream fos = new FileOutputStream(".//src//test//resources//excel.xlsx");
	wb.write(fos);
	wb.close();
	System.out.println("Executed");
}
}
	

	
