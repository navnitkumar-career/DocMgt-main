package com.doc.service;

import java.util.List;

import com.doc.userdto.TeamDTO;

public interface TeamService {
	
	public List<TeamDTO> getList();

	public boolean update(TeamDTO team);

	public boolean add(TeamDTO team);

	public boolean deleteById(String teamName);
	
}
