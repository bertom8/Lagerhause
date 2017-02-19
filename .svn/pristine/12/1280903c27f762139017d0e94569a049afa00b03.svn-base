package org.lagerhause.View;


import org.lagerhause.View.Constants.Constants;
import org.lagerhause.View.Util.TableCreatorUtility;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;

import org.lagerhause.Model.Classes.User;

/**
 * Felhasználókezelő UI felület
 * @author Tömördi Péter
 *
 */

public class UserManagementPanel extends Panel implements View {
	//-------------------------------------------------------------
	private static final long serialVersionUID = 4928750315712307165L;
	//-------------------------------------------------------------
	//-------------------------------------------------------------
	
	/**
	 * Konstruktor
	 */
	public UserManagementPanel(){
		setCaption(Constants.USER_MANAGEMENT_CAPTION);
		setStyleName(Constants.STYLE_MAIN_TITLE);
		setSizeFull();
		final TableCreatorUtility tcu = new TableCreatorUtility(Constants.USER_MANAGEMENT_TITLE,User.class);
		final GridLayout layout = tcu.createTableLayout();
		setContent(layout);
	}
	
	@Override
	public void enter(final ViewChangeEvent event) {
		
	}	
}
