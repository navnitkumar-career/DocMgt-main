
package com.doc.controller;

import io.github.pixee.security.Filenames;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.doc.entities.Document;
import com.doc.repository.DocumentRepository;
import com.doc.service.DocumentService;

@RestController
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	DocumentService documentService;

	@PostMapping(path = "/upload", produces = "application/json")
	public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file,
			@RequestParam("emailId") String emailId) throws IOException {
		try {
			if (!checkFileValidation(Filenames.toSimpleFileName(file.getOriginalFilename()))) {
				return fileUpload(file, emailId);
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is already exist.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body("There is some issue while file uploading. Please try after some time!");
		}

	}

	@GetMapping(path = "/count/{fileName}", produces = "application/json")
	public void count(@PathVariable("fileName") String fileName, HttpServletResponse response) throws IOException {

		Map<String, Integer> map = documentService.wordCount(fileName);
		if (map.size() == 0) {
			response.setStatus(403);
			response.getWriter().println("File does not exist!");
		} else {
			response.setStatus(200);
			response.getWriter().println(map);
		}
	}
	
	private ResponseEntity<String> fileUpload(MultipartFile file, String emailId) throws IOException {
		byte[] bytes = file.getBytes();
		String fileData = new String(bytes);
		fileData.replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", " ");
		boolean flag = documentService.upload(fileData, emailId, file.getOriginalFilename());
		if(flag) {
			return ResponseEntity.status(HttpStatus.OK).body("File is uploaded Successfully.");
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email ID does not exist.");
		}
	}

	private boolean checkFileValidation(String fileName) {
		boolean flag = false;
		List<Document> document = documentRepository.findAll();
		if(document != null) {
			for (Document doc : document) {
				if (doc.getFileName().equalsIgnoreCase(fileName)) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
		}
		return flag;
	}

	@GetMapping(path = "delete/{fileName}", produces = "application/json")
	public ResponseEntity<String> delete(@PathVariable("fileName") String fileName) {		
		Map<String, String> map = documentService.deleteByFileName(fileName);
		if (map.containsKey("success")) {
			return ResponseEntity.ok(map.get("success"));
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map.get("fail"));
		}
	}
}
