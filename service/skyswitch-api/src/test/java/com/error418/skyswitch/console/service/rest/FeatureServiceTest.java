package com.error418.skyswitch.console.service.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

import com.github.error418.skyswitch.api.model.FeatureStateModel;
import com.github.error418.skyswitch.api.service.rest.FeatureService;

public class FeatureServiceTest {

	private static final String MOCK_FEATURE_NAME = "MockFeature";

	private FeatureService utt;
	private FeatureManager manager;
	private Feature mockFeature;
	private FeatureState mockFeatureState;	
	
	@Before
	public void init() throws Exception {
		manager = Mockito.mock(FeatureManager.class);
		
		mockFeature = Mockito.mock(Feature.class);
		Mockito.when(mockFeature.name()).thenReturn(MOCK_FEATURE_NAME);
		
		Set<Feature> response = new HashSet<Feature>();
		response.add(mockFeature);
		
		mockFeatureState = Mockito.mock(FeatureState.class);
		Mockito.when(mockFeatureState.getFeature()).thenReturn(mockFeature);
		
		Mockito.when(manager.getFeatures()).thenReturn(response);
		Mockito.when(manager.getFeatureState(Mockito.any(Feature.class))).thenReturn(mockFeatureState);

		utt = new FeatureService(manager);
	}
	
	@Test
	public void testFeatureList() throws Exception {
		ResponseEntity<List<FeatureStateModel>> listEntity = utt.listFeatures();
		
		Assert.assertThat(listEntity.getBody(), Matchers.iterableWithSize(1));
		Assert.assertEquals(HttpStatus.OK, listEntity.getStatusCode());
	}
	
	@Test
	public void testFeatureApply() throws Exception {
		FeatureStateModel model = new FeatureStateModel();
		model.setFeatureName(MOCK_FEATURE_NAME);
		
		model.setEnabled(true);
		
		ResponseEntity<?> response = utt.modifyFeature(model);
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		Mockito.verify(manager, Mockito.times(1)).setFeatureState(Mockito.any(FeatureState.class));
		Mockito.verify(mockFeatureState, Mockito.times(1)).setEnabled(true);
	}
	
	@Test
	public void testFeatureNotFoundApply() throws Exception {
		FeatureStateModel model = new FeatureStateModel();
		model.setFeatureName("I DO NOT EXIST");
		
		ResponseEntity<?> response = utt.modifyFeature(model);
		
		Assert.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}
	
	@Test
	public void testFeatureStrategyApply() throws Exception {
		String strategyId = "TEST STRATEGY";

		FeatureStateModel model = new FeatureStateModel();
		model.setFeatureName(MOCK_FEATURE_NAME);
		
		model.setStrategyId(strategyId);
		
		ResponseEntity<?> response = utt.modifyFeature(model);
		
		Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
		
		Mockito.verify(manager, Mockito.times(1)).setFeatureState(Mockito.any(FeatureState.class));
		Mockito.verify(mockFeatureState, Mockito.times(1)).setStrategyId(strategyId);
	}
	
	
}
