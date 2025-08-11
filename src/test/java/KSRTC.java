import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class KSRTC {

	public static void main(String[] args) throws EncryptedDocumentException, IOException {
		// TODO Auto-generated method stub
		
		FileInputStream fis = new FileInputStream(".//src//test//resources//TestData1.xlsx");
		Workbook wb = WorkbookFactory.create(fis);	
		 Sheet sh = wb.getSheet("KSRTC");
		
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://ksrtc.in/");
		WebElement from = driver.findElement(By.xpath("//span[text()='Select Departure City']"));
		from.click();
		driver.findElement(By.xpath("(//div[@class='chosen-drop']/descendant::input[@placeholder='Search Your City Name'])[1]")).sendKeys("Benga");
		driver.findElement(By.xpath("(//ul[@class='chosen-results']/descendant::em)[1]")).click();
		
		driver.findElement(By.xpath("//span[text()='Select Destination City']")).click();
		driver.findElement(By.xpath("(//div[@class='chosen-drop'])[2]/descendant::input")).sendKeys("pavagada");
		driver.findElement(By.xpath("//em[text()='Pavagada']")).click();
		driver.findElement(By.id("departDate")).click();
		driver.findElement(By.xpath("//tbody/descendant::a[text()='7']")).click();
		
		driver.findElement(By.id("submitSearch")).click();
		driver.findElement(By.xpath("//span[text()='0930BNGKLYD']")).getText();
		List<WebElement> bus1details = driver.findElements(By.xpath("(//span[@class='service--route sc']/ancestor::div[@class='listinng-block-left'])[1]/descendant::div[@class='triptimebold darkText1']"));	
		List<WebElement> bus2details = driver.findElements(By.xpath("(//span[@class='service--route sc']/ancestor::div[@class='listinng-block-left'])[2]/descendant::div[@class='triptimebold darkText1']"));	
		// Create header row
		Row header = sh.createRow(0);
		header.createCell(0).setCellValue("Bus 1 Times");
		header.createCell(1).setCellValue("Bus 2 Times");
		// First loop for bus1details
		for (int i = 0; i < bus1details.size(); i++) {
		    Row row = sh.getRow(i + 1);
		    if (row == null) {
		        row = sh.createRow(i + 1);
		    }
		    row.createCell(0).setCellValue(bus1details.get(i).getText());
		    System.out.println(bus1details.get(i).getText());
		}
		// Second loop for bus2details
		for (int i = 0; i < bus2details.size(); i++) {
		    Row row = sh.getRow(i + 1);
		    if (row == null) {
		        row = sh.createRow(i + 1);
		    }
		    row.createCell(1).setCellValue(bus2details.get(i).getText());
		    System.out.println(bus2details.get(i).getText());
		}
		FileOutputStream fos = new FileOutputStream(".//src//test//resources//TestData1.xlsx");
		wb.write(fos);
		wb.close();
	}

}
