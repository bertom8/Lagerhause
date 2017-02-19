/**
 * 
 */
package org.lagerhause.Model.Services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Az osztály segítségével hozzható létre minden az adatbáziseléréshez/kezeléshez szükséges objektum
 * @author Bereczki Tamás
 *
 */
public final class CreateService {
	/**
	 * Visszaad egy em EntityManager objektumot
	 * @return Visszaad egy em EntityManager objektumot
	 *
	 * @author Bereczki Tamás
	 */
	public static <T> EntityManager createEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("org.lagerhause");
		EntityManager em = factory.createEntityManager();

		return em;
	}
}
