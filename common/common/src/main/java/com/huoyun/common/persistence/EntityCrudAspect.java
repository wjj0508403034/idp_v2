package com.huoyun.common.persistence;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.DateTime;

import com.huoyun.common.service.AbstractBusinessService;

@Aspect
public class EntityCrudAspect extends AbstractBusinessService {

	@Before("execution(* javax.persistence.EntityManager.persist(..)) && args(obj)")
	public void beforeCreate(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			this.entityConfigurer().beforeCreate(entity);

			DateTime now = DateTime.now();
			entity.setCreateTime(now);
			entity.setUpdateTime(now);
		}
	}

	@After("execution(* javax.persistence.EntityManager.persist(..)) && args(obj)")
	public void afterCreate(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			this.entityConfigurer().afterCreate(entity);
		}
	}

	@Before("execution(* javax.persistence.EntityManager.merge(..)) && args(obj)")
	public void beforeUpdate(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			this.entityConfigurer().beforeUpdate(entity);

			DateTime now = DateTime.now();
			entity.setUpdateTime(now);
		}
	}

	@After("execution(* javax.persistence.EntityManager.merge(..)) && args(obj)")
	public void afterUpdate(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			this.entityConfigurer().afterUpdate(entity);
		}
	}

	@Before("execution(* javax.persistence.EntityManager.delete(..)) && args(obj)")
	public void beforeDelete(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			this.entityConfigurer().beforeDelete(entity);
		}
	}

	@After("execution(* javax.persistence.EntityManager.delete(..)) && args(obj)")
	public void afterDelete(Object obj) {
		if (obj instanceof Entity) {
			Entity entity = (Entity) obj;
			this.entityConfigurer().afterDelete(entity);
		}
	}

	private EntityConfigurer entityConfigurer() {
		return this.getBean(EntityConfigurerAdapter.class).getEntityConfigurer();
	}
}
