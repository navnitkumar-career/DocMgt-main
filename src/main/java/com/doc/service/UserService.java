package com.doc.service;

import java.util.List;

import com.doc.userdto.UserDTO;

public interface UserService {
	
	public List<UserDTO> getList();

	public boolean update(UserDTO user);

	public boolean add(UserDTO user);

	public boolean deleteById(String emailId);
	
	public boolean validRequest(UserDTO user);

}
