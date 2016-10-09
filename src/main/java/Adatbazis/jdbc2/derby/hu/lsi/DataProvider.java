package Adatbazis.jdbc2.derby.hu.lsi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

//AutoCloseable - be kell zárni!!
public class DataProvider implements AutoCloseable {

	private Connection connection;
	
	public DataProvider() {
		try {
			connection = DriverManager.getConnection("jdbc:derby:memory:myDB;create=true");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void createUserTable() {
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("CREATE TABLE \"User\" ("
					+ "Name VARCHAR(255),"
					+ "Password VARCHAR(255),"
					+ "Email VARCHAR(255),"
					+ "PRIMARY KEY (Email))");
			statement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void addUser(User user) {
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO \"User\"("
					+ "Name, Password, Email)"
					+ "VALUES"
					+ "(?,?,?)");
		preparedStatement.setString(1, user.getName());
		preparedStatement.setString(2,  user.getPassword());
		preparedStatement.setString(3, user.getEmail());
		preparedStatement.executeUpdate();
		preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<User> getUsers() {
		Statement statement;
		try {
			statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM \"User\"");
			List<User> users = new ArrayList<User>();
			User user = new User();
			while(resultSet.next()) {
				user.setName(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				users.add(user);
			}
			statement.close();
			resultSet.close();
			return users;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}
	
}
