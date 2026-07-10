package com.guciowons.yummify.common.security.application;

import com.guciowons.yummify.common.security.domain.Permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecuredByPermission {
    Permission value();
}
