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

import com.doc.entities.Users;
import com.doc.repository.UserRepository;
import com.doc.serviceimpl.UserServiceimpl;
import com.doc.userdto.UserDTO;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class UserServiceTest {

	@InjectMocks
    UserServiceimpl userService;
	
	@Mock
	UserRepository userRepository;

	@Mock
	ModelMapper modelMapper;
	
	@Test
    public void testFindAll() 
    {
        Users users = new Users(null);
        List<Users> list = new ArrayList<>();
        list.add(users);
        
        when(userRepository.findAll()).thenReturn(list);
                
        List<UserDTO> list1 = userService.getList();
        assertThat(users.getEmailId()).isEqualTo(list1.get(0).getEmailId());
    }
	
	@Test
    public void testAddSuccess() 
    {
        Users users = new Users("test1@gmail.com");
        List<Users> list = new ArrayList<>();
        list.add(users);
        
        UserDTO userDTO = new UserDTO(1, "test@gmail.com", null);
        
        when(userRepository.findAll()).thenReturn(list);
                
        boolean flag = userService.add(userDTO);
        assertThat(flag).isEqualTo(true);
    }
	
	@Test
    public void testAddAlreadyExist() 
    {
        Users users = new Users("test@gmail.com");
        List<Users> list = new ArrayList<>();
        list.add(users);
        
        UserDTO userDTO = new UserDTO(1, "test@gmail.com", null);
        
        when(userRepository.findAll()).thenReturn(list);
                
        boolean flag = userService.add(userDTO);
        assertThat(flag).isEqualTo(false);
    }
	
	@Test
    public void testUpdateSuccess() 
    {
        Users users = new Users("test@gmail.com");
        List<Users> list = new ArrayList<>();
        list.add(users);
        
        UserDTO userDTO = new UserDTO(1, "test@gmail.com", null);
        
        when(userRepository.findAll()).thenReturn(list);
                
        boolean flag = userService.update(userDTO);
        assertThat(flag).isEqualTo(false);
    }
	
	@Test
    public void testDelete() 
    {
        Users users = new Users("test@gmail.com");
                
        when(userRepository.findByEmailId("test@gmail.com")).thenReturn(users);
                
        boolean flag = userService.deleteById("test@gmail.com");
        assertThat(flag).isEqualTo(true);
    }
	
	@Test
    public void validRequest() 
    {
		UserDTO userDTO = new UserDTO(1, "test@gmail.com", null);
                                
        boolean flag = userService.validRequest(userDTO);
        assertThat(flag).isEqualTo(true);
    }
}
