package hu.lsi.adatbazis.jpa.derby;

public class App {

	public static void main(String[] args) {
		try(DataProvider dataProvider = new DataProvider()) {
			
		} catch (Exception e) {
			throw new  RuntimeException(e);
		};

	}

}
