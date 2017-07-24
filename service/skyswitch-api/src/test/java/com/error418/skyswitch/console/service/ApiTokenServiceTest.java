package com.error418.skyswitch.console.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.github.error418.skyswitch.api.service.token.ApiTokenService;
import com.github.error418.skyswitch.api.service.token.TokenStorage;

public class ApiTokenServiceTest {
	
	private static final String TOKEN_VALUE = "testTokenValue";

	private ApiTokenService utt;

	private TokenStorage mockStorage;
	
	@Before
	public void init() throws Exception {
		mockStorage = Mockito.mock(TokenStorage.class);
		Mockito.when(mockStorage.loadToken()).thenReturn(TOKEN_VALUE);
		Mockito.doNothing().when(mockStorage).persistToken(Mockito.anyString());
		Mockito.when(mockStorage.isInitialized()).thenReturn(true);
	}
	
	@Test
	public void testValidateNull() {
		utt = new ApiTokenService(mockStorage);
		Assert.assertFalse(utt.isValid(null));
	}
	
	@Test
	public void testValidateEmpty() {
		utt = new ApiTokenService(mockStorage);
		Assert.assertFalse(utt.isValid(""));
		Assert.assertFalse(utt.isValid("  "));
	}
	
	@Test
	public void testInvalidToken() {
		utt = new ApiTokenService(mockStorage);
		Assert.assertFalse(utt.isValid("ABC"));
		Assert.assertFalse(utt.isValid(null));
	}
	
	@Test
	public void testValidToken() {
		utt = new ApiTokenService(mockStorage);
		Assert.assertTrue(utt.isValid(TOKEN_VALUE));
	}
	
	@Test
	public void testInit() throws Exception {
		
		utt = new ApiTokenService(mockStorage);
		Mockito.verify(mockStorage, Mockito.times(1)).loadToken();
		Mockito.verify(mockStorage, Mockito.times(0)).persistToken(Mockito.anyString());
		
		mockStorage = Mockito.mock(TokenStorage.class);
		Mockito.when(mockStorage.loadToken()).thenReturn(TOKEN_VALUE);
		Mockito.doNothing().when(mockStorage).persistToken(Mockito.anyString());
		Mockito.when(mockStorage.isInitialized()).thenReturn(false);
		
		utt = new ApiTokenService(mockStorage);
		Mockito.verify(mockStorage, Mockito.times(0)).loadToken();
		Mockito.verify(mockStorage, Mockito.times(1)).persistToken(Mockito.anyString());
	}
}
