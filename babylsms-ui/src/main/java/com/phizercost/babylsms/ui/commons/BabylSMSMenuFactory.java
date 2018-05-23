package com.phizercost.babylsms.ui.commons;
 

 
import org.springframework.security.core.context.SecurityContextHolder;

import com.phizercost.babylsms.ui.navigator.BabylSMSNavigator;
import com.phizercost.babylsms.utils.StringUtils;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Tree;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class BabylSMSMenuFactory implements UIComponentBuilder{

	private class BabylSMSMenu extends VerticalLayout implements Property.ValueChangeListener{
		private Tree mainMenu;
		private Tree adminMenu;

		public BabylSMSMenu init() {
			mainMenu = new Tree();
			adminMenu = new Tree();
			
			adminMenu.addValueChangeListener(this);
			mainMenu.addValueChangeListener(this);
			return this;
		}

		public BabylSMSMenu layout() {
			setWidth("100%");
			setHeightUndefined();
			
			
			//mainMenu.addItem(StringUtils.MENU_SENDER.getString());			
			//mainMenu.setChildrenAllowed(StringUtils.MENU_SENDER.getString(), false);

			mainMenu.addItem(StringUtils.MENU_FILES.getString());
			mainMenu.addItem(StringUtils.MENU_LOAD.getString());
			mainMenu.addItem(StringUtils.MENU_MANAGE.getString());
			mainMenu.addItem(StringUtils.MENU_RETRIEVE.getString());
			
			
			
			mainMenu.setChildrenAllowed(StringUtils.MENU_LOAD.getString(), false);
			mainMenu.setChildrenAllowed(StringUtils.MENU_MANAGE.getString(), false);
			mainMenu.setChildrenAllowed(StringUtils.MENU_RETRIEVE.getString(), false);
			mainMenu.setParent(StringUtils.MENU_LOAD.getString(), StringUtils.MENU_FILES.getString());
			mainMenu.setParent(StringUtils.MENU_MANAGE.getString(), StringUtils.MENU_FILES.getString());
			mainMenu.setParent(StringUtils.MENU_RETRIEVE.getString(), StringUtils.MENU_FILES.getString());
			
			mainMenu.addItem(StringUtils.MENU_BROADCAST.getString());
			mainMenu.addItem(StringUtils.MENU_TEST.getString());
			mainMenu.addItem(StringUtils.MENU_SCHEDULE.getString());
			mainMenu.addItem(StringUtils.MENU_SCHEDULED.getString());
		
			mainMenu.setChildrenAllowed(StringUtils.MENU_TEST.getString(), false);
			mainMenu.setChildrenAllowed(StringUtils.MENU_SCHEDULE.getString(), false);
			mainMenu.setChildrenAllowed(StringUtils.MENU_SCHEDULED.getString(), false);

			mainMenu.setParent(StringUtils.MENU_TEST.getString(), StringUtils.MENU_BROADCAST.getString());
			mainMenu.setParent(StringUtils.MENU_SCHEDULE.getString(), StringUtils.MENU_BROADCAST.getString());
			mainMenu.setParent(StringUtils.MENU_SCHEDULED.getString(), StringUtils.MENU_BROADCAST.getString());
			
			//mainMenu.addItem(StringUtils.MENU_REPORTS.getString());
			//mainMenu.addItem(StringUtils.MENU_DELIVERED.getString());
			//mainMenu.addItem(StringUtils.MENU_FAILURE.getString());
			//mainMenu.setChildrenAllowed(StringUtils.MENU_DELIVERED.getString(), false);
			//mainMenu.setChildrenAllowed(StringUtils.MENU_FAILURE.getString(), false);
			//mainMenu.setParent(StringUtils.MENU_DELIVERED.getString(), StringUtils.MENU_REPORTS.getString());
			//mainMenu.setParent(StringUtils.MENU_FAILURE.getString(), StringUtils.MENU_REPORTS.getString());
			
			mainMenu.addItem(StringUtils.MENU_LOGOUT.getString());
			
			
			
			

			addComponent(mainMenu);
			return this;
		}

		public void valueChange(ValueChangeEvent event) {
				String selectedItemPath = (String) event.getProperty().getValue();
				
				if(selectedItemPath == null) return;
				
				if( selectedItemPath.equals("Logout") ) {
					SecurityContextHolder.clearContext();
					UI.getCurrent().getPage().setLocation("/babylsms-web/login");
				}
				
				String path = selectedItemPath.toLowerCase().replaceAll("\\s+","");
				BabylSMSNavigator.navigate(path);
				
		}
		
		

	
	}

	public Component createComponent() {
		return new BabylSMSMenu().init().layout();
	}

}
