package org.lagerhause.Model.Classes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Beszállító adatainak tárolása.
 *
 * @author Horváth Dániel
 *
 */
@Entity
@Table(name="supplier")
public class Supplier {
	@Id
	@GeneratedValue
	@Column(name="id", nullable = false)
	private long id;
	@Column(name="name", nullable = false)
	private String name = "";
	@Column(name="country", nullable = false)
	private String country = "";
	@Column(name="city", nullable = false)
	private String city = "";
	@Column(name="street", nullable = false)
	private String street = "";
	@Column(name="house", nullable = false)
	private String house = "";
	@Column(name="phone_number", nullable = false)
	private String phoneNumber  = "";
	@Column(name="deleted", nullable = false, columnDefinition = "BIT default 0")
	private boolean deleted = false;
	/**
	 * Visszatér a beszállító id-jével
	 * @return A beszállító id-je
	 */
	public long getId() {
		return id;
	}
	/**
	 * Beállítja a beszállító id-jét
	 * @param id A beszállító id-je
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Visszatér az beszállító nevével
	 * @return A beszállító neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Beállítja a beszállító nevét
	 * @param name A beszállító neve
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Visszatér a beszállító címében szereplő országgal
	 * @return A beszállító címében szereplő ország
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Beállítja a beszállító címében szereplő országot
	 * @param country A beszállító címében szereplő ország
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * Visszatér a beszállító címében szereplő várossal
	 * @return A beszállító címében szereplő város
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Beállítja a beszállító címében szereplő várost
	 * @param city A beszállító címében szereplő város
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Visszatér a beszállító címében szereplő utcával
	 * @return A beszállító címében szereplő város
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * Beállítja a beszállító címében szereplő utcát
	 * @param street A beszállító címében szereplő utca
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * Visszatér a beszállító címében szereplő házszámmal
	 * @return A beszállító címében szereplő házszám
	 */
	public String getHouse() {
		return house;
	}
	/**
	 * Beállítja a beszállító címében szereplő házszámot
	 * @param house A beszállító címében szereplő házszám
	 */
	public void setHouse(String house) {
		this.house = house;
	}
	/**
	 * Visszatér a beszállító telefonszámával
	 * @return A beszállító telefonszáma
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Beállítja a beszállító telefonszámát
	 * @param phoneNumber A beszállító telefonszáma
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Visszaadja, hogy a beszállító törölve van-e
	 * @return true: törölve false: nincs törölve
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * Beállítja, hofy a beszállító törölve van-e
	 * @param deleted true: törölve false: nincs törölve
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * Visszatér a beszállító nevével
	 * @return a beszállító neve
	 */
	@Override
	public String toString() {
		return name;
	}


}
