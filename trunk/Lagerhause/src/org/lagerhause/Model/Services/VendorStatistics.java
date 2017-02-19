package org.lagerhause.Model.Services;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.lagerhause.Model.Classes.Statistic;
import org.lagerhause.Model.Classes.Supplier;

/**
 * A statisztikákat kezelő osztályhoz szükséges beszállítóstatisztikák
 * @author Szatmári Koppány Gergő
 * 
 */

public class VendorStatistics extends StatisticsService{
	private static EntityManager em;

    /**
     * A leghosszabb aktuális beszállítónév hossza
     * @return A leghosszabb aktuális beszállítónév hossza
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxSupplierNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXSUPPNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.SUPPLIERNAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppnameArray = em.createQuery(q).getResultList();
			String str = suppnameArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppnameArray.size();i++){
				if(suppnameArray.get(i).length()>str.length()){
					str=suppnameArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb aktuális beszállítónév hossza
     * @return A legrövidebb aktuális beszállítónév hossza
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minSupplierNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINSUPPNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.SUPPLIERNAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppnameArray = em.createQuery(q).getResultList();
			String str = suppnameArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppnameArray.size();i++){
				if(suppnameArray.get(i).length()<str.length()){
					str=suppnameArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos aktuális beszállítónév hossza
     * @return Az átlagos aktuális beszállítónév hossza
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgSupplierNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGSUPPNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.SUPPLIERNAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppnameArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<suppnameArray.size();i++){
				sum += suppnameArray.get(i).length();
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
     * A leghosszabb országnév hossza, az aktuális beszállítók közül
     * @return A leghosszabb országnév hossza, az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxSupplierCountryNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXSUPPCONAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_COUNTRYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			String str = suppArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				if(suppArray.get(i).length()>str.length()){
					str=suppArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb országnév hossza, az aktuális beszállítók közül
     * @return A legrövidebb országnév hossza, az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minSupplierCountryNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINSUPPCONAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_COUNTRYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			String str = suppArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				if(suppArray.get(i).length()<str.length()){
					str=suppArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos országnévhossz az aktuális beszállítók közül
     * @return Az átlagos országnévhossz az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgSupplierCountryNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGSUPPCONAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_COUNTRYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				sum += suppArray.get(i).length();
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
     * A leghosszabb városnév hossza, az aktuális beszállítók közül
     * @return A leghosszabb városnév hossza, az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxSupplierCityNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXSUPPCINAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_CITYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			String str = suppArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				if(suppArray.get(i).length()>str.length()){
					str=suppArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb városnév hossza, az aktuális beszállítók közül
     * @return A legrövidebb városnév hossza, az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minSupplierCityNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINSUPPCINAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_CITYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			String str = suppArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				if(suppArray.get(i).length()<str.length()){
					str=suppArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos városnévhossz az aktuális beszállítók közül
     * @return Az átlagos városnévhossz az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgSupplierCityNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGSUPPCINAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_CITYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				sum += suppArray.get(i).length();
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
     * A leghosszabb utcanév hossza, az aktuális beszállítók közül
     * @return A leghosszabb utcanév hossza, az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxSupplierStreetNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXSUPPSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_STREETPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			String str = suppArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				if(suppArray.get(i).length()>str.length()){
					str=suppArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb utcanév hossza, az aktuális beszállítók közül
     * @return A legrövidebb utcanév hossza, az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minSupplierStreetNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINSUPPSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_STREETPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			String str = suppArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				if(suppArray.get(i).length()<str.length()){
					str=suppArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos utcanévhossz az aktuális beszállítók közül
     * @return Az átlagos utcanévhossz az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgSupplierStreetNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGSUPPSTNAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_STREETPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> suppArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				sum += suppArray.get(i).length();
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
     * A legnagyobb házszámhossz az aktuális beszállítók közül
     * @return A legnagyobb házszámhossz az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxSupplierHouseNumber(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXSUPPHOUSENUMBER);
    	em = CreateService.createEntityManager();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<String> q = cb.createQuery(String.class);
    	Root<Supplier> c = q.from(Supplier.class);
    	q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
    	q.multiselect(c.get(ServiceConstants.S_HOUSENUMBERPROP));
    	if(em.createQuery(q).getResultList().isEmpty() != true){
    		List<String> suppArray = em.createQuery(q).getResultList();
			String str = suppArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				if(suppArray.get(i).length()>str.length()){
					str=suppArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
    	} else {
    		s.setStat(ServiceConstants.NULLEMPTY);
    	}
    	return s;
    }

    /**
     * A legkisebb házszámhossz az aktuális beszállítók közül
     * @return A legkisebb házszámhossz az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minSupplierHouseNumber(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINSUPPHOUSENUMBER);
    	em = CreateService.createEntityManager();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<String> q = cb.createQuery(String.class);
    	Root<Supplier> c = q.from(Supplier.class);
    	q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
    	q.multiselect(c.get(ServiceConstants.S_HOUSENUMBERPROP));
    	if(em.createQuery(q).getResultList().isEmpty() != true){
    		List<String> suppArray = em.createQuery(q).getResultList();
			String str = suppArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<suppArray.size();i++){
				if(suppArray.get(i).length()<str.length()){
					str=suppArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
    	} else {
    		s.setStat(ServiceConstants.NULLEMPTY);
    	}
    	return s;
    }

    /**
     * Az átlagos házszámhossz az aktuális beszállítók közül
     * @return Az átlagos házszámhossz az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgSupplierHouseNumber(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINSUPPHOUSENUMBER);
    	em = CreateService.createEntityManager();
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<String> q = cb.createQuery(String.class);
    	Root<Supplier> c = q.from(Supplier.class);
    	q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
    	q.multiselect(c.get(ServiceConstants.S_HOUSENUMBERPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<String> suppArray = em.createQuery(q).getResultList();
			int sum = ServiceConstants.INTDEFAULT;
			for(int i = ServiceConstants.INTDEFAULT; i<suppArray.size(); i++){
				sum += suppArray.get(i).length();
			}
			double avg = (double)sum/suppArray.size();
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A leghosszabb telefonszám hossza az aktuális beszállítók közül
     * @return A leghosszabb telefonszám hossza az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxSupplierTelNumberLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXTELNUMBERLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_PHONENUMBERPROP));
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
     * A legrövidebb telefonszám hossza az aktuális beszállítók közül
     * @return A legrövidebb telefonszám hossza az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minSupplierTelNumberLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINTELNUMBERLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_PHONENUMBERPROP));
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
     * Az átlagos telefonszámhossz az aktuális beszállítók közül
     * @return Az átlagos telefonszámhossz az aktuális beszállítók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgSupplierTelNumberLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGTELNUMBERLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Supplier> c = q.from(Supplier.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.S_PHONENUMBERPROP));
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