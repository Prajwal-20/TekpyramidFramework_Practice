package Practice_Session;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

public class XML_Practice_Vtiger {
	@Test
	public void orgTestData(XmlTest test) throws Exception, IOException {
		String BROWSER = test.getParameter("browser");
		String URL = test.getParameter("url");
		String USERNAME = test.getParameter("username");
		String PASSWORD = test.getParameter("password");
		
		//generate the random number
		Random random = new Random();
		int randomInt = random.nextInt(1000);
		
		//read testscript data from excel
		FileInputStream fis = new FileInputStream(".//src//test//resources//TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("Sheet1");
		Row row = sh.getRow(1);
		String cel = row.getCell(2).toString();
		wb.close();
		
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver(); 
		} else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver(); 
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get(URL);
		
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		Thread.sleep(2000);
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[contains(@onmouseout,'fnRemoveWindow();')]")).click();
		driver.findElement(By.name("accountname")).sendKeys(cel);
		
		driver.findElement(By.xpath("(//input[@name='button'])[1]")).click();
		
		driver.findElement(By.xpath("(//td[@class='small'])[2]")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]")).click();
		}
		
	}


