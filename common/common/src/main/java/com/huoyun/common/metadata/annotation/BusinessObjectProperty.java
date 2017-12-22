package com.huoyun.common.metadata.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface BusinessObjectProperty {

	String label() default "";

	boolean exposed() default true;

	boolean mandatory() default false;

	boolean readonly() default false;

	boolean searchable() default true;

}
