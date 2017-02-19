package org.lagerhause.Model.Services;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.lagerhause.Model.Classes.Export;
import org.lagerhause.Model.Classes.Import;
import org.lagerhause.Model.Classes.Statistic;
import org.lagerhause.Model.Classes.Ware;

import com.vaadin.data.util.BeanItemContainer;
/**
 * A statisztikákat kezelő osztály
 * @author Nagy Gergely
 * @author Horváth Dániel
 * @author Bereczki Tamás
 * @author Szatmári Koppány Gergő
 * 
 */
public class StatisticsService {
	private static EntityManager em;
	private static int impSuccess = ServiceConstants.INTDEFAULT;
	private static int expSuccess = ServiceConstants.INTDEFAULT;
	private static int impUnSuccess = ServiceConstants.INTDEFAULT;
	private static int expUnSuccess = ServiceConstants.INTDEFAULT;
	
	/**
	 * A statisztikákat tartalamzó BeanItemContainer létrehozása és feltöltése adattal
	 * @return Statisztikákat tartalamzó BeanItemContainer
	 */
	public static BeanItemContainer<Statistic> ServeStatistics() {
		BeanItemContainer<Statistic> container = new BeanItemContainer<Statistic>(Statistic.class);
		for(int i = ServiceConstants.INTDEFAULT; i < statistics.length; i++) {
			container.addBean(statistics[i].get());
		}
		return container;
	}
	
	/**
	 * Interfész, hogy meg adhassuk a ServeStatistics() metódusnak a statisztikai metódusokat
	 * @author Bereczki Tamás
	 *
	 */
	interface getStatistic {
        Statistic get();
    }

