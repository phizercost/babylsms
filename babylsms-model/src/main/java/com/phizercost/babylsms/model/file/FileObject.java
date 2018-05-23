package com.phizercost.babylsms.model.file;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="file",uniqueConstraints=
@UniqueConstraint(columnNames={"file_name"}))
public class FileObject {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name = "customers")
	private int customers;
	
	@Column(name = "creation_date")
	private String date;
	
	@Column(name = "owner")
	private String owner;
	
	@Column(name = "status")
	private Integer status = 1;
	
	
	public FileObject() {
	}

	public FileObject(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getNumberofCustomers() {
		return customers;
	}

	public void setNumberofCustomers(int numberofCustomers) {
		this.customers = numberofCustomers;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return this.getFileName();
	}

}