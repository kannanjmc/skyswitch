package com.github.error418.skyswitch.api.model;

import org.togglz.core.repository.FeatureState;

public class FeatureStateModel {

	private String featureName;
	private boolean enabled;

	public FeatureStateModel() {

	}

	public FeatureStateModel(FeatureState state) {
		this.featureName = state.getFeature().name();
		this.enabled = state.isEnabled();
	}

	public String getFeatureName() {
		return featureName;
	}

	public boolean isEnabled() {
		return enabled;
	}

}
