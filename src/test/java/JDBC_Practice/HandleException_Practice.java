package JDBC_Practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class HandleException_Practice {

	public static void main(String[] args) throws SQLException {
		Connection con = null;
		try {
			Driver driverref = new Driver();
			DriverManager.registerDriver(driverref);
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/customerdb", "root", "dinga");
			Statement stat = con.createStatement();
			ResultSet resultset = stat.executeQuery("select * from customerinfo");
			while(resultset.next()) {
				
			}
		}catch(Exception e) {
			System.out.println("Exception Handled");
		} finally {
			con.close();
		}
	}

}
