package hu.lsi.adatbazis.jdbc2.derby;

public class App {

	public static void main(String[] args) {
		try (DataProvider dataProvider = new DataProvider()) {
			dataProvider.createUserTable();
			dataProvider.addUser(new User("user1","pwd1","user1@vmi.hu"));
			dataProvider.addUser(new User("user2","pwd2","user2@vmi.hu"));
			dataProvider.addUser(new User("user3","pwd3","user3@vmi.hu"));
			System.out.println(dataProvider.getUsers());
		}
	}

}
