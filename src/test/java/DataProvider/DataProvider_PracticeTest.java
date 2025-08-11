package DataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvider_PracticeTest {
	
	@Test(dataProvider = "getData")
	public void createContactTest(String firstName, String lastName) {
		System.out.println("Firstname: "+firstName +" LastName: "+ lastName );
	}
	
	@DataProvider
	public Object[][] getData() {
		Object[][] objArr = new Object[3][2];
		objArr[0][0]= "Ram";
		objArr[0][1]="H";
		objArr[1][0]="Sam";
		objArr[1][1]="R";
		objArr[2][0]="John";
		objArr[2][1]="Smith";
		return objArr;
	}
}
