package com.phizercost.babylsms.ui.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.model.schedule.Schedule;
import com.phizercost.babylsms.service.removeschedule.RemoveScheduleService;

import com.phizercost.babylsms.service.showallschedules.ShowAllSchedulesService;
import com.phizercost.babylsms.ui.commons.BabylSMSMainUI;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

@SpringView(name=ShowAllScheduleLayoutFactory.NAME, ui=BabylSMSMainUI.class)
public class ShowAllScheduleLayoutFactory extends VerticalLayout implements View, Button.ClickListener{
	public static final String NAME = "scheduled";
	
	private Grid schedulesTable;
	private Button removeScheduleButton;
	private List<Schedule> schedules;
	private BeanItemContainer<Schedule> container =null;

	@Autowired
	private ShowAllSchedulesService showAllSchedulesService;
	
	@Autowired
	private RemoveScheduleService removeScheduleService;
	
	
	
	
	private void addLayout() {
		
		removeScheduleButton = new Button(NotificationMessages.SCHEDULE_REMOVE_BUTTON.getString());
		
		setMargin(true);
		container = new BeanItemContainer<Schedule>(Schedule.class, schedules);
		
		schedulesTable = new Grid(container);
		schedulesTable.setColumnOrder("scheduledTime");
		schedulesTable.removeColumn("id");
		schedulesTable.removeColumn("file");
		schedulesTable.removeColumn("sender");
		schedulesTable.setImmediate(true);
		schedulesTable.setHeight("100%");
		schedulesTable.setWidth("100%");
		schedulesTable.setSelectionMode(SelectionMode.MULTI);
		
		removeScheduleButton.addClickListener(this);

		
		addComponent(schedulesTable);
		addComponent(removeScheduleButton);
		
	}
	
	private void getSchedules() {
		schedules = showAllSchedulesService.getAllSchedules();
	}
	
	public void buttonClick(ClickEvent event) {
		
		MultiSelectionModel selectionModel = (MultiSelectionModel) schedulesTable.getSelectionModel();
		
		for(Object selectedItem : selectionModel.getSelectedRows()) {
			Schedule schedule = (Schedule) selectedItem;
			schedulesTable.getContainerDataSource().removeItem(schedule);
			removeScheduleService.removeSchedule(schedule);
		}
		//refreshTable();
		Notification.show(NotificationMessages.REMOVE_REMOVE_SUCCESS_TITLE.getString(), 
				NotificationMessages.REMOVE_SCHEDULE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
		
		schedulesTable.getSelectionModel().reset();
	}

	public void enter(ViewChangeEvent event) {
		
		if( schedulesTable != null ) return;
		
		getSchedules();
		addLayout();
	}
	
	
	
	public void refreshTable() {
		schedules = showAllSchedulesService.getAllSchedules();
		container.removeAllItems();
		container.addAll(schedules);
	}
	
	

}
