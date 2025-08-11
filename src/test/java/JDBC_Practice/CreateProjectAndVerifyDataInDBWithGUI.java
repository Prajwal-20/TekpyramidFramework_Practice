package JDBC_Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.jdbc.Driver;

public class CreateProjectAndVerifyDataInDBWithGUI {

	public static void main(String[] args) throws Exception {
		
		//create project in GUI
		
		String projectName="RamPro_01";
		WebDriver driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("http://49.249.28.218:8091/");
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
		
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();
		driver.findElement(By.name("projectName")).sendKeys(projectName);
		driver.findElement(By.name("createdBy")).sendKeys("Ram");
		
		Thread.sleep(3000);
		WebElement ele = driver.findElement(By.xpath("(//select[@name='status'])[2]"));
		
		Select sel = new Select(ele);
		//ele.click();
		sel.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();
		
		//verify project in DB
		boolean flag=false;
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection con = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root@%", "root");
		Statement stat = con.createStatement();
		ResultSet resultset = stat.executeQuery("select * from project");
		while(resultset.next()) {
			String actProjectName = resultset.getString(4);
			if(projectName.equals(actProjectName)) {
				flag=true;
				System.out.println(projectName+ "is avaliable in db");
			}
		}
		if(flag==false) {
			System.out.println(projectName+"is not avaliable in db");
		}
		con.close();
	}

}
