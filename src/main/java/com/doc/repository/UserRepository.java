package com.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.doc.entities.Users;

@Repository
public interface  UserRepository extends JpaRepository<Users, Integer>{

	Users findByEmailId(String emailId);

}
