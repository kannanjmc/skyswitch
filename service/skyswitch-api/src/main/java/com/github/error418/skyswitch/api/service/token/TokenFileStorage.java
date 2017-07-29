package com.github.error418.skyswitch.api.service.token;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

/**
 * Stores the token inside a file on the file system.
 */
public class TokenFileStorage implements TokenStorage {

	private static final String DEFAULT_TOKEN_FILENAME = "./skyswitch-api-token";
	
	private final File tokenFile;
	
	public TokenFileStorage() {
		tokenFile = new File(DEFAULT_TOKEN_FILENAME);
	}
	
	@Override
	public void persistToken(String token) throws TokenStorageException {
		try {
			FileUtils.writeStringToFile(tokenFile, token);
		} catch (IOException e) {
			throw new TokenStorageException("Could not write token to file.");
		}
	}

	@Override
	public String loadToken() throws TokenStorageException {
		if(tokenFile.exists()) {
			try {
				return FileUtils.readFileToString(tokenFile);
			} catch (IOException e) {
				throw new TokenStorageException("Could not load token file", e);
			}
		} 
		
		throw new TokenStorageException("Token file is missing");
	}

	@Override
	public boolean isInitialized() {
		return tokenFile.exists();
	}

}
