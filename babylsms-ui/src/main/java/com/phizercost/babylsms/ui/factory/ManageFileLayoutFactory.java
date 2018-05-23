package com.phizercost.babylsms.ui.factory;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.customer.Customer;
import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.service.removefile.RemoveFileService;
import com.phizercost.babylsms.service.showallfiles.ShowAllFilesService;
import com.phizercost.babylsms.service.showcustomersbyfile.ShowCustomersByFileService;
import com.phizercost.babylsms.ui.commons.BabylSMSMainUI;
import com.phizercost.babylsms.utils.DownloadFile;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name = ManageFileLayoutFactory.NAME, ui = BabylSMSMainUI.class)
public class ManageFileLayoutFactory extends VerticalLayout implements View, FileLoadedListener, Button.ClickListener {

	public static final String NAME = "manage";
	private Grid manageFileTable;
	private Button removeFilesButton;
	private Button downloadFilesButton;
	private Button archiveFilesButton;
	private List<FileObject> files;
	private List<Customer> customers;

	@Autowired
	private ShowAllFilesService allFilesService;

	@Autowired
	private RemoveFileService removeFileService;

	@Autowired
	private ShowAllFilesLayoutFactory showAllFilesLayoutFactory;

	@Autowired
	private ShowAllCustomersLayoutFactory showAllCustomersLayoutFactory;

	private void addLayout() {

		removeFilesButton = new Button(NotificationMessages.FILE_REMOVE_BUTTON.getString());
		downloadFilesButton = new Button(NotificationMessages.FILE_DOWNLOAD_BUTTON.getString());
		archiveFilesButton = new Button(NotificationMessages.FILE_ARCHIVE_BUTTON.getString());
		setMargin(true);
		
		GridLayout gridLayout = new GridLayout(3, 4);
		gridLayout.setSizeUndefined();
		gridLayout.setSpacing(true);
		
		BeanItemContainer<FileObject> container = new BeanItemContainer<FileObject>(FileObject.class, files);

		manageFileTable = new Grid(container);
		manageFileTable.setColumnOrder("fileName");
		manageFileTable.removeColumn("id");
		manageFileTable.removeColumn("status");
		manageFileTable.setImmediate(true);
		manageFileTable.setHeight("100%");
		manageFileTable.setWidth("100%");
		manageFileTable.setSelectionMode(SelectionMode.MULTI);

		removeFilesButton.addClickListener(this);
		downloadFilesButton.addClickListener(this);
		archiveFilesButton.addClickListener(this);
		
		addComponent(manageFileTable);
		addComponent(new HorizontalLayout(downloadFilesButton, archiveFilesButton, removeFilesButton));
		//gridLayout.addComponent(removeFilesButton,0,2);
		
		

	}

	private void loadFiles() {
		files = allFilesService.getAllFiles();
	}

	public void buttonClick(ClickEvent event) {

		if (event.getSource() == this.removeFilesButton) {
			MultiSelectionModel selectionModel = (MultiSelectionModel) manageFileTable.getSelectionModel();

			for (Object selectedItem : selectionModel.getSelectedRows()) {
				FileObject file = (FileObject) selectedItem;
				manageFileTable.getContainerDataSource().removeItem(file);
				removeFileService.removeFile(file);
			}

			Notification.show(NotificationMessages.REMOVE_REMOVE_SUCCESS_TITLE.getString(),
					NotificationMessages.REMOVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);

			manageFileTable.getSelectionModel().reset();
		} else if (event.getSource() == this.downloadFilesButton) {
			MultiSelectionModel selectionModel = (MultiSelectionModel) manageFileTable.getSelectionModel();
			for (Object selectedItem : selectionModel.getSelectedRows()) {
				FileObject file = (FileObject) selectedItem;
				customers = getCustomerList(file.getFileName());
				ArrayList<String> array = new ArrayList<String>();
				for (int i = 0; i < customers.size(); i++) {
					Customer customer = customers.get(i);
					
					array.add(customer.getPhoneNumber() + "|" + customer.getDob() + "|" + customer.getGender() + "|"
							+ customer.getRegistrationDate());
				}
				try {
					DownloadFile.download(array, file.getFileName());
				} catch (IOException e) {
					Notification.show(NotificationMessages.DOWNLOAD_ERROR_TITLE.getString(),
							NotificationMessages.DOWNLOAD_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
					e.printStackTrace();
				}
				
			}
			Notification.show(NotificationMessages.DOWNLOAD_SUCCESS_TITLE.getString(),
					NotificationMessages.DOWNLOAD_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);

		} else if (event.getSource() == this.archiveFilesButton) {
			MultiSelectionModel selectionModel = (MultiSelectionModel) manageFileTable.getSelectionModel();
			for (Object selectedItem : selectionModel.getSelectedRows()) {
				FileObject file = (FileObject) selectedItem;
				archiveFile(file.getId());
				
			}
			Notification.show(NotificationMessages.DOWNLOAD_ARCHIVE_TITLE.getString(),
					NotificationMessages.DOWNLOAD_ARCHIVE_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
			manageFileTable.getSelectionModel().reset();
		}
	}

	private void archiveFile(Integer id) {
		Connection conn = null;
		Statement stmt = null;


		String sqlQuery = "update file set status = 2 where id = " + id ;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/babylsms?user=root&password=patmos123!");
			stmt = conn.createStatement();
			stmt.executeUpdate(sqlQuery);
			stmt.close();
			conn.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception ex) {
				}
				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
				}
				conn = null;
			}
		}
	}

	public void enter(ViewChangeEvent event) {

		if (manageFileTable != null)
			return;

		loadFiles();
		addLayout();
	}

	public void fileLoaded() {
		showAllFilesLayoutFactory.refreshTable();
		showAllCustomersLayoutFactory.refreshTable();

	}

	private ArrayList<Customer> getCustomerList(String fileName) {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		ArrayList<Customer> records = new ArrayList<Customer>();
		String sqlQuery = "select * from customer where file_name = '" + fileName + "'";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/babylsms?user=root&password=patmos123!");
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sqlQuery);

			while (rs.next()) {
				Customer customer = new Customer(rs.getString("phone_number"), rs.getString("registration_date"),rs.getString("dob"), rs.getString("gender"), rs.getString("file_name"));
				records.add(customer);
			}

			stmt.close();
			rs.close();
			conn.close();

			return records;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (Exception ex) {
				}
				rs = null;
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (Exception ex) {
				}
				stmt = null;
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (Exception ex) {
				}
				conn = null;
			}
		}
		return null;

	}

}
