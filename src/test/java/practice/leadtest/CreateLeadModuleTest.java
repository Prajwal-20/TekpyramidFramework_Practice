package practice.leadtest;

import java.io.FileInputStream;
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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class CreateLeadModuleTest {

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
						Sheet sh = wb.getSheet("lead");
						Row row = sh.getRow(1);
						String lastName = row.getCell(2).toString() + randomInt;
						System.out.println(lastName);
						String companyName = row.getCell(3).toString()+randomInt;
						System.out.println(companyName);
						String firstName=row.getCell(4).toString()+randomInt;
						System.out.println(firstName);
						String industrytype = row.getCell(7).toString();
						System.out.println(industrytype);
						String leadtype = row.getCell(8).toString();
						System.out.println(leadtype);
						String dropdown = row.getCell(9).toString();
						System.out.println(dropdown);
						String phoneNumber = row.getCell(5).toString();
						String email = row.getCell(6).toString();
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
						
						//step2: navigate to leads module and create new lead
						driver.findElement(By.linkText("Leads")).click();
						driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
						
	//step3: create the mandatory fields and select lead source and industry from dropdown
						WebElement title_prefixing = driver.findElement(By.xpath("//select[@name='salutationtype']"));
						Select s = new Select(title_prefixing);
						s.selectByIndex(1);
						
						driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);
						driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(lastName);
						driver.findElement(By.xpath("//input[@name='company']")).sendKeys(companyName);
						
						Thread.sleep(1000);
						WebElement leadSource = driver.findElement(By.xpath("//select[@name='leadsource']"));
						Select s1 = new Select(leadSource);
						s1.selectByValue(leadtype);
						
						Thread.sleep(1000);
						WebElement industry = driver.findElement(By.xpath("//select[@name='industry']"));
						Select s2 = new Select(industry);
						s2.selectByValue(industrytype);
						
						//step4:click on save button
						driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						System.out.println("New Lead Created successfully");
						
						Thread.sleep(3000);
						//step:5navigate back to lead module and search with lasName in search bar
						driver.findElement(By.linkText("Leads")).click();
						
						WebElement in_dropdown = driver.findElement(By.id("bas_searchfield"));
						Select s3 = new Select(in_dropdown);
						s3.selectByVisibleText(dropdown);	
						
						driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(lastName);
						driver.findElement(By.xpath("//input[@name='submit']")).click();
						
						Thread.sleep(3000);
						//handling dynamic data
						driver.findElement(By.xpath("//a[text()='"+lastName+"']")).click();
						System.out.println("able to navigate to edit page");
						
						driver.findElement(By.xpath("//input[@title='Edit [Alt+E]']")).click();
						driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phoneNumber);
						driver.findElement(By.id("email")).sendKeys(email);
						driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
						WebElement assignedTo_group = driver.findElement(By.xpath("//select[@name='assigned_group_id']"));
						Select s4 = new Select(assignedTo_group);
						s4.selectByVisibleText("Team Selling");
						driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						
						//verification
						String actLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
						if(actLastName.trim().equals(lastName)) {
							System.out.println(lastName +"\t" +"verified successfully");
						} else {
							System.out.println(lastName +"\t"+ "not verified successfully");
						}
						
						Thread.sleep(2000);
						//step6:logout application
						driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
						driver.findElement(By.linkText("Sign Out")).click();
						
						//step7:close driver
						driver.close();
						
						
	}

}
