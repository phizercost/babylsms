package com.phizercost.babylsms.ui.commons;

import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.VerticalLayout;


@org.springframework.stereotype.Component
public class BabylSMSLogoLayoutFactory implements UIComponentBuilder{

	
	private class LogoLayout extends VerticalLayout{
		private Embedded logo;
		
		public LogoLayout init(){
			logo = new Embedded();
			logo.setSource (new ThemeResource("../../images/babyl.png"));
			logo.setWidth("1500px");
			logo.setHeight("63px");
			return this;
		}
		
		public LogoLayout layout(){
			addComponent(logo);
			setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
			return this;
		}
	}
	public Component createComponent() {
		return new LogoLayout().init().layout();
	}
	
	

}
