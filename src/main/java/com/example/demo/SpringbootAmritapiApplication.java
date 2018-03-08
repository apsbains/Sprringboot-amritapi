package com.example.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringbootAmritapiApplication {

	private static final Logger logger = LogManager.getLogger(SpringbootAmritapiApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootAmritapiApplication.class, args);
	}
	
/*	@Override
	public void run(String[] args) throws Exception {
		   
		logger.debug("Debugging log");
	        logger.info("Info log");
	        logger.warn("Hey, This is a warning!");
	        logger.error("Oops! We have an Error. OK");
	        logger.fatal("Damn! Fatal error. Please fix me.");
	        
	}
	*/
}
