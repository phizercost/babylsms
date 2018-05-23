package com.phizercost.babylsms.utils;

public enum NotificationMessages {
	
	FILE_SAVE_VALIDATION_ERROR_TITLE("ERROR"),
	FILE_SAVE_VALIDATION_ERROR_DESCRIPTION("An error happened when processing the file. Please check if the file is in the right format or it had not been loaded under the same name"),
	FILE_SAVE_VALIDATION_SUCCESS_TITLE("SUCCESS"),
	FILE_SAVE_VALIDATION_SUCCESS_DESCRIPTION("File Succesfully loaded"), 
	REMOVE_REMOVE_SUCCESS_TITLE("SUCCESS"), 
	REMOVE_SUCCESS_DESCRIPTION("File(s) successfully removed"), 
	FILE_REMOVE_BUTTON("Remove"), 
	SEND_MESSAGE_DESCRIPTION("Message sent succesfully to "), 
	SEND_MESSAGE_TITLE("SUCCESS"), 
	SEND_MESSAGE_ERROR_TITLE("ERROR"), 
	SEND_MESSAGE_ERROR_DESCRIPTION("Message not sent. Check the input parameters or contact the administrator"), 
	SENDER_NOT_ALLOWED_DESCRIPTION("The sender is not allowed. Use BABYL for testing purpose"), 
	RECEIVER_NOT_ALLOWED_DESCRIPTION("The receiver phone number is not valid. The format is 25072XXXXXXX or 25078XXXXXXX or 25073XXXXXXX"),
	MESSAGE_EMPTY_OR_NULL_DESCRIPTION("Message cannot be empty"), 
	MESSAGE_TOO_LONG_DESCRIPTION("The message is too long. It should not exceed 160 characters"), 
	FILE_CONTENT_ERROR_DESCRIPTION("File Contains invalid data"), 
	FILE_EMPTY_ERROR_DESCRIPTION("No Customers found in the file"), 
	SCHEDULE_SAVE_INVALID_TITLE("INVALID"), 
	SCHEDULE_SAVE_INVALID_DESCRIPTION("Schedule save is invalid"), 
	SCHEDULE_SAVE_VALIDATION_ERROR_TITLE("ERROR"), 
	SCHEDULE_SAVE_VALIDATION_ERROR_DESCRIPTION("Some errors occured while saving the schedule"), 
	SCHEDULE_SAVE_SUCCESS_TITLE("SUCCESS"), 
	SCHEDULE_SAVE_SUCCESS_DESCRIPTION("Schedule saved successfully"), 
	NOTIFIED_NUMBER_INVALID_TITLE("ERROR"), 
	NOTIFIED_NUMBER_INVALID_DESCRIPTION("The Notified number is not in the right format"),
	INVALID_DATE_TITLE("ERROR"), 
	INVALID_DATE_DESCRIPTION("The Chosen date is invalid"),
	SCHEDULE_REMOVE_BUTTON("REMOVE"), 
	REMOVE_SCHEDULE_SUCCESS_DESCRIPTION("Schedule(s) removed successfully"), 
	SEND_MESSAGE_UNKNOWN_ERROR_DESCRIPTION("An error occured. Message not sent. Please contact the administrator"), 
	SENDER_SAVE_VALIDATION_ERROR_TITLE("ERROR"), 
	SENDER_SAVE_VALIDATION_ERROR_DESCRIPTION("Some errors occured while saving the sender. Check whether the Sender does not exist"), 
	SENDER_SAVE_SUCCESS_TITLE("SUCCESS"), 
	SENDER_SAVE_SUCCESS_DESCRIPTION("Sender saved successfully"), 
	FILE_DOWNLOAD_BUTTON("Download"),
	DOWNLOAD_SUCCESS_TITLE("SUCCESS"), 
	DOWNLOAD_SUCCESS_DESCRIPTION("File(s) is/are downloaded succesfully in your home folder"), 
	DOWNLOAD_ERROR_DESCRIPTION("An error occured. Files were not downloaded"), 
	DOWNLOAD_ERROR_TITLE("ERROR"), 
	FILE_ARCHIVE_BUTTON("Archive"), 
	DOWNLOAD_ARCHIVE_TITLE("SUCCESS"), 
	DOWNLOAD_ARCHIVE_DESCRIPTION("File(s) is/are archived successfully"), 
	GENERAL_ERROR_TITLE("ERROR"), GENERAL_ERROR_DESCRIPTION("General Error. Please check the information entered"), FILE_RETRIEVE_BUTTON("Retrieve"), RETRIEVE_SUCCESS_TITLE("SUCCESS"), 
	RETRIEVE_SUCCESS_DESCRIPTION("File(s) retrieved successfully"), 
	REMOVE_SUCCESS_TITLE("SUCCESS"), 
	REMOVE_SENDER_SUCCESS_DESCRIPTION("Sender(s) removed successfully"), 
	REMOVE_ERROR_TITLE("ERROR"), 
	REMOVE_SENDER_ERROR_DESCRIPTION("Sender(s) cannot be removed. It might be associated to a broadcast already"), 
	SENDER_EMPTY_OR_NULL_DESCRIPTION("Please specify a Sender for this message"), 
	SENDER_SAVE_EMPTY_ERROR_DESCRIPTION("Please specify sender"), 
	USERNAME_OR_PASSWORD_SAVE_EMPTY_ERROR_DESCRIPTION("Please specify Username and Password"), 
	USER_SAVE_VALIDATION_ERROR_TITLE("Error"), 
	PASSWORD_NOT_MATCHING_ERROR_DESCRIPTION("Passwords do not match"), 
	USER_SAVE_VALIDATION_SUCCESS_TITLE("SUCCESS"), 
	USER_SAVE_VALIDATION_SUCCESS_DESCRIPTION("User is created successfully"), 
	USER_REMOVE_BUTTON("Remove"), REMOVE_USER_SUCCESS_DESCRIPTION("User(s) removed successfully"), REMOVE_USER_ERROR_DESCRIPTION("Users were not removed");
	
	private final String string;
	
	private  NotificationMessages(String string){
		this.string = string;
	}
	
	public String getString(){
		return this.string;		
	}

}
