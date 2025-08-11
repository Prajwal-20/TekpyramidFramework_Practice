package JDBC_Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteSelectQuery {

	public static void main(String[] args) throws Exception {
		
		//step1: Login/register to DB
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		
		//step2: connect to database
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "dinga");
		
		//step3:create sql statement
		Statement stat = con.createStatement();
		
		//step4: execute select query and get result
		ResultSet resultset = stat.executeQuery("select * from customerinfo");
//		resultset.next();
//		System.out.println(resultset.getString(1));
		
		while(resultset.next()) {
			System.out.println(resultset.getString(1)+ " " + resultset.getInt(2)+ " " +resultset.getString(3));
		}
		//step5: close connection
		con.close();
	}

}
