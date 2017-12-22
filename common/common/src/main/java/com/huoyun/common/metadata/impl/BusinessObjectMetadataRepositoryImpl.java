package com.huoyun.common.metadata.impl;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.huoyun.common.metadata.BusinessObjectMetadata;
import com.huoyun.common.metadata.BusinessObjectMetadataConfigurer;
import com.huoyun.common.metadata.BusinessObjectMetadataConfigurerAdapter;
import com.huoyun.common.metadata.BusinessObjectMetadataRepository;
import com.huoyun.common.metadata.NamespaceConstants;
import com.huoyun.common.metadata.annotation.BusinessObject;
import com.huoyun.common.service.AbstractBusinessService;

@Component
public class BusinessObjectMetadataRepositoryImpl extends AbstractBusinessService
		implements BusinessObjectMetadataRepository {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessObjectMetadataRepositoryImpl.class);

	private final Map<String, BusinessObjectMetadata> boMetaMap = new HashMap<>();

	@SuppressWarnings("unchecked")
	@PostConstruct
	public void init() {
		LOGGER.info("init system bo metadata ...");

		for (Class<?> klass : this.loadBusinessObjects()) {
			Class<? extends BusinessObject> boClass = (Class<? extends BusinessObject>) klass;
			BusinessObjectMetadataImpl boMeta = new BusinessObjectMetadataImpl(boClass, this.getApplicationContext());
			this.boMetaMap.put(boMeta.getIdentityName(), boMeta);
		}

		LOGGER.info("init system bo metadata end.");
	}

	@Override
	public BusinessObjectMetadata getBoMeta(String boNamespace, String boName) {
		return this.boMetaMap.get(String.format("%s.%s", boNamespace, boName));
	}

	@Override
	public BusinessObjectMetadata getBoMeta(String boName) {
		return this.getBoMeta(NamespaceConstants.SYSTEM_BUSINESS_OBJECT_NAMESPACE, boName);
	}

	private Set<Class<?>> loadBusinessObjects() {
		Reflections reflections = new Reflections(new ConfigurationBuilder().setUrls(this.scanUrls())
				.setScanners(new SubTypesScanner(), new TypeAnnotationsScanner()).useParallelExecutor());
		return reflections.getTypesAnnotatedWith(BusinessObject.class);
	}

	private BusinessObjectMetadataConfigurer boMetaConfigurer() {
		return this.getBean(BusinessObjectMetadataConfigurerAdapter.class).getBoMetaConfigurer();
	}

	private List<URL> scanUrls() {
		List<URL> urls = new ArrayList<>();
		for (String packageName : this.boMetaConfigurer().getScanPackages()) {
			urls.addAll(ClasspathHelper.forPackage(packageName));
		}
		return urls;
	}

}
