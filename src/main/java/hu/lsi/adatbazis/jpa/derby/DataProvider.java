package hu.lsi.adatbazis.jpa.derby;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataProvider implements AutoCloseable {

	private EntityManagerFactory emf;
	
	public DataProvider() {
		//adatbazisomPU XML-bol
		emf = Persistence.createEntityManagerFactory("adatbazisomPU");
	}

	@Override
	public void close() throws Exception {
		if (emf != null) {
			emf.close();
		}
	}
	
}
