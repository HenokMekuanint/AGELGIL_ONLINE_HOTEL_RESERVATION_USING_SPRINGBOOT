package com.agelgil.agelgil.lib.services;


import java.io.ByteArrayInputStream;
import java.io.IOException;

import com.agelgil.agelgil.lib.exceptions.StorageException;
import com.dropbox.core.DbxException;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.sharing.SharedLinkMetadata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class DropBoxService extends StorageService{

	@Autowired
	private DbxClientV2 dbxClient;	

	private final String ROOT = "/agelgill";

	@Override
	public String store(MultipartFile file) {
		String fileName = String.format("%s/%s", ROOT, generateFileName(file));
		
		try{
			ByteArrayInputStream inputStream = new ByteArrayInputStream(file.getBytes());
			dbxClient.files().uploadBuilder(fileName).uploadAndFinish(inputStream);
			inputStream.close();

			SharedLinkMetadata sharedLinkMetadata = dbxClient.sharing().createSharedLinkWithSettings(fileName);
			return sharedLinkMetadata.getUrl()+"&raw=1";
		}
		catch(IOException ex){
			throw new StorageException(ex.getMessage());
		}
		catch(DbxException ex){
			throw new StorageException(ex.getMessage());
		}
		
	}

	@Override
	public String getUrl(String filename) {

		return filename;

	}
	


	
}
