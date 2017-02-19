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
 * Behozott Ware-ek jegyzése, Supplierhez rendelése
 *
 * @author Horváth Dániel
 *
 */
@Entity
@Table(name="import")
public class Import {
	@Id
	@GeneratedValue
	private long id;
	@Column(name="time")
	private Timestamp timestamp = new Timestamp(new Date().getTime());
	@Column(name="success", columnDefinition ="BIT default 0")
	private boolean success;
	@ManyToOne
	private Ware ware = null;
	@ManyToOne
	private Supplier supplier = null;
	@Column(name="quantity")
	private int quantity;
	@Column(name="deleted", nullable = false, columnDefinition = "BIT default 0")
	private boolean deleted = false;
	
	/**
	 * Visszatér az Import id-jével
	 * @return Import id-je
	 */
	public long getId() {
		return id;
	}
	/**
	 * Beállítja az Import id-jét
	 * @param id Az import id-je
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Visszatér az Import hozzáadásának időpontjával.
	 * @return Az Import hozzáadásának időpontja
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * Az Import hozzáadási időpontjának hozzáadaása
	 * @param Az Import hozzáadásának időpontja
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	/**
	 * Az Import sikerességével tér vissza
	 * @return true: sikeres, false: sikertelen
	 */
	public boolean isSuccess() {
		return success;
	}
	/**
	 * Az import sikerességét állítja be. 
	 * @param success true: sikeres, false: sikertelen
	 */
		public void setSuccess(boolean success) {
		this.success = success;
	}
	/**
	 * Visszaadja az importált Ware-t
	 * @return Az importált Ware
	 */
	public Ware getWare() {
		return ware;
	}
	/**
	 * Beállítja az importált Ware-t
	 * @param ware Az importált Ware
	 */
	public void setWare(Ware ware) {
		this.ware = ware;
	}
	/**
	 * Visszatér az Import beszállítójával
	 * @return Az import beszállítója
	 */
		public Supplier getSupplier() {
		return supplier;
	}
	/**
	 * Beállítja az Import beszállítóját
	 * @param supplier Az Import beszállítója
	 */
	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	/**
	 * Visszatér az importált Ware-ek darabszámával
	 * @return Az importált Ware-ek darabszáma
	 */
	public int getQuantity(){
		return quantity;
	}
	/**
	 * Beállítja az importált Ware-ek darabszámát
	 * @param quantity Az importált Ware-ek darabszáma
	 */
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	/**
	 * Visszadaja, hogy az Import törölve van-e
	 * @return true: törölve, false: nincs törölve
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * Beállítja, hogy az Import törölve van-e
	 * @param deleted true: törölve false: nincs törölve
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * Visszatér az import id-jével
	 * @return Az import id-je
	 */
	@Override
	public String toString() {
		return String.valueOf(id);
	}


}
