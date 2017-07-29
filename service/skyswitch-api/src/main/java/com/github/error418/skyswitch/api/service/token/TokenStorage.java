package com.github.error418.skyswitch.api.service.token;

/**
 * Token storage interface.
 * 
 */
public interface TokenStorage {
	/**
	 * Persists the given token.
	 * 
	 * @param token token value to persist
	 * @throws TokenStorageException thrown when token could not be persisted
	 */
	public void persistToken(String token) throws TokenStorageException;
	
	/**
	 * Loads the token
	 * 
	 * @return token value
	 * @throws TokenStorageException thrown when the token could not be loaded from storage
	 */
	public String loadToken() throws TokenStorageException;
	
	/**
	 * Checks, if the storage is already initialized.
	 * 
	 * @return true, if the storage is already initialized
	 */
	public boolean isInitialized();
}
