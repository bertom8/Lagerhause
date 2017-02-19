package org.lagerhause.View.Util;

import javax.persistence.EntityManager;

import org.apache.poi.ss.usermodel.CellStyle;
import org.lagerhause.Model.Classes.Customer;
import org.lagerhause.Model.Classes.Export;
import org.lagerhause.Model.Classes.Import;
import org.lagerhause.Model.Classes.Log;
import org.lagerhause.Model.Classes.Statistic;
import org.lagerhause.Model.Classes.Storage;
import org.lagerhause.Model.Classes.Supplier;
import org.lagerhause.Model.Classes.User;
import org.lagerhause.Model.Classes.Ware;
import org.lagerhause.Model.Services.CreateService;
import org.lagerhause.Model.Services.ListenerService;
import org.lagerhause.Model.Services.StatisticsService;
import org.lagerhause.View.Constants.Constants;

import com.vaadin.addon.jpacontainer.JPAContainer;
import com.vaadin.addon.jpacontainer.JPAContainerFactory;
import com.vaadin.addon.tableexport.ExcelExport;
import com.vaadin.data.Container.Filter;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Compare;
import com.vaadin.data.util.filter.Like;
import com.vaadin.data.util.filter.Or;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.event.ItemClickEvent.ItemClickListener;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Az egységes táblázatdesignt megvalósító táblázat-generátor
 * @author Pilán Ádám György
 *
 */
public class TableCreatorUtility {
	//-------------------------------------------------------------
	//-------------------------------------------------------------
	private final String title;
	private final Class<?> containerClass;	
	private Table table;
	private ExcelExport excelExport;
	private JPAContainer<?> jpaContainer;	
	private Filter filter;
	private Filter deletedFilter = new Compare.Equal(Constants.DELETED, false);
	private TextField searchField;
	private Button addButton;
	private Button editButton;
	private Button removeButton;
	//-------------------------------------------------------------
	
	/**
	 * Konstruktor
	 * @param title A táblázat címe
	 * @param containerClass A táblázatban tárolt rekordok osztálya
	 */
	public TableCreatorUtility(final String title, final Class<?> containerClass) {
		this.title = title;
		this.containerClass = containerClass;
	}
	
	/**
	 * Az egységes designt követő táblázat megalkotása
	 * @return A layout, amin rajta van a táblázat címe, a kezelőgombok, a kereső és a táblázat
	 */
	public GridLayout createTableLayout(){
		final GridLayout layout = new GridLayout(Constants.CONST_1,Constants.CONST_2);
		layout.setRowExpandRatio(Constants.CONST_0, Constants.CONST_0);
		layout.setRowExpandRatio(Constants.CONST_1, Constants.CONST_1);
		final GridLayout titleGrid = createTitleGrid();
		layout.addComponent(titleGrid,Constants.CONST_0,Constants.CONST_0);
		final EntityManager entityManager = CreateService.createEntityManager();
		jpaContainer = JPAContainerFactory.makeNonCached(containerClass, entityManager);
		jpaContainer.addContainerFilter(deletedFilter);
		table = new Table(null,jpaContainer);		
		setTableColumns();	
		table.setSelectable(true);
		table.setMultiSelect(false);
		table.setSizeFull();
		table.setPageLength(Constants.CONST_0);	
		if (containerClass.isAssignableFrom(User.class)){
			table.addItemClickListener(getUserItemClickListener());
		}
		if (!containerClass.isAssignableFrom(Log.class)){
			addButton.addClickListener(ListenerService.getAddButtonListener(jpaContainer));	
			editButton.addClickListener(ListenerService.getEditButtonListener(table,containerClass));
			removeButton.addClickListener(ListenerService.getRemoveButtonListener(table,containerClass));
		}
		layout.addComponent(table,Constants.CONST_0,Constants.CONST_1);
		layout.setSizeFull();
		layout.setMargin(true);
		return layout;	
	}
	
