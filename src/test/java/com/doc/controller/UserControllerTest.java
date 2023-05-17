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

import com.doc.entities.Users;
import com.doc.service.UserService;
import com.doc.userdto.UserDTO;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class UserControllerTest {

	@InjectMocks
    UserController userController;
     
    @Mock
    UserService userService;
    
    @Test
    public void testFindAll() 
    {
        Users users1 = new Users("test1@gmail.com");
        List<UserDTO> list = new ArrayList<>();
        list.add(new UserDTO(1, "test1@gmail.com", null));
        
        when(userService.getList()).thenReturn(list);
        
        List<UserDTO> list1 = userController.getList();
        assertThat(users1.getEmailId()).isEqualTo(list1.get(0).getEmailId());
    }
    
    @Test
    public void testAddSuccess() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
         
        when(userService.add(any(UserDTO.class))).thenReturn(true);
         
        UserDTO userDTO = new UserDTO(1, "test1@gmail.com", null);
        when(userService.validRequest(userDTO)).thenReturn(true);
       
        ResponseEntity<String> responseEntity = userController.add(userDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void testAddFail() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
            
        UserDTO userDTO = new UserDTO(1, "test1@gmail.com", null);
        when(userService.validRequest(userDTO)).thenReturn(false);
       
        ResponseEntity<String> responseEntity = userController.add(userDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
    
    @Test
    public void testAddAlreadyExist() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
            
        UserDTO userDTO = new UserDTO(1, "test1@gmail.com", null);
        when(userService.validRequest(userDTO)).thenReturn(true);
        when(userService.add(userDTO)).thenReturn(false);
   
        ResponseEntity<String> responseEntity = userController.add(userDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
    
    @Test
    public void testUpdateSuccess() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        UserDTO userDTO = new UserDTO(1, "test1@gmail.com", null);
        when(userService.validRequest(userDTO)).thenReturn(true);
        when(userService.update(userDTO)).thenReturn(true);
        
        ResponseEntity<String> responseEntity = userController.update(userDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void testUpdateFail() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        UserDTO userDTO = new UserDTO(1, "test1@gmail.com", null);
        when(userService.validRequest(userDTO)).thenReturn(false);
        
        ResponseEntity<String> responseEntity = userController.update(userDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
    
    @Test
    public void testUpdateNotExist() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        UserDTO userDTO = new UserDTO(1, "test1@gmail.com", null);
        when(userService.validRequest(userDTO)).thenReturn(true);
        when(userService.update(userDTO)).thenReturn(false);
        
        ResponseEntity<String> responseEntity = userController.update(userDTO);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
    
    @Test
    public void testDeleteSuccess() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        when(userService.deleteById("test1@gmail.com")).thenReturn(true);
        
        ResponseEntity<String> responseEntity = userController.delete("test1@gmail.com");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
    
    @Test
    public void testDeleteFail() 
    {
    	MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
                  
        when(userService.deleteById("test1@gmail.com")).thenReturn(false);
        
        ResponseEntity<String> responseEntity = userController.delete("test1@gmail.com");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
}
