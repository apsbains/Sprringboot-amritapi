package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.service.PublishMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value= "/message", produces = "application/json")
@Api(value="message", description="Operations in message")
public class PublishMessageController {

	private static final Logger logger = LogManager.getLogger(PublishMessageController.class);
	
	@Autowired
	private PublishMessageService pubMsgSvc;
	
	@RequestMapping(value = "/sendMessage", method = RequestMethod.POST, consumes = "application/json")
	@ApiOperation(value = "Send message to Rabbit Mq")
	public ResponseEntity<String> sendMessage(@RequestBody String Message, String queueName) {
		
		logger.info("Message " + Message + " name " + queueName);
		pubMsgSvc.sendMessage(Message, queueName);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}


}
