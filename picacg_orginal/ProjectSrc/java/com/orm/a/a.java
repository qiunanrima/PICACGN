package com.orm.a;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: picacg_1.jar:com/orm/a/a.class */
public @interface a {
    boolean bp() default false;

    boolean bq() default false;

    String name();
}
