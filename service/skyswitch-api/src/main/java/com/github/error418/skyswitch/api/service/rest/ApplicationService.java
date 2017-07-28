package com.github.error418.skyswitch.api.service.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

import com.github.error418.skyswitch.api.model.FeatureStateModel;

@RestController
@RequestMapping(value = "/api/skyswitch/feature", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationService {

	private final FeatureManager manager;

	@Autowired
	public ApplicationService(final FeatureManager manager) {
		this.manager = manager;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<FeatureStateModel>> listFeatures() {
		List<FeatureStateModel>  featureStates = new ArrayList<>();
		
		for(Feature feature : manager.getFeatures()) {
			featureStates.add(new FeatureStateModel(manager.getFeatureState(feature)));
		}
		
		return new ResponseEntity<>(featureStates, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> modifyFeature(@RequestBody FeatureStateModel featureState) {
		Optional<Feature> feature = findFeature(featureState.getFeatureName());
		if(feature.isPresent()) {
			FeatureState state = manager.getFeatureState(feature.get());
			
			if(featureState.isEnabled() != null) {
				state.setEnabled(featureState.isEnabled());
			}
			
			state.setStrategyId(featureState.getStrategyId());
			Map<String, String> parameters = featureState.getStrategyParameter();
			if(parameters != null) {
				parameters.entrySet().forEach(entry -> state.setParameter(entry.getKey(), entry.getValue()));
			}
			
			manager.setFeatureState(state);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private Optional<Feature> findFeature(String featureId) {
		return manager.getFeatures().stream().filter(feature -> feature.name().equals(featureId)).findFirst();
	}
}
