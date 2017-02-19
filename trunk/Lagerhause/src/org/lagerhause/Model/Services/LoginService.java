package org.lagerhause.Model.Services;

import java.math.BigInteger;
import java.security.*;

import org.lagerhause.Model.Classes.User;

/**
 * Login Service osztály
 * @author Bereczki Tamás
 *
 */
public final class LoginService {
	
	/**
	 * Bejelentkezést/Azonosítást végző metódus.
	 * @return Visszatér boolean értékkel, hogy sikerült-e azonosítani az usert és az user változóba teszi a megtalált Usert
	 * @author Bereczki Tamás
	 */
	public static boolean signIn(final String username, final String password, User user) {
		boolean succeeded = false;
		User u = new User();
		if(UserService.findAUser(username, u)) {
			if (u.getPassword().equals(LoginService.hashing(password))) {
				User user1 = u;
				if (user1.isDeleted())
					return false;
				user.setAdmin(user1.isAdmin());
				user.setPassword(user1.getPassword());
				user.setPicture(user1.getPicture());
				user.setStatistics(user1.isStatistics());
				user.setUserName(user1.getUserName());
				user.setDeleted(user1.isDeleted());
				succeeded = true;
			}
		}
		if(succeeded)
			LogService.AddLogEntry(user.getUserName() + ServiceConstants.LOGGEDIN, user, LoginService.class);
		
		return succeeded;
	}
	
	/**
	 * Hash kód előállítása.
	 * Az User lekért adataiból és a megadott jelszavából egy MD5 hash kód készítése.
	 * @return A hash pecsét stringje
	 * @author Bereczki Tamás
	 */
	public static String hashing(final String pass) {
		
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.reset();
		m.update(pass.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);

		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		return hashtext;
	}
	
	/**
	 * Visszatér a pass saltolt MD5 hash pecsétjével
	 * @param pass
	 * @param username
	 * @return Visszatér a pass saltolt MD5 hash pecsétjével
	 *
	 * @author Bereczki Tamás
	 */
	public static String hashing(final String pass, final String username) {
		String saltedPass = pass.toUpperCase() + username.length();
		
		MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		m.reset();
		m.update(saltedPass.getBytes());
		byte[] digest = m.digest();
		BigInteger bigInt = new BigInteger(1,digest);
		String hashtext = bigInt.toString(16);

		while(hashtext.length() < 32 ){
		  hashtext = "0"+hashtext;
		}
		return hashtext;
	}
}
