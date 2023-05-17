package com.doc.service;

import java.util.Map;

public interface DocumentService {

	public boolean upload(String fileData, String emailId, String fileName);
	
	public Map<String, Integer> wordCount(String fileData);
	
	public Map<String, String> deleteByFileName(String fileName);
}
