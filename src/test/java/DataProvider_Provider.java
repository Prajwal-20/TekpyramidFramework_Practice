import org.testng.annotations.DataProvider;

public class DataProvider_Provider {

	@DataProvider
	public Object[][] getData(){
		Object[][] objArr=new Object[2][2];
		objArr[0][0]="Ram";
		objArr[0][1]="H";
		objArr[1][0]="Sam";
		objArr[1][1]="P";
		return objArr;
	}
	
}
