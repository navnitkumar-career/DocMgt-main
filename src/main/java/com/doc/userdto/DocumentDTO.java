package com.doc.userdto;

public class DocumentDTO {

	int id;
	String docData;
	int userId;
	String emailId;
	String fileName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDocData() {
		return docData;
	}

	public void setDocData(String docData) {
		this.docData = docData;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "DocumentDTO [id=" + id + ", docData=" + docData + ", userId=" + userId + ", emailId=" + emailId
				+ ", fileName=" + fileName + "]";
	}

}
