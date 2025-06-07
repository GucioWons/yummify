package com.guciowons.yummify.common.security.aspect;

import com.guciowons.yummify.common.security.enumerated.UserRole;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SecuredByRole {
    UserRole value();
}
