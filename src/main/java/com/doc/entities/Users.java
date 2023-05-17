package com.doc.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Users {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String emailId;
	
	@Column(name="datetime")
	private LocalDateTime dateTime;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime localDateTime) {
		this.dateTime = localDateTime;
	}
	
	public Users() {
		super();
	}
	
	public Users(String emailId) {
		super();
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", emailId=" + emailId + ", dateTime=" + dateTime + "]";
	}



}