	/**
	 * Az egységes designt követő táblázat megalkotása
	 * @return A layout, amin rajta van a táblázat címe, a kezelőgombok, a kereső és a táblázat
	 */
	public GridLayout createStatTableLayout(){
		final GridLayout layout = new GridLayout(Constants.CONST_1,Constants.CONST_2);
		layout.setRowExpandRatio(Constants.CONST_0, Constants.CONST_0);
		layout.setRowExpandRatio(Constants.CONST_1, Constants.CONST_1);
		final GridLayout titleGrid = createTitleGrid();
		layout.addComponent(titleGrid,Constants.CONST_0,Constants.CONST_0);
		BeanItemContainer<Statistic> statContainer = StatisticsService.ServeStatistics(); 
		table = new Table(null,statContainer);		
		setTableColumns();	
		table.setSelectable(true);
		table.setMultiSelect(false);
		table.setSizeFull();
		table.setPageLength(Constants.CONST_0);	
		layout.addComponent(table,Constants.CONST_0,Constants.CONST_1);
		layout.setSizeFull();
		layout.setMargin(true);
		return layout;	
	}
	
	/**
	 * A táblázat feletti sáv elkészítése
	 * @return A címet, gombsort és a keresőt tartalmazó layout
	 */
	private GridLayout createTitleGrid(){
		final GridLayout titleGrid = new GridLayout(Constants.CONST_8,Constants.CONST_1);
		titleGrid.setSizeFull();
		titleGrid.setHeight(Constants.SIZE_60PX);
		final Label titleLabel = new Label(title);
		titleGrid.addComponent(titleLabel, Constants.CONST_0, Constants.CONST_0);
		titleGrid.setComponentAlignment(titleLabel, Alignment.BOTTOM_LEFT);
		addButton = new Button(new ThemeResource(Constants.ICON_ADD_LOCATION));
		editButton = new Button(new ThemeResource(Constants.ICON_EDIT_LOCATION));
		removeButton = new Button(new ThemeResource(Constants.ICON_REMOVE_LOCATION));
		final Button importButton = new Button(new ThemeResource(Constants.ICON_IMPORT_LOCATION));		
		final Button exportButton = new Button(new ThemeResource(Constants.ICON_EXPORT_LOCATION));
		searchField = new TextField();
		searchField.setWidth(Constants.SIZE_250PX);
		searchField.setHeight(Constants.SIZE_60PX);
		final Button searchButton = new Button(new ThemeResource(Constants.ICON_SEARCH_LOCATION));
		setButtonStyle(addButton,Constants.ICON_ADD_CAPTION);
		setButtonStyle(editButton,Constants.ICON_EDIT_CAPTION);
		setButtonStyle(removeButton,Constants.ICON_REMOVE_CAPTION);
		setButtonStyle(importButton,Constants.IMPORT);
		setButtonStyle(exportButton,Constants.EXPORT);
		setButtonStyle(searchButton,Constants.ICON_SEARCH_CAPTION);
		searchButton.setClickShortcut(KeyCode.ENTER);
		if (!containerClass.isAssignableFrom(Statistic.class) && !containerClass.isAssignableFrom(Log.class)){
			titleGrid.addComponent(addButton, Constants.CONST_1, Constants.CONST_0);
			titleGrid.addComponent(editButton, Constants.CONST_2, Constants.CONST_0);
			titleGrid.addComponent(removeButton, Constants.CONST_3, Constants.CONST_0);
		}
		if (containerClass.isAssignableFrom(Import.class) || containerClass.isAssignableFrom(Export.class)){
			titleGrid.addComponent(importButton, Constants.CONST_4, Constants.CONST_0);
		}
		if (containerClass.isAssignableFrom(Ware.class)){
			titleGrid.addComponent(importButton, Constants.CONST_4, Constants.CONST_0);
		}
		if (containerClass.isAssignableFrom(Supplier.class) || containerClass.isAssignableFrom(Customer.class)){
			titleGrid.addComponent(exportButton, Constants.CONST_5, Constants.CONST_0);
		}
		if (containerClass.isAssignableFrom(Ware.class) || containerClass.isAssignableFrom(Storage.class)){
			titleGrid.addComponent(exportButton, Constants.CONST_5, Constants.CONST_0);
		}	
		if (containerClass.isAssignableFrom(Statistic.class) || containerClass.isAssignableFrom(Log.class)){
			titleGrid.addComponent(exportButton, Constants.CONST_5, Constants.CONST_0);
		}
		importButton.addClickListener(getImportClickListener());
		exportButton.addClickListener(getExportClickListener());
		searchButton.addClickListener(getSearchClickListener());
		if (!containerClass.isAssignableFrom(Statistic.class)){
			titleGrid.addComponent(searchField, Constants.CONST_6, Constants.CONST_0);
			titleGrid.addComponent(searchButton, Constants.CONST_7, Constants.CONST_0);
		}
		setLayoutExpandRatio(titleGrid);
		return titleGrid;
	}
	
