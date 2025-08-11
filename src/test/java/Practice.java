import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Practice {
	
	public static void main(String[] args) throws InterruptedException, IOException{
		
		
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(option);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://open.spotify.com/");
		driver.findElement(By.xpath("//span[text()='Log in']")).click();
		WebElement mailtxt = driver.findElement(By.xpath("//input[@placeholder='Email or username']"));
		mailtxt.sendKeys("ckavya498@gmail.com");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Continue']")).click();
		Thread.sleep(10000);		
	
		driver.findElement(By.xpath("//input[@data-testid='search-input']")).clear();
		driver.findElement(By.xpath("//input[@data-testid='search-input']")).sendKeys("shreya ghoshal");
		List<WebElement> listSongs = driver.findElements(By.xpath("(//div[@data-testid='top-sentinel'])[2]"));
		driver.findElement(By.xpath("(//*[local-name()='svg' and @class='e-91000-icon e-91000-baseline e-91000-icon--auto-mirror'])[2]")).click();
		for(WebElement songs:listSongs) {
			//System.out.println("Songs by Shreya Ghoshal");
			System.out.println(songs.getText());
		
			Thread.sleep(3000);
			driver.findElement(By.xpath("//div[text()='Saiyaara Reprise - Female']")).click();
			driver.findElement(By.xpath("//span[@class='e-91000-baseline e-91000-overflow-wrap-anywhere e-91000-button-primary__inner encore-bright-accent-set e-91000-button-icon-only--large']")).click();
				
		//write data to excel
			FileInputStream fis = new FileInputStream(".//src//test//resources//TestData1.xlsx");
			Workbook wb = WorkbookFactory.create(fis);
			Sheet sh = wb.getSheet("Songs");
			int rowCount = sh.getLastRowNum();
			Row row = sh.createRow(rowCount+1);
			row.createCell(0).setCellValue("Tum Hi Ho");
			row.createCell(1).setCellValue("Ve Kamleya");
			row.createCell(2).setCellValue("Phir Bhi Tumko Chaahunga");
			fis.close();
			FileOutputStream fos = new FileOutputStream(".//src//test//resources//TestData1.xlsx");
			wb.write(fos);
			wb.close();
			fos.close();
			System.out.println("Executed");
			driver.quit();
	}
}
}
