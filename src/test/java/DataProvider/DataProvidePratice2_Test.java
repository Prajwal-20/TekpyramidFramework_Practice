package DataProvider;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvidePratice2_Test {

	@Test(dataProvider="getData")
	public void createContactTest(String firstName,String lastName,long phoneNumer) {
		System.out.println(firstName + "\t" + lastName +  "\t==>" + phoneNumer);
	}
	
	@DataProvider
	public Object[][] getData(){
		Object[][] objArr = new Object[3][3];
		objArr[0][0]="Shree";
		objArr[0][1]="Ram";
		objArr[0][2]=9890867653l;
		objArr[1][0]="Sam";
		objArr[1][1]="P";
		objArr[1][2]=24546788787l;
		objArr[2][0]="John";
		objArr[2][1]="smith";
		objArr[2][2]=79892379739l;
		return objArr;
		
	}
}
