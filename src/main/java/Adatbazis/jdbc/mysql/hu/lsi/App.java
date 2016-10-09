package Adatbazis.jdbc.mysql.hu.lsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class App {
	
	private static Connection connectionMysql;
	private static Statement s;
	private static PreparedStatement p;
	
    //JDBC
	public static void main( String[] args ) throws ClassNotFoundException, SQLException {
    	
		try {
			
			//Connect to Mysql
			connectionMysql = DriverManager.getConnection(
					DataBaseConf.URL+DataBaseConf.DBNAME+DataBaseConf.TZCONFIG, 
					DataBaseConf.USERNAME,
					DataBaseConf.PWD);
			Statement s = connectionMysql.createStatement();
			
			//Connect and create a derby memory DB
			Connection connectionDerby = DriverManager.getConnection("jdbc:derby:memory:myDB;create=true");
			Statement derbystatement = connectionDerby.createStatement();
			derbystatement.executeUpdate("CREATE TABLE MyUser( Id INTEGER, Name VARCHAR(255), "
					+ "Password VARCHAR(255), "
					+ "Primary key (Id))");
					
			//Nem ajánlott
			String defsql = "SELECT c.cid FROM cache as c where c.cid = 'schema';";
			ResultSet defaultresult = s.executeQuery(defsql);
			while (defaultresult.next()) {
				System.out.println(defaultresult.getString("cid"));
			}

			//Ajánlott
	    	String prepsql = "SELECT c.cid FROM cache as c where c.cid = ?;";
	    	p = connectionMysql.prepareStatement(prepsql);
			p.setString(1, "schema");
		    ResultSet prepresultset = p.executeQuery();//executeUpdate()
	    	while (prepresultset.next()) {
	    		System.out.println(prepresultset.getString("cid"));
//	    		System.out.println(prepresultset.getString(1));
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {

		}
    }
}
