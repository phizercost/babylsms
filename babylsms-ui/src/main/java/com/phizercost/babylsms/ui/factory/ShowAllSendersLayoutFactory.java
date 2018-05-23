package com.phizercost.babylsms.ui.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.model.schedule.Schedule;
import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.service.removesender.RemoveSenderService;
import com.phizercost.babylsms.service.showallsendersservice.ShowAllSendersService;
import com.phizercost.babylsms.ui.commons.UIComponentBuilder;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification.Type;

@org.springframework.stereotype.Component
public class ShowAllSendersLayoutFactory implements UIComponentBuilder {

	private List<Sender> senders;
	private BeanItemContainer<Sender> container;
	private Button remove;
	
	private class ShowAllSendersLayout extends VerticalLayout implements Button.ClickListener{
		private Grid sendersTable;
		
		public ShowAllSendersLayout init(){
			setMargin(true);
			
			remove = new Button(NotificationMessages.FILE_REMOVE_BUTTON.getString());
			remove.addClickListener(this);
			container = new BeanItemContainer<Sender>(Sender.class, senders);
			sendersTable = new Grid(container);
			sendersTable.setColumnOrder("sender");
			sendersTable.removeColumn("id");
			sendersTable.setImmediate(true);
			sendersTable.setHeight("100%");
			sendersTable.setWidth("100%");
			sendersTable.setSelectionMode(SelectionMode.MULTI);
	
			return this;
		}
		
		public ShowAllSendersLayout load(){
			senders = showAllSendersService.getAllSenders();
			return this;
		}
		
		public ShowAllSendersLayout layout(){
			addComponent(sendersTable);
			addComponent(remove);
			return this;
		}
		
		public void buttonClick(ClickEvent event) {
			
			try{
				MultiSelectionModel selectionModel = (MultiSelectionModel) sendersTable.getSelectionModel();
				
				for(Object selectedItem : selectionModel.getSelectedRows()) {
					Sender sender = (Sender) selectedItem;
					sendersTable.getContainerDataSource().removeItem(sender);
					removeSenderService.removeSender(sender);
				}
				//refreshTable();
				Notification.show(NotificationMessages.REMOVE_SUCCESS_TITLE.getString(), 
						NotificationMessages.REMOVE_SENDER_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
				
				sendersTable.getSelectionModel().reset();
			}catch(Exception e){
				Notification.show(NotificationMessages.REMOVE_ERROR_TITLE.getString(), 
						NotificationMessages.REMOVE_SENDER_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				load();
				return;
			}
			
		}
	}
	
	@Autowired
	ShowAllSendersService showAllSendersService;
	
	@Autowired
	RemoveSenderService removeSenderService;
	
	public Component createComponent() {
		return new ShowAllSendersLayout().init().load().layout();
	}
	
	public void refreshTable() {
		senders = showAllSendersService.getAllSenders();
		container.removeAllItems();
		container.addAll(senders);
	}

}
