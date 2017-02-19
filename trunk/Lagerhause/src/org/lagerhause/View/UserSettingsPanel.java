package org.lagerhause.View;

import javax.persistence.EntityManager;

import org.lagerhause.Model.Classes.User;
import org.lagerhause.Model.Services.CreateService;
import org.lagerhause.Model.Services.LogService;
import org.lagerhause.Model.Services.LoginService;
import org.lagerhause.Model.Services.UserService;
import org.lagerhause.View.Constants.Constants;
import org.lagerhause.View.Util.ImageUploaderUtility;

import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

/**
 * Felhasználó beállításait tartalmazó UI (jelszóváltó és profilkép feltöltő)
 * @author Pilán Ádám György, Tömördi Péter
 *
 */
public class UserSettingsPanel extends Panel implements View {
	//-------------------------------------------------------------
	private static final long serialVersionUID = 4928750315712307165L;
	//-------------------------------------------------------------
	private final User loggedUser;
	private Image userImage;
	//-------------------------------------------------------------	
	
	/**
	 * Konstruktor
	 * @author Pilán Ádám György
	 */
	public UserSettingsPanel(){
		setCaption(Constants.SETTINGS_CAPTION);
		setStyleName(Constants.STYLE_MAIN_TITLE);
		setSizeFull();
		loggedUser = (User) VaadinSession.getCurrent().getAttribute(Constants.USER);
		final GridLayout mainLayout = new GridLayout(Constants.CONST_2,Constants.CONST_1);
		mainLayout.setColumnExpandRatio(Constants.CONST_0, Constants.CONST_0);
		mainLayout.setColumnExpandRatio(Constants.CONST_1, Constants.CONST_0);
		mainLayout.setDefaultComponentAlignment(Alignment.TOP_CENTER);
		mainLayout.setSizeFull();
		setContent(mainLayout);		
		mainLayout.addComponent(createPictureUploader(),Constants.CONST_0,Constants.CONST_0);	
		mainLayout.addComponent(createPasswordChanger(),Constants.CONST_1,Constants.CONST_0);	
		
	}
	/**
	 * Elkészíti a jelszóváltó panelt
	 * @return A jelszóváltó panel
	 * @author Tömördi Péter
	 */
	
	private Panel createPasswordChanger(){
		final GridLayout passwordChangeLayout = new GridLayout(Constants.CONST_2,Constants.CONST_3);
		final Panel passwordChangePanel = new Panel(passwordChangeLayout);
		passwordChangePanel.setCaption(Constants.PASSWORD_CHANGE_CAPTION);
		passwordChangePanel.addStyleName(Constants.STYLE_MAIN_TITLE);
		passwordChangeLayout.setWidth(Constants.SIZE_445PX);
		passwordChangeLayout.setHeightUndefined();
		passwordChangePanel.setWidth(Constants.SIZE_450PX);
		passwordChangePanel.setHeightUndefined();		
		passwordChangeLayout.setHeight(Constants.PASSWORD_CHANGE_LAYOUT_HEIGHT);
		PasswordField oldPassField = new PasswordField(Constants.OLD_PASSWORD);
		passwordChangeLayout.addComponent(oldPassField, Constants.CONST_0, Constants.CONST_0);
		PasswordField newPassFirst = new PasswordField(Constants.NEW_PASS_TWICE);
		passwordChangeLayout.addComponent(newPassFirst, Constants.CONST_0, Constants.CONST_1);
		PasswordField newPassSecond = new PasswordField(Constants.EMPTY_STRING);
		passwordChangeLayout.addComponent(newPassSecond, Constants.CONST_1 , Constants.CONST_1);
		Button submit = new Button(Constants.SAVE_BUTTON_CAPTION);
		passwordChangeLayout.addComponent(submit, Constants.CONST_0, Constants.CONST_2);
		passwordChangeLayout.setMargin(new MarginInfo(false, false, false, true));
		submit.addClickListener(submit(oldPassField, newPassFirst, newPassSecond ));
			return passwordChangePanel;
		}
	
