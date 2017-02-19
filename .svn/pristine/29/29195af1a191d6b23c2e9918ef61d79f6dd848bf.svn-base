package org.lagerhause.View;

import org.lagerhause.View.Constants.Constants;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

/**
 * Vaadin ValoMenuLayout kibővítve
 * @author Pilán Ádám György
 */
public class ValoMenuLayout extends HorizontalLayout {
	//-------------------------------------------------------------
	private static final long serialVersionUID = 4951619247082860026L;
	//-------------------------------------------------------------
	private CssLayout contentArea = new CssLayout();
    private CssLayout menuArea = new CssLayout();
    //-------------------------------------------------------------
    
    /**
     * Konstruktor
     */
    public ValoMenuLayout() {
        setSizeFull();
        menuArea.setPrimaryStyleName(Constants.STYLE_VALO_MENU);
        contentArea.setPrimaryStyleName(Constants.STYLE_VALO_CONTENT);
        contentArea.addStyleName(Constants.STYLE_VSCROLLABLE);
        contentArea.setSizeFull();
        addComponents(menuArea, contentArea);
        setExpandRatio(contentArea, Constants.CONST_1);
    }

    /**
     * contentArea cast-olt getter
     * @return (ComponentContainer) contentArea
     */
    public ComponentContainer getContentContainer() {
        return contentArea;
    }

    /**
     * Menüpont hozzáadó metódus
     * @param menu A hozzáadandó menüpont
     */
    public void addMenu(final Component menu) {
        menu.addStyleName(Constants.STYLE_VALO_MENU_PART);
        menuArea.addComponent(menu);
    }

    /**
     * contentArea getter
     * @return contentArea
     */
    public CssLayout getContentArea() {
        return contentArea;
    }

    /**
     * contentArea setter
     * @param contentArea
     */
    public void setContentArea(final CssLayout contentArea) {
        this.contentArea = contentArea;
    }

    /**
     * menuArea getter
     * @return menuArea
     */
    public CssLayout getMenuArea() {
        return menuArea;
    }

    /**
     * menuArea setter
     * @param menuArea
     */
    public void setMenuArea(final CssLayout menuArea) {
        this.menuArea = menuArea;
    }
}
