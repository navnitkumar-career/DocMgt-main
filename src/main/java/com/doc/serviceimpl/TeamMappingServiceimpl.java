package com.doc.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.entities.Team;
import com.doc.entities.TeamMapping;
import com.doc.entities.Users;
import com.doc.repository.TeamMappingRepository;
import com.doc.repository.TeamRepository;
import com.doc.repository.UserRepository;
import com.doc.service.TeamMappingService;
import com.doc.userdto.TeamMappingDTO;

@Service
public class TeamMappingServiceimpl implements TeamMappingService {

	@Autowired
	TeamMappingRepository teamMappingRepository;

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Transactional
	@Override
	public List<TeamMappingDTO> getList() {

		List<TeamMapping> teamMappingList = teamMappingRepository.findAll();

		List<TeamMappingDTO> team = teamMappingList.stream().map(t -> {

			TeamMappingDTO teams = new TeamMappingDTO();

			modelMapper.map(t, teams);
			teams.setUserId(t.getUsers().getId());
			teams.setEmailId(t.getUsers().getEmailId());
			return teams;
		}).collect(Collectors.toList());
		return team;
	}

	@Transactional
	@Override
	public Map<String, String> add(TeamMappingDTO teamMappingAdd) {

		Map<String, String> map = new HashMap<>();
		TeamMapping team = new TeamMapping();
		modelMapper.map(teamMappingAdd, team);
		Team t1 = teamRepository.findByTeamName(teamMappingAdd.getTeamName());
		team.setTeam(t1);

		Users u1 = userRepository.findByEmailId(teamMappingAdd.getEmailId());
		team.setUsers(u1);
		if (t1 == null || u1 == null) {
			map.put("fail", "Either Team or Email ID does not exists.");
			return map;
		}
		int count = teamMappingRepository.getCountByTeamIdAndEmailId(team.getUsers().getId(), team.getTeam().getId())
				.get(0);

		if (count == 0) {
			teamMappingRepository.save(team);
			map.put("success", "Team mapping added successfully.");
			return map;
		} else {
			map.put("fail", "Team mapping already exists.");
			return map;
		}
	}

	@Transactional
	@Override
	public Map<String, String> deleteById(TeamMappingDTO teamMapping) {
		Map<String, String> map = new HashMap<>();
		TeamMapping team = new TeamMapping();

		Team t1 = teamRepository.findByTeamName(teamMapping.getTeamName());
		team.setTeam(t1);
		Users u1 = userRepository.findByEmailId(teamMapping.getEmailId());
		team.setUsers(u1);
		if (t1 == null || u1 == null) {
			map.put("fail", "Either Team or Email ID does not exists.");
			return map;
		}
		int count = teamMappingRepository.getCountByEmailId(team.getUsers().getId()).get(0);
		if (count > 1) {
			teamMappingRepository.deleteTeamMappingByUserIdAndTeamId(team.getUsers().getId(), team.getTeam().getId());
			map.put("success", "Team Mapping Record Deleted Successfully.");
			return map;
		} else {
			map.put("fail", "Problem while deleting team mapping. User should have atleast one team.");
			return map;
		}
	}
}
