package com.phizercost.babylsms.ui.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.service.showallfiles.ShowAllFilesService;
import com.phizercost.babylsms.ui.commons.BabylSMSMainUI;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = RetrieveFileLayoutFactory.NAME, ui = BabylSMSMainUI.class)
public class RetrieveFileLayoutFactory extends VerticalLayout
		implements View, FileLoadedListener, Button.ClickListener {
	public static final String NAME = "retrieve";
	private Grid retrieveFileTable;
	private Button retrieveFilesButton;

	private List<FileObject> files;

	@Autowired
	private ShowAllFilesService allFilesService;

	@Autowired
	private ShowAllFilesLayoutFactory showAllFilesLayoutFactory;

	private void addLayout() {

		retrieveFilesButton = new Button(NotificationMessages.FILE_RETRIEVE_BUTTON.getString());

		setMargin(true);

		GridLayout gridLayout = new GridLayout(3, 4);
		gridLayout.setSizeUndefined();
		gridLayout.setSpacing(true);

		BeanItemContainer<FileObject> container = new BeanItemContainer<FileObject>(FileObject.class, files);

		retrieveFileTable = new Grid(container);
		retrieveFileTable.setColumnOrder("fileName");
		retrieveFileTable.removeColumn("id");
		retrieveFileTable.removeColumn("status");
		retrieveFileTable.setImmediate(true);
		retrieveFileTable.setHeight("100%");
		retrieveFileTable.setWidth("100%");
		retrieveFileTable.setSelectionMode(SelectionMode.MULTI);

		retrieveFilesButton.addClickListener(this);

		addComponent(retrieveFileTable);
		addComponent(new HorizontalLayout(retrieveFilesButton));
		// gridLayout.addComponent(removeFilesButton,0,2);

	}

	private void loadFiles() {
		files = allFilesService.getAllFilesToRetrieve();
	}

	public void buttonClick(ClickEvent event) {

		MultiSelectionModel selectionModel = (MultiSelectionModel) retrieveFileTable.getSelectionModel();

		for (Object selectedItem : selectionModel.getSelectedRows()) {
			FileObject file = (FileObject) selectedItem;
			retrieveFileTable.getContainerDataSource().removeItem(file);
			retrieveFile(file.getId());
		}

		Notification.show(NotificationMessages.RETRIEVE_SUCCESS_TITLE.getString(),
				NotificationMessages.RETRIEVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);

		retrieveFileTable.getSelectionModel().reset();

	}

	private void retrieveFile(Integer id) {
		Connection conn = null;
		Statement stmt = null;

		String sqlQuery = "update file set status = 1 where id = " + id;

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

		if (retrieveFileTable != null)
			return;

		loadFiles();
		addLayout();
	}

	public void fileLoaded() {
		showAllFilesLayoutFactory.refreshTable();

	}

}