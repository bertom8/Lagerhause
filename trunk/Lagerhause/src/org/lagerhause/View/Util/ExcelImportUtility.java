package org.lagerhause.View.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.EntityManager;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.lagerhause.Model.Classes.Customer;
import org.lagerhause.Model.Classes.Export;
import org.lagerhause.Model.Classes.Import;
import org.lagerhause.Model.Classes.Storage;
import org.lagerhause.Model.Classes.Supplier;
import org.lagerhause.Model.Classes.Ware;
import org.lagerhause.Model.Services.CreateService;
import org.lagerhause.Model.Services.LogService;
import org.lagerhause.View.Constants.Constants;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.server.Page;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

/**
 * Excel importáló osztály
 * @author Pilán Ádám György
 *
 */
public class ExcelImportUtility implements Receiver, SucceededListener {
	// -----------------------------------------------------------------------
	private static final long serialVersionUID = 4611160666146669923L;
	// -----------------------------------------------------------------------
	private File file;
	private boolean success;
	private FileOutputStream fileOutputStream;
	private final Class<?> containerClass;
	private final JPAContainer<?> jpaContainer;
	private EntityManager entityManager;
	// -----------------------------------------------------------------------
	
	/**
	 * Konstruktor
	 * @param jpaContainer Konténer. ami megjelenik
	 * @param containerClass Konténerben tárolt osztály
	 */
	public ExcelImportUtility(final JPAContainer<?> jpaContainer,final Class<?> containerClass){
		this.jpaContainer=jpaContainer;
		this.containerClass=containerClass;
	}

