package org.lagerhause.Model.Classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * A beérkező és kimenő áruk adatait tárolja
 * @author Horváth Dániel
 *
 */
@Entity
@Table(name="ware")
public class Ware {
	@Id
	@GeneratedValue
	private long id = -1;
	@Column(name="name")
	private String name  = "";
	@Column(name="category")
	private String category = "";
	@ManyToOne
	private Storage storage = null;
	@Column(name="serial")
	private String serial = "";
	@Column(name="quantity")
	private int quantity = 0;
	@Column(name="deleted", nullable = false, columnDefinition = "BIT default 0")
	private boolean deleted = false;

	/**
	 * Visszatér a Ware id-jével.
	 * @return Ware id-je
	 */
	public long getId() {
		return id;
	}
	/**
	 * Beállítja a Ware id-jét.
	 * @param id a beállítani kívánt id
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Visszatér a Ware nevével.
	 * @return Ware neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Beállítja a Ware nevét.
	 * @param name a beállítani kívánt név
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Visszatér a Ware kategóriájával.
	 * @return Ware kategóriája
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * Beállítja a Ware kategóriáját.
	 * @param category a beállítani kívánt kategória
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * Visszatér a Ware tárolási helyével. (Storage)
	 * @return A Ware tárolási helyét tároló osztály
	 */
	public Storage getStorage() {
		return storage;
	}
	/**
	 * Beállítja, hogy a Ware melyik Storage-ben van.
	 * @param storage A Storage ahol a Ware van.
	 */
	public void setStorage(Storage storage) {
		this.storage = storage;
	}
	/**
	 * Visszatér a Ware serial számával.
	 * @return A Ware serial számával
	 */
	public String getSerial() {
		return serial;
	}
	/**
	 * Beállítja a Ware serial számát.
	 * @param serial A beállítani kívánt serial szám
	 */
	public void setSerial(String serial) {
		this.serial = serial;
	}
	/**
	 * Visszatér a Ware darabszámával.
	 * @return Ware darabszáma
	 */
	public int getQuantity(){
		return quantity;
	}
	/**
	 * Beállítja a Ware darabszámát.
	 * @param quantity Ware darabszáma
	 */
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	/**
	 * Megmondja, hogy az adott ware törölve van-e.
	 * @return törölve van-e a Ware
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * Beállítja, hogy törölve van-e a Ware.
	 * @param deleted true:törölve, false:nem
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 *Visszatér a Ware nevével.
	 *@return a Ware neve 
	 */
	@Override
	public String toString() {
		return name;
	}
	

}
