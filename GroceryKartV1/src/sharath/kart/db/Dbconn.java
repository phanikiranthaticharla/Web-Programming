package sharath.kart.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import sharath.kart.constants.Constants;

//singleton class to get db connection
public class Dbconn {

	//get Database connection
	public static Connection getConnection() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(Constants.db_url,Constants.db_user, Constants.db_pass);
		return con;

	}
	
	//close DB connection
	public static void closeConnection(Connection conn) {

		try {
			conn.close();
		} catch (SQLException e) {
			
		}
	}

}
