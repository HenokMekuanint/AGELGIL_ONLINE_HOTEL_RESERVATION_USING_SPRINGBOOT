package com.agelgil.agelgil.client.di;

import com.agelgil.agelgil.client.data.converters.LongToCartItemConverter;
import com.agelgil.agelgil.client.data.repositories.CartItemRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ClientConfigs {

	@Bean
	public LongToCartItemConverter longToCartItemConverter(CartItemRepository repository){
		return new LongToCartItemConverter(repository);
	}

}
