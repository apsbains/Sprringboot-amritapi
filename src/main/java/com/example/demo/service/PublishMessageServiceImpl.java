package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.rabbitmq.PublishMessage;

@Service
public class PublishMessageServiceImpl implements PublishMessageService {

	 @Autowired
	 PublishMessage pubMsg;

	public void sendMessage(String Message, String queueName) {
		pubMsg.sendMessage(Message, queueName);
	}

}
