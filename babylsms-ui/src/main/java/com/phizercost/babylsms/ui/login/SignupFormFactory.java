package com.phizercost.babylsms.ui.login;


import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.service.security.RegisterUserService;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class SignupFormFactory {

	@Autowired
	private RegisterUserService registerUserService;
	
	private class SignupForm {
		
		private VerticalLayout root;
		private Panel panel;
		private TextField username;
		private ComboBox usertype;
		private PasswordField passwordField;
		private PasswordField passwordAgainField;
		private Button saveButton;
		
		public SignupForm init() {
			
			root = new VerticalLayout();
			root.setMargin(true);
			root.setHeight("100%");
			
			panel = new Panel("Signup");
			panel.setSizeUndefined();
			
			saveButton = new Button("Save");
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			
			username = new TextField("Username");
			passwordField = new PasswordField("Password");
			passwordAgainField = new PasswordField("Password again");
			usertype = new ComboBox("User Type");

			usertype.addItem("Admin");
			usertype.addItem("Regular");
			
			saveButton.addClickListener(new ClickListener() {
				public void buttonClick(ClickEvent event) {
					
					if( !passwordAgainField.getValue().equals(passwordField.getValue()) ) {
						Notification.show("Error", "Passwords do not match!", Type.ERROR_MESSAGE);
						return;
					}
					try{
						
					
					registerUserService.save(username.getValue(), passwordField.getValue(), usertype.getValue().toString());
					UI.getCurrent().getPage().setLocation("/babylsms-web/login");
					}catch(Exception e)
					{
						Notification.show("Error", "Please choose the user type", Type.ERROR_MESSAGE);
						return;
					}
				}
			});
			
			return this;
		}
		
		
		public Component layout() {
			
			root.addComponent(panel);
			root.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
			
			FormLayout signupLayout = new FormLayout();
			signupLayout.addComponent(username);
			signupLayout.addComponent(usertype);
			signupLayout.addComponent(passwordField);
			signupLayout.addComponent(passwordAgainField);
			
			signupLayout.addComponent(saveButton);
			signupLayout.setSizeUndefined();
			signupLayout.setMargin(true);
			
			panel.setContent(signupLayout);
			
			return root;
		}
	}
	
	public Component createComponent() {
		return new SignupForm().init().layout();
	}
}
