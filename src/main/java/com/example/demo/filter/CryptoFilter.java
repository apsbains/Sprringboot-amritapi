package com.example.demo.filter;


import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.stereotype.Component;

import com.example.demo.encryption.EncryptionUtil;



@Component
public class CryptoFilter implements Filter {

	  public static final String X_CLACKS_OVERHEAD = "X-Clacks-Overhead";
	  private static final Logger logger = LogManager.getLogger(CryptoFilter.class);
	  
	  @Override
	  public void doFilter(ServletRequest req, ServletResponse res,
	      FilterChain chain) throws IOException, ServletException {

		  
	    /*HttpServletResponse response = (HttpServletResponse) res;
	    response.setHeader(X_CLACKS_OVERHEAD, "GNU Terry Pratchett");
	    String resP =  response.toString();
	    */
		  HttpServletRequest httpServletRequest = (HttpServletRequest) req;
	      HttpServletResponse httpServletResponse = (HttpServletResponse) res;
	        BufferedServletResponseWrapper bufferedResponse = new BufferedServletResponseWrapper(
	                httpServletResponse);

	        // pass the wrappers on to the next entry
	        chain.doFilter(httpServletRequest, bufferedResponse);

	        String responseData = bufferedResponse.getResponseData();
	        logger.info("responsedata " + responseData); 
	        String resultString=null;     
	        if (responseData.contains("self") 
	        		|| responseData.contains("apiKeyVehicle") 
	        		|| responseData.isEmpty()//) {
	        		|| responseData.contains("New Get function")) {
	        //Do the encryption 
	        //String encryptedResponseData = encrypt(responseData);
	        	logger.info("Inne i If ");
			try {
				//resultString = EncryptionUtil.decrypt(EncryptionUtil.encrypt(responseData));
				EncryptionUtil encrypt = new EncryptionUtil();
				//resultString = encrypt.decrypt(encrypt.encrypt(responseData));
				resultString = new String(encrypt.encrypt(responseData));
			} catch (GeneralSecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        } else {
	        	
				try {
					//resultString = EncryptionUtil.decrypt(EncryptionUtil.encrypt(responseData));
					EncryptionUtil encrypt = new EncryptionUtil();
					resultString = encrypt.decrypt(encrypt.encrypt(responseData));
					
					logger.info("Inne i else ");
					//resultString = new String(encrypt.encrypt(responseData));
				} catch (GeneralSecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        }
	        
	        OutputStream outputStream = httpServletResponse.getOutputStream();
	        outputStream.write(resultString.getBytes());
	        outputStream.flush();
	        outputStream.close();
	        
	    //chain.doFilter(req, res);
	    //logger.info("Test response " + resP);
	     
	  }

	  @Override
	  public void destroy() {}

	  @Override
	  public void init(FilterConfig arg0) throws ServletException {}

	}
