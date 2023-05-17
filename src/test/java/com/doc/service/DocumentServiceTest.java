package com.doc.service;

import static org.mockito.ArgumentMatchers.anyString;
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

import com.doc.entities.Document;
import com.doc.entities.Users;
import com.doc.repository.DocumentRepository;
import com.doc.repository.UserRepository;
import com.doc.serviceimpl.DocumentServiceimpl;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DocumentServiceTest {
	
	@InjectMocks
	DocumentServiceimpl documentService;

	@Mock
	DocumentRepository documentRepository;

	@Mock
	UserRepository userRepository;

	@Mock
	ModelMapper modelMapper;
	
	@Test
    public void testUpload() 
    {
		when(userRepository.findByEmailId(anyString())).thenReturn(getUser());
		documentService.upload("Hello There", "test@gmail.com", "test.txt");
    }
	
	@Test
    public void testWordCount() 
    {
		Document doc = new Document();
		doc.setDocData("Hello There The There");
		doc.setFileName("Test.txt");
		doc.setUsers(getUser());
		
		when(documentRepository.findByFileName(anyString())).thenReturn(doc);
		documentService.wordCount("Test.txt");
    }
	
	@Test
    public void testDeleteSuccess() 
    {
		Document doc = new Document();
		doc.setDocData("Hello There The There");
		doc.setFileName("Test.txt");
		doc.setUsers(getUser());
		
		List<Document> docList = new ArrayList<>();
		docList.add(doc);
		
		when(documentRepository.findAll()).thenReturn(docList);
		documentService.deleteByFileName("Test.txt");
    }
	
	@Test
    public void testDeleteFail() 
    {
		Document doc = new Document();
		doc.setDocData("Hello There The There");
		doc.setFileName("Test.txt");
		doc.setUsers(getUser());
		
		List<Document> docList = new ArrayList<>();
		docList.add(doc);
		
		when(documentRepository.findAll()).thenReturn(docList);
		documentService.deleteByFileName("Test1.txt");
    }
	
	private Users getUser() {
		return new Users("test@gmail.com");
	}
}
