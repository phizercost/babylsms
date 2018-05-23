package com.phizercost.babylsms.ui.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.ui.commons.BabylSMSMainUI;
import com.phizercost.babylsms.utils.LoadFileUtils;
import com.phizercost.babylsms.utils.StringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;

@SpringView (name=LoadFileLayoutFactory.NAME, ui= BabylSMSMainUI.class)
public class LoadFileLayoutFactory extends VerticalLayout implements View, FileLoadedListener{
	public static final String NAME = "load";
	
	@Autowired
	private LoadFileMainLayoutFactory loadFileMainLayoutFactory;
	
	@Autowired
	private ShowAllFilesLayoutFactory showAllFilesLayoutFactory;
	
	@Autowired
	private ShowAllCustomersLayoutFactory showAllCustomersLayoutFactory;
	
	
	private TabSheet tabSheet;
	
	public void enter(ViewChangeEvent event){
		removeAllComponents();
		addLayout();
		//addComponent(new Label ("This is the Load File layout"));
	}
	
	private void addLayout(){
		setMargin(true);
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component loadFileMainTab = loadFileMainLayoutFactory.createComponent(this);
		Component showAllFilesTab = showAllFilesLayoutFactory.createComponent();
		Component showCustomersTab = showAllCustomersLayoutFactory.createComponent();
		
		tabSheet.addTab(loadFileMainTab, LoadFileUtils.LOAD_FILE.getString());
		tabSheet.addTab(showAllFilesTab, LoadFileUtils.SHOW_LOADED_FILES.getString());
		tabSheet.addTab(showCustomersTab, LoadFileUtils.SHOW_LOADED_CUSTOMERS.getString());
		
		 addComponent(tabSheet);
	}

	public void fileLoaded() {
		showAllFilesLayoutFactory.refreshTable();
		showAllCustomersLayoutFactory.refreshTable();
	}

}



 