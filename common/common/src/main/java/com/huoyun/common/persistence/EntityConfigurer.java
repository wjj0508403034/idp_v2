package com.huoyun.common.persistence;

import java.util.ArrayList;
import java.util.List;

public class EntityConfigurer {

	private final List<EntityChangedEventListener> entityChangedListeners = new ArrayList<>();

	public void addListener(EntityChangedEventListener listener) {
		this.entityChangedListeners.add(listener);
	}

	public void beforeCreate(Entity entity) {
		for (EntityChangedEventListener listener : this.entityChangedListeners) {
			listener.beforeCreate(entity);
		}
	}

	public void afterCreate(Entity entity) {
		for (EntityChangedEventListener listener : this.entityChangedListeners) {
			listener.afterCreate(entity);
		}
	}

	public void beforeUpdate(Entity entity) {
		for (EntityChangedEventListener listener : this.entityChangedListeners) {
			listener.beforeUpdate(entity);
		}
	}

	public void afterUpdate(Entity entity) {
		for (EntityChangedEventListener listener : this.entityChangedListeners) {
			listener.afterUpdate(entity);
		}
	}

	public void beforeDelete(Entity entity) {
		for (EntityChangedEventListener listener : this.entityChangedListeners) {
			listener.beforeDelete(entity);
		}
	}

	public void afterDelete(Entity entity) {
		for (EntityChangedEventListener listener : this.entityChangedListeners) {
			listener.afterDelete(entity);
		}
	}
}
