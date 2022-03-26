package com.patients.info.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="patients")
public class PatientsModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
	long id; 
	String pataientsName;
	String gender;
	int age;
	@Column(name = "DOB")
	Date dob;
	@Column(name = "Phone_Number")
	String phoneNumber;
	String email;
	String photo;
	
	public PatientsModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientsModel(long id, String pataientsName, String gender, int age, Date dob, String phoneNumber,
			String email, String photo) {
		super();
		this.id = id;
		this.pataientsName = pataientsName;
		this.gender = gender;
		this.age = age;
		this.dob = dob;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.photo = photo;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPataientsName() {
		return pataientsName;
	}

	public void setPataientsName(String pataientsName) {
		this.pataientsName = pataientsName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	} 
	 
	 
}
