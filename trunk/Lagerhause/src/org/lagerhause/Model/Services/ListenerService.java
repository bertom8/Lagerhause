package org.lagerhause.Model.Services;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.lagerhause.Model.Classes.*;
import org.lagerhause.Model.Services.ServiceConstants;
import org.vaadin.dialogs.ConfirmDialog;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.data.Item;
import com.vaadin.data.Validator.EmptyValueException;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

/**
 * ClickListenerek megvalósítása az adatbázisműveletek kezelésére
 * @author Bereczki Tamás
 * @author Szatmári Koppány Gergő
 *
 */
public class ListenerService {

	protected static JPAContainer<?> storageJPA = null;
	protected static JPAContainer<?> customerJPA = null;
	protected static ComboBox storageList = null;
	protected static ComboBox customerList = null;
	protected static ComboBox supplierList = null;
	protected static TextField hour = null;
	protected static TextField min = null;
	protected static EntityManager em = null;

	/**
	 * Add gombra való klikkelés megvalósítása
	 * @return Visszaad egy ClickListener függvényt az add funkcióra
	 * @author Bereczki Tamás
	 */
	public static ClickListener getAddButtonListener(JPAContainer<?> jpa) {
		Class<?> clazz = jpa.getEntityClass();
		return new ClickListener(){
			private static final long serialVersionUID = -8692947275044182180L;

			@Override
			public void buttonClick(ClickEvent event) {
				Window w = new Window(ServiceConstants.ADDWINDOW);
				w.setClosable(false);
				w.setModal(true);
				FormLayout fl = new FormLayout();
				fl.setMargin(true);
				fl.setSpacing(true);
				Object itemIdForAdd = jpa.addItem();
				Item item = jpa.getItem(itemIdForAdd);
				FieldGroup fg = new FieldGroup(item);
				createFormForAdd(clazz, fg, fl);
				HorizontalLayout hl = new HorizontalLayout();
				Button addButton = new Button(ServiceConstants.ADD);
				hl.addComponent(addButton);
				addButton.addClickListener(addButtonListener(w, clazz, fg, jpa, itemIdForAdd));
				Button cancelButton = new Button(ServiceConstants.CANCEL);
				hl.addComponent(cancelButton);
				cancelButton.addClickListener(cancelButtonListenerForAdd(w, jpa, itemIdForAdd));
				fl.addComponent(hl);
				w.setContent(fl);
				w.setVisible(true);
				UI.getCurrent().addWindow(w);
			}
		};
	}

	/**
	 * Edit gombra való klikkelés megvalósítása
	 * @return Visszaad egy ClickListener függvényt az edit funkcióra
	 * @author Bereczki Tamás
	 */
	@SuppressWarnings("unchecked")
	public static <T> ClickListener getEditButtonListener(Table table, T containerClass) {
		JPAContainer<T> jpa = (JPAContainer<T>)table.getContainerDataSource();
		return new ClickListener(){
			private static final long serialVersionUID = -8832446120032823884L;

			@Override
			public void buttonClick(ClickEvent event) {
				Object itemId = table.getValue();
				if (itemId == null) {
					new Notification(ServiceConstants.ALERTSTRING, ServiceConstants.NOSELECTEDITEM, Type.ERROR_MESSAGE).show(Page.getCurrent());
				}
				else {
					Window w = new Window(ServiceConstants.EDITWINDOW);
					w.setClosable(false);
					w.setResizable(false);
					w.setModal(true);
					FormLayout fl = new FormLayout();
					fl.setMargin(true);
					fl.setSpacing(true);
					Item item = jpa.getItem(itemId);
					FieldGroup fg = new FieldGroup(item);
					createFormForEdit(containerClass, fg, fl, item);
					HorizontalLayout hl = new HorizontalLayout();
					Button addButton = new Button(ServiceConstants.OKTEXT);
					hl.addComponent(addButton);
					addButton.addClickListener(editButtonListenerForEdit(w, containerClass, jpa, fg, itemId, item));
					Button cancelButton = new Button(ServiceConstants.CANCEL);
					hl.addComponent(cancelButton);
					cancelButton.addClickListener(cancelButtonListenerForEdit(w, jpa));
					fl.addComponent(hl);
					w.setContent(fl);
					w.setVisible(true);
					UI.getCurrent().addWindow(w);
				}
			}
		};
	}

