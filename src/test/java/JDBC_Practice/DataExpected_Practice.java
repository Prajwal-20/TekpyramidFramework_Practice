package JDBC_Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;

public class DataExpected_Practice {
	
	@Test
	public void projectCheckTest() throws Exception
	{
		String expectedResult="Bangalore";
		boolean flag=false;
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "dinga");
		Statement stat = con.createStatement();
		ResultSet resultset = stat.executeQuery("select * from customerinfo");
		while(resultset.next()) {
			String actProjectName = resultset.getString(4);
			if(expectedResult.equals(actProjectName)) {
				flag=true;
				System.out.println(expectedResult+"is avaliable==PASS");
			}
		}
		if(flag==false) {
			Assert.fail();
			System.out.println("expected result not found");
		}
		con.close();
	}
}
