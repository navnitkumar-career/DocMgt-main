package com.doc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.doc.entities.Team;
import com.doc.entities.TeamMapping;
import com.doc.entities.Users;
import com.doc.repository.TeamMappingRepository;
import com.doc.repository.TeamRepository;
import com.doc.repository.UserRepository;
import com.doc.serviceimpl.TeamMappingServiceimpl;
import com.doc.userdto.TeamMappingDTO;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TeamMappingServiceTest {

	@InjectMocks
    TeamMappingServiceimpl tmService;
	
	@Mock
	TeamMappingRepository tmRepository;
	
	@Mock
	TeamRepository teamRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	ModelMapper modelMapper;
	
	@Test
    public void testFindAll() 
    {
        TeamMapping team = new TeamMapping(new Users(null), new Team(null));
        List<TeamMapping> list = new ArrayList<>();
        list.add(team);
        
        when(tmRepository.findAll()).thenReturn(list);
                
        List<TeamMappingDTO> list1 = tmService.getList();
        assertThat(team.getTeam().getTeamName()).isEqualTo(list1.get(0).getTeamName());
        assertThat(team.getUsers().getEmailId()).isEqualTo(list1.get(0).getEmailId());
    }
	
	@Test
    public void testAddSuccess() 
    {
		TeamMappingDTO teamMappingDTO = getTeamMappingDTO();
		Team team = new Team();
		
		modelMapper.map(teamMappingDTO, team);
		when(teamRepository.findByTeamName(teamMappingDTO.getTeamName())).thenReturn(getTeam());
		when(userRepository.findByEmailId(teamMappingDTO.getEmailId())).thenReturn(getUser());
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> tmService.add(teamMappingDTO));
    }
	
	@Test
    public void testDeleteSuccess() 
    {
		TeamMappingDTO teamMappingDTO = getTeamMappingDTO();
		Team team = new Team();
		
		modelMapper.map(teamMappingDTO, team);
		when(teamRepository.findByTeamName(teamMappingDTO.getTeamName())).thenReturn(getTeam());
		when(userRepository.findByEmailId(teamMappingDTO.getEmailId())).thenReturn(getUser());
		Assertions.assertThrows(IndexOutOfBoundsException.class, () -> tmService.deleteById(teamMappingDTO));
    }
	
	@Test
    public void testDeleteFail() 
    {
		TeamMappingDTO teamMappingDTO = getTeamMappingDTO();
		Team team = new Team();
		
		modelMapper.map(teamMappingDTO, team);
		when(teamRepository.findByTeamName(teamMappingDTO.getTeamName())).thenReturn(null);
		when(userRepository.findByEmailId(teamMappingDTO.getEmailId())).thenReturn(null);

		tmService.deleteById(teamMappingDTO);
    }
	
	private Users getUser() {
		return new Users("test@gmail.com");
	}
	
	private Team getTeam() {
		return new Team("Test");
	}
	
	private TeamMappingDTO getTeamMappingDTO() {
		return new TeamMappingDTO(1, 1, "Test", 1, "test@gmail.com");
	}
	
}
