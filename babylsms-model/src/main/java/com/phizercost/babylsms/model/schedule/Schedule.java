package com.phizercost.babylsms.model.schedule;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.phizercost.babylsms.model.file.FileObject;
import com.phizercost.babylsms.model.sender.Sender;

@Entity
@Table(name = "schedule")
public class Schedule {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	
	@NotNull(message = "Must specify the time the broadcast should start!")
	@Column(name = "scheduled_time", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date scheduledTime;
	
	@NotNull(message = "Must specify the throughput. Number of messages per second")
	@Column(name = "throughput")
	@Min(value = 1, message = "Can not be less than 0")
	@Max(value = 16, message = "Can not be greater than 15")
	private int throughput;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "file_id")
	@NotNull(message="A file must be chosen")
	private FileObject file;
	
	@NotNull(message = "Must specify the message to be sent")
	@Column(name = "message")
	private String message;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@NotNull(message = "Must specify the sender")
	@JoinColumn(name = "sender_id")
	private Sender sender;
	
	@NotNull(message = "Must specify the number to notify for monitoring purpose")
	@Column(name = "notified_number")
	private String notifiedNumber;
	
	@Column(name = "status")
	private String status = "Pending";
	
	
	
	public Schedule() {

	}
	public Schedule(Date scheduledTime, int throughput, FileObject file, String message, Sender sender,
			String notifiedNumber) {
		this.scheduledTime = scheduledTime;
		this.throughput = throughput;
		this.file = file;
		this.message = message;
		this.sender = sender;
		this.notifiedNumber = notifiedNumber;
	}
	public Date getScheduledTime() {
		return scheduledTime;
	}
	public void setScheduledTime(Date scheduledTime) {
		this.scheduledTime = scheduledTime;
	}
	public int getThroughput() {
		return throughput;
	}
	public void setThroughput(int throughput) {
		this.throughput = throughput;
	}
	public FileObject getFile() {
		return file;
	}
	public void setFile(FileObject file) {
		this.file = file;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Sender getSender() {
		return sender;
	}
	public void setSender(Sender sender) {
		this.sender = sender;
	}
	public String getNotifiedNumber() {
		return notifiedNumber;
	}
	public void setNotifiedNumber(String notifiedNumber) {
		this.notifiedNumber = notifiedNumber;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Schedule [id=" + id + ", scheduledTime=" + scheduledTime + ", throughput=" + throughput + ", file="
				+ file + ", message=" + message + ", sender=" + sender + ", notifiedNumber=" + notifiedNumber
				+ ", status=" + status + "]";
	}
	
	
	
	
}
