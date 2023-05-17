package com.doc.userdto;

public class TeamMappingDTO {

	int id;
	int teamId;
	String teamName;
	int userId;
	String emailId;
	
	public TeamMappingDTO() {
		super();
	}

	public TeamMappingDTO(int id, int teamId, String teamName, int userId, String emailId) {
		super();
		this.id = id;
		this.teamId = teamId;
		this.teamName = teamName;
		this.userId = userId;
		this.emailId = emailId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TeamMappingDTO [id=" + id + ", teamId=" + teamId + ", teamName=" + teamName + ", userId=" + userId
				+ ", emailId=" + emailId + "]";
	}

	

}
