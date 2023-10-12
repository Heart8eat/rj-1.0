package com.rj.backendjixian.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * 标记不需要Token的api
 */

@Target({ElementType.METHOD, ElementType.TYPE})
public @interface PassToken {

        boolean required() default true;
}
