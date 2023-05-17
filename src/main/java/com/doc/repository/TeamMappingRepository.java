package com.doc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doc.entities.TeamMapping;

@Repository
public interface TeamMappingRepository extends JpaRepository<TeamMapping, Integer>{

	@Modifying
	@Query(value = "delete from team_mapping where users_id = :userId  and team_id = :teamId", nativeQuery = true)
	void deleteTeamMappingByUserIdAndTeamId(@Param("userId") int userId, @Param("teamId") int teamId);
	
	@Modifying
	@Query(value = "select count(*) as count from team_mapping where users_id= :userId and team_id= :teamId", nativeQuery = true)
	List<Integer> getCountByTeamIdAndEmailId(@Param("userId") int userId, @Param("teamId") int teamId);

	@Modifying
	@Query(value = "select count(*) as count from team_mapping where users_id = :userId", nativeQuery = true)
	List<Integer> getCountByEmailId(@Param("userId") int userId);
}
