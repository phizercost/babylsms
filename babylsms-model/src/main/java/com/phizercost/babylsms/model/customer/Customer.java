package com.phizercost.babylsms.model.customer;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="customer")
public class Customer {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Column(name="registration_date")
	private String registrationDate;
	
	@Column(name="dob")
	private String dob;
	
	@Column(name="gender")
	private String gender;
	
	@Column(name="file_name")
	private String fileName;
	
	public Customer(String phoneNumber, String registrationDate, String dob, String gender, String filename) {
		this.phoneNumber = phoneNumber;
		this.registrationDate = registrationDate;
		this.dob = dob;
		this.gender = gender;
		this.fileName = filename;
	}
	public Customer() {
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getFilename() {
		return fileName;
	}
	public void setFilename(String filename) {
		this.fileName = filename;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "Customer [id=" + id + ", phoneNumber=" + phoneNumber + ", registrationDate=" + registrationDate
				+ ", dob=" + dob + ", gender=" + gender + ", fileName=" + fileName + "]";
	}
	
	

}