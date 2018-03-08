package com.example.demo.encryption;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class KeyGeneratorUtil {

	/**
	 * @param args keyfile name
	 */

	public static void main(String args[]) throws NoSuchAlgorithmException,
			IOException {

		if (args.length < 1) {
			System.out.println("usage: java KeyGeneratorUtil filename");
			System.exit(0);
		}

		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		SecureRandom random = new SecureRandom(); // cryptograph. secure random
		keyGen.init(random);
		SecretKey secretKey = keyGen.generateKey();
		FileOutputStream output = new FileOutputStream(args[0]);
		output.write(secretKey.getEncoded());
		output.close();

	}

}
