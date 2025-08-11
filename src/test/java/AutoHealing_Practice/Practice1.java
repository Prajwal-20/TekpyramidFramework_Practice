package AutoHealing_Practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class Practice1 {

	@FindBy(name="user_name")
	private WebElement userEdit;
	
	@FindBy(name="user_password")
	private WebElement pwdEdit;
	
	@FindAll({@FindBy(id="submitButton"),@FindBy(xpath = "//input[@value='Login']")})
	private WebElement loginBtn;
	
	@Test
	public void sampleTest() {
		WebDriver driver = new ChromeDriver();
		driver.get("http://localhost:8888/");
		
		Practice1 pf = PageFactory.initElements(driver,Practice1.class);
		pf.userEdit.sendKeys("admin");
		pf.pwdEdit.sendKeys("admin");
		pf.loginBtn.click();
	}
}
