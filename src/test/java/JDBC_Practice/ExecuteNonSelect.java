package JDBC_Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ExecuteNonSelect {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Driver driverref = new Driver();
		DriverManager.registerDriver(driverref);
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "dinga");
		Statement stat = con.createStatement();
		stat.executeUpdate("insert into customerinfo values('stev',45,'goa')");
		System.out.println("successful");
		con.close();
		
	}

}
