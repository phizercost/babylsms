package com.phizercost.babylsms.ui.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.ui.commons.BabylSMSMainUI;
import com.phizercost.babylsms.utils.StringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView (name=UserManagementLayoutFactory.NAME, ui= BabylSMSMainUI.class)
public class UserManagementLayoutFactory extends VerticalLayout implements View, UserSavedListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "users";
	
	@Autowired
	private UserManagementMainLayoutFactory userManagementMainLayoutFactory;
	
	@Autowired
	private ShowAllUsersLayoutFactory showAllUsersLayoutFactory;
	
	private TabSheet tabSheet;
	
	
	private void addLayout() {
		setMargin(true);
		
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component saveUserMainTab = userManagementMainLayoutFactory.createComponent(this);
	    Component showUsersTab = showAllUsersLayoutFactory.createComponent();
	    
	    tabSheet.addTab(saveUserMainTab, StringUtils.USER_TITLE.getString());
	    tabSheet.addTab(showUsersTab, StringUtils.SHOW_ALL_USERS.getString());
	    
	    addComponent(tabSheet);

	}
	

	public void userSaved() {
		showAllUsersLayoutFactory.refreshTable();
		
	}

	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
		
	}

}
