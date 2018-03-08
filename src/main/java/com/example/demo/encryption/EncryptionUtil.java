package com.example.demo.encryption;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.io.ClassPathResource;

public class EncryptionUtil {
	private SecretKeySpec skeySpec;
	
	public EncryptionUtil(){	
		try {			
			ClassPathResource res = new ClassPathResource("key.key");
			if(res != null){
				File file = res.getFile();
				FileInputStream input = new FileInputStream(file);
				byte[] in = new byte[(int)file.length()];
				input.read(in);
				skeySpec = new SecretKeySpec(in, "AES");
				input.close();
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public byte[] encrypt(String input) 
			throws GeneralSecurityException, NoSuchPaddingException{
	       Cipher cipher = Cipher.getInstance("AES");

	       cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	       return cipher.doFinal(input.getBytes());
		
	}
	
	
	public String decrypt(byte[] input) throws GeneralSecurityException, NoSuchPaddingException{
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, skeySpec);
		return new String(cipher.doFinal(input));
	}
	
}
