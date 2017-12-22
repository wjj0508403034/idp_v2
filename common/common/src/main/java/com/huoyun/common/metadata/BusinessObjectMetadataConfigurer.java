package com.huoyun.common.metadata;

public class BusinessObjectMetadataConfigurer {

	private static final String DEFAULT_SCAN_PACKAGE = "com.huoyun";
	private static final String DEFAULT_BO_LABEL_LOCALIZATION_PATTERN = "bo.label.%s";
	private static final String DEFAULT_BO_PROPERTY_LABEL_LOCALIZATION_PATTERN = "bo.property.label.%s.%s";
	private String[] scanPackages = new String[] { DEFAULT_SCAN_PACKAGE };
	private String boLabelLocalizationPattern = DEFAULT_BO_LABEL_LOCALIZATION_PATTERN;
	private String boPropertyLabelLocalizationPattern = DEFAULT_BO_PROPERTY_LABEL_LOCALIZATION_PATTERN;

	public String[] getScanPackages() {
		return scanPackages;
	}

	public void setScanPackages(String[] scanPackages) {
		this.scanPackages = scanPackages;
	}

	public String getBoLabelLocalizationPattern() {
		return boLabelLocalizationPattern;
	}
	
	public String getBoPropertyLabelLocalizationPattern() {
		return boPropertyLabelLocalizationPattern;
	}

	public void setBoLabelLocalizationPattern(String boLabelLocalizationPattern) {
		this.boLabelLocalizationPattern = boLabelLocalizationPattern;
	}

}
