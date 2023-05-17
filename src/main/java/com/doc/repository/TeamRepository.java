package com.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.doc.entities.Team;

public interface TeamRepository extends JpaRepository<Team, Integer> {
	
	Team findByTeamName(String teamName);
}
