package com.phizercost.babylsms.ui.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.user.User;
import com.phizercost.babylsms.service.security.RegisterUserService;
import com.phizercost.babylsms.service.showalluserservice.ShowAllUsersService;
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
public class ShowAllUsersLayoutFactory implements UIComponentBuilder {

	private List<User> users;
	private BeanItemContainer<User> container;
	private Button remove;
	
	private class ShowAllUsersLayout extends VerticalLayout implements Button.ClickListener{
		private Grid usersTable;
		
		public ShowAllUsersLayout init(){
			setMargin(true);
			
			remove = new Button(NotificationMessages.USER_REMOVE_BUTTON.getString());
			remove.addClickListener(this);
			container = new BeanItemContainer<User>(User.class, users);
			usersTable = new Grid(container);
			usersTable.setColumnOrder("username");
			usersTable.removeColumn("password");
			usersTable.setImmediate(true);
			usersTable.setHeight("100%");
			usersTable.setWidth("100%");
			usersTable.setSelectionMode(SelectionMode.MULTI);
	
			return this;
		}
		
		public ShowAllUsersLayout load(){
			users = showAllUsersService.getAllUsers();
			return this;
		}
		
		public ShowAllUsersLayout layout(){
			addComponent(usersTable);
			addComponent(remove);
			return this;
		}
		
		public void buttonClick(ClickEvent event) {
			
			try{
				MultiSelectionModel selectionModel = (MultiSelectionModel) usersTable.getSelectionModel();
				
				for(Object selectedItem : selectionModel.getSelectedRows()) {
					User user = (User) selectedItem;
					usersTable.getContainerDataSource().removeItem(user);
					removeUserService.removeUser(user);
				}
				//refreshTable();
				Notification.show(NotificationMessages.REMOVE_SUCCESS_TITLE.getString(), 
						NotificationMessages.REMOVE_USER_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
				
				usersTable.getSelectionModel().reset();
			}catch(Exception e){
				Notification.show(NotificationMessages.REMOVE_ERROR_TITLE.getString(), 
						NotificationMessages.REMOVE_USER_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				load();
				return;
			}
			
		}
	}
	
	@Autowired
	ShowAllUsersService showAllUsersService;
	
	@Autowired
	RegisterUserService removeUserService;
	
	public Component createComponent() {
		return new ShowAllUsersLayout().load().init().layout();
	}
	
	public void refreshTable() {
		users = showAllUsersService.getAllUsers();
		container.removeAllItems();
		container.addAll(users);
	}

}
