package com.doc.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.doc.repository.DocumentRepository;
import com.doc.service.DocumentService;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class DocumentControllerTest {

	@InjectMocks
	DocumentController docController;
	
	@Mock
	DocumentService docService;
	
	@Mock
	DocumentRepository docRepository;
	
	@Test
    public void testUploadSuccess() throws IOException 
    {
		MultipartFile file = new MockMultipartFile("test.txt",
	            "fileThatDoesNotExists.txt",
	            "text/plain",
	            "This is a dummy file content".getBytes(StandardCharsets.UTF_8));
		
        when(docRepository.findAll()).thenReturn(null);
        when(docService.upload(anyString(), anyString(), anyString())).thenReturn(true);
        
        ResponseEntity<String> responseEntity = docController.upload(file, "test@gmail.com");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
    }
	
	@Test
    public void testUploadFail() throws IOException 
    {
        ResponseEntity<String> responseEntity = docController.upload(null, null);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
	
	@Test
    public void testDeleteFail() throws IOException 
    {
		Map<String, String> map = new HashMap<>();
		map.put("success", "Deleted");
		
        ResponseEntity<String> responseEntity = docController.delete("test.txt");
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
    }
	
}
