package com.github.error418.skyswitch.api.service.token;

import java.security.SecureRandom;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class ApiTokenService {
	
	private static Logger log = LoggerFactory.getLogger(ApiTokenService.class);
	
	/**
	 * Size of seed. Needs to be an even number.
	 */
	public static final int API_TOKEN_RAW_LENGTH = 30;
	
	private SecureRandom secureRandom = new SecureRandom();
	
	private String currentToken;
	private final TokenStorage tokenStorage;
	
	@Autowired
	public ApiTokenService(TokenStorage tokenStorage) {
		this.tokenStorage = tokenStorage;

		if(tokenStorage.isInitialized()) {
			try {
				currentToken = tokenStorage.loadToken();
				if(StringUtils.isEmpty(currentToken)) {
					resetToken();
				}
			} catch (TokenStorageException e) {
				log.warn("Failed to load skyswitch api token. Trying to generate a new one.");
				resetToken();
			}
		} else {
			log.info("Token file was not found. Generating a new API token...");
			resetToken();
		}
		
	}

	/**
	 * Resets the token by generating a new one.
	 * The new token will be persisted using the configured {@link TokenStorage}.
	 */
	public void resetToken() {
		byte[] random = new byte[API_TOKEN_RAW_LENGTH];
		secureRandom.nextBytes(random);
		
		currentToken = Hex.encodeHexString(random);
		
		try {
			tokenStorage.persistToken(currentToken);
		} catch (TokenStorageException e) {
			log.warn("could not persist api token", e);
		}
	}
	
	/**
	 * Checks, if the given token is equal to the current api token.
	 * 
	 * @param checkToken token to check
	 * @return true, if checkToken equals current api token
	 */
	public boolean isValid(String checkToken) {
		if(StringUtils.isEmpty(checkToken)) {
			return false;
		}
		
		return currentToken.equals(checkToken);
	}
}