	/**
	 * A statisztikai metódusokat tartalmazó tömb
	 */
    private static getStatistic[] statistics = new getStatistic[] {
        new getStatistic() { public Statistic get() { return hanyTermekVan(); } },
        new getStatistic() { public Statistic get() { return sumImportMaxQuantity(); } },
        new getStatistic() { public Statistic get() { return sumImportSuccess(); } },
        new getStatistic() { public Statistic get() { return sumExportMaxQuantity(); } },
        new getStatistic() { public Statistic get() { return sumExportSuccess(); } },
        new getStatistic() { public Statistic get() { return sumImportUnSuccess(); } },
        new getStatistic() { public Statistic get() { return sumExportUnSuccess(); } },
        new getStatistic() { public Statistic get() { return impSuccessRate(); } },
        new getStatistic() { public Statistic get() { return expSuccessRate(); } },
        
        new getStatistic() { public Statistic get() { return WareStatistics.sumWareItemQuantity(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.sumAllWareItemQuantity(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.wareEfficiency(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.maxSerialLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.minSerialLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.avgSerialLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.maxWareQuantity(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.minWareQuantity(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.avgWareQuantity(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.maxStorageUsage(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.minStorageUsage(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.avgStorageUsage(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.maxCategoryLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.minCategoryLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.avgCategoryLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.maxWareNameLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.minWareNameLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.avgWareNameLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.maxStorageNameLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.minStorageNameLength(); } },
        new getStatistic() { public Statistic get() { return WareStatistics.avgStorageNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.maxSupplierNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.minSupplierNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.avgSupplierNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.maxSupplierCountryNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.minSupplierCountryNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.avgSupplierCountryNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.maxSupplierCityNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.minSupplierCityNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.avgSupplierCityNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.maxSupplierStreetNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.minSupplierStreetNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.avgSupplierStreetNameLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.maxSupplierHouseNumber(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.minSupplierHouseNumber(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.avgSupplierHouseNumber(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.maxSupplierTelNumberLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.minSupplierTelNumberLength(); } },
        new getStatistic() { public Statistic get() { return VendorStatistics.avgSupplierTelNumberLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.maxCustomerNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.minCustomerNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.avgCustomerNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.maxCustomerCountryNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.minCustomerCountryNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.avgCustomerCountryNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.maxCustomerCityNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.minCustomerCityNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.avgCustomerCityNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.maxCustomerStreetNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.minCustomerStreetNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.avgCustomerStreetNameLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.maxCustomerHouseNumber(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.minCustomerHouseNumber(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.avgCustomerHouseNumber(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.maxCustomerTelNumberLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.minCustomerTelNumberLength(); } },
        new getStatistic() { public Statistic get() { return CustomerStatistics.avgCustomerTelNumberLength(); } },
        new getStatistic() { public Statistic get() { return firstImportedWare(); } },
        new getStatistic() { public Statistic get() { return lastImportedWare(); } },
        new getStatistic() { public Statistic get() { return firstLastImportedDelay(); } },
        new getStatistic() { public Statistic get() { return firstExportedWare(); } },
        new getStatistic() { public Statistic get() { return lastExportedWare(); } },
        new getStatistic() { public Statistic get() { return firstLastExportedDelay(); } }
    };
    
    /**
     * Hány darab, nem törölt, termékünk van jelenleg
     * @return Visszaadja a termékszámot
     * @author Bereczki Tamás
     */
    private static Statistic hanyTermekVan() {
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.HOWMANYWARES);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Long> q = cb.createQuery(Long.class);
		Root<Ware> c = q.from(Ware.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.select(cb.count(c));
    	s.setStat(em.createQuery(q).getSingleResult().toString());
    	return s;
    }
    /**
     * Darabra összesen legtöbbet importált termék
     * @return A legtöbbet beimportált termék, és ennek a mennyisége
     * @author Horváth Dániel
     */
    private static Statistic sumImportMaxQuantity() {
    	Statistic s = new Statistic();
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		Root<Import> c = q.from(Import.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.SUCCESSPROP), ServiceConstants.INTONE));
		q.multiselect(c.get(ServiceConstants.WAREPROP), c.get(ServiceConstants.QUANTITYPROP));
		q.multiselect(c.get(ServiceConstants.WAREPROP), cb.sum(c.get(ServiceConstants.QUANTITYPROP)));
		q.groupBy(c.get(ServiceConstants.WAREPROP));
		List<Object[]> valueArray = em.createQuery( q ).getResultList();
		String st=ServiceConstants.EMPTYSTR;
		long i = ServiceConstants.LONGDEFAULT, max=ServiceConstants.LONGDEFAULT;
		for(Object[] values : valueArray) {
			i = (long) values[ServiceConstants.INTONE];
			if(i>max) {
				max = i;
				st= values[ServiceConstants.INTDEFAULT].toString();
			}
		}
		
		s.setName(ServiceConstants.SUMIMPORTMAX+st);
		s.setStat(max+ServiceConstants.EMPTYSTR);
		return s;
    }
    /**
     * Az összes sikeresen importált termék
     * @return Az összes sikeresen importált termék száma
     * @author Horváth Dániel
     */
    private static Statistic sumImportSuccess() {
    	Statistic s = new Statistic();
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Import> c = q.from(Import.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.SUCCESSPROP), ServiceConstants.INTONE));
		q.multiselect(cb.sum(c.get(ServiceConstants.QUANTITYPROP)));
		s.setName(ServiceConstants.SUMIMPORTSUCCESS);
		Object result = em.createQuery(q).getSingleResult();
		if(result != null) {
			s.setStat(result.toString());
			impSuccess = (int) result;
		}
		else {
			s.setStat(ServiceConstants.INTDEFAULT+ServiceConstants.EMPTYSTR);
		}
		return s;
    }
    /**
	 * Darabra összesen legtöbbet exportált termék
	 * @return A legtöbbet kiexportált termék, és ennek a mennyisége
	 * @author Horváth Dániel
	 */
	private static Statistic sumExportMaxQuantity() {
		Statistic s = new Statistic();
		em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object[]> q = cb.createQuery(Object[].class);
		Root<Export> c = q.from(Export.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.SUCCESSPROP), ServiceConstants.INTONE));
		q.multiselect(c.get(ServiceConstants.WAREPROP), c.get(ServiceConstants.QUANTITYPROP));
		q.multiselect(c.get(ServiceConstants.WAREPROP), cb.sum(c.get(ServiceConstants.QUANTITYPROP)));
		q.groupBy(c.get(ServiceConstants.WAREPROP));
		List<Object[]> valueArray = em.createQuery( q ).getResultList();
		String st=ServiceConstants.EMPTYSTR;
		long i = ServiceConstants.LONGDEFAULT, max=ServiceConstants.LONGDEFAULT;
		for(Object[] values : valueArray) {
			i = (long) values[ServiceConstants.INTONE];
			if(i>max) {
				max = i;
				st= values[ServiceConstants.INTDEFAULT].toString();
			}
		}
		s.setName(ServiceConstants.SUMEXPORTMAX+st);
		s.setStat(max+ServiceConstants.EMPTYSTR);
		return s;
	}
	/**
	 * Az összes sikeresen exportált termék
	 * @return Az összes sikeresen importált termék száma
	 * @author Horváth Dániel
	 */
	private static Statistic sumExportSuccess() {
		Statistic s = new Statistic();
		em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Export> c = q.from(Export.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.SUCCESSPROP), ServiceConstants.INTONE));
		q.multiselect(cb.sum(c.get(ServiceConstants.QUANTITYPROP)));
		s.setName(ServiceConstants.SUMEXPORTSUCCESS);
		Object result = em.createQuery(q).getSingleResult();
		if(result != null) {
			s.setStat(result.toString());
			expSuccess = (int) result;
		}
		else {
			s.setStat(ServiceConstants.INTDEFAULT+ServiceConstants.EMPTYSTR);
		}
		return s;
	}
	/**
     * Az összes eddig sikertelenül megrendelt termék
     * @return Az összes sikertelenül megrendelt termék száma
     * @author Horváth Dániel
     */
    private static Statistic sumImportUnSuccess() {
    	Statistic s = new Statistic();
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Import> c = q.from(Import.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.SUCCESSPROP), ServiceConstants.INTDEFAULT));
		q.multiselect(cb.sum(c.get(ServiceConstants.QUANTITYPROP)));
		s.setName(ServiceConstants.SUMIMPORTUNSUCCESS);
		Object result = em.createQuery(q).getSingleResult();
		if(result != null) {
			s.setStat(result.toString());
			impUnSuccess = (int) result;
		}
		else {
			s.setStat(ServiceConstants.INTDEFAULT+ServiceConstants.EMPTYSTR);
		}
		return s;
    }
    /**
     * Az összes eddig sikertelenül exportált termék
     * @return Az összes sikertelenül exportált termék száma
     * @author Horváth Dániel
     */
    private static Statistic sumExportUnSuccess() {
    	Statistic s = new Statistic();
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Export> c = q.from(Export.class);
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.DELETEDROW), ServiceConstants.INTDEFAULT));
		q.multiselect(c).where(cb.equal(c.get(ServiceConstants.SUCCESSPROP), ServiceConstants.INTDEFAULT));
		q.multiselect(cb.sum(c.get(ServiceConstants.QUANTITYPROP)));
		s.setName(ServiceConstants.SUMEXPORTUNSUCCESS);
		Object result = em.createQuery(q).getSingleResult();
		if(result != null) {
			s.setStat(result.toString());
			expUnSuccess = (int) result;
		}
		else {
			s.setStat(ServiceConstants.INTDEFAULT+ServiceConstants.EMPTYSTR);
		}
		return s;
    }
    /**
     * A sikeresen importált termékek aránya
     * @return A sikeresen importált termékek aránya százalékban
     * @author Horváth Dániel
     */
    private static Statistic impSuccessRate() {
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.IMPORTSUCCESSRATE);
    	if(impUnSuccess==ServiceConstants.INTDEFAULT && impSuccess==ServiceConstants.INTDEFAULT) {
    		s.setStat(ServiceConstants.IMPORTSUCCESSRATEEMPTY);
    	}
    	else {
    		double d = ((double) impSuccess /(impUnSuccess+impSuccess))*ServiceConstants.INTPERCENTCONV;
        	NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
        	s.setStat(form.format(d)+ServiceConstants.PERCENTTXT);
    	}
    	return s;
    }
    /**
     * A sikeresen exportált termékek aránya
     * @return A sikeresen exportált termékek aránya százalékban
     * @author Horváth Dániel
     */
    private static Statistic expSuccessRate() {
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.EXPORTSUCCESSRATE);
    	if(expUnSuccess==ServiceConstants.INTDEFAULT && expSuccess==ServiceConstants.INTDEFAULT) {
    		s.setStat(ServiceConstants.EXPORTSUCCESSRATEEMPTY);
    	}
    	else {
    		double d = ((double) expSuccess /(expUnSuccess+expSuccess))*ServiceConstants.INTPERCENTCONV;
        	NumberFormat form = new DecimalFormat(ServiceConstants.DECFORMAT);
        	s.setStat(form.format(d)+ServiceConstants.PERCENTTXT);
    	}
    	return s;
    }

