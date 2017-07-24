package com.error418.skyswitch.console.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.github.error418.skyswitch.api.service.ApiTokenService;

public class ApiTokenServiceTest {
	
	private ApiTokenService utt;
	
	@Before
	public void init() {
		utt = new ApiTokenService();
	}
	
	@Test
	public void testValidateNull() {
		Assert.assertFalse(utt.isValid(null));
	}
	
	@Test
	public void testValidateEmpty() {
		Assert.assertFalse(utt.isValid(""));
		Assert.assertFalse(utt.isValid("  "));
	}
}
