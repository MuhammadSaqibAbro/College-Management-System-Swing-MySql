package college_management_swing.Java.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private DbConnection() {

	}

	public static Connection connection = null;

	public static Connection getConnection() {
		
		if(connection==null) {
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");

			try {
				connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/college_db", "root", "123456");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

		return connection;
	}
}
