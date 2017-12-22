package com.huoyun.common.metadata.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.huoyun.common.metadata.NamespaceConstants;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BusinessObject {

	String namespace() default NamespaceConstants.SYSTEM_BUSINESS_OBJECT_NAMESPACE;

	String name() default "";

	String label() default "";

	boolean exposed() default true;
}
