package org.lagerhause.Model.Classes;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Tárolási egység adatainak tárolása
 * @author Horváth Dániel
 *
 */
@Entity
@Table(name="storage")
public class Storage {
	@Id
	@GeneratedValue
	private long id;
	@Column(name="name")
	private String storageName  = "";
	@Column(name="deleted", nullable = false, columnDefinition = "BIT default 0")
	private boolean deleted = false;
	
	/**
	 * Visszatér a Stroage id-jével
	 * @return Storage id-je
	 */
	public long getId() {
		return id;
	}
	/**
	 * Beállítja a Storage id-jét
	 * @param id Strorage id-je
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Visszatér a Stroage nevével
	 * @return Storage neve
	 */
	public String getStorageName() {
		return storageName;
	}
	/**
	 * Beállítja a Stroage nevét
	 * @param storageName
	 */
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	/**
	 * Visszadja, hogy törölve van-e a Storage
	 * @return true: törölve false: nincs törölve
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * Beállítja, hogy törölve van-e a Storage
	 * @param deleted true: törölve false: nincs törölve
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * Visszatér a Stroage nevével
	 * @return A Storage neve
	 */
	@Override
	public String toString() {
		return storageName;
	}
	

}
