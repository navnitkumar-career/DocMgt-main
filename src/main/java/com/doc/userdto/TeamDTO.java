package com.doc.userdto;

public class TeamDTO {
	
	int id;
	String teamName;
	
	public TeamDTO() {
		super();
	}

	public TeamDTO(int id, String teamName) {
		super();
		this.id = id;
		this.teamName = teamName;
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

	@Override
	public String toString() {
		return "TeamDTO [id=" + id + ", teamName=" + teamName + "]";
	}

}
