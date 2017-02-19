package org.lagerhause.View;

import org.lagerhause.Model.Classes.Log;
import org.lagerhause.View.Constants.Constants;
import org.lagerhause.View.Util.TableCreatorUtility;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;

/**
 * A statisztikák kezelésére alkalmas panel
 * @author Pilán Ádám György
 *
 */
public class LogPanel extends Panel implements View {
	//-------------------------------------------------------------
	private static final long serialVersionUID = -1199306186547549975L;
	//-------------------------------------------------------------
	//-------------------------------------------------------------
	
	/**
	 * Konstruktor
	 */
	public LogPanel(){
		setCaption(Constants.LOGS_CAPTION);
		setStyleName(Constants.STYLE_MAIN_TITLE);	
		setSizeFull();
		final TableCreatorUtility tcu = new TableCreatorUtility(Constants.LOGS_CAPTION, Log.class);
		final GridLayout layout = tcu.createTableLayout();
		setContent(layout);
	}
	
	@Override
	public void enter(final ViewChangeEvent event) {
		
	}	
}
