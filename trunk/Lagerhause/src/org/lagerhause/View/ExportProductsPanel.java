package org.lagerhause.View;

import org.lagerhause.Model.Classes.Export;
import org.lagerhause.View.Constants.Constants;
import org.lagerhause.View.Util.TableCreatorUtility;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;

/**
 * Az eladások kezelésére alkalmas panel
 * @author Pilán Ádám György
 *
 */
public class ExportProductsPanel extends Panel implements View {
	//-------------------------------------------------------------
	private static final long serialVersionUID = 4928750315712307164L;
	//-------------------------------------------------------------
	//-------------------------------------------------------------
	
	/**
	 * Konstruktor
	 */
	public ExportProductsPanel(){
		setCaption(Constants.EXPORT_PRODUCTS_CAPTION);
		setStyleName(Constants.STYLE_MAIN_TITLE);
		setSizeFull();
		final TableCreatorUtility tcu = new TableCreatorUtility(Constants.EXPORT_PRODUCTS_TITLE,Export.class);
		final GridLayout layout = tcu.createTableLayout();
		setContent(layout);
	}
	
	@Override
	public void enter(final ViewChangeEvent event) {
		
	}	
}
