package com.github.error418.skyswitch.api.model;

import java.util.Map;

import org.togglz.core.repository.FeatureState;

public class FeatureStateModel {

	private String featureName;
	private Boolean enabled;
	private String strategyId;
	private Map<String, String> strategyParameter;
	

	public FeatureStateModel() {

	}

	public FeatureStateModel(FeatureState state) {
		this.featureName = state.getFeature().name();
		this.enabled = state.isEnabled();
		this.strategyId = state.getStrategyId();
		this.strategyParameter = state.getParameterMap();
	}

	public String getFeatureName() {
		return featureName;
	}

	public Boolean isEnabled() {
		return enabled;
	}

	public String getStrategyId() {
		return strategyId;
	}

	public Map<String, String> getStrategyParameter() {
		return strategyParameter;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public void setStrategyId(String strategyId) {
		this.strategyId = strategyId;
	}

	public void setStrategyParameter(Map<String, String> strategyParameter) {
		this.strategyParameter = strategyParameter;
	}

}
