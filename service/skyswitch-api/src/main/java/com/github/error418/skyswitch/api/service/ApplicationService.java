package com.github.error418.skyswitch.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;

import com.github.error418.skyswitch.api.model.FeatureStateModel;

@RestController()
@RequestMapping(value = "/api/skyswitch/feature", produces = MediaType.APPLICATION_JSON_VALUE)
public class ApplicationService {

	@Autowired
	FeatureManager manager;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> listApplications() {
		List<FeatureStateModel> featureStates = new ArrayList<>();
		
		for(Feature feature : manager.getFeatures()) {
			featureStates.add(new FeatureStateModel(manager.getFeatureState(feature)));
		}
		
		return new ResponseEntity<>(featureStates, HttpStatus.OK);
	}
	
}
