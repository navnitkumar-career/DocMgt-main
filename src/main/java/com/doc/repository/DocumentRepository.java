package com.doc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.doc.entities.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

	Document findByUsersId(int userId);

	Document findByFileName(String fileName);

	@Modifying
	@Query(value = "delete from document where file_name= :fileName", nativeQuery = true)
	void deleteDocumentByFileName(@Param("fileName") String fileName);

}
