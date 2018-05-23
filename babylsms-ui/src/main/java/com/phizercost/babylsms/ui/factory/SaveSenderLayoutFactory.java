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

@SpringView (name=SaveSenderLayoutFactory.NAME, ui= BabylSMSMainUI.class)
public class SaveSenderLayoutFactory extends VerticalLayout implements View, SenderSavedListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String NAME = "senders";
	
	@Autowired
	private SaveSenderMainLayoutFactory saveSenderMainLayoutFactory;
	
	@Autowired
	private ShowAllSendersLayoutFactory showAllSendersLayoutFactory;
	
	
	private TabSheet tabSheet;
	
	private void addLayout() {
		setMargin(true);
		
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component saveSenderMainTab = saveSenderMainLayoutFactory.createComponent(this);
	    Component showSendersTab = showAllSendersLayoutFactory.createComponent();
	    
	    tabSheet.addTab(saveSenderMainTab, StringUtils.SENDER_TITLE.getString());
	    tabSheet.addTab(showSendersTab, StringUtils.SHOW_ALL_SENDERS.getString());
	    
	    addComponent(tabSheet);

	}
	

	public void senderSaved() {
	 
		showAllSendersLayoutFactory.refreshTable();
		 
	}

	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		removeAllComponents();
		addLayout();
	}

}
