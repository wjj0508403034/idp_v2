package com.huoyun.common.ui.impl;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.huoyun.common.service.AbstractBusinessService;
import com.huoyun.common.ui.UIMetaLoader;
import com.huoyun.common.ui.xml.Root;

public class UIMetaLoaderImpl extends AbstractBusinessService implements
		UIMetaLoader {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(UIMetaLoaderImpl.class);

	private final Map<String, Root> cache = new HashMap<>();

	private static final String DEAULT_UIMETA_LOCATION = "classpath:/META-INF/ui-meta/*.xml";

	@Value("${ui.meta.location:" + DEAULT_UIMETA_LOCATION + "}")
	private String location;

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public Root getUiElement(String boNamespace, String boName) {
		return this.cache.get(String.format("%s.%s", boNamespace, boName));
	}

	@PostConstruct
	public void init() throws IOException, JAXBException {
		LOGGER.info("Start loading UI meta xml files ...");
		Unmarshaller unmarshaller = this.getMarshaller();

		for (Resource resource : this.getResourceResolver().getResources(
				this.getLocation())) {
			Root root = this.loadUIMeta(unmarshaller, resource);
			this.cache.put(this.getRootName(root), root);
		}

		LOGGER.info("Load UI meta xml files end.");
	}

	private String getRootName(Root root) {
		return String.format("%s.%s", root.getNamespace(), root.getName());
	}

	private Root loadUIMeta(Unmarshaller unmarshaller, Resource resource)
			throws IOException, JAXBException {
		LOGGER.info("load ui meta from file {}", resource.getFilename());
		URL url = resource.getURL();
		return (Root) unmarshaller.unmarshal(url);
	}

	private Unmarshaller getMarshaller() throws JAXBException {
		JAXBContext jc = JAXBContext.newInstance(Root.class);
		return jc.createUnmarshaller();
	}

	private ResourcePatternResolver getResourceResolver() {
		return new PathMatchingResourcePatternResolver(
				UIMetaLoader.class.getClassLoader());
	}

}
