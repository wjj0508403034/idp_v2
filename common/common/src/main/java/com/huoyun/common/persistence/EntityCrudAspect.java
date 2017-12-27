package com.huoyun.common.persistence;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import com.huoyun.common.service.AbstractBusinessService;

@Aspect
public class EntityCrudAspect extends AbstractBusinessService {

	@Before("execution(* javax.persistence.EntityManager.persist(..)) && args(obj)")
	public void beforeCreate(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			entity.beforeCreate();
			this.entityConfigurer().beforeCreate(entity);
		}
	}

	@After("execution(* javax.persistence.EntityManager.persist(..)) && args(obj)")
	public void afterCreate(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			entity.afterCreate();
			this.entityConfigurer().afterCreate(entity);
		}
	}

	@Before("execution(* javax.persistence.EntityManager.merge(..)) && args(obj)")
	public void beforeUpdate(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			entity.beforeUpdate();
			this.entityConfigurer().beforeUpdate(entity);
		}
	}

	@After("execution(* javax.persistence.EntityManager.merge(..)) && args(obj)")
	public void afterUpdate(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			entity.afterUpdate();
			this.entityConfigurer().afterUpdate(entity);
		}
	}

	@Before("execution(* javax.persistence.EntityManager.delete(..)) && args(obj)")
	public void beforeDelete(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			entity.beforeDelete();
			this.entityConfigurer().beforeDelete(entity);
		}
	}

	@After("execution(* javax.persistence.EntityManager.delete(..)) && args(obj)")
	public void afterDelete(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			entity.afterDelete();
			this.entityConfigurer().afterDelete(entity);
		}
	}

	private EntityConfigurer entityConfigurer() {
		return this.getBean(EntityConfigurerAdapter.class).getEntityConfigurer();
	}
}
