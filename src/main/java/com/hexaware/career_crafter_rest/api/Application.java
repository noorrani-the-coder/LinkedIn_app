package com.hexaware.career_crafter_rest.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


/*
 * Date: 2-06-2025
 * Developer: Yamini B
 * Project Name: Career Crafter backend development
 */
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		
		
	}
	
	

	    @Bean
	    public RestTemplate restTemplate() {
	        return new RestTemplate();
	    }
	


}
