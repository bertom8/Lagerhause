package org.lagerhause.Model.Services;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.lagerhause.Model.Classes.Customer;
import org.lagerhause.Model.Classes.Statistic;
/**
 * A statisztikákat kezelő osztályhoz szükséges vásárlóstatisztikák
 * @author Szatmári Koppány Gergő
 * 
 */

public class CustomerStatistics extends StatisticsService{
	private static EntityManager em;

    /**
     * A leghosszabb aktuális vásárlónév hossza
     * @return A leghosszabb aktuális vásárlónév hossza
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxCustomerNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXCUSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_NAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custnameArray = em.createQuery(q).getResultList();
			String str = custnameArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custnameArray.size();i++){
				if(custnameArray.get(i).length()>str.length()){
					str=custnameArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb aktuális vásárlónév hossza
     * @return A legrövidebb aktuális vásárlónév hossza
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minCustomerNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINCUSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_NAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custnameArray = em.createQuery(q).getResultList();
			String str = custnameArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custnameArray.size();i++){
				if(custnameArray.get(i).length()<str.length()){
					str=custnameArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos aktuális vásárlónév hossza
     * @return Az átlagos aktuális vásárlónév hossza
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgCustomerNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGCUSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_NAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custnameArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<custnameArray.size();i++){
				sum += custnameArray.get(i).length();
				counted += ServiceConstants.LONGONE;
			}
			double avg = (double)sum/counted;
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));			
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A leghosszabb országnév hossza, az aktuális vásárlók közül
     * @return A leghosszabb országnév hossza, az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxCustomerCountryNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXCUSTCONAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_COUNTRYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			String str = custArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				if(custArray.get(i).length()>str.length()){
					str=custArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb országnév hossza, az aktuális vásárlók közül
     * @return A legrövidebb országnév hossza, az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minCustomerCountryNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINCUSTCONAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_COUNTRYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			String str = custArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				if(custArray.get(i).length()<str.length()){
					str=custArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos országnévhossz az aktuális vásárlók közül
     * @return Az átlagos országnévhossz az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgCustomerCountryNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGCUSTCONAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_COUNTRYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				sum += custArray.get(i).length();
				counted += ServiceConstants.LONGONE;
			}
			double avg = (double)sum/counted;
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));			
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
   
    /**
     * A leghosszabb városnév hossza, az aktuális vásárlók közül
     * @return A leghosszabb városnév hossza, az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxCustomerCityNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXCUSTCINAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_CITYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			String str = custArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				if(custArray.get(i).length()>str.length()){
					str=custArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb városnév hossza, az aktuális vásárlók közül
     * @return A legrövidebb városnév hossza, az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minCustomerCityNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINCUSTCINAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_CITYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			String str = custArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				if(custArray.get(i).length()<str.length()){
					str=custArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos városnévhossz az aktuális vásárlók közül
     * @return Az átlagos városnévhossz az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgCustomerCityNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGCUSTCINAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_CITYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				sum += custArray.get(i).length();
				counted += ServiceConstants.LONGONE;
			}
			double avg = (double)sum/counted;
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));			
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A leghosszabb utcanév hossza, az aktuális vásárlók közül
     * @return A leghosszabb utcanév hossza, az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxCustomerStreetNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXCUSTSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_STREETPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			String str = custArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				if(custArray.get(i).length()>str.length()){
					str=custArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb utcanév hossza, az aktuális vásárlók közül
     * @return A legrövidebb utcanév hossza, az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minCustomerStreetNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINCUSTSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_STREETPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			String str = custArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				if(custArray.get(i).length()<str.length()){
					str=custArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos utcanévhossz az aktuális vásárlók közül
     * @return Az átlagos utcanévhossz az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgCustomerStreetNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGCUSTSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_STREETPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> custArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				sum += custArray.get(i).length();
				counted += ServiceConstants.LONGONE;
			}
			double avg = (double)sum/counted;
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));			
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * A legnagyobb házszámhossz az aktuális vásárlók közül
     * @return A legnagyobb házszámhossz az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxCustomerHouseNumber(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXCUSTHOUSENUMBER);
    	em = CreateService.createEntityManager();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<String> q = cb.createQuery(String.class);
    	Root<Customer> c = q.from(Customer.class);
    	q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
    	q.multiselect(c.get(ServiceConstants.C_HOUSENUMBERPROP));
    	if(em.createQuery(q).getResultList().isEmpty() != true){
    		List<String> custArray = em.createQuery(q).getResultList();
			String str = custArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				if(custArray.get(i).length()>str.length()){
					str=custArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
    	} else {
    		s.setStat(ServiceConstants.NULLEMPTY);
    	}
    	return s;
    }

    /**
     * A legkisebb házszámhossz az aktuális vásárlók közül
     * @return A legkisebb házszámhossz az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minCustomerHouseNumber(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINCUSTHOUSENUMBER);
    	em = CreateService.createEntityManager();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<String> q = cb.createQuery(String.class);
    	Root<Customer> c = q.from(Customer.class);
    	q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
    	q.multiselect(c.get(ServiceConstants.C_HOUSENUMBERPROP));
    	if(em.createQuery(q).getResultList().isEmpty() != true){
    		List<String> custArray = em.createQuery(q).getResultList();
			String str = custArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<custArray.size();i++){
				if(custArray.get(i).length()<str.length()){
					str=custArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
    	} else {
    		s.setStat(ServiceConstants.NULLEMPTY);
    	}
    	return s;
    }

    /**
     * Az átlagos házszámhossz az aktuális vásárlók közül
     * @return Az átlagos házszámhossz az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgCustomerHouseNumber(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINCUSTHOUSENUMBER);
    	em = CreateService.createEntityManager();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<String> q = cb.createQuery(String.class);
    	Root<Customer> c = q.from(Customer.class);
    	q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
    	q.multiselect(c.get(ServiceConstants.C_HOUSENUMBERPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<String> custArray = em.createQuery(q).getResultList();
			int sum = ServiceConstants.INTDEFAULT;
			for(int i = ServiceConstants.INTDEFAULT; i<custArray.size(); i++){
				sum += custArray.get(i).length();
			}
			double avg = (double)sum/custArray.size();
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A leghosszabb telefonszám hossza az aktuális vásárlók közül
     * @return A leghosszabb telefonszám hossza az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxCustomerTelNumberLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXCUSTTELNUMBERLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_PHONENUMBERPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> telnumArray = em.createQuery(q).getResultList();
			long max = telnumArray.get(ServiceConstants.INTDEFAULT).toString().length();
			for(int i = ServiceConstants.INTDEFAULT; i<telnumArray.size(); i++){
				if(telnumArray.get(i).toString().length()>max){
					max=telnumArray.get(i).toString().length();
				}
			}
			s.setStat(max+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * A legrövidebb telefonszám hossza az aktuális vásárlók közül
     * @return A legrövidebb telefonszám hossza az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minCustomerTelNumberLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINCUSTTELNUMBERLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_PHONENUMBERPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> telnumArray = em.createQuery(q).getResultList();
			long min = telnumArray.get(ServiceConstants.INTDEFAULT).toString().length();
			for(int i = ServiceConstants.INTDEFAULT; i<telnumArray.size(); i++){
				if(telnumArray.get(i).toString().length()<min){
					min=telnumArray.get(i).toString().length();
				}
			}
			s.setStat(min+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * Az átlagos telefonszámhossz az aktuális vásárlók közül
     * @return Az átlagos telefonszámhossz az aktuális vásárlók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgCustomerTelNumberLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGCUSTTELNUMBERLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Customer> c = q.from(Customer.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.C_PHONENUMBERPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){		
			List<Object> telnumArray = em.createQuery(q).getResultList();
			int sum = ServiceConstants.INTDEFAULT;
			String str = ServiceConstants.EMPTYSTR;
			for(int i = ServiceConstants.INTDEFAULT; i<telnumArray.size(); i++){
				str = telnumArray.get(i).toString();
				sum += str.length();
			}
			double avg = (double)sum/telnumArray.size();
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
}