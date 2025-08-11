package practice.producttest;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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

public class CreateProductsModuleTest {

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
						Sheet sh = wb.getSheet("product");
						Row row = sh.getRow(1);
						String productName = row.getCell(2).toString() + randomInt;
						System.out.println(productName);
						String vendorName = row.getCell(3).toString()+randomInt;
						System.out.println(vendorName);
						String phoneNumber = row.getCell(4).toString();
						String email = row.getCell(5).toString();
						String vendorQuickCreate = row.getCell(6).toString();
						String GLDrop = row.getCell(7).toString();
						String unitPrice = row.getCell(8).toString();
						String commissionRate = row.getCell(9).toString();
						String dropdown = row.getCell(10).toString();
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
											
						
						//Navigate to "Quick Create…" and select "New Vendor" from dropdown and  enter Vendor Name
						Thread.sleep(2000);
						WebElement quickCreate = driver.findElement(By.xpath("//select[@id='qccombo']"));
						Select s = new Select(quickCreate);
						s.selectByValue(vendorQuickCreate);
						Thread.sleep(2000);
						driver.findElement(By.xpath("//input[@name='vendorname']")).sendKeys(vendorName);
						driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(phoneNumber);
						driver.findElement(By.xpath("//input[@name='email']")).sendKeys(email);
						driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();				
						
						//step2: navigate to contacts module
						driver.findElement(By.linkText("Products")).click();
						
						//step3:click on create contact button
						driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
						
						//step4:Create Product with all mandatory fields
						driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(productName);
						
						//Try to add the start data and expiry date
						Date dateObj = new Date();
						SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
						String sales_startdate = sim.format(dateObj);
						System.out.println(sales_startdate);
						Calendar cal = sim.getCalendar();
						cal.add(Calendar.DAY_OF_MONTH, 20);
						String sales_enddate = sim.format(cal.getTime());
						System.out.println(sales_enddate);
						Thread.sleep(2000);
						driver.findElement(By.xpath("//input[@name='sales_start_date']")).sendKeys(sales_startdate);
						driver.findElement(By.xpath("//input[@name='sales_end_date']")).sendKeys(sales_enddate);
						
						WebElement GL_dropdown = driver.findElement(By.xpath("//select[@name='glacct']"));
						Select s1 = new Select(GL_dropdown);
						s1.selectByValue(GLDrop);
						
						//In "Pricing Information" try to set unit price and check all the checkboxes
						driver.findElement(By.id("unit_price")).sendKeys(unitPrice);
						driver.findElement(By.id("commissionrate")).sendKeys(commissionRate);
						driver.findElement(By.id("tax1_check")).click();
						
						//save
						driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
						
						//Again navigate "Products" module
						Thread.sleep(2000);
						driver.findElement(By.linkText("Products")).click();
						WebElement dropdown_home = driver.findElement(By.id("bas_searchfield"));
						Select s2=new Select(dropdown_home);
						s2.selectByValue(dropdown);
						
						driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(productName);
						driver.findElement(By.xpath("//input[@name='submit']")).click();
						
						Thread.sleep(4000);
						driver.findElement(By.xpath("//a[text()='"+productName+"']")).click();
						Thread.sleep(2000);
						driver.findElement(By.xpath("//input[@title='Delete [Alt+D]']")).click();
		                System.out.println("deleted successfully");
						
						Thread.sleep(2000);
						//step6:logout application
						driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']")).click();
						driver.findElement(By.linkText("Sign Out")).click();
						
						//step7:close driver
						driver.close();						
	}

}
