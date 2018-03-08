package com.example.demo.rabbitmq;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PublishMessage {

	
	private static final Logger logger = LogManager.getLogger(PublishMessage.class);
	
	@Autowired
	private Environment env;
	
	public void sendMessage(String Message, String queueName) {
		
		
		ConnectionFactory factory = new ConnectionFactory();
		Channel channel = null;
		Connection conn = null;
				
		
	//	try {
			//factory.setUri("amqp://guest:guest@tvra-win-es-081:5672");
			
			factory.setHost(env.getProperty("spring.rabbitmq.host"));
			factory.setUsername(env.getProperty("spring.rabbitmq.username"));
			factory.setPassword(env.getProperty("spring.rabbitmq.password"));
			factory.setPort(Integer.parseInt(env.getProperty("spring.rabbitmq.port")));
			
						
			
		/*} catch (KeyManagementException e) {
			
			logger.error(e.toString());
		} catch (NoSuchAlgorithmException e) {
		
			logger.error(e.toString());
		} catch (URISyntaxException e) {
			
			logger.error(e.toString());
		}*/
				
		
		try {
			
			conn = factory.newConnection();
			logger.info("Connection established ");
			channel = conn.createChannel();
			
			logger.info("Channel created");
			
			String exchangeName = "somethingNew_exchange";			
			String routingKey = "SomethingNew";
			
			/*channel.exchangeDeclare(exchangeName, "direct", true);
			String queueName = channel.queueDeclare().getQueue();
			channel.queueBind(queueName, exchangeName, routingKey);
			*/
			
			channel.exchangeDeclare(exchangeName, "direct", true);
			channel.queueDeclare(queueName, true, false, false, null);
			channel.queueBind(queueName, exchangeName, routingKey);
			
			byte[] messageBodyBytes = Message.getBytes();
			
			//channel.basicPublish(exchangeName, routingKey, null, messageBodyBytes);
			channel.basicPublish(exchangeName, routingKey,
		             new AMQP.BasicProperties.Builder()
		               .contentType("text/plain")
		               .deliveryMode(2)
		               .priority(1)
		               .userId(env.getProperty("spring.rabbitmq.username"))
		               .build(),
		               messageBodyBytes);
			
			
			channel.close();
			conn.close();
			logger.info("Channel and connection closed");
			
		} catch (IOException e) {
			
			logger.error(e.toString());
			
		} catch (TimeoutException e) {
			
			logger.error(e.toString());
		}
	}

}
