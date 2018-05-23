package com.phizercost.babylsms.ui.factory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.phizercost.babylsms.model.customer.Customer;
import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.service.loadfile.LoadFileService;
import com.phizercost.babylsms.service.savecustomer.SaveCustomerService;
import com.phizercost.babylsms.utils.BabylSMSUtils;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.Upload;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class LoadFileMainLayoutFactory {

	private class LoadFileMainLayout extends VerticalLayout {
		private Upload upload = null;
		private BeanFieldGroup<FileObject> fieldGroup;
		private FileObject fileObject = null;
		private String fileName;
		protected File tempFile;
		ArrayList<Customer> customers = null;
		FileLoadedListener fileLoadedListener;

		public LoadFileMainLayout(FileLoadedListener fileLoadedListener) {
			this.fileLoadedListener = fileLoadedListener;
		}

		public LoadFileMainLayout init() {
			fieldGroup = new BeanFieldGroup<FileObject>(FileObject.class);
			fileObject = new FileObject();

			upload = new Upload("", new Upload.Receiver() {
				private static final long serialVersionUID = 1L;

				public OutputStream receiveUpload(String filename, String mimeType) {
					try {
						fileName = this.parseFileName(filename);
						tempFile = File.createTempFile("temp", ".csv");
						return new FileOutputStream(tempFile);
					} catch (IOException e) {
						e.printStackTrace();
						return null;
					}
				}

				private String parseFileName(String filename) {
					String MyFileName = "";
					// searching the last position of path delimiter
					int lastPathDivider = filename.lastIndexOf("\\");

					// get the last substring of filename string
					MyFileName = filename.substring(lastPathDivider + 1);

					return MyFileName;
				}
			});
			upload.addFinishedListener(new Upload.FinishedListener() {
				private static final long serialVersionUID = 1L;

				public void uploadFinished(Upload.FinishedEvent finishedEvent) {

					try {

						customers = BabylSMSFileReader.read(tempFile.getAbsolutePath(), fileName);
						customers.size();
						if (customers.size() > 0) {

							if (fileContentChecker(customers)) {
								String currentUserName = "";
								String pattern = "yyyy-MM-dd hh:mm:ss";
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
								String date = simpleDateFormat.format(new Date());

								Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
								if (!(authentication instanceof AnonymousAuthenticationToken))
									currentUserName = authentication.getName();

								fileObject.setFileName(fileName);
								fileObject.setNumberofCustomers(customers.size());
								fileObject.setDate(date);
								fileObject.setOwner(currentUserName);

								try {
									fieldGroup.commit();
									loadFileService.loadFile(fileObject);
									for (int i = 0; i < customers.size(); i++) {
										saveCustomerService.saveCustomer(customers.get(i));
									}

									Notification.show(
											NotificationMessages.FILE_SAVE_VALIDATION_SUCCESS_TITLE.getString(),
											NotificationMessages.FILE_SAVE_VALIDATION_SUCCESS_DESCRIPTION.getString(),
											Type.WARNING_MESSAGE);
									fileLoadedListener.fileLoaded();
								} catch (Exception ex) {
									Notification.show(NotificationMessages.FILE_SAVE_VALIDATION_ERROR_TITLE.getString(),
											NotificationMessages.FILE_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(),
											Type.ERROR_MESSAGE);
								}

							} else {
								Notification.show(NotificationMessages.FILE_SAVE_VALIDATION_ERROR_TITLE.getString(),
										NotificationMessages.FILE_CONTENT_ERROR_DESCRIPTION.getString(),
										Type.ERROR_MESSAGE);
							}

						} else {
							Notification.show(NotificationMessages.FILE_SAVE_VALIDATION_ERROR_TITLE.getString(),
									NotificationMessages.FILE_EMPTY_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
						}
						tempFile.delete();
					} catch (Exception e) {
						Notification.show(NotificationMessages.FILE_SAVE_VALIDATION_ERROR_TITLE.getString(),
								NotificationMessages.FILE_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(),
								Type.ERROR_MESSAGE);
					}

				}
			});
			return this;
		}

		public Component layout() {

			setMargin(true);

			GridLayout gridLayout = new GridLayout(2, 4);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);
			gridLayout.addComponent(upload, 0, 0);

			return gridLayout;
		}

		private boolean fileContentChecker(ArrayList<Customer> content) {
			boolean result = true;
			Customer customer = null;
			if (content.size() > 0) {
				for (int i = 0; i < content.size(); i++) {
					customer = content.get(i);
					if (BabylSMSUtils.isStringEmpty(customer.getPhoneNumber())
							|| !BabylSMSUtils.isMsisdnValid(customer.getPhoneNumber())) {
						result = false;
						break;
					} else if (BabylSMSUtils.isStringEmpty(customer.getRegistrationDate())) {
						result = false;
						break;
					} else if (BabylSMSUtils.isStringEmpty(customer.getGender())) {
						result = false;
						break;
					} else if (BabylSMSUtils.isStringEmpty(customer.getDob())) {
						result = false;
						break;
					}
				}
			} else {
				result = false;
			}
			return result;

		}
	}

	@Autowired
	private LoadFileService loadFileService;

	@Autowired
	private SaveCustomerService saveCustomerService;

	public Component createComponent(FileLoadedListener fileLoadedListener) {
		// return new
		// LoadFileMainLayout(fileLoadedListener).init().bind().layout();
		return new LoadFileMainLayout(fileLoadedListener).init().layout();
	}

}
