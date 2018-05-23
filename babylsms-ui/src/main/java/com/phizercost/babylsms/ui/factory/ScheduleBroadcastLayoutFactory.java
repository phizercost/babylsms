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

@SpringView (name=ScheduleBroadcastLayoutFactory.NAME, ui= BabylSMSMainUI.class)
public class ScheduleBroadcastLayoutFactory extends VerticalLayout implements View, ScheduleSavedListener{
	public static final String NAME = "schedule";
	
	@Autowired
	private ScheduleBroadcastMainLayoutFactory scheduleBroadcastMainLayoutFactory;
	
	@Autowired
	private ShowAllScheduleLayoutFactory showAllScheduleLayoutFactory;
	
	private TabSheet tabSheet;
	
	private void addLayout() {
		setMargin(true);
		
		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component scheduleBroadcastMainTab = scheduleBroadcastMainLayoutFactory.createComponent(this);
	    //Component showScheduleTab = showAllScheduleLayoutFactory.createComponent();
	    
	    tabSheet.addTab(scheduleBroadcastMainTab, StringUtils.SCHEDULE_BROADCAST.getString());
	    //tabSheet.addTab(showScheduleTab, StringUtils.SHOW_ALL_SCHEDULES.getString());
	    
	    addComponent(tabSheet);

	}
	public void enter(ViewChangeEvent event){
		//addComponent(new Label ("This is the Broadcast schedule layout"));
		removeAllComponents();
		addLayout();
		
	}
	public void scheduleSaved() {
		showAllScheduleLayoutFactory.refreshTable();
		
	}
	public void scheduleBroadcast() {
		// TODO Auto-generated method stub
		
	}

}

