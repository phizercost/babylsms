package com.phizercost.babylsms.ui.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.customer.Customer;
import com.phizercost.babylsms.service.showallcustomers.ShowAllCustomersService;
import com.phizercost.babylsms.ui.commons.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;


@org.springframework.stereotype.Component
public class ShowAllCustomersLayoutFactory implements UIComponentBuilder{

	private List<Customer> customers;
	private BeanItemContainer<Customer> container;
	
	private class ShowAllCustomersLayout extends VerticalLayout{
		private Grid customersTable;
		
		public ShowAllCustomersLayout init(){
			setMargin(true);
			
			container = new BeanItemContainer<Customer>(Customer.class, customers);
			customersTable = new Grid(container);
			//customersTable.setColumnOrder("phoneNumber","fileName","gender", "dob", "registrationDate");
			customersTable.setColumnOrder("phoneNumber");
			customersTable.removeColumn("id");
			customersTable.setImmediate(true);
			customersTable.setHeight("100%");
			customersTable.setWidth("100%");
	
			return this;
		}
		
		public ShowAllCustomersLayout load(){
			customers = showAllCustomersService.getAllCustomers();
			return this;
		}
		
		public ShowAllCustomersLayout layout(){
			addComponent(customersTable);
			return this;
		}
	}
	
	@Autowired
	private ShowAllCustomersService showAllCustomersService;
	
	
	public Component createComponent(){
		return new ShowAllCustomersLayout().init().load().layout();
	}
	
	public void refreshTable() {
		customers = showAllCustomersService.getAllCustomers();
		container.removeAllItems();
		container.addAll(customers);
		
	}

}
