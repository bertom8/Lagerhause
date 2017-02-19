package org.lagerhause.Model.Classes;


import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 *
 * Kiadott Ware-ek jegyzése, Customerhez jegyzése
 *
 * @author Horváth Dániel
 *
 */

@Entity
@Table(name="export")
public class Export {
	@Id
	@GeneratedValue
	private long id;
	@Column(name="time")
	private Timestamp timestamp = new Timestamp(new Date().getTime());
	@Column(name="success",nullable = false, columnDefinition = "BIT default 0")
	private boolean success;
	@ManyToOne
	private Ware ware = null;
	@ManyToOne
	private Customer customer = null;
	@Column(name="quantity")
	private int quantity;
	@Column(name="deleted", nullable = false, columnDefinition = "BIT default 0")
	private boolean deleted = false;

	/**
	 * Visszatér az export id-jével
	 * @return Export id-je
	 */
	public long getId() {
		return id;
	}

	/**
	 * Beállítja az export id-jét
	 * @param id Az export id-je
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Visszatér az export hozzáadásának időpontjával
	 * @return Az export hozzáadásának időpontja
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * Beállítja az export hozzáadásának időpontját
	 * @param timestamp Az export hozzáadásának időpontja
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Visszatér az  export hozzáadásának sikerességével
	 * @return Az export hozzáadásának sikeressége
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * Beállítja az export hozzáadásának sikerességét
	 * @param success Az export hozzáadásának sikeresssége
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * Visszatér az exportált Ware-rel
	 * @return Az exportált Ware
	 */
	public Ware getWare() {
		return ware;
	}
	/**
	 * Beállítja az exportált Ware-t
	 * @param ware Az exportált Ware
	 */
	public void setWare(Ware ware) {
		this.ware = ware;
	}
	/**
	 * Visszatér az ügyféllel akinek az export címezve lett
	 * @return Az export ügyfele
	 */
	public Customer getCustomer() {
		return customer;
	}
	/**
	 * Beállítja az ügyfelet, akinek címezve lett az export
	 * @param customer Az export ügyfele
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	/**
	 * Visszatér az exportált Ware darabszámával
	 * @return Az exportált Ware darabszáma
	 */
	public int getQuantity(){
		return quantity;
	}
	/**
	 * Beállytja az exportált Ware darabszámát
	 * @param quantity
	 */
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	/**
	 * Visszaadja, hogy az export törölve van-e
	 * @return true: törölve false: nincs törölve
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * Beállítja, hogy az export törölve van-e
	 * @param deleted törölve false: nincs törölve
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	/**
	 * Visszatér az export Id-jével
	 * @return Az export id-je
	 */
	@Override
	public String toString() {
		return String.valueOf(id);
	}
	

}
