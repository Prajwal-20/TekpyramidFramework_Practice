package Practice_Session;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NestedFrame {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://demoapps.qspiders.com/ui/frames/nestedWithMultiple?sublist=3");
		driver.findElement(By.xpath("//aside[@class='bg-white rounded-md h-[100%] w-[100%] border border-orange-200 shadow shadow-orange-300 p-5 overflow-y-scroll myscrollbar ']"));
		driver.switchTo().frame(0);
		

	}

}
