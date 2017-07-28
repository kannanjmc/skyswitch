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

	private FeatureService utt;
	private FeatureManager manager;
	
	@Before
	public void init() throws Exception {
		manager = Mockito.mock(FeatureManager.class);
		
		utt = new FeatureService(manager);
	}
	
	@Test
	public void testFeatureList() throws Exception {
		Feature mockFeature = Mockito.mock(Feature.class);
		Mockito.when(mockFeature.name()).thenReturn("MockFeature");
		
		Set<Feature> response = new HashSet<Feature>();
		response.add(mockFeature);
		
		FeatureState mockFeatureState = Mockito.mock(FeatureState.class);
		Mockito.when(mockFeatureState.getFeature()).thenReturn(mockFeature);
		
		Mockito.when(manager.getFeatures()).thenReturn(response);
		Mockito.when(manager.getFeatureState(Mockito.any(Feature.class))).thenReturn(mockFeatureState);
		
		ResponseEntity<List<FeatureStateModel>> listEntity = utt.listFeatures();
		
		Assert.assertThat(listEntity.getBody(), Matchers.iterableWithSize(1));
		Assert.assertEquals(HttpStatus.OK, listEntity.getStatusCode());
	}
	
	
}
