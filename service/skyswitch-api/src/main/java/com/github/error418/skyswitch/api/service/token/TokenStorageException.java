package com.github.error418.skyswitch.api.service.token;

public class TokenStorageException extends Exception {

	private static final long serialVersionUID = 7140662643211209379L;

	public TokenStorageException(String message) {
		super(message);
	}
	
	public TokenStorageException(String message, Throwable cause) {
		super(message, cause);
	}
}
