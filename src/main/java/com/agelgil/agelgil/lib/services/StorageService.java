package com.agelgil.agelgil.lib.services;

import org.springframework.web.multipart.MultipartFile;

public abstract class StorageService {

	public abstract String store(MultipartFile file);

	public abstract String getUrl(String filename);
	
	protected String generateFileName(MultipartFile file){
		return String.format("%d_%s", System.currentTimeMillis(), file.getOriginalFilename());
	}

	
	
}