	/**
	 * Import listener
	 * @return Import ClickListener
	 */
	private ClickListener getImportClickListener(){
		return new ClickListener() {

			private static final long serialVersionUID = 3976810839386484874L;

			@Override
			public void buttonClick(final ClickEvent event) {
				final Window uploaderWindow = new Window(Constants.IMPORT);
				uploaderWindow.setWidth(Constants.SIZE_500PX);
				uploaderWindow.setHeight(Constants.SIZE_350PX);
				uploaderWindow.center();
				final GridLayout gridLayout = new GridLayout(Constants.CONST_1, Constants.CONST_3);
				gridLayout.setWidth(Constants.SIZE_350PX);
				gridLayout.setHeight(Constants.SIZE_300PX);
				final VerticalLayout layout = new VerticalLayout(gridLayout);
				layout.setComponentAlignment(gridLayout, Alignment.MIDDLE_CENTER);
				uploaderWindow.setContent(layout);
				uploaderWindow.setClosable(true);
				final Label label = new Label(Constants.UPLOADER_HEADER_CAPTION);
				label.setStyleName(Constants.STYLE_H2);
				label.setSizeFull();
				gridLayout.addComponent(label, Constants.CONST_0, Constants.CONST_0);
				final ExcelImportUtility receiver = new ExcelImportUtility(jpaContainer, containerClass);
				final Upload upload = new Upload(null, receiver);
				upload.addSucceededListener(receiver);
				upload.setButtonCaption(Constants.UPLOAD_CAPTION);
				upload.setSizeFull();
				gridLayout.addComponent(upload, Constants.CONST_0, Constants.CONST_1);
				final Label statusLabel = new Label(Constants.UPLOADER_WAITING_CAPTION);
				statusLabel.setSizeFull();
				gridLayout.addComponent(statusLabel, Constants.CONST_0, Constants.CONST_2);
				gridLayout.setComponentAlignment(label, Alignment.TOP_CENTER);
				upload.addSucceededListener(new SucceededListener() {

					private static final long serialVersionUID = 6838706596744918623L;
					@Override
					public void uploadSucceeded(final SucceededEvent event) {
						statusLabel.setValue(Constants.UPLOADER_DONE_CAPTION);
					}
				});
				UI.getCurrent().addWindow(uploaderWindow);
			}
		};
	}
	
	/**
	 * Export listener
	 * @return Export ClickListener
	 */
	private ClickListener getExportClickListener(){
		return new ClickListener() {

			private static final long serialVersionUID = -7304050848408078398L;

			@Override
			public void buttonClick(final ClickEvent event) {
				excelExport = new ExcelExport(table);
				excelExport.excludeCollapsedColumns();
				excelExport.setReportTitle(title);
				excelExport.setDisplayTotals(false);
				excelExport.convertTable();
				excelExport.getWorkbook().setSheetName(Constants.CONST_0, title);
				setExportStyle(excelExport.getWorkbook().getSheet(title).getLastRowNum(), table.getColumnHeaders().length);
				excelExport.setDateDataFormat(Constants.DATE_DEFAULT_FORMAT);
				excelExport.setExportFileName(containerClass.getSimpleName() + Constants.EXPORT_FILENAME);
				excelExport.sendConverted();
			}
		};
	}
	
