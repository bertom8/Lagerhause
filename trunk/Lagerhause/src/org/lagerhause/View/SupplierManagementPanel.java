package org.lagerhause.View;

import org.lagerhause.Model.Classes.Supplier;
import org.lagerhause.View.Constants.Constants;
import org.lagerhause.View.Util.TableCreatorUtility;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;

/**
 * A beszállítók kezelésére alkalmas panel
 * @author Pilán Ádám György
 *
 */
public class SupplierManagementPanel extends Panel implements View {
	//-------------------------------------------------------------
	private static final long serialVersionUID = 4928750315712307164L;
	//-------------------------------------------------------------
	//-------------------------------------------------------------
	
	/**
	 * Konstruktor
	 */
	public SupplierManagementPanel(){
		setCaption(Constants.SUPPLIER_MANAGEMENT_CAPTION);
		setStyleName(Constants.STYLE_MAIN_TITLE);
		setSizeFull();
		final TableCreatorUtility tcu = new TableCreatorUtility(Constants.SUPPLIER_MANAGEMENT_TITLE,Supplier.class);
		final GridLayout layout = tcu.createTableLayout();
		setContent(layout);
	}
	
	@Override
	public void enter(final ViewChangeEvent event) {
		
	}	
}
