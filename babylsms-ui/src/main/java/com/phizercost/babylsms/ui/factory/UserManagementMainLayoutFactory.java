package com.phizercost.babylsms.ui.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.phizercost.babylsms.model.user.User;
import com.phizercost.babylsms.service.getuserbyusername.GetUserByUsernameService;
import com.phizercost.babylsms.service.security.RegisterUserService;
import com.phizercost.babylsms.utils.BabylSMSUtils;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.phizercost.babylsms.utils.StringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;

@org.springframework.stereotype.Component
public class UserManagementMainLayoutFactory {

	private class UserManagementMainLayout extends VerticalLayout implements Button.ClickListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextField username;
		private ComboBox usertype;
		private PasswordField passwordField;
		private PasswordField passwordAgainField;

		private Button saveButton;
		private Button clearButton;

		private BeanFieldGroup<User> fieldGroup;
		private User user;
		
	

		private UserSavedListener userSavedListener;

		public UserManagementMainLayout(UserSavedListener userSavedListener) {
			this.userSavedListener = userSavedListener;
		}

		public UserManagementMainLayout init() {

				fieldGroup = new BeanFieldGroup<User>(User.class);
				user = new User();

				username = new TextField("Username");
				passwordField = new PasswordField("Password");
				passwordAgainField = new PasswordField("Password again");
				usertype = new ComboBox("User Type");

				usertype.addItem("Admin");
				usertype.addItem("Regular");

				saveButton = new Button(StringUtils.SAVE_BUTTON.getString());
				clearButton = new Button(StringUtils.CLEAR_BUTTON.getString());

				saveButton.addClickListener(this);
				clearButton.addClickListener(this);

				username.setNullRepresentation("");

				return this;
			
		}

		public Component layout() {

			setMargin(true);

			GridLayout gridLayout = new GridLayout(3, 4);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);

			gridLayout.addComponent(username, 0, 0);
			gridLayout.addComponent(usertype, 1, 0);
			gridLayout.addComponent(passwordField, 0, 1);
			gridLayout.addComponent(passwordAgainField, 1, 1);

			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 3);

			return gridLayout;

		}

		public void buttonClick(ClickEvent event) {

			if (event.getSource() == this.saveButton) {

				if (BabylSMSUtils.isStringEmpty(username.getValue())
						|| BabylSMSUtils.isStringEmpty(passwordField.getValue())) {
					Notification.show(NotificationMessages.USER_SAVE_VALIDATION_ERROR_TITLE.getString(),
							NotificationMessages.USERNAME_OR_PASSWORD_SAVE_EMPTY_ERROR_DESCRIPTION.getString(),
							Type.ERROR_MESSAGE);
					return;
				}

				if (!passwordAgainField.getValue().equals(passwordField.getValue())) {
					Notification.show(NotificationMessages.USER_SAVE_VALIDATION_ERROR_TITLE.getString(),
							NotificationMessages.PASSWORD_NOT_MATCHING_ERROR_DESCRIPTION.getString(),
							Type.ERROR_MESSAGE);
					return;
				}
				try {

					registerUserService.save(username.getValue(), passwordField.getValue(),
							usertype.getValue().toString());
					userSavedListener.userSaved();
					Notification.show(NotificationMessages.USER_SAVE_VALIDATION_SUCCESS_TITLE.getString(),
							NotificationMessages.USER_SAVE_VALIDATION_SUCCESS_DESCRIPTION.getString(),
							Type.WARNING_MESSAGE);

				} catch (Exception e) {
					Notification.show("Error", "An Error Occured. Check the information input", Type.ERROR_MESSAGE);
					return;
				}

			} else {
				clearField();
			}
		}

		public UserManagementMainLayout bind() {
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(user);
			return this;
		}

		private void clearField() {
			username.setValue("");
			passwordField.setValue("");
			passwordAgainField.setValue("");
		}
	}

	@Autowired
	private RegisterUserService registerUserService;

	public Component createComponent(UserSavedListener userSavedListener) {
		return new UserManagementMainLayout(userSavedListener).init().bind().layout();
	}

}
