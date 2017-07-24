package com.github.error418.skyswitch.api.service;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ApiTokenService {
	
	private static Logger log = LoggerFactory.getLogger(ApiTokenService.class);
	private static String TOKEN_FILENAME = "./skyswitch-api-token";
	
	/**
	 * Size of seed. Needs to be an even number.
	 */
	public static final int API_TOKEN_RAW_LENGTH = 30;
	
	private SecureRandom secureRandom = new SecureRandom();
	
	private String currentToken;
	
	public ApiTokenService() {
		File tokenFile = new File(TOKEN_FILENAME);
		if(tokenFile.exists()) {
			try {
				currentToken = FileUtils.readFileToString(tokenFile);
				log.info("loaded skyswitch api token from file.");
			} catch (IOException e) {
				log.warn("Failed to load skyswitch api token. Trying to generate a new one.");
				resetToken();
			}
		} else {
			log.info("Token file was not found. Generating a new API token...");
			resetToken();
		}
	}

	public void resetToken() {
		byte[] random = new byte[API_TOKEN_RAW_LENGTH];
		secureRandom.nextBytes(random);
		
		currentToken = Hex.encodeHexString(random);
		
		try {
			FileUtils.writeStringToFile(new File(TOKEN_FILENAME), currentToken);
		} catch (IOException e) {
			log.warn("Could not write api token to file.", e);
		}
	}
	
	public boolean isValid(String checkToken) {
		if(StringUtils.isEmpty(checkToken)) {
			return false;
		}
		
		return currentToken.equals(checkToken);
	}
}
