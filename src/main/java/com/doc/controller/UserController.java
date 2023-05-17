package com.doc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doc.service.UserService;
import com.doc.userdto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping(path = "getlist", produces = "application/json")
	public List<UserDTO> getList() {
		return userService.getList();
	}

	@PostMapping(path = "add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> add(@Valid @RequestBody UserDTO user) {
		if (userService.validRequest(user)) {
			boolean flag = userService.add(user);
			if (flag) {
				return ResponseEntity.ok("User is added.");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Id already exist.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Id is not valid or blank.");
		}
	}

	@PutMapping(path = "update", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> update(@Valid @RequestBody UserDTO user) {

		if (userService.validRequest(user)) {
			boolean flag = userService.update(user);
			if (flag) {
				return ResponseEntity.ok("User is Updated.");
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID does not exists or EmailId already exist.");
			}
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Id is not valid or blank.");
		}
	}

	@GetMapping(path = "delete/{emailId}", produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable("emailId") String emailId) {
		boolean flag = userService.deleteById(emailId);
		if (flag) {
			return ResponseEntity.ok("Deleted Successfully");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email Id does not exist.");
		}

	}

}
