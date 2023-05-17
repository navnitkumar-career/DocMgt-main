package com.doc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.doc.service.TeamMappingService;
import com.doc.userdto.TeamMappingDTO;

@RestController
@RequestMapping("/teammapping")
public class TeamMappingController {

	@Autowired
	TeamMappingService teamMappingService;

	@GetMapping(path = "getlist", produces = "application/json")
	public List<TeamMappingDTO> getList() {
		return teamMappingService.getList();
	}

	@PostMapping(path = "add", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> add(@RequestBody TeamMappingDTO team) {
		Map<String, String> map = teamMappingService.add(team);
		if (map.containsKey("success")) {
			return ResponseEntity.ok(map.get("success"));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map.get("fail"));
		}
	}

	@GetMapping(path = "delete", produces = "application/json")
	public ResponseEntity<String> delete(@RequestBody TeamMappingDTO teamMapping) {
		Map<String, String> map = teamMappingService.deleteById(teamMapping);	
		if (map.containsKey("success")) {
			return ResponseEntity.ok(map.get("success"));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map.get("fail"));
		}
	}

}
