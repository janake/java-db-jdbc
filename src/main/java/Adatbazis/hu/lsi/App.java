package Adatbazis.hu.lsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class App {
	
    private static Connection connection;
    private static Statement s;

	public static void main( String[] args ) throws ClassNotFoundException, SQLException {
    	
		try {
			connection = DriverManager.getConnection(
					DataBaseConf.URL+DataBaseConf.DBNAME+DataBaseConf.TZCONFIG, 
					DataBaseConf.USERNAME,
					DataBaseConf.PWD);
	    	s = connection.createStatement();
	    	ResultSet result = s.executeQuery("SELECT * FROM cache;");
	    	while (result.next()) {
	    		System.out.println(result.getString("cid"));
	    	}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			s.close();
			connection.close();			
		}
    }
}
