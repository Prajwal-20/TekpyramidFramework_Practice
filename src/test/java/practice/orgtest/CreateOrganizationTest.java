package practice.orgtest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateOrganizationTest {

	public static void main(String[] args) throws IOException, Exception {
		
		//read data from property file
		FileInputStream fis = new FileInputStream(".//src//test//resources//CommonData.properties");
		Properties p = new Properties();
		p.load(fis);
		
		String BROWSER = p.getProperty("browser");
		String URL = p.getProperty("url");
		String USERNAME = p.getProperty("username");
		String PASSWORD = p.getProperty("password");
		
		//random number
		Random r = new Random();
		int randomInt = r.nextInt(1000);
		
		//read testscript data from excel
				FileInputStream fis1 = new FileInputStream(".//src//test//resources//TestData1.xlsx");
				Workbook wb = WorkbookFactory.create(fis1);
				Sheet sh = wb.getSheet("org");
				Row row = sh.getRow(1);
				String orgName = row.getCell(2).toString()+randomInt;
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
				
				//step1:login to app
				
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();
				
				Thread.sleep(2000);
				//step2: navigate to Organization module
				
				driver.findElement(By.linkText("Organizations")).click();
				
				//step3: click on create Organization button
				driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
				
				//step4:enter all the details and create new organization
				driver.findElement(By.name("accountname")).sendKeys(orgName);			
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//step5: verify header msg expected result
				String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
				if(headerInfo.contains(orgName)) {
					System.out.println(orgName + "is created");
				} else {
					System.out.println(orgName + "is not created");
				}
				
				//verify header orgName info expected result
				String actOrgName = driver.findElement(By.id("dtlview_Organization Name")).getText();
				if(actOrgName.equals(orgName)) {
					System.out.println(orgName + "information is created");
				} else {
					System.out.println(orgName + "information is not created");
				}
				
				//close browser
				driver.quit();
				}	
		
	}