    /**
     * A legelőször importált termék időpontja
     * @return Legelőször importált termék időpontja
     * @author Szatmári Koppány Gergő
     */
    private static Statistic firstImportedWare(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.FIRSTIMPORTEDWARE);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Import> c = q.from(Import.class);
		q.multiselect(c.get(ServiceConstants.TIMESTAMPPROP)).orderBy(cb.asc(c.get(ServiceConstants.TIMESTAMPPROP)));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> portArray = em.createQuery(q).getResultList();
			String S = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(portArray.get(ServiceConstants.INTDEFAULT));
			
			s.setStat(S);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * A legutoljára importált termék időpontja
     * @return A legutoljára importált termék időpontja
     * @author Szatmári Koppány Gergő
     */
    private static Statistic lastImportedWare(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.LASTIMPORTEDWARE);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Import> c = q.from(Import.class);
		q.multiselect(c.get(ServiceConstants.TIMESTAMPPROP)).orderBy(cb.desc(c.get(ServiceConstants.TIMESTAMPPROP)));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> portArray = em.createQuery(q).getResultList();
			String S = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(portArray.get(ServiceConstants.INTDEFAULT));
			s.setStat(S);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * A legelső és legutolsó importált termék között eltelt napok száma
     * @return A legelső és legutolsó importált termék között eltelt napok száma
     * @author Szatmári Koppány Gergő
     */
    private static Statistic firstLastImportedDelay(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.FIRSTLASTIMPORTDELAY);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Import> c = q.from(Import.class);
		q.multiselect(c.get(ServiceConstants.TIMESTAMPPROP)).orderBy(cb.asc(c.get(ServiceConstants.TIMESTAMPPROP)));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> portArray = em.createQuery(q).getResultList();
			long diffMill = Math.abs(((Date) portArray.get(portArray.size()-1)).getTime() - ((Date) portArray.get(ServiceConstants.INTDEFAULT)).getTime());
			long diffDays = (diffMill + ServiceConstants.DATECONVERSIONSUMMER) / (ServiceConstants.DATECONVERSIONNORMAL);
			s.setStat(diffDays+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
    
    /**
     * A legelőször exportált termék időpontja
     * @return Legelőször exportált termék időpontja
     * @author Szatmári Koppány Gergő
     */
    private static Statistic firstExportedWare(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.FIRSTEXPORTEDWARE);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Export> c = q.from(Export.class);
		q.multiselect(c.get(ServiceConstants.TIMESTAMPPROP)).orderBy(cb.asc(c.get(ServiceConstants.TIMESTAMPPROP)));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> portArray = em.createQuery(q).getResultList();
			String S = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(portArray.get(ServiceConstants.INTDEFAULT));
			s.setStat(S);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * A legutoljára exportált termék időpontja
     * @return A legutoljára exportált termék időpontja
     * @author Szatmári Koppány Gergő
     */
    private static Statistic lastExportedWare(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.LASTEXPORTEDWARE);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Export> c = q.from(Export.class);
		q.multiselect(c.get(ServiceConstants.TIMESTAMPPROP)).orderBy(cb.desc(c.get(ServiceConstants.TIMESTAMPPROP)));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> portArray = em.createQuery(q).getResultList();
			String S = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(portArray.get(ServiceConstants.INTDEFAULT));
			s.setStat(S);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }

