package com.google.gson.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: picacg_1.jar:com/google/gson/annotations/SerializedName.class */
public @interface SerializedName {
    String[] alternate() default {};

    String value();
}
