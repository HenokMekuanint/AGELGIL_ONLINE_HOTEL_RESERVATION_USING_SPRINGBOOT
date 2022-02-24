package com.agelgil.agelgil.lib.exceptions;


public class InvalidTokenException extends RuntimeException{

	public InvalidTokenException(){
		super();
	}

	public InvalidTokenException(String msg){
		super(msg);
	}
	
}