    /**
     * A legelső és legutolsó exportált termék között eltelt napok száma
     * @return A legelső és legutolsó exportált termék között eltelt napok száma
     * @author Szatmári Koppány Gergő
     */
    private static Statistic firstLastExportedDelay(){
    	Statistic s = new Statistic();
    	s.setName(ServiceConstants.FIRSTLASTEXPORTDELAY);
    	em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Object> q = cb.createQuery(Object.class);
		Root<Export> c = q.from(Export.class);
		q.multiselect(c.get(ServiceConstants.TIMESTAMPPROP)).orderBy(cb.asc(c.get(ServiceConstants.TIMESTAMPPROP)));
		if(em.createQuery(q).getResultList().isEmpty() != true){
			List<Object> portArray = em.createQuery(q).getResultList();
			long diffMill = Math.abs(((Date) portArray.get(portArray.size()-1)).getTime() - ((Date) portArray.get(ServiceConstants.INTDEFAULT)).getTime());
			long diffDays = (diffMill + ServiceConstants.DATECONVERSIONSUMMER) / (ServiceConstants.DATECONVERSIONNORMAL);
			s.setStat(diffDays+ServiceConstants.EMPTYSTR);
		} else {
			s.setStat(ServiceConstants.NULLEMPTY);
		}
    	return s;
    }
}