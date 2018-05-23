package com.phizercost.babylsms.model.schedule;

public class BroadcastDetails {
	
	private int phoneNumber;
	private String Sender;
	private int notifiedNumber;
	private String message;
	private int throughput;
	
	
	
	
	public BroadcastDetails() {
		
	}
	public BroadcastDetails(int phoneNumber, String sender, int notifiedNumber, String message, int throughput) {
		this.phoneNumber = phoneNumber;
		Sender = sender;
		this.notifiedNumber = notifiedNumber;
		this.message = message;
		this.throughput = throughput;
	}
	public int getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(int phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getSender() {
		return Sender;
	}
	public void setSender(String sender) {
		Sender = sender;
	}
	public int getNotifiedNumber() {
		return notifiedNumber;
	}
	public void setNotifiedNumber(int notifiedNumber) {
		this.notifiedNumber = notifiedNumber;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getThroughput() {
		return throughput;
	}
	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}
	
	

}
