package com.github.error418.skyswitch.api.service.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.repository.FeatureState;

import com.github.error418.skyswitch.api.model.FeatureStateModel;

@RestController()
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
	
	@RequestMapping(method = RequestMethod.POST, value="{id}")
	public ResponseEntity<?> modifyFeature(@PathParam("id") String featureId) {
		Optional<Feature> feature = findFeature(featureId);
		if(feature.isPresent()) {
			FeatureState state = manager.getFeatureState(feature.get());
			
			
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	private Optional<Feature> findFeature(String featureId) {
		return manager.getFeatures().stream().filter(feature -> feature.name().equals(featureId)).findFirst();
	}
}