	/**
	 * Képmegjelenítő listener
	 * @return Képmegjelenítő ItemClickListener
	 */
	private ItemClickListener getUserItemClickListener(){
		ItemClickListener listener = new ItemClickListener() {
			
			private static final long serialVersionUID = 37359382987048352L;

			@Override
			public void itemClick(ItemClickEvent event) {
				if (event.isDoubleClick()){
					Window window = new Window(Constants.PROFILE_PICTURE);
					Panel panel = new Panel();
					if (event.getItem().getItemProperty(Constants.PICTURE).getValue()!=null){
						final ImageUploaderUtility iuu = new ImageUploaderUtility();	
						final Image image = iuu.getImageFromData((byte[])event.getItem().getItemProperty(Constants.PICTURE).getValue());
						image.setWidth(Constants.SIZE_400PX);
						panel.setContent(image);
					} else {
						final Image image = new Image(null,new ThemeResource(Constants.DEFAULT_PROFILE_PICTURE));
						image.setWidth(Constants.SIZE_400PX);
						panel.setContent(image);
					}
					window.setResizable(false);
					window.center();
					window.setContent(panel);
					UI.getCurrent().addWindow(window);
				}
			}
		};
		return listener;
	}
	
	/**
	 * Kereső listener
	 * @return Kereső ClickListener
	 */
	private ClickListener getSearchClickListener(){
		return new ClickListener() {

			private static final long serialVersionUID = 5316425090490307717L;

			@Override
			public void buttonClick(final ClickEvent event) {
				jpaContainer.removeAllContainerFilters();
				final Filter[] filterList = new Filter[table.getVisibleColumns().length];
				for(int i=Constants.CONST_0;i<table.getVisibleColumns().length;++i){
					filterList[i] = new Like(table.getVisibleColumns()[i],Constants.SEARCH_PERCENT+searchField.getValue()+Constants.SEARCH_PERCENT,false);
				}
				filter = new Or(filterList);
				jpaContainer.addContainerFilter(filter);
				jpaContainer.addContainerFilter(deletedFilter);
			}
		};
	}
	
	/**
	 * Export kinézetének beállítása
	 * @param rows Sorok száma
	 * @param columns Oszlopok száma
	 */
	private void setExportStyle(final int rows, final int columns) {		
        for (int i=Constants.CONST_1; i<=rows;++i){
        	for(int j=Constants.CONST_0;j<columns;++j){
        		setCellStyle(i,j,CellStyle.BORDER_THIN);
        	}
		}
        for(int j=Constants.CONST_0;j<columns;++j){
        	excelExport.getWorkbook().getSheet(title).setColumnWidth(j, Constants.TABLE_WIDTH/columns);  		
    	}	
        setCellStyle(Constants.CONST_0,Constants.CONST_0,CellStyle.BORDER_THIN);
	}
	
	/**
	 * Cella kinézetének beállítása
	 * @param i Sorazonosító
	 * @param j Oszlopazonosító
	 * @param style Szegély stílusa
	 */
	private void setCellStyle(final int i, final int j, final short style){
		final CellStyle cs = excelExport.getWorkbook().getSheet(title).getRow(i).getCell(j).getCellStyle();
		cs.setBorderTop(style);
        cs.setBorderLeft(style);
        cs.setBorderRight(style);
        cs.setBorderBottom(style); 
		cs.setWrapText(false);
		excelExport.getWorkbook().getSheet(title).getRow(i).getCell(j).setCellStyle(cs);
	}

