package com.doc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
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
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.doc.entities.Team;
import com.doc.service.TeamService;
import com.doc.userdto.TeamDTO;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TeamControllerTest {

	@InjectMocks
	TeamController teamController;
	
	@Mock
	TeamService teamService;
	
	@Test
    public void testFindAll() 
    {
        Team team = new Team("Test");
        List<TeamDTO> list = new ArrayList<>();
        list.add(new TeamDTO(1, "Test"));
        
        when(teamService.getList()).thenReturn(list);
        
        List<TeamDTO> list1 = teamController.getList();
        assertThat(team.getTeamName()).isEqualTo(list1.get(0).getTeamName());
    }
	
	@Test
    public void testAddSuccess() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(teamService.add(any(TeamDTO.class))).thenReturn(true);
         
        TeamDTO teamDTO = new TeamDTO(1, "Test");
       
        ResponseEntity<String> responseEntity = teamController.add(teamDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
	
	@Test
    public void testAddAlreadyExist() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
            
        TeamDTO teamDTO = new TeamDTO(1, "Test");
        when(teamService.add(teamDTO)).thenReturn(false);
   
        ResponseEntity<String> responseEntity = teamController.add(teamDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
	
	@Test
    public void testUpdateSuccess() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        TeamDTO teamDTO = new TeamDTO(1, "Test");
        when(teamService.update(teamDTO)).thenReturn(true);
        
        ResponseEntity<String> responseEntity = teamController.update(teamDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
	
	@Test
    public void testUpdateNotExist() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        TeamDTO teamDTO = new TeamDTO(1, "Test");
        when(teamService.update(teamDTO)).thenReturn(false);
        
        ResponseEntity<String> responseEntity = teamController.update(teamDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
	
	@Test
    public void testDeleteSuccess() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        when(teamService.deleteById("Test")).thenReturn(true);
        
        ResponseEntity<String> responseEntity = teamController.delete("Test");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void testDeleteFail() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        when(teamService.deleteById("Test")).thenReturn(false);
        
        ResponseEntity<String> responseEntity = teamController.delete("Test");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
}
