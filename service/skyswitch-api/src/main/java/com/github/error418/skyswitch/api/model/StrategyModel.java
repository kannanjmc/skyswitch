package com.github.error418.skyswitch.api.model;

import org.togglz.core.activation.Parameter;
import org.togglz.core.spi.ActivationStrategy;

public class StrategyModel {
	private String id;
	private String name;
	private Parameter[] parameters;
	
	public StrategyModel() {
		
	}
	
	public StrategyModel(ActivationStrategy strategy) {
		this.id = strategy.getId();
		this.name = strategy.getName();
		this.parameters = strategy.getParameters();
	}
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Parameter[] getParameters() {
		return parameters;
	}

	public void setParameters(Parameter[] parameters) {
		this.parameters = parameters;
	}
}
