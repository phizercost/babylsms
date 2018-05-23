package com.phizercost.babylsms.model.sender;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="sender",uniqueConstraints=
@UniqueConstraint(columnNames={"sender"}))
public class Sender {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	
	@Column(name="sender")
	@NotNull(message="Must specify Sender")
	private String sender;
	
	

	public Sender() {
	
	}

	public Sender(String sender) {
		this.sender = sender;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
	
	@Override
	public String toString() {
		return this.getSender();
	}
	
	
	

}
