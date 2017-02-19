package org.lagerhause.Model.Services;

import java.util.Iterator;

import javax.persistence.EntityTransaction;

import org.hibernate.HibernateException;
import org.lagerhause.Model.Classes.Customer;
import org.lagerhause.Model.Classes.Export;
import org.lagerhause.Model.Classes.Import;
import org.lagerhause.Model.Classes.Storage;
import org.lagerhause.Model.Classes.Supplier;
import org.lagerhause.Model.Classes.User;
import org.lagerhause.Model.Classes.Ware;
import org.lagerhause.Model.Services.ServiceConstants;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.data.Item;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.server.Page;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Field;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

/**
 * Az EditListenerhez szükséges függvények
 * @author Bereczki Tamás
 *
 */
public class EditListeners extends ListenerService {
	/**
	 * Elvégzi az adatbázisműveletet az User szerkesztésre
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	protected static void editListenerUserForm(FieldGroup fg, FormLayout fl) {
		Field<?> name = fg.buildAndBind(ServiceConstants.USERNAME, ServiceConstants.USERNAMEPROP);
		name.setEnabled(false);
		Field<?> admin = fg.buildAndBind(ServiceConstants.ADMIN, ServiceConstants.ADMINPROP);
		Field<?> statistic = fg.buildAndBind(ServiceConstants.STATISTIC, ServiceConstants.STATISTICPROP);
		@SuppressWarnings("unchecked")
		Field<String> password = (Field<String>) fg.buildAndBind(ServiceConstants.NEWPASSWORD, ServiceConstants.PASSWORDPROP);
		password.setValue("");
		fl.addComponent(name);
		fl.addComponent(admin);
		fl.addComponent(statistic);
		fl.addComponent(password);
	}

	/**
	 * Legyártja a Customer szerkesztő ablakot
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	protected static void editListenerCustomerForm(FieldGroup fg, FormLayout fl) {
		AddListeners.addListenerCustomerForm(fg, fl);
	}

	/**
	 * Legyártja a Storage szerkesztő ablakot
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	protected static void editListenerStorageForm(FieldGroup fg, FormLayout fl) {
		AddListeners.addListenerStorageForm(fg, fl);
	}

	/**
	 * Legyártja a Supplier szerkesztő ablakot
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	protected static void editListenerSupplierForm(FieldGroup fg, FormLayout fl) {
		AddListeners.addListenerSupplierForm(fg, fl);
	}

	/**
	 * Legyártja a Ware szerkesztő ablakot
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	protected static void editListenerWareForm(FieldGroup fg, FormLayout fl, Item item) {
		Field<?> name = fg.buildAndBind(ServiceConstants.WARENAME, ServiceConstants.WARENAMEPROP);
		Field<?> category = fg.buildAndBind(ServiceConstants.WARECATEGORY, ServiceConstants.WARECATEGORYPROP);
		storageJPA = JPAContainerFactory.makeNonCached(Storage.class, CreateService.createEntityManager());
		storageList = new ComboBox(ServiceConstants.WARESTORAGE, storageJPA);
		storageList.setItemCaptionPropertyId(ServiceConstants.STORAGENAMEPROP);
		Iterator<?> i = storageList.getItemIds().iterator();
		while (i.hasNext()) {
			Object wId = i.next();
			if (storageList.getItemCaption(wId).equals(((Storage)item.getItemProperty(ServiceConstants.WARESTORAGEPROP).getValue()).getStorageName())) {
				storageList.select(wId);
				storageList.setValue(((Storage)item.getItemProperty(ServiceConstants.WARESTORAGEPROP).getValue()).getId());
				break;
			}
		}
		storageList.setTextInputAllowed(false);
		storageList.setNewItemsAllowed(false);
		storageList.setNullSelectionAllowed(false);
		Field<?> serial = fg.buildAndBind(ServiceConstants.WARESERIAL, ServiceConstants.WARESERIALPROP);
		Field<?> quantity = fg.buildAndBind(ServiceConstants.WAREQUANTITY, ServiceConstants.WAREQUANTITYPROP);
		fl.addComponent(name);
		fl.addComponent(category);
		fl.addComponent(storageList);
		fl.addComponent(serial);
		fl.addComponent(quantity);
	}

	/**
	 * Legyártja a behozottaknak a szerkesztő ablakát
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	protected static void editListenerImportForm(FieldGroup fg, FormLayout fl, Item item) {
		Field<?> success = fg.buildAndBind(ServiceConstants.SUCCESS, ServiceConstants.SUCCESSPROP);
		em = CreateService.createEntityManager();
		storageJPA = JPAContainerFactory.makeNonCached(Ware.class, CreateService.createEntityManager());
		storageList = new ComboBox(ServiceConstants.WARENAME, storageJPA);
		storageList.setItemCaptionPropertyId(ServiceConstants.WARENAMEPROP);
		Iterator<?> i = storageList.getItemIds().iterator();
		while (i.hasNext()) {
			Object wId = i.next();
			if (storageList.getItemCaption(wId).equals(((Ware)item.getItemProperty(ServiceConstants.WAREPROP).getValue()).getName())) {
				storageList.select(wId);
				storageList.setValue(((Ware)item.getItemProperty(ServiceConstants.WAREPROP).getValue()).getId());
				break;
			}
		}
		storageList.setNewItemsAllowed(false);
		storageList.setTextInputAllowed(false);
		storageList.setNullSelectionAllowed(false);
		customerJPA = JPAContainerFactory.makeNonCached(Supplier.class, CreateService.createEntityManager());
		supplierList = new ComboBox(ServiceConstants.NAME, customerJPA);
		supplierList.setItemCaptionPropertyId(ServiceConstants.SUPPLIERNAMEPROP);
		Iterator<?> j = supplierList.getItemIds().iterator();
		while (j.hasNext()) {
			Object wId = j.next();
			if (supplierList.getItemCaption(wId).equals(((Supplier)item.getItemProperty(ServiceConstants.SUPPLIERPROP).getValue()).getName())) {
				supplierList.select(wId);
				supplierList.setValue(((Supplier)item.getItemProperty(ServiceConstants.SUPPLIERPROP).getValue()).getId());
				break;
			}
		}
		supplierList.setNewItemsAllowed(false);
		supplierList.setTextInputAllowed(false);
		supplierList.setNullSelectionAllowed(false);
		Field<?> quantity = fg.buildAndBind(ServiceConstants.WAREQUANTITY, ServiceConstants.WAREQUANTITYPROP);
		fl.addComponent(success);
		fl.addComponent(storageList);
		fl.addComponent(supplierList);
		fl.addComponent(quantity);
	}

	/**
	 * Legyártja a kivitteknek a szerkesztő ablakát
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	protected static void editListenerExportForm(FieldGroup fg, FormLayout fl, Item item) {
		Field<?> success = fg.buildAndBind(ServiceConstants.SUCCESS, ServiceConstants.SUCCESSPROP);
		storageJPA = JPAContainerFactory.makeNonCached(Ware.class, CreateService.createEntityManager());
		storageList = new ComboBox(ServiceConstants.WARENAME, storageJPA);
		storageList.setItemCaptionPropertyId(ServiceConstants.WARENAMEPROP);
		Iterator<?> i = storageList.getItemIds().iterator();
		while (i.hasNext()) {
			Object wId = i.next();
			if (storageList.getItemCaption(wId).equals(((Ware)item.getItemProperty(ServiceConstants.WAREPROP).getValue()).getName())) {
				storageList.select(wId);
				storageList.setValue(((Ware)item.getItemProperty(ServiceConstants.WAREPROP).getValue()).getId());
				break;
			}
		}
		storageList.setNewItemsAllowed(false);
		storageList.setTextInputAllowed(false);
		storageList.setNullSelectionAllowed(false);
		customerJPA = JPAContainerFactory.makeNonCached(Customer.class, CreateService.createEntityManager());
		customerList = new ComboBox(ServiceConstants.NAME, customerJPA);
		customerList.setItemCaptionPropertyId(ServiceConstants.C_NAMEPROP);
		Iterator<?> j = customerList.getItemIds().iterator();
		while (j.hasNext()) {
			Object wId = j.next();
			if (customerList.getItemCaption(wId).equals(((Customer)item.getItemProperty(ServiceConstants.CUSTOMERPROP).getValue()).getName())) {
				customerList.select(wId);
				customerList.setValue(((Customer)item.getItemProperty(ServiceConstants.CUSTOMERPROP).getValue()).getId());
				break;
			}
		}
		customerList.setNewItemsAllowed(false);
		customerList.setTextInputAllowed(false);
		customerList.setNullSelectionAllowed(false);
		Field<?> quantity = fg.buildAndBind(ServiceConstants.WAREQUANTITY, ServiceConstants.WAREQUANTITYPROP);
		fl.addComponent(success);
		fl.addComponent(storageList);
		fl.addComponent(customerList);
		fl.addComponent(quantity);
	}


	/**
	 * Elvégzi az adatbázisműveletet az User szerkesztésre
	 * @param fg
	 * @param jpa
	 * @param item
	 * @author Bereczki Tamás
	 */
	protected static void editListenerUserClick(FieldGroup fg, JPAContainer<?> jpa, Item item) {
		if((String)fg.getField(ServiceConstants.USERNAMEPROP).getValue() == "")
			new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.EMPTYTEXT,Type.ERROR_MESSAGE).show(Page.getCurrent());
		else {
			em = CreateService.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				User u = em.find(User.class, item.getItemProperty(ServiceConstants.USERNAMEPROP).getValue());
				u.setUserName((String)fg.getField(ServiceConstants.USERNAMEPROP).getValue());
				u.setAdmin((boolean)fg.getField(ServiceConstants.ADMINPROP).getValue());
				u.setStatistics((boolean)fg.getField(ServiceConstants.STATISTICPROP).getValue());
				if (((String)fg.getField(ServiceConstants.PASSWORDPROP).getValue()) == "" ) {
					u.setPassword((String)item.getItemProperty(ServiceConstants.PASSWORDPROP).getValue());
					em.persist(u);
					tx.commit();
					LogService.AddLogEntry(ServiceConstants.EDITEDUSER, u, User.class);
				}
				else {
					u.setPassword((String)fg.getField(ServiceConstants.PASSWORDPROP).getValue());
					em.persist(u);
					tx.commit();
					UserService.changePassword(u.getUserName(), u.getPassword());
					LogService.AddLogEntry(ServiceConstants.EDITEDUSERORPASS, u, User.class);
				}
				jpa.refresh();
			}
			catch (HibernateException e) {
				if (tx != null && tx.isActive())
					tx.rollback();
				e.printStackTrace();
			}
			finally {
				em.close();
			}
		}
	}


	/**
	 * Elvégzi az adatbázisműveletet az termékszerkesztésre
	 * @param fg
	 * @param jpa
	 * @param item
	 * @param itemId
	 * @author Bereczki Tamás
	 */
	protected static void editListenerWareClick(FieldGroup fg, JPAContainer<?> jpa, Item item, Object itemId) {
		int quantity = Integer.parseInt((String)fg.getField(ServiceConstants.WAREQUANTITYPROP).getValue());
		if (quantity < 1) {
			new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.WRONGVALUE,Type.ERROR_MESSAGE).show(Page.getCurrent());
		}
		else {
			em = CreateService.createEntityManager();
			Storage s = em.find(Storage.class, storageList.getValue());
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				Ware w = em.find(Ware.class, item.getItemProperty(ServiceConstants.ID).getValue());
				w.setName((String)fg.getField(ServiceConstants.WARENAMEPROP).getValue());
				w.setCategory((String)fg.getField(ServiceConstants.WARECATEGORYPROP).getValue());
				w.setSerial((String)fg.getField(ServiceConstants.WARESERIALPROP).getValue());
				w.setQuantity(quantity);
				w.setStorage(s);
				em.persist(w);
				tx.commit();
				jpa.refreshItem(itemId);
				LogService.AddLogEntry(ServiceConstants.EDITEDWARE, w, Ware.class);
			}
			catch (HibernateException e) {
				if (tx != null && tx.isActive())
					tx.rollback();
				e.printStackTrace();
			}
			finally {
				em.close();
			}
		}
	}


	/**
	 * Elvégzi az adatbázisműveletet az termékkivitelre
	 * @param fg
	 * @param jpa
	 * @param itemIdForEdit
	 * @author Bereczki Tamás
	 */
	protected static void editListenerImportClick(FieldGroup fg, JPAContainer<?> jpa, Object itemIdForEdit) {
		int quantity = Integer.valueOf((String)fg.getField(ServiceConstants.WAREQUANTITYPROP).getValue());
		if (quantity < 1) {
			new Notification(ServiceConstants.ALERTSTRING, ServiceConstants.WRONGVALUE, Type.ERROR_MESSAGE).show(Page.getCurrent());
			return;
		}
		Ware ware;
		Supplier supplier;
		if (storageList.getValue() == null || supplierList.getValue() == null)
			new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.EMPTYTEXT,Type.ERROR_MESSAGE).show(Page.getCurrent());
		else {
			em = CreateService.createEntityManager();
			Import i = em.find(Import.class, itemIdForEdit);
			ware = em.find(Ware.class, storageList.getValue());
			supplier = em.find(Supplier.class, supplierList.getValue());
			int quantityDifference = 0;
			int anotherQuantity = 0;
			Boolean getFalse = false;
			Boolean anotherWare = false;
			Ware otherWare = null;
			//Ha megváltozott a termék
			if (!i.getWare().equals(ware)) {
				anotherQuantity = i.getQuantity();
				anotherWare = true;
				otherWare = i.getWare();
			}
			//Vagy ha eddig is sikeres volt és most is sikeres a kivitel
			if (i.isSuccess() && (Boolean)fg.getField(ServiceConstants.SUCCESSPROP).getValue()) {
				quantityDifference += -(i.getQuantity() - quantity);
			}
			//vagy ha eddig nem volt sikeres, de most már sikeres a kivitel
			else if (!i.isSuccess() && (Boolean)fg.getField(ServiceConstants.SUCCESSPROP).getValue()) {
				anotherWare = false;
				quantityDifference += quantity;
			}
			else if (i.isSuccess() && (Boolean)fg.getField(ServiceConstants.SUCCESSPROP).getValue() == false) {
				quantityDifference += i.getQuantity();
				getFalse = true;
			}
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				i.setSuccess((Boolean)fg.getField(ServiceConstants.SUCCESSPROP).getValue());
				i.setWare(ware);
				i.setSupplier(supplier);
				i.setQuantity(quantity);
				em.merge(i);
				tx.commit();
				jpa.refresh();
				LogService.AddLogEntry(ServiceConstants.EDITEDIMPORT, i, Import.class);
				//Ha megváltozott a termék, akkor az előző mennyiségét állítsuk vissza
				if (anotherWare)
					decreaseWareQuantity(otherWare.getId(),anotherQuantity);
				//Ha megváltozott a termék és sikeres is a kivitel, akkor módosítsuk a termék mennyiségét
				if (anotherWare && i.isSuccess()) {
					increaseWareQuantity(ware.getId(), quantity);
				}
				if(i.isSuccess())
					increaseWareQuantity(ware.getId(), quantityDifference);
				else if (getFalse)
					decreaseWareQuantity(ware.getId(), quantityDifference);
			}
			catch (HibernateException e) {
				if (tx != null && tx.isActive())
					tx.rollback();
				e.printStackTrace();
			}
			finally {
				em.close();
			}
		}
	}


	/**
	 * Elvégzi az adatbázisműveletet az termékkivitelre
	 * @param fg
	 * @param jpa
	 * @param itemIdForEdit
	 * @atuhor Bereczki Tamás
	 */
	protected static void editListenerExportClick(FieldGroup fg, JPAContainer<?> jpa, Object itemIdForEdit) {
		/*
		 * quantity - A formon beütött mennyiség
		 * quantityDifference - A különbség az eddigi és a most beütött mennyiség között
		 * otherquantity - ha változott a termék, akkor ez tartalmazza az 'eddigi mennyiséget', amit vissza kell állítani a terméknél
		 * anotherWare - másik ware-t választottunk-e most ki
		 * getFalse - nem sikeresre lett-e állítva a kivitel
		 * ware - a most kiválasztott termék
		 * e - a szerkeszteni kívánt export entity
		 */
		int quantity = Integer.valueOf((String)fg.getField(ServiceConstants.WAREQUANTITYPROP).getValue());
		if (quantity < 1) {
			new Notification(ServiceConstants.ALERTSTRING, ServiceConstants.WRONGVALUE, Type.ERROR_MESSAGE).show(Page.getCurrent());
			return;
		}
		Ware ware;
		Customer customer;
		if (storageList.getValue() == null || customerList.getValue() == null)
			new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.EMPTYTEXT,Type.ERROR_MESSAGE).show(Page.getCurrent());
		else {
			em = CreateService.createEntityManager();
			Export e = em.find(Export.class, itemIdForEdit);
			ware = em.find(Ware.class, storageList.getValue());
			customer = em.find(Customer.class, customerList.getValue());
			int quantityDifference = 0; //a termék mennyiségváltozása, ha változott a termék
			int otherQuantity = 0;
			Boolean anotherWare = false;
			Boolean getFalse = false;
			Ware otherWare = null;
			//Ha megváltozott a termék
			if (!e.getWare().equals(ware)) {
				anotherWare = true;
				otherQuantity = e.getQuantity();
				otherWare = e.getWare();
			}
			//Vagy ha eddig is sikeres volt és most is sikeres a kivitel
			if (!anotherWare && e.isSuccess() && (Boolean)fg.getField(ServiceConstants.SUCCESSPROP).getValue()) {
				quantityDifference += -(e.getQuantity() - quantity);
			}
			//vagy ha eddig nem volt sikeres, de most már sikeres a kivitel
			else if (!e.isSuccess() && (Boolean)fg.getField(ServiceConstants.SUCCESSPROP).getValue()) {
				anotherWare = false; // Mert ha ez előtt nem volt sikeres, akkor nem is volt levonva a mennyiség
				quantityDifference += quantity;
			}
			else if (e.isSuccess() && (Boolean)fg.getField(ServiceConstants.SUCCESSPROP).getValue() == false) {
				getFalse = true;
				quantityDifference += e.getQuantity();
			}
			if (quantityDifference > 1 && ware.getQuantity() < quantity) {
				new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.NOENOUGHWARES,Type.ERROR_MESSAGE).show(Page.getCurrent());
				return;
			}
			em = CreateService.createEntityManager();
			EntityTransaction tx = em.getTransaction();
			try {
				tx.begin();
				e.setSuccess((Boolean)fg.getField(ServiceConstants.SUCCESSPROP).getValue());
				e.setWare(ware);
				e.setCustomer(customer);
				e.setQuantity(quantity);
				em.merge(e);
				tx.commit();
				jpa.refresh();
				LogService.AddLogEntry(ServiceConstants.EDITEDEXPORT, e, Export.class);
				//Ha megváltozott a termék, akkor az előző mennyiségét állítsuk vissza
				if (anotherWare)
					increaseWareQuantity(otherWare.getId(),otherQuantity);
				//Ha megváltozott a termék és sikeres is a kivitel, akkor módosítsuk a termék mennyiségét
				if (anotherWare && e.isSuccess()) {
					decreaseWareQuantity(ware.getId(),quantity);
				}
				//Ha nem változott a tároló, de sikeres a kivitel, akkor módosítsuk a termék mennyiségét
				if(e.isSuccess())
					decreaseWareQuantity(ware.getId(),quantityDifference);
				else if (getFalse)
					increaseWareQuantity(ware.getId(),quantityDifference);
			}
			catch (HibernateException ex) {
				if (tx != null && tx.isActive())
					tx.rollback();
				ex.printStackTrace();
			}
			finally {
				em.close();
			}
		}
	}

}
