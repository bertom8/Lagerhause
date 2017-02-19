package org.lagerhause.View;

import org.lagerhause.Model.Classes.Statistic;
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
public class StatisticsPanel extends Panel implements View {
	//-------------------------------------------------------------
	private static final long serialVersionUID = -1199306186547549975L;
	//-------------------------------------------------------------
	//-------------------------------------------------------------
	
	/**
	 * Konstruktor
	 */
	public StatisticsPanel(){
		setCaption(Constants.STATS_CAPTION);
		setStyleName(Constants.STYLE_MAIN_TITLE);	
		setSizeFull();
		final TableCreatorUtility tcu = new TableCreatorUtility(Constants.STATS_CAPTION, Statistic.class);
		final GridLayout layout = tcu.createStatTableLayout();
		setContent(layout);
	}
	
	@Override
	public void enter(final ViewChangeEvent event) {
		
	}	
}
