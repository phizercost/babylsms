package com.phizercost.babylsms.utils;

public enum StringUtils {
	
	MENU_FILES("Files"),
	MENU_LOAD("Load"),
	MENU_MANAGE("Manage"),
	MENU_BROADCAST("Broadcast"),
	MENU_SCHEDULE("Schedule"),
	MENU_TEST("Test"),
	MENU_REPORTS("Reports"),
	MENU_DELIVERED("Delivered"),
	MENU_FAILURE("Failure"), 
	RECEIVER_TITLE("Receiver"), 
	SENDER_TITLE("Sender"), 
	SMS_TITLE("SMS"), 
	SEND_BUTTON("Send"), 
	CLEAR_BUTTON("Clear"), 
	THROUGHPUT_TITLE("Throughput"), 
	SCHEDULED_TIME_TITLE("Schedule"), 
	NOTIFIED_NUMBER_TITLE("Number to notify"), 
	MESSAGE_TITLE("Message"), 
	FILE_TITLE("File"), 
	SAVE_BUTTON("Save"), 
	SCHEDULE_BROADCAST("Schedule"), 
	MENU_SCHEDULED("Scheduled"), 
	MENU_LOGOUT("Logout"), 
	MENU_SENDER("Senders"), 
	MENU_SAVE_SENDER("Save"), 
	MENU_REMOVE_SENDER("Delete"), 
	SHOW_ALL_SENDERS("Show All"), 
	MENU_RETRIEVE("Retrieve"), 
	MENU_USERS("Users"), 
	USER_TITLE("Users"), 
	SHOW_ALL_USERS("Show All");
	
	private final String string;
	
	private StringUtils(String string)
	{
		this.string = string;
	}
	
	public String getString(){
		return this.string;
	}
	


}
