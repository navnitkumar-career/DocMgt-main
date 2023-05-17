package com.doc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.doc.entities.Team;
import com.doc.repository.TeamRepository;
import com.doc.serviceimpl.TeamServiceimpl;
import com.doc.userdto.TeamDTO;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TeamServiceTest {

	@InjectMocks
    TeamServiceimpl teamService;
	
	@Mock
	TeamRepository teamRepository;

	@Mock
	ModelMapper modelMapper;
	
	@Test
    public void testFindAll() 
    {
        Team team = new Team(null);
        List<Team> list = new ArrayList<>();
        list.add(team);
        
        when(teamRepository.findAll()).thenReturn(list);
                
        List<TeamDTO> list1 = teamService.getList();
        assertThat(team.getTeamName()).isEqualTo(list1.get(0).getTeamName());
    }
	
	@Test
    public void testAddSuccess() 
    {
		Team team = new Team("Test");
		List<Team> list = new ArrayList<>();
        list.add(team);
        
        TeamDTO teamDTO = new TeamDTO(1, "Test1");
        
        when(teamRepository.findAll()).thenReturn(list);
                
        boolean flag = teamService.add(teamDTO);
        assertThat(flag).isEqualTo(true);
    }
	
	@Test
    public void testAddFail() 
    {
		Team team = new Team("Test");
		List<Team> list = new ArrayList<>();
        list.add(team);
        
        TeamDTO teamDTO = new TeamDTO(1, "Test");
        
        when(teamRepository.findAll()).thenReturn(list);
                
        boolean flag = teamService.add(teamDTO);
        assertThat(flag).isEqualTo(false);
    }
	
	@Test
    public void testUpdateSuccess()
    {
		Team team = new Team("Test");
		List<Team> list = new ArrayList<>();
        list.add(team);
        
        TeamDTO teamDTO = new TeamDTO(1, "Test");
        
        when(teamRepository.findAll()).thenReturn(list);
                
        boolean flag = teamService.update(teamDTO);
        assertThat(flag).isEqualTo(false);
    }
	
	@Test
    public void testDelete() 
    {
        Team team = new Team("Test");
                
        when(teamRepository.findByTeamName("Test")).thenReturn(team);
                
        boolean flag = teamService.deleteById("Test");
        assertThat(flag).isEqualTo(true);
    }
	
	@Test
    public void testDeleteFail() 
    {
        Team team = null;
                
        when(teamRepository.findByTeamName("Test")).thenReturn(team);
                
        boolean flag = teamService.deleteById("Test");
        assertThat(flag).isEqualTo(false);
    }
}
