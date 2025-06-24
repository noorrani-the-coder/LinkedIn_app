package com.hexaware.career_crafter_rest.api.exception;

public class JobNotFoundException extends RuntimeException{
	public JobNotFoundException(String message) {
        super(message);
    }

}
