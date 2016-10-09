package hu.lsi.adatbazis.jpa.derby;

import hu.lsi.adatbazis.jpa.derby.model.User;

public class App {

	public static void main(String[] args) throws Exception {
		try(DataProvider dataProvider = new DataProvider()) {
			User user = new User("user1","passwd","mail");
			dataProvider.addUser(user);
			System.out.println(dataProvider.getUsers());
			dataProvider.removeUser(user);
			System.out.println(user);
			System.out.println(dataProvider.getUsers());
		}
	}

}
