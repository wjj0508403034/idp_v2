package com.huoyun.common.persistence;

public interface EntityChangedEventListener {

	void beforeCreate(Entity entity);

	void afterCreate(Entity entity);

	void beforeUpdate(Entity entity);

	void afterUpdate(Entity entity);

	void beforeDelete(Entity entity);

	void afterDelete(Entity entity);
}
