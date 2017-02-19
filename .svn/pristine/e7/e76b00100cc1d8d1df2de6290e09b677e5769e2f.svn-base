package org.lagerhause.Model.Services;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.lagerhause.Model.Classes.Customer;
import org.lagerhause.Model.Classes.Export;
import org.lagerhause.Model.Classes.Import;
import org.lagerhause.Model.Classes.Log;
import org.lagerhause.Model.Classes.Storage;
import org.lagerhause.Model.Classes.Supplier;
import org.lagerhause.Model.Classes.User;
import org.lagerhause.Model.Classes.Ware;
import org.lagerhause.Model.Services.ServiceConstants;

import com.vaadin.ui.UI;

/**
 * Az események loggolását megvalósító osztály
 * 
 * @author Nagy Gergely
 *
 */
public class LogService {
	/**
	 * * A metódus egy új logbejegyzést készít, amely tartalamzza az időpontot,
	 * a usert(amit a jelenlegi sessionből kér le), valamint a manipulált
	 * objektumot és leírást a történésről.
	 * 
	 * @param action
	 *            A loggolandó esemény.
	 * @param obj
	 *            Az adatbáziselem, amin a változás történt.
	 * @param objectClass
	 *            Az adatbáziselem típusa
	 */
	public static void AddLogEntry(String action, Object obj, Class<?> objectClass) {

		EntityManager em = CreateService.createEntityManager();
		try {
			em.getTransaction().begin();
			Log log = new Log();
			User user = null;
			if(!objectClass.isAssignableFrom(LoginService.class))
				user = (User) UI.getCurrent().getSession().getAttribute(ServiceConstants.SESSIONUSER);
			else {
				user = (User)obj;
				log.setType(ServiceConstants.USERLOGNAME);
				log.setTypeid(user.getUserName());
			}
			log.setUser(user);
			log.setAction(action);
			log.setTimestamp(new Timestamp(new Date().getTime()));

			/**
			 * Kiválasztjuk, hogy milyen adattípusról van szó. Azért ezt
			 * használjuk a default classname kiírás helyett, mert az
			 * adatbázisba mentett adattagok közvetlenül kiíratásra kerülnek.
			 */
			if (objectClass.isAssignableFrom(Export.class)) {
				log.setType(ServiceConstants.EXPORTLOGNAME);
				Export exp = (Export) obj;
				log.setTypeid(String.valueOf(exp.getId()));
			} else if (objectClass.isAssignableFrom(Import.class)) {
				log.setType(ServiceConstants.IMPORTLOGNAME);
				Import imp = (Import) obj;
				log.setTypeid(String.valueOf(imp.getId()));
			} else if (objectClass.isAssignableFrom(User.class)) {
				log.setType(ServiceConstants.USERLOGNAME);
				User usr = (User) obj;
				log.setTypeid(usr.getUserName());
			} else if (objectClass.isAssignableFrom(Customer.class)) {
				log.setType(ServiceConstants.CUSTOMERLOGNAME);
				Customer cst = (Customer) obj;
				log.setTypeid(String.valueOf(cst.getId()));
			} else if (objectClass.isAssignableFrom(Supplier.class)) {
				log.setType(ServiceConstants.SUPPLIERLOGNAME);
				Supplier cst = (Supplier) obj;
				log.setTypeid(String.valueOf(cst.getId()));
			} else if (objectClass.isAssignableFrom(Storage.class)) {
				log.setType(ServiceConstants.STORAGELOGNAME);
				Storage cst = (Storage) obj;
				log.setTypeid(String.valueOf(cst.getId()));
			} else if (objectClass.isAssignableFrom(Ware.class)) {
				log.setType(ServiceConstants.WARELOGNAME);
				Ware wr = (Ware) obj;
				log.setTypeid(String.valueOf(wr.getId()));
			}
			em.persist(log);
			em.getTransaction().commit();
		} catch (HibernateException e) {
			if (em.getTransaction() != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			em.close();
		}

	}

}
