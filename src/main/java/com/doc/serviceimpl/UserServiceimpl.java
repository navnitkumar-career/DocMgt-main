package com.doc.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.doc.entities.Users;
import com.doc.repository.UserRepository;
import com.doc.service.UserService;
import com.doc.userdto.UserDTO;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Transactional
	@Override
	public List<UserDTO> getList() {

		List<Users> userList = userRepository.findAll();
		List<UserDTO> user = userList.stream().map(t -> {
			UserDTO users = new UserDTO();
			modelMapper.map(t, users);
			return users;
		}).collect(Collectors.toList());
		return user;
	}

	@Transactional
	@Override
	public boolean add(UserDTO userAdd) {
		List<Users> userList = userRepository.findAll();
		boolean flag = true;

		if (userList != null) {
			for (Users user1 : userList) {
				if (user1.getEmailId().toLowerCase().equals(userAdd.getEmailId().toLowerCase())) {
					flag = false;
					break;
				} else {
					flag = true;
				}
			}
		}

		if (flag) {
			Users user = new Users();
			modelMapper.map(userAdd, user);

			user.setDateTime(LocalDateTime.now());
			userRepository.save(user);
			modelMapper.map(user, userAdd);
			return flag;
		}
		return flag;
	}

	@Transactional
	@Override
	public boolean update(UserDTO userEdit) {
		List<Users> userList = userRepository.findAll();
		boolean flag = false;
		boolean idFlag = false;
		boolean emailFlag = false;
		
		for (Users user1 : userList) {
			if (!user1.getEmailId().equals(userEdit.getEmailId())) {
				emailFlag = true;
			} else {
				emailFlag = false;
				break;
			}
		}
		for (Users user1 : userList) {
			if (user1.getId() == userEdit.getId()) {
				if(!user1.getEmailId().equalsIgnoreCase(userEdit.getEmailId())) {
					idFlag = true;	
				}
				break;
			}
		}
		if (emailFlag && idFlag) {
			Users user = userRepository.getById(userEdit.getId());
			user.setEmailId(userEdit.getEmailId());
			userRepository.save(user);
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteById(String emailId) {
		Users user = userRepository.findByEmailId(emailId);
		if (user == null) {
			return false;
		} else {
			userRepository.deleteById(user.getId());
			return true;
		}
	}

	@Override
	public boolean validRequest(UserDTO user) {
		return isValidEmail(user.getEmailId());
	}

	private boolean isValidEmail(String emailId) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailId);
		return matcher.matches();
	}
}
