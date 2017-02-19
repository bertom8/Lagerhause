/**
 * 
 */
package org.lagerhause.Model.Services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.lagerhause.Model.Classes.User;

/**
 * User kezelési osztály
 * @author Bereczki Tamás
 *
 */
public class UserService {
	private static EntityManager em;
	
	/**
	 * Megkeres egy adott usert és visszaadja az User paraméterben, ha létezik
	 * @param username
	 * @param u
	 * @return Megkeres egy adott usert és visszaadja az User paraméterben, ha létezik
	 *
	 * @author Bereczki Tamás
	 */
	public static boolean findAUser(final String username, User u) {
		boolean succeeded = false;
		em = CreateService.createEntityManager();
		User user = new User();
		user = em.find(User.class, username);
		if (user != null) {
			u.setAdmin(user.isAdmin());
			u.setPassword(user.getPassword());
			u.setPicture(user.getPicture());
			u.setStatistics(user.isStatistics());
			u.setUserName(user.getUserName());
			u.setDeleted(user.isDeleted());
		}
		if (u.getUserName() != null) 
			succeeded = true;
		em.close();
		return succeeded;
	}
	
	/**
	 * Felhasználói jelszóváltást lehetővé tevő függvény
	 * @param username
	 * @param newPass
	 * @return Visszaad egy boolean értéket, hogy sikerült-e megváltoztatni és letárolni az új jelszót
	 */
	public static boolean changePassword(String username, String newPass) {
		boolean succeeded = false;
		
		em = CreateService.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			User u = null;
			u = em.find(User.class, username);
			if (u != null)
				u.setPassword(LoginService.hashing(newPass));
			em.merge(u);
			tx.commit();
			succeeded = true;
		}
		catch(HibernateException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			em.close();
		}
		
		return succeeded;
	}
}
