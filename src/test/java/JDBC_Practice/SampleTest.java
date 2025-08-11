package JDBC_Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class SampleTest {

	public static void main(String[] args) throws Exception {
		
		//step-1 load/register the database driver
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		
		//step2: connect to database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "dinga");
		System.out.println("Connection successfull");
		
		//step3: create sql statement
		Statement stat = con.createStatement();
		
		//step4: execute select query and execute
		ResultSet resultset = stat.executeQuery("select * from customerinfo");
		System.out.println(resultset);
		
		//step5: close database
		con.close();
		System.out.println("connection closed");
	}

}
