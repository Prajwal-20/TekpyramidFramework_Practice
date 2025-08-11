import org.testng.annotations.Test;

public class DataProviderTest{
	
	@Test(dataProviderClass = DataProvider_Provider.class,dataProvider = "getData")
	public void createContactName(String firstName, String lastName) {
		System.out.println(firstName + "/t" + lastName);
	}

}
