package Adatbazis.jdbc.mysql.hu.lsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	
    private static Connection connection;
    private static Statement s;
    private static java.sql.PreparedStatement p;

    //JDBC
	public static void main( String[] args ) throws ClassNotFoundException, SQLException {
    	
		try {
			
			//Connect to Mysql
			connection = DriverManager.getConnection(
					DataBaseConf.URL+DataBaseConf.DBNAME+DataBaseConf.TZCONFIG, 
					DataBaseConf.USERNAME,
					DataBaseConf.PWD);
			s = connection.createStatement();
			
			//Nem ajánlott
			String defsql = "SELECT c.cid FROM cache as c where c.cid = 'schema';";
			ResultSet defaultresult = s.executeQuery(defsql);
			while (defaultresult.next()) {
				System.out.println(defaultresult.getString("cid"));
			}

			//Ajánlott
	    	String prepsql = "SELECT c.cid FROM cache as c where c.cid = ?;";
	    	p = connection.prepareStatement(prepsql);
			p.setString(1, "schema");
		    ResultSet prepresultset = p.executeQuery();
	    	while (prepresultset.next()) {
	    		System.out.println(prepresultset.getString("cid"));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			s.close();
			p.close();			
		}
    }
}