	/**
	 * Beállítja a layouton a szökséges ExpandRatio-kat
	 * @param titleGrid A beállítandó GridLayout
	 */
	private void setLayoutExpandRatio(final GridLayout titleGrid){
		titleGrid.setColumnExpandRatio(Constants.CONST_0, Constants.CONST_1);
		titleGrid.setColumnExpandRatio(Constants.CONST_1, Constants.CONST_0);
		titleGrid.setColumnExpandRatio(Constants.CONST_2, Constants.CONST_0);
		titleGrid.setColumnExpandRatio(Constants.CONST_3, Constants.CONST_0);
		titleGrid.setColumnExpandRatio(Constants.CONST_4, Constants.CONST_0);
		titleGrid.setColumnExpandRatio(Constants.CONST_5, Constants.CONST_0);
		titleGrid.setColumnExpandRatio(Constants.CONST_6, Constants.CONST_0);
		titleGrid.setColumnExpandRatio(Constants.CONST_7, Constants.CONST_0);
	}
	
	/**
	 * Oszlopok neveinek és sorrendjének beállítása
	 * @param table A táblázat, amihez beállítjuk az oszlopokat
	 */
	private void setTableColumns(){
		if (containerClass.isAssignableFrom(User.class)){
			table.setVisibleColumns(new Object[]{Constants.COLUMN_USERNAME,Constants.COLUMN_ADMIN,Constants.STATISTICS});
			table.setColumnHeaders(Constants.COLUMN_USER_NAME_CAPTION,Constants.COLUMN_ADMIN_CAPTION,Constants.COLUMN_STATISTICS_CAPTION);
			table.setSortContainerPropertyId(Constants.COLUMN_USERNAME);
			setCheckboxForColumn(Constants.COLUMN_ADMIN,Constants.STATISTICS);
			table.setSortAscending(true);
		}
		if (containerClass.isAssignableFrom(Customer.class)){
			table.setVisibleColumns(new Object[]{Constants.NAME,Constants.COLUMN_COUNTRY,Constants.COLUMN_CITY,Constants.COLUMN_STREET,Constants.COLUMN_HOUSE,Constants.COLUMN_PHONENUMBER});
			table.setColumnHeaders(Constants.COLUMN_CUSTOMER_NAME_CAPTION,Constants.COLUMN_COUNTRY_CAPTION,Constants.COLUMN_CITY_CAPTION,Constants.COLUMN_STREET_CAPTION,Constants.COLUMN_HOUSE_CAPTION,Constants.COLUMN_CUSTOMER_PHONENUMBER_CAPTION);
			table.setSortContainerPropertyId(Constants.NAME);
			table.setSortAscending(true);
		}
		if (containerClass.isAssignableFrom(Supplier.class)){
			table.setVisibleColumns(new Object[]{Constants.NAME,Constants.COLUMN_COUNTRY,Constants.COLUMN_CITY,Constants.COLUMN_STREET,Constants.COLUMN_HOUSE,Constants.COLUMN_PHONENUMBER});
			table.setColumnHeaders(Constants.COLUMN_SUPPLIER_NAME_CAPTION,Constants.COLUMN_COUNTRY_CAPTION,Constants.COLUMN_CITY_CAPTION,Constants.COLUMN_STREET_CAPTION,Constants.COLUMN_HOUSE_CAPTION,Constants.COLUMN_SUPPLIER_PHONENUMBER_CAPTION);
			table.setSortContainerPropertyId(Constants.NAME);
			table.setSortAscending(true);
		}
		if (containerClass.isAssignableFrom(Storage.class)){
			table.setVisibleColumns(new Object[]{Constants.STORAGENAME});
			table.setColumnHeaders(Constants.COLUMN_STORAGE_NAME_CAPTION);
			table.setSortContainerPropertyId(Constants.STORAGENAME);
			table.setSortAscending(true);
		}
		if (containerClass.isAssignableFrom(Import.class)){
			table.setVisibleColumns(new Object[]{Constants.COLUMN_TIMESTAMP,Constants.COLUMN_SUCCESS,Constants.COLUMN_WARE,Constants.COLUMN_SUPPLIER, Constants.COLUMN_QUANTITY});
			table.setColumnHeaders(Constants.COLUMN_TIMESTAMP_CAPTION,Constants.COLUMN_SUCCESS_CAPTION,Constants.COLUMN_WARE_CAPTION,Constants.COLUMN_SUPPLIER_CAPTION,Constants.COLUMN_QUANTITY_CAPTION);
			table.setSortContainerPropertyId(Constants.COLUMN_TIMESTAMP);
			setCheckboxForColumn(Constants.COLUMN_SUCCESS);
			table.setSortAscending(true);
		}
		if (containerClass.isAssignableFrom(Export.class)){
			table.setVisibleColumns(new Object[]{Constants.COLUMN_TIMESTAMP,Constants.COLUMN_SUCCESS,Constants.COLUMN_WARE,Constants.COLUMN_CUSTOMER, Constants.COLUMN_QUANTITY});
			table.setColumnHeaders(Constants.COLUMN_TIMESTAMP_CAPTION,Constants.COLUMN_SUCCESS_CAPTION,Constants.COLUMN_WARE_CAPTION,Constants.COLUMN_CUSTOMER_CAPTION,Constants.COLUMN_QUANTITY_CAPTION);
			table.setSortContainerPropertyId(Constants.COLUMN_TIMESTAMP);
			setCheckboxForColumn(Constants.COLUMN_SUCCESS);
			table.setSortAscending(true);
		}	
		if (containerClass.isAssignableFrom(Ware.class)){
			table.setVisibleColumns(new Object[]{Constants.NAME,Constants.COLUMN_CATEGORY,Constants.COLUMN_STORAGE,Constants.COLUMN_SERIAL,Constants.COLUMN_QUANTITY});
			table.setColumnHeaders(Constants.COLUMN_WARE_NAME_CAPTION,Constants.COLUMN_CATEGORY_CAPTION,Constants.COLUMN_STORAGE_CAPTION,Constants.COLUMN_SERIAL_CAPTION,Constants.COLUMN_QUANTITY_CAPTION);
			table.setSortContainerPropertyId(Constants.NAME);
			table.setSortAscending(true);
		}
		if (containerClass.isAssignableFrom(Statistic.class)){
			table.setVisibleColumns(new Object[]{Constants.NAME,Constants.COLUMN_STAT});
			table.setColumnHeaders(Constants.EMPTY_STRING,Constants.EMPTY_STRING);
			table.setSortEnabled(false);
		}
		if (containerClass.isAssignableFrom(Log.class)){
			table.setVisibleColumns(new Object[]{Constants.USER,Constants.COLUMN_ACTION,Constants.COLUMN_TYPEID,Constants.COLUMN_TYPE,Constants.COLUMN_TIMESTAMP});
			table.setColumnHeaders(Constants.COLUMN_USER_NAME_CAPTION,Constants.COLUMN_ACTION_CAPTION,Constants.COLUMN_TYPEID_CAPTION,Constants.COLUMN_TYPE_CAPTION,Constants.COLUMN_TIMESTAMP_CAPTION);
			table.setSortContainerPropertyId(Constants.COLUMN_TIMESTAMP);
			table.setSortAscending(false);
		}		
	}
	
	/**
	 * Gombok stílusának beállítása
	 * @param button A gomb, amire a stílust szeretnénk beállítani
	 */
	private void setButtonStyle(final Button button, final String desc){
		button.setWidth(Constants.SIZE_60PX);
		button.setHeight(Constants.SIZE_60PX);
		button.setStyleName(Constants.STYLE_BTN_NOPADDING_NOBORDER);
		button.setDescription(desc);
	}
	
	/**
	 * Boolean helyett checkboxot megjeleníttető columngenerator
	 * @param columnIds Az oszlopok azonosítói
	 */
	private void setCheckboxForColumn(String... columnIds){
		for (String columnId : columnIds) {
			table.addGeneratedColumn(columnId, new Table.ColumnGenerator() {

				private static final long serialVersionUID = 1L;

				@Override
				public Object generateCell(Table source, Object itemId, Object columnId) {
					Boolean checked = (Boolean) source.getItem(itemId).getItemProperty(columnId).getValue();
					CheckBox checkBox = new CheckBox();
					checkBox.setValue(checked);
					checkBox.setEnabled(false);
					return checkBox;
				}
			});
		}
	}
}
