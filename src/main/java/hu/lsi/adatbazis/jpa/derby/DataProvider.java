package hu.lsi.adatbazis.jpa.derby;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import hu.lsi.adatbazis.jpa.derby.model.User;

public class DataProvider implements AutoCloseable {

	private EntityManagerFactory emf;
	
	public DataProvider() {
		//adatbazisomPU XML-bol
		emf = Persistence.createEntityManagerFactory("adatbazisomPU");
	}
	
	private void save(Object obj) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(obj);
		em.getTransaction().commit();
		em.close();
	}
	
	public void addUser(User user) {
		save(user);
	}
	
	public void removeUser(User user) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User managedUser = em.find(User.class, user.getEmail());
		em.remove(managedUser);
		em.getTransaction().commit();
	}

	@Override
	public void close() throws Exception {
		if (emf != null) {
			emf.close();
		}
	}

	public List<User> getUsers() {
		EntityManager em = emf.createEntityManager();
		TypedQuery<User> userQuery = em.createQuery("SELECT u FROM User u", User.class);
		List<User> userList = userQuery.getResultList();
		em.close();
		return userList;
	}
	
}
