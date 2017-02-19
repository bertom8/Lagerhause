package org.lagerhause.Model.Classes;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Az eseményeket tároló JavaBean
 * 
 * @author Nagy Gergely
 *
 */
@Entity
@Table(name = "log")
public class Log {
	@Id
	@GeneratedValue
	private long id;
	@Column(name = "action")
	private String action = "";
	@ManyToOne
	private User user = null;
	@Column(name = "typeid")
	private String typeid;
	@Column(name = "type")
	private String type = "";
	@Column(name = "timestamp")
	private Timestamp timestamp = null;
	@Column(name = "deleted")
	private boolean deleted = false;

	/**
	 * Visszatér a Log sorszámával
	 * 
	 * @return a log sorszáma
	 */
	public long getId() {
		return id;
	}

	/**
	 * Beállítja a Log sorszámát. Csak hibernate számára fenntartott metódus.
	 * 
	 * @param id
	 *            A Log sorszáma.
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Visszatér a Log által tárolt akció Stringjével.
	 * 
	 * @return Az akció Stringje.
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Beállítja a Log által tárolt akciót.
	 * 
	 * @param action
	 *            Az akció Stringje.
	 */
	public void setAction(String action) {
		this.action = action;
	}

	/**
	 * Visszaadja a Logból az akciót végrehajtó Usert.
	 * 
	 * @return Az akciót végrehajtó User.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Beállítja a Logban az akciót végrehajtó User-t.
	 * 
	 * @param user
	 *            Az akciót végrehajtó User.
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Visszatér az elem sorszámával, amin az akciót végrehajtották.
	 * 
	 * @return Az elem sorszáma amin az akciót végrehajtották. (Ha user, akkor a
	 *         username)
	 */
	public String getTypeid() {
		return typeid;
	}

	/**
	 * Beállítja az elem sorszámát, amin az akciót végrehajtották.
	 * 
	 * @param typeid
	 *            Az elem sorszáma, amin az akciót végrehajtották. (Ha user,
	 *            akkor a username)
	 */
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	/**
	 * Visszaadja a Logból az elem típusát, amin az akciót végrehajtották.
	 * 
	 * @return Az elem típusa String-ként, amin az akciót végrehajtották.
	 */
	public String getType() {
		return type;
	}

	/**
	 * Beállítja a Logban az elem típusát, amin az akciót végrehajtották.
	 * 
	 * @param typeid
	 *            Az elem típusa String-ként, amin az akciót végrehajtották.
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Visszatér a Logból az időponttal, amikor az akciót végrehajtották.
	 * 
	 * @return Az időpont, amikor az akciót végrehajtották.
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * Beállítja a Logban az időpontot, amikor az akciót végrehajtották.
	 * 
	 * @param timestamp
	 *            Az időpont amikor az akciót égrehajották.
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * Visszaadja, hogy a Log törölve van-e. Mivel nem törlünk Logot, így ez
	 * mindig false, csak egységesség miatt került be.
	 * 
	 * @return Törölve van-e a log.
	 */
	public boolean isDeleted() {
		return deleted;
	}

}
