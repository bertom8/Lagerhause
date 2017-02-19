package org.lagerhause.Model.Services;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.lagerhause.Model.Classes.Statistic;
import org.lagerhause.Model.Classes.Storage;
import org.lagerhause.Model.Classes.Ware;

/**
 * A statisztikákat kezelő osztályhoz szükséges termék- és tárolóstatisztikák
 * @author Szatmári Koppány Gergő
 * 
 */

public class WareStatistics extends StatisticsService{
	private static EntityManager em;
	private static int availableItems = ServiceConstants.INTDEFAULT;
	private static int lostItems = ServiceConstants.INTDEFAULT;	
	
    /**
     * A nem törölt termékek mennyisége
     * @return A nem törölt termékek mennyisége
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic sumWareItemQuantity(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.SUMWAREQUANTITY);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(cb.sum(c.get(ServiceConstants.QUANTITYPROP)));
		Object result = em.createQuery(q).getSingleResult();
		if(result != null){
			s.setStat(result.toString());
	    	availableItems = (int) result;
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;    	
    }
    
    /**
     * A kárbaveszett termékek darabszáma
     * @return A kárbaveszett termékek darabszáma
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic sumAllWareItemQuantity(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.LOSTWAREQUANTITY);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTONE));
		q.multiselect(cb.sum(c.get(ServiceConstants.QUANTITYPROP)));
		Object result = em.createQuery(q).getSingleResult();
		if(result != null){
			s.setStat(result.toString());
	    	lostItems = (int) result;
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;    	
    }
    
    /**
     * A raktár hatékonysága
     * @return A raktár hatákonysága százalékban mérve
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic wareEfficiency(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.WAREEFFICIENCY);
    	if(availableItems != ServiceConstants.INTDEFAULT){
    		double d = ((double) availableItems / (availableItems + lostItems))*ServiceConstants.INTPERCENTCONV;
    		NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
    		s.setStat(form.format(d)+ServiceConstants.PERCENTTXT);
    	} else {
    		s.setStat(ServiceConstants.NULLEMPTY);
    	}
    	return s;
    }
    
    /**
     * A leghosszabb sorozatszám hossza, raktáron lévő termékek közül
     * @return A leghosszabb sorozatszám hossza, raktáron lévő termékek közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxSerialLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.LONGESTSERIAL);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARESERIALPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> serialArray = em.createQuery(q).getResultList();
			long max = serialArray.get(ServiceConstants.INTDEFAULT).toString().length();
			for(int i = ServiceConstants.INTDEFAULT; i<serialArray.size(); i++){
				if(serialArray.get(i).toString().length()>max){
					max=serialArray.get(i).toString().length();
				}
			}
			s.setStat(max+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * A legrövidebb sorozatszám hossza, raktáron lévő termékek közül
     * @return A legrövidebb sorozatszám hossza, raktáron lévő termékek közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minSerialLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.SHORTESTSERIAL);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARESERIALPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> serialArray = em.createQuery(q).getResultList();
			long min = serialArray.get(ServiceConstants.INTDEFAULT).toString().length();
			for(int i = ServiceConstants.INTDEFAULT; i<serialArray.size(); i++){
				if(serialArray.get(i).toString().length()<min){
					min=serialArray.get(i).toString().length();
				}
			}
			s.setStat(min+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * Átlagos sorozatszámhossz
     * @return Átlagos sorozatszámhossz
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgSerialLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGSERIAL);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARESERIALPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){		
			List<Object> serialArray = em.createQuery(q).getResultList();
			int sum = ServiceConstants.INTDEFAULT;
			String str = ServiceConstants.EMPTYSTR;
			for(int i = ServiceConstants.INTDEFAULT; i<serialArray.size(); i++){
				str = serialArray.get(i).toString();
				sum += str.length();
			}
			double avg = (double)sum/serialArray.size();
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
        
    /**
     * A raktáron lévő legtöbb egyfajta termék mennyisége
     * @return A raktáron lévő legtöbb egyfajta termék mennyisége
     * @author Szatmári Koppány Gergő
     */
	protected static Statistic maxWareQuantity(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXWAREQUANTITY);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> q = cb.createQuery(Integer.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WAREQUANTITYPROP)).orderBy(cb.desc(c.get(ServiceConstants.WAREQUANTITYPROP)));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			int max = em.createQuery(q).getResultList().get(ServiceConstants.INTDEFAULT).intValue();
			s.setStat(max+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A raktáron lévő legkevesebb egyfajta termék mennyisége
     * @return A raktáron lévő legkevesebb egyfajta termék mennyisége
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minWareQuantity(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINWAREQUANTITY);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Integer> q = cb.createQuery(Integer.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WAREQUANTITYPROP)).orderBy(cb.asc(c.get(ServiceConstants.WAREQUANTITYPROP)));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			int min = em.createQuery(q).getResultList().get(ServiceConstants.INTDEFAULT).intValue();
			s.setStat(min+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A raktáron lévő átlagos egyfajta termékmennyiség
     * @return A raktáron lévő átlagos egyfajta termékmennyiség
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgWareQuantity(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGWAREQUANTITY);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(cb.count(c));
		Object result = em.createQuery(q).getSingleResult();
		if(result != null && availableItems !=ServiceConstants.INTDEFAULT){
			long counted = (long) em.createQuery(q).getSingleResult();
			double avg = (double)availableItems/counted;
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A használt tárolók közül a leggyakrabban használt gyakorisága
     * @return A használt tárolók közül a leggyakrabban használt gyakorisága
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxStorageUsage(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXSTORAGEUSAGE);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARESTORAGEPROP), cb.count(c.get(ServiceConstants.WARESTORAGEPROP)));
		q.groupBy(c.get(ServiceConstants.WARESTORAGEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object[]> storageArray = em.createQuery(q).getResultList();
			long max = ServiceConstants.LONGDEFAULT;
			for(Object[] value : storageArray) {
				if(((long) value[ServiceConstants.INTONE])>max) {
					max = (long) value[ServiceConstants.INTONE];
				}
			}		
			s.setStat(max+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);		
		}
		return s;
    }    
    
    /**
     * A használt tárolók közül a legkevésbé használt gyakorisága
     * @return A használt tárolók közül a legkevésbé használt gyakorisága
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minStorageUsage(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINSTORAGEUSAGE);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARESTORAGEPROP), cb.count(c.get(ServiceConstants.WARESTORAGEPROP)));
		q.groupBy(c.get(ServiceConstants.WARESTORAGEPROP));		
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object[]> storageArray = em.createQuery(q).getResultList();
			long min = ServiceConstants.LONGDEFAULT, first = ServiceConstants.LONGONE;
			for(Object[] value : storageArray) {
				if(first==ServiceConstants.LONGONE){
					min = (long) value[ServiceConstants.INTONE];
					first = ServiceConstants.LONGDEFAULT;
				}
				if(((long) value[ServiceConstants.INTONE])<min) {
					min = (long) value[ServiceConstants.INTONE];
				}
			}		
			s.setStat(min+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);	
		}
		return s;
    }   
    
    /**
     * A használt tárolók közül az átlagos tárolóhasználat gyakorisága
     * @return A használt tárolók közül az átlagos tárolóhasználat gyakorisága
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgStorageUsage(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGSTORAGEUSAGE);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARESTORAGEPROP), cb.count(c.get(ServiceConstants.WARESTORAGEPROP)));
		q.groupBy(c.get(ServiceConstants.WARESTORAGEPROP));		
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object[]> storageArray = em.createQuery(q).getResultList();		
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;
			for(Object[] value : storageArray) {
				sum += (long) value[ServiceConstants.INTONE];
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
     * A leghosszabb kategórianév hossza, a raktáron lévő termékek közül
     * @return A leghosszabb kategórianév hossza, a raktáron lévő termékek közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxCategoryLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXCATEGORYLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARECATEGORYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> categoryArray = em.createQuery(q).getResultList();
			String str = categoryArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<categoryArray.size();i++){
				if(categoryArray.get(i).length()>str.length()){
					str=categoryArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb kategórianév hossza, a raktáron lévő termékek közül
     * @return A legrövidebb kategórianév hossza, a raktáron lévő termékek közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minCategoryLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINCATEGORYLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARECATEGORYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> categoryArray = em.createQuery(q).getResultList();
			String str = categoryArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<categoryArray.size();i++){
				if(categoryArray.get(i).length()<str.length()){
					str=categoryArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos kategórianévhossza, a raktáron lévő termékek közül
     * @return Az átlagos kategórianévhossza, a raktáron lévő termékek közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgCategoryLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGCATEGORYLENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARECATEGORYPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> categoryArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<categoryArray.size();i++){
				sum += categoryArray.get(i).length();
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
     * A leghosszabb terméknév hossza, a raktáron lévő termékek közül
     * @return A leghosszabb terméknév hossza, a raktáron lévő termékek közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxWareNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXWARENAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARENAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> warenameArray = em.createQuery(q).getResultList();
			String str = warenameArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<warenameArray.size();i++){
				if(warenameArray.get(i).length()>str.length()){
					str=warenameArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legrövidebb terméknév hossza, a raktáron lévő termékek közül
     * @return A legrövidebb terméknév hossza, a raktáron lévő termékek közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minWareNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINWARENAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARENAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> warenameArray = em.createQuery(q).getResultList();
			String str = warenameArray.get(ServiceConstants.INTDEFAULT);
			for(int i=ServiceConstants.INTDEFAULT;i<warenameArray.size();i++){
				if(warenameArray.get(i).length()<str.length()){
					str=warenameArray.get(i);
				}
			}
			s.setStat(str.length()+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * Az átlagos terméknévhossz, a raktáron lévő termékek közül
     * @return Az átlagos terméknévhossz, a raktáron lévő termékek közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgWareNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGWARENAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> q = cb.createQuery(String.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.WARENAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){					
			List<String> warenameArray = em.createQuery(q).getResultList();
			long sum = ServiceConstants.LONGDEFAULT, counted = ServiceConstants.LONGDEFAULT;			
			for(int i=ServiceConstants.INTDEFAULT;i<warenameArray.size();i++){
				sum += warenameArray.get(i).length();
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
     * A leghosszabb tárolónév hossza az aktuális tárolók közül
     * @return A leghosszabb tárolónév hossza az aktuális tárolók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic maxStorageNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MAXSTORAGENAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Storage> c = q.from(Storage.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.STORAGENAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> storeArray = em.createQuery(q).getResultList();
			long max = storeArray.get(ServiceConstants.INTDEFAULT).toString().length();
			for(int i = ServiceConstants.INTDEFAULT; i<storeArray.size(); i++){
				if(storeArray.get(i).toString().length()>max){
					max=storeArray.get(i).toString().length();
				}
			}
			s.setStat(max+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * A legrövidebb tárolónév hossza az aktuális tárolók közül
     * @return A legrövidebb tárolónév hossza az aktuális tárolók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic minStorageNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.MINSTORAGENAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Storage> c = q.from(Storage.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.STORAGENAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> storeArray = em.createQuery(q).getResultList();
			long min = storeArray.get(ServiceConstants.INTDEFAULT).toString().length();
			for(int i = ServiceConstants.INTDEFAULT; i<storeArray.size(); i++){
				if(storeArray.get(i).toString().length()<min){
					min=storeArray.get(i).toString().length();
				}
			}
			s.setStat(min+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * Az átlagos tárolónévhossz az aktuális tárolók közül
     * @return Az átlagos tárolónévhossz az aktuális tárolók közül
     * @author Szatmári Koppány Gergő
     */
    protected static Statistic avgStorageNameLength(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.AVGSTORAGENAMELENGTH);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Storage> c = q.from(Storage.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c.get(ServiceConstants.STORAGENAMEPROP));
		if(em.createQuery(q).getResultList().isEmpty() != true){		
			List<Object> storeArray = em.createQuery(q).getResultList();
			int sum = ServiceConstants.INTDEFAULT;
			String str = ServiceConstants.EMPTYSTR;
			for(int i = ServiceConstants.INTDEFAULT; i<storeArray.size(); i++){
				str = storeArray.get(i).toString();
				sum += str.length();
			}
			double avg = (double)sum/storeArray.size();
			NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
			s.setStat(form.format(avg));
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
}