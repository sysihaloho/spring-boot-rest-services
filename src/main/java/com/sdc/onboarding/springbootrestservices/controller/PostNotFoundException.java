package com.sdc.onboarding.springbootrestservices.controller;

public class PostNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PostNotFoundException(String message) {
		super(message);
	}
}
