package com.phizercost.babylsms.ui.factory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.phizercost.babylsms.model.message.Message;
import com.phizercost.babylsms.model.sender.Sender;
import com.phizercost.babylsms.service.showallsendersservice.ShowAllSendersService;
import com.phizercost.babylsms.utils.BabylSMSUtils;
import com.phizercost.babylsms.utils.NotificationMessages;
import com.phizercost.babylsms.utils.PrefixCountryCode;
import com.phizercost.babylsms.utils.SendMessage;
import com.phizercost.babylsms.utils.StringUtils;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;

import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class TestBroadcastMainLayoutFactory {

	private class TestBroadcastMainLayout extends VerticalLayout {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private ComboBox sender;
		private TextField receiver;
		private TextArea sms;
		private Message message;
		private Button sendButton;
		private Button clearButton;
		private Label charCount;

		public TestBroadcastMainLayout init() {

			sender = new ComboBox(StringUtils.SENDER_TITLE.getString());
			receiver = new TextField(StringUtils.RECEIVER_TITLE.getString());
			charCount = new Label("160");
			sms = new TextArea(StringUtils.SMS_TITLE.getString());
			sms.setWidth("207%");
			sms.setHeight("20%");
			sms.setMaxLength(160);
			sender.setWidth("100%");

			sendButton = new Button(StringUtils.SEND_BUTTON.getString());

			sendButton.addClickListener(new Button.ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {

					try {

						message = new Message(sender.getValue().toString(), receiver.getValue(), sms.getValue());
						
						if (BabylSMSUtils.isStringEmpty(sender.getValue().toString()))
							Notification.show(NotificationMessages.SEND_MESSAGE_ERROR_TITLE.getString(),
									NotificationMessages.SENDER_EMPTY_OR_NULL_DESCRIPTION.getString(),
									Type.ERROR_MESSAGE);
						else if (!BabylSMSUtils.isReceiverMsisdnValid(message.getReceiver()))
							Notification.show(NotificationMessages.SEND_MESSAGE_ERROR_TITLE.getString(),
									NotificationMessages.RECEIVER_NOT_ALLOWED_DESCRIPTION.getString(),
									Type.ERROR_MESSAGE);
						else if (BabylSMSUtils.isStringEmpty(message.getMessage()))
							Notification.show(NotificationMessages.SEND_MESSAGE_ERROR_TITLE.getString(),
									NotificationMessages.MESSAGE_EMPTY_OR_NULL_DESCRIPTION.getString(),
									Type.ERROR_MESSAGE);
						else if (message.getMessage().length() > 159)
							Notification.show(NotificationMessages.SEND_MESSAGE_ERROR_TITLE.getString(),
									NotificationMessages.MESSAGE_TOO_LONG_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
						else
							sendMessage();
					} catch (Exception ex) {
						Notification.show(NotificationMessages.SEND_MESSAGE_ERROR_TITLE.getString(),
								NotificationMessages.SEND_MESSAGE_ERROR_DESCRIPTION.getString(),
								Type.ERROR_MESSAGE);
						ex.printStackTrace();
					}
				}
			});
			clearButton = new Button(StringUtils.CLEAR_BUTTON.getString());

			clearButton.addClickListener(new Button.ClickListener() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				public void buttonClick(ClickEvent event) {

					clearField();
				}
			});
			sender.setValue(null);
			receiver.setNullRepresentation("");
			sms.setNullRepresentation("");

			return this;
		}

		public Component layout() {

			setMargin(true);

			GridLayout gridLayout = new GridLayout(3, 3);
			gridLayout.setSizeUndefined();
			gridLayout.setSpacing(true);

			gridLayout.addComponent(sender, 0, 0);
			gridLayout.addComponent(receiver, 1, 0);

			gridLayout.addComponent(sms, 0, 1);

			gridLayout.addComponent(new HorizontalLayout(sendButton, clearButton), 0, 2);
			// gridLayout.addComponent(charCount,2,2);

			return gridLayout;
		}

		private void sendMessage() {

			try {

				String result = SendMessage.sendMessage(message.getSender(), message.getReceiver(),
						message.getMessage());
				if (result.equalsIgnoreCase("200")) {
					result = NotificationMessages.SEND_MESSAGE_DESCRIPTION.getString() + message.getReceiver();
					Notification.show(NotificationMessages.SEND_MESSAGE_TITLE.getString(), result,
							Type.WARNING_MESSAGE);
					System.out.println(result);
				} else {
					Notification.show(NotificationMessages.SEND_MESSAGE_ERROR_TITLE.getString(),
							NotificationMessages.SEND_MESSAGE_ERROR_DESCRIPTION.getString()+"|"+result, Type.ERROR_MESSAGE);

				}
				clearField();
			} catch (Exception e) {
				Notification.show(NotificationMessages.SEND_MESSAGE_ERROR_TITLE.getString(),
						NotificationMessages.SEND_MESSAGE_UNKNOWN_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				System.out.println(e.toString());
				return;
			}
		}

		private void clearField() {
			sender.setValue("");
			receiver.setValue("");
			sms.setValue("");
		}

		public TestBroadcastMainLayout load() {

			List<Sender> senders = showAllSendersService.getAllSenders();
			sender.addItems(senders);

			return this;
		}
	}

	@Autowired
	private ShowAllSendersService showAllSendersService;

	public Component createComponent() {
		return new TestBroadcastMainLayout().init().load().layout();
	}

}
