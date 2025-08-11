package practice.orgtest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationWithIndustry {

	public static void main(String[] args) throws InterruptedException, Throwable, IOException {
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
						Row row = sh.getRow(4);
						String orgName = row.getCell(2).toString()+randomInt;
						String industry = row.getCell(3).toString();
						String type = row.getCell(4).toString();
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
						
						//step4:enter all the details and create new organization and select industry
						driver.findElement(By.name("accountname")).sendKeys(orgName);
						WebElement wbsele = driver.findElement(By.name("industry"));
						Select s = new Select(wbsele);
						s.selectByVisibleText(industry);
						
						Thread.sleep(2000);
						WebElement wbsele1 = driver.findElement(By.xpath("//select[@name='accounttype']"));
						Select s1 = new Select(wbsele1);
						s1.selectByVisibleText(type);
					
						driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
						
						//step5: verify industry type and type info
						String actIndustries = driver.findElement(By.id("dtlview_Industry")).getText();
						if(actIndustries.contains(industry)) {
							System.out.println(industry + "is created");
						} else {
							System.out.println(industry + "is not created");
						}
						
						
						String actType = driver.findElement(By.id("dtlview_Type")).getText();
						if(actType.equals(type)) {
							System.out.println(type + "information is created");
						} else {
							System.out.println(type + "information is not created");
						}
						
						//close browser
						driver.quit();
						}	
				
			}