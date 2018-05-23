package com.phizercost.babylsms.ui.factory;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.service.savesender.SaveSenderService;
import com.phizercost.babylsms.service.showallsendersservice.ShowAllSendersService;
import com.phizercost.babylsms.utils.BabylSMSUtils;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.phizercost.babylsms.utils.StringUtils;
import com.vaadin.data.Validator;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class SaveSenderMainLayoutFactory {

	private class SaveSenderMainLayout extends VerticalLayout implements Button.ClickListener {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private TextField senderTxt;

		private Button saveButton;
		private Button clearButton;

		private BeanFieldGroup<Sender> fieldGroup;
		private Sender sender;

		private SenderSavedListener senderSavedListener;

		public SaveSenderMainLayout(SenderSavedListener senderSavedListener) {
			this.senderSavedListener = senderSavedListener;
		}

		public SaveSenderMainLayout init() {

			fieldGroup = new BeanFieldGroup<Sender>(Sender.class);
			sender = new Sender();

			senderTxt = new TextField(StringUtils.SENDER_TITLE.getString());

			senderTxt.setMaxLength(11);
			saveButton = new Button(StringUtils.SAVE_BUTTON.getString());
			clearButton = new Button(StringUtils.CLEAR_BUTTON.getString());

			saveButton.addClickListener(this);
			clearButton.addClickListener(this);

			senderTxt.setNullRepresentation("");

			return this;
		}

		public Component layout() {

			setMargin(true);

			GridLayout gridLayout = new GridLayout(1, 2);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);

			gridLayout.addComponent(senderTxt, 0, 0);
			gridLayout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 1);

			return gridLayout;

		}

		public void buttonClick(ClickEvent event) {

			if (event.getSource() == this.saveButton) {
				if (!BabylSMSUtils.isStringEmpty(senderTxt.getValue())) 
				save();
				else {
					Notification.show(NotificationMessages.SENDER_SAVE_VALIDATION_ERROR_TITLE.getString(),
							NotificationMessages.SENDER_SAVE_EMPTY_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				}

			} else {
				clearField();
			}
		}

		private void save() {

			sender.setSender(senderTxt.getValue());
			
				try {
					fieldGroup.commit();
				} catch (CommitException e) {
					Notification.show(NotificationMessages.SENDER_SAVE_VALIDATION_ERROR_TITLE.getString(),
							NotificationMessages.SENDER_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(),
							Type.ERROR_MESSAGE);
					return;
				}
				try {
					saveSenderService.saveSender(sender);
					senderSavedListener.senderSaved();
				} catch (Exception ex) {
					Notification.show(NotificationMessages.SENDER_SAVE_VALIDATION_ERROR_TITLE.getString(),
							NotificationMessages.SENDER_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(),
							Type.ERROR_MESSAGE);
					return;
				}
			 

			clearField();

			Notification.show(NotificationMessages.SENDER_SAVE_SUCCESS_TITLE.getString(),
					NotificationMessages.SENDER_SAVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);

		}

		public SaveSenderMainLayout bind() {
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(sender);
			return this;
		}

		private void clearField() {
			senderTxt.setValue("");
		}
	}

	@Autowired
	private SaveSenderService saveSenderService;

	public Component createComponent(SenderSavedListener senderSavedLister) {
		return new SaveSenderMainLayout(senderSavedLister).init().bind().layout();
	}

}
