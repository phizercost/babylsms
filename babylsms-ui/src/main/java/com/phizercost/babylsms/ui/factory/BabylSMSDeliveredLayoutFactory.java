package com.phizercost.babylsms.ui.factory;

 
import com.phizercost.babylsms.ui.commons.BabylSMSMainUI;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@SpringView (name=BabylSMSDeliveredLayoutFactory.NAME, ui= BabylSMSMainUI.class)
public class BabylSMSDeliveredLayoutFactory extends VerticalLayout implements View{
	public static final String NAME = "delivered";
	
	public void enter(ViewChangeEvent event){
		addComponent(new Label ("This is the Delivered report layout"));
	}

}
