package com.phizercost.babylsms.ui.factory;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.model.schedule.Schedule;
import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.service.addschedule.AddScheduleService;
import com.phizercost.babylsms.service.showallfiles.ShowAllFilesService;
import com.phizercost.babylsms.service.showallsendersservice.ShowAllSendersService;
import com.phizercost.babylsms.utils.BabylSMSUtils;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.phizercost.babylsms.utils.StringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DateField;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class ScheduleBroadcastMainLayoutFactory {

	private class ScheduleBroadcastMainLayout extends VerticalLayout implements Button.ClickListener {

		private ComboBox sender;
		private ComboBox throughput;
		private DateField scheduledTime;
		private TextField notifiedNumber;
		private TextArea message;
		private ComboBox file;
		private Button saveButton;
		private Button clearButton;

		private BeanFieldGroup<Schedule> fieldGroup;
		private Schedule schedule;

		private ScheduleSavedListener scheduleSavedLister;

		public ScheduleBroadcastMainLayout(ScheduleSavedListener scheduleSavedLister) {
			this.scheduleSavedLister = scheduleSavedLister;
		}

		public ScheduleBroadcastMainLayout init() {

			fieldGroup = new BeanFieldGroup<Schedule>(Schedule.class);
			schedule = new Schedule();

			sender = new ComboBox(StringUtils.SENDER_TITLE.getString());
			throughput = new ComboBox(StringUtils.THROUGHPUT_TITLE.getString());
			scheduledTime = new DateField(StringUtils.SCHEDULED_TIME_TITLE.getString());
			scheduledTime.setResolution(Resolution.MINUTE);
			notifiedNumber = new TextField(StringUtils.NOTIFIED_NUMBER_TITLE.getString());
			message = new TextArea(StringUtils.MESSAGE_TITLE.getString());
			sender.setWidth("100%");
			message.setWidth("100%");
			message.setHeight("20%");
			message.setMaxLength(160);
			
			for(int i=0; i < 10; i++){
				throughput.addItem(i+1);
			}
			
			file = new ComboBox(StringUtils.FILE_TITLE.getString());
			file.setWidth("100%");

			saveButton = new Button(StringUtils.SAVE_BUTTON.getString());
			clearButton = new Button(StringUtils.CLEAR_BUTTON.getString());

			 

			saveButton.addClickListener(this);
			clearButton.addClickListener(this);

			//sender.setNullRepresentation("");
			//throughput.setNullRepresentation("");
			// scheduledTime.setNullRepresentation("");
			notifiedNumber.setNullRepresentation("");
			message.setNullRepresentation("");

			return this;
		}

		public ScheduleBroadcastMainLayout bind() {
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(schedule);
			return this;
		}

		public Component layout() {

			setMargin(true);

			GridLayout gridLayout = new GridLayout(3, 4);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);

			gridLayout.addComponent(sender, 1, 0);
			gridLayout.addComponent(notifiedNumber, 0, 0);
			gridLayout.addComponent(throughput, 2, 0);
			

			gridLayout.addComponent(file, 1, 1);
			gridLayout.addComponent(scheduledTime, 0, 1);

			gridLayout.addComponent(message, 0, 2, 1, 2);

			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 3);

			return gridLayout;

		}

		public void buttonClick(ClickEvent event) {
			
			if (event.getSource() == this.saveButton) {
				
				try{
					if (!BabylSMSUtils.isMsisdnValid(notifiedNumber.getValue().toString())) {
						Notification.show(NotificationMessages.NOTIFIED_NUMBER_INVALID_TITLE.getString(),
								NotificationMessages.NOTIFIED_NUMBER_INVALID_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
					}

					else if(!BabylSMSUtils.isDateGreaterThanToday(scheduledTime.getValue().toString())){
						Notification.show(NotificationMessages.INVALID_DATE_TITLE.getString(),
								NotificationMessages.INVALID_DATE_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
						
					}else
						save();
				}catch(Exception ex){
					Notification.show(NotificationMessages.GENERAL_ERROR_TITLE.getString(),
							NotificationMessages.GENERAL_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
					return;
				}
				

			} else {
				clearField();
			}
		}

		private void save() {

			if (!isSaveOperationValid()) {

				Notification.show(NotificationMessages.SCHEDULE_SAVE_INVALID_TITLE.getString(),
						NotificationMessages.SCHEDULE_SAVE_INVALID_DESCRIPTION.getString(), Type.ERROR_MESSAGE);

				return;
			}
			//schedule.setSenderName((String) sender.);
			try {
				fieldGroup.commit();
			} catch (CommitException e) {
				Notification.show(NotificationMessages.SCHEDULE_SAVE_VALIDATION_ERROR_TITLE.getString(),
						NotificationMessages.SCHEDULE_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(),
						Type.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}

			addScheduleService.saveSchedule(schedule);
			//scheduleSavedLister.scheduleSaved();*/
			/*
			 * BroadcastJob broadcastJob = new
			 * BroadcastJob("Test","TestGroup","TestTrigger","TestTriggerGroup",
			 * "59","16","16","11","2017"); broadcastJob.schedule();
			 */
			clearField();

			Notification.show(NotificationMessages.SCHEDULE_SAVE_SUCCESS_TITLE.getString(),
					NotificationMessages.SCHEDULE_SAVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);

		}

		private void clearField() {
			sender.setValue(null);
			throughput.setValue(null);
			notifiedNumber.setValue(null);
			scheduledTime.setValue(null);
			file.setValue(null);
			message.setValue(null);
		}

		private boolean isSaveOperationValid() {
			return showAllFilesService.getAllFiles().size() != 0;
		}

		public ScheduleBroadcastMainLayout load() {

			List<FileObject> files = showAllFilesService.getAllFiles();
			file.addItems(files);
			
			List<Sender> senders = showAllSendersService.getAllSenders();
			sender.addItems(senders);

			return this;
		}
	}

	@Autowired
	private ShowAllFilesService showAllFilesService;
	
	@Autowired
	private ShowAllSendersService showAllSendersService;

	@Autowired
	private AddScheduleService addScheduleService;

	public Component createComponent(ScheduleSavedListener scheduleSavedLister) {
		// return new
		// ScheduleBroadcastMainLayout(scheduleSavedLister).init().load().bind().layout();
		return new ScheduleBroadcastMainLayout(scheduleSavedLister).init().bind().load().layout();
	}

}
