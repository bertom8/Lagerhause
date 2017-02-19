package org.lagerhause.View.Util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.lagerhause.View.Constants.Constants;

import com.vaadin.server.Page;
import com.vaadin.server.StreamResource;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;

/**
 * Képfeltöltő osztály
 * 
 * @author Pilán Ádám György
 *
 */
public class ImageUploaderUtility implements Receiver, SucceededListener {
	// -----------------------------------------------------------------------
	private static final long serialVersionUID = 4611160666149149923L;
	// -----------------------------------------------------------------------
	private File file;
	private byte[] data;
	private Image image;
	private boolean success;
	private FileOutputStream fileOutputStream;
	// -----------------------------------------------------------------------
	
	@Override
	public OutputStream receiveUpload(final String filename, final String mimeType) {
		try {
			if (Constants.EMPTY_STRING.equals(filename)) {
				file = new File(Constants.DEFAULT_FILE);
				fileOutputStream = new FileOutputStream(file);
				success = false;
				new Notification(Constants.UPLOAD_ERROR_NOTIFICATION, Constants.UPLOAD_ERROR_NOTIFICATION_NOFILE, Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
			} else {
				file = new File(Constants.FILE_LOCATION + filename);
				fileOutputStream = new FileOutputStream(file);
				if (!mimeType.contains(Constants.TARGET_MIMETYPE)) {
					success = false;
					new Notification(Constants.UPLOAD_ERROR_NOTIFICATION, Constants.UPLOAD_ERROR_NOTIFICATION_NOJPEG, Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
				} else {
					success = true;
				}
			}
		} catch (final FileNotFoundException e) {
			new Notification(Constants.FILE_NOT_FOUND_EXCEPTION).show(Page.getCurrent());
		}
		return fileOutputStream;
	}

	@Override
	public void uploadSucceeded(final SucceededEvent event) {
		if (success) {
			FileInputStream fileInputStream;
			try {
				if (file.length() > Constants.MAX_FILE_SIZE) {
					success = false;
					new Notification(Constants.UPLOAD_ERROR_NOTIFICATION, Constants.UPLOAD_ERROR_NOTIFICATION_EXCEEDED_FILESIZE, Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
				}
				data = new byte[(int) file.length()];
				fileInputStream = new FileInputStream(file);
				fileInputStream.read(data);
				fileInputStream.close();
				fileOutputStream.close();
				file.delete();
			} catch (final FileNotFoundException e) {
				success = false;
				new Notification(Constants.UPLOAD_ERROR_NOTIFICATION, Constants.FILE_NOT_FOUND_EXCEPTION, Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
			} catch (final IOException e) {
				success = false;
				new Notification(Constants.UPLOAD_ERROR_NOTIFICATION, Constants.UPLOAD_ERROR_NOTIFICATION_IO, Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
			}
		} else {
			try {
				fileOutputStream.close();
			} catch (final IOException e) {
				new Notification(Constants.UPLOAD_ERROR_NOTIFICATION, Constants.UPLOAD_ERROR_NOTIFICATION_IO, Notification.Type.WARNING_MESSAGE).show(Page.getCurrent());
			}
		}
	}

	/**
	 * A sikerességet tároló változó gettere
	 * 
	 * @return Igaz, ha sikeres upload. Hamis, ha sikertelen.
	 */
	public boolean isUploadSuccessful() {
		return success;
	}
	
	/**
	 * A feltöltött képet byte tömbben visszaadja
	 * @return A képi adat, mint byte tömb
	 */
	public byte[] getData(){
		return data;
	}
	
	/**
	 * Leképez egy byte tömböt kép elementté
	 * @return A betöltött kép element
	 */
	public Image getImageFromData(final byte[] data) {
		image = new Image();
		final StreamResource.StreamSource imageSource = new StreamResource.StreamSource() {
			private static final long serialVersionUID = 1234365234245L;

			@Override
			public InputStream getStream() {
				return new ByteArrayInputStream(data);
			}
		};
		final StreamResource imageResource = new StreamResource(imageSource, Constants.TEMP_IMAGE_FILENAME);
		imageResource.setCacheTime(Constants.CONST_0);
		image.setSource(imageResource);
		return image;
	}
}
