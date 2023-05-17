package com.doc.service;

import java.util.List;
import java.util.Map;

import com.doc.userdto.TeamMappingDTO;

public interface TeamMappingService {
	
	public List<TeamMappingDTO> getList();

	public Map<String, String> add(TeamMappingDTO teamMapping);

	public Map<String, String> deleteById(TeamMappingDTO teamMapping);

}
