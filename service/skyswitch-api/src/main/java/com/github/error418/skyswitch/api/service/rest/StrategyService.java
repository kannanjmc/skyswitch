package com.github.error418.skyswitch.api.service.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.manager.FeatureManager;

import com.github.error418.skyswitch.api.model.StrategyModel;

@RestController
@RequestMapping(value = "/api/skyswitch/strategy", produces = MediaType.APPLICATION_JSON_VALUE)
public class StrategyService {

	private final FeatureManager manager;

	@Autowired
	public StrategyService(final FeatureManager manager) {
		this.manager = manager;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<StrategyModel>> listStrategies() {
		List<StrategyModel> result = new ArrayList<>();
		manager.getActivationStrategies().forEach(strategy -> result.add(new StrategyModel(strategy)));
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