	@Override
	public OutputStream receiveUpload(final String filename, final String mimeType) {
		try {
			if (Constants.EMPTY_STRING.equals(filename)) {
				file = new File(Constants.DEFAULT_FILE);
				fileOutputStream = new FileOutputStream(file);
				success = false;
				new Notification(Constants.UPLOAD_ERROR_NOTIFICATION, Constants.UPLOAD_ERROR_NOFILE, Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
			} else {
				file = new File(Constants.FILE_LOCATION + filename);
				fileOutputStream = new FileOutputStream(file);
				if (!mimeType.contains(Constants.MIMETYPE_EXCEL) && !mimeType.contains(Constants.MIMETYPE_EXCEL_X)) {
					success = false;
					new Notification(Constants.UPLOAD_ERROR_NOTIFICATION, Constants.UPLOAD_ERROR_NOTIFICATION_NOEXCEL, Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
				} else {
					success = true;
				}
			}
		} catch (final FileNotFoundException e) {
			new Notification(Constants.UPLOAD_ERROR_NOTIFICATION,Constants.FILE_NOT_FOUND_EXCEPTION).show(Page.getCurrent());
		}
		return fileOutputStream;
	}
	
	@Override
	public void uploadSucceeded(final SucceededEvent event) {
		if (success) {
			importItemsFromFile(file);
		}
	}

	/**
	 * File feldolgozásának indítása
	 * @param file Bemeneti file
	 */
	public void importItemsFromFile(final File file) {
		try {
			final Workbook workbook = WorkbookFactory.create(file);
			final Sheet sheet = workbook.getSheetAt(0);
			importItems(sheet);
			if (success) {
				jpaContainer.refresh();
			}
		} catch (final Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * File feldolgozása
	 * @param sheet Munkalap
	 */
	private void importItems(final Sheet sheet) {
		for (int index = Constants.CONST_1; index <= sheet.getLastRowNum(); index++) {
			try {
				final Row row = sheet.getRow(index);
				switch(containerClass.getSimpleName()){
					case Constants.WARE: importWare(row); break;
					case Constants.IMPORT: importImport(row); break;
					case Constants.EXPORT: importExport(row); break;
				}
				final Row nextRow = sheet.getRow(index + Constants.CONST_1);
				if (nextRow == null || nextRow.getCell(Constants.CONST_0) == null) {
					break;
				}
			} catch (final NullPointerException e) {
				success = false;
			}
		}
	}
	
	/**
	 * Egy Áru importálása
	 * @param row Táblázat egy sora
	 */
	private void importWare(final Row row){
		final Object newItemId = jpaContainer.addItem();
		try {
			final Storage storage = EntityFinderUtility.findEntityByName(Storage.class, row.getCell(Constants.CONST_2).getStringCellValue(), Constants.STORAGENAME);
			if (storage != null) {
				entityManager = CreateService.createEntityManager();
				entityManager.getTransaction().begin();
				final Ware ware = entityManager.find(Ware.class, newItemId);
				final String name = row.getCell(Constants.CONST_0).getStringCellValue();
				final String category = row.getCell(Constants.CONST_1).getStringCellValue();
				final String serial = row.getCell(Constants.CONST_3).getStringCellValue();
				final int quantity = (int) row.getCell(Constants.CONST_4).getNumericCellValue();
				ware.setName(name);
				ware.setCategory(category);
				ware.setStorage(storage);
				ware.setSerial(serial);
				ware.setQuantity(quantity);
				if (entityExists(ware, name)) {
					jpaContainer.removeItem(newItemId);
					entityManager.getTransaction().rollback();
				} else {
					entityManager.persist(ware);
					entityManager.getTransaction().commit();
					LogService.AddLogEntry(Constants.WAREIMPORT, ware, Ware.class);
				}
				entityManager.close();
			} else {
				jpaContainer.removeItem(newItemId);
			}
		} catch (Exception e){
			jpaContainer.removeItem(newItemId);
		}
	}
	
	/**
	 * Egy import importálása
	 * @param row Táblázat egy sora
	 */
	private void importImport(final Row row){
		final Object newItemId = jpaContainer.addItem();
		try {
			final Ware ware = EntityFinderUtility.findEntityByName(Ware.class, row.getCell(Constants.CONST_2).getStringCellValue(), Constants.NAME);
			final Supplier supplier = EntityFinderUtility.findEntityByName(Supplier.class, row.getCell(Constants.CONST_3).getStringCellValue(), Constants.NAME);
			if (ware != null && supplier != null) {
				entityManager = CreateService.createEntityManager();
				entityManager.getTransaction().begin();
				final Import imp = entityManager.find(Import.class, newItemId);
				final Timestamp timestamp = new Timestamp(getLongTimestampValue(row.getCell(Constants.CONST_0).getNumericCellValue()));
				final boolean success = Constants.YES.equals(row.getCell(Constants.CONST_1).getStringCellValue()) ? true : false;
				final int quantity = (int) row.getCell(Constants.CONST_4).getNumericCellValue();
				imp.setTimestamp(timestamp);
				imp.setSuccess(success);
				imp.setWare(ware);
				imp.setSupplier(supplier);
				imp.setQuantity(quantity);
				if (entityExists(imp, timestamp)) {
					jpaContainer.removeItem(newItemId);
					entityManager.getTransaction().rollback();
				} else {
					entityManager.persist(imp);
					entityManager.getTransaction().commit();
					LogService.AddLogEntry(Constants.IMPORTIMPORT, imp, Import.class);
				}
				entityManager.close();
			} else {
				jpaContainer.removeItem(newItemId);
			}
		} catch (Exception e){
			jpaContainer.removeItem(newItemId);
		}
	}
	
	/**
	 * Egy export importálása
	 * @param row Táblázat egy sora
	 */
	private void importExport(final Row row){
		final Object newItemId = jpaContainer.addItem();
		try {
			final Ware ware = EntityFinderUtility.findEntityByName(Ware.class, row.getCell(Constants.CONST_2).getStringCellValue(), Constants.NAME);
			final Customer customer = EntityFinderUtility.findEntityByName(Customer.class, row.getCell(Constants.CONST_3).getStringCellValue(), Constants.NAME);
			if (ware != null && customer != null) {
				entityManager = CreateService.createEntityManager();
				entityManager.getTransaction().begin();
				final Export imp = entityManager.find(Export.class, newItemId);
				final Timestamp timestamp = new Timestamp(getLongTimestampValue(row.getCell(Constants.CONST_0).getNumericCellValue()));
				final boolean success = Constants.YES.equals(row.getCell(Constants.CONST_1).getStringCellValue()) ? true : false;
				final int quantity = (int) row.getCell(Constants.CONST_4).getNumericCellValue();
				imp.setTimestamp(timestamp);
				imp.setSuccess(success);
				imp.setWare(ware);
				imp.setCustomer(customer);
				imp.setQuantity(quantity);
				if (entityExists(imp, timestamp)) {
					jpaContainer.removeItem(newItemId);
					entityManager.getTransaction().rollback();
				} else {
					entityManager.persist(imp);
					entityManager.getTransaction().commit();
					LogService.AddLogEntry(Constants.EXPORTIMPORT, imp, Export.class);
				}
				entityManager.close();
			} else {
				jpaContainer.removeItem(newItemId);
			}
		} catch (Exception e) {
			jpaContainer.removeItem(newItemId);
		}
	}
	
	/**
	 * Excel DateTime (double) -> MySQL DateTime (long) konverter 
	 * @param numericCellValue A cella double értéke
	 * @return Az adatbázissal kompatibilis long érték
	 */
	private long getLongTimestampValue(final double numericCellValue) {
		return (long) ((numericCellValue - Constants.CONST_CONVERSION_SUB) * Constants.CONST_CONVERSION_SCALE);
	}
	
	@SuppressWarnings("unchecked")
	private <T> boolean entityExists(T entity, Object fieldContent) {
		List<T> resultList;
		if (entity.getClass().isAssignableFrom(Ware.class)){
			resultList = (List<T>) EntityFinderUtility.findMatchingEntities(entity.getClass(),(String)fieldContent,Constants.NAME);
			for (T ware : resultList){
				final Ware wOrig = (Ware) ware;
				final Ware wNew = (Ware) entity;
				if (wOrig.getSerial().equals(wNew.getSerial())){
					return true;
				}
			}
		} else {
			if (entity.getClass().isAssignableFrom(Import.class)){
				resultList = (List<T>) EntityFinderUtility.findAllEntities(Import.class);
				resultList.remove(resultList.size()-Constants.CONST_1);
				for (T im : resultList) {
					final Import iOrig = (Import) im;
					final Import iNew = (Import) entity;
					if (iOrig.getTimestamp().equals(iNew.getTimestamp())){
						if (iOrig.getSupplier().equals(iNew.getSupplier()) && iOrig.getTimestamp().equals(iNew.getTimestamp())) {
							return true;
						}
					}
				}
			} else {
				resultList = (List<T>) EntityFinderUtility.findAllEntities(Export.class);
				resultList.remove(resultList.size()-Constants.CONST_1);
				for (T ex : resultList) {
					final Export eOrig = (Export) ex;
					final Export eNew = (Export) entity;
					if (eOrig.getTimestamp().getTime()==eNew.getTimestamp().getTime()){
						if (eOrig.getCustomer().getId()==eNew.getCustomer().getId() && eOrig.getWare().getId()==eNew.getWare().getId()) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * A sikerességet tároló változó gettere
	 * @return Igaz, ha sikeres upload. Hamis, ha sikertelen.
	 */
	public boolean isUploadSuccessful() {
		return success;
	}

}