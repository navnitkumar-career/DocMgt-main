package com.doc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.doc.entities.TeamMapping;
import com.doc.entities.Users;
import com.doc.service.TeamMappingService;
import com.doc.userdto.TeamMappingDTO;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class TeamMappingControllerTest {

	@InjectMocks
	TeamMappingController tmController;
	
	@Mock
	TeamMappingService tmService;
	
	@Test
    public void testFindAll() 
    {
        TeamMapping teamMapping = new TeamMapping(getUser(), getTeam());
        List<TeamMappingDTO> list = getTeamMappingDTOList();
        
        when(tmService.getList()).thenReturn(list);
        
        List<TeamMappingDTO> list1 = tmController.getList();
        assertThat(teamMapping.getUsers().getEmailId()).isEqualTo(list1.get(0).getEmailId());
        assertThat(teamMapping.getTeam().getTeamName()).isEqualTo(list1.get(0).getTeamName());
    }
	
	@Test
    public void testAddSuccess() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Map<String, String> map = new HashMap<>();
        map.put("success", "Team Mapping added");
        when(tmService.add(any(TeamMappingDTO.class))).thenReturn(map);
               
        ResponseEntity<String> responseEntity = tmController.add(getTeamMappingDTO());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
	
	@Test
    public void testAddFail() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Map<String, String> map = new HashMap<>();
        map.put("fail", "There is some issue while adding");
        when(tmService.add(any(TeamMappingDTO.class))).thenReturn(map);
               
        ResponseEntity<String> responseEntity = tmController.add(getTeamMappingDTO());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
	
	@Test
    public void testDeleteSuccess() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Map<String, String> map = new HashMap<>();
        map.put("success", "Team Mapping deleted");
        when(tmService.deleteById(any(TeamMappingDTO.class))).thenReturn(map);
               
        ResponseEntity<String> responseEntity = tmController.delete(getTeamMappingDTO());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
	
	@Test
    public void testDeleteFail() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        
        Map<String, String> map = new HashMap<>();
        map.put("fail", "There is some issue while deleting");
        when(tmService.deleteById(any(TeamMappingDTO.class))).thenReturn(map);
               
        ResponseEntity<String> responseEntity = tmController.delete(getTeamMappingDTO());
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
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
	
	private List<TeamMappingDTO> getTeamMappingDTOList() {
		List<TeamMappingDTO> list = new ArrayList<>();
		list.add(new TeamMappingDTO(1, 1, "Test", 1, "test@gmail.com"));
		return list;
	}
}
