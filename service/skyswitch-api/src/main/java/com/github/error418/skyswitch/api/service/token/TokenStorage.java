package com.github.error418.skyswitch.api.service.token;

public interface TokenStorage {
	public void persistToken(String token) throws TokenStorageException;
	
	public String loadToken() throws TokenStorageException;
	
	public boolean isInitialized();
}
