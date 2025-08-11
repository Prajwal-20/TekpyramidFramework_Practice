package practice.contacttest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class CreateContactWithSupportDateTest {

	public static void main(String[] args) throws FileNotFoundException, Exception {
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
				Row row = sh.getRow(4);
				String lastName = row.getCell(2).toString()+randomInt;
				
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
				//step2: navigate to contacts module
				driver.findElement(By.linkText("Contacts")).click();
				
				//step3:click on create contact button
				driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
				
				//create current system date
				Date dateObj = new Date();
				SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
				String startDate = sim.format(dateObj);
				Calendar cal = sim.getCalendar();
				cal.add(Calendar.DAY_OF_MONTH, 30);
				String endDate = sim.format(cal.getTime());
				
				//step4: enter all the details and create new contact
				driver.findElement(By.name("lastname")).sendKeys(lastName);
				driver.findElement(By.name("support_start_date")).clear();
				driver.findElement(By.name("support_start_date")).sendKeys(startDate);
				Thread.sleep(2000);
				driver.findElement(By.name("support_end_date")).clear();
				driver.findElement(By.name("support_end_date")).sendKeys(endDate);
				driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
				
				
				//step5: verify start date and end date
				String actStartDate = driver.findElement(By.id("dtlview_Support Start Date")).getText();
				if(actStartDate.equals(startDate)) {
					System.out.println(startDate + "\t" +"is created successfully");
				} else {
					System.out.println(startDate + "\t" +"is not created successfully");
				}
				
				String actEndDate = driver.findElement(By.id("dtlview_Support End Date")).getText();
				if(actEndDate.equals(endDate)) {
					System.out.println(endDate + "is created successfully");
				} else {
					System.out.println(endDate +"is not created successfully");
				}
				
				//step6: close driver
				driver.close();
}

}