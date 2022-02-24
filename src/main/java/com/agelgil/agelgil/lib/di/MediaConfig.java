package com.agelgil.agelgil.lib.di;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MediaConfig {

	@Value("${com.agelgil.agelgil.dbx.access-token}")
	private String appSecret;


	@Bean
	public DbxClientV2 dropboxClient() throws DbxException {
		DbxRequestConfig config = new DbxRequestConfig("agelgill");
		DbxClientV2 client = new DbxClientV2(config, appSecret);
		return client;
	}

}
