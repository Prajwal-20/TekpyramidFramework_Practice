package practice.contacttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithOrgTest {

	public static void main(String[] args) throws InterruptedException, Exception {
		
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
						Sheet sh = wb.getSheet("contact");
						Row row = sh.getRow(7);
						String orgName = row.getCell(2).toString() + randomInt;
						System.out.println(orgName);
						String contactLastName = row.getCell(3).toString()+randomInt;		
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
						
						//step: verify header msg expected result
						String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
						if(headerInfo.contains(orgName)) {
							System.out.println(orgName + "is created");
						} else {
							System.out.println(orgName + "is not created");
						}
						
						//step5: navigate to contacts module
						driver.findElement(By.linkText("Contacts")).click();
						
						//step6:click on create contact button
						driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
						
						
						//step7: enter all the details and create new contact
						driver.findElement(By.name("lastname")).sendKeys(contactLastName);
						Thread.sleep(2000);
						
						//switch parent to child window
						
						String parentwindow = driver.getWindowHandle();
						driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();
						
						Set<String> childWindow = driver.getWindowHandles();
						for(String window:childWindow) {
							driver.switchTo().window(window);
							String title = driver.getTitle();
							System.out.println(title);
							if(window.contains("Administrator - Contacts - vtiger CRM 5")) {
								break;
							}
						}
							driver.findElement(By.name("search_text")).sendKeys(orgName);
							driver.findElement(By.name("search")).click();
							
							
							//handling dynamic data
							driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
							
						
						
						driver.switchTo().window(parentwindow);
						Thread.sleep(2000);

						
						driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						
						
						//step5: verify header
						String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
						if(actLastName.trim().equals(contactLastName)) {
							System.out.println(contactLastName +"\t"+"is created successfully");
						} else {
							System.out.println(contactLastName +"\t"+"is not created successfully");
						}
						
						String actOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
						if(actOrgName.trim().equals(orgName)) {
							System.out.println(orgName +"\t"+"is created successfully");
						} else {
							System.out.println(orgName +"\t"+"is not created successfully");
						}
						
						//step6: close driver
						driver.close();
		}

		}

