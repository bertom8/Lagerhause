package org.lagerhause.Model.Classes;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *  Ügyfél adatinak tárolása.
 *
 * @author Horváth Dániel
 *
 */

@Entity
@Table(name="customer")
public class Customer {

	@Id
	@GeneratedValue
	private long id;
	@Column(name="name")
	private String name = "";
	@Column(name="country")
	private String country = "";
	@Column(name="city")
	private String city = "";
	@Column(name="street")
	private String street = "";
	@Column(name="house")
	private String house = "";
	@Column(name="phone_number")
	private String phoneNumber = "";
	@Column(name="deleted", nullable = false, columnDefinition = "BIT default 0")
	private boolean deleted = false;
	/**
	 * Visszatér az ügyfél id-jével
	 * @return Az ügyfél id-je
	 */
	public long getId() {
		return id;
	}
	/**
	 * Beállítja az ügyfél id-jét
	 * @param id Az ügyfél id-je
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Visszatér az ügyfél nevével
	 * @return Az ügyfél neve
	 */
	public String getName() {
		return name;
	}
	/**
	 * Beállítja az ügyfél nevét
	 * @param name Az ügyfél neve
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Visszatér ügyfél címben lévő országgal
	 * @return Az ügyfél címben lévő ország
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * Beállítja az ügyfél címében lévő országot
	 * @param country Az ügyfél címben lévő ország
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * Visszatér az ügyfél címben lévő várossal
	 * @return Az ügyfél címben lévő város
	 */
	public String getCity() {
		return city;
	}
	/**
	 * Beállítja az ügyfél címben lévő várost
	 * @param city Az ügyfél címben lévő város
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * Visszatér az ügyfél címében lvő utcával
	 * @return Az ügyfél címében lévő utca
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * Beállítja az ügyfél címében lévő utcát
	 * @param street Az ügyfél címében lévő uca
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * Visszatér az ügyfél címében lévő házszámmal
	 * @return Az ügyfél címében lévő házszám
	 */
	public String getHouse() {
		return house;
	}
	/**
	 * Beállítja az ügyfél címében lévő házszámot
	 * @param house Az ügyfél címében lévő házszám
	 */
	public void setHouse(String house) {
		this.house = house;
	}
	/**
	 * Visszatér az ügyfél telefonszámát
	 * @return Az ügyfél telefonszáma
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * Beállítja az ügyfél telefonszámát
	 * @param phoneNumber Az ügyfél telefonszáma
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * Visszaadja, hogy az ügyfél törölve van-e
	 * @return true: törölve false: nincs törölve
	 */
	public boolean isDeleted() {
		return deleted;
	}
	/**
	 * Beállítja, hogy az ügyfél törölve van-e
	 * @param deleted true: törölve false: nincs törölve
	 */
	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	/**
	 * Visszatér az ügyfél nevével
	 * @return Az ügyfél neve
	 */
	@Override
	public String toString() {
		return name;
	}
	
}
