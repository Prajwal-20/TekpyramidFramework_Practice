package practice.ticketstest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateTroubleTicketsModuleTest {

	public static void main(String[] args) throws Exception {
		
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
						Sheet sh = wb.getSheet("tickets");
						Row row = sh.getRow(1);
						String title = row.getCell(2).toString() + randomInt;
						System.out.println(title);
						String companyName = row.getCell(3).toString()+randomInt;
						System.out.println(companyName);
						String orgDrop = row.getCell(4).toString();
						System.out.println(orgDrop);
						String ticketStatus = row.getCell(5).toString();
						System.out.println(ticketStatus);
						wb.close();
						
						WebDriver driver = null;
						if(BROWSER.equals("chrome")) {
							driver = new ChromeDriver(); 
						} else if(BROWSER.equals("firefox")) {
							driver = new FirefoxDriver(); 
						} else {
							driver=new ChromeDriver();
						}
						
						driver.manage().window().maximize();
						driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
						driver.get(URL);
						
						//step1:login to app
						
						driver.findElement(By.name("user_name")).sendKeys(USERNAME);
						driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
						driver.findElement(By.id("submitButton")).click();
						
                         //step2: navigate to Organization module		
						driver.findElement(By.linkText("Organizations")).click();
						
						//step3: click on create Organization button
						driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
						
						//step4:enter all the details and create new organization
						driver.findElement(By.name("accountname")).sendKeys(companyName);			
						driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
						
						//step5: navigate to tickets module
						Thread.sleep(4000);
						driver.findElement(By.xpath("(//a[text()='Trouble Tickets'])[1]")).click();
						
						//step6:click on create ticket button
						driver.findElement(By.xpath("//img[@title='Create Ticket...']")).click();
						
						//step7:enter mandatory fields
						driver.findElement(By.xpath("//textarea[@name='ticket_title']")).sendKeys(title);
						
						WebElement org_dropdown = driver.findElement(By.xpath("//select[@name='parent_type']"));
						Select s = new Select(org_dropdown);
						s.selectByValue(orgDrop);
						
						//switch to parent window
						String parentWindow = driver.getWindowHandle();
						
						driver.findElement(By.xpath("//input[@name='parent_name']/following-sibling::img")).click();
						//switch to child window
						Set<String> childWindow = driver.getWindowHandles();
						for(String window:childWindow) {
							driver.switchTo().window(window);
							String value_title = driver.getTitle();
							System.out.println(value_title);
							if(window.contains("Administrator - Trouble Tickets")) {
								
							}
						}
						driver.findElement(By.name("search_text")).sendKeys(companyName);
						driver.findElement(By.name("search")).click();
						

						//handling dynamic data
						driver.findElement(By.xpath("//a[text()='"+companyName+"']")).click();
						
								
					driver.switchTo().window(parentWindow);
					Thread.sleep(2000);
					
					WebElement status =driver.findElement(By.xpath("//select[@name='ticketstatus']"));
					Select s2=new Select(status);
					s2.selectByValue(ticketStatus);
					
					driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
					
					//verification
					String actTitleName = driver.findElement(By.id("mouseArea_Title")).getText();
					if(actTitleName.trim().equals(title)) {
						System.out.println(title +"\t"+"is created successfully");
					} else {
						System.out.println(title +"\t"+"is not created successfully");
					}
					

					Thread.sleep(2000);
					//step6:logout application
					driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
					driver.findElement(By.linkText("Sign Out")).click();
					
					//step7:close driver
					driver.close();
					
	}

}
