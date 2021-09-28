package project01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
	
	static final String user= "reimbruse_db";
	static final String password= "proj1";
//	static final String user= "admin";
//	static final String password= "12345678";
	static final String db_url="jdbc:oracle:thin:@database-1.cncgozgp4gc8.us-east-2.rds.amazonaws.com:1521:ORCL";
	
	public static Connection connector(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn=DriverManager.getConnection(db_url, user, password);
			return conn;
		}catch(ClassNotFoundException e){
			System.out.println("ERROR: unable to load driver class");
			return null;
		}catch(SQLException e){
			System.out.println("ERROR: SQL exception in Connector");
			e.printStackTrace();
			return null;
		}
	}
}
