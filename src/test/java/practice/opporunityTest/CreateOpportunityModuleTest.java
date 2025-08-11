package practice.opporunityTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Random;
import java.util.Set;

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

public class CreateOpportunityModuleTest {

	public static void main(String[] args) throws Throwable, IOException {
		
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
				Sheet sh = wb.getSheet("opportunity");
				Row row = sh.getRow(1);
				String opportunityName = row.getCell(2).toString() + randomInt;
				System.out.println(opportunityName);
				String companyName = row.getCell(3).toString()+randomInt;
				System.out.println(companyName);
				String orgName=row.getCell(4).toString()+randomInt;
				System.out.println(orgName);
				String description = row.getCell(5).toString();
				System.out.println(description);
				String switchdrop = row.getCell(6).toString();
				System.out.println(switchdrop);
				String stageSales = row.getCell(7).toString();
				String searchDrop = row.getCell(8).toString();
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
				driver.findElement(By.name("accountname")).sendKeys(orgName);			
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				

				
				//step4: navigate to opportunity module and create new opportunity
				Thread.sleep(4000);
				driver.findElement(By.xpath("//td[@class='tabUnSelected']/child::a[text()='Opportunities']")).click();
				driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();
				
				//step5: Create opportunity with all mandatory fields
				driver.findElement(By.xpath("//input[@name='potentialname']")).sendKeys(opportunityName);
				
				//step6:Add Related To Contacts by clicking on "+" img(switch window)
				Thread.sleep(2000);
				WebElement related_to = driver.findElement(By.xpath("//select[@id='related_to_type']"));
				Select s = new Select(related_to);
				s.selectByValue(switchdrop);
								
				//switch parent to child window
				
				String parentwindow = driver.getWindowHandle();
				driver.findElement(By.xpath("//input[@name='related_to_display']/following-sibling::img")).click();
				
				Set<String> childWindow = driver.getWindowHandles();
				for(String window:childWindow) {
					driver.switchTo().window(window);
					String title = driver.getTitle();
					System.out.println(title);
					if(window.contains("Administrator - Opportunities - vtiger CRM 5")) {
						//break;
					}
				}
					driver.findElement(By.name("search_text")).sendKeys(orgName);
					driver.findElement(By.name("search")).click();
							
					//handling dynamic data
					driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();

				
				Thread.sleep(2000);
				driver.switchTo().window(parentwindow);
				
				
				Thread.sleep(2000);
				//step7: Check wherther the dropdown for Sales Stage is working or not
				WebElement sales_stage = driver.findElement(By.xpath("//select[@name='sales_stage']"));
				Select s2 = new Select(sales_stage);
				s2.selectByValue(stageSales);
				
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				//step8: Go back to Opportunity - select the existing one try to edit
				Thread.sleep(2000);
				driver.findElement(By.linkText("Opportunities")).click();
				WebElement in_dropdown =driver.findElement(By.id("bas_searchfield"));
				Select s3 = new Select(in_dropdown);
				s3.selectByValue(searchDrop);
				
				driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(opportunityName);
				driver.findElement(By.xpath("//input[@name='submit']")).click();
				
				Thread.sleep(3000);
				//handling dynamic data
				Thread.sleep(2000);
				driver.findElement(By.xpath("//a[text()='"+orgName+"']")).click();
				System.out.println("able to navigate to edit page");
				//Add the Description Information and save
				driver.findElement(By.xpath("//input[@title='Edit [Alt+E]']")).click();
				driver.findElement(By.xpath("//textarea[@class='detailedViewTextBox']")).sendKeys(description);
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				
				//verification
				String actOpporunityName = driver.findElement(By.id("mouseArea_Opportunity Name")).getText();
				if(actOpporunityName.trim().equals(opportunityName)) {
					System.out.println(opportunityName +"\t" +"verified successfully");
				} else {
					System.out.println(opportunityName +"\t"+ "not verified successfully");
				}

				Thread.sleep(2000);
				//step6:logout application
				driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
				driver.findElement(By.linkText("Sign Out")).click();
				
				//step7:close driver
				driver.close();
				
	}

}