	/**
	 * Remove gombra való klikkelés megvalósítása
	 * @return Visszaad egy ClickListener függvényt a remove funkcióra
	 * @author Szatmári Koppány Gergő
	 */
	@SuppressWarnings("unchecked")
	public static <T> ClickListener getRemoveButtonListener(Table table, T containerClass) {
		JPAContainer<T> jpa = (JPAContainer<T>)table.getContainerDataSource();
		return new ClickListener() {
			private static final long serialVersionUID = -1441002219219016234L;

			@Override
			public void buttonClick(ClickEvent event) {
				if(table.getValue()==null){
					new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.NOSELECTEDITEM).show(Page.getCurrent());
				} else {
					Object itemId = table.getValue();

					ConfirmDialog.show(UI.getCurrent(), ServiceConstants.DELETEWINDOW, ServiceConstants.DELETE_Q,
							ServiceConstants.OKTEXT, ServiceConstants.CANCEL, new ConfirmDialog.Listener() {
						private static final long serialVersionUID = 1L;

						public void onClose(ConfirmDialog dialog) {
							if (dialog.isConfirmed()) {
								if ( (containerClass.equals(new Import().getClass())) && (jpa.getItem(itemId).getItemProperty(ServiceConstants.SUCCESSPROP).getValue().equals(true)) ) {
									long wareId = ((Ware)jpa.getItem(itemId).getItemProperty(ServiceConstants.WAREPROP).getValue()).getId();
									Object wareQ = jpa.getItem(itemId).getItemProperty(ServiceConstants.WAREQUANTITYPROP).getValue();
									decreaseWareQuantity(wareId,(Integer)wareQ);			                					                		
								} else if ( (containerClass.equals(new Export().getClass())) && (jpa.getItem(itemId).getItemProperty(ServiceConstants.SUCCESSPROP).getValue().equals(true)) ) {
									long wareId = ((Ware)jpa.getItem(itemId).getItemProperty(ServiceConstants.WAREPROP).getValue()).getId();
									Object wareQ = jpa.getItem(itemId).getItemProperty(ServiceConstants.WAREQUANTITYPROP).getValue();
									increaseWareQuantity(wareId,(Integer)wareQ);
								} 
								jpa.getItem(itemId).getItemProperty(ServiceConstants.GLOBAL_DELETED).setValue(true);
								jpa.commit();
								jpa.refreshItem(itemId);
								logAction(containerClass, jpa.getItem(itemId).getEntity(), ServiceConstants.REMOVEITEM);
								new Notification(ServiceConstants.SUCCESS_WIN_STRING,ServiceConstants.DELETED).show(Page.getCurrent());
							} 
						}
					});
				}
			}
		};
	}



	/**
	 * Megkeresi azt a Storage-et, aminek paraméterben megkapja az Id-jét
	 * @param storageId
	 * @return Visszaadja a megtalált Storage objektumot
	 * @author Bereczki Tamás
	 */
	protected static Storage getStorageById(long storageId) {
		Storage storage;
		em = CreateService.createEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Storage> q = cb.createQuery(Storage.class);
		Root<Storage> c = q.from(Storage.class);
		q.select(c).where(cb.equal(c.get(ServiceConstants.ID), storageId));
		TypedQuery<Storage> tq = em.createQuery(q);
		storage = tq.getResultList().get(0);

		em.close();
		return storage;
	}

	/**
	 * Megváltoztatja a Ware mennyiség adattagját attól függően, hogy mennyit vittek el a raktárból
	 * @param wId
	 * @param wQuantity
	 */
	protected static void decreaseWareQuantity(long wId, int eQuantity) {
		EntityManager em2 = CreateService.createEntityManager();
		EntityTransaction tx = em2.getTransaction();
		try{
			tx.begin();
			Ware w = em2.find(Ware.class, wId);
			w.setQuantity(w.getQuantity() - eQuantity);
			em2.persist(w);
			tx.commit();
		}
		catch(HibernateException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			em2.close();
		}
	}

	/**
	 * Megváltoztatja a Ware mennyiség adattagját attól függően, hogy mennyit hoztak be a raktárba
	 * @param wId
	 * @param eQuantity
	 */
	protected static void increaseWareQuantity(long wId, int eQuantity) {
		EntityManager em2 = CreateService.createEntityManager();
		EntityTransaction tx = em2.getTransaction();
		try{
			tx.begin();
			Ware w = em2.find(Ware.class, wId);
			w.setQuantity(w.getQuantity() + eQuantity);
			em2.persist(w);
			tx.commit();
		}
		catch(HibernateException e) {
			if (tx != null && tx.isActive())
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			em2.close();
		}
	}

	/**
	 * Az Add ClickListenerjében létrehozott addButtonnak a listenerjét visszaadó függvény
	 * @param w
	 * @param clazz
	 * @param fg
	 * @param jpa
	 * @param itemIdForAdd
	 * @return addButtonnak a listenerjét
	 * @author Bereczki Tamás
	 */
	private static <T> ClickListener addButtonListener(Window w, Class<T> clazz, FieldGroup fg, JPAContainer<?> jpa, Object itemIdForAdd) {
		return new Button.ClickListener() {
			private static final long serialVersionUID = 3639316094848016450L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					if (clazz.isAssignableFrom(User.class)) {
						AddListeners.addListenerUserClick(fg, jpa, itemIdForAdd);
					}
					else if (clazz.isAssignableFrom(Ware.class)) {
						AddListeners.addListenerWareClick(fg, jpa, itemIdForAdd);
					}
					else if (clazz.isAssignableFrom(Export.class)) {
						AddListeners.addListenerExportClick(fg, jpa, itemIdForAdd);
					}
					else if(clazz.isAssignableFrom(Import.class)) {
						AddListeners.addListenerImportClick(fg, jpa, itemIdForAdd);
					}
					else {
						if (clazz.isAssignableFrom(Supplier.class)) {
							if (fg.getField(ServiceConstants.SUPPLIERNAMEPROP).getValue().equals("") || 
									fg.getField(ServiceConstants.S_COUNTRYPROP).getValue().equals("") ||
									fg.getField(ServiceConstants.S_CITYPROP).getValue().equals("") ||
									fg.getField(ServiceConstants.S_STREETPROP).getValue().equals("") ||
									fg.getField(ServiceConstants.S_HOUSENUMBERPROP).getValue().equals("") ||
									fg.getField(ServiceConstants.S_PHONENUMBERPROP).getValue().equals("")) {
								jpa.removeItem(itemIdForAdd);
								new Notification(ServiceConstants.ALERTSTRING, ServiceConstants.EMPTYTEXT, Type.ERROR_MESSAGE).show(Page.getCurrent());
								w.close();
								return;
							}
						}
						else if(clazz.isAssignableFrom(Customer.class)) {
							if (fg.getField(ServiceConstants.C_NAMEPROP).getValue().equals("") || 
									fg.getField(ServiceConstants.C_COUNTRYPROP).getValue().equals("") ||
									fg.getField(ServiceConstants.C_CITYPROP).getValue().equals("") ||
									fg.getField(ServiceConstants.C_STREETPROP).getValue().equals("") ||
									fg.getField(ServiceConstants.C_HOUSENUMBERPROP).getValue().equals("") ||
									fg.getField(ServiceConstants.C_PHONENUMBERPROP).getValue().equals("")) {
								jpa.removeItem(itemIdForAdd);
								new Notification(ServiceConstants.ALERTSTRING, ServiceConstants.EMPTYTEXT, Type.ERROR_MESSAGE).show(Page.getCurrent());
								w.close();
								return;
							}
						}
						else if(clazz.isAssignableFrom(Storage.class))
							if (fg.getField(ServiceConstants.STORAGENAMEPROP).getValue().equals("")) {
								jpa.removeItem(itemIdForAdd);
								new Notification(ServiceConstants.ALERTSTRING, ServiceConstants.EMPTYTEXT, Type.ERROR_MESSAGE).show(Page.getCurrent());
								w.close();
								return;
							}
						fg.commit();
						logAction(clazz, jpa.getItem(itemIdForAdd).getEntity(), ServiceConstants.ADDEDITEM);
					}
					jpa.refresh();
					w.close();
				}
				catch(EmptyValueException e) {
					new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.EMPTYTEXT).show(Page.getCurrent());
				}
				catch(CommitException e) {
					jpa.removeItem(itemIdForAdd);
					new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.COMMITEXC).show(Page.getCurrent());
				}
			}
		};
	}

	/**
	 * Az Add ClickListenerjében létrehozott cancelButtonnak a listenerjét visszaadó függvény
	 * @param w
	 * @param jpa
	 * @param itemIdForAdd
	 * @return cancelButtonnak a listenerjét
	 * @author Bereczki Tamás
	 */
	private static ClickListener cancelButtonListenerForAdd(Window w, JPAContainer<?> jpa, Object itemIdForAdd) {
		return new Button.ClickListener() {
			private static final long serialVersionUID = 3639316094848016450L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				jpa.removeItem(itemIdForAdd);
				w.close();
			}
		};
	}

	/**
	 * Az Edit ClickListenerjében létrehozott addButtonnak a listenerjét visszaadó függvény
	 * @param w
	 * @param containerClass
	 * @param jpa
	 * @param fg
	 * @param itemId
	 * @param item
	 * @return addButtonnak a listenerjét
	 * @author Bereczki Tamás
	 */
	private static <T> ClickListener editButtonListenerForEdit(Window w, T containerClass, JPAContainer<?> jpa, FieldGroup fg, Object itemId, Item item) {
		return new Button.ClickListener() {
			private static final long serialVersionUID = 3639316094848016450L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					if (containerClass.equals(new User().getClass()))
						EditListeners.editListenerUserClick(fg, jpa, item);
					else if (containerClass.equals(new Ware().getClass()))
						EditListeners.editListenerWareClick(fg, jpa, item, itemId);
					else if (containerClass.equals(new Export().getClass()))
						EditListeners.editListenerExportClick(fg, jpa, itemId);
					else if (containerClass.equals(new Import().getClass()))
						EditListeners.editListenerImportClick(fg, jpa, itemId);
					else {
						fg.commit();
						logAction(containerClass, jpa.getItem(itemId).getEntity(), ServiceConstants.EDITEDITEM);
					}
					jpa.refreshItem(itemId);
					w.close();
				}
				catch(EmptyValueException e) {
					new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.EMPTYTEXT).show(Page.getCurrent());
				}
				catch(CommitException e) {
					jpa.discard();
					new Notification(ServiceConstants.ALERTSTRING,ServiceConstants.COMMITEXC).show(Page.getCurrent());
				}

			}
		};
	}

	/**
	 * Az Edit ClickListenerjében létrehozott cancelButtonnak a listenerjét visszaadó függvény
	 * @param w
	 * @param jpa
	 * @return cancelButtonnak a listenerjét
	 * @author Bereczki Tamás
	 */
	private static ClickListener cancelButtonListenerForEdit(Window w, JPAContainer<?> jpa) {
		return new Button.ClickListener() {
			private static final long serialVersionUID = 3639316094848016450L;

			@Override
			public void buttonClick(com.vaadin.ui.Button.ClickEvent event) {
				jpa.discard();
				w.close();
			}
		};
	}

	/**
	 * Megcsinálja a Formot class alapján
	 * @param clazz
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	private static void createFormForAdd(Class<?> clazz, FieldGroup fg, FormLayout fl) {
		if (clazz.isAssignableFrom(User.class)) {
			AddListeners.addListenerUserForm(fg,fl);
		}
		else if (clazz.isAssignableFrom(Customer.class)) {
			AddListeners.addListenerCustomerForm(fg,fl);
		}
		else if (clazz.isAssignableFrom(Storage.class)) {
			AddListeners.addListenerStorageForm(fg,fl);
		}
		else if (clazz.isAssignableFrom(Supplier.class)) {
			AddListeners.addListenerSupplierForm(fg,fl);
		}
		else if (clazz.isAssignableFrom(Ware.class)) {
			AddListeners.addListenerWareForm(fg,fl);
		}
		else if (clazz.isAssignableFrom(Export.class)) {
			AddListeners.addListenerExportForm(fg,fl);
		}
		else if (clazz.isAssignableFrom(Import.class)) {
			AddListeners.addListenerImportForm(fg,fl);
		}
	}

	/**
	 * Megcsinálja a Formot a containerClass alapján
	 * @param containerClass
	 * @param fg
	 * @param fl
	 * @author Bereczki Tamás
	 */
	private static <T> void createFormForEdit(T containerClass, FieldGroup fg, FormLayout fl, Item item) {
		if (containerClass.equals(new User().getClass())) {
			EditListeners.editListenerUserForm(fg, fl);
		}
		else if (containerClass.equals(new Customer().getClass())) {
			EditListeners.editListenerCustomerForm(fg, fl);
		}
		else if (containerClass.equals(new Storage().getClass())) {
			EditListeners.editListenerStorageForm(fg, fl);
		}
		else if (containerClass.equals(new Supplier().getClass())) {
			EditListeners.editListenerSupplierForm(fg, fl);
		}
		else if (containerClass.equals(new Ware().getClass())) {
			EditListeners.editListenerWareForm(fg, fl, item);
		}
		else if (containerClass.equals(new Export().getClass())) {
			EditListeners.editListenerExportForm(fg, fl, item);
		}
		else if (containerClass.equals(new Import().getClass())) {
			EditListeners.editListenerImportForm(fg, fl, item);
		}
	}

	/**
	 * A helyes loggolást kezelő metódus, az olyan esetekhez, mikor FieldGroup segítségével commitolunk
	 * @param containerClass
	 * @param obj
	 * @param action
	 * @author Bereczki Tamás
	 */
	protected static <T> void logAction(T containerClass, Object obj, String action) {
		if (containerClass.equals(new User().getClass())) {
			LogService.AddLogEntry(action, obj, new User().getClass());
		}
		else if (containerClass.equals(new Customer().getClass())) {
			LogService.AddLogEntry(action, obj, new Customer().getClass());
		}
		else if (containerClass.equals(new Storage().getClass())) {
			LogService.AddLogEntry(action, obj, new Storage().getClass());
		}
		else if (containerClass.equals(new Supplier().getClass())) {
			LogService.AddLogEntry(action, obj, new Supplier().getClass());
		}
		else if (containerClass.equals(new Ware().getClass())) {
			LogService.AddLogEntry(action, obj, new Ware().getClass());
		}
		else if (containerClass.equals(new Export().getClass())) {
			LogService.AddLogEntry(action, obj, new Export().getClass());
		}
		else if (containerClass.equals(new Import().getClass())) {
			LogService.AddLogEntry(action, obj, new Import().getClass());
		}
	}
}