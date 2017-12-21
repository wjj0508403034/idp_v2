package com.huoyun.common.persistence;

import java.io.Serializable;
import java.util.Properties;
import java.util.UUID;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.AbstractUUIDGenerator;
import org.hibernate.id.Configurable;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdGenerator extends AbstractUUIDGenerator implements Configurable {

	@SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(IdGenerator.class);

	public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
	}

	public Serializable generate(SessionImplementor sessionImplementor, Object entity) throws HibernateException {
		UUID uuid = UUID.randomUUID();
		long bits = uuid.getMostSignificantBits();
		if (bits < 0) {
			return -bits;
		}
		return bits;
	}

}