		private ClickListener submit (PasswordField oldPassField, PasswordField newPassFirst, PasswordField newPassSecond) {
			return new ClickListener(){


				private static final long serialVersionUID = -7292624747068979285L;

				@Override
				public void buttonClick(final ClickEvent event) {
					boolean oldPassOK = false;
					boolean changeSuccess = false;
					String oldPass = oldPassField.getValue();
					oldPass = LoginService.hashing(oldPass);
					if (oldPass.equals(loggedUser.getPassword())) {
						oldPassOK = true;						
					} else {
						new Notification(Constants.ERROR,Constants.WRONG_OLD_PASS_NOTIFICATION,Type.ERROR_MESSAGE).show(Page.getCurrent());
						oldPassOK = false;
					}
					if (((newPassFirst.getValue()).equals(newPassSecond.getValue())) && (oldPassOK == true)) {
						if (newPassFirst.getValue().equals(Constants.EMPTY_STRING)) {
							new Notification(Constants.ERROR,Constants.NEW_PASS_MISSING,Type.ERROR_MESSAGE).show(Page.getCurrent());
							newPassFirst.addValidator(new StringLengthValidator(
								    Constants.NEW_PASS_EMPTY,
								    Constants.CONST_1, Constants.CONST_10, true));
							newPassSecond.addValidator(new StringLengthValidator(
								    Constants.NEW_PASS_EMPTY,
								    Constants.CONST_1, Constants.CONST_10, true));
						} else {
							changeSuccess = UserService.changePassword(loggedUser.getUserName(), newPassFirst.getValue());
							if (changeSuccess) {
								new Notification(Constants.PASSWORD_CHANGE_CAPTION, Constants.PASSWORD_CHANGE_SUCCESS_NOTIFICATION).show(Page.getCurrent());								
							} else {
								new Notification(Constants.PASSWORD_CHANGE_CAPTION, Constants.PASSWORD_CHANGE_FAILED_NOTIFICATION, Type.ERROR_MESSAGE).show(Page.getCurrent());;
							}
							newPassFirst.removeAllValidators();
							newPassSecond.removeAllValidators();
						}
					} else if (oldPassOK == true){
						new Notification(Constants.ERROR,Constants.NEW_PASS_NOT_EQUAL_NOTIFICATION,Type.ERROR_MESSAGE).show(Page.getCurrent());
					}
					oldPassField.setValue(Constants.EMPTY_STRING);
					newPassFirst.setValue(Constants.EMPTY_STRING);
					newPassSecond.setValue(Constants.EMPTY_STRING);
				}};
		}
	
	/**
	 * Elkészíti a képfeltöltő panelt
	 * @return A képfeltöltő panel
	 * @author Pilán Ádám György
	 */
	private Panel createPictureUploader(){
        final ImageUploaderUtility receiver = new ImageUploaderUtility();
		final GridLayout pictureChangeLayout = new GridLayout(Constants.CONST_1,Constants.CONST_2);
		final Panel pictureChangePanel = new Panel(pictureChangeLayout);
		pictureChangePanel.setCaption(Constants.EDIT_PICTURE_CAPTION);
		pictureChangePanel.addStyleName(Constants.STYLE_MAIN_TITLE);
		pictureChangeLayout.setWidth(Constants.SIZE_395PX);
		pictureChangePanel.setWidth(Constants.SIZE_400PX);
		pictureChangePanel.setHeightUndefined();
		if (loggedUser.getPicture()!=null){
			userImage = new Image(null);
		 	userImage.setSource(receiver.getImageFromData(loggedUser.getPicture()).getSource());
		} else {
			userImage = new Image(null ,new ThemeResource(Constants.DEFAULT_PROFILE_PICTURE));
		}
        userImage.setWidth(Constants.SIZE_300PX);
        userImage.setHeightUndefined();
        final HorizontalLayout imageLayout = new HorizontalLayout(userImage);
        imageLayout.setWidth(Constants.SIZE_400PX);
        imageLayout.setComponentAlignment(userImage, Alignment.BOTTOM_CENTER);
        final Upload upload = new Upload(Constants.UPLOAD_PICTURE_CAPTION, receiver);
        upload.setButtonCaption(Constants.UPLOAD_CAPTION);
        upload.addSucceededListener(receiver);
        upload.addSucceededListener(new SucceededListener() {
			private static final long serialVersionUID = 2362364094871896300L;

			@Override
			public void uploadSucceeded(final SucceededEvent event) {
				if (receiver.isUploadSuccessful()){
					userImage.setSource(receiver.getImageFromData(receiver.getData()).getSource());
					userImage.setWidth(Constants.SIZE_300PX);
					userImage.setHeightUndefined();
					((LagerhauseUI)UI.getCurrent()).refreshUserPic(receiver.getData());
					final EntityManager entityManager = CreateService.createEntityManager();
					entityManager.getTransaction().begin();
					final User user = entityManager.find(User.class, loggedUser.getUserName());
					user.setPicture(receiver.getData());
					entityManager.getTransaction().commit();
					LogService.AddLogEntry(Constants.USERPICCHANGED, user, User.class);
				}
			}
		});
        final HorizontalLayout uploadLayout = new HorizontalLayout(upload);
        uploadLayout.setWidth(Constants.SIZE_395PX);
        uploadLayout.setMargin(new MarginInfo(true,false,true,false));
        uploadLayout.setComponentAlignment(upload, Alignment.MIDDLE_CENTER);
        pictureChangeLayout.addComponent(imageLayout,Constants.CONST_0,Constants.CONST_0);
        pictureChangeLayout.addComponent(uploadLayout,Constants.CONST_0,Constants.CONST_1);
        pictureChangeLayout.setComponentAlignment(uploadLayout, Alignment.TOP_CENTER);
		return pictureChangePanel;
	}
	
	@Override
	public void enter(final ViewChangeEvent event) {
		
	}	
}