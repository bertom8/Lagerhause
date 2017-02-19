package org.lagerhause.Model.Classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.lagerhause.Model.Services.LoginService;

/**
 * A felhasználó login és jogosultság adatait tárolja
 *
 * @author Horváth Dániel
 *
 */
@Entity
@Table(name="user")
public class User {
	@Id
	@Column(name="user_name")
	private String userName  = "";
	@Column(name="password")
	private String password = LoginService.hashing("$Default");
	@Column(name="admin", nullable = false, columnDefinition = "BIT default 0")
	private boolean admin = false;
	@Column(name="statistics", nullable = false, columnDefinition = "BIT default 0")
	private boolean statistics = false;
	@Column( name = "picture" )
	@Lob
	private byte[] picture = null;
	@Column(name="deleted", nullable = false, columnDefinition = "BIT default 0")
	private boolean deleted = false;

	/**
	 * Visszatér a User nevével.
	 * @return User neve
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Beállítja a User nevét.
	 * @param userName User neve
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Visszatér a User jelszó hashével.
	 * @return password hash
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Beállítja a User jelszó hashét.
	 * @param password jelszó hash.
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Visszaadja, hogy a User admin-e?
	 * @return True: admin, false: nem admin
	 */
	public boolean isAdmin() {
		return admin;
	}
	/**
	 * Beállítja, a User admin tulajdonságát.
	 * @param admin true: admin user, false: nem admin
	 */
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	/**
	 * Visszadja, hogy a user hozzáfér-e a statisztikákhoz?
	 * @return true: hozzáfér, false: nem fér hozzá
	 */
	public boolean isStatistics() {
		return statistics;
	}
	/**
	 * Beállítja, hogy a user hozzáfér-e a statisztikákhoz?
	 * @param statistics hozzáfér, false: nem fér hozzá
	 */
	public void setStatistics(boolean statistics) {
		this.statistics = statistics;
	}
	/**
	 * Visszaadja a User feltöltött képét
	 * @return User képe
	 */
	public byte[] getPicture() {
		return picture;
	}
	/**
	 * Beállítja, a User képét
	 * @param picture User képe
	 */
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	/**
	 * Visszatér a deleted értékével
	 * @return deleted
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * Beállítja a deleted mező értékét.
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * VIsszatér a User nevével
	 * @return Username
	 */
	@Override
	public String toString() {
		return userName;
	}
	
}
