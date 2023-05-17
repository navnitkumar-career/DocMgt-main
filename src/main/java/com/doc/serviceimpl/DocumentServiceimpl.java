package com.doc.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.doc.entities.Document;
import com.doc.entities.Users;
import com.doc.repository.DocumentRepository;
import com.doc.repository.UserRepository;
import com.doc.service.DocumentService;

@Service
public class DocumentServiceimpl implements DocumentService {

	@Autowired
	DocumentRepository documentRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean upload(String fileData, String emailId, String fileName) {
		Document doc = new Document();
		Users user = userRepository.findByEmailId(emailId);
		
		if (user == null) {
			return false;
		} else {
			doc.setUsers(user);
			byte[] bytes;
			bytes = fileData.getBytes();
			String completeData = new String(bytes);
			completeData.replaceAll("\\s", "").replaceAll("[^a-zA-Z0-9]", " ");
			doc.setDocData(completeData);
			doc.setFileName(fileName);
			documentRepository.save(doc);
			return true;
		}
	}

	@Override
	public Map<String, Integer> wordCount(String fileName) {
		Document doc = documentRepository.findByFileName(fileName);
		Map<String, Integer> map = new HashMap<>();
		if (doc == null) {
			return map;
		} else {
			String wordArray[] = doc.getDocData().toString().trim().split(" ");

			for (int i = 0; i < wordArray.length; i++) {
				wordArray[i] = wordArray[i].replace("\n", "").replace("\r", "").replace(",", " ").replace("'", " ").replace(":", " ").replace("?", " ").replace(".", " ").replace("’", "")
						.replace("'", "").replace("’", "").toLowerCase().trim();
				if (!wordArray[i].equalsIgnoreCase("The") && !wordArray[i].equalsIgnoreCase("Me")
						&& !wordArray[i].equalsIgnoreCase("You") && !wordArray[i].equalsIgnoreCase("I")
						&& !wordArray[i].equalsIgnoreCase("Of") && !wordArray[i].equalsIgnoreCase("And")
						&& !wordArray[i].equalsIgnoreCase("A") && !wordArray[i].equalsIgnoreCase("We")
						&& !wordArray[i].equalsIgnoreCase("") && !wordArray[i].equalsIgnoreCase(" ")) {
					if (map.containsKey(wordArray[i])) {
						map.put(wordArray[i].replace("\\?", " ").trim(), map.get(wordArray[i]) + 1);
					} else {
						map.put(wordArray[i].replace("\\?", " ").trim(), 1);
					}
				}
			}
		}
		return map;
	}

	@Transactional
	@Override
	public Map<String, String> deleteByFileName(String fileName) {
		List<Document> doc = documentRepository.findAll();
		Map<String, String> map = new HashMap<>();
		boolean flag = false;
		if (doc != null) {
			for (Document document : doc) {
				if (document.getFileName().equalsIgnoreCase(fileName)) {
					flag = true;
					break;
				} else {
					flag = false;
				}
			}
		}

		if (flag) {
			documentRepository.deleteDocumentByFileName(fileName);
			map.put("success", "Document File Deleted Successfully.");
		} else {
			map.put("fail", "File does not exist!");
			return map;
		}
		return map;
	}
}
